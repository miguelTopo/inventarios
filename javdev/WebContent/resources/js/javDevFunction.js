/**
 * 
 */
function passwordUserCrypto() {
	var pwInput = document.getElementById("userAddForm:pw-input").value;
	var pwRetry = document.getElementById("userAddForm:pw-retry-input").value;
	if (pwInput != undefined && pwInput.trim()!="" && pwRetry != undefined && pwRetry.trim()!="") {
		var hashPw = CryptoJS.MD5(pwInput);
		var hashPwRetry = CryptoJS.MD5(pwRetry);
		document.getElementById("userAddForm:pw-hidden").value = hashPw;
		document.getElementById("userAddForm:pw-retry-hidden").value = hashPwRetry;
		hash = null;
	}
	saveUserAction();
}

function passwordCrypto(){
	var idForm = "input_loginForm";
	var pwInput = document.getElementById("loginForm:pw-input").value;
	console.log(pwInput);
	var hash = CryptoJS.MD5(pwInput);
	document.getElementById("loginForm:pw-hidden").value = hash;
	hash = null; 
}

function handleComplete(xhr, status, args){
	var isValid = args.successLogin; 
	if (isValid) {
		setTimeout(redirectTo(args.mainView), 500);
	}
}

function redirectTo(urlRequest){
	window.location = urlRequest;
}
