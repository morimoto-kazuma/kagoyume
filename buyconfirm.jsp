<%-- 
    Document   : buyconfirm
    Created on : 2017/08/18, 18:59:04
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
    //ユーザーID取り出し
    Integer buy_userID = (Integer) hs.getAttribute("buy_userID");

    //セッションからcart_t(DB)の情報が入ったArrayList変数を取り出し
    ArrayList<UserDataDTO> hako = (ArrayList) hs.getAttribute("cart_buyconfirm");

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

    //合計金額用変数
    int total = 0;

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>購入確認ページ</h1><br>
        <!--ログアウト-->
　　    <a href="LoginCheck">ログアウト</a><br>

        <h2>━━━━━━━━━━━━━━━━━━━━━━━</h2>
        <!--拡張for文を利用しhakoからDTOの情報取り出す。
        そして各テキストづつ取り出し表示-->
        <% for (UserDataDTO udd : hako) {%>
        <p>商品名：<%= udd.getC_Name()%></p>
        <p>価格：<%= udd.getC_Price()%>円</p>
        <br>
        <%total += Integer.parseInt(udd.getC_Price());%>
        <%}%>
        <h2>━━━━━━━━━━━━━━━━━━━━━━━</h2>
        <h2>合計金額：<% out.print(total);%>円</h2>
        <br>
        ■配送方法選択
        <br>
        <br>
        <form action="Buycomplete" method="GET">
            <% for (int i = 1; i <= 8; i++) {%>
            <input type="radio" name="type" value="<%=i%>"><%=jh.exTypenum(i)%><br>
            <% } %>
            <br>
            <!--合計金額をセッションへ格納-->
            <%hs.setAttribute("total", total);%>

            <input type="hidden" name="Buy_UserID"  value="<%= buy_userID%>">
            <input type="submit" name="btnSubmit" value="購入">
        </form>
        <br>
        <form action="Cart" method="GET">
            <input type="submit" name="btnSubmit" value="カートに戻る">
        </form>
    </body>
</html>
