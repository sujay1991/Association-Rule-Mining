<html>
<body>
<link rel="stylesheet" href="main_adm_diag.css" type="text/css">
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
/*highest “death on discharge” ratio*/
$query = "select t.ADMITTING_DIAGNOSIS_CODE,(d.dead/t.tot) ratio from (select ADMITTING_DIAGNOSIS_CODE,count(*) as tot from dataset_1 group by ADMITTING_DIAGNOSIS_CODE) t,(select ADMITTING_DIAGNOSIS_CODE,count(*) as dead from dataset_1 where DISCHARGE_STATUS= 'B'  group by ADMITTING_DIAGNOSIS_CODE) d where t.ADMITTING_DIAGNOSIS_CODE=d.ADMITTING_DIAGNOSIS_CODE order by ratio desc limit 10;";
$result = mysqli_query($link, $query);
	if(mysqli_num_rows($result)>0){
	echo"<br><br><h1>Top 10 admission diagnoses with the highest 'death on discharge' ratio </h1>";
	echo "<table><thead><tr><th>ADMITTING_DIAGNOSIS_CODE</th><th>DISCHARGE RATIO</th></tr></thead>";
	while($row = mysqli_fetch_assoc($result)) {
		echo "<tr><td>" .$row['ADMITTING_DIAGNOSIS_CODE']. "</td><td>" .$row['ratio']. "</td></tr>";
	}
	echo"</table>";
	}
	else {
	echo "Sorry, no result for your search combination. Please try different search";
	}
	?>
	</body>
	</html>