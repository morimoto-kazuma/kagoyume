package jums;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
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
public class Item extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /*
    商品検索ページから遷移しその商品コードで検索
    商品の詳細を取得するサーブレット
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //セッションスタート
        HttpSession session = request.getSession();

        //文字コード変換
        response.setContentType("text/html;charset=UTF-8");
        try {
            //文字コード変換
            request.setCharacterEncoding("UTF-8");

            //ログイン後セッションに格納している"Key"の情報を書き換えないよう条件分岐
            if (session.getAttribute("check") == null) {
                //ストアIDとコード情報を格納
                String store_id = request.getParameter("id");
                //ストアIDコードをセッションに格納
                session.setAttribute("store_id", store_id);
            }

            //セッションからストアIDコードの情報を取り出し
            String store_id = (String) session.getAttribute("store_id");

            //外部APIに接続
            URL url = new URL("https://shopping.yahooapis.jp/ShoppingWebService/V1/itemLookup?appid=dj00aiZpPWlwa0poQk5qbTFCSCZzPWNvbnN1bWVyc2VjcmV0Jng9NTc-&itemcode=" + URLEncoder.encode(store_id, "UTF-8") + "&responsegroup=medium");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.connect();

            //XMLを取得
            InputStream in = conn.getInputStream();
            DocumentBuilderFactory documentbuilderfactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentbuilder = documentbuilderfactory.newDocumentBuilder();
            Document document = documentbuilder.parse(in);
            Node node = document.getDocumentElement();
            in.close();

            //中身を取り出す
            Node Result_Node = node.getFirstChild();//⇒<Result>
            NodeList item_list = Result_Node.getChildNodes();//⇒<ItemCode> ～ <Hit>

            //商品コード検索の各ノードテキスト情報(詳細情報ページ)をセッションに格納
            session.setAttribute("detail_name", JumsHelper.getInstance().serch_result(item_list, "Name"));
            session.setAttribute("detail_image", JumsHelper.getInstance().serch_result(item_list, "Image", "Medium"));
            session.setAttribute("detail_description", JumsHelper.getInstance().serch_result(item_list, "Description"));
            session.setAttribute("detail_review", JumsHelper.getInstance().serch_result(item_list, "Review", "Rate"));
            session.setAttribute("detail_price", JumsHelper.getInstance().serch_result(item_list, "Price"));

            //商品詳細ページへ遷移
            request.getRequestDispatcher("/item.jsp").forward(request, response);

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
