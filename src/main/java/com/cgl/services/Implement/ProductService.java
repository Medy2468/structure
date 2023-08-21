package com.cgl.services.Implement;

import com.cgl.entities.Product;
import com.cgl.repo.ProductRepository;
import com.cgl.services.interfaces.IProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProduct
{
    private final ProductRepository productRepository;

    @Override
    public Product addProduct(Product product)
    {
        //Enregistrer un nouveau produit
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product)
    {
        //Vérifier si un produit existe ou pas
        Product isExisted = getOneProduct(id);

        //Récupérer de nouvelles valeurs
        isExisted.setLabel(product.getLabel());
        isExisted.setDescription(product.getDescription());
        isExisted.setPrice(product.getPrice());
        isExisted.setPhotoFile(product.getPhotoFile());

        return productRepository.save(isExisted);
    }

    @Override
    public void deleteProduct(Long id)
    {
        //Vérifier si un produit existe ou pas
        Product isExisted = getOneProduct(id);

        //Effacer
        productRepository.delete(isExisted);
    }

    @Override
    public List<Product> getAllProduct()
    {
        //Afficher la liste de tous les produits
        return productRepository.findAll();
    }

    @Override
    public Product getOneProduct(Long id)
    {
        //Récupérer un produit
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Le produit n'existe pas !"));
    }
}
