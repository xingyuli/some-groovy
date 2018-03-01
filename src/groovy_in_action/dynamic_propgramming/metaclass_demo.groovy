package groovy_in_action.dynamic_propgramming

MetaClass mc = String.metaClass
final Object[] NO_ARGS = []
assert 1 == mc.respondsTo('toString', NO_ARGS).size()
assert 3 == mc.properties.size()
assert 76 == mc.methods.size()
assert 177 == mc.metaMethods.size()
assert '' == mc.invokeMethod('', 'toString', NO_ARGS)
assert null == mc.invokeStaticMethod(String, 'println', NO_ARGS)
assert '' == mc.invokeConstructor(NO_ARGS)


/* Setting other metaclasses */

class InspectMe {
    int outer() {
        return inner()
    }
    private int inner() {
        return 1
    }
}
// Setup
def tracer = new TracingInterceptor(writer: new StringWriter())
def proxyMetaClass = ProxyMetaClass.getInstance(InspectMe)
proxyMetaClass.interceptor = tracer

// Assigning a metaclass
InspectMe inspectMe = new InspectMe()
inspectMe.metaClass = proxyMetaClass

// Normal method call
assert 1 == inspectMe.outer()
println tracer.writer.toString()


/* use the proxy metaclass temporarily */

proxyMetaClass.use(inspectMe) {
    inspectMe.outer() // proxy in use
}
// proxy is no longer in use
