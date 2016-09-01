<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/9/1
  Time: 13:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
  <script src="/theme/js/jquery-3.0.0.min.js" />

  <style>
    .content{
      width: 100%;
      margin-top: 10%;
    }
    .tableForm{
      width: 100%;
    }
    .searchContent{

    }
  </style>
</head>
<body>
  <div class="content">
      <table class="tableForm" align="center" width="100%" style="text-align: center">
        <tr>
          <td>
            <input id="keyWords" class="searchContent" name="keyWords" type="type" PLACEHOLDER="输入搜索内容" />
            <input type="button" id="submitBtn" value="提交">
          </td>
        </tr>
      </table>
  </div>
</body>
<script>
  $(function(){
    $("submitBtn").bind("click", function(){
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
            s=data;
            if(!data){
              $("#label").html("可以使用");

              return true;
            }else{
              $("#label").html("已经注册了，换个名字");
              return false;
            }
          }
        });
      }
    });
  });
</script>
</html>
