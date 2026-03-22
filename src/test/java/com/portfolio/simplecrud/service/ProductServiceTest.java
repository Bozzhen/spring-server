package com.portfolio.simplecrud.service;

import com.portfolio.simplecrud.exception.ProductNotFoundException;
import com.portfolio.simplecrud.model.entity.Product;
import com.portfolio.simplecrud.model.request.NewProduct;
import com.portfolio.simplecrud.model.response.ProductCreated;
import com.portfolio.simplecrud.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void shouldCreateProduct() {
        NewProduct newProduct = new NewProduct("Test Product", new BigDecimal("10.50"));
        Product savedProduct = new Product(1L, "Test Product", new BigDecimal("10.50"));

        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        ProductCreated result = productService.create(newProduct);

        assertNotNull(result);
        assertEquals("1", result.productId());
        verify(productRepository).save(any(Product.class));
    }

    @Test
    void shouldFindProductById() {
        Product expectedProduct = new Product(1L, "Test Product", new BigDecimal("10.50"));
        when(productRepository.findById(1L)).thenReturn(Optional.of(expectedProduct));

        Product result = productService.findById(1L);

        assertEquals(expectedProduct, result);
    }

    @Test
    void shouldThrowExceptionWhenProductNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.findById(1L));
    }
}
