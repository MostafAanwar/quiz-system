<%-- 
    Document   : student
    Created on : Nov 27, 2019, 11:25:33 PM
    Author     : fancypixel
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="dbpackage.dbConnect"%>

<%@page  contentType="text/html" pageEncoding="UTF-8"%>
<%
    session = request.getSession(false);
    String id = (String) session.getAttribute("id");
//    String pass = (String)session.getAttribute("password");

    Connection con = dbConnect.connectionDB();
    String query = "SELECT * FROM student_answer where s_id='" + id + "'";

    PreparedStatement st = con.prepareStatement(query);

    ResultSet rs = st.executeQuery();

    String returnedID = null;

    while (rs.next()) {
        returnedID = rs.getString("s_id");
    }
    boolean disable = false;
    if (returnedID != null) {
        disable = true;

    }

    rs.close();
    st.close();
    con.close();
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Student</title>
  </head>
    <body>
        <% if(disable){
            out.print("<button disabled>Start Exam</button>");
        }
        else
          out.print("<button onclick=\"window.location='quiz.jsp'\">Start Exam</button>");
        %>
        
    </body>
</html>