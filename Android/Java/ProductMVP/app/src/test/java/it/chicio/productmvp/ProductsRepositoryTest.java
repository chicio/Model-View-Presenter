package it.chicio.productmvp;

import android.os.Handler;

import org.junit.Test;
import org.mockito.ArgumentMatchers;

import java.util.Timer;
import java.util.TimerTask;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ProductsRepositoryTest {

    @Test
    public void productsRetrieved() throws Exception {
        Product[] products = { new Product("name", "description", "image") };
        ProductsRepositoryListener productsRepositoryListener = mock(ProductsRepositoryListener.class);
        ProductsRepository productsRepository = new ProductsRepository(productsRepositoryListener);
        productsRepository.get();
        Thread.sleep(3000);
        verify(productsRepositoryListener).onRetrieved(ArgumentMatchers.<Product[]>any());
    }

    private class ProductsRepository {
        private ProductsRepositoryListener productsRepositoryListener;

        ProductsRepository(ProductsRepositoryListener productsRepositoryListener) {
            this.productsRepositoryListener = productsRepositoryListener;
        }

        void get() {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    Product[] products = { new Product("name", "description", "image") };
                    productsRepositoryListener.onRetrieved(products);
                }
            }, 2000);
        }
    }

    private interface ProductsRepositoryListener {
        void onRetrieved(Product[] products);
    }
}
