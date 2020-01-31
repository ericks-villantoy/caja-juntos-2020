var table;
var listadoRendicionAdministrado=[];

$(document).ready(function() {
	
	$("body").css({ 'padding-right': '0' });
	$(".content-main").css("height","788px");
	$("#sedeF").text(sede.descripcionSede);
	$("#fechaBusIni").mask("99/99/9999");
	$("#fechaBusFin").mask("99/99/9999");
	cargarConsulta();
});

function nuevoRendicion(){
	window.location="./rendicionAdministradoDetalle";		    	
}

function editar(id){
	
	if(listadoRendicionAdministrado.length>0){
		
		$.each(listadoRendicionAdministrado, function(key,rend) {
			if(rend.idRendicion===id){
				window.location="./rendicionAdministradoDetalle/"+id;
			}
		});
	}
}

function darBaja(id){
	
	BootstrapDialog.confirm({
        title: 'Eliminar Registro',
        message: '¿Estás seguro que desea eliminar registro?',
        closable: false, 
        draggable: true, 
        btnCancelLabel: 'No', 
        btnOKLabel: 'Si', 
        btnOKClass: 'btn-primary', 
        callback: function(result) {

            if(result) {
            		var parametros={
            				idRendicion:id,
            				eliminar:true
            	        };
            		
            		waitingDialog.show('Espere por favor... Procesando');
            		$.ajax({
            		    url:'./grabarRendicionAdministrado',
            		    type:'POST',
            		    data: JSON.stringify(parametros),
            		    dataType: 'json',
            		    contentType: "application/json; charset=utf-8",
            		    success:function(res){
            		    	if(res.exito){
            		    		waitingDialog.hide();
                		    	var texto= 'Se elimino satisfactoriamente';
                			    notificacionMensaje(texto,'success')
                			    cargarConsulta();
                			    
                		 		
            		    	}else{
            		    		waitingDialog.hide();
                		    	var texto= res.respuesta;
                			    notificacionMensaje(texto,'danger')
                			    return false;
            		    	}
            		    	
            		    },
            		    error:function(res){
            		    	var texto= 'Hubo un problema con la eliminacion del registro';
            			    notificacionMensaje(texto,'danger')
            			    waitingDialog.hide();
            		        return false;
            		    }
            		});
            	
            	
            } else {
            	console.log('estas dando click en NO');
            	waitingDialog.hide();
            }
        }
    });
	waitingDialog.hide();
}

function cargarConsulta(){
	listadoRendicionAdministrado.length = 0;
	var parametros={
			idSede:$("#selSedes").val(),
			fechaInicioTxt:$("#fechaBusIni").val(),
			fechaFinTxt:$("#fechaBusFin").val()
		  };
	
	var url = "./obtenerRendicionesAdministrado"; 
	waitingDialog.show('Espere por favor... Listando Rendiciones del Administrado Filtradas');
	$.ajax({
  	    url:url,
  	    type:'POST',
  	    dataType: 'json',
  	    data: JSON.stringify(parametros),
  	    contentType: "application/json; charset=utf-8",
  	    success:function(lista){
  	    	if(lista.length>7)
  	    		$(".content-main").css("height","1108px");
  	    	
  	    	if(lista.length>15)
  	    		$(".content-main").css("height","1508px");
  	    	
  	    	var miJson=[];
  	    	listadoRendicionAdministrado=lista;
  	    	
  	    	$.each(lista, function(key,reg) {
  	    		
  	    		reg.administradoX = reg.administrado.nombresCompletos;
  	    		
  	    		reg.opciones ='';
  	    		
  	    		if(reg.puedeEditar){
  	    			reg.opciones += 	'<a href=javascript:editar('+ reg.idRendicion+');>'+
										'<i class="glyphicon glyphicon-pencil"></i>'+
										'</a>&nbsp;&nbsp;';
  	    		}
  	    		
  	    		reg.opciones += 	'<a title="Dar de Baja Registro" href=javascript:darBaja('+ reg.idRendicion+');>'+
									'<i class="glyphicon glyphicon-off"></i>'+
									'</a>&nbsp;&nbsp;';
  	    		
  	    		if(reg.puedeEnviar){
  	    			reg.opciones += 	'<a title="Enviar a Aprobar" href=javascript:enviarAprobar('+ reg.idRendicion+');>'+
					  					'<i class="glyphicon glyphicon-share-alt"></i>'+
					  					'</a>&nbsp;&nbsp;';
  	    		}
  	    			
				miJson.push(reg);
  	    	});
  	    	
  	    	loadTable(miJson);
  		  	waitingDialog.hide();
  		  $(".modal-backdrop").remove();
  	    },
  	    error:function(res){
  	    	if(res.responseText.indexOf("containerLogin")>1){
  	    		
  	    		window.location="./login";
  	    	}
  	    	waitingDialog.hide();
  	    	 var texto= 'Hubo un problema con la obtencion del registro del administrado.';
  	    	  notificacionMensaje(texto,'danger');
  	    	  return false;
  	    }
  	});

	
};

