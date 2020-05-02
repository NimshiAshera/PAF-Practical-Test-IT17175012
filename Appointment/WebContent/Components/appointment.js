/**
 * 
 */

$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	{
		$("#alertSuccess").hide();
	}
		$("#alertError").hide();
	});


//SAVE ============================================
$(document).on("click", "#btnSave", function(event)
	{
		// Clear alerts---------------------
		$("#alertSuccess").text("");
		$("#alertSuccess").hide();
		$("#alertError").text("");
		$("#alertError").hide();
		
		// Form validation-------------------
		var status = validateViewForm();
			if (status != true)
			{
				$("#alertError").text(status);
				$("#alertError").show();
				return;
			}

		// If valid------------------------
		$("#viewList").submit();

	});

//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
		{
			$("#hidref_IDSave").val($(this).closest("tr").find('#hidViewIDUpdate').val());
			$("#doctor_id").val($(this).closest("tr").find('td:eq(0)').text());
			$("#doctor_name").val($(this).closest("tr").find('td:eq(1)').text());
			$("#hospital_id").val($(this).closest("tr").find('td:eq(2)').text());
			$("#name").val($(this).closest("tr").find('td:eq(3)').text()); 
			$("#appointment_time").val($(this).closest("tr").find('td:eq(4)').text());
			$("#appointment_date").val($(this).closest("tr").find('td:eq(5)').text());
			$("#WardNo").val($(this).closest("tr").find('td:eq(6)').text());
		
		});


//CLIENTMODEL=========================================================================
function validateViewForm()
{
	// doctor_id
	if ($("#doctor_id").val().trim() == "")
	{
		return "Insert Doctor ID.";
	}

	// doctor_name
	if ($("#doctor_name").val().trim() == "")
	{
		return "Insert Doctor Name.";
	} 
	
	// hospital_id
	if ($("#hospital_id").val().trim() == "")
	{
		return "Insert Hospital ID.";
	}

	// hospital_name
	if ($("#name").val().trim() == "")
	{
		return "Insert Hospital Name.";
	} 
	
	// appointment_time
	if ($("#appointment_time").val().trim() == "")
	{
		return "Insert Time for the Appointment.";
	}

	// appointment_date
	if ($("#appointment_date").val().trim() == "")
	{
		return "Insert Date for the Appointment.";
	} 
	
	//WardNo
	if ($("#WardNo").val().trim() == "")
	{
		return "Insert Hospital Ward Number.";
	}
	
	return true;
}


function onViewSaveComplete(response, status)
{
	if (status == "success")
	{

		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divViewsGrid").html(resultSet.data);
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

	$("#hidRef_IDSave").val("");
	$("#viewList")[0].reset();
}


$(document).on("click", ".btnRemove", function(event)
{
	 $.ajax(
	 {
		 url : "AppViewAPI",
		 type : "DELETE",
		 data : "ref_id=" + $(this).data("ref_id"),
		 dataType : "text",
		 complete : function(response, status)
		 {
			 	onViewDeleteComplete(response.responseText, status);
		 }
	 });
});


function onViewDeleteComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);

		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divViewsGrid").html(resultSet.data);
		
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error")
	{
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else
	{
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}







