package main;

import dao.Impl.ProductDAOImpl;
import dao.ProductDAO;
import entity.Product;
import exception.InvalidDataException;

import java.util.Scanner;

public class ProductMain {
    static ProductDAO productDao = new ProductDAOImpl();

    public boolean ProductSectionDetails() {
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("\n--------------product Section ----------");
            System.out.println("1. getProductDetails");
            System.out.println("2. updateProductInfo");
            System.out.println("3. isProductInStock");
            System.out.println("4. Exit");
            System.out.println("Enter your choice: ");
            int customerchoice = sc.nextInt();
            switch (customerchoice){
                case 1:
                    System.out.println("Enter the product Id: ");
                    int id = sc.nextInt();
                    productDao.getProductDetails(id);
                    break;
                case 2:
                    System.out.println("Enter Product Id which You want to update");
                    Product product = new Product();
                    product.setProductID(sc.nextInt());
                    System.out.println("Update Product Name: ");
                    product.setProductName(sc.next());
                    System.out.println("Update Description: ");
                    product.setDescription(sc.next());
                    System.out.println("Update the price: ");
                    product.setPrice(sc.nextDouble());
                    try {
                        productDao.updateProductInfo(product);
                    } catch (InvalidDataException e){
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    System.out.println("Enter The Product Id:");
                    int productId = sc.nextInt();
                    boolean isInStock = productDao.isProductInStock(productId);
                    if (isInStock) {
                        System.out.println("Product is in stock.");
                    } else {
                        System.out.println("Product is out of stock.");
                    }
                    break;
                case 4:
                    System.out.println("Returning to Main Menu.");
                    return false;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");

            }

        }

    }

}
