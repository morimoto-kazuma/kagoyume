/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import base.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author PCUser
 */
public class UserDataDAO extends HttpServlet {

    /*
    インスタンスオブジェクトを返却させてコードの簡略化
     */
    public static UserDataDAO getInstance() {
        return new UserDataDAO();
    }

    /*
    ユーザーIDで購入履歴情報を返却
     */
    public ArrayList MyHistory(int userID) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBManager.getConnection();
            st = con.prepareStatement("select * from buy_t where userID = ?");

            st.setInt(1, userID);

            ResultSet rs = st.executeQuery();
            ArrayList<UserDataDTO> array = new ArrayList<UserDataDTO>();

            while (rs.next()) {
                //DTOのインスタンス生成
                UserDataDTO resultUd = new UserDataDTO();
                //DBからサーチした情報をArrayListへ格納
                resultUd.setBuyID(rs.getInt(1));
                resultUd.setUserID(rs.getInt(2));
                resultUd.setItemCode(rs.getString(3));
                resultUd.setType(rs.getInt(4));
                resultUd.setBuyDate(rs.getTimestamp(5));
                resultUd.setBuyName(rs.getString(6));
                resultUd.setBuyPrice(rs.getInt(7));
                array.add(resultUd);
            }

            System.out.println("search completed");

            return array;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    /*
    ユーザーIDでユーザー情報返却
     */
    public UserDataDTO UserUpdate(int userID) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBManager.getConnection();
            st = con.prepareStatement("select * from user_t where userID = ?");

            st.setInt(1, userID);

            ResultSet rs = st.executeQuery();

            rs.next();
            //DTOのインスタンス生成
            UserDataDTO resultUd = new UserDataDTO();
            //DBからサーチした情報をArrayListへ格納
            resultUd.setUserID(rs.getInt(1));
            resultUd.setName(rs.getString(2));
            resultUd.setPassword(rs.getString(3));
            resultUd.setMail(rs.getString(4));
            resultUd.setAddress(rs.getString(5));
            resultUd.setTotal(rs.getInt(6));
            resultUd.setNewDate(rs.getTimestamp(7));
            resultUd.setDeleteFlg(rs.getInt(8));

            System.out.println("search completed");

            return resultUd;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    /*
    ユーザー情報更新
     */
    public void Myupdateresult(Integer userID, UserDataBeans udb) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBManager.getConnection();
            st = con.prepareStatement("update user_t set name = ?,password = ?,mail = ?,address = ?,newDate = ? where userID = ?");

            st.setString(1, udb.getUserName());
            st.setString(2, udb.getPassword());
            st.setString(3, udb.getMail());
            st.setString(4, udb.getAddress());
            st.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            st.setInt(6, userID);

            st.executeUpdate();

            System.out.println("updete completed");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    /*
    deleteFlgを0⇒1へ変更
     */
    public void MyDelete(int userID) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBManager.getConnection();
            st = con.prepareStatement("update user_t set deleteFlg = ? where userID = ?");

            st.setInt(1, 1);
            st.setInt(2, userID);

            st.executeUpdate();

            System.out.println("updete completed");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    /*
    購入商品の管理DBとユーザーを紐づけるためユーザー名とパスワードで検索しユーザーID返却
     */
    public UserDataDTO LoginNameSearch(String name, String pass) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBManager.getConnection();
            st = con.prepareStatement("select * from user_t where name = ? AND password = ?");

            st.setString(1, name);
            st.setString(2, pass);

            ResultSet rs = st.executeQuery();

            rs.next();
            //DTOのインスタンス生成
            UserDataDTO resultUd = new UserDataDTO();
            //DBからサーチした情報をArrayListへ格納
            resultUd.setUserID(rs.getInt(1));
            resultUd.setName(rs.getString(2));
            resultUd.setPassword(rs.getString(3));
            resultUd.setMail(rs.getString(4));
            resultUd.setAddress(rs.getString(5));
            resultUd.setTotal(rs.getInt(6));
            resultUd.setNewDate(rs.getTimestamp(7));
            resultUd.setDeleteFlg(rs.getInt(8));

            System.out.println("search completed");

            return resultUd;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    /*
    会員登録更新を登録しユーザー情報を返却
     */
    public UserDataDTO RegistrationInsertSserch(UserDataBeans udb) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBManager.getConnection();
            st = con.prepareStatement("INSERT INTO user_t(name,password,mail,address,total,newDate) VALUES(?,?,?,?,?,?)");
            st.setString(1, udb.getUserName());
            st.setString(2, udb.getPassword());
            st.setString(3, udb.getMail());
            st.setString(4, udb.getAddress());
            st.setInt(5, 0);
            st.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            st.executeUpdate();

            System.out.println("insert completed");

            st = con.prepareStatement("SELECT * FROM user_t WHERE name like ? AND password like ?");

            st.setString(1, udb.getUserName());
            st.setString(2, udb.getPassword());

            ResultSet rs = st.executeQuery();

