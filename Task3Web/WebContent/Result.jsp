<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>Rules based on the conditions provided</h2>
<!-- <script type="javascript" type="text/javascript"> -->
<%
/* List<ItemObj> myList = (ArrayList<ItemObj>) request.getAttribute("list"); */ 
Map<String, String> result = (HashMap<String, String>) request.getAttribute("resultMap");
List<String> comList1 = (ArrayList<String>) request.getAttribute("combList");%>
<%System.out.println("CombList is " + comList1);%>
<br><br>
<%="The combination of attributes "+comList1 %>
<%if(result.size()==0){%>
	<h3>Sorry, No Rules Found!!</h3>
<%}if(result.size()>0){%>
<h3>Rules based on the thresholds</h3>
	<%for (Map.Entry<String, String> entry : result.entrySet()) {
	String rule=entry.getKey();
	String val=entry.getValue();
    System.out.println("Rule: "+entry.getKey()+"->"+entry.getValue()); %>
    <br><br>
    <%= "Rule: "+entry.getKey()+"->"+entry.getValue() %>
<%}}%>

<!-- </script> -->


</body>
</html>