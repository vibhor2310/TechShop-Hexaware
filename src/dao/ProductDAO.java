package dao;

import entity.Product;

public interface ProductDAO {
    public void getProductDetails(int productID);
    public void updateProductInfo(Product product);
    public boolean isProductInStock(int productID);
    public int getPrice(int productID);
}
