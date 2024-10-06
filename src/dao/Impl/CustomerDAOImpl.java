package dao.Impl;

import dao.CustomerDAO;
import entity.Customer;
import exception.InvalidDataException;
import util.DBConn;

import java.sql.*;

public class CustomerDAOImpl implements CustomerDAO {
    Connection connection;
    Statement statement;
    PreparedStatement preparedStatement;
    ResultSet resultSet;


    @Override
    public void viewAllCustomer() {
        try{
            connection = DBConn.getMyDbConnection();
            statement = connection.createStatement();
            String query = "SELECT * FROM Customers";
            resultSet = statement.executeQuery(query);
            System.out.println("\nList of Customers:");
            while(resultSet.next()){
                System.out.println("CustomerID: " + resultSet.getInt(1));
                System.out.println("FirstName: " + resultSet.getString(2));
                System.out.println("LastName: " + resultSet.getString(3));
                System.out.println("Email: " + resultSet.getString(4));
                System.out.println("Phone: " + resultSet.getString(5));
                System.out.println("Address: " + resultSet.getString(6));
                System.out.println("-------------------------------------");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public int calculateTotalOrders(int customerID) {
        try{
            connection = DBConn.getMyDbConnection();
            String query = "SELECT COUNT(*) FROM orders WHERE CustomerID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, customerID);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt(1);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void getCustomerDetails(int customerID) {
        try {
            connection = DBConn.getMyDbConnection();
            String query = "SELECT * FROM Customers WHERE customerID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,customerID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                System.out.println("Customer ID: " + resultSet.getInt("CustomerID"));
                System.out.println("First Name: " + resultSet.getString("FirstName"));
                System.out.println("Last Name: " + resultSet.getString("LastName"));
                System.out.println("Email: " + resultSet.getString("Email"));
                System.out.println("Phone: " + resultSet.getString("Phone"));
                System.out.println("Address: " + resultSet.getString("Address"));
            }
            else{
                System.out.println("Customer Not Found with ID: " + customerID);
            }
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("An error occurred while retrieving Customer details.");
        }
    }

    @Override
    public void updateCustomerInfo(Customer customer) {
        try {
            connection = DBConn.getMyDbConnection();
            String query = "UPDATE Customers SET Email = ?,Phone = ?, Address = ? WHERE CustomerID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,customer.getEmail());
            preparedStatement.setString(2, customer.getPhone());
            preparedStatement.setString(3,customer.getAddress());
            preparedStatement.setInt(4,customer.getCustomerID());

            int rowsUpdated = preparedStatement.executeUpdate();
            if(rowsUpdated>0){
                System.out.println("Customer information updated successfully.");
            } else {
                System.out.println("Failed to update customer information.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void createNewCustomer(Customer customer)  {
        try {
            connection = DBConn.getMyDbConnection();
            String query = "INSERT INTO Customer(CustomerID,FirstName, LastName, Email, Phone, Address) VALUES (?,?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            if(!isValidEmail(customer.getEmail())) {
                throw new InvalidDataException(toString());
            }
            preparedStatement.setInt(1, customer.getCustomerID());
            preparedStatement.setString(2, customer.getFirstName());
            preparedStatement.setString(3, customer.getLastName());
            preparedStatement.setString(4, customer.getEmail());
            preparedStatement.setString(5, customer.getPhone());
            preparedStatement.setString(6, customer.getAddress());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("New customer created successfully!");
            } else {
                System.out.println("Failed to create a new customer.");
            }
        } catch(SQLException e){
            e.printStackTrace();
        }


    }
    public static boolean isValidEmail(String email) {
        // Implement your email validation logic here
        // For simplicity, this example assumes a basic email format check
        return email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    }

}
