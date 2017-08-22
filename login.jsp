<%-- 
    Document   : login
    Created on : 2017/08/09, 16:40:11
    Author     : PCUser
--%>
<!--「新規会員登録」というリンクも表示される。-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="jums.JumsHelper" %>
<!DOCTYPE html>
<%  
    //JumsHelperインスタンス生成
    JumsHelper jh = JumsHelper.getInstance();
    //セッションスタート
    HttpSession hs = request.getSession();

    String login = "login";
    String err;

    //未ログイン⇒null、ログイン済み⇒文字列"login"
    String loginname = (String) hs.getAttribute("logincheck");

    //ログイン済みの場合
    if (loginname != null) {
        //ユーザー名を変数へ代入
        loginname = (String) hs.getAttribute("user");
    }

    if (hs != null) {

//セッションからエラー文取り出し変数へ格納
        err = (String) hs.getAttribute("err");

//errで表示文の条件分岐
        if (err == null) {
            out.print("");
        } else {
            out.print(err + "<br>" + "<br>");

//errをセッションから削除
            hs.removeAttribute("err");
        }
    }

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%if (loginname != null) {%>
        <!--登録情報ページへ-->
        <a href="Mydata"><%= loginname%></a>さんログイン中！<br>
        <%}%>
        <!--直前のページへ-->
        <form action="Login" method="GET">
            <h1>ログインページ</h1><br>

            ユーザー名<input type="text" name="user" value=""><br>
            パスワード<input type="text" name="pass" value=""><br>
            <br>
            <input type="hidden" name="login"  value="<%= login%>">
            <input type="submit" name="btnSubmit" value="ログイン"><br>
            <br>
            <!--新規会員登録ページへ-->
            <a href="Registration">新規会員登録</a><br>
        </form>
        <br>
        <!--トップへ-->
        <%= jh.Home()%><br>
    </body>
</html>
