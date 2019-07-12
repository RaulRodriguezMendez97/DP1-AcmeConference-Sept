function getValue() {
	var x = document.getElementById("keyword").value;
	document.getElementById("ticker").value = x;
	document.getElementById("description").value = x;
	document.getElementById("address").value = x;
}

function cadenaVacia() {
	var x = document.getElementById("keyword").value;
	if (x == "") {
		document.getElementById("ticker").value = x;
		document.getElementById("description").value = x;
		document.getElementById("address").value = x;
	}
}
