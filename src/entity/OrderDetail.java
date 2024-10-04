package entity;

public class OrderDetail {
    private int orderDetailID;
    private Order order;
    private Product product;
    private int quantity;

// Constructor
    public OrderDetail(){
        super();
    }
    public OrderDetail(int orderDetailID, Order order, Product product, int quantity){
        this.orderDetailID = orderDetailID;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

    // getter and setter
    public int getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(int orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
