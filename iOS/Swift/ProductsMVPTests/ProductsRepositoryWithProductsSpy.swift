import Foundation
import ProductsMVP

class ProductsRepositoryWithProductsSpy: Repository {
    private(set) var getHasBeenCalled: Bool = false
    
    func get(finish: @escaping ([Product]?) -> Void) {
        getHasBeenCalled = true
        let products = [
            Product(name: "Car", description: "A beautiful car", image: "car"),
            Product(name: "Book", description: "", image: "book")
        ]
        finish(products)
    }
}
