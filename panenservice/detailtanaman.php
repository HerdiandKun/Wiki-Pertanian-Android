 <?PHP
	include "koneksi.php";
	
	$komoditi = $_POST['komoditi'];
$query = "select t.namatanaman, t.masapanen, t.gambar, t.idtanaman,t.deskripsi from tanaman t, komoditi k where k.namakomoditi = '$komoditi' and t.idkomoditi=k.idkomoditi Order by t.namatanaman asc" ;
$result = oci_parse($connection,$query);
oci_execute($result);

$data = array();
while($row = oci_fetch_object($result)){
	$data[]= $row;
}
echo json_encode($data);

?>