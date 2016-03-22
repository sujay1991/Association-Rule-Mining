<html>
<body>
<link rel="stylesheet" href="main_result.css" type="text/css">
<?php
$servername1 = "localhost";
$username1 = "root";
$password1 = "root";
$dbname1 = "db_project1";
$age = $_GET['age'];
$sex = $_GET['sex'];
$adm_diag = $_GET['adm_diag'];
echo "<h1> RESULT AND COMPARISION</h1>";
echo "<h4> AGE: $age <br> ";
//echo "Age: $age <br> <br>";
echo "<h4> SEX: $sex <br> ";
//echo "Sex: $sex<br> <br>";
echo "<h4> ADMISSION DIAGNOSIS CODE: $adm_diag<br><br>";
   $link = mysqli_connect($servername1, $username1, $password1, $dbname1);
if (!$link) {
    die("Connection failed: " . mysqli_connect_error());
}// echo "For the selected values";
$query = "select age from dataset_1 where age=$age and sex=$sex and ADMITTING_DIAGNOSIS_CODE=$adm_diag;";
$result = mysqli_query($link, $query);
if(mysqli_num_rows($result)>0){
/*Average LOS  */
$query = "select avg(LENGTH_OF_STAY) as los from dataset_1 where age=$age and sex=$sex and ADMITTING_DIAGNOSIS_CODE=$adm_diag group by age, sex, ADMITTING_DIAGNOSIS_CODE;";
$result = mysqli_query($link, $query);
while($row = mysqli_fetch_assoc($result)) {
	$avg_los = $row['los'];
}
	
/*discharge status (%) for the given combination  */
	 $query = "select (dead.num_dead/tot_num) as ds from (select count(*) as num_dead from dataset_1 where AGE=$age and SEX=$sex and ADMITTING_DIAGNOSIS_CODE =$adm_diag and DISCHARGE_STATUS ='B') dead, (select count(*) as tot_num from dataset_1 where AGE=$age and SEX=$sex and ADMITTING_DIAGNOSIS_CODE =$adm_diag) tot;";
	 $result = mysqli_query($link, $query);
	 while($row = mysqli_fetch_assoc($result)) {
	 		$ds = $row['ds'];
	 }

/*average total cost  */
$query = "select avg(TOTAL_CHARGES) as avg_tot from dataset_1 where age=$age and sex=$sex and ADMITTING_DIAGNOSIS_CODE=$adm_diag group by age, sex, ADMITTING_DIAGNOSIS_CODE;";
$result = mysqli_query($link, $query);
	while($row = mysqli_fetch_assoc($result)) {
		$avg_tot = $row['avg_tot'];
	}

/*Average LOS for all adm diagnosis code*/
	$query = "select avg(LENGTH_OF_STAY) as avg_los_all from dataset_1 where age=$age and sex=$sex;";
	$result = mysqli_query($link, $query);
		while($row = mysqli_fetch_assoc($result)) {
			$avg_los_all = $row['avg_los_all'];
		}
	
/*discharge status for all adm diag code*/
	$query = "select (dead.num_dead/tot_num) as ds from (select count(*) as num_dead from dataset_1 where AGE=$age and SEX=$sex  and DISCHARGE_STATUS ='B') dead, (select count(*) as tot_num from dataset_1 where AGE=$age and SEX=$sex) tot;";
	$result = mysqli_query($link, $query);
		while($row = mysqli_fetch_assoc($result)) {
			$ds_all = $row['ds'];
		}
	
	/*Average total charges adm  for all diagnosis code*/
	$query = "select avg(TOTAL_CHARGES) as avg_tot_all from dataset_1 where age=$age and sex=$sex;";
	$result = mysqli_query($link, $query);
		while($row = mysqli_fetch_assoc($result)) {
			$avg_tot_all = $row['avg_tot_all'];
		}

	echo"<table><thead><tr><th> </th><th>SELECTED ADMITTING_DIAGNOSIS_CODE </th><th>All_ADMITTING_DIAGNOSIS_CODE</th></tr>
	<tr><th>Avg Length of stay</th><td>$avg_los</td><td> $avg_los_all</td></tr>
	<tr><th>Discharge status %</th><td>$ds</td><td>$ds_all</td></tr>
	<tr><th>Average total charges</th><td>$avg_tot</td><td>$avg_tot_all</td></tr></thead>
	</table><BR><BR>";

/*Deviation ratio*/
	/*LOS*/
 	$deviation_ratio_los= $avg_los/$avg_los_all ;

	/*Discharge Status*/
	$deviation_ratio_ds=$ds/$ds_all;
	
	/*Total charges*/
	$deviation_ratio_tc= $avg_tot/$avg_tot_all;

/*Absolute difference*/
	/*LOS*/
	$absolute_difference_los= $avg_los-$avg_los_all ;

	/*Discharge status*/
	$absolute_difference_ds= $ds-$ds_all ;

	/*Total charges*/
	$absolute_difference_tc=$avg_tot-$avg_tot_all ;

	echo"<table><thead><tr><th> </th><th>DEVIATION RATIO(%)</th><th>ABSOLUTE DIFFERENCE</th></tr>
	<tr><th>Avg Length of stay</th><td>$deviation_ratio_los</td><td> $absolute_difference_los</td></tr>
	<tr><th>Discharge status %</th><td>$deviation_ratio_ds</td><td>$absolute_difference_ds</td></tr>
	<tr><th>Average total charges</th><td>$deviation_ratio_tc</td><td>$absolute_difference_tc</td></tr></thead>
	</table>";
}//main if closes	
else {
	echo "Sorry, no result for your search combination. Please try different search";
} 
	
?>
</body>
</html>