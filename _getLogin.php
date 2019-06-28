<?php

	$user = $_GET['user'];
	$pass = $_GET['pass'];
	
	require_once('_koneksi.php');
	
	$sql = "SELECT * FROM
			wartawan WHERE
			username='$user' AND password='$pass'";
			
	$fetch = mysqli_query($conn, $sql);
	
	$result = array();
	$row = mysqli_fetch_array($fetch);
	
	if(is_null($row)) {
		$result["status"] = 400;
		$result["message"] = "User Not Found";
		$result["login"] = null;
	} else {
		$result["status"] = 200;
		$result["message"] = "Success";
		$result["login"] = array(
			"nama_user" => $row["nama_wartawan"],
			"username" => $row["username"],
			"password" => $row["password"]
		);
	}
	
	header('Content-Type: application/json', true, $result["status"]);
	echo json_encode($result);
	
	mysqli_close($conn);
	
		

?>