package groovy_in_action._14_xml_n_json

import org.w3c.dom.Node

import javax.xml.parsers.DocumentBuilderFactory

def fac = DocumentBuilderFactory.newInstance()
def builder = fac.newDocumentBuilder()
def doc = builder.parse(new FileInputStream('data/plan.xml'))
def plan = doc.documentElement

String info(Node node) {
    switch (node.nodeType) {
        case Node.ELEMENT_NODE:
            return 'element: ' + node.nodeName
        case Node.ATTRIBUTE_NODE:
            return "attribute: ${node.nodeName}=${node.nodeValue}"
        case Node.TEXT_NODE:
            return 'text: ' + node.nodeValue
    }
    return 'some other type: ' + node.nodeType
}

assert info(plan) == 'element: plan'
assert plan.childNodes.length == 5 // Element and whitespace children visible

def firstWeek = plan.childNodes.find { it.nodeName == 'week' } // Object iteration method
assert info(firstWeek) == 'element: week'

def firstTask = firstWeek.childNodes.item(1) // Indexed access
assert info(firstTask) == 'element: task'

def firstTaskText = firstTask.childNodes.item(0)
assert info(firstTaskText) == 'text: easy'

def firstTaskTitle = firstTask.attributes.getNamedItem('title')
assert info(firstTaskTitle) == 'attribute: title=read XML chapter'

def firstTaskTitleText = firstTaskTitle.childNodes.item(0)
assert info(firstTaskTitleText) == 'text: read XML chapter'