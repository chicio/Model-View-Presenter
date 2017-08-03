import Foundation
import ProductsMVP

class ProductDetailViewSpy: ProductDetailView {
    private(set) var showProductHasBeenCalled: Bool = false
    private(set) var showErrorHasBeenCalled: Bool = false
    
    func show(product: Product) {
        showProductHasBeenCalled = true
    }
    
    func showErrorWith(message: String) {
        showErrorHasBeenCalled = true
    }
}
