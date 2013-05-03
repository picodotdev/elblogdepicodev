define(['jquery', 'jasmine-html', 'sinon', 'tareas'], function($, jasmine, sinon, tareas) {
	function trim(string) {
		return string.replace(/^\s+|\s+$/gi, '');
	}
	
	describe('aplicación lista de tareas', function() {
		var tareaNoCompletada;
		var tareaCompletada;
		
		beforeEach(function() {
			tareaNoCompletada = new tareas.Tarea({ descripcion:'Tarea', completada:false });
			tareaCompletada = new tareas.Tarea({ descripcion:'Tarea', completada:true});
		});
		
		describe('modelo tarea', function() { 
			it('no completado', function() {
				var t = tareaNoCompletada.toPlantilla();
				expect(t.descripcion).toEqual('Tarea');
				expect(t.completada).toBeFalsy();
				expect(t.attrs.checked).toBeNull();
				expect(t.attrs.completada).toBeNull();
			});
			it('completado', function() {
				var t = tareaCompletada.toPlantilla();
				expect(t.descripcion).toEqual('Tarea');
				expect(t.completada).toBeTruthy();
				expect(t.attrs.checked).toEqual('checked');
				expect(t.attrs.completada).toEqual('completada');
			});
		});
		describe('modelo tareas', function() {
			var modelos;
			
			beforeEach(function() {
				modelos = [];
				modelos.push(tareaNoCompletada);
				modelos.push(tareaCompletada);
			});

			it('búsqueda completadas', function() {
				var modelo = new tareas.Tareas();
				modelo.reset(modelos);

				expect(1).toEqual(modelo.findCompletadas().length);				
			});
			it('eliminar completadas', function() {
				var modelo = new tareas.Tareas();
				modelo.reset(modelos);

				modelo.removeCompletadas();
				
				expect(1).toEqual(modelo.length);
				expect(0).toEqual(modelo.findCompletadas().length);
			});
		});
		describe('vistas', function() {
			var server;
		
			beforeEach(function() {
				server = sinon.fakeServer.create();
			});
			
			afterEach(function() {
				server.restore();
			});
			
			describe('tareas', function() {
				it('marcar como completada', function() {
					var vista = new tareas.TareaView({model: tareaNoCompletada});
				
					var input = $("input[name='completada']", vista.el);
					input.attr('checked', true);
					input.trigger('change');
				
					expect(tareaNoCompletada.get('completada')).toBeTruthy();
					expect(1).toEqual(server.requests.length);
				});
				it('marcar como no completada', function() {
					var vista = new tareas.TareaView({model: tareaCompletada});
				
					var input = $("input[name='completada']", vista.el);
					input.attr('checked', false);
					input.trigger('change');
				
					expect(tareaCompletada.get('completada')).toBeFalsy();
					expect(1).toEqual(server.requests.length);
				});
			});
			describe('tareasapp', function() {
				var vista;
				
				beforeEach(function() {
					vista = new tareas.TareasApp();
				});
				
				it('inicializar lista tareas', function() {
					vista.resetTareas([tareaNoCompletada, tareaCompletada]);					

					expect(2).toEqual($('#lista-tareas', vista.el).children().length);
					expect('1 tareas de 2 completadas').toEqual(trim($('#estado', vista.el).html()));
				});
				it('añadir una tarea', function() {
					vista.addTarea(tareaNoCompletada);
					
					expect(1).toEqual($('#lista-tareas', vista.el).children().length);
				});
				it('nueva tarea', function() {
					var input = $("input[name='nuevaTarea']", vista.el);					
					var e = $.Event('keypress');
					e.which = 13;
					
					input.val('Tarea');
					input.trigger(e);

					expect(1).toEqual($('#lista-tareas', vista.el).children().length);
					expect('').toEqual(input.val());
					expect(1).toEqual(server.requests.length);
				});
				it('limpiar tareas completadas', function() {
					vista.resetTareas([tareaNoCompletada, tareaCompletada]);
					
					var input = $("input[name='limpiar']", vista.el);									
					input.trigger('click');		

					expect('disabled').toEqual(input.attr('disabled'));
					expect(1).toEqual($('#lista-tareas', vista.el).children().length);
				});
				it('botón limpiar con tareas completadas', function() {
					vista.resetTareas([tareaCompletada]);
										
					var input = $("input[name='limpiar']", vista.el);
					
					expect(input.attr('disabled')).toBeUndefined();
				});
				it('botón limpiar con tareas no completadas', function() {
					vista.resetTareas([tareaNoCompletada]);
					
					var input = $("input[name='limpiar']", vista.el);
					
					expect('disabled').toEqual(input.attr('disabled'));
				});
				it('botón limpiar sin tareas', function() {
					var input = $("input[name='limpiar']", vista.el);
					expect('disabled').toEqual(input.attr('disabled'));
				});
			});
		});
	});
});