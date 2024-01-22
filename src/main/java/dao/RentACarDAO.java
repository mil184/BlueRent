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

import beans.RentACar;
import beans.User;

public class RentACarDAO {
	
	private HashMap<String, RentACar> rentACars = new HashMap<String, RentACar>();
	private ArrayList<RentACar> rentACarList = new ArrayList<RentACar>();
	private String realPath;
	
	String path = System.getProperty("user.dir") + "\\src\\main\\csv\\rentACars.txt";

	
	public Collection<RentACar> findAll(){
		return rentACars.values();
	}
	
	public RentACar findByName(String name) {
		return rentACars.containsKey(name) ? rentACars.get(name) : null;
	}
	
	public RentACar Save(RentACar rentACar) {
		rentACars.put(rentACar.getName(), rentACar);
		writeRentACar(rentACar);
		return rentACar;
	}
	
	public void Delete(String name) {
		rentACars.remove(name);
	}
	
	public RentACarDAO(String path) {
		BufferedReader in = null;
		try {
			File file = new File(path);
			in = new BufferedReader(new FileReader(file));
			readRentACars(in);
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
	
	private void readRentACars(BufferedReader in) {
	    String line;
	    try {
	        while ((line = in.readLine()) != null) {
	            line = line.trim();
	            if (line.equals("") || line.startsWith("#"))
	                continue;

	            StringTokenizer st = new StringTokenizer(line, ";");

	            if (st.countTokens() >= 7) {
	                String name = st.nextToken().trim();
	                String startTime = st.nextToken().trim();
	                String endTime = st.nextToken().trim();
	                String status = st.nextToken().trim();
	                String locationId = st.nextToken().trim();
	                String logoPath = st.nextToken().trim();
	                String grade = st.nextToken().trim();

	                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	                LocalTime _startTime = LocalTime.parse(startTime, formatter);
	                LocalTime _endTime = LocalTime.parse(endTime, formatter);

	                RentACar rentACar = new RentACar(name, _startTime, _endTime, Integer.parseInt(locationId), logoPath, Double.parseDouble(grade));
	                rentACars.put(name, rentACar);
	                rentACarList.add(rentACar);
	                System.out.println(rentACar);
	            }
	        }
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}
	
	public void writeRentACar(RentACar rentACar) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {

                StringBuilder line = new StringBuilder();

                // Append user data to the line
                line.append(rentACar.getName()).append(";")
                    .append(rentACar.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"))).append(";")
                    .append(rentACar.getEndTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"))).append(";")
                    .append(rentACar.getStatus()).append(";")
                    .append(rentACar.getLocationId()).append(";")
                    .append(rentACar.getLogoPath()).append(";")
                    .append(rentACar.getGrade()).append(";");

                writer.write(line.toString());
                writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public ArrayList<RentACar> getList() {
		return rentACarList;
	}
	
	// public RentACar Update(RentACar rentACar)
	
	// private void rewriteRentACarsFile()
}