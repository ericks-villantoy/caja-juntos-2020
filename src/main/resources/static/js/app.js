
$(document).ready(function() {
	var ventana_alto = $(window).height();
	$(".content-main").css("height", ventana_alto);
	
	$(".alert-info").hide();
	$(".alert-success").hide();
	$(".alert-warning").hide();
	$(".alert-danger").hide();
	 openNav(); 
	 activarMenu();
	$(".numeric").numeric({
			allowMinus   : false,
			allowThouSep : false
		});
	 $(".numeric").numeric("integer")
     $(".decimal").numeric({});
	
	 $("#logout").attr('href', contextRoot+"logout")
	 $("#cambioContrasena").attr('href', contextRoot+"cambioClave")
});

$(".closebtn").click(function(){
	closeNav();
});

function validar_email( email ) 
{
    var regex = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    return regex.test(email) ? true : false;
}

function activarMenu(){
	/*
	 * Colocar en memoria el menu
	 * */
	var menuPrincipal = $("#menuPrincipal").val();
	var menuActive = $("#menuActivo").val();
	
	if(menuPrincipal!=""){
	$("#menuHome").removeClass('active'); 
	$("#"+menuActive).addClass('active');
    $("#"+menuPrincipal).click();
	}
	
}

function openNav() {
	$(".side-menu").css("display", "block");
	$(".container").css("marginLeft", "250px");
	$(".container").css("width", "80%");
		
}

function closeNav() {
	$(".side-menu").css("display", "none");
	$(".container").css("marginLeft", "0");
	$(".container").css("width", "100%");
	
}

function notificacionMensaje(texto, clase){
	setTimeout(function(){

		$.gritter.add({
		title: 'Sistema de Rendiciones de Caja Chica',
		text: texto,
		sticky: false,
		class_name: clase,
		time: '4000'
	});

}, 100)
	$(".modal-backdrop").remove();
	$("body").css({ 'padding-right': '0' });
	return false;
	
}


function forzarOcultarModal(){
	waitingDialog.hide();
 	$("#modalcito").hide();
}


$('.modal').on('hide.bs.modal', function (e) {
    $("body").css("padding-right","0 !important");
    $(".modal-backdrop").css('z-index', 1040);
    
});

function b64toBlob(b64Data, contentType, sliceSize) {
	  contentType = contentType || '';
	  sliceSize = sliceSize || 512;

	  var byteCharacters = atob(b64Data);
	  var byteArrays = [];

	  for (var offset = 0; offset < byteCharacters.length; offset += sliceSize) {
	    var slice = byteCharacters.slice(offset, offset + sliceSize);

	    var byteNumbers = new Array(slice.length);
	    for (var i = 0; i < slice.length; i++) {
	      byteNumbers[i] = slice.charCodeAt(i);
	    }

	    var byteArray = new Uint8Array(byteNumbers);

	    byteArrays.push(byteArray);
	  }
	    
	  var blob = new Blob(byteArrays, {type: contentType});
	  return blob;
	}


function validaFechaDDMMAAAA(fecha){
	var dtCh= "/";
	
	var fechaActual = new Date();
	var ano = fechaActual.getFullYear();
	//Validacion para que no sea menor
	
	var minYear=ano;
	var maxYear=ano+1;
	function isInteger(s){
		var i;
		for (i = 0; i < s.length; i++){
			var c = s.charAt(i);
			if (((c < "0") || (c > "9"))) return false;
		}
		return true;
	}
	function stripCharsInBag(s, bag){
		var i;
		var returnString = "";
		for (i = 0; i < s.length; i++){
			var c = s.charAt(i);
			if (bag.indexOf(c) == -1) returnString += c;
		}
		return returnString;
	}
	function daysInFebruary (year){
		return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
	}
	function DaysArray(n) {
		for (var i = 1; i <= n; i++) {
			this[i] = 31
			if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
			if (i==2) {this[i] = 29}
		}
		return this
	}
	function isDate(dtStr){
		var daysInMonth = DaysArray(12)
		var pos1=dtStr.indexOf(dtCh)
		var pos2=dtStr.indexOf(dtCh,pos1+1)
		var strDay=dtStr.substring(0,pos1)
		var strMonth=dtStr.substring(pos1+1,pos2)
		var strYear=dtStr.substring(pos2+1)
		strYr=strYear
		if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
		if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
		for (var i = 1; i <= 3; i++) {
			if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
		}
		month=parseInt(strMonth)
		day=parseInt(strDay)
		year=parseInt(strYr)
		if (pos1==-1 || pos2==-1){
			return false
		}
		if (strMonth.length<1 || month<1 || month>12){
			return false
		}
		if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
			return false
		}
		if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
			return false
		}
		if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
			return false
		}
		return true
	}
	if(isDate(fecha)){
		return true;
	}else{
		return false;
	}
}

