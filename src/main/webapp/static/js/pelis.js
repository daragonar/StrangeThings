$(document).ready(function(){
	$(".btn-borrar-peli").on("click", function() {
		var id = $(this).parents('tr').data('id');
		var csrf = $('#_csrf').val();
		
		$.ajax({
			url: 'peliculas/'+id,
			type: 'DELETE',
			headers: {'X-CSRF-TOKEN': csrf},
			success : function(result) {
				$('tr[data-id="' + id + '"]').remove();
			}
		})
	})
	$(".btn-editar-peli").on("click", function() {
		var id = $(this).parents('tr').data('id');
		var url = 'peliculas/peli/'+id;
		
		$.get(url)
			.done(function(pelicula){
			$("#nombre").val(pelicula.nombrePel);
			$("#caratula").val(pelicula.caratula);
			$("#trailer").val(pelicula.trailer);
			$("#DescripcionPel").val(pelicula.descripcionPel);
			$("#idPel").val(pelicula.idPel)
			$('#modal-id').modal('show');
			})
		
	})
	
	
})