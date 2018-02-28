package groovy_in_action.closure

def adder = { x, y -> return x + y }

assert adder(4, 3) == 7
assert adder.call(2, 6) == 8

/* Calling a closure */

def benchmark(int repeat, Closure worker) { // Puts closures last
    // Some prework
    def start = System.nanoTime()

    // Calls closure the the given number of times
    repeat.times(worker)

    // Some postwork
    def stop = System.nanoTime()
    return stop - start
}

// Passes different closures for analysis
def slow = benchmark(10000) { (int) it / 2 }
def fast = benchmark(10000) { it.intdiv(2) }
assert fast * 2 < slow


def adder2 = { x, y=5 -> return x+y }
assert adder2(4, 3) == 7
assert adder2.call(7) == 12


/* Reacting on the parameter count or type */

def numParams(Closure closure) {
    closure.getMaximumNumberOfParameters()
}
assert numParams { one -> } == 1
assert numParams { one, two ->  } == 2

def paramTypes(Closure closure) {
    closure.getParameterTypes()
}
assert paramTypes { String s -> } == [String]
assert paramTypes { Number n, Date d -> } == [Number, Date]


/* Curring */

def mult = { x, y -> return x * y }
def twoTimes = mult.curry(2)
assert twoTimes(5) == 10


/* Closure composition */

def fourTimes = twoTimes >> twoTimes
def eightTimes = twoTimes << fourTimes
assert eightTimes(1) == twoTimes(fourTimes(1))


/* Memoization */

def fib
fib = { it < 2 ? 1 : fib(it-1) + fib(it-2) }
fib = fib.memoize()
assert fib(40) == 165_580_141


/* Classification via the isCase method */

def odd = { it % 2 == 1 }

assert [1,2,3].grep(odd) == [1,3]

switch (10) {
    case odd: assert false
}

if (2 in odd) assert false

odd.asWritable()
