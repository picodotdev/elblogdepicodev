define(function () {
	function Cuadrado(lado) {
		this.lado = lado;
	}

	Cuadrado.prototype.calculateArea = function() {
		return this.lado * this.lado;
	}

	function Triangulo(base, altura) {
		this.base = base;
		this.altura = altura;
	}

	Triangulo.prototype.calculateArea = function() {
		return (this.base * this.altura) / 2;
	}

	return {Cuadrado:Cuadrado, Triangulo:Triangulo};
});
