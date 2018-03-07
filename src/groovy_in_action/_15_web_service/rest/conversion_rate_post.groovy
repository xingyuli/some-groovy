package groovy_in_action._15_web_service.rest

import groovyx.net.http.ContentType
@Grab('org.codehaus.groovy.modules.http-builder:http-builder:0.7.2')
import groovyx.net.http.RESTClient

def url = 'http://www.webservicex.net/CurrencyConvertor.asmx/'
def converter = new RESTClient(url)
def postBody = [FromCurrency: 'USD', ToCurrency: 'EUR']
converter.post(path: 'ConversionRate', body: postBody, requestContentType: ContentType.URLENC) { resp, data ->
    assert resp.status == 200
    assert data.name() == 'double'
    println data.text()
}