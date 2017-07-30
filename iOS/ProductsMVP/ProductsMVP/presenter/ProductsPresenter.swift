import Foundation

public class ProductsPresenter {
    private let productsView: ProductsView
    private let productsRepository: Repository
    
    public init(productsView: ProductsView, productsRepository: Repository) {
        self.productsView = productsView
        self.productsRepository = productsRepository
    }
    
    public func onStart() {
        productsView.showLoadingStatus()
        productsRepository.get { [unowned self] retrievedProducts in
            self.tryToShow(retrievedProducts: retrievedProducts)
            self.productsView.hideLoadingStatus()
        }
    }
    
    private func tryToShow(retrievedProducts: [Product]?) {
        if let products = retrievedProducts {
            self.productsView.show(products: products)
        } else {
            self.productsView.showErrorMessage()
        }
    }
}
