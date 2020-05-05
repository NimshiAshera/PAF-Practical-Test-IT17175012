package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import model.Appointment;


/**
 * Servlet implementation class AppointmentsAPI
 */

@WebServlet("/AppointmentsAPI")
public class AppointmentsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       	
	Appointment appointmentObj = new Appointment();
		
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppointmentsAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		Appointment appointmentObj = new Appointment();
		
		String output = appointmentObj.insertAppointment(request.getParameter("doctor_id"),
				 				request.getParameter("doctor_name"),
								request.getParameter("hospital_id"),
				 				request.getParameter("name"),
								request.getParameter("appointment_time"),
				 				request.getParameter("appointment_date"),
				 				request.getParameter("WardNo"));
		response.getWriter().write(output); 
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map paras = getParasMap(request);
		
		Appointment appointmentObj = new Appointment();
		 
		String output = appointmentObj.updateAppointment(paras.get("hidAppointmentIDSave").toString(),
								paras.get("doctor_id").toString(),
								paras.get("doctor_name").toString().replace("+",""),
								paras.get("hospital_id").toString(),
								paras.get("name").toString().replace("+",""),
								paras.get("appointment_time").toString().replace("%3A",":"),
								paras.get("appointment_date").toString(),
								paras.get("WardNo").toString());
		
		response.getWriter().write(output); 
		
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException
	{
		Map paras = getParasMap(request);
		
		Appointment appointmentObj = new Appointment();
			 
		String output = appointmentObj.deleteAppointment(paras.get("ref_id").toString());
			
		response.getWriter().write(output);
			
	}
	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request)
	{
		Map<String, String> map = new HashMap<String, String>();
	
		try
		{
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ?
					scanner.useDelimiter("\\A").next() : "";
	
			scanner.close();
	
			String[] params = queryString.split("&");
	
			for (String param : params)
			{ 
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		 }
		
		catch (Exception e)
		{
		}
		
		return map;
	}

}
