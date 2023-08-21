package com.cgl.repo;

import com.cgl.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>
{
    //Lister tous les produits non-archivés
    //@Query("select prod from Product prod where prod.isArchived = false")
    //List<Product> getAllProductNonArchived();

    //Lister tous les produits archivés
    //@Query("select prod from Product prod where prod.isArchived = true")
    //List<Product> getAllProductArchived();
}
