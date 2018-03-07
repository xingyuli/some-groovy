package groovy_in_action._14_xml_n_json

import javax.xml.stream.XMLInputFactory
import javax.xml.stream.XMLStreamReader

def input = 'file:data/plan.xml'.toURL()
def underway = []
def upcoming = []

def eachStartElement(inputStream, Closure yield) {
    // Declares parser
    def token = XMLInputFactory.newInstance().createXMLStreamReader(inputStream)
    try {
        while (token.hasNext()) { // Loops through events of interest
            if (token.startElement) yield token
            token.next()
        }
    } finally {
        token?.close()
        inputStream?.close()
    }
}

// Defines category for simple attribute access
class XMLStreamCategory {
    static Object get(XMLStreamReader self, String key) {
        return self.getAttributeValue(null, key)
    }
}

// Uses category
use (XMLStreamCategory) {
    eachStartElement(input.openStream()) { element ->
        if (element.name.toString() != 'task') return
        switch (element.done) {
            case '0' :
                upcoming << element.title
                break
            case { it != element.total } :
                underway << element.title
                break
        }
    }
}

assert underway == [
    'use in current project'
]
assert upcoming == [
    're-read DB chapter',
    'use DB/XML combination'
]
