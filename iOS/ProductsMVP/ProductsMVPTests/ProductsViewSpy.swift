import Foundation
import ProductsMVP

class ProductsViewSpy: ProductsView {
    private(set) var showLoadingStatusHasBeenCalled: Bool = false
    private(set) var hideLoadingStatusHasBeenCalled: Bool = false
    private(set) var showProductsHasBeenCalled: Bool = false
    private(set) var showsErrorMessageHasBeenCalled: Bool = false
    private(set) var showDetailForProductHasBeenCalled: Bool = false
    
    func showLoadingStatus() {
        showLoadingStatusHasBeenCalled = true
    }
    
    func hideLoadingStatus() {
        hideLoadingStatusHasBeenCalled = true
    }
    
    func show(products: [Product]) {
        showProductsHasBeenCalled = true
    }
    
    func showErrorWith(message: String) {
        showsErrorMessageHasBeenCalled = true
    }
    
    func showDetailFor(product: Product) {
        showDetailForProductHasBeenCalled = true
    }
}
