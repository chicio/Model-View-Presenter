package it.chicio.productmvp.presenter;

import it.chicio.productmvp.model.Product;
import it.chicio.productmvp.view.ProductDetailView;

public class ProductDetailPresenter {
    private ProductDetailView productDetailView;
    private Product product;

    public ProductDetailPresenter(ProductDetailView productDetailView, Product product) {
        this.productDetailView = productDetailView;
        this.product = product;
    }

    public void onStart() {
        productDetailView.show("Product");
        if (isValidProduct()) {
            productDetailView.show(product);
        } else {
            productDetailView.showErrorWith("Product not valid");
        }
    }

    private boolean isValidProduct() {
        return product != null;
    }
}