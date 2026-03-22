package com.portfolio.simplecrud.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.portfolio.simplecrud.exception.ProductNotFoundException;
import com.portfolio.simplecrud.model.entity.Product;
import com.portfolio.simplecrud.model.request.NewProduct;
import com.portfolio.simplecrud.model.request.PatchProduct;
import com.portfolio.simplecrud.model.response.ProductCreated;
import com.portfolio.simplecrud.model.response.ProductDto;
import com.portfolio.simplecrud.repository.ProductRepository;

import java.math.BigDecimal;

import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
@Transactional
@Service
public class ProductService {
    final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Page<ProductDto> getAll(@NonNull Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(ProductDto::new);
    }

    public ProductCreated create(NewProduct newProduct) {
        Product p = Product.builder()
                .name(newProduct.name())
                .price(newProduct.price())
                .build();
        p = productRepository.save(p);
        return new ProductCreated(p.getId().toString());
    }

    @Transactional(readOnly = true)
    public Product findById(@NonNull Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException());
    }

    public void patch(PatchProduct patchProduct, Long id) {
        Product p = findById(id);

        String newName = patchProduct.name();
        if (hasText(newName)) {
            p.setName(newName);
        }

        BigDecimal newPrice = patchProduct.price();
        if (newPrice != null) {
            p.setPrice(newPrice);
        }
        // saved back on transaction closing
    }

    public void delete(Long id) {
        Product p = findById(id);
        productRepository.delete(p);
    }
}
