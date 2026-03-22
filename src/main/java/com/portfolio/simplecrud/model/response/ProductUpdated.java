package com.portfolio.simplecrud.model.response;

public record ProductUpdated(
        String message
) {
    public ProductUpdated() {
        this("Product updated successfully.");
    }
}
