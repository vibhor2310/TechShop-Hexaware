package main;

import dao.Impl.OrderDetailDAOImpl;
import dao.OrderDetailDAO;

import java.util.Scanner;

public class OrderDetailMain {
    static OrderDetailDAO orderDetailDao = new OrderDetailDAOImpl();

    public boolean orderDetailSectionDetails(){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("\n---------------orderDetail Section -----------");
            System.out.println("1. calculateSubTotal");
            System.out.println("2. getOrderDetailInfo");
            System.out.println("3. updateQuantity");
            System.out.println("4. addDiscount");
            System.out.println("5. Exit");
            System.out.println("Enter your choice: ");

            int customerchoice = sc.nextInt();
            switch (customerchoice){
                case 1:
                    System.out.println("Enter the Order Detail ID: ");
                    int orderDetailId = sc.nextInt();
                    double subtotal = orderDetailDao.calculateSubtotal(orderDetailId);
                    System.out.println("Subtotal for Order Detail ID: $" + subtotal);
                    break;
                case 2:
                    System.out.println("Enter the Order Detail ID: ");
                    int orderDetailIdInfo = sc.nextInt();
                    orderDetailDao.getOrderDetailInfo(orderDetailIdInfo);
                    break;
                case 3:
                    System.out.println("Enter the Order Detail ID: ");
                    int orderDetailIdUpdate = sc.nextInt();
                    System.out.println("Enter the new quantity: ");
                    int newQuantity = sc.nextInt();
                    orderDetailDao.updateQuantity(orderDetailIdUpdate, newQuantity);
                    System.out.println("Quantity updated successfully.");
                    break;
                case 4:
                    System.out.println("Enter the Order Detail ID: ");
                    int orderDetailIdDiscount = sc.nextInt();
                    System.out.println("Enter the discount percentage: ");
                    double discountPercentage = sc.nextDouble();
                    orderDetailDao.addDiscount(orderDetailIdDiscount, discountPercentage);
                    System.out.println("Discount added successfully.");
                    break;
                case 5:
                    System.out.println("Returning to Main Section.");
                    return false;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }

        }
    }


}
