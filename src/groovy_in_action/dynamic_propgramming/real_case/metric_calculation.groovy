package groovy_in_action.dynamic_propgramming.real_case

Number.metaClass {
    getMm = { delegate }
    getCm = { delegate * 10.mm }
    getM  = { delegate * 100.cm }
}
assert 1.m + 20.cm - 8.mm == 1.192.m