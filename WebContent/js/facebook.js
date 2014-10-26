
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
		
		setCookie("key",accessToken, 100);
		$.ajax ({
			type : "POST" ,
			url: "sendkey",
			data:"key="+accessToken, 
			dataType : "json" ,
			success: function(rep){
				if(!(typeof (rep.code) == 'undefined')){
					erreurServlet(rep.code,rep.mess);
				}else{
					setCookie("id_fb",rep.id, 100);
					setCookie("name",rep.name, 100);
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




function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+d.toUTCString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}


function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1);
        if (c.indexOf(name) != -1) return c.substring(name.length,c.length);
    }
    return "";
}

function checkCookie(cname) {
    var cookie=getCookie(cname);
    return cookie!="";
}



