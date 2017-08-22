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
public class Buyconfirm extends HttpServlet {

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
        try {
            //文字コード変換
            request.setCharacterEncoding("UTF-8");
            //セッションスタート
            HttpSession session = request.getSession();
            //現在ログイン中のユーザーの名前とパスワードを取得
            String user_name = (String) session.getAttribute("user");
            String password = (String) session.getAttribute("pass");
            
            UserDataDTO udd = new UserDataDTO();
            
            //ユーザーIDを取得
            udd = UserDataDAO.getInstance().LoginNameSearch(user_name, password);
            
            int buy_userID = udd.getUserID();
            //ユーザー別カート内商品情報をcart_buyconfirmへ格納
            ArrayList cart_buyconfirm = UserDataDAO.getInstance().CartSearch(buy_userID);
            //ユーザー別カート内商品情報をセッションへ格納
            session.setAttribute("cart_buyconfirm", cart_buyconfirm);
            //ユーザーIDセッションへ格納
            session.setAttribute("buy_userID", buy_userID);
            //購入確認画面へ
            request.getRequestDispatcher("/buyconfirm.jsp").forward(request, response);

        } catch (Exception e) {
            System.out.print(e.getMessage());
            System.out.print(e);
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
