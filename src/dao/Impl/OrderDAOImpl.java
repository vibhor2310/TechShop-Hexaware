package dao.Impl;

import dao.InventoryDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import exception.PaymentFailedException;
import util.DBConn;

import java.sql.*;

public class OrderDAOImpl implements OrderDAO {

    static ProductDAO productDAO = new ProductDAOImpl();


    Connection connection;
    Statement statement;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    @Override
    public double calculateTotalAmount(int orderID) {
        double totalAmount = 0.0;
        try{
            connection = DBConn.getMyDbConnection();
            String query = "SELECT TotalAmount FROM orders WHERE OrderID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,orderID);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                totalAmount = resultSet.getDouble("TotalAmount");
            }
            else {
                System.out.println("Order not found with ID: " + orderID);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return totalAmount;
    }

    @Override
    public void getOrderDetails(int orderID) {
        try{
            connection = DBConn.getMyDbConnection();
            String query = "SELECT * FROM Orders WHERE OrderID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,orderID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Order Details for Order ID: " + orderID);
                System.out.println("Customer ID: " + resultSet.getInt("CustomerID"));
                System.out.println("Order Date: " + resultSet.getDate("OrderDate"));
                System.out.println("Total Amount: $" + resultSet.getDouble("TotalAmount"));
//                System.out.println("Order Status: " + resultSet.getString("OrderStatus"));
            } else {
                System.out.println("Order not found with ID: " + orderID);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void updateOrderStatus(int orderID, String newStatus) {
        try{
            connection = DBConn.getMyDbConnection();
            String query = "UPDATE orders SET Status = ? WHERE OrderID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newStatus);
            preparedStatement.setInt(2, orderID);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Order status updated successfully.");
            } else {
                System.out.println("Failed to update order status. Order not found.");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void cancelOrder(int orderID) {
        try{
            connection = DBConn.getMyDbConnection();
            String query =  "DELETE FROM orders WHERE OrderID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,orderID);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Order canceled successfully.");
            } else {
                System.out.println("Failed to cancel order. Order not found.");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void totalAmount(int productID, int quantity) {
        int price = productDAO.getPrice(productID)*quantity;
        System.out.println("Your order amount is "+price);
        System.out.println("If You Want to place the order go to payment Option and Pay the Total amount" );

    }


}
