var menuOn = false;

function affiche(){
	displayAll();
	if ( isCo ){
		menuOn = true;
		var url = window.location.href;
		url = url.substring(url.lastIndexOf("#")+1);
		if(url.length > 1 && url.length < 5){
			switchMenu(url);
		}else{
			switchMenu("pro");
		}
		
	}else{
		$('#myModal').modal('show');
	}
	
	
}


function bestRider(){
	$.ajax({
		type: "POST",
		url : "search/bestrider",
		data : "user="+getCookie("id_user"),
		datType : "json",
		success: function(rep){
			var json = JSON.parse(rep);
			document.getElementById('bestRider').innerHTML = "<a href=\"prof.html?id="+json.id+"\" >" +json.name + " ("+json.max+")</a>";
		},
		error : function(jqXHR, textStatus, errorThrown) {
			$('#responserecerche').html("<p>num de gare innexistant</p>");
		}
	});
}

function trainTaken(){
	$.ajax({
		type: "POST",
		url : "search/getnbtraintaken",
		data : "user="+getCookie("id_user"),
		datType : "json",
		success: function(rep){
			var json = JSON.parse(rep);
			document.getElementById('trainTaken').innerHTML = json.nb;
		},
		error : function(jqXHR, textStatus, errorThrown) {
			$('#responserecerche').html("<p>num de gare innexistant</p>");
		}
	});
}

function statSearchedTrain(){
	$.ajax({
		type: "POST",
		url : "search/mostsearchedstation", // Tu t'es cru sur Needforspeed ?
		data : "user="+getCookie("id_user"),
		datType : "json",
		success: function(rep){
			var json = JSON.parse(rep);
			document.getElementById('GareMost').innerHTML = getNomGare(json.station) + " ("+json.num+")";
		},
		error : function(jqXHR, textStatus, errorThrown) {
			$('#responserecerche').html("<p>num de gare innexistant</p>");
		}
	});
}

function displayAll(){
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
}

function switchMenu(item){
	displayAll();
	if (menuOn){
		if($(item).attr("id") == "prop" || $(item).attr("id") == "prom" || item == "pro"){
			$( "#prop" ).addClass("active");
			$( "#prom1" ).addClass("active");
			$( "#pro" ).css("display", "inline");

			callP();


		}else if( $(item).attr("id") == "favp" || $(item).attr("id") == "favm" || item == "fav"){
			$( "#favp" ).addClass("active");
			$( "#favm1" ).addClass("active");
			$( "#fav" ).css("display", "inline");

			callF();


		}else if( $(item).attr("id") == "trap" || $(item).attr("id") == "tram" || item == "tra"){
			$( "#trap" ).addClass("active");
			$( "#tram1" ).addClass("active");
			$( "#tra" ).css("display", "inline");

			callT();


		}else if( $(item).attr("id") == "stap" || $(item).attr("id") == "stam" || item == "sta"){
			$( "#stap" ).addClass("active");
			$( "#stam1" ).addClass("active");
			$( "#sta" ).css("display", "inline");

			callS();


		}
	}

}


