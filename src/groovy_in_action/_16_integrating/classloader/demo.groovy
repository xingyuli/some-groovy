package groovy_in_action._16_integrating.classloader

def gcl = new GroovyClassLoader()
Class greetingClass = gcl.parseClass(new File('Hello.groovy'))
assert "Hello!" == greetingClass.newInstance().greeting()
