package groovy_in_action.dynamic_propgramming.real_case

interface ChannelComponent {}
class Producer implements ChannelComponent {
    List<Integer> outChannel
}
class Adaptor implements ChannelComponent {
    List<Integer> inChannel
    List<String> outChannel
}
class Printer implements ChannelComponent {
    List<String> inChannel
}

class WiringCategory {
    static connections = []
    static setInChannel(ChannelComponent self, value) { // Intercepts assignments
        connections << [target: self, source: value]
    }
    static getOutChannel(ChannelComponent self) {
        self
    }
}

Producer producer = new Producer()
Adaptor adaptor = new Adaptor()
Printer printer = new Printer()

use WiringCategory, {
    adaptor.inChannel = producer.outChannel // Fakes assignments
    printer.inChannel = adaptor.outChannel
}

assert WiringCategory.connections == [
        [source: producer, target: adaptor],
        [source: adaptor, target: printer]
]