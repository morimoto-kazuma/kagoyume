<%-- 
    Document   : registration
    Created on : 2017/08/20, 17:51:12
    Author     : PCUser
--%>

<%@page import="jums.UserDataBeans"%>
<%@page import="jums.JumsHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    //セッションスタート
    HttpSession hs = request.getSession();

    UserDataBeans udb = null;

    //戻ってきた場合のみフォームの入力を表示
    boolean reinput = false;
    if (request.getParameter("mode") != null && request.getParameter("mode").equals("reinput")) {
        reinput = true;
        udb = (UserDataBeans) hs.getAttribute("udb");
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>新規会員登録ページ</h1>
        <br>
        <h2>各入力フォームにご入力お願いします</h2>
        <form action="Registrationconfirm" method="POST">
            <table>
                <tr>
                    <td>■ユーザー名</td>
                    <td><input type="text" name="username" value="<% if (reinput) {
                    out.print(udb.getUserName());
                }%>"></td>
                </tr>
                <tr>
                    <td>■パスワード</td>
                    <td><input type="text" name="password" value="<% if (reinput) {
                    out.print(udb.getPassword());
                }%>"></td>
                </tr>
                <tr>
                    <td>■メールアドレス</td>
                    <td><input type="text" name="mail" value="<% if (reinput) {
                    out.print(udb.getMail());
                }%>"></td>
                </tr>
                <tr>
                    <td>■住所</td>
                    <td><input type="text" name="address" value="<% if (reinput) {
                    out.print(udb.getAddress());
                }%>"></td>
                </tr>
            </table>
            <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
            <br>
            <input type="submit" name="btnSubmit" value="確認画面へ">
        </form>
    </body>
</html>
