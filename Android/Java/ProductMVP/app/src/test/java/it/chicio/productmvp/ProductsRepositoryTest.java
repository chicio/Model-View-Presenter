package it.chicio.productmvp;

import org.junit.Test;
import org.mockito.ArgumentMatchers;

import it.chicio.productmvp.model.Product;
import it.chicio.productmvp.model.ProductsRepository;
import it.chicio.productmvp.model.ProductsRepositoryListener;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ProductsRepositoryTest {

    private ProductsRepositoryListener productsRepositoryListener;
    private ProductsRepository productsRepository;

    @Test
    public void productsRetrieved() throws Exception {
        givenAProductListener();
        givenAProductRepositoryWithAListener();
        whenTheRepositoryTriesToGetTheProducts();
        thenTheProductsRepositoryListenerIsNotified();
    }

    private void givenAProductRepositoryWithAListener() {
        productsRepository = new ProductsRepository();
        productsRepository.setListener(productsRepositoryListener);
    }

    private void givenAProductListener() {
        productsRepositoryListener = mock(ProductsRepositoryListener.class);
    }

    private void whenTheRepositoryTriesToGetTheProducts() throws InterruptedException {
        productsRepository.get();
        Thread.sleep(3100);
    }

    private void thenTheProductsRepositoryListenerIsNotified() {
        verify(productsRepositoryListener).onRetrieved(ArgumentMatchers.<Product[]>any());
    }
}
