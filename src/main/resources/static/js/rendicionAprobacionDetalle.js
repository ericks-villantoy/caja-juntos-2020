var table;
var tableItems;
var listadoRendicionDetalle=[];
var listadoDocumentosArchivos;
var listadoDocumentosArchivosItem;
var detalleRendicion={};

$(document).ready(function() {
	
	$("body").css({ 'padding-right': '0' });
	$(".content-main").css("height","788px");
	cargarConsulta();
	cargarFlujosAprobacion();
});

function limpiar(){
	$("#administrado").text('');
	$("#idRendicion").val('');
	$('#idDetalleRendicion').val('');
	$('#fechaComision').val('');
	$('#comentario').val('');
	$('#documentoUpload').val('');
	listadoRendicionDetalle.length = 0;
}

function limpiarItem(){
	$('#idDetalleRendicion').val('');
	
	$('#fechaItem').val('');
	$('#selClaseItem').val('0');
	$('#serieItem').val('');
	$('#numeroDocumentoItem').val('');
	$('#selEspecificaItem').val('0');
	$('#detalleItem').val('');
	$('#proveedorItem').val('');
	$('#rucProveedorItem').val('');
	$('#importeProveedorItem').val('');
	$('#documentoUploadItem').val('');
	$('#foliosItem').val('');
	var miJson=[];
  	loadTableMovil(miJson);
}

function retornarRendicion(){
	window.location=contextRoot+"rendicionAdministrado"
}

function redirigirRendicion(idDetalle){
	window.location=contextRoot+"rendicionAdministradoDetalle/"+idDetalle;
}

function cargaVisualizacion(){
	limpiar()
	var miJson=[];
	$("#administrado").text(administrado.nombresCompletos);
	$("#idRendicion").val(rendicion.idRendicion);
	$('#fechaComision').text(rendicion.fechaInicioTxt);
	$('#comentario').text(rendicion.comentario);
	$('#documentoUpload').text(rendicion.documentoReferencia);
	
	listadoRendicionDetalle = rendicion.listadoDetallesRendicion;
	
	if(listadoRendicionDetalle.length>7)
  		$(".content-main").css("height","1108px");
  	
  	if(listadoRendicionDetalle.length>15)
  		$(".content-main").css("height","1508px");
  	
  	var miJson=[];
  	
  	$.each(listadoRendicionDetalle, function(key,detalle) {
  		
  		 
  		
  		detalle.opciones='<a href="javascript:visualizarItem('+ detalle.idDetalleRendicion+');" title="visualizar Item">'+
			'<i class="glyphicon glyphicon-eye-open"></i>'+
			'</a>&nbsp;&nbsp;';
  		
  		if(detalle.usuarioRegistraPagado!=null && detalle.usuarioRegistraPagado.trim().length>0){
  			detalle.usuarioPago="Fecha: "+ detalle.fechaPagadoStr;
  		}else{
  			detalle.usuarioPago="No pagado";
  			detalle.opciones +='<a href="javascript:marcarPago('+ detalle.idDetalleRendicion+');" title="Marcar Pagado">'+
			'<i class="glyphicon glyphicon-usd"></i>'+
			'</a>&nbsp;&nbsp;';
  		}
  			
		miJson.push(detalle);
  	});
  	
  	loadTableItems(miJson);
	
}

