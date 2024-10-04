package dao.Impl;

import dao.OrderDetailDAO;
import entity.OrderDetail;
import util.DBConn;

import java.sql.*;

public class OrderDetailDAOImpl implements OrderDetailDAO {

    Connection connection;
    Statement statement;
    PreparedStatement preparedStatement;
    ResultSet resultSet;


    @Override
    public double calculateSubtotal(int orderDetailID) {
        double subTotal = 0.0;
        try{
            connection = DBConn.getMyDbConnection();
            String query = "SELECT od.Quantity * p.Price FROM OrderDetails od JOIN Products p ON od.ProductID = p.ProductID WHERE od.OrderDetailID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,orderDetailID);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                double productPrice = resultSet.getDouble("Price");
                int quantity = resultSet.getInt("Quantity");
                subTotal = (productPrice * quantity);
            }
            else {
                System.out.println("OrderDetailID " + orderDetailID + " not found.");
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return subTotal;
    }

    @Override
    public void getOrderDetailInfo(int orderDetailID) {
        try {
            connection = DBConn.getMyDbConnection();
            String query = "SELECT * FROM orderDetails WHERE OrderDetailID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, orderDetailID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int orderID = resultSet.getInt("OrderID");
                int productID = resultSet.getInt("ProductID");
                int quantity = resultSet.getInt("Quantity");

                System.out.println("OrderDetailID: " + orderDetailID);
                System.out.println("OrderID: " + orderID);
                System.out.println("ProductID: " + productID);
                System.out.println("Quantity: " + quantity);

            }


        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void updateQuantity(int orderDetailID, int newQuantity) {
        try {
            connection = DBConn.getMyDbConnection();
            String query = "UPDATE orderDetails SET Quantity = ? WHERE OrderDetailID = ?";
            preparedStatement.setInt(1, newQuantity);
            preparedStatement.setInt(2, orderDetailID);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Quantity updated successfully for OrderDetailID: " + orderDetailID);
            } else {
                System.out.println("Failed to update quantity. OrderDetail not found.");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void addDiscount(int orderID, double discountPercentage) {
        try{
            connection = DBConn.getMyDbConnection();
            String query = "SELECT orderDetails.Quantity, products.Price FROM orderDetails INNER JOIN products ON orderDetails.ProductID = products.ProductID WHERE orderDetails.OrderID = ?";
            preparedStatement.setInt(1, orderID);
            resultSet = preparedStatement.executeQuery();

            double totalCostBeforeDiscount = 0.0;
            double totalCostAfterDiscount = 0.0;

            while (resultSet.next()) {
                int quantity = resultSet.getInt("Quantity");
                double price = resultSet.getDouble("Price");

                totalCostBeforeDiscount += quantity * price;
            }
            double discountAmount = totalCostBeforeDiscount * (discountPercentage / 100);
            totalCostAfterDiscount = totalCostBeforeDiscount - discountAmount;
            System.out.println("Total Order Cost Before Discount: $" + totalCostBeforeDiscount);
            System.out.println("Discount Amount: $" + discountAmount);
            System.out.println("Total Order Cost After Discount: $" + totalCostAfterDiscount);


        } catch (SQLException e){
            e.printStackTrace();
        }

    }

//    @Override
//    public void insertOrderDetails(OrderDetail orderDetail) {
//
//
//    }
}
