/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dbpackage.dbConnect;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import javax.jms.Session;
/**
 *
 * @author fancypixel
 */
@WebServlet(urlPatterns = {"/main"})
public class main extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet main</title>");            
            out.println("</head>");
            out.println("<body>");
            //out.println("<h1>Servlet main at " + request.getContextPath() + "</h1>");
            dbConnect db= new dbConnect();
            Connection con = db.connectionDB();
            Statement Stmt = con.createStatement();
            ResultSet RS =Stmt.executeQuery("SELECT question_id,question_text FROM question\n" +
            "ORDER BY RAND()\n" +
            "LIMIT 1");
             int id=0;
             List<Integer> ids = new ArrayList<>();
            while(RS.next()){
               out.println("<h1>");
               out.println(RS.getString("question_text"));
               id = RS.getInt("question_id");
               out.println("</h1>");
               out.println("<br>");
           }
            RS = Stmt.executeQuery("SELECT text, answer_id from answer where correct = '1' and q_id='"+ id + "'");
            
            if(RS.next())
                out.print("<input name = \"ids\" type=\"radio\">");
                out.println(RS.getString("text"));
                out.println("<br>");
            ids.add(RS.getInt("answer_id"));
            RS =Stmt.executeQuery("SELECT text, answer_id from answer where correct = '0' and q_id='"+ id + "'" + "ORDER BY RAND() LIMIT 2");    
            while(RS.next())    
            {
                out.print("<input name = \"ids\" type=\"radio\">");
                out.println(RS.getString("text"));
                out.println("<br>");
                ids.add(RS.getInt("answer_id"));
            }
            
            RS =Stmt.executeQuery("insert into student_answer values("+  ");");
            RS.close();
            Stmt.close();
            con.close();
            out.println("</body>");
            out.println("</html>");
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
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
