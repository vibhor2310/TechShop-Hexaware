package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConn {
    static Connection conn;

    public static Connection getMyDbConnection(){
        String filename = "C:\\Users\\vibho\\IdeaProjects\\TechShop\\src\\db.properties";
        Properties properties = new Properties();

        try {
            FileInputStream fis = new FileInputStream(filename);
            properties.load(fis);
            String url = properties.getProperty("db.url");
            String name = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");
//            Class.forName(Driver);
            conn = DriverManager.getConnection(url, name, password);
        }  catch (SQLException e) {

            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return conn;

    }

//    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
//        System.out.println(getMyDbConnection());
//    }

}
