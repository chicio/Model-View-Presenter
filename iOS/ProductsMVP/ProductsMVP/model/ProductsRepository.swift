import Foundation

public class ProductsRepository: Repository {
    
    public func get(finish: @escaping ([Product]?) -> Void) {
        DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(3000)) {
            let products = [
                Product(name: "Car", description: "A beautiful car", image: "car"),
                Product(name: "Book", description: "", image: "book")
            ]
            finish(products)
        }
    }
}
