package it.chicio.productmvp;

import org.junit.Test;

import it.chicio.productmvp.model.Product;
import it.chicio.productmvp.model.Repository;
import it.chicio.productmvp.presenter.ProductsPresenter;
import it.chicio.productmvp.view.ProductsView;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ProductsPresenterTest {
    private ProductsView productsView;
    private Repository productsRepository;
    private ProductsPresenter productsPresenter;
    private final Product[] products  = new Product[]{new Product("aName", "aDescription", "anImage")};
    private final Product product  = new Product("aName", "aDescription", "anImage");

    @Test
    public void onStart() throws Exception {
        givenAProductsRepository();
        givenAProductsView();
        givenAProductsPresenter();
        whenTheProductsPresenterStarts();
        thenTheTitleIsDisplayed();
        thenTheProductViewShowsLoadingStatus();
        thenTheProductsPresenterBecomeListenerOfTheRepository();
        thenTryToRetrieveProduct();
    }

    @Test
    public void onProductsRetrieved() throws Exception {
        givenAProductsRepository();
        givenAProductsView();
        givenAProductsPresenter();
        whenTheProductsHaveBeenRetrieved();
        thenTheProductsViewShowsTheProducts();
        thenTheProductsViewHidesLoadingStatus();
    }

    @Test
    public void onNoProductsRetrieved() throws Exception {
        givenAProductsRepository();
        givenAProductsView();
        givenAProductsPresenter();
        whenTheProductsHaveNotBeenRetrieved();
        thenTheProductsViewShowsAnErrorMessage();
        thenTheProductsViewHidesLoadingStatus();
    }

    @Test
    public void onProductSelectedWithDescription() throws Exception {
        givenAProductsRepository();
        givenAProductsView();
        givenAProductsPresenter();
        whenAProductIsSelected();
        thenTheViewShowsTheProductDetail();
    }

    private void givenAProductsPresenter() {
        productsPresenter = new ProductsPresenter(productsView, productsRepository);
    }

    private void givenAProductsRepository() {
        productsRepository = mock(Repository.class);
    }

    private void givenAProductsView() {
        productsView = mock(ProductsView.class);
    }

    private void whenTheProductsPresenterStarts() {
        productsPresenter.onStart();
    }

    private void whenTheProductsHaveBeenRetrieved() {
        productsPresenter.onRetrieved(products);
    }

    private void whenTheProductsHaveNotBeenRetrieved() {
        productsPresenter.onRetrieved(null);
    }

    private void whenAProductIsSelected() {
        productsPresenter.onSelected(product);
    }

    private void thenTheTitleIsDisplayed() {
        verify(productsView).show(anyString());
    }

    private void thenTryToRetrieveProduct() {
        verify(productsRepository).get();
    }

    private void thenTheProductsPresenterBecomeListenerOfTheRepository() {
        verify(productsRepository).setListener(productsPresenter);
    }

    private void thenTheProductViewShowsLoadingStatus() {
        verify(productsView).showLoadingStatus();
    }

    private void thenTheProductsViewHidesLoadingStatus() {
        verify(productsView).hideLoadingStatus();
    }

    private void thenTheProductsViewShowsTheProducts() {
        verify(productsView).show(products);
    }

    private void thenTheProductsViewShowsAnErrorMessage() {
        verify(productsView).showErrorWith(anyString());
    }

    private void thenTheViewShowsTheProductDetail() {
        verify(productsView).showDetailFor(product);
    }
}
