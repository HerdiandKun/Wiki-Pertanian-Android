<?PHP
	$workspace = "PANEN";
	$password = "1234";
	$server = "localhost/xe";
	
	$connection = oci_connect($workspace, $password, $server);
?>