package com.svalero.amazonapi.service;

import com.svalero.amazonapi.domain.Product;
import com.svalero.amazonapi.dto.ProductPatchDTO;
import com.svalero.amazonapi.exception.ProductNotFoundException;
import com.svalero.amazonapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findProduct(long id) throws ProductNotFoundException {
        return productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public List<Product> findByCategory(String categoryName) {
        List<Product> products = productRepository.findByCategory(categoryName);
        return products;
    }

    @Override
    public List<Product> findByPrice(float price) {
        return productRepository.findByPrice(price);
    }

    @Override
    public List<Product> findByPriceAndCategory(float price, String category) {
        return productRepository.findByPriceAndCategory(price, category);
    }

    @Override
    public Product addProduct(Product product) {
        product.setCreationDate(LocalDate.now());
        return productRepository.save(product);
    }

    @Override
    public void deleteProductById(long productId) throws ProductNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);
        productRepository.delete(product);
    }

    @Override
    public Product modifyProduct(long productId, Product product) throws ProductNotFoundException {
        Product newProduct = productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);
        newProduct.setName(product.getName());
        newProduct.setCategory(product.getCategory());
        newProduct.setDescription(product.getDescription());
        newProduct.setPrice(product.getPrice());
        newProduct.setCreationDate(product.getCreationDate());
        newProduct.setObservations(product.getObservations());
        newProduct.setQuantity(product.getQuantity());

        return productRepository.save(newProduct);
    }

    @Override
    public void patchProduct(long productId, ProductPatchDTO productPatchDTO) throws ProductNotFoundException {
        Product newProduct = productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);
        if (productPatchDTO.getField().equals("price")) {
            newProduct.setPrice(Float.parseFloat(productPatchDTO.getValue()));
        }

        productRepository.save(newProduct);
    }
}
