/**
 * 
 */
if($('#idDetalleRendicion').val().length>0 && ($('#idDetalleRendicion').val().indexOf("x") < 0)){
            		
            			listadoRendicionDetalle.forEach(function(item){
            			  if(item.idDetalleRendicion ==$('#idDetalleRendicion').val()){
            				  item = detalle;
            				  item.idDetalleRendicion ==$('#idDetalleRendicion').val()
            			  }
            			})
            			detalle.opciones='<a href=javascript:editarItem('+ detalle.idDetalleRendicion+');>'+
        				'<i class="glyphicon glyphicon-pencil"></i>'+
        				'</a>&nbsp;&nbsp;<a title="Dar de Baja Registro" href=javascript:darBajaItem('+ detalle.idDetalleRendicion+');>'+
        				'<i class="glyphicon glyphicon-off"></i>'+
        				'</a>&nbsp;&nbsp;';
                    	$('#grabarItem').removeAttr("disabled");
                    	$("#grabarItem").prop( "disabled", false);
                    	
                    	loadTableItems(listadoRendicionDetalle);
                    	$('#modalRendicionItem').modal('hide');
            		
            	}else{
            		detalle.opciones='<a href=javascript:editarItem('+ detalle.idDetalleRendicion+');>'+
    				'<i class="glyphicon glyphicon-pencil"></i>'+
    				'</a>&nbsp;&nbsp;<a title="Dar de Baja Registro" href=javascript:darBajaItem('+ detalle.idDetalleRendicion+');>'+
    				'<i class="glyphicon glyphicon-off"></i>'+
    				'</a>&nbsp;&nbsp;';
                	listadoRendicionDetalle.push(detalle);
                	$('#grabarItem').removeAttr("disabled");
                	$("#grabarItem").prop( "disabled", false);
                	
                	loadTableItems(listadoRendicionDetalle);
                	$('#modalRendicionItem').modal('hide');
            		
            	}
            	
            	
            	
            	
            	