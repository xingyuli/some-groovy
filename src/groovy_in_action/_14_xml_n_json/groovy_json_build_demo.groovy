package groovy_in_action._14_xml_n_json

import groovy.json.JsonBuilder
import groovy.json.JsonOutput

def builder = new JsonBuilder()
builder.weeks {
    capacity '8'
    tasks([
        {
            done '0'
            total '4'
            title 'build web service'
        },
        {
            done '0'
            total '1'
            title 'build web service client'
        }
    ])
}

assert builder.toString() == '{"weeks":{"capacity":"8","tasks":[' +
        '{"done":"0","total":"4","title":"build web service"},' +
        '{"done":"0","total":"1","title":"build web service client"}' +
        ']}}'

/* Invoice example with JsonBuilder */

builder = new JsonBuilder()
builder {
    invoices(1..3) { day ->
        invoice(date: "2015-01-0$day") {
            item(count: day) {
                product(name: 'ULC', dollar: 1499)
            }
        }
    }
}
println builder.toPrettyString()

/* Athlete example with JsonOutput */

def json = JsonOutput.toJson([date: '2015-01-01', time: '6 am'])
assert json == '{"date":"2015-01-01","time":"6 am"}'

class Athlete { String first, last }

def mj = new Athlete(first: 'Michael', last: 'Jordan')
assert JsonOutput.toJson(mj) == '{"first":"Michael","last":"Jordan"}'

def pt = new Athlete(first: 'Paul', last: 'Tergat')
def athletes = [basketball: mj, marathon: pt]

json = JsonOutput.toJson(athletes)
assert JsonOutput.prettyPrint(json) == '''
{
    "basketball": {
        "first": "Michael",
        "last": "Jordan"
    },
    "marathon": {
        "first": "Paul",
        "last": "Tergat"
    }
}
'''.trim()
