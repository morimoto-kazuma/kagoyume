<%-- 
    Document   : mydata
    Created on : 2017/08/21, 15:43:43
    Author     : PCUser
--%>

<%@page import="jums.JumsHelper"%>
<%@page import="jums.UserDataDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    //JumsHelperインスタンス生成
    JumsHelper jh = JumsHelper.getInstance();
    //セッションスタート
    HttpSession hs = request.getSession();
    //セッションからユーザー情報取り出し
    UserDataDTO mydata = (UserDataDTO) hs.getAttribute("mydata");

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>会員情報</h1>
        <table>
            <tr>
            <tb>名前:</tb>
            <tb><%= mydata.getName()%></tb>
        </tr>
        <br>
        <tr>
        <tb>パスワード:</tb>
        <tb><%= mydata.getPassword()%></tb>
    </tr>
    <br>
    <tr>
    <tb>メールアドレス:</tb>
    <tb><%= mydata.getMail()%></tb>
</tr>
<br>
<tr>
<tb>住所:</tb>
<tb><%= mydata.getAddress()%></tb>
</tr>
<br>
<tr>
<tb>総購入金額:</tb>
<tb><%= mydata.getTotal()%></tb>
</tr>
<br>
<tr>
<tb>登録日時:</tb>
<tb><%= jh.SimpleDate(mydata.getNewDate())%></tb>
</tr>
<br>
</table>
<a href="Myhistory?mh=<%= mydata.getUserID()%>">購入履歴</a><br>
<br>
<a href="Myupdate?mu=<%= mydata.getUserID()%>">登録変更</a><br>
<br>
<a href="Mydelete?md=<%= mydata.getUserID()%>">登録削除</a><br>
<br>
<!--トップへ-->
<%=jh.Home()%><br>
<br>

</body>
</html>
