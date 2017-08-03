import XCTest

@testable import ProductsMVP

class ProductsRepositoryTests: XCTestCase {
    private var productsRepository: ProductsRepository!
    
    func testProductsRetrieved() {
        givenAProductsRepository()
        whenTheRepositoryTryToRetrieveProducts { [unowned self] products, repositoryExpectation in
            self.thenTheProductsListIsRetrieved(products: products,
                                                expectation: repositoryExpectation)
        }
        thenTheRepositoryFinishedToTryToRetrieve()
    }
    
    private func givenAProductsRepository() {
        productsRepository = ProductsRepository()
    }
    
    func whenTheRepositoryTryToRetrieveProducts(finish: @escaping ([Product]?, XCTestExpectation) -> Void) {
        let repositoryExpectation = expectation(description: "RepositoryExpectation")
        productsRepository.get { products in
            finish(products, repositoryExpectation)
        }
    }
    
    private func thenTheProductsListIsRetrieved(products: [Product]?, expectation: XCTestExpectation) {
        XCTAssertNotNil(products)
        XCTAssertTrue(products!.count > 0)
        expectation.fulfill()
    }
    
    private func thenTheRepositoryFinishedToTryToRetrieve() {
        waitForExpectations(timeout: 10) { error in
            if let error = error {
                XCTFail("Repository finish() not called: \(error)")
            }
        }
    }
}
