package dao;

import entity.Customer;

public interface CustomerDAO {
    public void viewAllCustomer();
    public int calculateTotalOrders(int customerID);
    public void getCustomerDetails(int customerID);
    public void updateCustomerInfo(Customer customer);
    public void createNewCustomer(Customer customer);

}
