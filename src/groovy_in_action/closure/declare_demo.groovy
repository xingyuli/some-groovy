package groovy_in_action.closure

/* Using assignments for declaration */


def printer = { line -> println line }


/* Referring to methods as closures */

class SizeFilter {
    Integer limit

    boolean sizeUpTo(String value) {
        return value.size() <= limit
    }
}

SizeFilter filter6 = new SizeFilter(limit: 6) // GroovyBean constructor calls
SizeFilter filter5 = new SizeFilter(limit: 5)

Closure sizeUpTo6 = filter6.&sizeUpTo // Method closure assignment

def words = ['long string', 'medium', 'short', 'tiny']

assert 'medium' == words.find(sizeUpTo6) // Calling with closure
assert 'short' == words.find(filter5.&sizeUpTo) // Passing a method closure directly


/* Multimethod, also known as runtime overload resolution, closures */

class MultiMethodSample {

    int mysteryMethod(String value) {
        return value.size()
    }

    int mysteryMethod(List list) {
        return list.size()
    }

    int mysteryMethod(int x, int y) {
        return x + y
    }

}

MultiMethodSample instance = new MultiMethodSample()
Closure multi = instance.&mysteryMethod // Only a single closure is created

// Different implementations are called based on argument types
assert 10 == multi('string arg')
assert 3 == multi(['list', 'of', 'values'])
assert 14 == multi(6, 8)


Map map = [a:1, b:2]
map.each { key, value -> map[key] = value * 2 } // Parameter sequence with commas
assert map == [a:2, b:4]

Closure doubler = { key, value -> map[key] = value * 2 } // Assigns and then calls a closure reference
map.each(doubler)
assert map == [a:4, b:8]

def doubleMethod(entry) { // Usual method declaration
    entry.value = entry.value * 2
}
doubler = this.&doubleMethod // References and calls a method as a closure
map.each(doubler)
assert map == [a:8, b:16]
