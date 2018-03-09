package groovy_in_action._16_integrating.shell

def monthly = "amount * (rate/12) / (1-(1+rate/12)**-numberOfMonths)"

def shell = new GroovyShell()
def script = shell.parse(monthly) // Parse formula into reusable script

script.binding.amount = 154000 // Accesses binding variable
script.rate = 3.75 / 100 // Accesses binding variable using shorthand
script.numberOfMonths = 240

assert script.run() == 913.0480050387338

// Creates new binding
script.binding = new Binding(
        amount: 185000,
        rate: 3.50/100,
        numberOfMonths: 300)

assert script.run() == 926.1536089487843
