<%-- 
    Document   : registrationcomplete
    Created on : 2017/08/21, 11:43:55
    Author     : PCUser
--%>

<%@page import="jums.UserDataDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="jums.JumsHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    //JumsHelperのインスタンス生成
    JumsHelper jh = JumsHelper.getInstance();
    //セッションスタート
    HttpSession hs = request.getSession();

    //セッションからcart_t(DB)の情報を取り出し
    UserDataDTO udd = (UserDataDTO) hs.getAttribute("registrationData");

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>登録完了しました。</h1>
        <table>
            <tr>
            <tb>名前:</tb>
            <tb><%= udd.getName()%></tb>
        </tr>
        <br>
        <tr>
        <tb>パスワード:</tb>
        <tb><%= udd.getPassword()%></tb>
    </tr>
    <br>
    <tr>
    <tb>メールアドレス:</tb>
    <tb><%= udd.getMail()%></tb>
</tr>
<br>
<tr>
<tb>住所:</tb>
<tb><%= udd.getAddress()%></tb>
</tr>
<br>
</table>
<br>
<%= jh.Home()%>
<br>
<p><img src="http://www.nakaoroshi.or.jp/wp/wp-content/uploads/2014/11/40ad9c6a2b2b50081d70f6d4b03dc587.jpg" alt="github"></a></p>
</body>
</html>
