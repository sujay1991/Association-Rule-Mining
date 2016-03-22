<html>
<body>
<link rel="stylesheet" href="main_high_los.css" type="text/css">
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
/*highest average length of stay  */
$query = "select ADMITTING_DIAGNOSIS_CODE, avg(length_of_stay) as avg_len from dataset_1 group by ADMITTING_DIAGNOSIS_CODE order by avg_len desc  limit 10;";
$result = mysqli_query($link, $query);
if(mysqli_num_rows($result)>0){
	echo"<br><br><h1>Top 10 diagnoses with the highest average length of stay</h1>";
	echo "<table><thead><tr><th>Admission diagnosis code</th><th>Avg length of stay</th></tr></thead>";
	while($row = mysqli_fetch_assoc($result)) {
		echo "<tr><td>" .$row['ADMITTING_DIAGNOSIS_CODE']. "</td><td>" .$row['avg_len']. "</td></tr>";
	}
	echo"</table>";
}
else {
	echo "Sorry, no result for your search combination. Please try different search";
}
	?>
	</body>
	</html>