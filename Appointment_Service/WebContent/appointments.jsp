<%@page import="model.Appointment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
    			<%

					 //Initialize
					 session.setAttribute("statusMsg","");
					 System.out.println("Trying to process...");

					 //Save---------------------------------
					 if (request.getParameter("doctor_id") != null)
					 {
					  	Appointment appointmentObj = new Appointment();
					  	
					  	String stsMsg = "";
					 	
					  	//Insert--------------------------
					 	if (request.getParameter("hidAppointmentIDSave") == "")
					  	{
					  
					 		stsMsg = appointmentObj.insertAppointment(
								request.getParameter("doctor_id"),
					 			request.getParameter("doctor_name"),
								request.getParameter("hospital_id"),
					 			request.getParameter("name"),
								request.getParameter("appointment_time"),
					  			request.getParameter("appointment_date"),
					  			request.getParameter("WardNo"));
					  	}
					 
					 	else//Update----------------------
					  	{
					  		stsMsg = appointmentObj.updateAppointment(
								request.getParameter("hidAppointmentIDSave"),
					  			request.getParameter("doctor_id"),
					 			request.getParameter("doctor_name"),
								request.getParameter("hospital_id"),
					 			request.getParameter("name"),
								request.getParameter("appointment_time"),
					  			request.getParameter("appointment_date"),
					  			request.getParameter("WardNo"));
					  	}
					  
					  	session.setAttribute("statusMsg", stsMsg);
					 }
					 
					//Delete-----------------------------
					if (request.getParameter("hidAppointmentIDDelete") != null)
					{
					  	Appointment appointmentObj = new Appointment();
					  	
					  	String stsMsg =
					  
					  	appointmentObj.deleteAppointment(request.getParameter("hidAppointmentIDDelete"));
					  	session.setAttribute("statusMsg", stsMsg);
					 }

				%>
    
   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Appointment</title>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<link rel="stylesheet" href="Views/style.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/appointments.js"></script>

</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-8">

 			<h1 class="m-3" style="text-align:center">Appointment Grid</h1>
 			
 			<br>

 			<form id="formAppointment" name="formAppointment" method="post" action="appointments.jsp">
 			
 			<label>Doctor ID:</label>
			<input id="doctor_id" name="doctor_id" type="text" class="form-control form-control-sm">
			
			<br> 

			<label>Doctor name:</label>
			<select id="doctor_name" name="doctor_name" type="text" class="form-control form-control-sm">
					<option value="select">select...</option>
					<option value="Dr.Nalin Wijekoon">1- Dr.Nalin Wijekoon</option>
                    <option value="Dr.Saman Senanayaka">2- Dr.Saman Senanayaka</option>
                    <option value="Dr. Manil Peiris">3- Dr. Manil Peiris</option>
                    <option value="Dr. Anura Kularathna">4- Dr. Anura Kularathna</option>
                    <option value="Dr. Janaka Rambukwella">5- Dr. Janaka Rambukwella</option>
                    <option value="Dr. Shantha Disanayaka">6- Dr. Shantha Disanayaka</option>  
                    <option value="Dr. Jagath Ranaweera">7- Dr. Jagath Ranaweera</option>
                    <option value="Dr. Sampath Perera">8- Dr. Sampath Perera</option>
                    <option value="Dr. Vindya Silva">9- Dr. Vindya Silva</option>
                    <option value="Dr. Kalindu Weerasinghe">10- Dr. Kalindu Weerasinghe</option>                   			
			</select>
					
			<br> 

			<label>Hospital ID:</label>
			<input id="hospital_id" name="hospital_id" type="text" class="form-control form-control-sm">
			
			<br> 

			<label>Hospital name:</label>
			<select id="name" name="name" type="text" class="form-control form-control-sm">
					<option value="select">select...</option>
					<option value="Kandy Private Hospital">1- Kandy Private Hospital</option>
                    <option value="Asiri Hospital">2- Asiri Hospital</option>
                    <option value="Nawaloka Hospital">3- Nawaloka Hospital</option>
                    <option value="Lanka Hospitals">4- Lanka Hospitals</option>
                    <option value="Durdans Hospital">5- Durdans Hospital</option>
                    <option value="Asiri Hospital Matara">6- Asiri Hospital Matara</option>                   			
			</select>
					
			<br>

			<label>Appointment Time:</label>
			<input id="appointment_time" name="appointment_time" type="time" class="form-control form-control-sm">

			<br>

			<label>Appointment Date:</label>
			<input id="appointment_date" name="appointment_date" type="date" class="form-control form-control-sm">

			<br>

			<label>Ward No.:</label>
			<select id="WardNo" name="WardNo" type="text" class="form-control form-control-sm">
					<option value="select">select...</option>
					<option value="KPH001">Kandy Private Hospital - KPH001</option>
					<option value="KPH002">Kandy Private Hospital - KPH002</option>
                    <option value="AHC001">Asiri Hospital - AHC001</option>
                    <option value="AHC002">Asiri Hospital - AHC002</option>
                    <option value="NH001">Nawaloka Hospital - NH001</option>
                    <option value="NH002">Nawaloka Hospital - NH002</option>
                    <option value="LH001">Lanka Hospitals - LH001</option>
                    <option value="LH002">Lanka Hospitals - LH002</option>
                    <option value="DH001">Durdans Hospital - DH001</option>
                    <option value="DH002">Durdans Hospital - DH002</option>
                    <option value="AHM001">Asiri Hospital Matara - AHM001</option>                   			
			</select>
			
			<br><br>
			
			<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
			<input type="hidden" id="hidAppointmentIDSave" name="hidAppointmentIDSave" value="">
	
			</form>
			
			<div id="alertSuccess" class="alert alert-success">
			
				<%
					//out.print(session.getAttribute("statusMsg"));
				%>
			
			</div>
			<div id="alertError" class="alert alert-danger"></div>
			
			
			
			<br>
				
			<div id="divAppointmentsGrid">
			
				<%
					 Appointment appointmentObj = new Appointment();
					 out.print(appointmentObj.viewAppointments());
					 					 
				 %>
				 				
			</div>
			
			<br><br><br>
			
			</div>

		</div>

		
	</div>
 
	
</body>

</html>