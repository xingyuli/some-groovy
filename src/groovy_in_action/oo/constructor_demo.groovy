package groovy_in_action.oo

import groovy.transform.ToString

/* Positional parameters */

@ToString(includeNames = true)
class VendorWithCtor {
    String name, product

    VendorWithCtor(name, product) { // Constructor definition
        this.name = name
        this.product = product
    }
}

def first = new VendorWithCtor('Canoo', 'ULC') // Normal constructor use

def second = ['Canoo', 'ULC'] as VendorWithCtor // Coercion with `as`

VendorWithCtor third = ['Canoo', 'ULC'] // Coercion in assignment, called implicit construction

println(first)
println(second)
println(third)


/* Named parameters */

@ToString(includeNames = true)
class SimpleVendor {
    String name, product
}

println(new SimpleVendor())
println(new SimpleVendor(name: 'Canoo'))
println(new SimpleVendor(product: 'ULC'))
println(new SimpleVendor(name: 'Canoo', product: 'ULC'))

def vendor = new SimpleVendor(name: 'Canoo')
assert vendor.name == 'Canoo'
