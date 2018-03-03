package groovy_in_action._12_gdk

/***********************/
/* Interactive objects */
/***********************/

/* Using dump and inspect */

def newline = "\n"

assert newline.toString() == "\n"

assert newline.dump() == '''<java.lang.String@a value=
 hash=10>'''

assert newline.inspect() == /'\n'/

/* Reflecting on properties */

class MyClass {
    def first = 1                 // read-write property
    def getSecond() { first * 2 } // read-only property
    public third = 3              // public field
    def myMethod() {}             // public method
}
def obj = new MyClass()

assert obj.hasProperty('first') // Property check
assert obj.respondsTo('myMethod') // Method check

def keys = ['first', 'second', 'class']
assert obj.properties.keySet() == new HashSet(keys)

// Properties map
assert 1 == obj.properties['first']
assert 1 == obj.properties.first

// Direct access
assert 1 == obj.first
assert 1 == obj['first'] // getAt('first)

def one = 'first'
def two = 'second'
obj[one] = obj[two] // Dynamic assignment
assert obj.dump() =~ 'first=2' // Field introspection


/*****************************/
/* Convenient Object methods */
/*****************************/

new Date().with {
    println "$date.$month.$year"
}

text = """
This text appears
slowly on the screen
as if someone was
typing it. 
"""
for (c in text) {
    sleep 100
    print c
}
