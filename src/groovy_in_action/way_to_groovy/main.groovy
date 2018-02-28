package groovy_in_action.way_to_groovy

import static groovyx.gpars.GParsPool.withPool

// Listing a file: closures and i/o additions

def number = 0
new File("data.txt").eachLine { line ->
    number++
    println "$number: $line"
}

// Printing a list: collection literals and simplified property access

def classes = [ String, List, File ]
for (clazz in classes) {
    println clazz.package.name
}

println([ String, List, File ]*.package*.name)

// XML handling the groovy way: GPath with dynamic properties

def customers = new XmlSlurper().parse(new File('customers.xml'))
for (customer in customers.corporate.customer) {
    println "${customer.@name} works for ${customer.@company}"
}


def urls = [ 'http://www.groovy-lang.org', 'http://gr8conf.org/' ]*.toURL()
println withPool {
    urls.collectParallel {
        it.text.findAll(~/[Gg]roovy/).size()
    }
}