function loadTable(data){
	var colBusqueda = [
	    
		{ data: "fechaInicioTxt", className: "dt-center", targets: "_all" },
		{ data: "administradoX", className: "dt-left", targets: "_all" },
	    { data: "importeTotal", className: "dt-center", targets: "_all" },
	    { data: "estadoDescripcion", className: "dt-center", targets: "_all"  },
	    { data: "fechaAprobado", className: "dt-center", targets: "_all"  },
	    { data: "opciones", className: "dt-center", targets: "_all"  }
	   
	];
	
	if (table) {
        table.destroy();
        $('#listadoComisiones > tbody').empty();
      }
	table = $('#listadoComisiones').DataTable( {
    	scrollX: true,
        searching: false,
        iDisplayLength: 20,
        dom: "<'row be-datatable-header'<'col-sm-6'l><'col-sm-6 text-right'B>><'row be-datatable-body'<'col-sm-12'tr>><'row be-datatable-footer'<'col-sm-5'i><'col-sm-7'p>>",
        bLengthChange: false,
        
        language: {
                    "lengthMenu": '_MENU_ items por página',
                    "search": '<i class="fa fa-search"></i>',
                    "sZeroRecords":    "No se encontraron resultados",
                    "sEmptyTable":     "Ningún dato disponible en esta tabla",
                    "sInfo":           "Total de registros: _TOTAL_",
                    "sInfoEmpty":      "",
                    "sInfoFilteminT":   "(filtrado de un total de _MAX_ registros)",
                    "sLoadingRecords": "Cargando...",
                    "oAria": {
                        "sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
                        "sSortDescending": ": Activar para ordenar la columna de manera descendente"
                    },
                    "paginate": {
                        "previous": '<i class="fa fa-angle-left"></i>',
                        "next": '<i class="fa fa-angle-right"></i>'
                    }
                },
         bSort: false,
         paging: true,
         data: data,
         select: true,
         columns: colBusqueda
    });
	
	
};

function enviarAprobar(id){
	
	
	BootstrapDialog.confirm({
        title: 'Eliminar Registro',
        message: '¿Estás seguro que desea enviar rendicion para su verificación?',
        closable: false, 
        draggable: true, 
        btnCancelLabel: 'No', 
        btnOKLabel: 'Si', 
        btnOKClass: 'btn-primary', 
        callback: function(result) {

            if(result) {
            		var parametros={
            				idRendicion:id,
            				enviarAprobar:true
            	        };
            		
            		waitingDialog.show('Espere por favor... Procesando');
            		$.ajax({
            		    url:'./grabarRendicionAdministrado',
            		    type:'POST',
            		    data: JSON.stringify(parametros),
            		    dataType: 'json',
            		    contentType: "application/json; charset=utf-8",
            		    success:function(res){
            		    	if(res.exito){
            		    		waitingDialog.hide();
                		    	var texto= 'Se envio satisfactoriamente';
                			    notificacionMensaje(texto,'success')
                			    cargarConsulta();
                			    
                		 		
            		    	}else{
            		    		waitingDialog.hide();
                		    	var texto= res.respuesta;
                			    notificacionMensaje(texto,'danger')
                			    return false;
            		    	}
            		    	
            		    },
            		    error:function(res){
            		    	var texto= 'Hubo un problema con el envio del registro';
            			    notificacionMensaje(texto,'danger')
            			    waitingDialog.hide();
            		        return false;
            		    }
            		});
            	
            	
            } else {
            	console.log('estas dando click en NO');
            	waitingDialog.hide();
            }
        }
    });
	waitingDialog.hide();
	
}

$("#grabarRegistro").on("click",function(){
	grabarRegistro();
});


