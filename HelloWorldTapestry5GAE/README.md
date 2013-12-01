HelloWorldTapestry5GAE
======================

Aplicación ejemplo Hola mundo con Apache Tapestry 5 (5.3.4) para el Google App Engine

Comandos
--------

+ Para compilar la aplicación:

$ ./gradlew clean copyAll

+ Para probar en local:

$ $GAE_HOME/bin/dev_appserver.sh src/main/webapp/

+ Para subir la aplicación a Google App Engine:

$ $GAE_HOME/bin/appcfg.sh update src/main/webapp/

Notas
-----

+ Tapestry file encoding google app engine:

[TAP5-1778](https://issues.apache.org/jira/browse/TAP5-1778)
[appconfig](http://code.google.com/intl/es-ES/appengine/docs/java/config/appconfig.html)
