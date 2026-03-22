package com.portfolio.simplecrud.model.response;

public record ProductDeleted(
        String message
) {
    public ProductDeleted() {
        this("Product deleted.");
    }
}
