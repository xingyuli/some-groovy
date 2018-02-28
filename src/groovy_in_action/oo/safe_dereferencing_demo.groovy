package groovy_in_action.oo

def map = [a:[b:[c:1]]]
assert map.a.b.c == 1

if (map && map.a && map.a.x) { // Protects with if: short-circuit evaluation
    assert map.a.x.c == null
}

try {
    assert map.a.x.c == null
} catch (NullPointerException ignore) { // Protects with try-catch
}

assert map?.a?.x?.c == null // Safe de-referencing

assert (map?.a?.x?.c ?: 'default') == 'default'