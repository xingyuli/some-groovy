package groovy_in_action._16_integrating.shell

def shell = new GroovyShell()
def result = shell.evaluate('12 + 23')
assert result == 35
