import Foundation
import ProductsMVP

class ProductsRepositoryWithoutProductsSpy: Repository {
    private(set) var getHasBeenCalled: Bool = false
    
    func get(finish: @escaping ([Product]?) -> Void) {
        getHasBeenCalled = true
        finish(nil)
    }
}
