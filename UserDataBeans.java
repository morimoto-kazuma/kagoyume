package jums;

import java.io.Serializable;
import java.util.ArrayList;

public class UserDataBeans implements Serializable {

    private int userID;
    private String username;
    private String password;
    private String mail;
    private String address;

    public UserDataBeans() {
        this.userID = 0;
        this.username = "";
        this.password = "";
        this.mail = "";
        this.address = "";

    }

    //ユーザーID
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    //ユーザー名
    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {

        if (username.length() == 0) {
            this.username = "";
        } else {
            this.username = username;
        }
    }

    //パスワード
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        if (password.length() == 0) {
            this.password = "";
        } else {
            this.password = password;
        }
    }

    //メール
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {

        if (mail.length() == 0) {
            this.mail = "";
        } else {
            this.mail = mail;
        }
    }

    //住所
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {

        if (address.length() == 0) {
            this.address = "";
        } else {
            this.address = address;
        }
    }

    /*
    登録フォーム未入力チェック用 チェックリスト返却
     */
    public ArrayList<String> chkproperties() {
        ArrayList<String> chkList = new ArrayList<String>();
        if (this.username.equals("")) {
            chkList.add("username");
        }
        if (this.password.equals("")) {
            chkList.add("password");
        }
        if (this.mail.equals("")) {
            chkList.add("mail");
        }
        if (this.address.equals("")) {
            chkList.add("address");
        }

        return chkList;
    }
}
