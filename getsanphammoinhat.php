<?php
$conect = mysqli_connect("localhost","root","","cuahangwatch");
mysqli_query($conect,"SET NAME 'utf8'");
$query = "SELECT * FROM sanpham ORDER BY ID DESC LIMIT 6";
$data = mysqli_query($conect,$query);
$arraysanpham = array();
while ($row = mysqli_fetch_assoc($data)) {
	array_push($arraysanpham,new Sanpham($row['id'],$row['tensanpham'],$row['giasanpham'],$row['hinhsanpham'],$row['motasanpham'],$row['idsanpham']));
}
echo json_encode($arraysanpham);

class Sanpham{
	function Sanpham($id,$tensp,$giasp,$hinhsp,$motasp,$idsp){
		$this -> id = $id;
		$this -> tensp = $tensp;
		$this -> giasp = $giasp;
		$this -> hinhsp = $hinhsp;
		$this -> motasp = $motasp;
		$this -> idsp = $idsp;
	}

}

?>