import Foundation

public protocol Repository {
    func get(finish: @escaping ([Product]?) -> Void)
}
