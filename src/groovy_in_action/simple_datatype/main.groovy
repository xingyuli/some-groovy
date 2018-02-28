package groovy_in_action.simple_datatype

import java.awt.Point
import java.awt.Rectangle

def printNext(Integer myInt) {
    println(++myInt)
}

printNext(3)

// Casting lists and maps to arbitrary classes

Point topLeft = new Point(0, 0) // classic
Point bottomRight = [ 100, 100 ] // List cast
Point center = [ x:50, y: 50 ] // Map cast

assert bottomRight instanceof Point
assert center instanceof Point

def rect = new Rectangle()
rect.location = [ 0, 0 ]
rect.size = [ width: 100, height: 100 ]
