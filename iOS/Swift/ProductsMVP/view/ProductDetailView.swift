import Foundation

public protocol ProductDetailView {
    func show(product: Product)
    func showErrorWith(message: String)
}
