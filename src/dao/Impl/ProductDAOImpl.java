package dao.Impl;

import dao.ProductDAO;
import entity.Product;
import util.DBConn;

import java.sql.*;

public class ProductDAOImpl implements ProductDAO {
    Connection connection;
    Statement statement;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    @Override
    public void getProductDetails(int productID) {
        try {
            connection = DBConn.getMyDbConnection();
            String query = "SELECT * FROM products WHERE ProductID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,productID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                System.out.println("Product ID: " + resultSet.getInt("ProductID"));
                System.out.println("Product Name: " + resultSet.getString("ProductName"));
                System.out.println("Description: " + resultSet.getString("Description"));
                System.out.println("Price: " + resultSet.getDouble("Price"));
            }
            else{
                System.out.println("Product not found with ID: " + productID);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }



    }

    @Override
    public void updateProductInfo(Product product) {
        try{
            connection = DBConn.getMyDbConnection();
            String query = "UPDATE Products SET ProductName=?, Description=?, Price=? WHERE ProductID=?";
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getProductID());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Product information updated successfully.");
            } else {
                System.out.println("Failed to update product information.");
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public boolean isProductInStock(int productID) {
        try {
            connection = DBConn.getMyDbConnection();
            String query = "SELECT QuantityInStock FROM inventory WHERE ProductID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int stockQuantity = resultSet.getInt("QuantityInStock");
                return stockQuantity > 0;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int getPrice(int productID) {
        try{
            connection = DBConn.getMyDbConnection();
            String query = "SELECT Price FROM Products WHERE ProductID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,productID);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int price = resultSet.getInt("Price");
                return price;
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
}
