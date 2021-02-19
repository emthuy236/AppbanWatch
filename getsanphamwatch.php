<?php
$conect = mysqli_connect("localhost","root","","cuahangwatch");
mysqli_query($conect,"SET NAME 'utf8'");
$page1 = $_GET['page'];
$idsp1 = 1;
$space1 = 6;
$limit1 = ($page1 -1) * $space1;
$arraysp1 = array();

$query1 = "SELECT * FROM sanpham WHERE idsanpham = $idsp1 LIMIT $limit1,$space1";
$data1 = mysqli_query($conect,$query1);

while ($row1 = mysqli_fetch_assoc($data1)){
	array_push($arraysp1,new sanphamwatch($row1['id'],$row1['tensanpham'],$row1['giasanpham'],$row1['hinhsanpham'],$row1['motasanpham'],$row1['idsanpham']));
}

echo json_encode($arraysp1);
class sanphamwatch{
	function sanphamwatch($id1,$tensp1,$giasp1,$hinhsp1,$motasp1,$idsp1){
		$this -> id = $id1;
		$this -> tensp = $tensp1;
		$this -> giasp = $giasp1;
		$this -> hinhsp = $hinhsp1;
		$this -> motasp = $motasp1;
		$this -> idsp = $idsp1;
	}
}
?>