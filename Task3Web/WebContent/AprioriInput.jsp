<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<style type="text/css">     
    select {
        width:500px;
        height: 370px;
    }
</style>
<form action="apriori" method="post">
Select all the required atrributes:<br></br>
<select multiple name="keyword">
  <option value="AGE" name="keyword">AGE</option>
  <option value="SEX" name="keyword">SEX</option>
  <option value="RACE" name="keyword">RACE</option>
  <option value="DAY_OF_ADMISSION" name="keyword">DAY_OF_ADMISSION</option>
  <option value="DISCHARGE_STATUS" name="keyword">DISCHARGE_STATUS</option>
  <option value="STAY_INDICATOR" name="keyword">STAY_INDICATOR</option>
  <option value="DRG_CODE" name="keyword">DRG_CODE</option>
  <option value="LENGTH_OF_STAY" name="keyword">LENGTH_OF_STAY</option>
  <option value="DRG_PRICE" name="keyword">DRG_PRICE</option>
  <option value="TOTAL_CHARGES" name="keyword">TOTAL_CHARGES</option>
  <option value="COVERED_CHARGES" name="keyword">COVERED_CHARGES</option>
  <option value="POA_DIAGNOSIS_INDICATOR_1" name="keyword">POA_DIAGNOSIS_INDICATOR_1</option>
  <option value="POA_DIAGNOSIS_INDICATOR_2" name="keyword" >POA_DIAGNOSIS_INDICATOR_2</option>
  <option value="DIAGNOSIS_CODE_1" name="keyword">DIAGNOSIS_CODE_1</option>
  <option value="DIAGNOSIS_CODE_2" name="keyword">DIAGNOSIS_CODE_2</option>
  <option value="PROCEDURE_CODE_1" name="keyword">PROCEDURE_CODE_1</option>
  <option value="PROCEDURE_CODE_2" name="keyword">PROCEDURE_CODE_2</option>
  <option value="DISCHARGE_DESTINATION" name="keyword">DISCHARGE_DESTINATION</option>
  <option value="SOURCE_OF_ADMISSION" name="keyword">SOURCE_OF_ADMISSION</option>
  <option value="TYPE_OF_ADMISSION" name="keyword">TYPE_OF_ADMISSION</option>
  <option value="ADMITTING_DIAGNOSIS_CODE" name="keyword">ADMITTING_DIAGNOSIS_CODE</option>
</select>
<br><br>
Enter the thresholds: <br>
Support: <input type="text" name="support"> </br><br>
Confidence: <input type="text" name="conf"> </br><br>
<input type="submit" value="Find the rules!!">
</form>
</body>
</html>