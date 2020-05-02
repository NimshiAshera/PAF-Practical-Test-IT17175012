package com.HealthcareSystem.service.Appointment;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document; 

import com.google.gson.*;

import com.HealthcareSystem.resources.AppointmentResources;


@Path("/Appointments")
public class AppointmentService {
	
	AppointmentResources appointmentObj = new AppointmentResources();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	
	public String readAppointment()
	 {
		return appointmentObj.readAppointment(); 

	 } 
	
	
	@GET
	@Path("/{appointment_id}")
	@Produces(MediaType.TEXT_HTML)
	
	public String readRequiredAppointment(@PathParam("appointment_id") String appointment_id)
	 {
		return appointmentObj.readRequiredAppointment(appointment_id); 

	 }
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String insertAppointment
	(	@FormParam("ref_id") String ref_id,
		@FormParam("user_id") String user_id
	)
			
	{
		String output = appointmentObj.insertAppointment(ref_id,user_id);
	
		return output;
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String updateAppointment(String appointmentData)
	{
		//Convert the input string to a JSON object
		JsonObject appointmentObject = new JsonParser().parse(appointmentData).getAsJsonObject();
	
		//Read the values from the JSON object
		String appointment_id = appointmentObject.get("appointment_id").getAsString();
		String ref_id = appointmentObject.get("ref_id").getAsString();
		String user_id = appointmentObject.get("user_id").getAsString();
	 
		String output = appointmentObj.updateAppointment(appointment_id, ref_id, user_id);
	
		return output;
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String deleteAppointment(String appointmentData)
	{
		
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(appointmentData, "", Parser.xmlParser());

		//Read the value from the element <itemID>
		String appointment_id = doc.select("appointment_id").text();
		String output = appointmentObj.deleteAppointment(appointment_id);
		return output;
	}


}
