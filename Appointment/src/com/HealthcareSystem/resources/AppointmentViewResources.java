package com.HealthcareSystem.resources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.cj.xdevapi.Statement;

import com.HealthcareSystem.utils.DBConnection;

public class AppointmentViewResources {

	
	//view list of available appointment 
	
			public String listAppointment() {

				String output = "";
				
				try
				{
					
					DBConnection db = new DBConnection();
					Connection conn = null;
					conn = db.getConnection();
					
					if (conn == null)
					{
						return "Error while connecting to the database for reading."; 
					}
			 
					// Prepare the html table to be displayed
					
					output = "<table border=\"2\" width=\"150%\"><tr style=\"text-align:center\" ><th>Ref ID</th><th>Doctor ID</th><th>Doctor Name</th><th>Hospital ID</th><th>Hospital Name</th><th>Appointment Time</th><th>Appointment Date</th><th>Ward No</th></tr>";
			 
					String query = " select * from appointment_view";
				
					PreparedStatement stmt = conn.prepareStatement(query);
					ResultSet rs = stmt.executeQuery(query);
			
					// iterate through the rows in the result set
			 
					while (rs.next())
					{
						String ref_id = Integer.toString(rs.getInt("ref_id"));
						String doctor_id = Integer.toString(rs.getInt("doctor_id"));
						String doctor_name = rs.getString("doctor_name");
						String hospital_id = Integer.toString(rs.getInt("hospital_id"));
						String name = rs.getString("name");
						String appointment_time = rs.getString("appointment_time");
						String appointment_date = rs.getString("appointment_date");
						String WardNo = rs.getString("WardNo");
			
						// Add into the html table
						
						output += "<tr style=\"text-align:center\"><td>"+ ref_id +" </td>";
						output += "<td>"+ doctor_id +" </td> ";
						output += "<td>" + doctor_name + "</td>";
						output += "<td>" + hospital_id + "</td>";
						output += "<td>" + name + "</td>";
						output += "<td>" + appointment_time + "</td>";
						output += "<td>" + appointment_date + "</td>";
						output += "<td>" + WardNo + "</td>";
					
					}
			
					conn.close();
					// Complete the html table
			
					output += "</table>";
				}
				
				catch (Exception e)
				{
					output = "Error while reading the appointments.";
					System.err.println(e.getMessage());
				}
			
				return output;
				
			}
			
	
			public String viewAppointment()
			 {
				String output = "";
								
				try
				{
					
					DBConnection db = new DBConnection();
					Connection conn = null;
					conn = db.getConnection();
					
					if (conn == null)
					{
						return "Error while connecting to the database for reading."; 
					}
			 
					// Prepare the html table to be displayed
					
					output = "<table border=\"2\" width=\"150%\"><tr style=\"text-align:center\" ><th>Doctor ID</th><th>Doctor Name</th><th>Hospital ID</th><th>Hospital Name</th><th>Appointment Time</th><th>Appointment Date</th><th>Ward No</th><th>Update</th><th>Remove</th></tr>";
			 
					String query = " select * from appointment_view";
				
					PreparedStatement stmt = conn.prepareStatement(query);
					ResultSet rs = stmt.executeQuery(query);
			
					// iterate through the rows in the result set
			 
					while (rs.next())
					{
						String ref_id = Integer.toString(rs.getInt("ref_id"));
						String doctor_id = Integer.toString(rs.getInt("doctor_id"));
						String doctor_name = rs.getString("doctor_name");
						String hospital_id = Integer.toString(rs.getInt("hospital_id"));
						String name = rs.getString("name");
						String appointment_time = rs.getString("appointment_time");
						String appointment_date = rs.getString("appointment_date");
						String WardNo = rs.getString("WardNo");
			
						// Add into the html table
						
						output += "<tr style=\"text-align:center\"><td><input id=\"hidViewIDUpdate\" name=\"hidViewIDUpdate\" type=\"hidden\" value=\"" + ref_id + "\">" + doctor_id + "</td>"; 
						output += "<td>" + doctor_name + "</td>";
						output += "<td>" + hospital_id + "</td>";
						output += "<td>" + name + "</td>";
						output += "<td>" + appointment_time + "</td>";
						output += "<td>" + appointment_date + "</td>";
						output += "<td>" + WardNo + "</td>";
			
						//buttons
						output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>  "
								+ "<td><input name='btnRemove' type='button' value='Remove'  class='btnRemove btn btn-danger' data-ref_id='" + ref_id + "'>" + "</td></tr>"; 
					
					
					}
			
					conn.close();
					// Complete the html table
			
					output += "</table>";
				}
				
				catch (Exception e)
				{
					output = "Error while reading the appointments.";
					System.err.println(e.getMessage());
				}
			
				return output;
			 } 
			
