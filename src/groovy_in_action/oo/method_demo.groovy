package groovy_in_action.oo

class ClassWithTypedAndUntypedMethods {

    static void main(args) { // Implicit public
        def some = new ClassWithTypedAndUntypedMethods()
        some.publicVoidMethod()
        assert 'hi' == some.publicUntypedMethod()
        assert 'ho' == some.publicTypedMethod()
        combinedMethod() // Calls static method of current class
    }

    void publicVoidMethod() {}

    def publicUntypedMethod() {
        return 'hi'
    }

    String publicTypedMethod() {
        return 'ho'
    }

    private static final void combinedMethod() {}

}

class ClassWithTypedAndUntypedMethodParams {
    static void main(args) {
        assert 'untyped' == method(1)
        assert 'typed' == method('whatever')
        assert 'two args' == method(1, 2)
    }

    static method(arg) {
        return 'untyped'
    }
    static method(String arg) {
        return 'typed'
    }
    static method(arg1, Number arg2) {
        return 'two args'
    }
}

class Summer {
    def sumWithDefaults(a, b, c=0) { // Explicit argument default value
        return a + b + c
    }
    def sumWithList(List args) { // Defines arguments as List
        return args.inject(0) { sum, item -> sum += item }
    }
    def sumWithOptionals(a, b, Object[] optionals) { // Optional arguments as array
        return a + b + sumWithList(optionals.toList())
    }
    def sumNamed(Map args) { // Defines arguments as map
        ['a', 'b', 'c'].each { args.get(it, 0) }
        return args.a + args.b + args.c
    }
}
def summer = new Summer()

assert 2 == summer.sumWithDefaults(1, 1)
assert 3 == summer.sumWithDefaults(1, 1, 1)

assert 2 == summer.sumWithList([ 1, 1 ])
assert 3 == summer.sumWithList([ 1, 1, 1 ])

assert 2 == summer.sumWithOptionals(1, 1)
assert 3 == summer.sumWithOptionals(1, 1, 1)

assert 2 == summer.sumNamed(a: 1, b: 1)
assert 3 == summer.sumNamed(a: 1, b: 1, c: 1)
assert 1 == summer.sumNamed(c: 1)