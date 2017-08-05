import Foundation
import UIKit

class ProductDetailViewController: UIViewController, ProductDetailView {
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var descriptionLabel: UILabel!
    @IBOutlet weak var imageView: UIImageView!
    var product: Product!
    private var productDetailPresenter: ProductDetailPresenter!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        initializeDependencies()
        productDetailPresenter.onStart()
    }
    
    private func initializeDependencies() {
        productDetailPresenter = ProductDetailPresenter(productDetailView: self, product: product)
    }
    
    //MARK: ProductDetailView
    
    func show(product: Product) {
        nameLabel.text = product.name
        descriptionLabel.text = product.description
        imageView.image = UIImage(named: product.image)
    }
    
    func showErrorWith(message: String) {
        let alert = UIAlertController(title: "Error", message: message, preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: "Ok", style: .default, handler: { [unowned self] action in
            self.dismiss(animated: true, completion: nil)
        }))
        present(alert, animated: true, completion: nil)
    }
}
