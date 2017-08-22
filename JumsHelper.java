package jums;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.w3c.dom.NodeList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author PCUser
 */
public class JumsHelper {

    private final String LogoutURL = "top.jsp";
    private final String cartpageURL = "Cart";
    private final String LoginLogoutURL = "LoginCheck";

    public static JumsHelper getInstance() {
        return new JumsHelper();
    }

    /*
良く使うページのリンク返却
     */
    public String Home() {
        return "<a href=\"" + LogoutURL + "\">トップページへ</a>";
    }

    public String CartPage() {
        return "<a href=\"" + cartpageURL + "\">カート一覧</a>";
    }

    public String LoginLogout() {
        return "<a href=\"" + LoginLogoutURL + "\"LoginCheck?check=<%= top%>>ログインページへ</a>";

    }

    /*
    XML必要なノードテキストを取り出し返却するメソッド
     */
    public ArrayList serch_result(NodeList item_list, String tag_1, String tag_2) {
        ArrayList<String> nodetexts = new ArrayList<String>();

        for (int i = 0; i < item_list.getLength(); i++) {

            NodeList item = item_list.item(i).getChildNodes();//⇒item＝<Name> ～ <IsAdult>

            //<Name> ～ <IsAdult>の間を回す
            for (int j = 0; j < item.getLength(); j++) {
                //<Name> ～ <IsAdult>の(j)番目のタグ名とtag_1(引数2)をチェック
                if (item.item(j).getNodeName().equals(tag_1)) {
                    //tag_2が"Check"ではない場合
                    if (!tag_2.equals("Check")) {
                        //item_2 ＝ <Name> ～ <IsAdult>の(j)番目の子供ノード(例:<Id> ～ <Medium>)
                        NodeList item_2 = item.item(j).getChildNodes();
                        //<Id> ～ <Medium>を回す
                        for (int k = 0; k < item_2.getLength(); k++) {
                            //<Id> ～ <Medium>の(K)番目のタグ名とtag_2(引数3)チェック
                            if (item_2.item(k).getNodeName().equals(tag_2)) {
                                //一致したタグ名とテキストをArrayListへ格納
                                nodetexts.add(item_2.item(k).getTextContent());

                            }
                        }
                    } else {
                        //一致したタグ名とテキストをArrayListへ格納
                        nodetexts.add(item.item(j).getTextContent());
                    }
                }
            }
        }
        return nodetexts;
    }

    //serch_resultメソッドのデフォルト値設定
    public ArrayList serch_result(NodeList item_list, String tag_1) {
        return serch_result(item_list, tag_1, "Check");
    }

    /*
    発送方法は数字で取り扱っているので画面に表示するときは日本語に変換
     */
    public String exTypenum(int i) {
        switch (i) {
            case 1:
                return "定形外郵便";
            case 2:
                return "ゆうメール";
            case 3:
                return "クリックポスト";
            case 4:
                return "スマートレター";
            case 5:
                return "レターパック";
            case 6:
                return "はこBOON";
            case 7:
                return "ゆうパック";
            case 8:
                return "森本宅急便";
        }
        return "";
    }

    /**
     * 入力されたデータのうち未入力項目がある場合、チェックリストにしたがいどの項目が 未入力なのかのhtml文を返却する
     *
     * @param chkList　UserDataBeansで生成されるリスト。未入力要素の名前が格納されている
     * @return 未入力の項目に対応する文字列
     */
    public String chkinput(ArrayList<String> chkList) {
        String output = "";
        for (String val : chkList) {
            if (val.equals("username")) {
                output += "ユーザー名";
            }
            if (val.equals("password")) {
                output += "パスワード";
            }
            if (val.equals("mail")) {
                output += "メールアドレス";
            }
            if (val.equals("address")) {
                output += "住所";
            }

            output += "が未記入です<br>";
        }
        return output;
    }

    /*
    タイムスタンプをフォーマットする用のメソッド
     */
    public String SimpleDate(Timestamp newDate) {
        String newdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(newDate);
        return newdate;
    }

}
