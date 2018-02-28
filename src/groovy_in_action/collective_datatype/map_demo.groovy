package groovy_in_action.collective_datatype

def myMap = [a:1, b:2, c:3]
assert myMap instanceof LinkedHashMap
assert myMap['a'] == 1

def composed = [x:'y', *:myMap] // Spread operator
assert composed == [x:'y', a:1, b:2, c:3]

// For the common case of having keys of type String, you can leave out the
// string markers (single or double quotes) in a map declaration
assert ['a':1] == [a:1]

def x = 'a'
assert ['x':1] == [x:1]
assert ['a':1] == [(x):1]

myMap = ['a.b':1]
assert myMap.'a.b' == 1


/* Changing map content and building new objects from it */

myMap = [a:1, b:2, c:3]
myMap.clear()
assert myMap.isEmpty()

myMap = [a:1, b:2, c:3]
myMap.remove('a')
assert myMap.size() == 2

assert [a:1] + [b:2] == [a:1, b:2]

myMap = [a:1, b:2, c:3]
def abMap = myMap.subMap(['a', 'b']) // Creates a view onto original map
assert abMap.size() == 2

abMap = myMap.findAll { it.value < 3 }
assert abMap.size() == 2
assert abMap.a == 1

def found = myMap.find { it.value < 2 }
assert found.key == 'a'
assert found.value == 1

def doubled = myMap.collect { it.value *= 2 }
assert doubled instanceof List
assert doubled.every { it % 2 == 0 }

def addTo = []
myMap.collect(addTo) { it.value *= 2 }
assert addTo instanceof List
assert addTo.every { it % 2 == 0 }
