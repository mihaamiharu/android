<?php

	$judul = $_POST['judul_berita'];
	$isi = $_POST['isi_berita'];
	$date = date("Y-m-d h:i:s");
	
	if (isset($_POST['judul_berita']) != NULL) {
		$sql = "INSERT INTO berita
				(judul_berita, isi_berita, waktu_posted)
				VALUES (?,?,?)";
				
		require_once('_koneksi.php');
		
		$stmt = mysqli_stmt_init($conn);
		mysqli_stmt_prepare($stmt, $sql);
		mysqli_stmt_bind_param($stmt, "sss", $judul, $isi, $date);
		
		if($judul != NULL && $isi != NULL) {
			if(!mysqli_stmt_execute($stmt)){
				$result["status"] = 400;
				$result["message"] = "Insert Failed" . mysqli_error($conn);			
			} else {
				$result["status"] = 200;
				$result["message"] = "Insert Success";
			}
		}
	} else {
		$result["status"] = 200;
		$result["message"] = "Parameter not found";
	}
	
	header('Content-Type: application/json', true, $result["status"]);
	echo json_encode($result);
	
	mysqli_stmt_close($stmt);
	mysqli_close($conn);

?>