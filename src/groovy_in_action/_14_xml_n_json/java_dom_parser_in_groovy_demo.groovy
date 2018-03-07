package groovy_in_action._14_xml_n_json

import groovy.xml.DOMBuilder
import groovy.xml.dom.DOMCategory

def doc = DOMBuilder.parse(new FileReader('data/plan.xml'))
def plan = doc.documentElement
use(DOMCategory) {
    // Accessing node name
    assert plan.name() == 'plan'
    assert plan.week[0].name() == 'week'

    // Accessing node attribute
    assert plan.week[0].'@capacity' == '8'

    assert plan.week.task[0].name() == 'task'

    // Accessing node text
    assert plan.week.task[0].text() == 'easy'
}