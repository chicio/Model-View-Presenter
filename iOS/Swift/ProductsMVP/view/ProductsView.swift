import Foundation

public protocol ProductsView {
    func showLoadingStatus()
    func hideLoadingStatus()
    func show(title aTitle: String)
    func show(products: [Product])
    func showErrorWith(message: String)
    func showDetailFor(product: Product)
}
