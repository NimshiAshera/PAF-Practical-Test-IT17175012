<%@page import="com.HealthcareSystem.resources.AppointmentViewResources" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Appointment List</title>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<link rel="stylesheet" href="Views/style.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/appointment.js"></script>

</head>

<body>

	<div class="container">
		<div class="row">
			<div class="col-8">

 			<h1 class="m-3" style="text-align:center">Appointment List</h1>
 			
 			<br>
 			
 			<form id="viewList" name="viewList" method="post" action="AppointmentView.jsp">
 						
			<label >Doctor ID:</label>
 			<input id="doctor_id" name="doctor_id" type="number" min="1" max="10" class="form-control form-control-sm">
			
			<br> 
			
			<label>Doctor Name:</label>
			<select id="doctor_name" name="doctor_name" type="text" class="form-control form-control-sm">
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
 			<input id="hospital_id" name="hospital_id" type="number" min="1" max="6" class="form-control form-control-sm">
			
			<br> 
			
			<label>Hospital Name:</label>
			<select id="name" name="name" type="text" class="form-control form-control-sm">
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
			
			<label>Hospital Ward:</label>
			<select id="WardNo" name="WardNo" type="text" class="form-control form-control-sm">
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
			<input type="hidden" id="hidRef_IDSave" name="hidRef_IDSave" value="">
			<a href="AppointmentView.jsp"><input id="btn" name="btn" type="button" value="Update OR Delete" class="btn btn-primary"></a>
			
			<br><br><br>
 			
 			</form>
 			
 			<br>
				<div id="divViewsGrid">
					
			
				<%
					 AppointmentViewResources appointmentObj = new AppointmentViewResources();
					 out.print(appointmentObj.listAppointment());
					 
					//Save---------------------------------
					 if (request.getParameter("doctor_id") != null)
					 {
					  	
					  	String stsMsg = "";
					 	
					  	//Insert--------------------------
					 	if (request.getParameter("hidRef_IDSave") == "")
					  	{
					  
					 		stsMsg = appointmentObj.insertAppointmentView(
					 				request.getParameter("doctor_id"),
					 				request.getParameter("doctor_name"),
					 				request.getParameter("hospital_id"),
					 				request.getParameter("name"),
					  				request.getParameter("appointment_time"),
					  				request.getParameter("appointment_date"),
					  				request.getParameter("WardNo")
					  		);
					  	}
					 
					  	session.setAttribute("statusMsg", stsMsg);
					 }
					 
					 		 
				 %>
				 
				
				</div>
				
				<br><br><br>
 			
 			
 			</div>

		</div>

	</div>

</body>
</html>