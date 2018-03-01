package groovy_in_action.dynamic_propgramming

import groovy.time.TimeCategory

/* How to use existing categories like TimeCategory and Collections */

def janFirst1970 = new Date(0)
use TimeCategory, {
    Date xmas = janFirst1970 + 1.year - 7.days
    assert xmas.month == Calendar.DECEMBER
    assert xmas.date == 25
}

use Collections, {
    def list = [0, 1, 2, 3]
    list.rotate 1
    assert list == [3, 0, 1, 2]
}


/* Running a category to marshal and unMarshal integers to/from strings */

class Marshal {
    static String marshal(Integer self) {
        self.toString()
    }
    static Integer unMarshal(String self) {
        self.toInteger()
    }
}
use Marshal, {
    assert 1.marshal() == "1"
    assert "1".unMarshal() == 1
    [Integer.MIN_VALUE, -1, 0, Integer.MAX_VALUE].each {
        assert it.marshal().unMarshal() == it
    }
}
