<%-- 
    Document   : mydeleteresult
    Created on : 2017/08/21, 19:24:41
    Author     : PCUser
--%>

<%@page import="jums.UserDataDAO"%>
<%@page import="jums.UserDataDTO"%>
<%@page import="jums.JumsHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    //JumsHelperインスタンス生成
    JumsHelper jh = JumsHelper.getInstance();
    //セッションスタート
    HttpSession hs = request.getSession();
    //セッションクリア
    hs.invalidate();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>削除しました。</h1>
        <p><img src="https://www.sozai-library.com/wp-content/uploads/2013/06/00693-450x337.jpg" alt="github"></a></p>
    <!--トップ画面へ遷移-->
    <%= jh.Home()%>
</body>
</html>
