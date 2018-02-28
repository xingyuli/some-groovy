package groovy_in_action.basic

import groovy.transform.Immutable

Book gina = new Book('Groovy in Action')

assert gina.getTitle() == 'Groovy in Action'
assert getTitleBackwards(gina) == 'noitcA ni yvoorG'

String getTitleBackwards(book) {
    String title = book.getTitle()
    return title.reverse()
}

class BookBean {
    String title
}

// println new BookBean(title: 'abc').title

def groovyBook = new BookBean()
// property use with explicit accessors
groovyBook.setTitle('Groovy in Action')
assert groovyBook.getTitle() == 'Groovy in Action'

// property use with Groovy shortcuts
groovyBook.title = 'Groovy conquers the world'
assert groovyBook.title == 'Groovy conquers the world'

@Immutable class FixedBook {
    String title
}
// Positional constructor
def foo = new FixedBook('Groovy in Action')
// Named-arg constructor
def bar = new FixedBook(title: 'Groovy in Action')
assert foo.title == 'Groovy in Action'
// Standard equals
assert foo == bar

try {
    // not allowed
    foo.title = 'Oops!'
    assert false, 'should not reach here'
} catch (ReadOnlyPropertyException expected) {
    println "Expected Error: '$expected.message'"
}

assert '12345' =~ /\d+/
assert 'xxxxx' == '12345'.replaceAll(/\d/, 'x')

// List of Roman numerals
def roman = [ '', 'I', 'II', 'III', 'IV', 'V', 'VI', 'VII' ]
// List access
assert roman[4] == 'IV'
// List expansion
roman[8] ='VIII'
assert roman.size() == 9

// Maps
def http = [
        100: 'CONTINUE',
        200: 'OK',
        400: 'BAD REQUEST'
]
assert http[200] == 'OK'
http[500] = 'INTERNAL SERVER ERROR'
assert http.size() == 4


// Ranges
def x = 1..10
assert x.contains(5)
assert !x.contains(15)
assert x.size() == 10
assert x.from == 1
assert x.to == 10
assert x.reverse() == 10..1

def totalClinks = 0
def partyPeople = 100
1.upto(partyPeople) { guestNumber ->
    clinksWithGuest = guestNumber - 1
    totalClinks += clinksWithGuest
}
assert totalClinks == (partyPeople * (partyPeople - 1)) / 2
