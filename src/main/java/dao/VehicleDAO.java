package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;

import beans.Vehicle;
import enums.DriveType;
import enums.FuelType;
import enums.VehicleStatus;
import enums.VehicleType;

public class VehicleDAO {

	private HashMap<Integer, Vehicle> vehicles = new HashMap<Integer, Vehicle>();
	private ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();
	
	String path = System.getProperty("user.dir") + "\\src\\main\\csv\\vehicles.txt";
	
	public Collection<Vehicle> findAll()
	{
		return vehicles.values();	
	}
	
	public Vehicle findVehicle(int id) 
	{
		return vehicles.containsKey(id) ? vehicles.get(id) : null;
	}
	
	public Vehicle Save(Vehicle vehicle) 
	{
		Integer maxId = -1;
		for (Integer id : vehicles.keySet()) {
			int idNum = id;
			if (idNum > maxId) {
				maxId = idNum;
			}
		}
		maxId++;
		vehicle.setId(maxId);
		vehicles.put(vehicle.getId(), vehicle);
		System.out.println("Entered save in dao, now going into write..");
		writeVehicle(vehicle);
		return vehicle;
	}
	
	public void Delete(int id)  
	{
		vehicles.remove(id);
		rewriteVehiclesFile();
	}
	
	public Vehicle update(Vehicle updatedVehicle) {
	    Vehicle vehicleToUpdate = this.findVehicle(updatedVehicle.getId());

	    if (vehicleToUpdate != null) {
	        vehicles.remove(updatedVehicle.getId());

	        Vehicle newVehicle = new Vehicle(
	            updatedVehicle.getId(),
	            updatedVehicle.getBrand(),
	            updatedVehicle.getModel(),
	            updatedVehicle.getPrice(),
	            updatedVehicle.getVehicleType(),
	            updatedVehicle.getRentACarId(),
	            updatedVehicle.getDriveType(),
	            updatedVehicle.getFuelType(),
	            updatedVehicle.getConsumption(),
	            updatedVehicle.getDoorCount(),
	            updatedVehicle.getPeopleCount(),
	            updatedVehicle.getDescription(),
	            updatedVehicle.getImageUrl(),
	            updatedVehicle.getStatus()
	        );

	        vehicles.put(updatedVehicle.getId(), newVehicle); 

	        rewriteVehiclesFile();

	        return newVehicle;
	    }

	    return null;
	}

	
	public Vehicle getById(int id) {
		return vehicles.containsKey(id) ? vehicles.get(id) : null;
	}

	public VehicleDAO() {
		BufferedReader in = null;
		try {
			File file = new File(path);
			in = new BufferedReader(new FileReader(file));
			readVehicles(in);
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


	private void readVehicles(BufferedReader in) {
	    String line;
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm"); 

	    try {
	        while ((line = in.readLine()) != null) {
	            line = line.trim();
	            if (line.equals("") || line.startsWith("#"))
	                continue;

	            StringTokenizer st = new StringTokenizer(line, ";");
	            if (st.countTokens() >= 6) {
	                int id = Integer.parseInt(st.nextToken().trim());
	                String brand = st.nextToken().trim();
	                String model = st.nextToken().trim();
	                double price = Double.parseDouble(st.nextToken().trim());
	                VehicleType vehicleType = VehicleType.valueOf(st.nextToken().trim());
	                int rentACarId = Integer.parseInt(st.nextToken().trim());
	                DriveType driveType = DriveType.valueOf(st.nextToken().trim());
	                FuelType fuelType = FuelType.valueOf(st.nextToken().trim());
	                double consumption = Double.parseDouble(st.nextToken().trim());
	                int doorCount = Integer.parseInt(st.nextToken().trim());
	                int peopleCount = Integer.parseInt(st.nextToken().trim());
	                String description = st.nextToken().trim();
	                String imageUrl = st.nextToken().trim();
	                VehicleStatus status = VehicleStatus.valueOf(st.nextToken().trim());

	                Vehicle vehicle = new Vehicle(id, brand, model, price, vehicleType, rentACarId, driveType, fuelType, consumption, doorCount, peopleCount, description, imageUrl, status);
	                vehicles.put(vehicle.getId(), vehicle);
	            }
	        }
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}


    public void writeVehicle(Vehicle vehicle) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {

                StringBuilder line = new StringBuilder();
                 System.out.println("WRITING TO FILE");
                line.append(vehicle.getId()).append(";")
                .append(vehicle.getBrand()).append(";")
                .append(vehicle.getModel()).append(";")
                .append(vehicle.getPrice()).append(";")
                .append(vehicle.getVehicleType()).append(";")
                .append(vehicle.getRentACarId()).append(";")
                .append(vehicle.getDriveType()).append(";")
                .append(vehicle.getFuelType()).append(";")
                .append(vehicle.getConsumption()).append(";")
                .append(vehicle.getDoorCount()).append(";")
                .append(vehicle.getPeopleCount()).append(";")
                .append(vehicle.getDescription()).append(";")
                .append(vehicle.getImageUrl()).append(";")
                .append(vehicle.getStatus()).append(";");

                writer.write(line.toString());
                writer.newLine();
                
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	private void rewriteVehiclesFile() {
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
	        for (Vehicle vehicle : vehicles.values()) {
	            StringBuilder line = new StringBuilder();

                line.append(vehicle.getId()).append(";")
                .append(vehicle.getBrand()).append(";")
                .append(vehicle.getModel()).append(";")
                .append(vehicle.getPrice()).append(";")
                .append(vehicle.getVehicleType()).append(";")
                .append(vehicle.getRentACarId()).append(";")
                .append(vehicle.getDriveType()).append(";")
                .append(vehicle.getFuelType()).append(";")
                .append(vehicle.getConsumption()).append(";")
                .append(vehicle.getDoorCount()).append(";")
                .append(vehicle.getPeopleCount()).append(";")
                .append(vehicle.getDescription()).append(";")
                .append(vehicle.getImageUrl()).append(";")
                .append(vehicle.getStatus()).append(";");

	            writer.write(line.toString());
	            writer.newLine();
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}
