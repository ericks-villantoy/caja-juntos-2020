var table;
var tableItems;
var listadoRendicionDetalle=[];
var listadoDocumentosArchivos;
var listadoDocumentosArchivosItem;
var detalleRendicionReq={};
var rendicionReq={};
var detalleRendicion={};

$(document).ready(function() {
	
	$("body").css({ 'padding-right': '0' });
	$(".content-main").css("height","788px");
	$("#fechaComision").mask("99/99/9999");
	$("#fechaItem").mask("99/99/9999");
	cargarConsulta();
	cargarClaseGasto();
	cargarEspecificaGasto();
	cargarTarifario();
});

function cargarEspecificaGasto(){
	
	var lisEspecifica=[];
	
	$('#selEspecificaItem').empty();
	var url =  contextRoot+"listarEspecificaGasto";
	
	 $.ajax({
	  	    url:url,
	  	    type:'POST',
	  	    dataType: 'json',
	  	    contentType: "application/json; charset=utf-8",
	  	    success:function(res){
	  	    	$.each(res, function(i, data) {
	  	    		var especifica ={}
	  	    		especifica.id=data.idEspecifica;
	  	    		especifica.text=data.descripcionEspecifica;
	  	    	
	  	    		lisEspecifica.push(especifica);
	  	    	});
	  	    	var especifica ={}
	  	    	especifica.id=0;
	  	    	especifica.text="Seleccione Especifica Gasto";
  	    		lisEspecifica.unshift(especifica);
	  	    	
	  	    	$("#selEspecificaItem").select2({
	  			  data:lisEspecifica,
	  			  width:"100%"
	  	    	});
	  	    	
	  	    	
	  	    },
	  	    error:function(res){
	  	    	 var texto= 'Hubo un problema con la obtencion de las Especificas Gastos.';
	  	    	  notificacionMensaje(texto,'danger');
	  	    	  return false;
	  	    }
	  	});
	
}

function cargarTarifario(){
	
	var lisTarifario=[];
	
	$('#selTarifarioItem').empty();
	var url =  contextRoot+"listarTarifario";
	
	 $.ajax({
	  	    url:url,
	  	    type:'POST',
	  	    dataType: 'json',
	  	    contentType: "application/json; charset=utf-8",
	  	    success:function(res){
	  	    	$.each(res, function(i, data) {
	  	    		var tarifario ={}
	  	    		tarifario.id=data.idRutaSede;
	  	    		tarifario.text=data.descripcionTotal;
	  	    	
	  	    		lisTarifario.push(tarifario);
	  	    	});
	  	    	var tarifario ={}
	  	    	tarifario.id=0;
	  	    	tarifario.text="Seleccione Especifica Gasto";
	  	    	lisTarifario.unshift(tarifario);
	  	    	
	  	    	$("#selTarifarioItem").select2({
	  			  data:lisTarifario,
	  			  width:"100%"
	  	    	});
	  	    	
	  	    	
	  	    },
	  	    error:function(res){
	  	    	 var texto= 'Hubo un problema con la obtencion de las Especificas Gastos.';
	  	    	  notificacionMensaje(texto,'danger');
	  	    	  return false;
	  	    }
	  	});
	
}

function cargarClaseGasto(){
	
	var lisClase=[];
	
	$('#selClaseItem').empty();
	
	var url = contextRoot+"listarTiposClases";
	
	 $.ajax({
	  	    url:url,
	  	    type:'POST',
	  	    dataType: 'json',
	  	    contentType: "application/json; charset=utf-8",
	  	    success:function(res){
	  	    	$.each(res, function(i, data) {
	  	    		var clase ={}
	  	    		clase.id=data.idClase;
	  	    		clase.text=data.descripcionClase;
	  	    	
	  	    		lisClase.push(clase);
	  	    	});
	  	    	var clase ={}
	  	    	clase.id=0;
	  	    	clase.text="Seleccione Clase Gasto";
  	    		lisClase.unshift(clase);
	  	    	
	  	    	$("#selClaseItem").select2({
	  			  data:lisClase,
	  			  width:"100%"
	  	    	});
	  	    	
	  	    	
	  	    },
	  	    error:function(res){
	  	    	 var texto= 'Hubo un problema con la obtencion de las Clases Gastos.';
	  	    	  notificacionMensaje(texto,'danger');
	  	    	  return false;
	  	    }
	  	});
	
}

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

