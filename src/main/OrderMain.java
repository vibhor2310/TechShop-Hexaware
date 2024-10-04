package main;

import dao.Impl.OrderDAOImpl;
import dao.OrderDAO;
import exception.InvalidDataException;

import java.util.Scanner;

public class OrderMain {
    static OrderDAO orderDao = new OrderDAOImpl();
    public boolean orderSectionDetails(){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("\n---------Orders Section ---------");
            System.out.println("1. calculateTotalAmount");
            System.out.println("2. getOrderDetails");
            System.out.println("3. updateOrderStatus");
            System.out.println("4. cancelOrder");
            System.out.println("5. totalAmount");
            System.out.println("6. Exit");
            System.out.println("Enter your choice: ");
            int customerchoice = sc.nextInt();
            switch (customerchoice){
                case 1:
                    System.out.println("Enter the Order Id: ");
                    int orderId = sc.nextInt();
                    double totalAmount = orderDao.calculateTotalAmount(orderId);
                    System.out.println("Total Amount for Order ID: $" + totalAmount);
                    break;
                case 2:
                    System.out.println("Enter the Order Id: ");
                    int orderId1 = sc.nextInt();
                    orderDao.getOrderDetails(orderId1);
                    break;
                case 3:
                    System.out.println("Enter the Order Id: ");
                    int orderId2 = sc.nextInt();
                    System.out.println("Enter New Order status");
                    String newStatus = sc.next();
                    try {
                        orderDao.updateOrderStatus(orderId2, newStatus);
                    } catch (InvalidDataException e){
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    System.out.println("Enter the Order Id to cancel: ");
                    int orderId3 = sc.nextInt();
                    orderDao.cancelOrder(orderId3);
                    break;
                case 5:
                    System.out.println("Enter the Product id: ");
                    int productid = sc.nextInt();
                    System.out.println("Enter the Quantity: ");
                    int quantity = sc.nextInt();
                    try {
                        orderDao.totalAmount(productid, quantity);
                    } catch (InvalidDataException e){
                        e.printStackTrace();
                    }
                    break;
                case 6:
                    System.out.println("Returning to Main Section.");
                    return false;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");


            }


        }
    }
}
