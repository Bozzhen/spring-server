package com.portfolio.simplecrud.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.portfolio.simplecrud.model.request.NewProduct;
import com.portfolio.simplecrud.model.request.PatchProduct;
import com.portfolio.simplecrud.model.response.ProductCreated;
import com.portfolio.simplecrud.model.response.ProductDeleted;
import com.portfolio.simplecrud.model.response.ProductDto;
import com.portfolio.simplecrud.model.response.ProductUpdated;
import com.portfolio.simplecrud.service.ProductService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    final ProductService productService;

    @GetMapping
    Page<ProductDto> getAll(Pageable pageable) {
        return productService.getAll(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ProductCreated create(
            @Valid @RequestBody NewProduct newProduct) {
        return productService.create(newProduct);
    }

    @GetMapping("/{id}")
    ProductDto getOne(
            @PathVariable Long id) {
        return new ProductDto(productService.findById(id));
    }

    @PatchMapping("/{id}")
    ProductUpdated update(
            @Valid @RequestBody PatchProduct patchProduct,
            @PathVariable Long id) {
        productService.patch(patchProduct, id);
        return new ProductUpdated();
    }

    @DeleteMapping("/{id}")
    ProductDeleted delete(
            @PathVariable Long id) {
        productService.delete(id);
        return new ProductDeleted();
    }
}