function visualizarItem(id){
	
	if(listadoRendicionDetalle.length>0){
		
		$.each(listadoRendicionDetalle, function(key,item) {
			if(item.idDetalleRendicion===id){
				detalleRendicion=item;
				$('#idDetalleRendicion').val(id);
				$('#fechaItem').text(item.fechatxt);
				$('#claseItem').text(item.clase!=null?item.clase.descripcionClase:"");
				$('#serieItem').text(item.serieRecibo);
				$('#numeroDocumentoItem').text(item.numeroRecibo);
				$('#especificaItem').text(item.especifica!=null?item.especifica.descripcionEspecifica:"");
				$('#detalleItem').text(item.detalle);
				$('#proveedorItem').text(item.razonSocial);
				$('#rucProveedorItem').text(item.rucEmpresa);
				$('#importeProveedorItem').text(item.importe);
				$('#documentoUploadItem').text(item.documentoReferencia);
				$('#foliosItem').text(item.cantidadFolios);
				
				$("#divTarifario").css("display", "none");
				if(item.listadoRutaDetalleRendicion.length>0){
					$("#divTarifario").css("display", "block");	
					var miJson=[];
		  	    	$.each(item.listadoRutaDetalleRendicion, function(key,rut) {
		  	    		rut.descripcion = rut.rutaSede.descripcionTotal;
		  	    	miJson.push(rut);
		  	    	});
		  	    	
		  	    	loadTableMovil(miJson);
		  	    	$('.dataTables_scrollHeadInner').css("width","100%");
		  	    	$('.table').css("width","100%");
		  	   }
				$('#modalRendicionItem').modal('show');
				
			}
		});
		

		
	}
	
}

function cargarConsulta(){
	
	var parametros={
			idRendicion:$("#idRendicion").val()
		  };
	
	
	var url = contextRoot+"obtenerDetalleRendicionesAprobacion"; 
	waitingDialog.show('Espere por favor... Listando Rendicion detalle para aprobacion');
	$.ajax({
  	    url:url,
  	    type:'POST',
  	    dataType: 'json',
  	    data: JSON.stringify(parametros),
  	    contentType: "application/json; charset=utf-8",
  	    success:function(resp){
  	    	rendicion = resp;
  	    	administrado = resp.administrado;
  	    	var lista = resp.listadoDetallesRendicion;
  	    	
  	    	if(rendicion.puedeAprobar)
  	    		$("#confirmarAccion").prop('disabled', false);
  	    	if(!rendicion.puedeAprobar)
  	    		$("#confirmarAccion").prop('disabled', true);
  	    	
  	    	if(rendicion.puedeAprobar)
  	    		$("#confirmacionFlujo").css("display", "block");
	  	  	
  	    	if(!rendicion.puedeAprobar)
	  	  		$("#confirmacionFlujo").css("display", "none");
	  	  	
  	    	
  	    	if(rendicion!=null){
  	    		cargaVisualizacion();
  	  		}
  	    	
  	    	waitingDialog.hide();
  		  $(".modal-backdrop").remove();
  	    },
  	    error:function(res){
  	    	if(res.responseText.indexOf("containerLogin")>1){
  	    		
  	    		window.location=contextRoot+"login";
  	    	}
  	    	waitingDialog.hide();
  	    	 var texto= 'Hubo un problema con la obtencion del detalle de rendiciones administrado';
  	    	  notificacionMensaje(texto,'danger');
  	    	  return false;
  	    }
  	});

	
};

function marcarPago(idDetalle){

	
	var	url = contextRoot+"marcarPagadoItem";
	
	var req =	{
						idDetalleRendicion:idDetalle
						}
	
	BootstrapDialog.confirm({
        title: 'Registro Pagado de Item de la Rendición ',
        message: '¿Estás seguro de registrar Pagado?',
        closable: false, 
        draggable: true, 
        btnCancelLabel: 'No', 
        btnOKLabel: 'Si', 
        btnOKClass: 'btn-primary', 
        callback: function(result) {
            if(result) {
            	$.ajax({
              	    url:url,
              	    type:'POST',
              	    dataType: 'json',
              	    data: JSON.stringify(req),
              	    contentType: "application/json; charset=utf-8",
              	    success:function(resp){
              	    	if(resp.exito){
              	    	  var texto= 'Se registro pagado con exito.';
              	    	  notificacionMensaje(texto,'success');
              	    	  
              	    	}else{
              	    		var texto= resp.respuesta;
                	    	notificacionMensaje(texto,'danger');
                	    	
              	    	}
              	    	cargarConsulta();
              	    	waitingDialog.hide();
              	    },
              	    error:function(resp){
              	    	
              	    	$('#grabarRegistro').removeAttr("disabled");
                    	$("#grabarRegistro").prop( "disabled", false);
              	    	 var texto= 'Hubo un inconveniente con el registro de la accion de aprobación.';
             	    	 notificacionMensaje(texto,'danger');
              	    	 return false;
              	    }
              	});
            	
            }else {
            	console.log('estas dando click en NO');
            	$('#grabarRegistro').removeAttr("disabled");
            	$("#grabarRegistro").prop( "disabled", false);
            	waitingDialog.hide();
            }
        }
    });
}

