//-----------------TOOLS index
var menu = 1;
function switchMenu(bool){
	if(menu == -1 || menu == 0 && bool){
		menu = 1;
		$( "#pp" ).removeClass("active");
		$( "#ee" ).addClass("active");
		$("#page1").css("display", "inline");
		$("#page2").css("display", "none");
	}else if (menu == -1 || menu == 1 && !bool){
		$( "#ee" ).removeClass("active");
		$( "#pp" ).addClass("active");
		$("#page2").css("display", "inline");
		$("#page1").css("display", "none");
		menu = 0;

	}
}


//-----------------Service serachGare

//Apelle a la servlet
function searchGare() {
	var num = $("#gareliste option:selected").val();

	searchStation(num);

	if($('#chek').is(':checked') && isCo){
		addFav(num);
	}else if($('#chek').is(':checked') && !isCo){
		$('#popUp').modal('show');
	}

	$.ajax({
		type : "POST",
		url : "search/trainatgare",
		data : "num="+num,
		dataType : "json",
		success : function(rep) {
			var tpl = "<table class=\"table table-striped\"><tr><td>Heure</td><td>Misson</td><td>Num</td><td>Terminus</td><td>Gestion</td></tr>{{#passages}}{{#train}}" +
			"<tr><td>{{#date}}{{content}}{{/date}}</td><td>{{miss}}</td><td>{{num}}</td><td class='ttt'>{{term}}</td><td><button type=\"button\" class=\"btn btn-success\" " +
			" onclick=\"addTrain('{{num}}','{{#date}}{{content}}{{/date}}',"+num+",{{term}});\">Ajouter ce train</button></td></tr>{{/train}}{{/passages}}</table>";
			var html = Mustache.to_html(tpl, rep);
			$('#responserecerche').html(html);
			
			$('.ttt').each(function(i, obj) {
			    buff = getNomGare(obj.innerText);
			    obj.innerText = buff;
			});

		},
		error : function(jqXHR, textStatus, errorThrown) {
			$('#responserecerche').html("<p>num de gare innexistant</p>");
		}
	});

}
function addTrain(numT,date,numG,term){
	$.ajax({
		type : "POST",
		url : "train/addtrain",
		data : "key="+getCookie("key")+"&user="+getCookie("id_user")+"&numT="+numT+"&date="+date+"&numG="+numG+"&term="+term,
		dataType : "json", //CHECK?
		success : function(rep) {
			$('#popUp1').modal('show');

		},
		error : function(jqXHR, textStatus, errorThrown) {

		}
	});
}



function searchStation(num){
	$.ajax({
		type: "POST",
		url : "search/searchstation",
		data : "token="+getCookie("key")+"&uic="+num+"&user="+getCookie("id_user"),
		datType : "json",
		success: function(rep){
		},
		error : function(jqXHR, textStatus, errorThrown) {
			$('#responserecerche').html("<p>num de gare innexistant</p>");
		}
	});
}

function addFav(num){
	$.ajax({
		type : "POST",
		url : "prof/garefavor",
		data : "token="+getCookie("key")+"&uic="+num+"&user="+getCookie("id_user"),
		dataType : "json",
		success : function(rep) {
			var tpl = "<table class=\"table table-striped\"><tr><td>Heure</td><td>Misson</td><td>Num</td></tr>{{#passages}}{{#train}}" +
			"<tr><td>{{#date}}{{content}}{{/date}}</td><td>{{miss}}</td><td>{{num}}</td></tr>{{/train}}</table>{{/passages}}";
			var html = Mustache.to_html(tpl, rep);
			$('#responserecerche').html(html);

		},
		error : function(jqXHR, textStatus, errorThrown) {
			$('#responserecerche').html("<p>num de gare innexistant</p>");
		}
	});
}

