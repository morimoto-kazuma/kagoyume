<%-- 
    Document   : add
    Created on : 2017/08/16, 17:09:07
    Author     : PCUser
--%>

<%@page import="jums.JumsHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    //JumsHelperインスタンス生成
    JumsHelper jh = JumsHelper.getInstance();

    //セッションスタート
    HttpSession hs = request.getSession();

    //未ログイン⇒null、ログイン済み⇒文字列"login"
    String loginname = (String) hs.getAttribute("logincheck");
    //ログイン/アウトページ分岐用
    boolean logoutcheck = false;

    //ログイン済みの場合
    if (loginname != null) {
        //ユーザー名を変数へ代入
        loginname = (String) hs.getAttribute("user");
        //ログイン/アウトページ分岐用変数trueへ変更
        logoutcheck = true;
    }
    //trueもしくはfalseをセッションへ格納
    hs.setAttribute("logoutcheck", logoutcheck);


    //直前ページの履歴削除
    hs.removeAttribute("check");

    //ログインページへ遷移する際に直前のページですアピール変数
    String add = "add";
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%if (loginname != null) {%>
        <a href="Mydata"><%= loginname%></a>さんログイン中！<br>
        <%}%>
        <h2>カートに追加しました。</h2>
        <br>
        <!--ログインページへ-->
        <a href="LoginCheck?check=<%= add%>"><%if (!logoutcheck) {%>ログイン<%} else {%>ログアウト<%}%></a><br>
        <!--CartPageサーブレットへ遷移-->
        <%= jh.CartPage()%>
        <br>
        <!--トップ画面へ遷移-->
        <%= jh.Home()%>
        <!--カートの画像-->
        <p><img src="http://img.freepik.com/freie-ikonen/e-commerce-in-den-warenkorb-legen_318-32326.jpg?size=338&ext=jpg" alt="github"></a></p>
</body>
</html>