function cargarFlujosAprobacion(){
	
	var parametros={
			idRendicion:$("#idRendicion").val()
		  };
	
	
	var url = contextRoot+"listarFlujoAprobacion"; 
	waitingDialog.show('Espere por favor... Listando flujos de aprobación');
	$.ajax({
  	    url:url,
  	    type:'POST',
  	    dataType: 'json',
  	    data: JSON.stringify(parametros),
  	    contentType: "application/json; charset=utf-8",
  	    success:function(listado){
  	    	var flujos="";
  	    	var i=1
  	    	$.each(listado, function(key,flu) {
  	    		if(i%2 == 0){
  	    			flujos +='<a href="#" class="list-group-item list-group-item-action list-group-item-primary">'+flu.descripcion+'</a>';
  	    		}else{
  	    			flujos +='<a href="#" class="list-group-item list-group-item-action list-group-item-danger">'+flu.descripcion+'</a>';
  	    		}
  	    		i++;
  	  		});
  	    	
  	    	$("#flujoApro").append(flujos);
  	    	waitingDialog.hide();
  		  
  	    },
  	    error:function(res){
  	    	if(res.responseText.indexOf("containerLogin")>1){
  	    		
  	    		window.location=contextRoot+"login";
  	    	}
  	    	waitingDialog.hide();
  	    	 var texto= 'Hubo un problema con la obtencion los flujos de aprobacion';
  	    	  notificacionMensaje(texto,'danger');
  	    	  return false;
  	    }
  	});

	
};

