$.urlParam = function(name){
    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
    if (results==null){
       return null;
    }
    else{
       return results[1] || 0;
    }
}

function affiche(){
	var numT = $.urlParam('numT');
	var date = $.urlParam('date');
	var term = $.urlParam('term');
	
	$.ajax ({
		type : "POST" ,
		url: "train/gettrain",
		data: "numT="+numT+"&date="+date+"&term="+term, 
		dataType : "json" ,
		success: function(rep){
			var tpl = "<h4> Information du train <h4>" +
					"<h5>Numéro du train : "+ numT+"<h5>" +
					"<h5>Terminus : "+ getNomGare(term)+"<h5>" +
					" <table class=\"table table-striped\"><tr><td>Qui le prends</td><td>Où</td><td>A quel heure</td></tr>{{#train}}" +
			"<tr><td class='ami'>{{user_id}}</td><td class='ttt'>{{numG}}</td><td>{{date}}</td></tr>{{/train}}</table>";
			var html = Mustache.to_html(tpl, rep);
			$('#trainR').html(html);
			$('.ttt').each(function(i, obj) {
			    buff = getNomGare(obj.innerText);
			    obj.innerText = buff;
			});
			$('.ami').each(function(i, obj) {
			    buff = obj.innerText;
			    $('.ami').each(function(i, obj) {
				    
				    $.ajax ({
						type : "POST" ,
						url: "prof/getprofil",
						data: "id="+buff, 
						dataType : "json" ,
						success: function(rep){
								obj.html( '<a href=\"prof.html?id=\"{{user_id}}\">' +rep.last_name +" "+ rep.first_name +'</a>');
						},
						error :function(jqXHR, textStatus , errorThrown ){

						}
					})
				});
			});
		},
		error :function(jqXHR, textStatus , errorThrown ){

		}
	})
	
	
	
	
}

