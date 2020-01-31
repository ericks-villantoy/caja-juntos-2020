var table;
var listadoAperturaCierre=[];

$(document).ready(function() {
	
	$("body").css({ 'padding-right': '0' });
	$(".content-main").css("height","788px");
	$("#sedeF").text(sede.descripcionSede);
		
	document.getElementById("selAnios").value=selAnio.anio;		
	$("#sedeSel").val(sede.idSede);
	
	//$("#fechaBusIni").mask("99/99/9999");
	//$("#fechaBusFin").mask("99/99/9999");
	cargarConsulta();
	
	
	
});


function limpiar(){
	$('#idAperturaCierre').val('');
	$('#selSedeApertura').val('0');
	$('#selAniosApertura').val('0');	
	$('#selEspecifica').val('0');	
	$('#montoApertura').val('');
	$('#montoLiquidacion').val('');
	$('#montoEjecucion').val('');
	$('#saldo').val('');
	$('#descCerrado').val('');
	
	
}

function nuevaApertura(){
	limpiar();
	$('#modalApertura').modal('show');
		    	
}

function editar(id){
	
	if(listadoAperturaCierre.length>0){
		
		$.each(listadoAperturaCierre, function(key,apert) {
			if(apert.idAperturaCierre===id){
				$('#idAperturaCierre').val(apert.idAperturaCierre);
				$('#selSedeApertura').val(apert.sede.idSede);				
				$('#selAniosApertura').val(apert.anio);
				$('#selEspecifica').val(apert.especifica.idEspecifica);				
				$('#montoApertura').val(apert.montoApertura);
				$('#montoLiquidacion').val(apert.montoLiquidacion);
				$('#montoEjecucion').val(apert.montoEjecucion);
				$('#saldo').val(apert.saldo);
				$('#descCerrado').val(apert.descCerrado);
				$('#modalApertura').modal('show');
			}
		});
	}
}


function cargarConsulta(){
	listadoAperturaCierre.length = 0;
	var parametros={
			codRegion:$("#sedeSel").val(),
			anio:$("#selAnios").val()
		  };
	
	var url = "./obtenerAperturasCaja"; 
	waitingDialog.show('Espere por favor... Listando Apertura por Específica');
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
  	    	listadoAperturaCierre=lista;
  	    	
  	    	$.each(lista, function(key,reg) {
  	    		
  	    		reg.conceptoX = reg.especifica.descripcionEspecifica;
  	    		reg.clasificadorX = reg.especifica.codigoCalse;
  	    		
  	    		reg.opciones = 	'<a href=javascript:editar('+ reg.idAperturaCierre+');>'+
								'<i class="glyphicon glyphicon-pencil"></i>'+
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
  	    	 var texto= 'Hubo un problema con la obtencion del registro de apertura.';
  	    	  notificacionMensaje(texto,'danger');
  	    	  return false;
  	    }
  	});

	
};

function loadTable(data){
	var colBusqueda = [
	    
		{ data: "anio", className: "dt-center", targets: "_all" },
		{ data: "conceptoX", className: "dt-center", targets: "_all" },
		{ data: "clasificadorX", className: "dt-left", targets: "_all" },
	    { data: "montoApertura", className: "dt-center", targets: "_all" },
	    { data: "montoLiquidacion", className: "dt-center", targets: "_all"  },
	    { data: "montoEjecucion", className: "dt-center", targets: "_all"  },
	    { data: "saldo", className: "dt-center", targets: "_all"  },
	    { data: "opciones", className: "dt-center", targets: "_all"  }
	   
	];
	
	if (table) {
        table.destroy();
        $('#listadoAperturaCierre > tbody').empty();
      }
	table = $('#listadoAperturaCierre').DataTable( {
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



function grabarRegistro(){
	
	
	if($('#selSedeApertura').val()=='0')
	{		
		notificacionMensaje('Por favor seleccionar la Sede','danger');
		return;
	}
	
	if($('#selAniosApertura').val()=='0')
	{		
		notificacionMensaje('Por favor seleccionar el Año','danger');
		return;
	}
	if($('#selEspecifica').val()=='0')
	{		
		notificacionMensaje('Por favor seleccionar la Específica','danger');
		return;
	}
	
	if($('#montoApertura').val()=='')
	{		
		notificacionMensaje('Por favor ingresar el monto de apertura','danger');
		return;
	}	
	
	$("#grabarRegistro").prop( "disabled", true);
	
	var parametros={
			idAperturaCierre:		   $('#idAperturaCierre').val(),
			idSede:		   	   $('#selSedeApertura').val(),
			anio:		   	   $('#selAniosApertura').val(),
			idEspecifica:		   	   $('#selEspecifica').val(),			
			montoApertura:   		   $('#montoApertura').val().replace("S/.",""),
			montoLiquidacion:   		   $('#montoLiquidacion').val().replace("S/.","")
		  };
	
	var url = "./grabarAperturaCierre";
	
	BootstrapDialog.confirm({
        title: 'Registro de Apertura',
        message: '¿Estás seguro de registrar la Apertura de la Especifica de Gasto?',
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
              	    	$('#modalApertura').modal('hide');
           	  	      	$('#modalApertura').hide();
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


$("#grabarRegistro").on("click",function(){
	grabarRegistro();
});
