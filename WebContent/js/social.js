//-----------------TOOLS social
var callAJAXP = false;
var callAJAXF = false;
var callAJAXT = false;
var callAJAXS = false;

function affiche(){
	var url = window.location.href;
	url = url.substring(url.lastIndexOf("#")+1)
	switchMenu(url+"m")
}


function switchMenu(item){
	$( "#prop" ).removeClass("active");
	$( "#prom1" ).removeClass("active");
	$( "#pro" ).css("display", "none");
	$( "#favp" ).removeClass("active");
	$( "#favm1" ).removeClass("active");
	$( "#fav" ).css("display", "none");
	$( "#trap" ).removeClass("active");
	$( "#tram1" ).removeClass("active");
	$( "#tra" ).css("display", "none");
	$( "#stap" ).removeClass("active");
	$( "#stam1" ).removeClass("active");
	$( "#sta" ).css("display", "none");

	if($(item).attr("id") == "prop" || $(item).attr("id") == "prom" || item == "prom"){
		$( "#prop" ).addClass("active");
		$( "#prom1" ).addClass("active");
		$( "#pro" ).css("display", "inline");
		
		if( !callAJAXP ){
			callAJAXP = true;
			callP();
		}
		
	}else if( $(item).attr("id") == "favp" || $(item).attr("id") == "favm" || item == "favm"){
		$( "#favp" ).addClass("active");
		$( "#favm1" ).addClass("active");
		$( "#fav" ).css("display", "inline");
		
		if( !callAJAXF ){
			callAJAXF = true;
			callF();
		}
		
	}else if( $(item).attr("id") == "trap" || $(item).attr("id") == "tram" || item == "tram"){
		$( "#trap" ).addClass("active");
		$( "#tram1" ).addClass("active");
		$( "#tra" ).css("display", "inline");
		
		if( !callAJAXT ){
			callAJAXT = true;
			callT();
		}
		
	}else if( $(item).attr("id") == "stap" || $(item).attr("id") == "stam" || item == "stam"){
		$( "#stap" ).addClass("active");
		$( "#stam1" ).addClass("active");
		$( "#sta" ).css("display", "inline");
		
		if( !callAJAXS ){
			callAJAXS = true;
			callS();
		}
		
	}
	
}


function follow(){
	$('#follow').modal('show');
	$("#buttonunfollow").css("display", "inline");
	$("#buttonfollow").css("display", "none");
	callFollow();
}

function unfollow(){
	$('#unfollow').modal('show');
	$("#buttonfollow").css("display", "inline");
	$("#buttonunfollow").css("display", "none");
	callUnfollow();
}

function callP(){
//profil
//personne que je suis
//personne qui me suivent
	
}

function callF(){
//Gare favoris
//trajet favoris
	
}

function callT(){

	
}

function callS(){
	
}

function callFollow(){
	
}

function callUnfollow(){
	
}