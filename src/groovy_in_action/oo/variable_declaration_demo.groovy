package groovy_in_action.oo

import groovy.test.GroovyAssert

class ClassWithTypedAndUntypedFieldsAndProperties {

    public fieldWithModifier
    String typedField
    def untypedField
    protected field1, field2, field3
    private assignedField = new Date()

    static classField
    public static final String CONSTA = 'a', CONSTB = 'b'

    def someMethod() {
        def localUntypedMethodVar = 1
        int localTypedMethodVar = 1
        def localVarWithoutAssignment, andAnotherOne
    }

}

def localvar = 1
boundvar1 = 1

def someMethod() {
    def localMethodVar = 1
    boundvar2 = 1
}

someMethod()

final String PI = '3.14'
assert PI.class.name == 'java.lang.String'
assert PI.size() == 4
GroovyAssert.shouldFail(ClassCastException) {
    Float areaOfCircleRadiusOne = PI
}
