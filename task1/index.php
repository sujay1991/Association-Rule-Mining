<html>
<body>
<link rel="stylesheet" href="main2.css" type="text/css">
<h1> Investigation of hospital admission cases  </h1>

<?php
$servername1 = "localhost";
$username1 = "root";
$password1 = "root";
$dbname1 = "db_project1";
   $link = mysqli_connect($servername1, $username1, $password1, $dbname1);
if (!$link) {
    die("Connection failed: " . mysqli_connect_error());
}

else{
?>
 
<h3> Connection success!!!</h3>
<?php
 	//echo "Conection Success!!\n";
}
?>
<h2> Please select your age </h2>
<?php 
$age = "select distinct(AGE) from dataset_1 order by AGE asc;";
$result = mysqli_query($link, $age);
echo "<form action='result.php' method='GET'>";

 
//echo "Kindly select the following values:";
echo"<h3>AGE:<select name='age'>";
if(mysqli_num_rows($result)>0){
while($row = mysqli_fetch_assoc($result)) {
$disp_age = $row['AGE'];
echo "<option value='$disp_age'>$disp_age</option>";
}
}
echo "</select>";

$sex = "select distinct(SEX) from dataset_1 ORDER BY SEX asc;";
$result = mysqli_query($link, $sex);
?>
<h2> Please select your gender </h2>

<?php
echo "<h3>SEX:<select name='sex'>";
if(mysqli_num_rows($result)>0){
while($row = mysqli_fetch_assoc($result)) {
$disp_sex = $row['SEX'];
echo "<option value='$disp_sex'>$disp_sex</option>";
}
}
echo "</select>";

$adm_diag = "select distinct(ADMITTING_DIAGNOSIS_CODE)from dataset_1 order by ADMITTING_DIAGNOSIS_CODE asc;";
$result = mysqli_query($link, $adm_diag);
?>
<h2> Please select your admission diagnosis code</h2>
<?php 
echo "<h3>ADMITTING DIAGNOSIS CODE:<select name='adm_diag'>";
if(mysqli_num_rows($result)>0){
while($row = mysqli_fetch_assoc($result)) {
$disp_adc = $row['ADMITTING_DIAGNOSIS_CODE'];
echo "<option value='$disp_adc'>$disp_adc</option>";
}
}
echo "</select>";
echo "<br> <br><input type='submit' name='submit'></form>";

/*Top 10 admission diagnoses*/
echo "<form action='adm_diag.php' method='GET'>";
echo "<br> <br><input type='submit' value='Top 10 admission diagnoses'></form>";

/*Top 10 most expensive admission diagnoses*/
echo "<form action='exp_diag.php' method='GET'>";
echo "<br> <br><input type='submit' value='Top 10 most expensive admission diagnoses'></form>";

/*Top 10 diagnoses with the highest average length of stay*/
echo "<form action='high_los.php' method='GET'>";
echo "<br> <br><input type='submit' value='Top 10 diagnoses with the highest average length of stay'></form>";

?>
<a href="http://localhost:9999/Task3Web/AprioriInput.jsp">Task3</a>
</body>
</html>