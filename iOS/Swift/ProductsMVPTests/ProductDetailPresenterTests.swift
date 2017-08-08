import XCTest

@testable import ProductsMVP

class ProductDetailPresenterTests: XCTestCase {
    private var productDetailPresenter: ProductDetailPresenter!
    private var productDetailView: ProductDetailViewSpy!
    
    func testShowDetailOfAProduct() {
        givenAProductDetailView()
        givenAProductDetailPresenterWith(product: Product(name: "aProduct",
                                                          description: "aDescription",
                                                          image: "image"))
        whenThePresenterStarts()
        thenTheTitleIsDisplayed()
        thenTheProductDetailIsShown()
    }
    
    func testShowDetailOfAnInvalidProduct() {
        givenAProductDetailView()
        givenAProductDetailPresenterWith(product: nil)
        whenThePresenterStarts()
        thenAnErrorMessageIsDisplayed()
    }
    
    private func givenAProductDetailView() {
        productDetailView = ProductDetailViewSpy()
    }
    
    private func givenAProductDetailPresenterWith(product: Product?) {
        productDetailPresenter = ProductDetailPresenter(productDetailView: productDetailView, product: product)
    }
    
    private func whenThePresenterStarts() {
        productDetailPresenter.onStart()
    }
    
    private func thenTheTitleIsDisplayed() {
        XCTAssertTrue(productDetailView.showTitleHasBeenCalled)
    }
    
    private func thenTheProductDetailIsShown() {
        XCTAssertTrue(productDetailView.showProductHasBeenCalled)
    }
    
    private func thenAnErrorMessageIsDisplayed() {
        XCTAssertTrue(productDetailView.showErrorHasBeenCalled)
    }
}
