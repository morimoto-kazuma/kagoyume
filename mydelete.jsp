<%-- 
    Document   : mydelete
    Created on : 2017/08/21, 19:23:28
    Author     : PCUser
--%>

<%@page import="jums.UserDataDTO"%>
<%@page import="jums.JumsHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    //セッションスタート
    HttpSession hs = request.getSession();

    UserDataDTO userdelete = null;
    //ユーザー情報取得
    userdelete = (UserDataDTO) hs.getAttribute("userdelete");

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>会員削除確認</h1>
        <br>
        <form action="Mydeleteresult" method="POST">
            <table>
                <tr>
                    <td>■ユーザー名</td>
                    <td><% out.print(userdelete.getName());%></td>
                </tr>
                <tr>
                    <td>■パスワード</td>
                    <td><% out.print(userdelete.getPassword());%></td>
                </tr>
                <tr>
                    <td>■メールアドレス</td>
                    <td><% out.print(userdelete.getMail());%></td>
                </tr>
                <tr>
                    <td>■住所</td>
                    <td><% out.print(userdelete.getAddress());%></td>
                </tr>
            </table>
            <h2>この情報を本当に削除しますか</h2>
            <input type="hidden" name="md"  value="<%= userdelete.getUserID()%>">
            <br>
            <input type="submit" name="btnSubmit" value="確認画面へ">
        </form>
        <a href="Mydata">登録情報ページへ戻る</a><br>
    </body>
</html>
