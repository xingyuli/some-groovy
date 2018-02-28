package groovy_in_action.oo

class MyBean implements Serializable {
    def untyped
    String typed
    def item1, item2
    def assigned = 'default value'
}
def bean = new MyBean()
assert 'default value' == bean.getAssigned()
bean.setUntyped('some value')
assert 'some value' == bean.getUntyped()

bean = new MyBean(typed: 'another value')
assert 'another value' == bean.getTyped()



class MrBean {
    String firstname, lastname // Groovy style properties

    String getName() { // Getter for derived property
        return "$firstname $lastname"
    }
}
bean = new MrBean(firstname: 'Rowan')
bean.lastname = 'Atkinson'
assert 'Rowan Atkinson' == bean.name


/* GDK methods for bean properties */

class ClassWithProperties {
    def someProperty
    public someField
    private somePrivateField
}
def obj = new ClassWithProperties()
def store = []
obj.properties.each { property ->
    store += property.key
    store += property.value
}
// println store
assert store.contains('someProperty')
assert !store.contains('someField')
assert !store.contains('somePrivateField')
// The `class` property stems from Java. But tools that use Java's bean
// introspection often hide this property.
assert store.contains('class')
assert obj.properties.size() == 2


/* Expando */

def boxer = new Expando()
assert null == boxer.takeThis
boxer.takeThis = 'ouch!'
assert 'ouch!' == boxer.takeThis
boxer.fightBack = { times -> delegate.takeThis * times }
assert 'ouch!ouch!ouch!' == boxer.fightBack(3)
