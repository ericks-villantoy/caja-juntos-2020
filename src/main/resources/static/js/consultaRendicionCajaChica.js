var table;
var listadoRendicionDetalle=[];

$(document).ready(function() {
	
	$("body").css({ 'padding-right': '0' });
	$(".content-main").css("height","788px");
	$("#sedeF").text(sede==null?'':sede.descripcionSede);
	cargarConsulta();
});

function cargarConsulta(){
	listadoRendicionDetalle.length = 0;
	
	var idSedeReq;
	if(sede==null){
		idSedeReq = $("#selSedes").val();
	}else{
		idSedeReq = sede.idSede;
	}
	
	var parametros={
			idRendicionCajaChica:$("#selRendicionesCajaChica").val(),
			idSede:idSedeReq
		  };
	
	var url = "./obtenerConsultaCajaChica"; 
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

function actualizaTabla(lista){
	var miJson=[];
  	listadoRendicionDetalle=lista;
  	
  	$.each(lista, function(key,reg) {
  		
  		reg.fechRendicion=reg.rendicionCajaChica.fechaRendicionCajaChicaTxt;
  		reg.importeTotal = reg.rendicionCajaChica.importe;
  		reg.estadoRendicionDescripcion = reg.rendicionCajaChica.estado=='1'?'Registrado':'';
		miJson.push(reg);
  	});
  	
  	loadTable(miJson);
}


function loadTable(data){
	var colBusqueda = [
		{ data: "fechRendicion", className: "dt-center", targets: "_all" },
		{ data: "importeTotal", className: "dt-center", targets: "_all" },
	    { data: "estadoRendicionDescripcion", className: "dt-center", targets: "_all"  }
	   
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
	
	var req =	{
				listadoRendicionCajachica:listadoRendicionDetalle
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

$("#exportarExcel").on("click",function(){
	waitingDialog.show('Espere por favor... recuperando información');
	
	if(listadoRendicionDetalle.length==0){
		var texto= 'No hay registros a exportar excel.';
	    notificacionMensaje(texto,'warning');
	    return false;
		
	}
	var idSedeReq;
	if(sede==null){
		idSedeReq = $("#selSedes").val();
	}else{
		idSedeReq = sede.idSede;
	}
	
	var parametros={
			listadoExcel:listadoRendicionDetalle,
			idSede:idSedeReq
		  };
	
	var url = "./generarExcelConsultaCajaChica";
  	
	$.ajax({
  	    url:url,
  	    type:'POST',
  	    dataType: 'json',
  	    data: JSON.stringify(parametros),
  	    contentType: "application/json; charset=utf-8",
  	    success:function(resp){
  	    	var url = resp.mBase64Archivo;
  	    	var nombre = resp.mNombreArchivo;
  	    	$('#miXls').val(url);
  	    	$('#nombreXls').val(nombre);
	  	  	$('#frmExportarExcel').submit();
  	    	
 		  	 waitingDialog.hide();
  	    	  
  	    },
  	    error:function(resp){
  	    	 var texto= 'Hubo un problema con la generacion del excel.';
  	    	  notificacionMensaje(texto,'danger');
  	    	  return false;
  	    }
  	});
});

$("#observarRegistro").on("click",function(){
	observarRegistro();
});

$(".seltodo").on("click",function(){
	seleccionarTodo();
});

