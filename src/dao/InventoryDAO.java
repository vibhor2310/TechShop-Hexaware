package dao;

import entity.Product;

import java.util.List;

public interface InventoryDAO {
    public void getProduct(int productID);
    public int getQuantityInStock(int productID);
    public void addToInventory(int productID, int quantity);
    public void removeFromInventory(int productID, int quantity);
    public void updateStockQuantity(int productID, int newQuantity);
    public boolean isProductAvailable(int productID, int quantityToCheck);
   public void listOutOfStockProducts();
   public void listAllProducts();
   public double getInventoryValue();
   public void listLowStockProducts(int threshold);

}
