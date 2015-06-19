<?PHP
	include "koneksi.php";
	
	extract($_POST);
	
	$query = "SELECT * FROM komoditi";
	
	$statement = oci_parse($connection, $query);
	
	oci_execute($statement);
	
	$data = array();
	while($row = oci_fetch_object($statement)){
	$data[]= $row;
}

	
	
	echo json_encode($data);
?>