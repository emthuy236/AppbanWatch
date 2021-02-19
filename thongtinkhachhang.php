<?php
$conect = mysqli_connect("localhost","root","","cuahangwatch");
mysqli_query($conect,"SET NAME 'utf8'");
$tenkhachhang = $_POST['tenkhachang'];
$email = $_POST['email1'];
$sodienthoai = $_POST['sodienthoai1'];
if (strlen($tenkhachhang) >0 && strlen($email) >0 && strlen($sodienthoai) >0) {
	$query = "INSERT INTO donhang(id,tenkhachhang,email,sodienthoai) VALUES (null,'$tenkhachhang','$email','$sodienthoai')";
	if (mysqli_query($conect,$query)) {
	    $iddonhang = $conect->insert_id;
	    echo $iddonhang;
	}else{
		echo "Thất bại";
	}
}else{
	echo "Kiểm tra lại dữ liệu của bạn";
}
?>

