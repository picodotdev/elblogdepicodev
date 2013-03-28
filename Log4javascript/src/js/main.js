require(['log4javascript'], function(log4j) {
	var layout = new log4j.PatternLayout('%d{HH:mm:ss} %-5p %c %m%n');

	var popUpAppender = new log4j.PopUpAppender();
	popUpAppender.setLayout(layout);
	popUpAppender.setThreshold(log4j.Level.INFO);

	var ajaxAppender = new log4j.AjaxAppender('ajaxAppender');
	ajaxAppender.setLayout(layout);
	ajaxAppender.setThreshold(log4j.Level.ERROR);

	log4j.setEnabled(true);

	var log = log4j.getLogger('es.com.blogspot.elblogdepicodev.log4javascript');

	log.addAppender(popUpAppender);
	log.addAppender(ajaxAppender);

	log.trace("Tracing message (appears in pop-up)");
	log.debug("Debugging message (appears in pop-up)");
	log.warn("Warn message (appears in pop-up)");
	log.info("Info message (appears in pop-up)");
	log.error("Error message (appears in pop-up and in server log)");
	log.fatal("Fatal message (appears in pop-up and in server log)");
});
