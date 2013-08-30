define('tareas', [ 'jquery', 'underscore', 'backbone', 'backbone.marionette', 'mustache', 'plantillas', 'i18n!i18n/nls/mensajes' ], function($, _, Backbone, Marionette, Mustache, plantillas, mensajes) {
	function trim(string) {
		return string.replace(/^\s+|\s+$/gi, '');
	}

	function render(plantilla, datos, mensajes) {
		var d = datos || {};
		var m = mensajes || {};
		
		var vista = _.extend(d, {
			message: m
		});
		
		var f = plantillas[plantilla];
		var p = f();
		return p(vista);
	}

	var Tarea = Backbone.Model.extend({
		urlRoot : 'rest/tareas/tarea',
		defaults : {
			id : null,
			descripcion : '',
			completada : false
		},
		toJSON : function() {
			return {
				id : this.get('id'),
				descripcion : this.get('descripcion'),
				completada : this.get('completada')
			};
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

	var TareaView = Marionette.ItemView.extend({
		tagName: 'li',
		ui: {
			checkbox: "input[name='completada']"
		},
		modelEvents: {
			'change': 'render'
		},
		events: {
			"change input[name='completada']" : 'onChangeCompletada'
		},
		serializeData: function() {
			var completada = this.model.get('completada');

			var data = this.model.toJSON();
			_.extend(data, {
				attrs: {
					checked : (completada) ? 'checked="checked"' : null,
					completada : (completada) ? 'completada' : null
				}
			});
			return data;
		},
		template: function(data) {
			return render('tarea', data);
		},
		// Eventos
		onChangeCompletada: function() {
			var completada = this.ui.checkbox.is(':checked');
			this.model.set('completada', completada);
			this.model.save();
		}
	});

	var TareasView = Marionette.CollectionView.extend({
		tagName: 'ul',
		className: 'tareas',
		itemView: TareaView
	});

	var EstadoView = Marionette.ItemView.extend({
		collectionEvents : {
			'all': 'render'
		},
		serializeData: function() {
			var completadas = this.options.collection.findCompletadas().length;
			var total = this.collection.length;

			return {
				completadas : completadas,
				total : total
			};
		},
		template: function(data) {
			var m = {
				'COMPLETADAS_tareas_de_TOTAL_completadas': Mustache.render(mensajes.COMPLETADAS_tareas_de_TOTAL_completadas, data),
				'Muy_bien_has_completado_todas_las_tareas': mensajes.Muy_bien_has_completado_todas_las_tareas,
			};
			return render('estado', data, m);
		},
		render: function() {
			this.$el.html(this.template(this.serializeData()));
		}
	});

	var AppLayout = Marionette.Layout.extend({
		ui: {
			tareaInput: "input[name='nuevaTarea']",
			limpiar: "input[name='limpiar']"
		},
		events: {
			"keypress input[name='nuevaTarea']": 'onKeypressNuevaTarea',
			"click input[name='limpiar']": 'onClickLimpiar'
		},
		collectionEvents : {
			'all': 'update'
		},
		regions: {
			tareas: "#tareas",
			estado: "#estado"
		},
		template: function() {
			var m = {
				'Lista_de_tareas': mensajes.Lista_de_tareas,
				'Introduce_una_nueva_tarea': mensajes.Introduce_una_nueva_tarea,
				'Limpiar': mensajes.Limpiar
			};
			return render('tareas', null, m);
		},
		update: function() {
			var completadas = this.options.collection.findCompletadas().length;

			// Habilitar/deshabilitar el botón de limpiar tareas completadas
			if (completadas == 0) {
				this.ui.limpiar.attr('disabled', 'disabled');
			} else {
				this.ui.limpiar.removeAttr('disabled');
			}
		},
		// Eventos
		onKeypressNuevaTarea: function(event) {
			// Comprobar si la tecla pulsada es el return
			if (event.which == 13) {
				var descripcion = this.ui.tareaInput.val();
				descripcion = trim(descripcion);

				// Comprobar si se ha introducido descripción de la tarea
				if (descripcion == '') {
					return;
				}

				// Añadir la tarea y limpiar el input
				var tarea = new Tarea({
					descripcion : descripcion,
					completada : false
				});
				this.options.controller.addTarea(tarea);
				this.ui.tareaInput.val('');
			}
		},
		onClickLimpiar: function() {
			this.options.controller.removeTareasCompletadas();
		}
	});

	var TareasApp = Marionette.Controller.extend({
		initialize: function() {
			// Construir el modelo
			this.tareas = new Tareas();

			// Construir las vistas
			this.layout = new AppLayout({
				el: this.options.el,
				collection: this.tareas,
				controller: this
			});
			this.layout.render();

			this.tareasView = new TareasView({
				collection: this.tareas
			});
			this.estadoView = new EstadoView({
				collection: this.tareas
			});			

			this.layout.tareas.show(this.tareasView);
			this.layout.estado.show(this.estadoView);
			this.layout.update();

		},
		// Métodos
		addTarea : function(tarea) {
			this.tareas.add(tarea);
			tarea.save();
		},
		removeTareasCompletadas : function() {
			this.tareas.removeCompletadas();

		},
		resetTareas : function(tareas) {
			this.tareas.reset(tareas);
		},
		fetch : function() {
			this.tareas.fetch();
		}
	});

	return {
		Tarea: Tarea,
		Tareas: Tareas,
		TareaView: TareaView,
		TareasApp: TareasApp
	};
});