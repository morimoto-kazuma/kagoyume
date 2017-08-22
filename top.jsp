<%-- 
    Document   : top
    Created on : 2017/08/09, 11:51:21
    Author     : PCUser
--%>
<%@page import="jums.JumsHelper"%>
<%--
ゆめかごトップページ
・ログインページ
・キーワード検索フォーム
--%>
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
    String top = "top";
    
    String err;

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
<html lang="ja">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ゆめかごトップ</title>
    </head>
    <body>
        <%if (loginname != null) {%>
        ようこそ！<a href="Mydata"><%= loginname%></a>さん<br>
        <%}%>
        <br>
        <h1>ゆめかごトップ</h1><br>
        <h3>『金銭取引が絶対に発生しない』『いくらでも、
            どんなものでも購入できる(気分になれる)』『ECサイト』です。</h3><br>
        <!--ログインページへ-->    
        <a href="LoginCheck?check=<%= top%>"><%if (!logoutcheck) {%>ログイン<%} else {%>ログアウト<%}%></a>　<%= jh.CartPage()%><br>
        <br>
        <form action="Saerch" method="GET">
            キーワード検索
            <input type="text" name="Key" value="">
            <input type="submit" name="btnSubmit" value="検索">
        </form>
        <p><img src="https://www.photolibrary.jp/mhd2/img322/450-20140101222840204219.jpg" alt="github"></a></p>
</body>
</html>
