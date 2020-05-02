package com.HealthcareSystem.service.Appointment;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.HealthcareSystem.resources.AppointmentViewResources;


@Path("/View")
public class AppointmentViewService {
	
	AppointmentViewResources appointmentObj = new AppointmentViewResources();
	
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	
	public String viewAppointment()
	 {
		return appointmentObj.viewAppointment(); 

	 }

	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String insertAppointmentView
	(	@FormParam("doctor_id") String doctor_id,
		@FormParam("doctor_name") String doctor_name,
		@FormParam("hospital_id") String hospital_id,
		@FormParam("name") String name,
		@FormParam("appointment_time") String appointment_time,
		@FormParam("appointment_date") String appointment_date,
		@FormParam("WardNo") String WardNo
	)
			
	{
		String output = appointmentObj.insertAppointmentView(doctor_id,doctor_name,hospital_id,name,appointment_time,appointment_date,WardNo);
	
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String updateAppointmentView(String appointmentViewData)
	{
		//Convert the input string to a JSON object
		JsonObject appointmentObject = new JsonParser().parse(appointmentViewData).getAsJsonObject();
	
		//Read the values from the JSON object
		String ref_id = appointmentObject.get("ref_id").getAsString();
		String doctor_id = appointmentObject.get("doctor_id").getAsString();
		String doctor_name = appointmentObject.get("doctor_name").getAsString();
		String hospital_id = appointmentObject.get("hospital_id").getAsString();
		String name = appointmentObject.get("name").getAsString();
		String appointment_time = appointmentObject.get("appointment_time").getAsString();
		String appointment_date = appointmentObject.get("appointment_date").getAsString();
		String WardNo = appointmentObject.get("WardNo").getAsString();
		
		String output = appointmentObj.updateAppointmentView(ref_id, doctor_id, doctor_name,hospital_id,name,appointment_time,appointment_date,WardNo);
	
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String deleteAppointmentView(String appointmentViewData)
	{
		
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(appointmentViewData, "", Parser.xmlParser());

		//Read the value from the element <itemID>
		String ref_id = doc.select("ref_id").text();
		String output = appointmentObj.deleteAppointmentView(ref_id);
		return output;
	}

}
