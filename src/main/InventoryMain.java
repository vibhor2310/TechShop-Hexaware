package main;

import dao.Impl.InventoryDAOImpl;
import dao.InventoryDAO;
import exception.InvalidDataException;
import java.util.Scanner;

public class InventoryMain {
    static InventoryDAO inventoryDao = new InventoryDAOImpl();

    public boolean inventorySectionDetails(){
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("\n----------Inventory Section------------");
            System.out.println("1. getProduct");
            System.out.println("2. getQuantityInStock");
            System.out.println("3. addToInventory");
            System.out.println("4. removeFromInventory");
            System.out.println("5. updateStockQuantity");
            System.out.println("6. isProductAvailable");
            System.out.println("7. getInventoryValue");
            System.out.println("8. listLowStockProducts");
            System.out.println("9. listOutOfStockProducts");
            System.out.println("10. listAllProducts");
            System.out.println("11. Exit");
            System.out.println("Enter your choice: ");
            int customerchoice = sc.nextInt();
            switch (customerchoice){
                case 1:
                    System.out.println("Enter the product Id: ");
                    int productId = sc.nextInt();
                    inventoryDao.getProduct(productId);
                    System.out.println("Product found");
                    break;
                case 2:
                    System.out.println("Enter the product Id: ");
                    int productId1 = sc.nextInt();
                    int quantityInStock = inventoryDao.getQuantityInStock(productId1);
                    System.out.println("the quantity in stock: " + quantityInStock);
                    break;
                case 3:
                    System.out.println("Enter the product Id: ");
                    int productId2 = sc.nextInt();
                    System.out.println("Enter the quantity to add: ");
                    int quantity = sc.nextInt();
                    try {
                        inventoryDao.addToInventory(productId2, quantity);
                    } catch (InvalidDataException e){
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    System.out.println("Enter the product Id: ");
                    int productId3 = sc.nextInt();
                    System.out.println("Enter the quantity to remove: ");
                    int quantity1 = sc.nextInt();
                    try {
                        inventoryDao.removeFromInventory(productId3, quantity1);
                    } catch (InvalidDataException e){
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    System.out.println("Enter the product Id: ");
                    int productId4 = sc.nextInt();
                    System.out.println("Enter new quantity stock: ");
                    int quantity2 = sc.nextInt();
                    inventoryDao.updateStockQuantity(productId4, quantity2);
                    System.out.println("Stock of the quantity updated successfully");
                    break;
                case 6:
                    System.out.println("Enter the product Id: ");
                    int productId5 = sc.nextInt();
                    System.out.println("Enter the quantity to check: ");
                    int quantity3 = sc.nextInt();
                    boolean isAvailable = inventoryDao.isProductAvailable(productId5, quantity3);
                    if(isAvailable) {
                        System.out.println("Product is available ");
                    } else {
                        System.out.println("Product is not available ");
                    }
                    break;
                case 7:
                    double productValue = inventoryDao.getInventoryValue();
                    System.out.println("Total Inventory Value: $" + productValue);
                    break;
                case 8:
                    System.out.println("Enter low stock threshold value: ");
                    int threshold = sc.nextInt();
                    System.out.println("Low stock products: ");
                    inventoryDao.listLowStockProducts(threshold);
                    break;
                case 9:
                    System.out.println("Out of stock products: ");
                    inventoryDao.listOutOfStockProducts();
                    break;
                case 10:
                    System.out.println("All products: ");
                    inventoryDao.listAllProducts();
                    break;
                case 11:
                    System.out.println("Returning to Main Menu.");
                    return false;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 11.");


            }
        }
    }
}
