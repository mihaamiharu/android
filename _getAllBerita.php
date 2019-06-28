<?php

	$sql = "SELECT * FROM berita ORDER BY id_berita DESC";
	
	require_once('_koneksi.php');
	
	$fetch = mysqli_query($conn, $sql);
	
	$result = array();
	
	$result["status"] = 200;
	$result["message"] = "Success";
	$result["berita"] = array();
	
	while($row = mysqli_fetch_array($fetch)) {
		array_push($result["berita"], array(
			'id_berita' => $row['id_berita'],
			'judul_berita' => $row['judul_berita'],
			'isi_berita' => $row['isi_berita'],
			'waktu_posted' => $row['waktu_posted']
		));
	}
	
	header('Content-Type: application/json', true, $result["status"]);
	echo json_encode($result);
	
	mysqli_close($conn);
	
?>