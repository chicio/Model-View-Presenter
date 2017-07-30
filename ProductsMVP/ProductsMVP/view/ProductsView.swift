import Foundation

public protocol ProductsView {
    func showLoadingStatus()
    func hideLoadingStatus()
    func show(products: [Product])
    func showErrorMessage()
}
