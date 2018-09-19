function loginOne(){
	var providerName = "userstore";
	var username = "<Email>";
	var password = "<Password>";
	var authClient = null;
	
	function success(response){
		alert("Login success. "+JSON.stringify(response));
	}
	
	function failure(response){
		alert("Login failure. "+JSON.stringify(response));
	}
	try {
		authClient = kony.sdk.getCurrentInstance().getIdentityService(providerName);
		authClient.login({"userid": username, "password": password}, success, failure);	
    } catch (exception) {
            alert("Login failure. "+exception.message);
    }
}