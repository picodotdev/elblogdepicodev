define('tareas', ['jquery', 'underscore', 'backbone', 'mustache'], function($, _, Backbone, Mustache) {
	function trim(string) {
		return string.replace(/^\s+|\s+$/gi, '');
	}
	
	function render(plantilla, vista) {
		render.plantillas = render.plantillas || {};
		
		var p = render.plantillas[plantilla];
		if (p == null) {
			var texto = $(plantilla).text();
			texto = _.unescape(texto);
			
			p = Mustache.compile(texto);
			render.plantillas[plantilla] = p;
		}	
		return p(vista);
	}	
	
	var Tarea = Backbone.Model.extend({
		urlRoot : 'rest/tareas/tarea',
		defaults: {
			id: null,
			descripcion: '',
			completada: false
		},
		toPlantilla: function() {
			var json = this.toJSON();
			json.attrs = {
				checked: (this.get('completada')?'checked':null),
				completada: (this.get('completada')?'completada':null)
			};
		    return json;			
		}
	});
	
	var Tareas = Backbone.Collection.extend({
		url: 'rest/tareas',
		model: Tarea,
		findCompletadas: function() {
			return this.models.filter(function(tarea) {
				return tarea.get('completada');
			});
		},
		removeCompletadas: function() {
			_.each(this.findCompletadas(), function(tarea) {
				tarea.destroy();
			});
		}
	});
	
	var TareaView = Backbone.View.extend({
		tagName: 'li',
		className: 'tarea',
		events: {
        	"change input[name='completada']": 'onChangeCompletada'
    	},
        initialize: function() {
			_.bindAll(this);
        	
			this.model.on('change', this.render);
			this.model.on('remove', this.remove);

			this.render();
        },
    	render: function() {
    		var texto = render('#tarea-template', this.model.toPlantilla());
    		$(this.el).html(texto);
    	},
    	// Eventos
    	onChangeCompletada: function() {
    		var completada = $("input[name='completada']", this.el).is(':checked');
    		this.model.set('completada', completada);
    		this.model.save();
    	}
	});
	
	var TareasApp = Backbone.View.extend({
		events: {
        	"keypress input[name='nuevaTarea']": 'onKeypressNuevaTarea',
        	"click input[name='limpiar']": 'onClickLimpiar'
    	},
        initialize: function() {
			_.bindAll(this);
        	
			this.tareas = new Tareas();			
			this.tareas.on('add', this.render);
			this.tareas.on('remove', this.render);
			this.tareas.on('change', this.render);
			this.tareas.on('reset', this.reset);

    		var texto = render('#tareas-template', null);
    		$(this.el).html(texto);
			this.render();
        },
    	render: function() {
    		var completadas = this.tareas.findCompletadas().length; 
    		var total = this.tareas.length;
    		
    		// Habilitar/deshabilitar el botón de limpiar tareas completadas
    		if (completadas == 0) {
    			$("input[name='limpiar']", this.el).attr('disabled', 'disabled');
    		} else {
    			$("input[name='limpiar']", this.el).removeAttr('disabled');
    		}
    		
    		// Cambiar el mensaje de estado de las tareas
    		var texto = render('#estado-template', {completadas: completadas, total: total});
    		$('#estado', this.el).html(texto);
    	},
        reset: function() {
        	this.tareas.each(function(o) {
	    		var vista = new TareaView({model: o});
	    		$('#lista-tareas', this.el).append(vista.el);
			}, this);
        	this.render();
        },
    	// Métodos
    	addTarea: function(tarea) {
    		tarea.save();
    		this.tareas.add(tarea);
    		
    		var vista = new TareaView({model: tarea});
    		$('#lista-tareas', this.el).append(vista.el);
    	},
    	resetTareas: function(tareas) {
    		this.tareas.reset(tareas);
    	},
    	fetch: function() {
    		this.tareas.fetch();
    	},
    	// Eventos
    	onKeypressNuevaTarea: function(event) {
    		// Comprobar si la tecla pulsada es el return
    		if (event.which == 13) {
    			var input = $("input[name='nuevaTarea']", this.el);
    			var descripcion = input.val();
    			descripcion = trim(descripcion);

    			// Comprobar si se ha introducido descripción de la tarea
    			if (descripcion == '') {
    				return;
    			}
    			
    			// Añadir la tarea y limpiar el input 
    			var tarea = new Tarea({ descripcion: descripcion, completada: false });
    			this.addTarea(tarea);
    			input.val('');
    		}
    	},
    	onClickLimpiar: function() {
    		this.tareas.removeCompletadas();
    	}
	});

	return { Tarea:Tarea, Tareas:Tareas, TareaView:TareaView, TareasApp:TareasApp };
});