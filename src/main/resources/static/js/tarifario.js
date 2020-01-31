var table;
var listadoRutasSede=[];

$(document).ready(function() {
	
	$("body").css({ 'padding-right': '0' });
	$(".content-main").css("height","788px");
	
	
});

function limpiar(){
	$('#idRutaSede').val('');
	$('#rutaTarifario').val('');
	$('#tarifa').val('');
	$('#rutaInicio').val('');
	$('#rutaFin').val('');
	
}

function nuevoTarifario(){
	limpiar();
	$('#modalTarifario').modal('show');
		    	
}

function editar(id){
	
	if(listadoRutasSede.length>0){
		
		$.each(listadoRutasSede, function(key,tari) {
			if(tari.idRutaSede===id){
				$('#idRutaSede').val(tari.idRutaSede);
				$('#selSedeTarifario').val(tari.sede.idSede);
				$('#rutaTarifario').val(tari.codigoRuta);
				$('#tarifa').val(tari.tarifa);
				$('#rutaInicio').val(tari.descripcionUbigeoPartida);
				$('#rutaFin').val(tari.descripcionUbigeoLlegada);
				$('#modalTarifario').modal('show');
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
            				idRutaSede:id,
            				eliminar:true
            	        };
            		
            		waitingDialog.show('Espere por favor... Procesando');
            		$.ajax({
            		    url:'./grabarRutaSede',
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
	listadoRutasSede.length = 0;
	var parametros={
			idSede:$("#selSedes").val(),
			nombreRuta:$("#ruta").val()
		  };
	
	if($('#cb_activos').is(':checked')){
		parametros.activo=true;
	}
	
	var url = "./obtenerTarifarioRutas"; 
	waitingDialog.show('Espere por favor... Listando tarifario Filtrados');
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
  	    	listadoRutasSede=lista;
  	    	
  	    	$.each(lista, function(key,reg) {
  	    		
  	    			reg.opciones = 	'<a href=javascript:editar('+ reg.idRutaSede+');>'+
					'<i class="glyphicon glyphicon-pencil"></i>'+
					'</a>&nbsp;&nbsp;<a title="Dar de Baja Registro" href=javascript:darBaja('+ reg.idRutaSede+');>'+
					'<i class="glyphicon glyphicon-off"></i>'+
					'</a>&nbsp;&nbsp;';
					
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
  	    	 var texto= 'Hubo un problema con la obtencion de las RSEs.';
  	    	  notificacionMensaje(texto,'danger');
  	    	  return false;
  	    }
  	});

	
};

function grabarRegistro(){
	
	
	if($('#selSedeTarifario').val()=='0')
	{
		//alert("Por favor seleccionar la Sede");
		notificacionMensaje('Por favor seleccionar la Sede','danger');
		return;
	}
	
		
	if($('#rutaTarifario').val()=='')
	{
		//alert("Por favor ingresar el nombre de la ruta");
		notificacionMensaje('Por favor ingresar el nombre de la ruta','danger');
		return;
	}
	
	if($('#tarifa').val()=='')
	{
		//alert("Por favor ingresar la tarifa");
		notificacionMensaje('Por favor ingresar la tarifa','danger');
		return;
	}
	
	if($('#tarifa').val()==0)
	{
		//alert("La Tarifa ingresada debe ser mayor a cero");
		notificacionMensaje('La Tarifa ingresada debe ser mayor a cero','danger');
		return;
	}
	
	
	if($('#rutaInicio').val()=='')
	{
		//alert("Por favor ingresar la ruta inicial");
		notificacionMensaje('Por favor ingresar la ruta inicial','danger');
		return;
	}
	
	if($('#rutaFin').val()=='')
	{
		//alert("Por favor ingresar la ruta final");
		notificacionMensaje('Por favor ingresar la ruta final','danger');
		return;
	}
	
	
	$("#grabarRegistro").prop( "disabled", true);
	
	var parametros={
			idRutaSede:		   $('#idRutaSede').val(),
			idSede:		   	   $('#selSedeTarifario').val(),
			codigoRuta:	   	   $('#rutaTarifario').val(),
			tarifa:   		   $('#tarifa').val().replace("S/.",""),
			descripcionUbigeoPartida:   $('#rutaInicio').val(),
			descripcionUbigeoLlegada:   $('#rutaFin').val()
		  };
	
	var url = "./grabarRutaSede";
	
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
              	    	  
              	    	}else{
              	    		var texto= resp.respuesta;
                	    	notificacionMensaje(texto,'danger');
                	    	
              	    	}
              	    	$('#modalTarifario').modal('hide');
           	  	      	$('#modalTarifario').hide();
              	    	$('#grabarRegistro').removeAttr("disabled");
                    	$("#grabarRegistro").prop( "disabled", false);
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

function loadTable(data){
	var colBusqueda = [
	    
		{ data: "codigoRuta", className: "dt-left", targets: "_all" },
		{ data: "descripcionUbigeoPartida", className: "dt-left", targets: "_all" },
	    { data: "descripcionUbigeoLlegada", className: "dt-left", targets: "_all" },
	    { data: "tarifa", className: "dt-left", targets: "_all"  },
	    { data: "opciones", className: "dt-center", targets: "_all"  }
	   
	];
	
	if (table) {
        table.destroy();
        $('#listadoTarifario > tbody').empty();
      }
	table = $('#listadoTarifario').DataTable( {
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

$("#grabarRegistro").on("click",function(){
	grabarRegistro();
});


