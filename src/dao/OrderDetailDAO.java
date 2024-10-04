package dao;

import entity.OrderDetail;

public interface OrderDetailDAO {
    public double calculateSubtotal(int orderDetailID);
    public void getOrderDetailInfo(int orderDetailID);
    public void updateQuantity(int orderDetailID, int newQuantity);
    public void addDiscount(int orderID, double discountPercentage);
//    public void insertOrderDetails(OrderDetail orderDetail);
}
