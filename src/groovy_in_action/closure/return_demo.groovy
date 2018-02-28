package groovy_in_action.closure

[1, 2, 3].collect { it * 2 }
[1, 2, 3].collect { return it * 2 }

[1, 2, 3].collect {
    if (it%2 == 0) return it * 2
    return it
}
