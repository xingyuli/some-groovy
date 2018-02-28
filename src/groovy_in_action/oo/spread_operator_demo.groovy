package groovy_in_action.oo

def getList() {
    return [1,2,3]
}
def sum(a,b,c) {
    return a + b + c
}
assert 6 == sum(*getList())

def range = (1..3)
assert [0,1,2,3] == [0,*range]

def map = [a:1, b:2]
assert [a:1, b:2, c:3] == [c:3, *:map]
