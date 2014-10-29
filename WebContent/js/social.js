
function affiche(){
	var url = window.location.href;
	url = url.substring(url.lastIndexOf("#")+1)
	if(!(url.length >= 5)){
		switchMenu(url+"m")
	}else{
		switchMenu("prom");
	}
	
}


function statSearchedTrain(){
	$.ajax({
		type: "POST",
		url : "MostSearchedStation",
		data : "user="+getCookie("id_user"),
		datType : "json",
		success: function(rep){
			var json = JSON.parse(rep);
			document.getElementById('GareMost').innerHTML = json.station + " ("+json.num+")";
		},
		error : function(jqXHR, textStatus, errorThrown) {
			$('#responserecerche').html("<p>num de gare innexistant</p>");
		}
	});
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
		
			callP();
	
		
	}else if( $(item).attr("id") == "favp" || $(item).attr("id") == "favm" || item == "favm"){
		$( "#favp" ).addClass("active");
		$( "#favm1" ).addClass("active");
		$( "#fav" ).css("display", "inline");
		
			callF();
		
		
	}else if( $(item).attr("id") == "trap" || $(item).attr("id") == "tram" || item == "tram"){
		$( "#trap" ).addClass("active");
		$( "#tram1" ).addClass("active");
		$( "#tra" ).css("display", "inline");
		
			callT();
		
		
	}else if( $(item).attr("id") == "stap" || $(item).attr("id") == "stam" || item == "stam"){
		$( "#stap" ).addClass("active");
		$( "#stam1" ).addClass("active");
		$( "#sta" ).css("display", "inline");
		
			callS();
		
		
	}
	
}


function callP(){
	$('#photoP').attr( "src", "http://graph.facebook.com/"+getCookie("id_fb")+"/picture" );
	$('#infoP').append( "<h3>"+ getCookie("lname") +" "+ getCookie("fname") +"</h3>" );
	
	$.ajax ({
		type : "POST" ,
		url: "ListFollow",
		data: "quiJeSuis="+getCookie("id_user"), 
		dataType : "json" ,
		success: function(rep){
			if(!(typeof (rep.code) == 'undefined')){
				erreurServlet(rep.code,rep.mess);
			}else{
				
				
				
				var tpl = '{{#list}}<div class="col-md-3"><a href="prof.html?id={{id_user}}">'+
					'<img src="http://graph.facebook.com/{{id_fb}}/picture" height="50px" width="50px">' +
					'<p>{{name}}</p></a></div>{{/list}}';
				var html = Mustache.to_html(tpl, rep);
				console.log(html);
				$('#whofollow').html(html);
				
			} 
		},
		error :function(jqXHR, textStatus , errorThrown ){

		}
	})
	
		$.ajax ({
		type : "POST" ,
		url: "ListFollow",
		data: "quiMeSuis="+getCookie("id_user"), 
		dataType : "json" ,
		success: function(rep){
			if(!(typeof (rep.code) == 'undefined')){
				erreurServlet(rep.code,rep.mess);
			}else{
				
				
				
				var tpl = '{{#list}}<div class="col-md-3"><a href="prof.html?id={{id_user}}">'+
					'<img src="http://graph.facebook.com/{{id_fb}}/picture" height="50px" width="50px">' +
					'<p>{{name}}</p></a></div>{{/list}}';
				var html = Mustache.to_html(tpl, rep);
				console.log(html);
				$('#whofollowme').html(html);
				
			} 
		},
		error :function(jqXHR, textStatus , errorThrown ){

		}
	})
	
}

function callF(){
	$.ajax ({
		type : "POST" ,
		url: "listgare",
		data: "user="+getCookie("id_user"), 
		dataType : "json" ,
		success: function(rep){
			if(!(typeof (rep.code) == 'undefined')){
				erreurServlet(rep.code,rep.mess);
			}else{
				for (var i = 0; i < rep.list.length; i++) {
				    var counter = rep.list[i];
				     addGare(counter.uic); 
				   
				}
				
				
				
			} 
		},
		error :function(jqXHR, textStatus , errorThrown ){

		}
	})
	
}

function addGare(num){
	$.ajax({
		type : "POST",
		url : "trainatgare",
		data : "num="+num,
		dataType : "json",
		success : function(rep) {
			
			var tpl = "<div class=\"col-md-4\"><h5>Nom :"+ num + "</h5></div><div class=\"col-md-4\"><button id=\"buttonunfav\" type=\"button\"  class=\"btn btn-danger\" onclick=\"unfav("+num+")\"  >Retirer des favoris</button></div>" +
					"<table class=\"table table-striped\"><tr><td>Heur</td><td>Misson</td><td>Num</td><td>Terminus</td><td>Gestion</td></tr>{{#passages}}{{#train}}" +
			"<tr><td>{{#date}}{{content}}{{/date}}</td><td>{{miss}}</td><td>{{num}}</td><td>{{term}}</td><td><button type=\"button\" class=\"btn btn-success\" " +
			" onclick=\"addTrain('{{num}}','{{#date}}{{content}}{{/date}}','"+num+"','{{term}}');\">Ajouter ce train</button></td></tr>{{/train}}</table>{{/passages}}";
			var html = Mustache.to_html(tpl, rep);
			$('#listgare').append(html);

		},
		error : function(jqXHR, textStatus, errorThrown) {
			$('#responserecerche').html("<p>num de gare innexistant</p>");
		}
	});

}

function unfav(num){
	$.ajax({
		type : "POST",
		url : "SupGareFavor",
		data : "token="+getCookie("key")+"&uic="+num+"&user="+getCookie("id_user"),
		dataType : "json",
		success : function(rep) {
			$('#unfav').modal('show');

		},
		error : function(jqXHR, textStatus, errorThrown) {
			$('#responserecerche').html("<p>num de gare innexistant</p>");
		}
	});

}

function callT(){
	$.ajax({
		type : "POST",
		url : "getmytrainf",
		data : "key="+getCookie("key")+"&user="+getCookie("id_user"),
		dataType : "json",
		success : function(rep) {
			var tpl = "<table class=\"table table-striped\"><tr><td>Depart</td><td>Heur</td><td>Misson</td><td>Num</td><td>Terminus</td><td>Gestion</td></tr>{{#train}}" +
			"<tr><td>{{numG}}</td><td>{{#date}}{{content}}{{/date}}</td><td>{{miss}}</td><td>{{num}}</td><td>{{term}}</td><td><button type=\"button\" class=\"btn btn-danger\" " +
			" onclick=\"supTrain('{{num}}','{{#date}}{{content}}{{/date}}');\">Suprimer ce train</button></td></tr>{{/train}}</table>";
			var html = Mustache.to_html(tpl, rep);
			$('#tav').append(html);

		},
		error : function(jqXHR, textStatus, errorThrown) {

		}
	});

}

function addTrain(numT,date,numG,term){
	$.ajax({
		type : "POST",
		url : "addtrain",
		data : "key="+getCookie("key")+"&user="+getCookie("id_user")+"&numT="+numT+"&date="+date+"&numG="+numG+"&term="+term,
		dataType : "json",
		success : function(rep) {
			$('#addT').modal('show');

		},
		error : function(jqXHR, textStatus, errorThrown) {
			
		}
	});
}

function suppTrain(numT,date){
	
}
