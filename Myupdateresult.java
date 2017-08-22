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
public class Myupdateresult extends HttpServlet {

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
        //セッションスタート
        HttpSession session = request.getSession();

        try {
            request.setCharacterEncoding("UTF-8");//リクエストパラメータの文字コードをUTF-8に変更

            //アクセスルートチェック
            String accesschk = request.getParameter("ac");
            if (accesschk.equals(null) || (Integer) session.getAttribute("ac") != Integer.parseInt(accesschk)) {
                throw new Exception("不正なアクセスです");
            }

            //フォームからの入力を取得して、JavaBeansに格納
            UserDataBeans udb = new UserDataBeans();

            udb.setUserName(request.getParameter("username"));
            udb.setPassword(request.getParameter("password"));
            udb.setMail(request.getParameter("mail"));
            udb.setAddress(request.getParameter("address"));

            UserDataDTO MyupdateData = new UserDataDTO();
            //ユーザーID取得
            Integer userID = (Integer) session.getAttribute("userID");
            //会員情報更新
            UserDataDAO.getInstance().Myupdateresult(userID, udb);
            //更新後のユーザー情報取得
            MyupdateData = UserDataDAO.getInstance().UserUpdate(userID);

            //更新後のユーザー情報を上書き
            session.setAttribute("MyupdateData", MyupdateData);

            //セッションの「ようこそ--さん！」を上書き
            session.setAttribute("logincheck", MyupdateData.getName());
            session.setAttribute("user", MyupdateData.getName());

            System.out.println("Session updated!!");
            //登録確認画面へ
            request.getRequestDispatcher("/myupdateresult.jsp").forward(request, response);
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
