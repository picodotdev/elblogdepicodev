define('plantillas', ['mustache', 'text!plantillas/tareas.mustache', 'text!plantillas/tarea.mustache', 'text!plantillas/estado.mustache'], function(Mustache, tareas, tarea, estado) {
	var cache = {};
	
    function get(plantilla) {
    	if (cache[plantilla] == null) {
    		cache[plantilla] = Mustache.compile(plantilla);
    	}
    	return cache[plantilla];
    }
    
    return {
    	tareas: function() {
    		return get(tareas);
    	},
    	tarea: function() {
    		return get(tarea);
    	},
    	estado: function() {
    		return get(estado);
    	}
    }
});