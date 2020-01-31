var table;
var listadoRendicionDetalle=[];

$(document).ready(function() {
	
	$("body").css({ 'padding-right': '0' });
	$(".content-main").css("height","788px");
	var meses = new Array ("Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre");
	var fechaActual = new Date();
	var anio = fechaActual.getFullYear();
	var mes = fechaActual.getMonth();
	
	$("#sedeF").text(sede==null?'':sede.descripcionSede);
	$("#fechaMes").val("Mes: "+ meses[mes] )
	$("#fechaAnio").val("Año: "+ anio);
	
	var fechaCajaChica = new Date();
	var anioCajaChica = fechaCajaChica.getFullYear();
	
	$('#selectAnio option[value='+anioCajaChica+']').attr("selected",true);
	
	var mesActualValor = fechaCajaChica.getMonth();
	
	$("#selectMes").select2({data: meses, width: "100%"});
	$('#selectMes option[value='+mesActualValor+']').attr("selected",true);
	$("#selectMes").trigger('change');
	$("#selSedes").val(codigoSede);
	
	cargarConsulta();
	
});

function actualizaTabla(lista){
	var miJson=[];
  	listadoRendicionDetalle=lista;
  	
  	$.each(lista, function(key,reg) {
  		
  		reg.seleccion ='<input type="checkbox" onchange="marcarSeleccion(this,'+ reg.idDetalleRendicion+')">';
  		if(reg.marcado)
  			reg.seleccion='<input type="checkbox" checked="checked">';
  		
  		reg.estadoTesoreria="APROBADO";
  		
  		if(reg.observado)
  			reg.estadoTesoreria="OBSERVADO";
  			
//  		reg.opciones = 	'<a href=javascript:observarCajachica('+ reg.idDetalleRendicion+'); title="Observar rendicion">'+
//						'<i class="glyphicon glyphicon-warning-sign"></i>'+
//						'</a>&nbsp;&nbsp;<a title="Enviar a Aprobar" href=javascript:enviarAprobar('+ reg.idDetalleRendicion+'); title="Enviar rendicion Caja Chica">'+
//		  				'<i class="glyphicon glyphicon-share-alt"></i>'+
//		  				'</a>&nbsp;&nbsp;<a title="Visualizar Detalle" href=javascript:visualizarItem('+ reg.idDetalleRendicion+'); title="Enviar rendicion Caja Chica">'+
//		  				'<i class="glyphicon glyphicon-eye-open"></i>'+
//		  				'</a>&nbsp;&nbsp;';
  		
  		reg.opciones = 	'<a href=javascript:observarCajachica('+ reg.idDetalleRendicion+'); title="Observar rendicion">'+
					'<i class="glyphicon glyphicon-warning-sign"></i>'+
					'</a>&nbsp;&nbsp;<a title="Visualizar Detalle" href=javascript:visualizarItem('+ reg.idDetalleRendicion+'); title="Enviar rendicion Caja Chica">'+
					'<i class="glyphicon glyphicon-eye-open"></i>'+
					'</a>&nbsp;&nbsp;';
			
		miJson.push(reg);
  	});
  	
  	loadTable(miJson);
}

function obtenerHistorialApeturaCierre(){
	
	
	var idSedeReq;
	if(sede==null){
		idSedeReq = $("#selSedes").val();
	}else{
		idSedeReq = sede.idSede;
	}
	
	var parametros={
			idSede:idSedeReq,
			anio:$("#selectAnio").val(),
			mes:$("#selectMes").val()
			
		  };
	
	var url = "./obtenerHistorialApeturaCierre"; 
	waitingDialog.show('Espere por favor... Listando Historial');
	$.ajax({
  	    url:url,
  	    type:'POST',
  	    dataType: 'json',
  	    data: JSON.stringify(parametros),
  	    contentType: "application/json; charset=utf-8",
  	    success:function(listado){
  	    	var historial="";
  	    	var i=1
  	    	$.each(listado, function(key,hist) {
  	    		if(i%2 == 0){
  	    			historial +='<a href="#" class="list-group-item list-group-item-action list-group-item-primary">'+hist.historial+'</a>';
  	    		}else{
  	    			historial +='<a href="#" class="list-group-item list-group-item-action list-group-item-danger">'+hist.historial+'</a>';
  	    		}
  	    		i++;
  	  		});
  	    	
  	    	$("#historial").append(historial);
  	    	waitingDialog.hide();
  	    },
  	    error:function(res){
  	    	if(res.responseText.indexOf("containerLogin")>1){
  	    		
  	    		window.location="./login";
  	    	}
  	    	waitingDialog.hide();
  	    	 var texto= 'Hubo un problema con la recuepracion del historial.';
  	    	  notificacionMensaje(texto,'danger');
  	    	  return false;
  	    }
  	});

	
};

