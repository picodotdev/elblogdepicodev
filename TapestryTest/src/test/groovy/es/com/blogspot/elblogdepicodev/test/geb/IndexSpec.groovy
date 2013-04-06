package es.com.blogspot.elblogdepicodev.test.geb

import geb.Page
import geb.spock.GebSpec
 
class IndexPage extends Page {
    static url = 'http://localhost:8080/TapestryTest/Index'
    static at = { title.endsWith('Apache Tapestry') }
    static content = {
        id { $('#id1') }
    }
}

class IndexSpec extends GebSpec {
    def 'go to index'() {
        when:
        to IndexPage

        then:
		id.text() == 'Hola mundo Tapestry!!!'
   }
}