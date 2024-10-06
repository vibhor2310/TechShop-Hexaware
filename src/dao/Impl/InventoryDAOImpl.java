package dao.Impl;

import dao.InventoryDAO;
import util.DBConn;

import java.sql.*;

public class InventoryDAOImpl implements InventoryDAO {

    Connection connection;
    Statement statement;
    PreparedStatement preparedStatement;
    ResultSet resultSet;



    @Override
    public void getProduct(int productID) {
        try{
            connection = DBConn.getMyDbConnection();
            String query = "SELECT * FROM products WHERE ProductID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productID);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                System.out.println("Product ID: " + resultSet.getInt("ProductID"));
                System.out.println("Product Name: " + resultSet.getString("ProductName"));
                System.out.println("Description: " + resultSet.getString("Description"));
                System.out.println("Price: " + resultSet.getDouble("Price"));
            }
            else {
                System.out.println("Product not found with ID: " + productID);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public int getQuantityInStock(int productID) {
        int quantityInStock = 0;
        try {
            connection = DBConn.getMyDbConnection();
            String query = "SELECT QuantityInStock FROM inventory WHERE ProductID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productID);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                quantityInStock = resultSet.getInt("QuantityInStock");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quantityInStock;

    }

    @Override
    public void addToInventory(int productID, int quantity) {
        try{
            connection = DBConn.getMyDbConnection();
            String query = "INSERT INTO inventory (ProductID, QuantityInStock) VALUES (?, ?) ON DUPLICATE KEY UPDATE QuantityInStock = QuantityInStock + ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productID);
            preparedStatement.setInt(2, quantity);
            preparedStatement.setInt(3, quantity);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Quantity added successfully.");
            } else {
                System.out.println("Quantity not added");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void removeFromInventory(int productID, int quantity) {
        try{
            connection = DBConn.getMyDbConnection();
            String query = "UPDATE inventory SET QuantityInStock = QuantityInStock - ? WHERE ProductID = ?";
            preparedStatement = connection.prepareStatement(query);
            int currentStock = getQuantityInStock(productID);
//            if(currentStock < quantity) {
//                throw new InsufficientStockException("Insufficient stock for product ID: " +productId);
//            }
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, productID);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Quantity is removed from inventory successfully");

            } else {
                System.out.println("Quantity is removed from inventory unsuccessfully");

            }
        } catch(SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void updateStockQuantity(int productID, int newQuantity) {
        try{
            connection = DBConn.getMyDbConnection();
            String query = "UPDATE inventory SET QuantityInStock = ? WHERE ProductID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,  newQuantity);
            preparedStatement.setInt(2,  productID);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Product not found in inventory");
            } else {
                System.out.println("Product found in inventory");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }


    }

    @Override
    public boolean isProductAvailable(int productID, int quantityToCheck) {
        boolean available = false;
        try{
            connection = DBConn.getMyDbConnection();
            String query = "SELECT QuantityInStock FROM inventory WHERE ProductID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productID);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int quantityInStock = resultSet.getInt("QuantityInStock");
                available = quantityInStock >= quantityToCheck;
            }

        } catch(SQLException e){
            e.printStackTrace();
        }
        return available;
    }

    @Override
    public void listOutOfStockProducts() {
        try{
            connection = DBConn.getMyDbConnection();
            String query = "SELECT * FROM products LEFT JOIN inventory ON products.productId = inventory.productId WHERE inventory.productId IS NULL OR inventory.QuantityInStock = 0";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int productID = resultSet.getInt("ProductID");
                String productName = resultSet.getString("ProductName");
                String description = resultSet.getString("Description");
                double price = resultSet.getDouble("Price");


                System.out.println("ProductID: " + productID);
                System.out.println("ProductName: " + productName);
                System.out.println("Description: "+description);
                System.out.println("Price: " + price);

            }

        } catch (SQLException e){
            e.printStackTrace();
        }


    }

    @Override
    public void listAllProducts() {
        try {
            connection = DBConn.getMyDbConnection();
            String query = "SELECT * FROM products";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int productID = resultSet.getInt("ProductID");
                String productName = resultSet.getString("ProductName");
                String description = resultSet.getString("Description");
                double price = resultSet.getDouble("Price");


                System.out.println("ProductID: " + productID);
                System.out.println("ProductName: " + productName);
                System.out.println("Description: "+description);
                System.out.println("Price: " + price);

            }
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public double getInventoryValue() {
        double inventoryValue = 0.0;
        try{
            connection = DBConn.getMyDbConnection();
            String query = "SELECT sum(Inventory.QuantityInStock * Products.Price) as TotalValue FROM Inventory INNER JOIN Products ON Inventory.ProductID = Products.ProductID";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {


                inventoryValue = resultSet.getDouble("TotalValue");
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("Total Inventory Value: $" + inventoryValue);
        return inventoryValue;
    }

    @Override
    public void listLowStockProducts(int threshold) {
        try {
            connection = DBConn.getMyDbConnection();
            String query = "SELECT * FROM Products p JOIN Inventory i ON p.ProductID = i.ProductID WHERE i.QuantityInStock<?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, threshold);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int productID = resultSet.getInt("ProductID");
                String productName = resultSet.getString("ProductName");
                String description = resultSet.getString("Description");
                double price = resultSet.getDouble("Price");


                System.out.println("ProductID: " + productID);
                System.out.println("ProductName: " + productName);
                System.out.println("Description: " + description);
                System.out.println("Price: " + price);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
