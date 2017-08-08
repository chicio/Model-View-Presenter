import UIKit

class ProductsViewController: UIViewController, UITableViewDataSource, UITableViewDelegate, ProductsView {
    @IBOutlet weak var productsTableView: UITableView!
    @IBOutlet weak var productsLoadingView: UIView!
    @IBOutlet weak var productsActivityIndicator: UIActivityIndicatorView!
    private var productsList: [Product]!
    private var productSelected: Product!
    private var productsRepository: ProductsRepository!
    private var productsPresenter: ProductsPresenter!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        initializeDependencies()
        productsPresenter.onStart()
    }
    
    private func initializeDependencies() {
        productsList = []
        productsRepository = ProductsRepository()
        productsPresenter = ProductsPresenter(productsView: self, productsRepository: productsRepository)
    }
    
    //MARK: ProductsView
    
    public func show(title aTitle: String) {
        title = aTitle
    }

    public func showLoadingStatus() {
        productsActivityIndicator.startAnimating()
        productsLoadingView.isHidden = false
    }
    
    public func hideLoadingStatus() {
        productsActivityIndicator.stopAnimating()
        productsLoadingView.isHidden = true
    }
    
    public func show(products: [Product]) {
        productsList = products
        productsTableView.reloadData()
    }
    
    public func showErrorWith(message: String) {
        let alert = UIAlertController(title: "Error", message: message, preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: "Ok", style: .default, handler: { [unowned self] action in
            self.dismiss(animated: true, completion: nil)
        }))
        present(alert, animated: true, completion: nil)
    }
    
    public func showDetailFor(product: Product) {
        productSelected = product
        performSegue(withIdentifier: "ShowProductDetail", sender: nil)
    }
    
    //MARK: Segue
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if let productDetailViewController = segue.destination as? ProductDetailViewController {
            productDetailViewController.product = productSelected
        }
    }
    
    //MARK: UITableView Datasource
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return productsList.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "ProductCell", for: indexPath)
        cell.textLabel?.text = productsList[indexPath.row].name
        return cell
    }
    
    //MARK: UITableView Delegate
    
    public func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        productsPresenter.onSelected(product: productsList[indexPath.row])
    }
}
