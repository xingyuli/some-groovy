package groovy_in_action._16_integrating.shell

/* Making data available to a GroovyShell using a Binding */

// Creates and populates binding
def binding = new Binding()
binding.mass = 22.3
binding.velocity = 10.6

// Evaluates expression using binding
def shell = new GroovyShell(binding)
def expression = "mass * velocity**2 / 2"
assert shell.evaluate(expression) == 1252.814

// Changes binding data and reevaluates
binding.setVariable('mass', 25.4)
assert shell.evaluate(expression) == 1426.972


/* Data can flow out of the binding as well as into it */

// Pre-populating binding data
binding = new Binding(x: 6, y: 4)
shell = new GroovyShell(binding)
// Setting binding data within evaluated script
shell.evaluate('''
    xSquare = x * x
    yCube   = y * y * y
    
    def localVariable = "local variable"
''')
// Method access to binding data
assert binding.getVariable('xSquare') == 36
// Groovy property access to binding data
assert binding.yCube == 64

try {
    // if a variable is defined with the `def` keyword or with a type,
    // it will be a local variable
    binding.localVariable
} catch (e) {
    assert e.class.name == 'groovy.lang.MissingPropertyException'
}
