package groovy_in_action._16_integrating.shell

def shell = new GroovyShell()
// Defines a new class
def clazz = shell.evaluate('''
    class MyClass {
        def method() { "value" }
    }
    return MyClass
''')
assert clazz.name == 'MyClass'

// Creates an instance of the class
def instance = clazz.newInstance()
// Uses the object as normal
assert instance.method() == 'value'
