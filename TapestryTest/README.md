# Pruebas unitarias, de integración y funcionales con Apache Tapestry

Este proyecto contiene la configuración necesaria de gradle para disponer de la infraestructura
necesaria para hacer pruebas unitarias, de integración y funcionales de un proyecto que use el
framework [Apache Tapestry](http://tapestry.apache.org/).

Las pruebas unitarias de los componentes y páginas de Apache Tapestry pueden realizarse de varias
formas. Dado que los componentes y páginas son POJOs puede probarse el código Java de los mismos
con teses de JUnit e inyectando los objetos con los que trabaje el componente pudiendo crearse los
mocks necesarios creados con la librería [Mockito](https://code.google.com/p/mockito/). La prueba
HolaMundoTest muestra este caso. Para facilitar la búsqueda de elementos en el objeto 
[Document](http://tapestry.apache.org/current/apidocs/org/apache/tapestry5/dom/Document.html) devuelto
por el método [PageTester.renderPage](http://tapestry.apache.org/current/apidocs/org/apache/tapestry5/test/PageTester.html#renderPage(java.lang.String))
se puede utilizar [tapestry-xpath](http://tapestryxpath.sourceforge.net/) que permite usar 
expresiones xpath al hacer las búsquedas.

Si además del código Java se quiere probar el código HTML generado se ha de utilizar la clase
[PageTester](http://tapestry.apache.org/current/apidocs/org/apache/tapestry5/test/PageTester.html).
La prueba HolaMundoWithTesterTest muestra este caso usando
[tapestry-testify](http://tapestrytestify.sourceforge.net/).

Si queremos hacer pruebas de integración o funcionales se puede utilizar
[Geb](http://www.gebish.org/testing) combinado con [Spock](https://code.google.com/p/spock/)
y usando el [plugin para gradle del servidor de tomcat](https://github.com/bmuschko/gradle-tomcat-plugin)
que permite arrancar un servidor tomcat embebido con la aplicación contra el que se
ejecutarán las pruebas.

## Comandos

Para ejecutar los teses unitarios:

./gradlew test

Para ejecutar los teses de integración y funcionales:

./gradlew integrationTest

Para ejecutar la aplicación ejecutar:

./gradlew tomcatRun

...y abrir [http://localhost:8080/TapestryTest/Index](http://localhost:8080/TapestryTest/Index)
en el navegador.