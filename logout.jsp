<%-- 
    Document   : logout
    Created on : 2017/08/18, 12:38:16
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
    //セッションオールクリア
    hs.invalidate();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>ログアウトしました。</h1>
        <p><img src="http://redpointit.com/shinmahocopy/img/byebye.jpg" alt="github"></a></p>
    <!--トップ画面へ遷移-->
    <%= jh.Home()%>
</body>
</html>