//Changement de Menu dynamique (Radio options)
function choixRER(){
	$("#ter").css("display", "none");
	$("#tram").css("display", "none");
	$("#rer").css("display", "inline");
	$("#gare").css("display", "none");
}
function choixTER(){
	$("#ter").css("display", "inline");
	$("#tram").css("display", "none");
	$("#rer").css("display", "none");
	$("#gare").css("display", "none");
}
function choixTRAM(){
	$("#ter").css("display", "none");
	$("#tram").css("display", "inline");
	$("#rer").css("display", "none");
	$("#gare").css("display", "none");
}


//Changement de la liste déroulante
function choixTRAIN(s){
	var name = $("#mainform input[type='radio']:checked").val();
	$("#gare").css("display", "inline");
	$("#gareliste").html('<option>----------------</option>')
	switch(name) //mot-clé de la réponse du fichier php
	{
	case "a":
		$.each( tags, function( key, value ) {
			if (typeof value.fields.a !== 'undefined') {
				$("#gareliste").append('<option value="'+value.fields.code_uic +'"  >'+ value.fields.libelle_point_arret +'</option>');

			}
		});
		break;
	case "b":
		$.each( tags, function( key, value ) {
			if (typeof value.fields.b !== 'undefined') {
				$("#gareliste").append('<option value="'+value.fields.code_uic +'" >'+ value.fields.libelle_point_arret +'</option>');

			}
		});
		break;
	case "c":
		$.each( tags, function( key, value ) {
			if (typeof value.fields.c !== 'undefined') {
				$("#gareliste").append('<option value="'+value.fields.code_uic +'" >'+ value.fields.libelle_point_arret +'</option>');

			}
		});
		break;
	case "d":
		$.each( tags, function( key, value ) {
			if (typeof value.fields.d !== 'undefined') {
				$("#gareliste").append('<option value="'+value.fields.code_uic +'" >'+ value.fields.libelle_point_arret +'</option>');

			}
		});
		break;
	case "e":
		$.each( tags, function( key, value ) {
			if (typeof value.fields.e !== 'undefined') {
				$("#gareliste").append('<option value="'+value.fields.code_uic +'" >'+ value.fields.libelle_point_arret +'</option>');

			}
		});
		break;
	case "l":
		$.each( tags, function( key, value ) {
			if (typeof value.fields.l !== 'undefined') {
				$("#gareliste").append('<option value="'+value.fields.code_uic +'" >'+ value.fields.libelle_point_arret +'</option>');

			}
		});
		break;
	case "j":
		$.each( tags, function( key, value ) {
			if (typeof value.fields.j !== 'undefined') {
				$("#gareliste").append('<option value="'+value.fields.code_uic +'" >'+ value.fields.libelle_point_arret +'</option>');

			}
		});
		break;
	case "h":
		$.each( tags, function( key, value ) {
			if (typeof value.fields.h !== 'undefined') {
				$("#gareliste").append('<option value="'+value.fields.code_uic +'" >'+ value.fields.libelle_point_arret +'</option>');

			}
		});
		break;
	case "n":
		$.each( tags, function( key, value ) {
			if (typeof value.fields.n !== 'undefined') {
				$("#gareliste").append('<option value="'+value.fields.code_uic +'" >'+ value.fields.libelle_point_arret +'</option>');

			}
		});
		break;
	case "p":
		$.each( tags, function( key, value ) {
			if (typeof value.fields.p !== 'undefined') {
				$("#gareliste").append('<option value="'+value.fields.code_uic +'" >'+ value.fields.libelle_point_arret +'</option>');

			}
		});
		break;
	case "r":
		$.each( tags, function( key, value ) {
			if (typeof value.fields.r !== 'undefined') {
				$("#gareliste").append('<option value="'+value.fields.code_uic +'" >'+ value.fields.libelle_point_arret +'</option>');

			}
		});
		break;
	case "u":
		$.each( tags, function( key, value ) {
			if (typeof value.fields.u !== 'undefined') {
				$("#gareliste").append('<option value="'+value.fields.code_uic +'" >'+ value.fields.libelle_point_arret +'</option>');

			}
		});
		break;
	case "k":
		$.each( tags, function( key, value ) {
			if (typeof value.fields.k !== 'undefined') {
				$("#gareliste").append('<option value="'+value.fields.code_uic +'" >'+ value.fields.libelle_point_arret +'</option>');

			}
		});
		break;	
	default:
		$.each( tags, function( key, value ) {
			if (typeof value.fields.t4 !== 'undefined') {
				$("#gareliste").append('<option value="'+value.fields.code_uic +'" >'+ value.fields.libelle_point_arret +'</option>');

			}
		});
	break;
	}

}

