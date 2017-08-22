<%-- 
    Document   : saerch
    Created on : 2017/08/16, 0:39:17
    Author     : PCUser
--%>

<%@page import="jums.JumsHelper"%>
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

    //セッションから商品検索結果のテキストをArrayListから取り出し
    ArrayList<String> nodetexts_id = (ArrayList) hs.getAttribute("id");
    ArrayList<String> nodetexts_name = (ArrayList) hs.getAttribute("name");
    ArrayList<String> nodetexts_image = (ArrayList) hs.getAttribute("image");
    ArrayList<String> nodetexts_price = (ArrayList) hs.getAttribute("price");
    //検索キーワードをセッションから取り出し
    String key = (String) hs.getAttribute("key");

    //直前ページの履歴削除
    hs.removeAttribute("check");

    //ログインページへ遷移する際に直前のページですアピール変数
    String saerch = "saerch";
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
        <h2>検索キーワード「<%= key%>」</h2><br>
        <h2>検索結果数上位<%=nodetexts_name.size()%>件を表示</h2><br>
        <!--ログインページへ-->    
        <a href="LoginCheck?check=<%= saerch%>"><%if (!logoutcheck) {%>ログイン<%} else {%>ログアウト<%}%></a>　<%= jh.CartPage()%><br>
        <br>
        <!--それぞれをArrayListから取り出し表示-->
        <%for (int i = 0; i < nodetexts_name.size(); i++) {%>
        <h2><%=(i + 1)%>位</h2>
        <table>
            <tr>
                <td><a href="Item?id=<%= nodetexts_id.get(i)%>"><img src="<%out.print(nodetexts_image.get(i));%>" alt="github"></a></td>
                <!--Itemサーブレットへ遷移-->
                <td>商品名:<a href="Item?id=<%= nodetexts_id.get(i)%>"><%out.print(nodetexts_name.get(i));%></a><br>
                    価格:<%out.print(nodetexts_price.get(i));%>円</td>
            </tr>
        </table>
        <%}%>

    </body>
</html>
