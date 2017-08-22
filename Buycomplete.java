/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author PCUser
 */
public class Buycomplete extends HttpServlet {

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
            //セッションスタート
            HttpSession session = request.getSession();
            //入力フォームからの発送方法を変数へ代入
            String haisouCheck = request.getParameter("type");
            //購入合計金額取得
            Integer total = (Integer) session.getAttribute("total");
            //発送方法を選択していた場合の処理
            if (haisouCheck != null) {

                //購入用DBへカート内商品情報+発送方法+userID+購入時刻を格納+cart_tの購入金額の合計を更新
                UserDataDAO.getInstance().BuyInsert((Integer) session.getAttribute("userID"), Integer.parseInt(haisouCheck), total);
                
                Integer num = (Integer) session.getAttribute("userID");
                //ユーザー別カート情報クリア
                UserDataDAO.getInstance().CartAllDelete(num);
                //購入確認画面へ
                request.getRequestDispatcher("/buycomplete.jsp").forward(request, response);

                //発送方法選択されていなかった場合の処理
            } else {
                //入力ミス文をセッションへ格納
                session.setAttribute("err", "配送方法が未選択です" + "<br>" + "もう一度入力ください");
                //購入確認画面へ戻る
                request.getRequestDispatcher("/buyconfirm.jsp").forward(request, response);
            }
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
