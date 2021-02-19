<?php
$conect = mysqli_connect("localhost","root","","cuahangwatch");
mysqli_query($conect,"SET NAME 'utf8'");
$query = "SELECT * FROM loaisanpham";
$data = mysqli_query($conect,$query);
 $arrayloaisp = array();

 while ($row = mysqli_fetch_assoc($data)) {
 	array_push($arrayloaisp,new Loaisp($row['id'],$row['tenloaisanpham'],$row['hinhloaisanpham']));
 }
 echo json_encode($arrayloaisp);

 class Loaisp{
 	function Loaisp($id,$tenloaisp,$hinhloaisp){
 		$this -> id = $id;
 		$this -> tenloaisp = $tenloaisp;
 		$this -> hinhloaisp = $hinhloaisp;

 	}
 }

?>