function loadTableItems(data){
var colBusqueda = [
	    
		{ data: "fechatxt", className: "dt-left", targets: "_all" },
		{ data: "tipoClase", className: "dt-left", targets: "_all" },
	    { data: "razonSocial", className: "dt-left", targets: "_all" },
	    { data: "importe", className: "dt-left", targets: "_all"  },
	    { data: "usuarioPago", className: "dt-left", targets: "_all" },
	    { data: "opciones", className: "dt-center", targets: "_all"  }
	   
	];
	
	if (tableItems) {
		tableItems.destroy();
        $('#listadoDetalleItems > tbody').empty();
      }
	tableItems = $('#listadoDetalleItems').DataTable( {
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
}

function loadTableMovil(data){
	var colBusqueda = [
		{ data: "descripcion", className: "dt-left", targets: "_all" },
		{ data: "importe", className: "dt-center", targets: "_all" }
	];
	
	if (table) {
        table.destroy();
        $('#listadoTarifario > tbody').empty();
      }
	table = $('#listadoTarifario').DataTable( {
    	scrollX: true,
        searching: false,
        iDisplayLength: 4,
        dom: "<'row be-datatable-header'<'col-sm-6'l><'col-sm-6 text-right'B>><'row be-datatable-body'<'col-sm-12'tr>><'row be-datatable-footer'<'col-sm-5'i><'col-sm-7'p>>",
        bLengthChange: false,
        bAutoWidth: false,
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
         columns: colBusqueda,
         
    })
    .columns.adjust();
	
	
};

function visualizaDocumentoReferencia(){
	
	var parametrospdf={
			nombreArchivo:rendicion.documentoReferencia
		};
	
	var contentType = 'application/pdf';
	var url = contextRoot+ 'visualizaArchivoPdf';
	$.ajax({
  	    url: url,
  	    type:'POST',
  	    data: JSON.stringify(parametrospdf),
  	    dataType: 'json',
  	    contentType: "application/json; charset=utf-8",
  	    success:function(resp){
  	    
  	    	if(resp.archivoCreado){
  	    		var b64Data = resp.archivoBase64;

  	    		var blob = b64toBlob(b64Data, contentType);
  	    		var blobUrl = URL.createObjectURL(blob);
  	    		
  	    		$("#pdfviewer").attr("src",blobUrl );
  	    		$('#modalpdfviewer').draggable({
  	    	      handle: ".modal-header"
  	    		});
  	    		$('#modalpdfviewer').modal('show');
  	    		waitingDialog.hide();
  	  	  		$("body").css({ 'padding-right': '0' });
  	    	}else{
  	    		rendicion.documentoReferencia="";
  	    		var texto= 'Documento no se ha creado correctamente, vuelva a cargarlo';
  		    	notificacionMensaje(texto,'danger');
  		    	$("#documentoUpload").val("");
  		    }
  	    	
  	    
	  	},
	
		error:function(res){
		
		  if(res.responseText==""){
	    	waitingDialog.hide();
	    	 var texto= 'Hubo un problema al intentar visualizar archivo.';
	    	  notificacionMensaje(texto,'danger');
	    	  return false;
		  }else{
			  if(res.responseText.indexOf("containerLogin")>1){
	  	    		
	  	    		window.location="./login";
	  	    	}  
		  }
	    }
	
	});
		    	
}

function habilitaObservacion(accion){
	var idTipo = '2';
	if(accion.val()==idTipo){
		$("#observAccion").css("display", "block");
	}else{
		$("#observAccion").css("display", "none");
	}
}

function confirmarAccion(){
	
$("#confirmarAccion").prop( "disabled", true);
	
	var	url = contextRoot+"aprobarRendicionAdministrado";
	
	var aprobacionReq =	{
						idRendicion:rendicion.idRendicion,
						accion:$("#selAccion").val(),
						observacion:$("#comentarioAprobacion").val()
						}
	
	BootstrapDialog.confirm({
        title: 'Registro Detalle de Rendición',
        message: '¿Estás seguro de registrar Detalle?',
        closable: false, 
        draggable: true, 
        btnCancelLabel: 'No', 
        btnOKLabel: 'Si', 
        btnOKClass: 'btn-primary', 
        callback: function(result) {
            if(result) {
            	$.ajax({
              	    url:url,
              	    type:'POST',
              	    dataType: 'json',
              	    data: JSON.stringify(aprobacionReq),
              	    contentType: "application/json; charset=utf-8",
              	    success:function(resp){
              	    	if(resp.exito){
              	    	  var texto= 'Se registro accion de aprobación con exito.';
              	    	  notificacionMensaje(texto,'success');
              	    	  
              	    	}else{
              	    		var texto= resp.respuesta;
                	    	notificacionMensaje(texto,'danger');
                	    	
              	    	}
              	    	$('#confirmarAccion').removeAttr("disabled");
                    	$("#confirmarAccion").prop( "disabled", false);
                    	cargarConsulta();
              	    	waitingDialog.hide();
              	    },
              	    error:function(resp){
              	    	
              	    	$('#grabarRegistro').removeAttr("disabled");
                    	$("#grabarRegistro").prop( "disabled", false);
              	    	 var texto= 'Hubo un inconveniente con el registro de la accion de aprobación.';
             	    	 notificacionMensaje(texto,'danger');
              	    	 return false;
              	    }
              	});
            	
            }else {
            	console.log('estas dando click en NO');
            	$('#grabarRegistro').removeAttr("disabled");
            	$("#grabarRegistro").prop( "disabled", false);
            	waitingDialog.hide();
            }
        }
    });
}

$("#selAccion").change(function() { 
	habilitaObservacion($(this));
});

$("#grabarRegistro").on("click",function(){
	grabarRegistro();
});

$("#documentoUpload").on("click",function(){
	if((rendicion.documentoReferencia!=null && rendicion.documentoReferencia!="") || $("#documentoUpload").val()!="" )
		visualizaDocumentoReferencia();
});
$("#documentoUploadItem").on("click",function(){
	if((detalleRendicion.documentoReferencia!=null && detalleRendicion.documentoReferencia!="") || $("#documentoUploadItem").val()!="" )
		visualizaDocumentoReferencia();
});
