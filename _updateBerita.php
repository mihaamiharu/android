<?php

	$id_berita = $_POST['id_berita'];
	$judul = $_POST['judul_berita'];
	$isi = $_POST['isi_berita'];
	$date = date("Y-m-d h:i:s");
	
	$sql = "UPDATE berita SET judul_berita=?, isi_berita=?, waktu_posted=? WHERE id_berita=?";
			
	require_once('_koneksi.php');
		
	$stmt = mysqli_stmt_init($conn);
	mysqli_stmt_prepare($stmt, $sql);
	mysqli_stmt_bind_param($stmt, "sssi", $judul, $isi, $date, $id_berita);
		
	if($judul != NULL && $isi != NULL) {
		if(!mysqli_stmt_execute($stmt)){
			$result["status"] = 400;
			$result["message"] = "Update Gagal Alasan:" . mysqli_error($conn);			
		} else {
			$result["status"] = 200;
			$result["message"] = "Update Success";
		}
		header('Content-Type: application/json', true, $result["status"]);
		echo json_encode($result);
	}
	
	mysqli_stmt_close($stmt);
	mysqli_close($conn);

?>