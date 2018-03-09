package groovy_in_action._16_integrating.shell

import org.codehaus.groovy.control.CompilerConfiguration

abstract class BaseScript extends Script {
    def multiply(a, b) { a * b }
}

def conf = new CompilerConfiguration()
conf.scriptBaseClass = 'groovy_in_action._16_integrating.shell.BaseScript'
def shell = new GroovyShell(conf)
def value = shell.evaluate('''
    multiply(5, 6)
''')
assert value == 30


/* Using the Binding to share functions between scripts */

def binding = new Binding(multiply: { a, b -> a * b }) // Creates closure with binding
shell = new GroovyShell(binding)
// Calls closure like a normal method
value = shell.evaluate('''
    multiply(5, 6)
''')
assert value == 30
