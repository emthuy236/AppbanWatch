<?php
$conect = mysqli_connect("localhost","root","","cuahangwatch");
mysqli_query($conect,"SET NAME 'utf8'");
$json = $_POST['json'];
$data = json_decode($json,true);
 foreach ($data as  $value) {
	$madonhang = $value['madonhang'];
	$masanpham = $value['masanpham'];
	$tensanpham = $value['tensanpham'];
	$giasanpham = $value['giasanpham'];
	$soluongsanpham = $value['soluongsanpham'];
	$query = "INSERT INTO chitietdonhang (id,madonhang,masanpham,tensanpham,giasanpham,soluongsanpham) VALUES (null,'$madonhang','$masanpham','$tensanpham','$giasanpham','$soluongsanpham')";
	$dta = mysqli_query($conect,$query);
}
 if ($dta) {
	echo "1";
}else{
	echo "0";
}
?>