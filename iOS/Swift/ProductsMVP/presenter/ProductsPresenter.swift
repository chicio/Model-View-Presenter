import Foundation

public class ProductsPresenter {
    private let productsView: ProductsView
    private let productsRepository: Repository
    
    public init(productsView: ProductsView, productsRepository: Repository) {
        self.productsView = productsView
        self.productsRepository = productsRepository
    }
    
    public func onStart() {
        productsView.show(title: "Products")
        productsView.showLoadingStatus()
        productsRepository.get { [unowned self] retrievedProducts in
            self.tryToShow(retrievedProducts: retrievedProducts)
            self.productsView.hideLoadingStatus()
        }
    }
    
    private func tryToShow(retrievedProducts: [Product]?) {
        if let products = retrievedProducts {
            productsView.show(products: products)
        } else {
            productsView.showErrorWith(message: "No products available")
        }
    }
    
    public func onSelected(product: Product) {
        if product.description != "" {
            productsView.showDetailFor(product: product)
        } else {
            productsView.showErrorWith(message: "Product without description")
        }
    }
}
