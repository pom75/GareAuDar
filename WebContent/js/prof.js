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
		url: "GetProfil",
		data: "id="+url, 
		dataType : "json" ,
		success: function(rep){
			if(!(typeof (rep.code) == 'undefined')){
				erreurServlet(rep.code,rep.mess);
			}else{
				id = rep.id;
				$('#photoP').attr( "src", "http://graph.facebook.com/"+rep.id_fb+"/picture" );
				$('#infoP').append( "<h3>"+ rep.last_name +" "+ rep.first_name +"</h3>" );
				
			} 
		},
		error :function(jqXHR, textStatus , errorThrown ){

		}
	})
	
	
	$.ajax ({
		type : "POST" ,
		url: "ListFollow",
		data: "quiJeSuis="+url, 
		dataType : "json" ,
		success: function(rep){
			if(!(typeof (rep.code) == 'undefined')){
				erreurServlet(rep.code,rep.mess);
			}else{
				
				
				
				var tpl = '{{#list}}<div class="col-md-3"><a href="prof.html?id={{id_user}}">'+
					'<img src="http://graph.facebook.com/{{id_fb}}/picture" height="50px" width="50px">' +
					'<p>{{name}}</p></a></div>{{/list}}';
				var html = Mustache.to_html(tpl, rep);
				$('#whofollow').html(html);
				
			} 
		},
		error :function(jqXHR, textStatus , errorThrown ){

		}
	})
	
		$.ajax ({
		type : "POST" ,
		url: "ListFollow",
		data: "quiMeSuis="+url, 
		dataType : "json" ,
		success: function(rep){
			if(!(typeof (rep.code) == 'undefined')){
				erreurServlet(rep.code,rep.mess);
			}else{
				
				for (var i = 0; i < rep.list.length; i++) {
				    var counter = rep.list[i];
				    if( counter.id_user == getCookie("id_user")){
				    	affUnFollow();
				    }
				}
				
				var tpl = '{{#list}}<div class="col-md-3"><a href="prof.html?id={{id_user}}">'+
					'<img src="http://graph.facebook.com/{{id_fb}}/picture" height="50px" width="50px">' +
					'<p>{{name}}</p></a></div>{{/list}}';
				var html = Mustache.to_html(tpl, rep);
				$('#whofollowme').html(html);
				
			} 
		},
		error :function(jqXHR, textStatus , errorThrown ){

		}
	})
	
}

function affFollow(){
	$( "#buttonfollow" ).css("display", "inline");
	$( "#buttonunfollow" ).css("display", "none");
}

function affUnFollow(){
	$( "#buttonfollow" ).css("display", "none");
	$( "#buttonunfollow" ).css("display", "inline");
}


function follow(){
	$("#buttonunfollow").css("display", "inline");
	$("#buttonfollow").css("display", "none");
	callFollow();
}

function unfollow(){
	$("#buttonfollow").css("display", "inline");
	$("#buttonunfollow").css("display", "none");
	callUnfollow();
}


function callFollow(){
	
	$.ajax ({
		type : "POST" ,
		url: "AddFollow",
		data: "user1="+getCookie("id_user")+"&user2="+$.urlParam('id')+"&key="+getCookie("key"), 
		dataType : "json" ,
		success: function(rep){
			if(!(typeof (rep.code) == 'undefined')){
				erreurServlet(rep.code,rep.mess);
			}else{
				
				$('#follow').modal('show');

				
			} 
		},
		error :function(jqXHR, textStatus , errorThrown ){

		}
	})
	
}

function callUnfollow(){
	
	$.ajax ({
		type : "POST" ,
		url: "RemoveFollow",
		data: "user1="+getCookie("id_user")+"&user2="+$.urlParam('id')+"&key="+getCookie("key"), 
		dataType : "json" ,
		success: function(rep){
			if(!(typeof (rep.code) == 'undefined')){
				erreurServlet(rep.code,rep.mess);
			}else{
				
				$('#unfollow').modal('show');

				
			} 
		},
		error :function(jqXHR, textStatus , errorThrown ){

		}
	})
	
}
