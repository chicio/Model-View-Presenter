package it.chicio.productmvp.model;

import java.util.Timer;
import java.util.TimerTask;

public class ProductsRepository implements Repository {
    private ProductsRepositoryListener productsRepositoryListener;

    @Override
    public void setListener(ProductsRepositoryListener productsRepositoryListener) {
        this.productsRepositoryListener = productsRepositoryListener;
    }

    public void get() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Product[] products = {
                    new Product("Car", "A beautiful car", "car"),
                    new Product("Book", "", "book")
                };
                productsRepositoryListener.onRetrieved(products);
            }
        }, 3000);
    }
}
