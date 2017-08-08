import XCTest

@testable import ProductsMVP

class ProductsPresenterTests: XCTestCase {
    private var productsRepositoryWithProducts: ProductsRepositoryWithProductsSpy!
    private var productsRepositoryWithoutProducts: ProductsRepositoryWithoutProductsSpy!
    private var productsView: ProductsViewSpy!
    private var productPresenter: ProductsPresenter!

    func testOnStartWithProducts() {
        givenAProductsRepositoryWithProducts()
        givenAProductsView()
        givenAProductsPresenterWith(repository: productsRepositoryWithProducts)
        whenTheProductsPresenterStarts()
        thenTheTitleIsDisplayed()
        thenTheProductViewShowsLoadingStatus()
        thenTryToRetrieveProduct()
        thenTheProductViewHidesLoadingStatus()
        thenTheProductsViewShowsTheProducts()
    }
    
    func testOnStartWithoutProducts() {
        givenAProductsRepositoryWithoutProducts()
        givenAProductsView()
        givenAProductsPresenterWith(repository: productsRepositoryWithoutProducts)
        whenTheProductsPresenterStarts()
        thenTheTitleIsDisplayed()
        thenTheProductViewShowsLoadingStatus()
        thenTryToRetrieveProductFromEmptyRepository()
        thenTheProductViewHidesLoadingStatus()
        thenTheProductsViewShowsAnErrorMessage()
    }
    
    func testOnProductWithDescriptionSelected() {
        givenAProductsRepositoryWithProducts()
        givenAProductsView()
        givenAProductsPresenterWith(repository: productsRepositoryWithProducts)
        whenAProductWithDescriptionIsSelected()
        thenTheProductDetailIsShown()
    }
    
    func testOnProductWithoutDescriptionSelected() {
        givenAProductsRepositoryWithProducts()
        givenAProductsView()
        givenAProductsPresenterWith(repository: productsRepositoryWithProducts)
        whenAProductWithoutDescriptionIsSelected()
        thenTheProductsViewShowsAnErrorMessage()
    }
    
    private func givenAProductsRepositoryWithProducts() {
        productsRepositoryWithProducts = ProductsRepositoryWithProductsSpy()
    }
    
    private func givenAProductsRepositoryWithoutProducts() {
        productsRepositoryWithoutProducts = ProductsRepositoryWithoutProductsSpy()
    }
    
    private func givenAProductsView() {
        productsView = ProductsViewSpy()
    }
    
    private func givenAProductsPresenterWith(repository: Repository) {
        productPresenter = ProductsPresenter(productsView: productsView, productsRepository: repository)
    }
    
    private func whenTheProductsPresenterStarts() {
        productPresenter.onStart()
    }
    
    func whenAProductWithDescriptionIsSelected() {
        productPresenter.onSelected(product: Product(name: "Car",
                                                     description: "A beautiful car",
                                                     image: "car"))
    }
    
    func whenAProductWithoutDescriptionIsSelected() {
        productPresenter.onSelected(product: Product(name: "Car",
                                                     description: "",
                                                     image: "car"))
    }
    
    private func thenTheTitleIsDisplayed() {
        XCTAssertTrue(productsView.showTitleHasBeenCalled)
    }
    
    private func thenTryToRetrieveProduct() {
        XCTAssertTrue(productsRepositoryWithProducts.getHasBeenCalled)
    }
    
    private func thenTheProductViewShowsLoadingStatus() {
        XCTAssertTrue(productsView.showLoadingStatusHasBeenCalled)
    }

    private func thenTheProductViewHidesLoadingStatus() {
        XCTAssertTrue(productsView.hideLoadingStatusHasBeenCalled)
    }

    private func thenTheProductsViewShowsTheProducts() {
        XCTAssertTrue(productsView.showProductsHasBeenCalled)
    }
    
    private func thenTryToRetrieveProductFromEmptyRepository() {
        XCTAssertTrue(productsRepositoryWithoutProducts.getHasBeenCalled)
    }
    
    private func thenTheProductsViewShowsAnErrorMessage() {
        XCTAssertTrue(productsView.showsErrorMessageHasBeenCalled)
    }
    
    private func thenTheProductDetailIsShown() {
        XCTAssertTrue(productsView.showDetailForProductHasBeenCalled)
    }
}
