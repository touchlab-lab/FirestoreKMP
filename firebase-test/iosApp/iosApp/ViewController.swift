import UIKit
import firebase_test

class ViewController: UIViewController {
    override func viewDidLoad() {
        super.viewDidLoad()
        ConfigTestFirestoreKt.configTestFirestore()
//        label.text = Proxy().proxyHello()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    @IBOutlet weak var label: UILabel!
}
