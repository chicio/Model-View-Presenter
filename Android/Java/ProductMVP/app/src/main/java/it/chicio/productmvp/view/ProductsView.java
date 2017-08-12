package it.chicio.productmvp.view;

import it.chicio.productmvp.model.Product;

public interface ProductsView {
    void showLoadingStatus();
    void hideLoadingStatus();
    void show(String title);
    void show(Product[] products);
    void showErrorWith(String message);
    void showDetailFor(Product product);
}
