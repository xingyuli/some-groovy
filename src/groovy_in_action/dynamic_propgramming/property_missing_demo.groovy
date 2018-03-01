package groovy_in_action.dynamic_propgramming

def propertyMissing(String name) {
    int result = 0
    name.each {
        result <<= 1
        if (it == 'I') result++
    }
    return result
}
println IIOI + IOI

class DynamicPretender {
    // Closure property with default logic
    Closure whatToDo = { name -> "accessed $name" }
    def propertyMissing(String name) {
        // Delegates to the closure
        whatToDo(name)
    }
}
def one = new DynamicPretender()
assert one.hello == 'accessed hello'
one.whatToDo = { name -> name.size() } // Changes hook behavior at runtime
assert one.hello == 5


/* Using getProperty to call parameterless methods without parentheses  */

class NoParens {
    def getProperty(String propertyName) {
        if (metaClass.hasProperty(this, propertyName)) {
            return metaClass.getProperty(this, propertyName)
        }
        invokeMethod(propertyName, null) // Dynamic invocation
    }
}
class PropUser extends NoParens { // Subclass for feature sharing
    boolean existingProperty = true
}
def user = new PropUser()
assert user.existingProperty
assert user.toString() == user.toString // Look, no parentheses!
