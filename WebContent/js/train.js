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
	affFollow();
	var url = $.urlParam('id');
	var id;
	
	$.ajax ({
		type : "POST" ,
		url: "train/gettrain",
		data: "id="+url, 
		dataType : "json" ,
		success: function(rep){
			if(!(typeof (rep.code) == 'undefined')){
				erreurServlet(rep.code,rep.mess);
			}else{
				
				
			} 
		},
		error :function(jqXHR, textStatus , errorThrown ){

		}
	})
	
	
	
	
}

