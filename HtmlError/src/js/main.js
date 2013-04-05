require(['log4javascript'], function(log4j) {
	var log4j = log4javascript;

	var ajaxAppender = new log4j.AjaxAppender('http://localhost:8080/html-error/ajaxAppender');
	var ajaxLayout = new log4j.PatternLayout('%d{HH:mm:ss} %-5p %c %m%n');
	ajaxAppender.setLayout(ajaxLayout);
	ajaxAppender.setThreshold(log4j.Level.INFO);

	log4j.setEnabled(true);

	var log = log4j.getLogger('es.com.blogspot.elblogdepicodev.log4javascript');

	log.addAppender(ajaxAppender);

	// Capturador de errores javascript
	window.onerror = function(message, url, linenumber) {
		log.error(message + ", " + url + ", " + linenumber);
	}

	// ¡Atención!: La siguiente linea de código va a producir un error
	error;
});
