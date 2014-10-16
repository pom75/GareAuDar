
(function(d, s, id) {
	var js, fjs = d.getElementsByTagName(s)[0];
	if (d.getElementById(id))
		return;
	js = d.createElement(s);
	js.id = id;
	js.src = "//connect.facebook.net/fr_FR/sdk.js#xfbml=1&appId=1500073900262956&version=v2.0";
	fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

window.fbAsyncInit = function() {
	$('#mymodal').modal('show');
	FB.init({
		appId : '1500073900262956',
		cookie : true, // enable cookies to allow the server to access 
		// the session
		xfbml : true, // parse social plugins on this page
		version : 'v2.1' // use version 2.1
	});


	FB.getLoginStatus(function(response) {
		statusChangeCallback(response);
	});

};

function statusChangeCallback(response) {
	if (response.status === 'connected') {
		// Logged into your app and Facebook
		
		accessToken = response.authResponse.accessToken;
		console.log(accessToken);
		$.ajax ({
			type : "POST" ,
			url: "sendkey",
			data:"key="+accessToken, 
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
	} else if (response.status === 'not_authorized') {
		$('#myModal').modal('show');
	} else {
		$('#myModal').modal('show');
	}
}




function getInformationUser(token) {
	FB.api('/me', function(rep) {
		FB.api('/me/friends', {
		}, function(response) {
			$.ajax ({
				type : "POST" ,
				url: "fbuser",
				data:"id="+rep.id+"&name="+rep.name+"&first_name="+rep.first_name+"&last_name="+rep.last_name+"&link="+
				rep.link+"&gender="+rep.gender+"&local="+rep.local+"&link="+rep.link+"&token="+token+"&="+token, 
				dataType : "json" ,
				success: function(rep){
					if(!(typeof (rep.code) == 'undefined')){
						boolInscr = true;
						alert("Erreur "+rep.code+" : "+rep.mess);
					}else{
						boolInscr = true;
						pop('inscrip');
					} 
				},
				error :function(jqXHR, textStatus , errorThrown ){
					boolInscr = true;
					alert("textStatus");
				}
			})

		});
	});
}