//-----------------Service inineraire

//Apelle a la servlet
function searchIti() {
	var dep = $("#gareliste1 option:selected").val();
	var arr = $("#gareliste2 option:selected").val();
	$.ajax({
		type : "POST",
		url : "search/itineraire",
		data : "dep="+dep+"&arr="+arr,
		dataType : "json",
		success : function(rep) {
			var tpl = "<table class=\"table table-striped\"><tr><td>Heure</td><td>Misson</td><td>Num</td></tr>{{#passages}}{{#train}}" +
			"<tr><td>{{#date}}{{content}}{{/date}}</td><td>{{miss}}</td><td>{{num}}</td></tr>{{/train}}</table>{{/passages}}";
			var html = Mustache.to_html(tpl, rep);
			$('#responserecerche1').html(html);

		},
		error : function(jqXHR, textStatus, errorThrown) {
			$('#responserecerche1').html("<p>num de gare innexistant</p>");
		}
	});

}


//Changement de Menu dynamique (Radio options)
function choixRER1(){
	$("#ter1").css("display", "none");
	$("#tram1").css("display", "none");
	$("#rer1").css("display", "inline");
	$("#gare1").css("display", "none");
}
function choixTER1(){
	$("#ter1").css("display", "inline");
	$("#tram1").css("display", "none");
	$("#rer1").css("display", "none");
	$("#gare1").css("display", "none");
}
function choixTRAM1(){
	$("#ter1").css("display", "none");
	$("#tram1").css("display", "inline");
	$("#rer1").css("display", "none");
	$("#gar1e").css("display", "none");
}


