import Foundation

public class ProductDetailPresenter {
    private let productDetailView: ProductDetailView
    private let product: Product?
    
    public init(productDetailView: ProductDetailView, product: Product?) {
        self.productDetailView = productDetailView
        self.product = product
    }
    
    public func onStart() {
        if let product = product {
            productDetailView.show(product: product)
        } else {
            productDetailView.showErrorWith(message: "Product not valid")
        }
    }
}
