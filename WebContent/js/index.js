//-----------------TOOLS index
function switchMenu(bool){
	if(bool){
		
	}else{
		
	}
}


//-----------------Service serachGare

//Apelle a la servlet
function searchGare() {
		var num = $("#gareliste option:selected").val();
		console.log(num);
		$.ajax({
			type : "POST",
			url : "trainatgare",
			data : "num="+num,
			dataType : "json",
			success : function(rep) {
				var tpl = "<table class=\"table table-striped\"><tr><td>Heur</td><td>Misson</td><td>Num</td></tr>{{#passages}}{{#train}}" +
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
		console.log(num);
		$.ajax({
			type : "POST",
			url : "itineraire",
			data : "dep="+dep+"arr="+arr,
			dataType : "json",
			success : function(rep) {
				//TODOOOO
				var tpl = "<table class=\"table table-striped\"><tr><td>Heur</td><td>Misson</td><td>Num</td></tr>{{#passages}}{{#train}}" +
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