<%-- 
    Document   : item
    Created on : 2017/08/16, 11:44:20
    Author     : PCUser
--%>

<%@page import="jums.JumsHelper"%>
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
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

//セッションから商品詳細のテキスト情報を取り出し
    ArrayList<String> detail_name = (ArrayList) hs.getAttribute("detail_name");
    ArrayList<String> detail_image = (ArrayList) hs.getAttribute("detail_image");
    ArrayList<String> detail_description = (ArrayList) hs.getAttribute("detail_description");
    ArrayList<String> detail_review = (ArrayList) hs.getAttribute("detail_review");
    ArrayList<String> detail_price = (ArrayList) hs.getAttribute("detail_price");

//コード検索用コード取得
    String s_id = (String) hs.getAttribute("store_id");

//ログインページへ遷移する際に直前のページですアピール変数
    String item = "item";
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
        <h1>商品詳細ページ</h1>
        <p><img src="<% out.print(detail_image.get(0));%>" alt="github"></a></p>
    <p>商品名：<% out.print(detail_name.get(0));%></p>
    <p>商品詳細：<% out.print(detail_description.get(0));%></p>
    <p>評価：<% out.print(detail_review.get(0));%>点</p>
    <p>価格：<% out.print(detail_price.get(0));%>円</p>
    <%if (!logoutcheck) {%>
    <!--未ログインの場合、ログインページへ-->
    <form action="LoginCheck" method="GET">
        <input type="hidden" name="check"  value="<%= add%>">
        <input type="hidden" name="s_id"  value="<%= s_id%>">
        <input type="submit" name="btnSubmit" value="カートに追加する">
    </form>
    <%} else {%>
    <!--Addサーブレットへ遷移、商品ID検索用コードを非表示情報として送る--> 
    <form action="Add" method="GET">
        <input type="hidden" name="s_id"  value="<%= s_id%>">
        <input type="submit" name="btnSubmit" value="カートに追加する">
    </form>
    <%}%>
    <!--ログインページへ-->
    <a href="LoginCheck?check=<%= item%>"><%if (!logoutcheck) {%>ログイン<%} else {%>ログアウト<%}%></a>　<%= jh.CartPage()%><br>
</body>
</html>
