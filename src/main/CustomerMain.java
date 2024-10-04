package main;

import dao.CustomerDAO;
import dao.Impl.CustomerDAOImpl;
import entity.Customer;
import exception.InvalidDataException;

import java.util.Scanner;

public class CustomerMain {
    static CustomerDAO customerDao = new CustomerDAOImpl();

    public boolean customerSectionDetails(){
        Scanner sc =new Scanner(System.in);

        while(true){
            System.out.println("\n-----------------------Customer Section-----------------");
            System.out.println("1. View All Customers Details");
            System.out.println("2. View Particular Customer Details By ID");
            System.out.println("3.  Create New  User");
            System.out.println("4. To calculateTotalOrders");
            System.out.println("5. To UpdateCustomerInfo");
            System.out.println("6. Exit");
            System.out.println("Enter your choice: ");
            int customerChoice = sc.nextInt();
            switch (customerChoice) {
                case 1:
                    customerDao.viewAllCustomer();
                    break;
                case 2:
                    System.out.println("Enter CustomerID");
                    int id = sc.nextInt();
                    customerDao.getCustomerDetails(id);
                    break;
                case 3:
                    Customer customer = new Customer();
                    System.out.println("Enter CustomerId: ");
                    customer.setCustomerID(sc.nextInt());
                    System.out.println("Enter First Name: ");
                    customer.setFirstName(sc.next());
                    System.out.println("Enter Last Name: ");
                    customer.setLastName(sc.next());
                    System.out.println("Enter Email: ");
                    customer.setEmail(sc.next());
                    System.out.println("Enter Phone: ");
                    customer.setPhone(sc.next());
                    System.out.println("Enter Address: ");
                    customer.setAddress(sc.next());
                    try {
                        customerDao.createNewCustomer(customer);
                    } catch (InvalidDataException e) {
                        e.printStackTrace();
                    }
                    break;

                case 4:
                    System.out.println("Enter the Customer Id: ");
                    int customer_order = sc.nextInt();
                    System.out.println("Total Orders are : " + customerDao.calculateTotalOrders(customer_order));
                    break;
                case 5:
                    Customer updateCustomer = new Customer();
                    System.out.println("Enter CustomerId You Want To Update: ");
                    updateCustomer.setCustomerID(sc.nextInt());
                    System.out.println("Enter First Name: ");
                    updateCustomer.setFirstName(sc.next());

                    System.out.println("Enter Last Name: ");
                    updateCustomer.setLastName(sc.next());

                    System.out.println("Enter Email: ");
                    updateCustomer.setEmail(sc.next());

                    System.out.println("Enter Phone: ");
                    updateCustomer.setPhone(sc.next());

                    System.out.println("Enter Address: ");
                    updateCustomer.setAddress(sc.next());
                    customerDao.updateCustomerInfo(updateCustomer);
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
