<?php
$conect = mysqli_connect("localhost","root","","cuahangwatch");
mysqli_query($conect,"SET NAME 'utf8'");
$page = $_GET['page'];
$idsp = 2;
$space = 6;
$limit = ($page -2) * $space;
$arraysp = array();

$query = "SELECT * FROM sanpham WHERE idsanpham = $idsp LIMIT $limit,$space";
$data = mysqli_query($conect,$query);

while ($row = mysqli_fetch_assoc($data)){
	array_push($arraysp,new sanphamphone($row['id'],$row['tensanpham'],$row['giasanpham'],$row['hinhsanpham'],$row['motasanpham'],$row['idsanpham']));
}

echo json_encode($arraysp);
class sanphamphone{
	function sanphamphone($id,$tensp,$giasp,$hinhsp,$motasp,$idsp){
		$this -> id = $id;
		$this -> tensp = $tensp;
		$this -> giasp = $giasp;
		$this -> hinhsp = $hinhsp;
		$this -> motasp = $motasp;
		$this -> idsp = $idsp;
	}
}
?>