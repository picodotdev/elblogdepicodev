function Reloj(spec) {
	this.id = spec.id;
	
	var sthis = this;
	
	var f = function(pe) {
        $(sthis.id).update(new Date().toString());
    };
	
    new PeriodicalExecuter(f, 1);
}

Tapestry.Initializer.reloj = function(spec) {
	new Reloj(spec);
}