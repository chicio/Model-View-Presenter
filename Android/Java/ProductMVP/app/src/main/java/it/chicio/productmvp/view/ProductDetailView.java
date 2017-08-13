package it.chicio.productmvp.view;

import it.chicio.productmvp.model.Product;

public interface ProductDetailView {
    void show(String title);
    void show(Product product);
    void showErrorWith(String message);
}