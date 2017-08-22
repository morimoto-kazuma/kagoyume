<%-- 
    Document   : myhistory
    Created on : 2017/08/21, 16:54:21
    Author     : PCUser
--%>

<%@page import="jums.UserDataDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="jums.JumsHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    //JumsHelperインスタンス生成
    JumsHelper jh = JumsHelper.getInstance();
    //セッションスタート
    HttpSession hs = request.getSession();
    //購入履歴情報取得
    ArrayList<UserDataDTO> Myhis = (ArrayList) hs.getAttribute("Myhis");

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>購入履歴</h1>
        <br>
        <% for (UserDataDTO udd : Myhis) {%>
        ━━━━━━━━━━━━━━━━━━━━━━━━━━━━<br>
        <br>
        <table>
            <tr>
            <tb>商品名:</tb>
            <tb><%= udd.getBuyName()%></tb>
        </tr>
        <br>
        <tr>
        <tb>価格:</tb>
        <tb><%= udd.getBuyPrice()%></tb>
    </tr>
    <br>
    <tr>
    <tb>発送方法:</tb>
    <tb><%= jh.exTypenum(udd.getType())%></tb>
</tr>
<br>
<tr>
<tb>購入日時:</tb>
<tb><%= jh.SimpleDate(udd.getBuyDate())%></tb>
</tr>
</table>
<%}%>
━━━━━━━━━━━━━━━━━━━━━━━━━━━━<br>
</body>
<br>
<a href="Mydata">会員情報ページへ</a><br>
<br>
<%= jh.Home()%>
</html>
