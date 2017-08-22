package jums;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import base.DBManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author PCUser
 */
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//・どのページからも遷移できる。ログインしているかいないかで処理が分岐する
//・ログインしていない状態(各ページの「ログイン」というリンクから)で遷移してきた場合は、
//  ユーザー名とパスワードを入力するフォームが表示される。
//  また、「新規会員登録」というリンクも表示される。
//・ログインに成功すると、その情報をログイン状態を管理できるセッションに書き込み、
//  そのまま直前まで閲覧していたページに遷移する
//・ログインしている状態で(各ページの「ログアウト」というリンクから)遷移してきた場合は、
//  ログアウト処理を行う(セッ　ションの破棄、クッキーに保存されたセッションIDを破棄)その後topへ
//・ユーザーデータの削除フラグが1の場合は削除されたユーザーとして処理すること
    //いろんなとこからここのサーブレットに入りいろんな出口がある
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //文字コード変換
        request.setCharacterEncoding("UTF-8");
        //セッションスタート
        HttpSession session = request.getSession();
        try {

            //ユーザー名とパスワードのチェック-------------------------------------------------
            //ログインページからの入力フォームのテキストを変数へ代入
            String user = request.getParameter("user");
            String pass = request.getParameter("pass");
            //UserDataDTOインスタンス生成
            UserDataDTO udd = new UserDataDTO();
            //userID取得
            udd = UserDataDAO.getInstance().LoginNameSearch(user, pass);

            int userID = udd.getUserID();

            //ログインページからの入力フォームのテキストをセッションへ格納
            session.setAttribute("userID", userID);
            session.setAttribute("user", user);
            session.setAttribute("pass", pass);

            Connection con = null;
            PreparedStatement st = null;
            ResultSet rs = null;
            
            //登録情報があるかを確認
            con = DBManager.getConnection();
            st = con.prepareStatement("select * from user_t where name = ? AND password = ? AND deleteFlg = ?");

            st.setString(1, user);
            st.setString(2, pass);
            st.setInt(3, 0);

            rs = st.executeQuery();

            //nextメソッドで、登録情報の有無を確認
            if (rs.next()) {
                //直前ページをチェックするための変数
                String check = (String) session.getAttribute("check");
                String logincheck = request.getParameter("login");
                
                session.setAttribute("logincheck", logincheck);
                
                //各ページへ振り分け
                switch (check) {
                    case "top":
                        request.getRequestDispatcher("/top.jsp").forward(request, response);
                        break;
                    case "saerch":
                        request.getRequestDispatcher("/Saerch").forward(request, response);
                        break;
                    case "item":
                        request.getRequestDispatcher("/Item").forward(request, response);
                        break;
                    case "add":
                        request.getRequestDispatcher("/Add").forward(request, response);
                        break;
                    case "cart":
                        request.getRequestDispatcher("/Cart").forward(request, response);
                        break;
                }

                //登録情報がなかった場合   
            } else {
                //入力ミス文をセッションへ格納
                session.setAttribute("err", "登録情報がありません" + "<br>" + "もう一度入力ください");
                //InventoryControl_LoginPage.jspへ戻る
                RequestDispatcher dispatch = request.getRequestDispatcher("/login.jsp");
                dispatch.forward(request, response);
            }

        } catch (SQLException e_sql) {
            System.out.print("エラーです。お疲れ様です。" + e_sql.toString());
        } catch (Exception e) {
            System.out.print("エラーですね。お疲れ様です。" + e.toString());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
