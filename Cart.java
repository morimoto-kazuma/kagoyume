/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author PCUser
 */
public class Cart extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try{
            //文字コード変換
            request.setCharacterEncoding("UTF-8");
            //削除するためのcartIDを取得 or null
            String c_cartID = request.getParameter("Delete");
            //セッションスタート
            HttpSession session = request.getSession();

            //DTOインスタンス生成
            UserDataDTO udd = new UserDataDTO();
            //DAOインスタンス生成
            UserDataDAO db = new UserDataDAO();
            //ユーザー名とパスワード取得
            String username = (String) session.getAttribute("user");
            String password = (String) session.getAttribute("pass");
            
            //ログインしていない場合カート追加できないのでここで阻止
            if (username == null || password == null) {
                //カートの中身ありませんページへ
                request.getRequestDispatcher("/cartnot.jsp").forward(request, response);
            }
            //ユーザーID取得
            udd = db.LoginNameSearch(username, password);

            int userID = udd.getUserID();

            //カート内商品ページで削除を押した場合のみcart_t(DB)から商品情報抹消
            if (c_cartID != null) {
                //商品をカートから削除
                UserDataDAO.getInstance().CartDelete(Integer.parseInt(c_cartID));
            }
            //ユーザー別カート内商品情報をcartsearchへ格納
            ArrayList cartsearch = UserDataDAO.getInstance().CartSearch(userID);
            
            //カートがない場合
            if (cartsearch.size() == 0) {
                //カートの中身ありませんページへ
                request.getRequestDispatcher("/cartnot.jsp").forward(request, response);
            }

            //ユーザー別カート内商品情報をセッションへ格納
            session.setAttribute("cartsearch", cartsearch);

            //カート内商品情報表示ページへ
            request.getRequestDispatcher("/cart.jsp").forward(request, response);

        } catch (Exception e) {
            System.out.print(e.getMessage());
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
