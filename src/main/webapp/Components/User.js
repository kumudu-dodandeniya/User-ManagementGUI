$(document).ready(function() 
{ 
	
	  
	 $("#alertSuccess").hide(); 
	 
	 $("#alertError").hide(); 
}); 


// SAVE ============================================
$(document).on("click", "#btnSave", function(event) 
{ 
		// Clear alerts---------------------
		 $("#alertSuccess").text(""); 
		 $("#alertSuccess").hide(); 
		 $("#alertError").text(""); 
		 $("#alertError").hide(); 
	
	
		// Form validation-------------------
		var status = validateUserForm(); 
		if (status != true) 
		 { 
			 $("#alertError").text(status); 
			 $("#alertError").show(); 
			 return; 
		 } 
	
	
		// If valid------------------------
		var type = ($("#hiduserIDSave").val() == "") ? "POST" : "PUT"; 
		 $.ajax( 
		 { 
			 url : "UserAPI", 
			 type : type, 
			 data : $("#formUser").serialize(), 
			 dataType : "text", 
			 complete : function(response, status) 
		 { 
		 	 onUserSaveComplete(response.responseText, status); 
		 } 
		 }); 
});

 
function onUserSaveComplete(response, status) 
{ 
		if (status == "success") 
		 { 
			 var resultSet = JSON.parse(response); 
		
			 if (resultSet.status.trim() == "success") 
			 { 
				 $("#alertSuccess").text("Successfully saved."); 
				 $("#alertSuccess").show(); 
				 $("#divUserGrid").html(resultSet.data); 
			
			 } else if (resultSet.status.trim() == "error") 
			 { 
				 $("#alertError").text(resultSet.data); 
				 $("#alertError").show(); 
			 } 
		
		} else if (status == "error") 
		{ 
			 $("#alertError").text("Error while saving."); 
			 $("#alertError").show();
		 
		 } else
		 { 
			 $("#alertError").text("Unknown error while saving.."); 
			 $("#alertError").show(); 
		 } 
		 
		 $("#hiduserIDSave").val(""); 
		 $("#formUser")[0].reset(); 
}



 
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
	 $("#hiduserIDSave").val($(this).data(userid)); 
	 $("#userCode").val($(this).closest("tr").find('td:eq(0)').text()); 
	 $("#userName").val($(this).closest("tr").find('td:eq(1)').text()); 
	 $("#nic").val($(this).closest("tr").find('td:eq(2)').text()); 
	 $("#phone").val($(this).closest("tr").find('td:eq(3)').text()); 
	 $("#email").val($(this).closest("tr").find('td:eq(3)').text()); 
	 $("#address").val($(this).closest("tr").find('td:eq(3)').text()); 
	 $("#password").val($(this).closest("tr").find('td:eq(3)').text()); 
}); 



$(document).on("click", ".btnRemove", function(event) 
{ 
		 $.ajax( 
		 { 
			 url : "UserAPI", 
			 type : "DELETE", 
			 data : "userID=" + $(this).data("userid"),
			 dataType : "text", 
			 complete : function(response, status) 
		 { 
		 	 onUserDeleteComplete(response.responseText, status); 
		 } 
 	}); 
});



function onUserDeleteComplete(response, status) 
{ 
		if (status == "success") 
		 { 
			 var resultSet = JSON.parse(response); 
		
			 if (resultSet.status.trim() == "success") {
				 
					 $("#alertSuccess").text("Successfully deleted."); 
					 $("#alertSuccess").show(); 
					 $("#divUserGrid").html(resultSet.data); 
				
			 } else if (resultSet.status.trim() == "error") {
				 
					 $("#alertError").text(resultSet.data); 
					 $("#alertError").show(); 
			 } 
		
			 } else if (status == "error") { 
				
					 $("#alertError").text("Error while deleting."); 
					 $("#alertError").show(); 
				
			 } else{
				 
					 $("#alertError").text("Unknown error while deleting.."); 
					 $("#alertError").show(); 
 		} 
}

// CLIENT-MODEL================================================================
function validateUserForm() 
{ 
	// CODE
	if ($("#userCode").val().trim() == "") 
	 { 
	 	return "Please Insert User Code."; 
	 } 

	// NAME
	if ($("#userName").val().trim() == "") 
	 { 
	 	return "Please Insert User Name."; 
	 }

	// NIC-------------------------------
	if ($("#nic").val().trim() == "") 
	 { 
	 	return "Please Insert User NIC."; 
	 } 

	// PHONE-------------------------------
	if ($("#phone").val().trim() == "") 
	 { 
	 	return "Please Insert User Phone Number."; 
	 } 

	// EMAIL-------------------------------
		if ($("#email").val().trim() == "") 
		 { 
		 	return "Please Insert User Email Address."; 
		 } 
	
	// ADDRESS-------------------------------
		if ($("#address").val().trim() == "") 
		 { 
		 	return "Please Insert User Home Address."; 
		 } 
	
	// PASSWORD -------------------------------
		if ($("#password").val().trim() == "") 
		 { 
		 	return "Please Insert User Password."; 
		 } 
return true; 
}

