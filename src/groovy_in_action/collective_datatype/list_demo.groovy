package groovy_in_action.collective_datatype

List myList = [1,2,3]

assert myList.size() == 3
assert myList[0] == 1
assert myList instanceof ArrayList

List emptyList = []
assert emptyList.size() == 0

List longList = (0..1000).toList()
assert longList[555] == 555

List explicitList = new ArrayList()
explicitList.addAll(myList) // Fills from myList
assert explicitList.size() == 3
explicitList[0] = 10
assert explicitList[0] == 10

explicitList = new LinkedList(myList) // Fills from myList
assert explicitList.size() == 3
explicitList[0] = 10
assert explicitList[0] == 10


/* Subscript operator: getAt & putAt */

myList = ['a', 'b', 'c', 'd', 'e', 'f']

assert myList[0..2] == ['a', 'b', 'c'] // getAt(Range)
assert myList[0,2,4] == ['a', 'c', 'e'] // getAt(collection of indexes)

myList[0..2] = ['x', 'y', 'z'] // putAt(Range)
assert myList == ['x', 'y', 'z', 'd', 'e', 'f']

myList[3..5] = [] // Removing elements
assert myList == ['x', 'y', 'z']

myList[1..1] = [0, 1, 2] // Adding elements
assert myList == ['x', 0, 1, 2, 'z']


/* Adding and removing items */

myList = []

myList += 'a' // plus(Object)
assert myList == ['a']

myList += ['b', 'c'] // plus(Collection)
assert myList == ['a', 'b', 'c']

myList = []
myList << 'a' << 'b' // leftShift is like append
assert myList == ['a', 'b']

assert myList - ['b'] == ['a'] // minus(Collection)

assert myList * 2 == ['a', 'b', 'a', 'b'] // Multiply


/* Control structures */

myList = ['a','b','c']

assert myList.isCase('c')
assert 'b' in myList

def candidate = 'c'
switch (candidate) {
    case myList: assert true; break // Classifies by containment
    default: assert false
}

assert ['x', 'a', 'z'].grep(myList) == ['a'] // Intersection filter

myList = []
if (myList) assert false // Empty lists are false

// Lists can be iterated with a 'for' loop
def expr = ''
for (i in [1, '*', 5]) { // Iterates over a list
    expr += i
}
assert expr == '1*5'


/* Manipulating list content */

// removing duplicates
def x = [1,1,1]
assert [1] == new HashSet(x).toList()
assert [1] == x.unique()

// removing null
x = [1, null, 1]
assert [1,1] == x.findAll { it != null }
assert [1,1] == x.grep { it }


// Accessing list content

def list = [1,2,3]

assert list.first() == 1
assert list.head() == 1
assert list.tail() == [2,3]
assert list.last() == 3
assert list.count(2) == 1
assert list.max() == 3
assert list.min() == 1

def even = list.find { it % 2 == 0 }
assert even == 2

def store = ''
list.each { store += it }
assert store == '123'

store = ''
list.reverseEach { store += it }
assert store == '321'

store = ''
list.eachWithIndex{ item, i -> store += "$i:$item " }
assert store == '0:1 1:2 2:3 '

assert list.join('-') == '1-2-3'

assert list.sum() == 6

def factorial = list.inject(1) { fac, item -> fac * item }
assert factorial == 1 * 1 * 2 * 3

def urls = [
    new URL('http', 'myshop.com', 80, 'index.html'),
    new URL('https', 'myshop.com', 443, 'buynow.html'),
    new URL('ftp', 'myshop.com', 21, 'downloads')
]
assert urls
    .findAll { it.port < 99 }
    .collect { it.file.toUpperCase() }
    .sort()
    .join(', ') == 'DOWNLOADS, INDEX.HTML'