function agregarItem(){
	
	if($('#idRendicion').val()=='')
	{		
		//alert("Primero debe grabar la rendición");
		notificacionMensaje('Primero debe grabar la rendición','danger');
		return;
	}
	
	limpiarItem();
	$('#modalRendicionItem').modal('show');
}

function retornarRendicion(){
	window.location=contextRoot+"rendicionAdministrado"
}

function redirigirRendicion(idDetalle){
	window.location=contextRoot+"rendicionAdministradoDetalle/"+idDetalle;
}

function cargaEdicion(){
	limpiar()
	var miJson=[];
	$("#administrado").text(administrado.nombresCompletos);
	$("#idRendicion").val(rendicion.idRendicion);
	$('#fechaComision').val(rendicion.fechaInicioTxt);
	$('#comentario').val(rendicion.comentario);
	$('#documentoUpload').val(rendicion.documentoReferencia);
	
	listadoRendicionDetalle = rendicion.listadoDetallesRendicion;
	
	if(listadoRendicionDetalle.length>7)
  		$(".content-main").css("height","1108px");
  	
  	if(listadoRendicionDetalle.length>15)
  		$(".content-main").css("height","1508px");
  	
  	var miJson=[];
  	
  	$.each(listadoRendicionDetalle, function(key,detalle) {
  		
  		detalle.opciones='<a href=javascript:editarItem('+ detalle.idDetalleRendicion+');>'+
			'<i class="glyphicon glyphicon-pencil"></i>'+
			'</a>&nbsp;&nbsp;<a title="Dar de Baja Registro" href=javascript:darBajaItem('+ detalle.idDetalleRendicion+');>'+
			'<i class="glyphicon glyphicon-off"></i>'+
			'</a>&nbsp;&nbsp;';
			
		miJson.push(detalle);
  	});
  	
  	loadTableItems(miJson);
	
}

function editarItem(id){
	
	if(listadoRendicionDetalle.length>0){
		
		$.each(listadoRendicionDetalle, function(key,item) {
			if(item.idDetalleRendicion===id){
				detalleRendicion=item;
				$('#idDetalleRendicion').val(id);
				$('#fechaItem').val(item.fechatxt);
				$('#selClaseItem').val(item.clase!=null?item.clase.idClase:"0");
				$('#selClaseItem').change();
				$('#serieItem').val(item.serieRecibo);
				$('#numeroDocumentoItem').val(item.numeroRecibo);
				$('#selEspecificaItem').val(item.especifica!=null?item.especifica.idEspecifica:"");
				$('#selEspecificaItem').change();
				$('#detalleItem').val(item.detalle);
				$('#proveedorItem').val(item.razonSocial);
				$('#rucProveedorItem').val(item.rucEmpresa);
				$('#importeProveedorItem').val(item.importe);
				$('#documentoUploadItem').val(item.documentoReferencia);
				$('#foliosItem').val(item.cantidadFolios);
				
				
				
				var miJson=[];
	  	    	$.each(item.listadoRutaDetalleRendicion, function(key,rut) {
	  	    		rut.descripcion = rut.rutaSede.descripcionTotal;
	  	    		rut.opciones='<a title="Dar de Baja Registro" href=javascript:darBajaRutaItem('+ rut.idRutaRendicionDetalle+');>'+
	  	  			'<i class="glyphicon glyphicon-off"></i>'+
	  	  			'</a>&nbsp;&nbsp;';
	  	  			
	  	  		miJson.push(rut);
	  	    	});
	  	    	
	  	    	loadTableMovil(miJson);
	  	    	$('.dataTables_scrollHeadInner').css("width","100%");
	  	    	$('.table').css("width","100%");
				$('#modalRendicionItem').modal('show');
				
			}
		});
		

		
	}
	
	
}

