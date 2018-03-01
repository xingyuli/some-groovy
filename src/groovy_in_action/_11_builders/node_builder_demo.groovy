package groovy_in_action._11_builders

def builder = new NodeBuilder() // Builder creation
def ulcDate = Date.parse('yyyy-MM-dd', '2015-01-01')
def otherDate = Date.parse('yyyy-MM-dd', '2015-02-02')
def invoices = builder.invoices { // Root node creation
    invoice(date: ulcDate) { // Invoice creation
        item(count: 5) {
            product(name: 'ULC', dollar: 1499)
        }
        item(count: 1) {
            product(name: 'Visual Editor', dollar: 499)
        }
    }
    invoice(date: otherDate) {
        item(count: 4) {
            product(name: 'Visual Editor', dollar: 499)
        }
    }
}

soldAt = invoices.grep {
    it.item.product.any { it.'@name' == 'ULC' } // GPath query
}.'@date'
assert soldAt == [ulcDate]


/* Using logic inside the NodeBuilder */

TimeZone.default = TimeZone.getTimeZone('CET') // Sets TimeZone for consistent Date toString() values in test

builder = new NodeBuilder()
invoices = builder.invoices {
    for (day in 1..3) {
        def invDate = Date.parse('yyyy-MM-dd', "2015-01-0$day") // Loops over three days
        invoice(date: invDate) { // Code for building a single invoice
            item(count: day) {
                product(name: 'ULC', dollar: 1499)
            }
        }
    }
}

def writer = new StringWriter()
invoices.print(new PrintWriter(writer)) // Prints to a StringWriter for testing

assert writer.toString() == """\
invoices() {
  invoice(date:Thu Jan 01 00:00:00 CET 2015) {
    item(count:1) {
      product(name:'ULC', dollar:1499)
    }
  }
  invoice(date:Fri Jan 02 00:00:00 CET 2015) {
    item(count:2) {
      product(name:'ULC', dollar:1499)
    }
  }
  invoice(date:Sat Jan 03 00:00:00 CET 2015) {
    item(count:3) {
      product(name:'ULC', dollar:1499)
    }
  }
}
"""