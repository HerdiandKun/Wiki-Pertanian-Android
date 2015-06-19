<?PHP
	include "koneksi.php";
	
	extract($_POST);
	
	$query = "SELECT NAMA, COUNT(*) AS jumlah FROM member 
			WHERE username='".$username."' AND 
			password='".$password."' group by nama";
	
	$statement = oci_parse($connection, $query);
	
	oci_execute($statement);
	
	$data = array();
	
	$data[] = oci_fetch_object($statement);
	
	echo json_encode($data);
?>