<%-- 
    Document   : myupdateresult
    Created on : 2017/08/21, 18:14:06
    Author     : PCUser
--%>

<%@page import="jums.UserDataDTO"%>
<%@page import="jums.JumsHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%  
    //JumsHelperインスタンス生成
    JumsHelper jh = JumsHelper.getInstance();
    //セッションスタート
    HttpSession hs = request.getSession();

    //セッションからユーザー情報を取り出し
    UserDataDTO MyupdateData = (UserDataDTO) hs.getAttribute("MyupdateData");

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>登録情報更新しました</h1>
        <table>
            <tr>
            <tb>名前:</tb>
            <tb><%= MyupdateData.getName()%></tb>
        </tr>
        <br>
        <tr>
        <tb>パスワード:</tb>
        <tb><%= MyupdateData.getPassword()%></tb>
    </tr>
    <br>
    <tr>
    <tb>メールアドレス:</tb>
    <tb><%= MyupdateData.getMail()%></tb>
</tr>
<br>
<tr>
<tb>住所:</tb>
<tb><%= MyupdateData.getAddress()%></tb>
</tr>
<br>
</table>
<br>
<a href="Mydata">登録情報ページ</a><br>
<br>
</body>
</html>
