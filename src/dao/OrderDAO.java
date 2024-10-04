package dao;

public interface OrderDAO {
    public double calculateTotalAmount(int orderID);
    public void getOrderDetails(int orderID);
    public void updateOrderStatus(int orderID, String newStatus);
    public void cancelOrder(int orderID);
    public void totalAmount(int productID, int quantity);

}
