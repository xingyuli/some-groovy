package groovy_in_action.collective_datatype

// Date ranges
def today = new Date()
def yesterday = today - 1
assert (yesterday..today).size() == 2

// String ranges
assert ('a'..'c').contains('b')

def log = ''
for (element in 5..9) { // for-in-range loop
    log += element
}
assert log == '56789'

// Iterating over a range
def result = ''
(5..9).each { result += it }
assert result == '56789'

assert 5 in 0..10
assert (0..10).isCase(5)

// Ranges for classification
def age = 36
switch (age) {
    case 16..20 : println 'case1'; break
    case 21..50 : println 'case2'; break
    case 51..65 : println 'case3'; break
    default: throw new IllegalArgumentException()
}

// Filtering with ranges
def ages = [20, 36, 42, 56]
def midage = 21..50
assert ages.grep(midage) == [36, 42]
