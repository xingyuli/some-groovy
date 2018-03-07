package groovy_in_action._15_web_service

def url = 'http://feeds.bbci.co.uk/news/rss.xml?edition=uk'

println 'The top three news items today:'
def items = new XmlParser().parse(url).channel[0].item
for (item in items[0..2]) {
    println item.title.text()
    println item.link.text()
    println item.description.text()
    println '----'
}
