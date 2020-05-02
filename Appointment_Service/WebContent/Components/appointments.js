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
		var status = validateAppointmentForm();
			if (status != true)
			{
				$("#alertError").text(status);
				$("#alertError").show();
				return;
			}

		// If valid------------------------
		$("#formAppointment").submit();

	});

//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
		{
			$("#hidAppointmentIDSave").val($(this).closest("tr").find('#hidAppointmentIDUpdate').val());
			$("#doctor_id").val($(this).closest("tr").find('td:eq(0)').text());
			$("#doctor_name").val($(this).closest("tr").find('td:eq(1)').text());
			$("#hospital_id").val($(this).closest("tr").find('td:eq(2)').text());
			$("#name").val($(this).closest("tr").find('td:eq(3)').text());
			$("#appointment_time").val($(this).closest("tr").find('td:eq(4)').text());
			$("#appointment_date").val($(this).closest("tr").find('td:eq(5)').text());
			$("#WardNo").val($(this).closest("tr").find('td:eq(6)').text()); 
		
		});


//CLIENTMODEL=========================================================================
function validateAppointmentForm()
{
	// Doc ID
	if ($("#doctor_id").val().trim() == "")
	{
		return "Insert Doctor_ID.";
	}

	// NAME
	if ($("#doctor_name").val().trim() == "")
	{
		return "Insert Doctor Name.";
	} 

	// Hopsital ID
	if ($("#hospital_id").val().trim() == "")
	{
		return "Insert Hospital ID.";
	}

	// NAME
	if ($("#name").val().trim() == "")
	{
		return "Insert Hospital Name.";
	} 
	
	// Appointment Time
	if ($("#appointment_time").val().trim() == "")
	{
		return "Insert a time for the appointment.";
	} 

	// Appointment Date
	if ($("#appointment_date").val().trim() == "")
	{
		return "Insert a date for the appointment.";
	} 

	// Ward No------------------------
	if ($("#WardNo").val().trim() == "")
	{
		return "Insert a ward no. for the appointment.";
	}
	
	return true;
}


function onAppointmentSaveComplete(response, status)
{
	if (status == "success")
	{

		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divAppointmentsGrid").html(resultSet.data);
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

	$("#hidAppointmentIDSave").val("");
	$("#formAppointment")[0].reset();
}


$(document).on("click", ".btnRemove", function(event)
{
	 $.ajax(
	 {
		 url : "AppointmentsAPI",
		 type : "DELETE",
		 data : "ref_id=" + $(this).data("ref_id"),
		 dataType : "text",
		 complete : function(response, status)
		 {
			 	onAppointmentDeleteComplete(response.responseText, status);
		 }
	 });
});


function onAppointmentDeleteComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);

		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divAppointmentsGrid").html(resultSet.data);
		
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







