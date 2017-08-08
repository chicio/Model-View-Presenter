import Foundation

public protocol ProductDetailView {
    func show(title aTitle: String)
    func show(product: Product)
    func showErrorWith(message: String)
}
