package com.cgl.services.interfaces;

import com.cgl.entities.Product;

import java.util.List;

public interface IProduct
{
    Product addProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
    List<Product> getAllProduct();

    //List<Product> getNonArchivedList();
    //List<Product> getArchivedList();

    Product getOneProduct(Long id);
}
