package groovy_in_action._14_xml_n_json

import org.xml.sax.Attributes
import org.xml.sax.InputSource
import org.xml.sax.helpers.DefaultHandler

import javax.xml.parsers.SAXParserFactory

class PlanHandler extends DefaultHandler { // Declares our handler
    def underway = []
    def upcoming = []

    // Interested in element start events
    @Override
    void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName != 'task') return // Interested only in task elements
        def title = attributes.getValue('title')
        def total = attributes.getValue('total')
        switch (attributes.getValue('done')) {
            case '0'             : upcoming << title; break
            case { it != total } : underway << title; break
        }
    }

}

def handler = new PlanHandler()
def factory = SAXParserFactory.newInstance()
def reader = factory.newSAXParser().XMLReader // Declares our SAX reader
reader.contentHandler = handler
new File('data/plan.xml').withInputStream {
    reader.parse(new InputSource(it))
}

assert handler.underway == [
    'use in current project'
]
assert handler.upcoming == [
    're-read DB chapter',
    'use DB/XML combination'
]
