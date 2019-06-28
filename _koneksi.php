<?php

	define('HOST', 'localhost'); //server host default
	define('USER', 'root'); //server user default
	define('PASS', ''); 
	define('DB', 'news');

	$conn = mysqli_connect(HOST, USER, PASS, DB)
		or die("Database gagal terhubung" . mysqli_error());

?>