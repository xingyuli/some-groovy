package groovy_in_action.closure

class Mother {
    def prop = 'prop'
    def method() { 'method' }
    Closure birth(param) { // Creation method
        def local = 'local'
        def closure = {
            [ this, prop, method(), local, param ]
        }
        return closure
    }
}

Mother julia = new Mother()
def closure = julia.birth('param') // Closure declaration time

def context = closure.call() // Closure execution time

assert context[0] == julia // What "this" refers to
assert context[1, 2] == [ 'prop', 'method' ] // Free variables, resolved
assert context[3, 4] == [ 'local', 'param' ] // Bound variables

// Read only
assert closure.thisObject == julia // While the thisObject may change over the lifetime of a closure, the owner never does.
assert closure.owner == julia

// Scope control
assert closure.delegate == julia
assert closure.resolveStrategy == Closure.OWNER_FIRST


def map = [:]
map.with { // delegate is now map
    a = 1 // same as map.a = 1
    b = 2 // same as map.b = 2
}
assert map == [a:1, b:2]

def foo(n) {
    return { n += it }
}
def accumulator = foo(1)
assert accumulator(2) == 3
assert accumulator(1) == 4
