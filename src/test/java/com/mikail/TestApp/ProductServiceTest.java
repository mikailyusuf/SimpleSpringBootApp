package com.mikail.TestApp;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    public ProductServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllProducts() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");

        when(productRepository.findAll()).thenReturn(Collections.singletonList(product));

        List<Product> products = productService.getAllProducts();
        assertEquals(1, products.size());
        assertEquals("Test Product", products.get(0).getName());
    }

    @Test
    void getProductById() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Optional<Product> retrievedProduct = productService.getProductById(1L);
        assertEquals("Test Product", retrievedProduct.get().getName());
    }

    @Test
    void createProduct() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(100.0);

        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product savedProduct = productService.createProduct(product);
        assertEquals("Test Product", savedProduct.getName());
    }

    @Test
    void updateProduct() {
        Product existingProduct = new Product();
        existingProduct.setId(1L);
        existingProduct.setName("Existing Product");
        existingProduct.setPrice(50.0);

        Product updatedProductDetails = new Product();
        updatedProductDetails.setName("Updated Product");
        updatedProductDetails.setPrice(100.0);

        when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(any(Product.class))).thenReturn(updatedProductDetails);

        Optional<Product> updatedProduct = productService.getProductById(1L);
        if (updatedProduct.isPresent()) {
            Product product = updatedProduct.get();
            product.setName(updatedProductDetails.getName());
            product.setPrice(updatedProductDetails.getPrice());
            Product savedProduct = productService.createProduct(product);
            assertEquals("Updated Product", savedProduct.getName());
            assertEquals(100.0, savedProduct.getPrice());
        } else {
            fail("Product not found");
        }
    }

    @Test
    void deleteProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        doNothing().when(productRepository).deleteById(1L);

        productService.deleteProduct(1L);

        verify(productRepository, times(1)).deleteById(1L);
    }
}