<%-- 
    Document   : cartnot
    Created on : 2017/08/22, 12:05:48
    Author     : PCUser
--%>

<%@page import="jums.JumsHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%  
    //JumsHelperインスタンス生成
    JumsHelper jh = JumsHelper.getInstance();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>カート内に商品がありません。</h1>
        <br>
        <%= jh.Home()%><br>
        <br>
        <p><img src="https://image.freepik.com/free-icon/no-translate-detected_318-48236.jpg" alt="github"></a></p>
</body>
</html>
