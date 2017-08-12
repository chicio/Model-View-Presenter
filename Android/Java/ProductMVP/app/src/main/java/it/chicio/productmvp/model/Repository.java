package it.chicio.productmvp.model;

public interface Repository {
    void setListener(ProductsRepositoryListener productsRepositoryListener);
    void get();
}