function callP(){
	$('#photoP').attr( "src", "http://graph.facebook.com/"+getCookie("id_fb")+"/picture" );
	$('#infoP').html( "<h3>"+ getCookie("lname") +" "+ getCookie("fname") +"</h3>" );
	
	$.ajax ({
		type : "POST" ,
		url: "social/listfollow",
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
		url: "social/listfollow",
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
		url: "train/listgare",
		data: "user="+getCookie("id_user"), 
		dataType : "json" ,
		success: function(rep){
			if(!(typeof (rep.code) == 'undefined')){
				erreurServlet(rep.code,rep.mess);
			}else{
				$('#listgare').html("");
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
		url : "search/trainatgare",
		data : "num="+num,
		dataType : "json",
		success : function(rep) {
			
			var tpl = "<div class=\"col-md-4\"><h5>"+ getNomGare(num) + "</h5></div><div class=\"col-md-4\"><button id=\"buttonunfav\" type=\"button\"  class=\"btn btn-danger\" onclick=\"unfav("+num+")\"  >Retirer des favoris</button></div>" +
					"<table class=\"table table-striped\"><tr><td>Heure</td><td>Misson</td><td>Num</td><td>Terminus</td><td>Gestion</td></tr>{{#passages}}{{#train}}" +
			"<tr><td>{{#date}}{{content}}{{/date}}</td><td>{{miss}}</td><td>{{num}}</td><td class='ttt'>{{term}}</td><td><button type=\"button\" class=\"btn btn-success\" " +
			" onclick=\"addTrain('{{num}}','{{#date}}{{content}}{{/date}}','"+num+"','{{term}}');\">Ajouter ce train</button></td></tr>{{/train}}</table>{{/passages}}";
			var html = Mustache.to_html(tpl, rep);
			$('#listgare').append(html);
			$('.ttt').each(function(i, obj) {
			    buff = getNomGare(obj.innerText);
			    obj.innerText = buff;
			});

		},
		error : function(jqXHR, textStatus, errorThrown) {
			
		}
	});

}

function unfav(num){
	$.ajax({
		type : "POST",
		url : "prof/supgarefavor",
		data : "token="+getCookie("key")+"&uic="+num+"&user="+getCookie("id_user"),
		dataType : "json",
		success : function(rep) {
			$('#unfav').modal('show');

		},
		error : function(jqXHR, textStatus, errorThrown) {
			
		}
	});

}

function callT(){
	$.ajax({
		type : "POST",
		url : "train/getmytrainf",
		data : "key="+getCookie("key")+"&user="+getCookie("id_user"),
		dataType : "json",
		success : function(rep) {
			var tpl = "<table class=\"table table-striped\"><tr><td>Depart</td><td>Heure</td><td>Num</td><td>Terminus</td><td>Gestion</td></tr>{{#train}}" +
			"<tr><td class='ttt'>{{numG}}</td><td>{{#date}}{{content}}{{/date}}</td><td><a href=\"train.html?numT={{num}}&date={{#date}}{{content}}{{/date}}&term={{term}}\">{{num}}</a></td><td class='ttt' >{{term}}</td></a><td><button type=\"button\" class=\"btn btn-danger\" " +
			" onclick=\"suppTrain('{{num}}','{{#date}}{{content}}{{/date}}');\">Suprimer ce train</button></td></tr>{{/train}}</table>";
			var html = Mustache.to_html(tpl, rep);
			$('#tav').html(html);
			$('.ttt').each(function(i, obj) {
			    buff = getNomGare(obj.innerText);
			    obj.innerText = buff;
			});
			

		},
		error : function(jqXHR, textStatus, errorThrown) {

		}
	});
	
	$.ajax({
		type : "POST",
		url : "train/getmytrainp",
		data : "key="+getCookie("key")+"&user="+getCookie("id_user"),
		dataType : "json",
		success : function(rep) {
			var tpl = "<table class=\"table table-striped\"><tr><td>Depart</td><td>Heure</td><td>Num</td><td>Terminus</td><td>Gestion</td></tr>{{#train}}" +
			"<tr><td class='ttt' >{{numG}}</td><td>{{date}}</td><td><a href=\"train.html?numT={{numT}}&date={{date}}&term={{term}}\">{{numT}}</a></td><td class='ttt' >{{term}}</td><td><button type=\"button\" class=\"btn btn-danger\" " +
			" onclick=\"suppTrain('{{numT}}','{{date}}');\">Suprimer ce train</button></td></tr>{{/train}}</table>";
			var html = Mustache.to_html(tpl, rep);
			$('#tpa').html(html);


		},
		error : function(jqXHR, textStatus, errorThrown) {

		}
	});
	

	$.ajax({
		type : "POST",
		url : "train/gettrainff",
		data : "key="+getCookie("key")+"&user="+getCookie("id_user"),
		dataType : "json",
		success : function(rep) {
			console.log(rep);
			
			var tpl = "<table class=\"table table-striped\"><tr><td>Depart</td><td>Heure</td><td>Num</td><td>Terminus</td><td>Nom</td></tr>{{#train}}" +
			"<tr><td class='ttt' >{{numG}}</td><td>{{date}}</td><td><a href=\"train.html?numT={{numT}}&date={{date}}&term={{term}}\">{{numT}}</a></td><td class='ttt'>{{term}}</td><td class='ami'>{{user_id}}</td></tr>{{/train}}</table>";
			var html = Mustache.to_html(tpl, rep);
			$('#tavf').html(html);  
			$('.ami').each(function(i, obj) {
				buff = obj.innerText;
				$.ajax ({
					type : "POST" ,
					url: "prof/getprofil",
					data: "id="+buff, 
					dataType : "json" ,
					success: function(rep){
						obj.innerHTML = '<a href="prof.html?id='+rep.id+'">' +rep.last_name +" "+ rep.first_name +'</a>';
					},
					error :function(jqXHR, textStatus , errorThrown ){

					}
				})

			});


		},
		error : function(jqXHR, textStatus, errorThrown) {

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
			$('#addT').modal('show');

		},
		error : function(jqXHR, textStatus, errorThrown) {
			
		}
	});
}

//TODO test
function suppTrain(numT,date){
	$.ajax({
		type : "POST",
		url : "train/supptrain",
		data : "key="+getCookie("key")+"&user="+getCookie("id_user")+"&numT="+numT+"&date="+date,
		dataType : "json",
		success : function(rep) {
			$('#suppT').modal('show');
			//TODO: see what to do
		},
		error : function(jqXHR, textStatus, errorThrown) {
			
		}
	});
}
