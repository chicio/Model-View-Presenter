package it.chicio.productmvp;

import org.junit.Test;

import it.chicio.productmvp.model.Product;
import it.chicio.productmvp.presenter.ProductDetailPresenter;
import it.chicio.productmvp.view.ProductDetailView;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ProductDetailPresenterTest {
    private ProductDetailPresenter productDetailPresenter;
    private ProductDetailView productDetailView;
    private Product product;

    @Test
    public void onStartWithValidProduct() throws Exception {
        givenAValidProduct();
        givenAProductDetailView();
        givenAProductDetailPresenter();
        whenThePresenterStarts();
        thenTheTitleIsShown();
        thenTheProductDetailIsShown();
    }

    @Test
    public void onStartWithInvalidProduct() throws Exception {
        givenAnInvalidProduct();
        givenAProductDetailView();
        givenAProductDetailPresenter();
        whenThePresenterStarts();
        thenTheTitleIsShown();
        thenAnErrorMessageIsDisplayed();
    }

    private void givenAValidProduct() {
        product = new Product("aName", "aDescription", "anImage");
    }

    private void givenAnInvalidProduct() {
        product = null;
    }

    private void givenAProductDetailView() {
        productDetailView = mock(ProductDetailView.class);
    }

    private void givenAProductDetailPresenter() {
        productDetailPresenter = new ProductDetailPresenter(productDetailView, product);
    }

    private void whenThePresenterStarts() {
        productDetailPresenter.onStart();
    }

    private void thenTheProductDetailIsShown() {
        verify(productDetailView).show(product);
    }

    private void thenTheTitleIsShown() {
        verify(productDetailView).show(anyString());
    }

    private void thenAnErrorMessageIsDisplayed() {
        verify(productDetailView).showErrorWith(anyString());
    }
}
