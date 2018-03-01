package groovy_in_action._11_builders

import groovy.xml.MarkupBuilder

def builder = new MarkupBuilder()
builder.numbers {

    description 'Squares and factors of 10..15'

    for (i in 10..15) {
        number (value: i, square: i*i) { // Emit number elements 10-15
            for (j in 2..<i) {
                if (i % j == 0) {
                    factor (value: j) // Emit each factor element
                }
            }
        }
    }

}

TimeZone.default = TimeZone.getTimeZone('CET') // Sets TimeZone for consistent Date toString() values in test

def writer = new StringWriter()
builder = new MarkupBuilder(writer)
builder.invoices {
    for (day in 1..3) {
        def invDate = Date.parse('yyyy-MM-dd', "2015-01-0$day") // Loops over three days
        invoice(date: invDate) { // Code for building a single invoice
            item(count: day) {
                product(name: 'ULC', dollar: 1499)
            }
        }
    }
}

assert "\n" + writer.toString() == """
<invoices>
  <invoice date='Thu Jan 01 00:00:00 CET 2015'>
    <item count='1'>
      <product name='ULC' dollar='1499' />
    </item>
  </invoice>
  <invoice date='Fri Jan 02 00:00:00 CET 2015'>
    <item count='2'>
      <product name='ULC' dollar='1499' />
    </item>
  </invoice>
  <invoice date='Sat Jan 03 00:00:00 CET 2015'>
    <item count='3'>
      <product name='ULC' dollar='1499' />
    </item>
  </invoice>
</invoices>"""
