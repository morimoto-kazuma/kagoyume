<%-- 
    Document   : buycomplete
    Created on : 2017/08/19, 13:35:49
    Author     : PCUser
--%>
<%@page import="jums.JumsHelper"%>
<!--購入確認画面-->
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

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%if (loginname != null) {%>
        <!--登録情報ページへ-->
        <a href="Mydata"><%= loginname%></a>さん！ご購入頂きました！<br>
        <%}%>
        <h1>購入が完了いたしました。</h1>
        <!--ログアウト-->
        <p><img src="https://www.brush-stock.com/wp-content/uploads/vewoem000048.jpg" alt="github"></a></p>
    <!--トップへ-->
    <%= jh.Home()%>
    <br>
    <!--ログアウトページへ-->
    <a href="LoginCheck">ログアウト</a><br>
</body>
</html>
