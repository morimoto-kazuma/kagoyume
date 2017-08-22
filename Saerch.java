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
import javax.servlet.RequestDispatcher;
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
 * @author morimoto-k
 */
public class Saerch extends HttpServlet {

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
        //直リンク防止用ランダムな値をセッションに格納
        session.setAttribute("ac", (int) (Math.random() * 1000));

        try {
            //JumsHelperインスタンス生成
            JumsHelper jh = new JumsHelper();
            //入力フォームの文字コード変換
            request.setCharacterEncoding("UTF-8");

            //ログイン後セッションに格納している"Key"の情報を書き換えないよう条件分岐
            if (session.getAttribute("check") == null) {
                //入力フォームの情報を格納
                String str = request.getParameter("Key");
                //入力フォームの情報をセッションに格納
                session.setAttribute("key", str);
            }

            //セッションから入力フォームの情報を取り出し
            String key = (String) session.getAttribute("key");
            //入力フォームが未入力の場合の処理
            if (key.equals("")) {
                //入力ミス文をセッションへ格納
                session.setAttribute("err", "検索キーワードが入力されていません" + "<br>" + "もう一度入力ください");
                //InventoryControl_LoginPage.jspへ戻る
                RequestDispatcher dispatch = request.getRequestDispatcher("/top.jsp");
                dispatch.forward(request, response);

            }

            //外部APIに接続----------------------------------------------------------
            //urlへURLを代入
            URL url = new URL("https://shopping.yahooapis.jp/ShoppingWebService/V1/itemSearch?appid=dj00aiZpPWlwa0poQk5qbTFCSCZzPWNvbnN1bWVyc2VjcmV0Jng9NTc-&query=" + URLEncoder.encode(key, "UTF-8") + "&hits=10");
            //接続
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //URL 接続を出力
            conn.setDoOutput(true);
            //通信リンクを確立
            conn.connect();

            //XMLを取得-------------------------------------------------------------
            //connの入力を受け取る入力ストリームを返却されて inへ代入
            InputStream in = conn.getInputStream();

            //アプリケーションで XML ドキュメントから DOM オブジェクトツリーを生成するパーサーを取得できるファクトリ API を定義。 
            //newInstance()でDocumentBuilderFactoryクラスをインスタンス化
            DocumentBuilderFactory documentbuilderfactory = DocumentBuilderFactory.newInstance();

            //現在設定されているパラメータを使用して DocumentBuilder の新しいインスタンスを作成します。
            DocumentBuilder documentbuilder = documentbuilderfactory.newDocumentBuilder();

            //取得したいファイル（XMLとか）を指定して、Documentオブジェクトを取得
            //parseメソッド＝指定された内容をXMLドキュメントとして構文解析して、Documentオブジェクトを返すメソッド
            //パラメータに指定されたInputStreamからXML文書を読み取り、Documentを作成する。
            Document document = documentbuilder.parse(in);

            //ドキュメントから要素を取得して、Nodeオブジェクトを返す
            Node node = document.getDocumentElement();
            //入力ストリーム終了
            in.close();

            //中身を取り出す----------------------------------------------------------
            //ノードreceiptNodeから最初の子ノードelementNodesを取得する。
            Node Result_Node = node.getFirstChild(); //getFirstChild　最初に見つかった子要素を取得⇒<Result>

            NodeList item_list = Result_Node.getChildNodes();//⇒<Request> ～<Hit index5>

            //JumsHelperのserch_resultメソッドからの戻り値で返却されたそれぞれのArrayList変数をセッションへ格納
            session.setAttribute("id", jh.serch_result(item_list, "Code"));
            session.setAttribute("name", jh.serch_result(item_list, "Name"));
            session.setAttribute("image", jh.serch_result(item_list, "Image", "Small"));
            session.setAttribute("price", jh.serch_result(item_list, "PriceLabel", "DefaultPrice"));

            //検索結果画面へ遷移
            request.getRequestDispatcher("/saerch.jsp").forward(request, response);

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
