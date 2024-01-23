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
		rewriteFile();
	}
	
	public RentACarDAO(String path) {
		BufferedReader in = null;
		try {
			File file = new File(this.path);
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

	            if (st.countTokens() <= 8) {
	                String name = st.nextToken().trim();
	                String startTime = st.nextToken().trim();
	                String endTime = st.nextToken().trim();
	                String status = st.nextToken().trim();
	                String address = st.nextToken().trim();
	                String city = st.nextToken().trim();
	                String logoPath = st.nextToken().trim();
	                String grade = st.nextToken().trim();

	                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	                LocalTime _startTime = LocalTime.parse(startTime, formatter);
	                LocalTime _endTime = LocalTime.parse(endTime, formatter);
	                double _grade = Double.parseDouble(grade);

	                RentACar rentACar = new RentACar(name, _startTime, _endTime, address, city, logoPath, _grade);
	                rentACars.put(name, rentACar);
	                rentACarList.add(rentACar);
	            }
	        }
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}
	
	public void writeRentACar(RentACar rentACar) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.path, true))) {

                StringBuilder line = new StringBuilder();

                // Append user data to the line
                line.append(rentACar.getName()).append(";")
                    .append(rentACar.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"))).append(";")
                    .append(rentACar.getEndTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"))).append(";")
                    .append(rentACar.getStatus()).append(";")
                    .append(rentACar.getAddress()).append(";")
                    .append(rentACar.getCity()).append(";")
                    .append(rentACar.getLogoPath()).append(";")
                    .append(rentACar.getGrade());

                writer.write(line.toString());
                writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public ArrayList<RentACar> getList() {
		return rentACarList;
	}
	
	public RentACar update(RentACar updatedRentACar) {
	    RentACar rentACarToUpdate = this.findByName(updatedRentACar.getName());

	    if (rentACarToUpdate != null) {
	        rentACars.remove(updatedRentACar.getName()); // Delete the line containing the found user

	        RentACar newRentACar = new RentACar(
	        	updatedRentACar.getName(),	
	        	updatedRentACar.getStartTime(),	
	        	updatedRentACar.getEndTime(),	
	        	updatedRentACar.getAddress(),	
	        	updatedRentACar.getCity(),	
	        	updatedRentACar.getLogoPath(),	
	        	updatedRentACar.getGrade());

	        rentACars.put(updatedRentACar.getName(), newRentACar);

	        rewriteFile();

	        return newRentACar;
	    }

	    return null; // User not found
	}

	private void rewriteFile() {
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.path))) {
	        for (RentACar rentACar : rentACars.values()) {
	            StringBuilder line = new StringBuilder();

	            line.append(rentACar.getName()).append(";")
                .append(rentACar.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"))).append(";")
                .append(rentACar.getEndTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"))).append(";")
                .append(rentACar.getStatus()).append(";")
                .append(rentACar.getAddress()).append(";")
                .append(rentACar.getCity()).append(";")
                .append(rentACar.getLogoPath()).append(";")
                .append(rentACar.getGrade()).append(";");

	            writer.write(line.toString());
	            writer.newLine();
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}