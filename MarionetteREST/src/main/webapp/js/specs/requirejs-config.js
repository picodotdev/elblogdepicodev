requirejs.config({
	baseUrl: './',
	shim : {
		'underscore': {
			exports: '_'
		},
		'backbone': {
			deps: ['underscore'],
			exports: 'Backbone'
		},
        'json2': {
            exports: 'JSON'
        },
		'backbone.marionette': {
			deps: [ 'backbone' ],
			exports: 'Marionette'
		},
        'sinon': {
            exports: 'sinon'
        }
	},
	locale: 'es'
});