package main;

import java.util.Scanner;

public class MainModule {
    static CustomerMain customerMain = new CustomerMain();
    static ProductMain productMain = new ProductMain();
    static OrderMain orderMain = new OrderMain();
    static OrderDetailMain orderDetailMain = new OrderDetailMain();
    static InventoryMain inventoryMain = new InventoryMain();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("-------------------TECH - SHOP----------------");
                System.out.println("1. Customer Section");
                System.out.println("2. Product Section");
                System.out.println("3. Orders Section");
                System.out.println("4. OrderDetails Section");
                System.out.println("5. Inventory Section");
                System.out.println("6. Exit");
                System.out.println("Enter your choice: ");
                int choice = sc.nextInt();

                boolean backToMainSection;
                switch (choice) {
                    case 1:
                        backToMainSection = customerMain.customerSectionDetails();
                        if (!backToMainSection) {
                            break;
                        }
                        break;
                    case 2:
                        backToMainSection = productMain.ProductSectionDetails();
                        if (!backToMainSection) {
                            break;
                        }
                        break;
                    case 3:
                        backToMainSection = orderMain.orderSectionDetails();
                        if (!backToMainSection) {
                            break;
                        }
                        break;
                    case 4:
                        backToMainSection = orderDetailMain.orderDetailSectionDetails();
                        if (!backToMainSection) {
                            break;
                        }
                        break;
                    case 5:
                        backToMainSection = inventoryMain.inventorySectionDetails();
                        if (!backToMainSection) {
                            break;
                        }
                        break;
                    case 6:
                        System.out.println("Exiting TechShop. Goodbye!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                }

            }

        }
    }