			//add new appointment to view
			
			public String insertAppointmentView(String doctor_id,String doctor_name,String hospital_id, String name, String appointment_time, String appointment_date, String WardNo)
			{
				String output = "";
				
				try
				{
					DBConnection db = new DBConnection();
					Connection conn = null;
					conn = db.getConnection();
					
					if (conn == null)
					{
						return "Error while connecting to the database for inserting."; 
					}
			
					// create a prepared statement
			
					String query = " insert into appointment_view(`ref_id`,`doctor_id`,`doctor_name`,`hospital_id`,`name`,`appointment_time`,`appointment_date`,`WardNo`)" + " values (?, ?, ?, ?, ?, ?, ?, ?)";
			 
					PreparedStatement preparedStmt = conn.prepareStatement(query);
			
					// binding values
			
					preparedStmt.setInt(1, 0);
					preparedStmt.setString(2, doctor_id);
					preparedStmt.setString(3, doctor_name);
					preparedStmt.setString(4, hospital_id);
					preparedStmt.setString(5, name);
					preparedStmt.setString(6, appointment_time);
					preparedStmt.setString(7, appointment_date);
					preparedStmt.setString(8, WardNo);
					 
			
					// execute the statement
					preparedStmt.execute();
					conn.close();
					
					String newView = viewAppointment();
					output = "{\"status\":\"success\", \"data\": \"" + newView + "\"}"; 
				}
				catch (Exception e)
				{
					output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";
					System.err.println(e.getMessage());
				}
				
				return output;
			 } 
			
			//update appointment view
			
					public String updateAppointmentView(String ref_id, String doctor_id, String doctor_name, String hospital_id, String name, String appointment_time, String appointment_date, String WardNo)
					{
						String output = "";
					
						try
						{
							DBConnection db = new DBConnection();
							Connection conn = null;
							conn = db.getConnection();
					
								if (conn == null)
								{
									return "Error while connecting to the database for updating."; }
					 
								// create a prepared statement
								String query = "UPDATE appointment_view SET doctor_id=?, doctor_name=?, hospital_id=?, name=?, appointment_time=?, appointment_date=?, WardNo=?   WHERE ref_id=?";
								
								PreparedStatement preparedStmt = conn.prepareStatement(query);
								
								// binding values
								preparedStmt.setString(1, doctor_id);
								preparedStmt.setString(2, doctor_name);
								preparedStmt.setString(3, hospital_id);
								preparedStmt.setString(4, name);
								preparedStmt.setString(5, appointment_time);
								preparedStmt.setString(6, appointment_date);
								preparedStmt.setString(7, WardNo);
								preparedStmt.setInt(8, Integer.parseInt(ref_id)); 
					
								// execute the statement
								preparedStmt.execute();
					
								conn.close();

								String newView = viewAppointment();
								output = "{\"status\":\"success\", \"data\": \"" + newView + "\"}"; 

						}
						
						catch (Exception e)
						{
							output = "{\"status\":\"error\", \"data\": \"Error while updating the appointment list.\"}"; 
							System.err.println(e.getMessage());
						}
						
						return output;
					 } 

					
					//delete appointment view
					
					public String deleteAppointmentView(String ref_id)
					{
						
						String output = "";
						
						try
						{
							
							DBConnection db = new DBConnection();
							Connection conn = null;
							conn = db.getConnection();
						
							if (conn == null)
					
							{
								return "Error while connecting to the database for deleting."; }
					
								// create a prepared statement
								String query = "delete from appointment_view where ref_id=?";
								PreparedStatement preparedStmt = conn.prepareStatement(query);
								
								// binding values
								preparedStmt.setInt(1, Integer.parseInt(ref_id));
					
								// execute the statement
								preparedStmt.execute();
								
								conn.close();
					 
								String newView = viewAppointment();
								output = "{\"status\":\"success\", \"data\": \"" + newView + "\"}"; 
							
							}
					
							catch (Exception e)
							{
					
								output =  "{\"status\":\"error\", \"data\": \"Error while deleting the appointment view.\"}"; 
								System.err.println(e.getMessage());
							}
						return output;
						
						} 
	
}
