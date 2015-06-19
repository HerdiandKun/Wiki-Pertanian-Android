<?PHP
	include "koneksi.php";
	
	extract($_POST);
	
	$query = "INSERT INTO member(username, nama, password) VALUES ('".$username."', '".$nama."', '".$password."')";
	
	$statement = oci_parse($connection, $query);
	
	oci_execute($statement);
?>