//Changement de la liste déroulante
function choixTRAIN1(s){
	var name = $("#mainform1 input[type='radio']:checked").val();
	$("#gare1").css("display", "inline");
	$("#gare2").css("display", "inline");
	$("#gareliste1").html('<option>----------------</option>');
	$("#gareliste2").html('<option>----------------</option>');
	switch(name) //mot-clé de la réponse du fichier php
	{
	case "a":
		$.each( tags, function( key, value ) {
			if (typeof value.fields.a !== 'undefined') {
				$("#gareliste1").append('<option value="'+value.fields.code_uic +'" >'+ value.fields.libelle_point_arret +'</option>');$("#gareliste2").append('<option value="'+value.fields.code_uic +'" >'+ value.fields.libelle_point_arret +'</option>');

			}
		});
		break;
	case "b":
		$.each( tags, function( key, value ) {
			if (typeof value.fields.b !== 'undefined') {
				$("#gareliste1").append('<option value="'+value.fields.code_uic +'" >'+ value.fields.libelle_point_arret +'</option>');$("#gareliste2").append('<option value="'+value.fields.code_uic +'" >'+ value.fields.libelle_point_arret +'</option>');

			}
		});
		break;
	case "c":
		$.each( tags, function( key, value ) {
			if (typeof value.fields.c !== 'undefined') {
				$("#gareliste1").append('<option value="'+value.fields.code_uic +'" >'+ value.fields.libelle_point_arret +'</option>');$("#gareliste2").append('<option value="'+value.fields.code_uic +'" >'+ value.fields.libelle_point_arret +'</option>');

			}
		});
		break;
	case "d":
		$.each( tags, function( key, value ) {
			if (typeof value.fields.d !== 'undefined') {
				$("#gareliste1").append('<option value="'+value.fields.code_uic +'" >'+ value.fields.libelle_point_arret +'</option>');$("#gareliste2").append('<option value="'+value.fields.code_uic +'" >'+ value.fields.libelle_point_arret +'</option>');

			}
		});
		break;
	case "e":
		$.each( tags, function( key, value ) {
			if (typeof value.fields.e !== 'undefined') {
				$("#gareliste1").append('<option value="'+value.fields.code_uic +'" >'+ value.fields.libelle_point_arret +'</option>');$("#gareliste2").append('<option value="'+value.fields.code_uic +'" >'+ value.fields.libelle_point_arret +'</option>');

			}
		});
		break;
	case "l":
		$.each( tags, function( key, value ) {
			if (typeof value.fields.l !== 'undefined') {
				$("#gareliste1").append('<option value="'+value.fields.code_uic +'" >'+ value.fields.libelle_point_arret +'</option>');$("#gareliste2").append('<option value="'+value.fields.code_uic +'" >'+ value.fields.libelle_point_arret +'</option>');

			}
		});
		break;
	case "j":
		$.each( tags, function( key, value ) {
			if (typeof value.fields.j !== 'undefined') {
				$("#gareliste1").append('<option value="'+value.fields.code_uic +'" >'+ value.fields.libelle_point_arret +'</option>');$("#gareliste2").append('<option value="'+value.fields.code_uic +'" >'+ value.fields.libelle_point_arret +'</option>');

			}
		});
		break;
	case "h":
		$.each( tags, function( key, value ) {
			if (typeof value.fields.h !== 'undefined') {
				$("#gareliste1").append('<option value="'+value.fields.code_uic +'" >'+ value.fields.libelle_point_arret +'</option>');$("#gareliste2").append('<option value="'+value.fields.code_uic +'" >'+ value.fields.libelle_point_arret +'</option>');

			}
		});
		break;
	case "n":
		$.each( tags, function( key, value ) {
			if (typeof value.fields.n !== 'undefined') {
				$("#gareliste1").append('<option value="'+value.fields.code_uic +'" >'+ value.fields.libelle_point_arret +'</option>');$("#gareliste2").append('<option value="'+value.fields.code_uic +'" >'+ value.fields.libelle_point_arret +'</option>');

			}
		});
		break;
	case "p":
		$.each( tags, function( key, value ) {
			if (typeof value.fields.p !== 'undefined') {
				$("#gareliste1").append('<option value="'+value.fields.code_uic +'" >'+ value.fields.libelle_point_arret +'</option>');$("#gareliste2").append('<option value="'+value.fields.code_uic +'" >'+ value.fields.libelle_point_arret +'</option>');

			}
		});
		break;
	case "r":
		$.each( tags, function( key, value ) {
			if (typeof value.fields.r !== 'undefined') {
				$("#gareliste1").append('<option value="'+value.fields.code_uic +'" >'+ value.fields.libelle_point_arret +'</option>');$("#gareliste2").append('<option value="'+value.fields.code_uic +'" >'+ value.fields.libelle_point_arret +'</option>');

			}
		});
		break;
	case "u":
		$.each( tags, function( key, value ) {
			if (typeof value.fields.u !== 'undefined') {
				$("#gareliste1").append('<option value="'+value.fields.code_uic +'" >'+ value.fields.libelle_point_arret +'</option>');$("#gareliste2").append('<option value="'+value.fields.code_uic +'" >'+ value.fields.libelle_point_arret +'</option>');

			}
		});
		break;
	case "k":
		$.each( tags, function( key, value ) {
			if (typeof value.fields.k !== 'undefined') {
				$("#gareliste1").append('<option value="'+value.fields.code_uic +'" >'+ value.fields.libelle_point_arret +'</option>');$("#gareliste2").append('<option value="'+value.fields.code_uic +'" >'+ value.fields.libelle_point_arret +'</option>');

			}
		});
		break;	
	default:
		$.each( tags, function( key, value ) {
			if (typeof value.fields.t4 !== 'undefined') {
				$("#gareliste1").append('<option value="'+value.fields.code_uic +'" >'+ value.fields.libelle_point_arret +'</option>');$("#gareliste2").append('<option value="'+value.fields.code_uic +'" >'+ value.fields.libelle_point_arret +'</option>');

			}
		});
	break;
	}

}
