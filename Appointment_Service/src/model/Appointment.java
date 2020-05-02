package model;

import java.sql.*;

public class Appointment
{ 
	//A common method to connect to the DB

	private Connection connect()
	{
		Connection con = null;
 
		try
		{
			Class.forName("com.mysql.jdbc.Driver");

			//Provide the correct details: DBServer/DBName, username, password
			
			con = DriverManager.getConnection("jdbc:mysql://localhost/new?useSSL=false" , "root", "root");
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return con;
	}

	
	public String insertAppointment(String doctor_id, String doctor_name, String hospital_id, String name, String appointment_time, String appointment_date, String WardNo)
	{
		String output = "";

		try
		{
			Connection con = connect();
	
			if (con == null)
			{
				return "Error while connecting to the database for inserting."; 
			}
 
			// create a prepared statement
			String query = " insert into appointment (`ref_id`,`doctor_id`,`doctor_name`,`hospital_id`,`name`,`appointment_time`,`appointment_date`,`WardNo`)" + " values (?, ?, ?, ?, ?, ?, ?, ?)";
 
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, doctor_id);
			preparedStmt.setString(3, doctor_name);
			preparedStmt.setString(4, hospital_id);
			preparedStmt.setString(5, name);
 			preparedStmt.setString(6, appointment_time);
			preparedStmt.setString(7, appointment_date);
			preparedStmt.setString(8, WardNo);
 
			//execute the statement
			preparedStmt.execute();
			con.close();

			String newAppointment = viewAppointments();
			output = "{\"status\":\"success\", \"data\": \"" + newAppointment + "\"}"; 

		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while inserting.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String viewAppointments()
	{
		String output = "";
	
		try
		{
			Connection con = connect();

			if (con == null)
			{
				return "Error while connecting to the database for reading."; 
			}

			// Prepare the html table to be displayed
			output = "<table border=\"2\" width=\"150%\"><tr style=\"text-align:center\" ><th>Doctor ID</th><th>Doctor Name</th><th>Hospital ID</th><th>Hospital Name</th><th>Appointment Time</th><th>Appointment Date</th><th>Ward No.</th><th>Update</th><th>Remove</th></tr>";

			String query = "select * from appointment ";
			
			Statement stmt = con.createStatement();
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
				output += "<tr style=\"text-align:center\"><td><input id=\"hidAppointmentIDUpdate\" name=\"hidAppointmentIDUpdate\" type=\"hidden\" value=\"" + ref_id + "\">" + doctor_id + "</td>"; 
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

			con.close();

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

	public String updateAppointment(String ref_id, String doctor_id, String doctor_name, String hospital_id, String name, String appointment_time, String appointment_date, String WardNo)
	{
		String output = "";

		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for updating."; 
			}

			// create a prepared statement
			String query = "UPDATE appointment SET doctor_id=?, doctor_name=?, hospital_id=?, name=?, appointment_time=?, appointment_date=?, WardNo=? WHERE ref_id=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

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

			con.close();

			String newAppointment = viewAppointments();
			output = "{\"status\":\"success\", \"data\": \"" + newAppointment + "\"}"; 

		}

		catch (Exception e)
		{

			output = "{\"status\":\"error\", \"data\": \"Error while updating.\"}"; 
			System.err.println(e.getMessage());
		}

		return output;

	} 


	public String deleteAppointment(String ref_id)
	{

		String output = "";

		try

		{

			Connection con = connect();

			if (con == null)

			{
				return "Error while connecting to the database for deleting."; 
			}

			// create a prepared statement
			String query = "delete from appointment where ref_id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(ref_id));

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newAppointment = viewAppointments();
			 output = "{\"status\":\"success\", \"data\": \"" + newAppointment + "\"}"; 
		}

		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while deleting.\"}"; 
			System.err.println(e.getMessage());
		}

		return output;
	}
}
 