            rs.next();
            //DTOのインスタンス生成
            UserDataDTO resultUd = new UserDataDTO();
            //DBからサーチした情報をArrayListへ格納
            resultUd.setUserID(rs.getInt(1));
            resultUd.setName(rs.getString(2));
            resultUd.setPassword(rs.getString(3));
            resultUd.setMail(rs.getString(4));
            resultUd.setAddress(rs.getString(5));
            resultUd.setTotal(rs.getInt(6));
            resultUd.setNewDate(rs.getTimestamp(7));
            resultUd.setDeleteFlg(rs.getInt(8));

            return resultUd;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }

    }

    /*
   カートへ商品追加するためのDB処理
     */
    public void CartInsert(UserDataDTO ud, int userID) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBManager.getConnection();
            st = con.prepareStatement("INSERT INTO cart_t(image,name,price,searchID,storeID) VALUES(?,?,?,?,?)");

            st.setString(1, ud.getA_Image());
            st.setString(2, ud.getA_Name());
            st.setString(3, ud.getA_Price());
            st.setInt(4, userID);
            st.setString(5, ud.getA_StoreID());

            st.executeUpdate();
            System.out.println("insert completed");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    /*
    cart_t内の情報をユーザー別で取り出す処理
     */
    public ArrayList CartSearch(int userID) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBManager.getConnection();
            st = con.prepareStatement("SELECT * FROM cart_t WHERE searchID like ?");

            st.setInt(1, userID);

            ResultSet rs = st.executeQuery();

            //UserDataDTOへそれぞれの情報を格納
            ArrayList<UserDataDTO> hako = new ArrayList<UserDataDTO>();

            while (rs.next()) {
                //DTOのインスタンス生成
                UserDataDTO resultUd = new UserDataDTO();
                //DBからサーチした情報をArrayListへ格納
                resultUd.setC_CartID(rs.getInt(1));
                resultUd.setC_Image(rs.getString(2));
                resultUd.setC_Name(rs.getString(3));
                resultUd.setC_Price(rs.getString(4));
                resultUd.setC_UserID(rs.getInt(5));
                resultUd.setC_StoreID(rs.getString(6));
                hako.add(resultUd);
            }
            System.out.println("search completed");

            return hako;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    /*
    カート内商品をcartIDで個別に選択し削除
     */
    public void CartDelete(int c_cartID) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBManager.getConnection();
            st = con.prepareStatement("DELETE FROM cart_t WHERE cartID = ?");

            st.setInt(1, c_cartID);

            st.executeUpdate();

            System.out.println("delete completed");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    /*
    ユーザー別カート内情報を全て削除
     */
    public void CartAllDelete(Integer c_cartID) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBManager.getConnection();
            st = con.prepareStatement("DELETE FROM cart_t WHERE searchID = ?");

            st.setInt(1, c_cartID);

            st.executeUpdate();

            System.out.println("delete completed");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    /*
    Buycompleteからカート内商品を購入する際に発動するメソッド
    引数1:カート内情報取り出し用ID 2:ログイン中のユーザーID 3:発送方法番号
    カート内の商品DBの情報取得⇒購入用DBへ必要な情報を格納
     */
    public void BuyInsert(Integer buy_userID, int type, Integer total) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try {

            con = DBManager.getConnection();
            //カート情報取り出し用の値でカートから情報を取り出し
            st = con.prepareStatement("SELECT * FROM cart_t WHERE searchID like ?");

            st.setInt(1, buy_userID);
            ResultSet rs = st.executeQuery();

            ArrayList<UserDataDTO> array = new ArrayList<UserDataDTO>();

            while (rs.next()) {
                //DTOのインスタンス生成
                UserDataDTO resultUd = new UserDataDTO();
                //DBからサーチした情報をArrayListへ格納
                resultUd.setC_CartID(rs.getInt(1));
                resultUd.setC_Image(rs.getString(2));
                resultUd.setC_Name(rs.getString(3));
                resultUd.setC_Price(rs.getString(4));
                resultUd.setC_UserID(rs.getInt(5));
                resultUd.setC_StoreID(rs.getString(6));
                array.add(resultUd);
            }
            //カート内情報が入っているArrayListから必要な情報+引数2.3+現在時刻をbuy_t(DB)へ格納
            for (UserDataDTO ud : array) {
                st = con.prepareStatement("INSERT INTO buy_t(userID,itemCode,type,buyDate,name,price) VALUES(?,?,?,?,?,?)");

                st.setInt(1, buy_userID);
                st.setString(2, ud.getC_StoreID());
                st.setInt(3, type);
                st.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
                st.setString(5, ud.getC_Name());
                st.setString(6, ud.getC_Price());
                //商品ごとの価格を変数へ追加していく
                st.executeUpdate();
            }

            //合計金額を更新 
            st = con.prepareStatement("UPDATE user_t SET total = total + ? WHERE userID = ?");
            //update cart_t set price + 1500 where cartID = 16;
            st.setInt(1, total);
            st.setInt(2, buy_userID);

            st.executeUpdate();

            System.out.println("3 completed");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

}
