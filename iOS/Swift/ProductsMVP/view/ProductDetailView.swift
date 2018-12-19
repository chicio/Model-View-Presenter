import Foundation

public protocol ProductDetailView: class {
    func show(title aTitle: String)
    func show(product: Product)
    func showErrorWith(message: String)
}
