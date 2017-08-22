<%-- 
    Document   : registrationconfirm
    Created on : 2017/08/21, 11:23:13
    Author     : PCUser
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="jums.UserDataBeans"%>
<%@page import="jums.JumsHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    //JumsHelperインスタンス生成
    JumsHelper jh = JumsHelper.getInstance();
    //セッションスタート
    HttpSession hs = request.getSession();
    //入力フォームの情報を取り出し
    UserDataBeans udb = (UserDataBeans) hs.getAttribute("udb");
    //入力漏れがないかチェック
    ArrayList<String> chkList = udb.chkproperties();

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% if (chkList.size() == 0) {%>
        <h1>登録確認</h1>
        <table>
            <tr>
            <tb>名前:</tb>
            <tb><%= udb.getUserName()%></tb>
        </tr>
        <br>
        <tr>
        <tb>パスワード:</tb>
        <tb><%= udb.getPassword()%></tb>
    </tr>
    <br>
    <tr>
    <tb>メールアドレス:</tb>
    <tb><%= udb.getMail()%></tb>
</tr>
<br>
<tr>
<tb>住所:</tb>
<tb><%= udb.getAddress()%></tb>
</tr>
<br>
</table>
上記の内容で登録します。よろしいですか？
<form action="Registrationcomplete" method="POST">
    <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
    <input type="submit" name="yes" value="はい">
</form>
<% } else {%>
<h1>入力が不完全です</h1>
<%=jh.chkinput(chkList)%>
<% }%>
<form action="Registration" method="POST">
    <input type="submit" name="no" value="登録画面に戻る">
    <input type="hidden" name="mode" value="reinput">
</form>
<%=jh.Home()%>
</body>
</html>
