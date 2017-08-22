/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author PCUser
 */
public class Add extends HttpServlet {

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
            //文字コード変換
            request.setCharacterEncoding("UTF-8");

            //ログイン後セッションに格納している"Key"の情報を書き換えないよう条件分岐
            if (session.getAttribute("check") == null) {
                //ストアIDとコード情報を取得
                String store_id = request.getParameter("s_id");
                session.setAttribute("store_id", store_id);
                //ストアIDコードをセッションに格納
            }
            //セッションからストアIDコードの情報を取り出し
            String store_id = (String) session.getAttribute("store_id");
            //DTOインスタンス生成
            UserDataDTO udd = new UserDataDTO();
            //DAOインスタンス生成
            UserDataDAO db = new UserDataDAO();
            
            //セッションからユーザー名とパスワード取得
            String username = (String) session.getAttribute("user");
            String password = (String) session.getAttribute("pass");
            //上記情報でユーザーIDを戻り値として取得
            udd = db.LoginNameSearch(username, password);
            
            int userID = udd.getUserID();
            //ユーザーIDをセッションへ保存
            session.setAttribute("userID", userID);

            //外部APIに接続------------------------------------------------------
            URL url = new URL("https://shopping.yahooapis.jp/ShoppingWebService/V1/itemLookup?appid=dj00aiZpPWlwa0poQk5qbTFCSCZzPWNvbnN1bWVyc2VjcmV0Jng9NTc-&itemcode=" + URLEncoder.encode(store_id, "UTF-8") + "&responsegroup=medium");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.connect();

            //XMLを取得---------------------------------------------------------
            InputStream in = conn.getInputStream();
            DocumentBuilderFactory documentbuilderfactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentbuilder = documentbuilderfactory.newDocumentBuilder();
            Document document = documentbuilder.parse(in);
            Node node = document.getDocumentElement();
            in.close();

            //中身を取り出す-----------------------------------------------------
            Node Result_Node = node.getFirstChild();//⇒<Result>
            NodeList item_list = Result_Node.getChildNodes();//⇒<ItemCode> ～ <Hit>

            //JumsHelperのserch_resultメソッドからの戻り値で返却されたそれぞれのArrayList変数へ格納
            ArrayList<String> hako_1 = JumsHelper.getInstance().serch_result(item_list, "Image", "Medium");
            ArrayList<String> hako_2 = JumsHelper.getInstance().serch_result(item_list, "Name");
            ArrayList<String> hako_3 = JumsHelper.getInstance().serch_result(item_list, "Description");
            ArrayList<String> hako_4 = JumsHelper.getInstance().serch_result(item_list, "Review", "Rate");
            ArrayList<String> hako_5 = JumsHelper.getInstance().serch_result(item_list, "Price");

            //カートに追加するための商品テキスト情報をセッションに格納
            session.setAttribute("cart_image", hako_1);
            session.setAttribute("cart_name", hako_2);
            session.setAttribute("cart_description", hako_3);
            session.setAttribute("cart_review", hako_4);
            session.setAttribute("cart_price", hako_5);
            session.setAttribute("store_id", store_id);

            //カート内商品表示で必要なテキスト情報をDTOへ格納
            udd.setA_Image(hako_1);
            udd.setA_Name(hako_2);
            udd.setA_Price(hako_5);
            udd.setA_StoreID(store_id);

            //imege,name,priceそれぞれのテキストがDAOでcart_t(DB)に格納される
            db.CartInsert(udd, userID);

            //カートに追加しましたページへ遷移
            request.getRequestDispatcher("/add.jsp").forward(request, response);

        } catch (Exception ex) {
            System.out.print(ex.getMessage());
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
