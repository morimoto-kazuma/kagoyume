package jums;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Timestamp;
import java.util.ArrayList;

public class UserDataDTO {

    //user_t(会員情報管理テーブル)に対応する変数
    private int userID; //ユーザーID これはどちらにも対応している
    private String name;//ユーザー名
    private String password;//パスワード
    private String mail;//メールアドレス
    private String address;//住所
    private int total;//総購入金額
    private Timestamp newDate;//登録日時
    private int deleteFlg;//削除フラグ

    //buy_t(購入管理テーブル)に対応する変数
    private int buyID;
    private String itemCode;
    private int type;
    private Timestamp buyDate;
    private String buyname;
    private int buyprice;

    //cartDB用
    private String a_image;
    private String a_name;
    private String a_price;
    private String a_store;
    private String a_storeID;

    //カート内表示用
    private int c_cartID;
    private String c_image;
    private String c_name;
    private String c_price;
    private int c_userID;
    private String c_storeID;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Timestamp getNewDate() {
        return newDate;
    }

    public void setNewDate(Timestamp newDate) {
        this.newDate = newDate;
    }

    public int getDeleteFlg() {
        return deleteFlg;
    }

    public void setDeleteFlg(int deleteFlg) {
        this.deleteFlg = deleteFlg;
    }

    //---------------------------------------------------------------
    public int getBuyID() {
        return buyID;
    }

    public void setBuyID(int buyID) {
        this.buyID = buyID;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Timestamp getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Timestamp buyDate) {
        this.buyDate = buyDate;
    }

    public String getBuyName() {
        return buyname;
    }

    public void setBuyName(String buyname) {
        this.buyname = buyname;
    }

    public int getBuyPrice() {
        return buyprice;
    }

    public void setBuyPrice(int buyprice) {
        this.buyprice = buyprice;
    }

    //cart追加用-------------------------------------------------------------------------------
    public String getA_Image() {
        return a_image;
    }

    public void setA_Image(ArrayList<String> a_image) {
        this.a_image = a_image.get(0);
    }

    public String getA_Name() {
        return a_name;
    }

    public void setA_Name(ArrayList<String> a_name) {
        this.a_name = a_name.get(0);
    }

    public String getA_Price() {
        return a_price;
    }

    public void setA_Price(ArrayList<String> a_price) {
        this.a_price = a_price.get(0);
    }

    public String getA_Store() {
        return a_store;
    }

    public void setA_Store(String setA_store) {
        this.a_store = a_store;
    }

    public String getA_StoreID() {
        return a_storeID;
    }

    public void setA_StoreID(String a_storeID) {
        this.a_storeID = a_storeID;
    }

    //カート内表示用-------------------------------------------------------------------------------
    public int getC_CartID() {
        return c_cartID;
    }

    public void setC_CartID(int c_cartID) {
        this.c_cartID = c_cartID;
    }

    public String getC_Image() {
        return c_image;
    }

    public void setC_Image(String c_image) {
        this.c_image = c_image;
    }

    public String getC_Name() {
        return c_name;
    }

    public void setC_Name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_Price() {
        return c_price;
    }

    public void setC_Price(String c_price) {
        this.c_price = c_price;
    }

    public int getC_UserID() {
        return c_userID;
    }

    public void setC_UserID(int c_userID) {
        this.c_userID = c_userID;
    }

    public String getC_StoreID() {
        return c_storeID;
    }

    public void setC_StoreID(String c_storeID) {
        this.c_storeID = c_storeID;
    }

}
