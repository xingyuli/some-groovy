package groovy_in_action.dynamic_propgramming

import org.codehaus.groovy.runtime.InvokerHelper

InvokerHelper.invokeMethod(this, 'println', 'Hello')


/* Customizing methodMissing */

class Pretender {
    def methodMissing(String name, Object args) {
        "called $name with $args"
    }
}
def bounce = new Pretender()
assert bounce.hello('world') == 'called hello with [world]'


class MiniGorm {
    def db = []
    def methodMissing(String name, Object args) {
        db.find { it[name.toLowerCase()-'findby'] == args[0] }
    }
}
def people = new MiniGorm()
def dierk = [first: 'Dierk', last: 'Koenig']
def paul = [first: 'Paul', last: 'King']
people.db << dierk << paul

assert people.findByFirst('Dierk') == dierk
assert people.findByLast('King') == paul


/* Customizing propertyMissing */

class PropPretender {
    def propertyMissing(String name) {
        "accessed $name"
    }
}
bounce = new PropPretender()
assert bounce.hello == 'accessed hello'

