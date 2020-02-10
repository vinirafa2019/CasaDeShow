$('#confirmacaoExclusaoModal').on('show.bs.modal' ,function(event){
	var button = $(event.relatedTarget);
	
	 var Casa = button.data('id');
	 var nomecasaCasa= button.data('nomecasa');
	 var modal = $(this);
	 var form = modal.find('form');
	 var action = form.data('url-base');
	 
	 if(!action.endsWith('/')){		 
		 action +='/';		 
	 }
	 form.attr('action', action + Casa);
	 modal.find('.modal-body span').html('Tem certeza que deseja excluir <strong>' + nomecasaCasa +'</strong>?');
	 
});

$(function () {
	  $('[data-toggle="tooltip"]').tooltip()
	});

