package com.search.www;

/**
 * Created by Administrator on 2016/9/1.
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JSoupBaiduSearcher{
    private static final Logger LOG = LoggerFactory.getLogger(JSoupBaiduSearcher.class);

    public SearchResult search(String keyword) {
        return search(keyword, 1);
    }
    public SearchResult search(String keyword, int page) {
        int pageSize = 10;
        //�ٶ��������ÿҳ��СΪ10��pn��������Ĳ���ҳ�������Ƿ��ؽ���Ŀ�ʼ��
        //���ȡ��һҳ��pn=0���ڶ�ҳ��pn=10������ҳ��pn=20���Դ����ƣ������ģʽ��(page-1)*pageSize
        String url = "http://www.baidu.com/s?pn="+(page-1)*pageSize+"&wd="+keyword;

        SearchResult searchResult = new SearchResult();
        searchResult.setPage(page);
        List<Webpage> webpages = new ArrayList<Webpage>();
        try {
            Document document = Jsoup.connect(url).get();

            //��ȡ���������Ŀ
            int total = getBaiduSearchResultCount(document);
            searchResult.setTotal(total);
            int len = 10;
            if (total < 1) {
                return null;
            }
            //����������Ľ������һҳ
            if (total < 10) {
                len = total;
            }
            for (int i = 0; i < len; i++) {
                String titleCssQuery = "html body div div div div#content_left div#" + (i + 1 + (page-1)*pageSize) + ".result.c-container h3.t a";
                String summaryCssQuery = "html body div div div div#content_left div#" + (i + 1 + (page-1)*pageSize) + ".result.c-container div.c-abstract";
                LOG.debug("titleCssQuery:" + titleCssQuery);
                LOG.debug("summaryCssQuery:" + summaryCssQuery);
                Element titleElement = document.select(titleCssQuery).first();
                String href = "";
                String titleText = "";
                if(titleElement != null){
                    titleText = titleElement.text();
                    href = titleElement.attr("href");
                }else{
                    //����ٶȰٿ�
                    titleCssQuery = "html body div#out div#in div#wrapper div#container div#content_left div#1.result-op h3.t a";
                    summaryCssQuery = "html body div#out div#in div#wrapper div#container div#content_left div#1.result-op div p";
                    LOG.debug("����ٶȰٿ� titleCssQuery:" + titleCssQuery);
                    LOG.debug("����ٶȰٿ� summaryCssQuery:" + summaryCssQuery);
                    titleElement = document.select(titleCssQuery).first();
                    if(titleElement != null){
                        titleText = titleElement.text();
                        href = titleElement.attr("href");
                    }
                }
                LOG.debug(titleText);
                Element summaryElement = document.select(summaryCssQuery).first();
                //����ٶ�֪��
                if(summaryElement == null){
                    summaryCssQuery = summaryCssQuery.replace("div.c-abstract","font");
                    LOG.debug("����ٶ�֪�� summaryCssQuery:" + summaryCssQuery);
                    summaryElement = document.select(summaryCssQuery).first();
                }
                String summaryText = "";
                if(summaryElement != null){
                    summaryText = summaryElement.text();
                }
                LOG.debug(summaryText);

                if (titleText != null && !"".equals(titleText.trim()) && summaryText != null && !"".equals(summaryText.trim())) {
                    Webpage webpage = new Webpage();
                    webpage.setTitle(titleText);
                    webpage.setUrl(href);
                    webpage.setSummary(summaryText);
                    if (href != null) {
                        String content = Tools.getHTMLContent(href);
                        webpage.setContent(content);
                    } else {
                        LOG.info("ҳ����ȷ��ȡʧ��");
                    }
                    webpages.add(webpage);
                } else {
                    LOG.error("��ȡ��������б������:" + titleText + " - " + summaryText);
                }
            }


        } catch (IOException ex) {
            LOG.error("��������",ex);
        }
        searchResult.setWebpages(webpages);
        return searchResult;
    }
    /**
     * ��ȡ�ٶ����������
     * ��ȡ�����ı����������֣�
     * �ٶ�Ϊ���ҵ���ؽ��Լ13,200��
     * @param document �ĵ�
     * @return �����
     */
    private int getBaiduSearchResultCount(Document document){
        String cssQuery = "html body div div div div.nums";
        LOG.debug("total cssQuery: " + cssQuery);
        Element totalElement = document.select(cssQuery).first();
        String totalText = totalElement.text();
        LOG.info("��������ı���" + totalText);

        String regEx="[^0-9]";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(totalText);
        totalText = matcher.replaceAll("");
        int total = Integer.parseInt(totalText);
        LOG.info("�����������" + total);
        return total;
    }

    public static void main(String[] args) {
        JSoupBaiduSearcher searcher = new JSoupBaiduSearcher();
        SearchResult searchResult = searcher.search("���д�",1);
        List<Webpage> webpages = searchResult.getWebpages();
        if (webpages != null) {
            int i = 1;
            LOG.info("������� ��ǰ�� " + searchResult.getPage() + " ҳ��ҳ���СΪ��" + searchResult.getPageSize() + " ���н������" + searchResult.getTotal());
            for (Webpage webpage : webpages) {
                LOG.info("������� " + (i++) + " ��");
                LOG.info("���⣺" + webpage.getTitle());
                LOG.info("URL��" + webpage.getUrl());
                LOG.info("ժҪ��" + webpage.getSummary());
                LOG.info("���ģ�" + webpage.getContent());
                LOG.info("");
            }
        } else {
            LOG.error("û�����������");
        }
    }
}