<%-- 
    Document   : quiz
    Created on : Nov 27, 2019, 10:50:31 AM
    Author     : fancypixel
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dbpackage.dbConnect"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="student.jsp">
            
        
        <% 
            Connection con = dbConnect.connectionDB();
            Statement Stmt = con.createStatement();
            ResultSet RS =Stmt.executeQuery("SELECT question_id,question_text FROM question\n" +
            "ORDER BY RAND()\n" +
            "LIMIT 1");
            int q_id=0;
            List<Integer> ids = new ArrayList<Integer>();
                        while(RS.next()){
               out.println("<h1>");
               out.println(RS.getString("question_text"));
               q_id = RS.getInt("question_id");
               out.println("</h1>");
               out.println("<br>");
           }
            RS = Stmt.executeQuery("SELECT text, answer_id from answer where correct = '1' and q_id='"+ q_id + "'");
            
            if(RS.next())
                out.print("<input name = \"ids\" type=\"radio\">");
                out.println(RS.getString("text"));
                out.println("<br>");
            ids.add(RS.getInt("answer_id"));
            RS =Stmt.executeQuery("SELECT text, answer_id from answer where correct = '0' and q_id='"+ q_id + "'" + "ORDER BY RAND() LIMIT 2");    
            while(RS.next())    
            {
                out.print("<input name = \"ids\" type=\"radio\">");
                out.println(RS.getString("text"));
                out.println("<br>");
                ids.add(RS.getInt("answer_id"));
            }
            session = request.getSession(false);
            String ID = (String) session.getAttribute("id");
           for(int i=0;i<ids.size();i++){
                Stmt.executeUpdate("insert into student_answer values('"+ ID+"','"+ q_id +"','" + ids.get(i) +"');");
           }
           
            
            RS.close();
            Stmt.close();
            con.close();
        %>
        <input type="submit" value="submit"/> 
        </form>
    </body>
</html>
