$('#confirmacaoExclusaoModal').on('show.bs.modal' ,function(event){
	var button = $(event.relatedTarget);
	
	 var CasaShow = button.data('id');
	 var nomecasaCasaShow = button.data('descricao');
	 var modal = $(this);
	 var form = modal.find('form');
	 var action = form.data('url-base');
	 
	 if(!action.endsWith('/')){		 
		 action +='/';		 
	 }
	 form.attr('action', action + CasaShow);
	 modal.find('.modal-body span').html('Tem certeza que deseja excluir <strong>' + nomecasaCasaShow  +'</strong>?');
	 
});

$(function () {
	  $('[data-toggle="tooltip"]').tooltip()
	});

$('#confirmacaoExclusaoModal1').on('show.bs.modal' ,function(event){
	var button = $(event.relatedTarget);
	
	 var Evento = button.data('id');
	 var descricaoEvento = button.data('descricao');
	 var modal = $(this);
	 var form = modal.find('form');
	 var action = form.data('url-base');
	 
	 if(!action.endsWith('/')){		 
		 action +='/';		 
	 }
	 form.attr('action', action + Evento);
	 modal.find('.modal-body span').html('Tem certeza que deseja excluir <strong>' + descricaoEvento +'</strong>?');
	 
});

