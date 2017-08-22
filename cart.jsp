<%-- 
    Document   : cart
    Created on : 2017/08/17, 17:28:27
    Author     : PCUser
--%>

<%@page import="jums.UserDataDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="jums.JumsHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    //JumsHelperのインスタンス生成
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

    //セッションからcart_t(ユーザー別)の情報が入ったArrayList変数を取り出し
    ArrayList<UserDataDTO> hako = (ArrayList) hs.getAttribute("cartsearch");
    //セッションから商品コード検索用を取り出し
    String store_id = (String) hs.getAttribute("store_id");
    //合計金額用変数
    int total = 0;

    //直前ページの履歴削除
    hs.removeAttribute("check");

    //ログインページへ遷移する際に直前のページですアピール変数
    String cart = "cart";
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%if (loginname != null) {%>
        <a href="Mydata"><%= loginname%></a>さんのカートページです！<br>
        <%}%>
        <h1>カート内商品一覧</h1><br>
        <!--ログインページへ-->
　　    <a href="LoginCheck?check=<%= cart%>"><%if (!logoutcheck) {%>ログイン<%} else {%>ログアウト<%}%></a><br>

        <h2>━━━━━━━━━━━━━━━━━━━━━━━</h2>
        <!--拡張for文を利用しhakoからDTOの情報取り出す。
        そして各テキストづつ取り出し表示-->
        <% for (UserDataDTO udd : hako) {%>
        <a href="Item?id=<%= store_id%>"><img src="<%= udd.getC_Image()%>" alt="github"></a>
        <!--Itemサーブレットへ商品コードを持って遷移出来ていない(※後で修正要)-->
        <p>商品名：<a href="Item?id=<%= store_id%>"><%= udd.getC_Name()%></a></p>
        <p>価格：<%= udd.getC_Price()%>円</p>
        <%total += Integer.parseInt(udd.getC_Price());%>
        <!--削除しもう一度このページへ遷移-->
        <form action="Cart" method="GET">
            <input type="hidden" name="Delete"  value="<%=udd.getC_CartID()%>">
            <input type="submit" name="btnSubmit" value="カートから削除する">
        </form> 
        <%}%>
        <h2>━━━━━━━━━━━━━━━━━━━━━━━</h2>
        <h2>合計金額：<% out.print(total);%>円</h2>
        <br>
        <!--ログインのチェック-->
        <%if (!logoutcheck) {%>
        <!--ログインページへ-->
        <form action="LoginCheck" method="GET">
            <input type="hidden" name="check"  value="<%= cart%>">
            <input type="submit" name="btnSubmit" value="カートの商品を購入する">
        </form>
        <%} else {%>
        <!--購入ページへ-->
        <form action="Buyconfirm" method="GET">
            <input type="hidden" name="buy"  value="">
            <input type="submit" name="btnSubmit" value="カートの商品を購入する">
        </form>
        <%}%>
    </body>
    <%=jh.Home()%>
</html>
