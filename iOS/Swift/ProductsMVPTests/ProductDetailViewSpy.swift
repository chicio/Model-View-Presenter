import Foundation
import ProductsMVP

class ProductDetailViewSpy: ProductDetailView {
    private(set) var showTitleHasBeenCalled: Bool = false
    private(set) var showProductHasBeenCalled: Bool = false
    private(set) var showErrorHasBeenCalled: Bool = false
    
    func show(title aTitle: String) {
        showTitleHasBeenCalled = true
    }
    
    func show(product: Product) {
        showProductHasBeenCalled = true
    }
    
    func showErrorWith(message: String) {
        showErrorHasBeenCalled = true
    }
}
