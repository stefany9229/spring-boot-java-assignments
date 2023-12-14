package org.adaschool.api.service.product;

import org.adaschool.api.exception.ProductNotFoundException;
import org.adaschool.api.repository.product.Product;
import org.adaschool.api.repository.product.ProductDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsServiceMap implements ProductsService {

    private final ArrayList<Product> listProduct;


    public ProductsServiceMap( ArrayList<Product> listprodcut) {
        this.listProduct = new ArrayList<>();
    }


    @Override
    public Product save(Product product) {
        listProduct.add(product);
        return product;
    }

    @Override
    public Optional<Product> findById(String id) {
        Optional<Product> productOptional = listProduct.stream().filter(product -> product.getId().equals(id)).findFirst();
        if (productOptional.isEmpty()){
            throw new ProductNotFoundException(id);
        }
        return productOptional;
    }

    @Override
    public List<Product> all() {
        return listProduct;
    }

    @Override
    public void deleteById(String id) {
        Optional<Product> productOptional = listProduct.stream().filter(product -> product.getId().equals(id)).findFirst();
        listProduct.remove(productOptional.get());
    }

    @Override
    public Product update(Product product, String productId) {
        Optional<Product> productOptional = findById(productId);
        Product product1 = null;
        if (!productOptional.isEmpty()) {
            product1 = productOptional.get();
            product1.setName(product.getName());
            product1.setPrice(product.getPrice());
            product1.setDescription(product.getDescription());
            product1.setCategory(product.getCategory());
            product1.setImageUrl(product.getImageUrl());
            product1.setTags(product.getTags());
            save(product1);
        }else {
            throw new ProductNotFoundException(productId);
        }
        return product1 ;
    }
}
