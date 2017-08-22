<%-- 
    Document   : myupdate
    Created on : 2017/08/21, 17:54:33
    Author     : PCUser
--%>

<%@page import="jums.UserDataDTO"%>
<%@page import="jums.JumsHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    //セッションスタート
    HttpSession hs = request.getSession();

    UserDataDTO userupdate = null;
    //ユーザー情報をセッションから取り出し
    userupdate = (UserDataDTO) hs.getAttribute("userupdate");

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
        <form action="Myupdateresult" method="POST">
            <table>
                <tr>
                    <td>■ユーザー名</td>
                    <td><input type="text" name="username" value="<% out.print(userupdate.getName());%>"required></td>
                </tr>
                <tr>
                    <td>■パスワード</td>
                    <td><input type="text" name="password" value="<% out.print(userupdate.getPassword());%>"required></td>
                </tr>
                <tr>
                    <td>■メールアドレス</td>
                    <td><input type="text" name="mail" value="<% out.print(userupdate.getMail());%>"required></td>
                </tr>
                <tr>
                    <td>■住所</td>
                    <td><input type="text" name="address" value="<% out.print(userupdate.getAddress());%>"required></td>
                </tr>
            </table>
            <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
            <br>
            <input type="submit" name="btnSubmit" value="確認画面へ">
        </form>
    </body>
</html>
