
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
		// Logged into your app and Facebook.
		testAPI();
	} else if (response.status === 'not_authorized') {
		$('#myModal').modal('show');
	} else {
		$('#myModal').modal('show');
	}
}



function Nom_De_La_Fonction() {
	FB.getLoginStatus(function(response) {
		if (response.status === 'connected') {
			// the user is logged in and has authenticated your
			// app, and response.authResponse supplies
			// the user's ID, a valid access token, a signed
			// request, and the time the access token 
			// and signed request each expire
			var uid = response.authResponse.userID;
			accessToken = response.authResponse.accessToken;
			console.log(accessToken);

		} else if (response.status === 'not_authorized') {
			// the user is logged in to Facebook, 
			// but has not authenticated your app
		} else {
			// the user isn't logged in to Facebook.
		}
	});

	FB.api('/me', function(response) {
		console.log(JSON.stringify(response));
	});

	FB.api('/me/friends', {
		access_token : accessToken
	}, function(response) {

		console.log(JSON.stringify(response));
	});

}


