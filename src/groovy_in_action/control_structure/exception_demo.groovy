package groovy_in_action.control_structure

try {
    if (Math.random() < 0.5) 1/ 0
    else null.hashCode()
} catch (ArithmeticException | NullPointerException exception) {
    println exception.class.name
}