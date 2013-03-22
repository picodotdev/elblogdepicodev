require(['mustache'], function(Mustache) {
	function assert(bool) {
		if (!bool) {
			throw 'Exception';
		}
	}

{
	// Variable
	var datos = {
			  nombre: 'picodotdev'		
	};

	var plantilla = '¡Hola {{nombre}}!';

	var texto = Mustache.render(plantilla, datos);

	assert(texto == '¡Hola picodotdev!');
	alert(texto);
}
	
{
	// Sección sobre variable
	var datos = {
		nombre: null
	};

	var plantilla = '¡Hola{{#nombre}} {{nombre}}{{/nombre}}!';

	var texto = Mustache.render(plantilla, datos);

	assert(texto == '¡Hola!');
	alert(texto);
}

{
	// Sección sobre array
	var datos = {
		nombres: ['Luis', 'Juan', 'Pedro']
	};

	var plantilla = 'Hola:\n{{#nombres}}{{.}}\n{{/nombres}}';

	var texto = Mustache.render(plantilla, datos);

	assert(texto == 'Hola:\nLuis\nJuan\nPedro\n');
	alert(texto);
}

{
	// Parcial
	var datos = {
		nombres: ['Luis', 'Juan', 'Pedro']
	};

	var plantilla = 'Hola:\n{{#nombres}}{{>nombre}}{{/nombres}}';
	var parciales = {
		nombre: '{{.}}\n'
	};

	var texto = Mustache.render(plantilla, datos, parciales);

	assert(texto == 'Hola:\nLuis\nJuan\nPedro\n');
	alert(texto);
}	

{
	// Plantilla compilada
	var datos = {
		nombre: 'picodotdev'		
	};

	var plantilla = '¡Hola {{nombre}}!';
	var plantillac = Mustache.compile(plantilla);
	
	var texto = plantillac(datos);

	assert(texto == '¡Hola picodotdev!');
	alert(texto);
}
});
