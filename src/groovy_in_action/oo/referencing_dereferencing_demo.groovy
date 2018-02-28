package groovy_in_action.oo

class Counter {
    public count = 0
}
def counter = new Counter()

counter.count = 1
assert counter.count == 1

def fieldName = 'count'
counter[fieldName] = 2
assert counter[fieldName] == 2

class PretendFieldCounter {
    public count = 0

    Object get(String name) {
        return 'pretend value'
    }
    void set(String name, Object value) {
        count++
    }
}
def pretender = new PretendFieldCounter()
assert pretender.isNoField == 'pretend value'
assert pretender.count == 0

pretender.isNoFieldEither = 'just to increase counter'
assert pretender.count == 1
