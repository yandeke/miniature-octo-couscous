<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <title></title>
  <script type="text/javascript" src="/theme/js/jquery-3.0.0.min.js"></script>
  <script type="text/javascript">
    $(function(){
      bindForm();
    });
    function bindForm(){
      $("#submitBtn").bind("click", function(){
        var keyWords = $("#keyWords").val();
        if(keyWords != undefined && $.trim(keyWords).length > 0){
          $.ajax({
            async: false,
            type: "post",
            url: "searchContent.html",
            data: {
              "keyWords": keyWords
            },
            cache: false,
            dataType: "json",
            success: function (data) {
            }
          });
        }
      });
    }
  </script>
  <style>
    .tableForm{
      width: 100%;
      margin-top: 20%;
    }
    .searchContent{

    }
  </style>
</head>
<body>
      <table class="tableForm" align="center" width="100%" style="text-align: center; margin-top: 15%">
        <tr>
          <td>
            <input id="keyWords" class="searchContent" name="keyWords" type="type" PLACEHOLDER="输入搜索内容" />
            <input type="button" id="submitBtn" value="提交">
          </td>
        </tr>
      </table>
</body>
</html>
