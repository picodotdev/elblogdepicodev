package es.com.blogspot.elblogdepicodev.plugintapestry.geb

import geb.Page
import geb.spock.GebSpec

    // Definición de la página índice 
class IndexPage extends Page {
    // Localización
    static url = 'http://localhost:8080/PlugInTapestry/index'
    // Determinar que se cargó una página 
    static at = { title.startsWith('PlugIn') }
    // Definición de los elementos de la página
    static content = {
        meta { $('meta[pagina]') }
    }
}

class IndexSpec extends GebSpec {
    def 'go to index'() {
        when:
        to IndexPage

        then:
		meta.@pagina == 'Index'
   }
}