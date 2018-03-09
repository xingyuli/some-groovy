package groovy_in_action._16_integratings.spring

class Circle implements Shape {
    Long radius
    String color = "Blue"
    Circle(Long radius) {
        this.radius = radius
    }
}
