<html>
<body>
<link rel="stylesheet" href="main_exp_diag.css" type="text/css">
<?php
$servername1 = "localhost";
$username1 = "root";
$password1 = "root";
$dbname1 = "db_project1";
$link = mysqli_connect($servername1, $username1, $password1, $dbname1);
if (!$link) {
	die("Connection failed: " . mysqli_connect_error());
}

/*Top 10 values*/
/*expensive admission diagnoses*/
$query = "select ADMITTING_DIAGNOSIS_CODE, avg(TOTAL_CHARGES) as avg_tot from dataset_1 group by ADMITTING_DIAGNOSIS_CODE order by avg_tot desc limit 10;";
$result = mysqli_query($link, $query);
if(mysqli_num_rows($result)>0){
	echo "<br><br><h1>Top 10 most expensive admission diagnoses</h1>";
	echo "<table><thead><tr><th>ADMITTING_DIAGNOSIS_CODE</th><th>TOTAL_CHARGES</th></tr></thead>";
	while($row = mysqli_fetch_assoc($result)) {
		echo "<tr><td>" .$row['ADMITTING_DIAGNOSIS_CODE']. "</td><td>" .$row['avg_tot']. "</td></tr>";
			}
	echo"</table>";
}
else {
	echo "Sorry, no result for your search combination. Please try different search";
}
	?>
	</body>
	</html>