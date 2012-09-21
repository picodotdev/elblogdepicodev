import spock.lang.Specification
import org.mockito.Mockito

class MockitoSpock extends Specification {
    def "procesado de elementos"() {
        setup:
        def servicio = Mockito.mock(Servicio.class)
        Mockito.when(servicio.findDatos()).thenReturn([[id:1, letra:'A'], [id:2, letra:'B']])
        
        def procesador = new Procesador(servicio)
            
        when:
        def resultado = procesador.procesar() 
    
        then:
        resultado == '1,2'
    }
}

interface Servicio {

    List findDatos()
}

class Procesador {

    private Servicio servicio

    Procesador(Servicio servicio) {
        this.servicio = servicio
    }

    String procesar() {
        def datos = servicio.findDatos()
        def ids = datos.collect {
            it.id
        }
        return ids.join(',')
    }
}
