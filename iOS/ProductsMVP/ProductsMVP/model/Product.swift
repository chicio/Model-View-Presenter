import Foundation

public struct Product {
    let name: String
    let description: String
    let image: String
    
    public init(name: String, description: String, image: String) {
        self.name = name
        self.description = description
        self.image = image
    }
}
