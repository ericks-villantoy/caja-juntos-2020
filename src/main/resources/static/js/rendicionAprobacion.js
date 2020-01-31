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

function visualizar(id){
	
	if(listadoRendicionAdministrado.length>0){
		
		$.each(listadoRendicionAdministrado, function(key,rend) {
			if(rend.idRendicion===id){
				window.location="./rendicionAprobacionDetalle/"+id;
			}
		});
	}
}

function cargarConsulta(){
	listadoRendicionAdministrado.length = 0;
	var parametros={
			idSede:$("#selSedes").val(),
			fechaInicio:$("#fechaBusIni").val(),
			fechaFin:$("#fechaBusFin").val()
		  };
	
	var url = "./obtenerRendicionesAprobaciones"; 
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
  	    		
  	    		reg.opciones = 	'<a href=javascript:visualizar('+ reg.idRendicion+');>'+
								'<i class="glyphicon glyphicon-eye-open"></i>'+
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

$("#grabarRegistro").on("click",function(){
	grabarRegistro();
});