function cargarConsulta(){
	listadoRendicionDetalle.length = 0;
	
	var idSedeReq;
	if(sede==null){
		idSedeReq = $("#selSedes").val();
	}else{
		idSedeReq = sede.idSede;
	}
	
	var parametros={
			idSede:idSedeReq
		  };
	
	var url = "./obtenerDetalleRendicionesAprobadas"; 
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
  	    	
  	    	actualizaTabla(lista);
  		  	waitingDialog.hide();
  		  obtenerHistorialApeturaCierre();
  		  	$(".modal-backdrop").remove();
	    	$("body").css({ 'padding-right': '0' });
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

function observarCajachica(id){
	
	if(listadoRendicionDetalle.length>0){
		
		$.each(listadoRendicionDetalle, function(key,item) {
			if(item.idDetalleRendicion===id){
				$('#idDetalleRendicionObservado').val(id);
				$('#observacionRendicionCajaChica').val(item.observacionRendicion);
				$('#modalObservacion').modal('show');
			}
		});
		
	}
	
}

function marcarSeleccion(obj, id){
	
	if(listadoRendicionDetalle.length>0){
			$.each(listadoRendicionDetalle, function(key,item) {
				if(item.idDetalleRendicion===id){
					if(obj.checked)
						item.marcado=true;
					else
						item.marcado=false;
					
				}
			});
			
	}
	actualizaTabla(listadoRendicionDetalle);
	
}

function observarRegistro(){
	var id =parseInt($('#idDetalleRendicionObservado').val());
	if(listadoRendicionDetalle.length>0){
			$.each(listadoRendicionDetalle, function(key,item) {
				if(item.idDetalleRendicion===id){
					item.observacionRendicion=$('#observacionRendicionCajaChica').val();
					
					if($('#observacionRendicionCajaChica').val().length>2)
						item.observado=true;
					else
						item.observado=false;
				}
			});
			
	}
	actualizaTabla(listadoRendicionDetalle);
	$('#modalObservacion').modal('hide');
}

function seleccionarTodo(){
	var marcado=false;
		if( $('.seltodo').is(':checked') ) {
			marcado=true;
			
		}
		
		if(listadoRendicionDetalle.length>0){
			$.each(listadoRendicionDetalle, function(key,item) {
				item.marcado=marcado;
				});
		}
			
	actualizaTabla(listadoRendicionDetalle);
	
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

function visualizaDocumentoReferencia(){
	
	var parametrospdf={
			nombreArchivo:detalleRendicion.documentoReferencia
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

function loadTable(data){
	var colBusqueda = [
		{ data: "seleccion", className: "dt-center", targets: "_all" },
		{ data: "fechatxt", className: "dt-center", targets: "_all" },
		{ data: "importe", className: "dt-center", targets: "_all" },
	    { data: "estadoRendicionDescripcion", className: "dt-center", targets: "_all"  },
	    { data: "estadoTesoreria", className: "dt-center", targets: "_all"  },
	    { data: "opciones", className: "dt-center", targets: "_all"  }
	   
	];
	
	if (table) {
        table.destroy();
        $('#listadoRendicionCajaChica > tbody').empty();
      }
	table = $('#listadoRendicionCajaChica').DataTable( {
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

function enviarRendicion(){

	
	var	url = "./enviarRendicionCajaChica";
	
	var idSedeReq;
	if(sede==null){
		idSedeReq = $("#selSedes").val();
	}else{
		idSedeReq = sede.idSede;
	}
	
	var req =	{
				listadoRendicionCajachica:listadoRendicionDetalle,
				idSede:idSedeReq
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


function confirmarAccion(){
	
	$("#confirmarAccion").prop( "disabled", true);
		
		var	url = "./reaperturarCerrarCajaChica";
		
		var idSedeReq;
		if(sede==null && $("#selSedes").val()!='0'){
			idSedeReq = $("#selSedes").val();
		}else{
			idSedeReq = sede.idSede;
		}
		
		var aprobacionReq =	{
							idSede:idSedeReq,
							accion:$("#selAccion").val(),
							anio:$("#selectAnio").val(),
							mes:$("#selectMes").val(),
							observacion:$("#observacion").val()
							}
		
		BootstrapDialog.confirm({
	        title: 'Registro Reapertura y Cierre',
	        message: '¿Estás seguro de registrar Accion?',
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
	              	    	  var texto= 'Se registro accion de apertura cierre con exito.';
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
	              	    	 var texto= 'Hubo un inconveniente con el registro de la accion de apertura cierre.';
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

$("#observarRegistro").on("click",function(){
	observarRegistro();
});

$(".seltodo").on("click",function(){
	seleccionarTodo();
});

