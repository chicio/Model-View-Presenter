package it.chicio.productmvp.model;

public interface ProductsRepositoryListener {
    void onRetrieved(Product[] products);
}