function darBajaItem(id){
	
	var	url = contextRoot+"grabarDetalleRendicionAdministrado";
	
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
            				idDetalleRendicion:id,
            				eliminar:true
            	        };
            		
            		waitingDialog.show('Espere por favor... Procesando');
            		$.ajax({
            		    url:url,
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

function darBajaRutaItem(id){
	
	var	url = contextRoot+"grabarDetalleRendicionTarifarioAdministrado";
	
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
            				idRutaRendicionDetalle:id,
            				eliminar:true
            	        };
            		
            		waitingDialog.show('Espere por favor... Procesando');
            		$.ajax({
            		    url:url,
            		    type:'POST',
            		    data: JSON.stringify(parametros),
            		    dataType: 'json',
            		    contentType: "application/json; charset=utf-8",
            		    success:function(res){
            		    	if(res.exito){
            		    		waitingDialog.hide();
                		    	var texto= 'Se elimino satisfactoriamente';
                			    notificacionMensaje(texto,'success')
                			    cargarConsultaTarifario();
                			    
                		 		
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
	
	var parametros={
			idRendicion:$("#idRendicion").val()
		  };
	
	
	var url = contextRoot+"obtenerDetalleRendicionesAdministrado"; 
	waitingDialog.show('Espere por favor... Listando detalle rendicion');
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
  	    	
  	    	if(rendicion!=null){
  	    		cargaEdicion();
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

function cargarConsultaTarifario(){
	
	var parametros={
			idDetalleRendicion:$("#idDetalleRendicion").val()
		  };
	
	
	var url = contextRoot+"obtenerDetalleTarifarioRendicion"; 
	waitingDialog.show('Espere por favor... Listando tarifario x ruta');
	$.ajax({
  	    url:url,
  	    type:'POST',
  	    dataType: 'json',
  	    data: JSON.stringify(parametros),
  	    contentType: "application/json; charset=utf-8",
  	    success:function(lista){
  	    	var miJson=[];
  	    	$.each(lista, function(key,rut) {
  	    		rut.descripcion = rut.rutaSede.descripcionTotal;
  	    		rut.opciones='<a title="Dar de Baja Registro" href=javascript:darBajaRutaItem('+ rut.idRutaRendicionDetalle+');>'+
  	  			'<i class="glyphicon glyphicon-off"></i>'+
  	  			'</a>&nbsp;&nbsp;';
  	  			
  	  		miJson.push(rut);
  	    	});
  	    	
  	    	loadTableMovil(miJson);
  	    	
  	    	waitingDialog.hide();
  		  $(".modal-backdrop").remove();
  	    },
  	    error:function(res){
  	    	if(res.responseText.indexOf("containerLogin")>1){
  	    		
  	    		window.location=contextRoot+"login";
  	    	}
  	    	waitingDialog.hide();
  	    	 var texto= 'Hubo un problema con la obtencion de las RSEs.';
  	    	  notificacionMensaje(texto,'danger');
  	    	  return false;
  	    }
  	});

	
};

function registrarDetalle(){
	
	
	if(validarRendicionAdministrado()==false)		{
		return;
	}
	
	$("#grabarRegistro").prop( "disabled", true);
	
	var	url = contextRoot+"grabarRendicionAdministrado";
	
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
            	obtenerRendicionAdministrado();
            	$.ajax({
              	    url:url,
              	    type:'POST',
              	    dataType: 'json',
              	    data: JSON.stringify(rendicionReq),
              	    contentType: "application/json; charset=utf-8",
              	    success:function(resp){
              	    	if(resp.exito){
              	    	  var texto= 'Se registro con exito.';
              	    	  notificacionMensaje(texto,'success');
              	    	  
              	    	}else{
              	    		var texto= resp.respuesta;
                	    	notificacionMensaje(texto,'danger');
                	    	
              	    	}
              	    	$('#modalTarifario').modal('hide');
           	  	      	$('#modalTarifario').hide();
              	    	$('#grabarRegistro').removeAttr("disabled");
                    	$("#grabarRegistro").prop( "disabled", false);
                    	redirigirRendicion(resp.idRendicion);
                    	cargarConsulta();
              	    	waitingDialog.hide();
              	    },
              	    error:function(resp){
              	    	
              	    	$('#grabarRegistro').removeAttr("disabled");
                    	$("#grabarRegistro").prop( "disabled", false);
              	    	 var texto= 'Hubo un inconveniente con el registro de Ruta de Sede.';
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

function registrarDetalleItem(){
	
	if(validarItemRendicionAdministrado()==false){
		return;
	}
	
	$("#grabarItem").prop( "disabled", true);
	
	var url = contextRoot+'grabarDetalleRendicionAdministrado';
	
	BootstrapDialog.confirm({
        title: 'Registro de Items ',
        message: '¿Estás seguro de registrar Item?',
        closable: false, 
        draggable: true, 
        btnCancelLabel: 'No', 
        btnOKLabel: 'Si', 
        btnOKClass: 'btn-primary', 
        callback: function(result) {
            if(result) {
            	obtenerDetalleRendicionAdministrado();
            	obtenerRendicionAdministrado();
            	
            	detalleRendicionReq.rendicionReq=rendicionReq;
            	
            	waitingDialog.show('Espere por favor... registrando Item');
            	$.ajax({
              	    url:url,
              	    type:'POST',
              	    dataType: 'json',
              	    data: JSON.stringify(detalleRendicionReq),
              	    contentType: "application/json; charset=utf-8",
              	    success:function(resp){
              	    	if(resp.exito){
              	    	  var texto= 'Se registro con exito.';
              	    	  notificacionMensaje(texto,'success');
              	    	$("#idDetalleRendicion").val(resp.idDetalleRendicion);
              	    	$("#idRendicion").val(resp.idRendicion);
              	    	}else{
              	    		var texto= resp.respuesta;
                	    	notificacionMensaje(texto,'danger');
                	    	
              	    	}
              	    	$('#modalRendicionItem').modal('hide');
              	    	$('#grabarItem').removeAttr("disabled");
                    	$("#grabarItem").prop( "disabled", false);
              	    	cargarConsulta();
            		  	waitingDialog.hide();
              	    },
              	    error:function(resp){
              	    	if(resp.responseText.indexOf("containerLogin")>1){
              	    		window.location=contextRoot+"login";
              	    	}
              	    	$('#grabarItem').removeAttr("disabled");
                    	$("#grabarItem").prop( "disabled", false);
              	    	 var texto= 'Hubo un inconveniente con el registro del Item.';
             	    	 notificacionMensaje(texto,'danger');
              	    	 return false;
              	    }
              	});
            	
            }else {
            	console.log('estas dando click en NO');
            	$('#grabarItem').removeAttr("disabled");
            	$("#grabarItem").prop( "disabled", false);
            	waitingDialog.hide();
            }
        }
    });
	
}

function registrarDetalleTarifario(){
	
	$("#agregarTarifario").prop( "disabled", true);
	
	if($('#selTarifarioItem').val()=="0"){
		var texto= 'Seleccione tarifario.';
    	notificacionMensaje(texto,'success');
    	$('#agregarTarifario').removeAttr("disabled");
    	$("#agregarTarifario").prop( "disabled", false);
    	return false;
	}
	
	var parametros={
			idDetalleRendicion:    $('#idDetalleRendicion').val(),
			idRendicion:    $('#idRendicion').val(),
			idRutaSede:    $('#selTarifarioItem').val()
		  };
	
	obtenerDetalleRendicionAdministrado();
	obtenerRendicionAdministrado();
	parametros.detalleRendicionReq=detalleRendicionReq;
	parametros.rendicionReq=rendicionReq;
	
	var	url = contextRoot+"grabarDetalleRendicionTarifarioAdministrado";
	
	BootstrapDialog.confirm({
        title: 'Registro de Tarifas por Sede',
        message: '¿Estás seguro de registrar Tarifa?',
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
              	    data: JSON.stringify(parametros),
              	    contentType: "application/json; charset=utf-8",
              	    success:function(resp){
              	    	if(resp.exito){
              	    	  var texto= 'Se registro con exito.';
              	    	  notificacionMensaje(texto,'success');
              	    	$("#idDetalleRendicion").val(resp.idDetalleRendicion);
              	    	}else{
              	    		var texto= resp.respuesta;
                	    	notificacionMensaje(texto,'danger');
                	    	
              	    	}
              	    	$('#agregarTarifario').removeAttr("disabled");
                    	$("#agregarTarifario").prop( "disabled", false);
              	    	cargarConsultaTarifario();
              	    	waitingDialog.hide();
              	    },
              	    error:function(resp){
              	    	
              	    	$('#agregarTarifario').removeAttr("disabled");
                    	$("#agregarTarifario").prop( "disabled", false);
              	    	 var texto= 'Hubo un inconveniente con el registro de Ruta de Sede.';
             	    	 notificacionMensaje(texto,'danger');
              	    	 return false;
              	    }
              	});
            	
            }else {
            	console.log('estas dando click en NO');
            	$('#agregarTarifario').removeAttr("disabled");
            	$("#agregarTarifario").prop( "disabled", false);
            	waitingDialog.hide();
            }
        }
    });
	
}

function loadTableItems(data){
var colBusqueda = [
	    
		{ data: "fechatxt", className: "dt-left", targets: "_all" },
		{ data: "tipoClase", className: "dt-left", targets: "_all" },
	    { data: "razonSocial", className: "dt-left", targets: "_all" },
	    { data: "importe", className: "dt-left", targets: "_all"  },
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

function cargarDocumento() {
	listadoDocumentosArchivos="";
    $.FileDialog({
		multiple: true,
		accept:"application/pdf"
		}).on('files.bs.filedialog', function(ev) {
        var files = ev.files;
        
        var file = files[0];
        var pdfs=[];
        $.each(files, function(i, fi) {
        	pdfs.push(fi.content);
        });
        
        listadoDocumentosArchivos=pdfs;
        
        if(listadoDocumentosArchivos.length>1)
        	$("#documentoUpload").val("Varios PDFs");
        if(listadoDocumentosArchivos.length===1)
        	$("#documentoUpload").val(file.name);
       
    }).on('cancel.bs.filedialog', function(ev) {
        $("#output").html("Cancelled!");
    });
};

function cargarDocumentoItem() {
	listadoDocumentosArchivosItem="";
    $.FileDialog({
		multiple: true,
		accept:"application/pdf"
		}).on('files.bs.filedialog', function(ev) {
        var files = ev.files;
        
        var file = files[0];
        var pdfs=[];
        $.each(files, function(i, fi) {
        	pdfs.push(fi.content);
        });
        
        listadoDocumentosArchivosItem=pdfs;
        
        if(listadoDocumentosArchivosItem.length>1)
        	$("#documentoUploadItem").val("Varios PDFs");
        if(listadoDocumentosArchivosItem.length===1)
        	$("#documentoUploadItem").val(file.name);
       
    }).on('cancel.bs.filedialog', function(ev) {
        $("#output").html("Cancelled!");
    });
};

function loadTableMovil(data){
	var colBusqueda = [
		{ data: "descripcion", className: "dt-left", targets: "_all" },
		{ data: "importe", className: "dt-center", targets: "_all" },
	    { data: "opciones", className: "dt-center", targets: "_all"  }
	   
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

function visualizaMovilidad(cont){
	var idTipo = '1';
	if(cont.val()==idTipo){
		$("#divTarifario").css("display", "block");
	}else{
		$("#divTarifario").css("display", "none");
	}
}

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

function eliminarDocumentoReferenciaRendicion(){
	
	waitingDialog.show('Espere por favor... eliminando Documento Referencia');
	var parametrospdf={
			idRendicion:rendicion.idRendicion
		};
	
	var contentType = 'application/pdf';
	var url = contextRoot+ 'eliminarArchivoPdf';
	
	$.ajax({
  	    url:url,
  	    type:'POST',
  	    data: JSON.stringify(parametrospdf),
  	    dataType: 'json',
  	    contentType: "application/json; charset=utf-8",
  	    success:function(resp){
  	    
  	    	if(resp.exito){
  	    		var texto= 'Se elimino correctamente.';
  		    	notificacionMensaje(texto,'success');
  		    	  
  	    	}else{
  	    		var texto= 'Hubo un problema en la eliminación del Documento referencia.';
  		    	notificacionMensaje(texto,'danger');
  		    	  
  	    	}
  	    	waitingDialog.hide();
  	    	$('#modalpdfviewer').hide();
  	    	$(".modal-backdrop").remove();
  	    	cargarConsulta();
  	    
	  	},
	
		error:function(res){
		
		  if(res.responseText==""){
	    	waitingDialog.hide();
	    	 var texto= 'Hubo un problema al intentar eliminar archivo.';
	    	  notificacionMensaje(texto,'danger');
	    	  return false;
		  }else{
			  if(resp.responseText.indexOf("containerLogin")>1){
	  	    		
	  	    		window.location="./login";
	  	    	}  
		  }
	    }
	
	});
		    	
}

function obtenerDetalleRendicionAdministrado(){
	
	detalleRendicionReq.idDetalleRendicion= $('#idDetalleRendicion').val();
	detalleRendicionReq.idRendicion=$('#idRendicion').val();
	detalleRendicionReq.fechatxt=$('#fechaItem').val();
	detalleRendicionReq.idClase=$('#selClaseItem').val();
	detalleRendicionReq.tipoClase=$('#selClaseItem').text();
	detalleRendicionReq.serieRecibo=$('#serieItem').val();
	detalleRendicionReq.numeroRecibo=$('#numeroDocumentoItem').val();
	detalleRendicionReq.idEspecifica=$('#selEspecificaItem').val();
	detalleRendicionReq.especifica=$('#selEspecificaItem').text();
	detalleRendicionReq.detalle=$('#detalleItem').val();
	detalleRendicionReq.razonSocial=$('#proveedorItem').val();
	detalleRendicionReq.rucEmpresa=$('#rucProveedorItem').val();
	detalleRendicionReq.importe=$('#importeProveedorItem').val();
	detalleRendicionReq.documentoReferencia=$('#documentoUploadItem').val();
	detalleRendicionReq.cantidadFolios=$('#foliosItem').val();
	detalleRendicionReq.listadoDocumentoArchivo= listadoDocumentosArchivosItem;
}

function obtenerRendicionAdministrado(){
	
	rendicionReq.idRendicion=		$('#idRendicion').val();
	rendicionReq.idDetalleRendicion=$("#idDetalleRendicion").val();
	rendicionReq.comentario=	   	   	$('#comentario').val();
	rendicionReq.fechaInicioTxt= 	$('#fechaComision').val();
	rendicionReq.documentoReferencia=$('#documentoUpload').val();
	rendicionReq.listadoDocumentoArchivo= listadoDocumentosArchivos;
	rendicionReq.listadoDetallesRendicion=listadoRendicionDetalle;
}
/*
function verificarAgregarItemRuta(){
	if($("#proveedorItem").val().length<3){
	  var texto= 'Debe digitar Nombre de empresa.';
  	  notificacionMensaje(texto,'danger');
  	  return false;
	}
	if($("#rucProveedorItem").val().length<9){
		  var texto= 'Debe digitar RUC de empresa.';
	  	  notificacionMensaje(texto,'danger');
	  	  return false;
	}
	return true;
}*/

$("#selEspecificaItem").change(function() { 
	visualizaMovilidad($(this));
});

$("#grabarRegistro").on("click",function(){
	grabarRegistro();
});

$("#grabarItem").on("click",function(){
	registrarDetalleItem();
});

$("#agregarTarifario").on("click",function(){
	//if(verificarAgregarItemRuta()){
	if(validarItemRendicionAdministrado()){
		registrarDetalleTarifario();
	}
});

$("#documentoUpload").on("click",function(){
	if(rendicion.documentoReferencia==null || rendicion.documentoReferencia==="" || $("#documentoUpload").val()==="")
		cargarDocumento();
	if((rendicion.documentoReferencia!=null && rendicion.documentoReferencia!="") || $("#documentoUpload").val()!="" )
		visualizaDocumentoReferencia();
});
$("#documentoUploadItem").on("click",function(){
	if(detalleRendicion.documentoReferencia==null || detalleRendicion.documentoReferencia==="" || $("#documentoUploadItem").val()==="")
		cargarDocumentoItem();
	if((detalleRendicion.documentoReferencia!=null && detalleRendicion.documentoReferencia!="") || $("#documentoUploadItem").val()!="" )
		visualizaDocumentoReferencia();
});

$("#eliminarDocumentoRef").on("click",function(event){
	event.preventDefault(); 
	eliminarDocumentoReferenciaRendicion();
});


//KUJA: 20191229

function validarRendicionAdministrado(){
	
	var flagRespuesta = true;
	
	if($('#fechaComision').val()=='')		{
				
		//alert('Por favor ingresar la Fecha de la Comisión');
		notificacionMensaje('Por favor ingresar la Fecha de la Comisión','danger');
		return false;
	}
	
	
	var vdataNaix = $('#fechaComision').val();
    var vregexNaix = /^([012][1-9]|3[01])(\/)(0[1-9]|1[012])\2(\d{4})$/;
    var vanyData = vdataNaix.substring(vdataNaix.length-4, 4);
    
	if((!vregexNaix.test(vdataNaix)) || (/^(\d{4})$/.test(vanyData))){
        //alert("La fecha de la comisión debe tener el siguiente formato dd/mm/aaaa.");
		notificacionMensaje('La fecha de la comisión debe tener el siguiente formato dd/mm/aaaa.','danger');
        return false;
    }
	
	return flagRespuesta;
}


function validarItemRendicionAdministrado(){
	
	var flagRespuesta = true;
	
	if($('#fechaItem').val()=='')		{		
		notificacionMensaje('Por favor ingresar la Fecha del Item','danger');
		return false;
	}
		
	var vdataNaix = $('#fechaItem').val();
    var vregexNaix = /^([012][1-9]|3[01])(\/)(0[1-9]|1[012])\2(\d{4})$/;
    var vanyData = vdataNaix.substring(vdataNaix.length-4, 4);
    
	if((!vregexNaix.test(vdataNaix)) || (/^(\d{4})$/.test(vanyData))){        
		notificacionMensaje('La fecha del Item debe tener el siguiente formato dd/mm/aaaa.','danger');
        return false;
    }
	
	if($('#selClaseItem').val()=='0')
	{		
		notificacionMensaje('Por favor seleccionar la clase de gasto','danger');
		return false;		
	}
	
	if($('#selEspecificaItem').val()=='0')
	{		
		notificacionMensaje('Por favor seleccionar la específica de gasto','danger');
		return false;		
	}
	
	if($('#importeProveedorItem').val()=='')		{		
		notificacionMensaje('Por favor ingresar el importe','danger');
		return false;
	}
	
	if($('#importeProveedorItem').val()<=0)		{		
		notificacionMensaje('El importe debe ser mayor a cero.','danger');
		return false;
	}
	
	
	if($('#selClaseItem').val()=='3' || $('#selClaseItem').val()=='4' || $('#selClaseItem').val()=='5' || $('#selClaseItem').val()=='6'){
	
		if($('#numeroDocumentoItem').val()=='')		{		
			notificacionMensaje('Por favor ingresar el Nº de documento','danger');
			return false;
		}
		
		if($('#proveedorItem').val()=='')		{		
			notificacionMensaje('Por favor ingresar el proveedor','danger');
			return false;
		}
		
		if($('#rucProveedorItem').val()=='')		{		
			notificacionMensaje('Por favor ingresar el N° de RUC','danger');
			return false;
		}
	}
	
	
	/*
	if($('#detalleItem').val()=='')		{		
		notificacionMensaje('Por favor ingresar el detalle del documento','danger');
		return false;
	}*/		
	
	
	if($('#rucProveedorItem').val()!='' && $('#rucProveedorItem').val().length!=11)		{
		
		notificacionMensaje('El RUC debe ser un número de 11 dígitos','danger');
		return false;
	}
	 
	/*
	if($('#importeProveedorItem').val()=='')		{	
		notificacionMensaje('Por favor ingresar el importe','danger');
		return false;
	}*/
	/*
	if($('#documentoUploadItem').val()=='')		{		
		notificacionMensaje('Por favor cargar el archivo de referencia','danger');
		return false;
	}
	
	if($('#foliosItem').val()=='')		{	
		notificacionMensaje('Por favor ingresar el N° de folios','danger');
		return false;
	}*/
			
	return flagRespuesta;
}

