package groovy_in_action.oo

class Invoice {
    List items
    Date date
}
class LineItem {
    Product product
    int count
    int total() {
        return product.dollar * count
    }
}
class Product {
    String name
    def dollar
}

def ulcDate = Date.parse('yyyy-MM-dd', '2015-01-01')
def otherDate = Date.parse('yyyy-MM-dd', '2015-02-02')
def ulc = new Product(dollar: 1499, name: 'ULC')
def ve = new Product(dollar: 499, name: 'Visual Editor')

def invoices = [
    new Invoice(date: ulcDate, items: [
        new LineItem(count: 5, product: ulc),
        new LineItem(count: 1, product: ve)
    ]),
    new Invoice(date: otherDate, items: [
        new LineItem(count: 4, product: ve)
    ])
]

def allItems = invoices.items.flatten()

// Total for each line item
assert [5*1499, 499, 4*499] == allItems*.total()

// Query of product names
assert ['ULC'] == allItems.grep{ it.total() > 7000 }.product.name

// Query of invoice date
def searchDates = invoices.grep{
    it.items.any{ it.product == ulc }
}.date*.toString()
assert [ulcDate.toString()] == searchDates
