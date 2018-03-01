package groovy_in_action.dynamic_propgramming

assert String.metaClass =~ /MetaClassImpl/
String.metaClass.low = { -> delegate.toLowerCase() }
assert String.metaClass =~ /ExpandoMetaClass/

assert "DiErK".low() == 'dierk'


/* Modifying the metaclass of a Class (Groovy and Java) */

class MyGroovy1 {}

def before = new MyGroovy1()

MyGroovy1.metaClass.myProp = 'MyGroovy prop'
MyGroovy1.metaClass.test = { -> myProp }

try {
    before.test() // Not available
    assert false, 'should throw MME'
} catch(mme) {}

def after = new MyGroovy1()
assert after.test() == 'MyGroovy prop'


/* Modifying the metaclass of a Groovy instance */

class MyGroovy2 {}

def myGroovy = new MyGroovy2()

myGroovy.metaClass.myProp = 'MyGroovy prop'
myGroovy.metaClass.test = { -> myProp}

try {
    new MyGroovy2().test()
    assert false, 'should throw MME'
} catch(mme) {}

assert myGroovy.test() == 'MyGroovy prop'


/* Modifying the metaclass of a Java instance */

def myJava = new String()

myJava.metaClass.myProp = 'MyJava prop'
myJava.metaClass.test = { -> myProp }

try {
    new String().test()
    assert false, 'should throw MME'
} catch(mme) {}

assert myJava.test() == 'MyJava prop'


def move(string, distance) {
    string.collect { (it as char) + distance as char }.join()
}
String.metaClass {
    shift = -1
    encode { -> move delegate, shift }
    decode { -> move delegate, -shift }
    getCode { -> encode() }
    getOrig { -> decode() }
}
assert "IBM".encode() == "HAL"
assert "HAL".orig == "IBM"

def ibm = "IBM"
ibm.shift = 7
assert ibm.code == 'PIT'


/* Adding a static method to a class */

Integer.metaClass.static.answer = {-> 42}

assert Integer.answer() == 42


/* Metaclass changes for superclass and interfaces */

class MySuperGroovy {}
class MySubGroovy extends MySuperGroovy {}

MySuperGroovy.metaClass.added = {-> true }
assert new MySubGroovy().added()

Map.metaClass.toTable = {->
    delegate.collect { [it.key, it.value] }
}
assert [a:1, b:2].toTable() == [
        ['a', 1],
        ['b', 2]
]
