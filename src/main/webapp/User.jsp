<%@ page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="View/bootstrap.min.css">
<script type="Components/jquery-3.2.1.min.js"></script>
<script type="Components/User.js"></script>
</head>
<body>
<body>

<div class="container"><div class="row"><div class="col-6">
	<h1>User Management</h1>

		<form id = "formUser" name="formUser" method="post" action="User.jsp">
			 User Code: <input id="userCode"name="userCode" type="text"
			 			class = "form-control form-control-sm" ><br>
			 				
			 Username: <input id="userName" name="userName" type="text" 
			 			class = "form-control form-control-sm"><br>
			 			
			 NIC: <input id="nic" name="nic" type="text" 
			 		class = "form-control form-control-sm"><br>
			 		
			 Phone: <input id="phone"  name="phone" type="text" 
			 		class = "form-control form-control-sm"><br>
			 		
			 Email: <input id="email" name="email" type="text" 
			 		class = "form-control form-control-sm"><br>
			 		
			 Address: <input id="address" name="address" type="text"
			 			class = "form-control form-control-sm" ><br>
			 			
			 Password: <input id="password" name="password" type="text"
			 			class = "form-control form-control-sm" ><br>
			 
			 <input id="btnSave" name="btnSave" type="button" value="Save"
			 		class = "btn btn-primary">
			 		
			 		
			 <input type="hidden" id="hidUserIDSave" name="hidUserIDSave" value="">		
		</form>
		
	<div id="alertSuccess" class="alert alert-success"></div>
	<div id="alertError" class="alert alert-danger"></div>
	<br>
	
	<div id="divUserGrid">

		<%
		 User userObj = new User(); 
		 out.print(userObj.readUser()); 
		%>
	</div>
</div> </div> </div> 
		
</body>
</html>