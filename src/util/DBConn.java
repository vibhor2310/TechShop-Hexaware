package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
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
            String driver = properties.getProperty("db.driver");
            Class.forName(driver);
            conn = DriverManager.getConnection(url, name, password);
        }  catch (SQLException e) {

            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return conn;

    }

//    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
//        System.out.println(getMyDbConnection());
//    }

}
