package dbpackage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
/**
 *
 * @author fancypixel
 */
public class dbConnect {
    public static Connection connectionDB() throws ClassNotFoundException, SQLException{
    Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/quizsys";
            String user = "root";
            String password = "root";
            Connection Con =null;
            Con = DriverManager.getConnection(url, user, password);
        return Con;
}
}
