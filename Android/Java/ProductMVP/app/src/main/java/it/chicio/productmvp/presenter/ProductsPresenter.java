package it.chicio.productmvp.presenter;

import it.chicio.productmvp.model.Product;
import it.chicio.productmvp.model.ProductsRepositoryListener;
import it.chicio.productmvp.model.Repository;
import it.chicio.productmvp.view.ProductsView;

public class ProductsPresenter implements ProductsRepositoryListener {
    private ProductsView productsView;
    private Repository productsRepository;

    public ProductsPresenter(ProductsView productsView, Repository productsRepository) {
        this.productsView = productsView;
        this.productsRepository = productsRepository;
    }

    public void onStart() {
        productsView.show("Products");
        productsView.showLoadingStatus();
        productsRepository.setListener(this);
        productsRepository.get();
    }

    @Override
    public void onRetrieved(Product[] products) {
        tryToShowTheProducts(products);
        productsView.hideLoadingStatus();
    }

    private void tryToShowTheProducts(Product[] products) {
        if (isValidProduct(products)) {
            productsView.show(products);
        } else {
            productsView.showErrorWith("No products available");
        }
    }

    private boolean isValidProduct(Product[] products) {
        return products != null;
    }

    public void onSelected(Product product) {
        if (hasValidDescription(product)) {
            productsView.showDetailFor(product);
        } else {
            productsView.showErrorWith("Product without description");
        }
    }

    private boolean hasValidDescription(Product product) {
        return !product.getDescription().equals("");
    }
}
