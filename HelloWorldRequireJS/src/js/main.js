require(['figuras'], function(figuras) {
	var c = new figuras.Cuadrado(4);
	var t = new figuras.Triangulo(4, 3);

	alert('El área de un cuadrado de lado ' + c.lado + ' es ' + c.calculateArea());
	alert('El área de un triágulo de base ' + t.base + ' y altura ' + t.altura + ' es ' + t.calculateArea());
});
