function affiche(){
	$.ajax ({
		type : "POST" ,
		url: "tweets",
		data: "n=30", 
		dataType : "json" ,
		success: function(rep){
			if(!(typeof (rep.code) == 'undefined')){
				erreurServlet(rep.code,rep.mess);
			}else{
				// var tpl = '{{#tweets}}<h4>{{user}}</h4><p>{{message}}</p><br><br>{{/tweets}}';
				var tpl = '{{#tweets}}<h4>{{user}}</h4><h5>{{date}}</h5><p>{{message}}</p><br>{{/tweets}}'
				var html = Mustache.to_html(tpl, rep);
			$('#actu').html(html);
				
			} 
		},
		error :function(jqXHR, textStatus , errorThrown ){

		}
	})
}