package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;

import beans.Location;
import beans.User;

public class LocationDAO {
	
	private HashMap<Integer, Location> locations = new HashMap<Integer, Location>();
	private ArrayList<Location> locationList = new ArrayList<Location>();
	private String realPath;
	
	String path = System.getProperty("user.dir") + "\\src\\main\\csv\\locations.txt";
	
	public Collection<Location> findAll()
	{
		return locations.values();
	}
	
	public Location findLocation(int id)
	{
		return locations.containsKey(id) ? locations.get(id) : null;
	}
	
	public Location Save(Location location)
	{
		Integer maxId = -1;
		for (Integer id : locations.keySet()) {
			int idNum = id;
			if (idNum > maxId) {
				maxId = idNum;
			}
		}
		maxId++;
		location.setId(maxId);
		locations.put(location.getId(), location);
		writeLocation(location);
		return location;
	}
	
	public void Delete(Integer id)
	{
		locations.remove(id);
	}
	
	public LocationDAO(String path) {
		BufferedReader in = null;
		try {
			File file = new File(this.path);
			in = new BufferedReader(new FileReader(file));
			readLocations(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if ( in != null ) {
				try {
					in.close();
				}
				catch (Exception e) { }
			}
		}
	}
	
	private void readLocations(BufferedReader in) {
	    String line;
	    try {
	        while ((line = in.readLine()) != null) {
	            line = line.trim();
	            if (line.equals("") || line.startsWith("#"))
	                continue;

	            StringTokenizer st = new StringTokenizer(line, ";");

	            if (st.countTokens() >= 6) {
	                String id = st.nextToken().trim();
	                String longitude = st.nextToken().trim();
	                String latitude = st.nextToken().trim();
	                String address = st.nextToken().trim();
	                String city = st.nextToken().trim();
	                String zipCode = st.nextToken().trim();

	                Location location = new Location(Integer.parseInt(id), Double.parseDouble(longitude), Double.parseDouble(latitude), address, city, zipCode);
	                locations.put(Integer.parseInt(id), location);
	                locationList.add(location);
	                System.out.println(location);
	            }
	        }
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}


    public void writeLocation(Location location) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.path, true))) {

                StringBuilder line = new StringBuilder();

                // Append user data to the line
                line.append(Integer.toString(location.getId())).append(";")
                    .append(Double.toString(location.getLongitude())).append(";")
                    .append(Double.toString(location.getLatitude())).append(";")
                    .append(location.getAddress()).append(";")
                    .append(location.getCity()).append(";")
                    .append(location.getZipCode()).append(";");


                // Write the line to the file
                writer.write(line.toString());
                writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}