package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;

import beans.Rental;
import beans.Vehicle;
import enums.RentalStatus;

public class RentalDAO {
	
    private HashMap<String, Rental> rentals = new HashMap<String, Rental>();
    private ArrayList<Rental> rentalList = new ArrayList<Rental>();
    
    String path = System.getProperty("user.dir") + "\\src\\main\\csv\\rentals.txt";
    
    private VehicleDAO vehicleDAO;
    
    public Collection<Rental> findAll() {
        return rentals.values();
    }

    public Rental findRental(String id) {
        return rentals.containsKey(id) ? rentals.get(id) : null;
    }
    
    public Rental save(Rental rental) {
        String randomID;
        do {
            randomID = generateRandomID(); 
        } while (rentals.containsKey(randomID));

        rental.setId(randomID);
        rental.setStartDate(LocalDate.now());
        rentals.put(rental.getId(), rental);
        rentalList.add(rental);
        writeRental(rental);
        System.out.println(randomID);
        setVehicles();
        return rental;
    }

    private String generateRandomID() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomID = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int index = (int) (Math.random() * characters.length());
            randomID.append(characters.charAt(index));
        }

        return randomID.toString();
    }

    
    public void Delete(String id) {
        rentals.remove(id);
    }
    
    public RentalDAO(VehicleDAO vehicleDao) {
    	vehicleDAO = vehicleDao;
        System.out.println(path);
        BufferedReader in = null;
        try {
            File file = new File(path);
            in = new BufferedReader(new FileReader(file));
            readRentals(in);
            setVehicles();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (in != null) {
                try {
                    in.close();
                }
                catch (Exception e) { }
            }
        }
    }
    
    private void setVehicles() {
    	for(Rental rental: rentals.values()) {
    		ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
    		for(int vehicleId: rental.getVehicleIds()) {
    			vehicles.add(vehicleDAO.getById(vehicleId));
    		}
    		rental.setVehicles(vehicles);
    	}
    }
    
    private void readRentals(BufferedReader in) {
        String line;
        try {
            while ((line = in.readLine()) != null) {
                line = line.trim();
                if (line.equals("") || line.startsWith("#"))
                    continue;

                StringTokenizer st = new StringTokenizer(line, ";");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                if (st.countTokens() >= 4) {
                    String id = st.nextToken().trim();

                    String vehicleIds = st.nextToken().trim();
                    ArrayList<Integer> vehicleIdList = new ArrayList<>();
                    StringTokenizer idTokenizer = new StringTokenizer(vehicleIds, "|");
                    while (idTokenizer.hasMoreTokens()) {
                        String vehicleId = idTokenizer.nextToken().trim();
                        vehicleIdList.add(Integer.parseInt(vehicleId));
                    }
              
                    int rentACarId = Integer.parseInt(st.nextToken().trim());
                    LocalDate startDate = LocalDate.parse(st.nextToken().trim(), formatter);

                    LocalDate endDate = LocalDate.parse(st.nextToken().trim(), formatter);
                    
                    int duration = Integer.parseInt(st.nextToken().trim());

                    double price = Double.parseDouble(st.nextToken().trim());

                    String username = st.nextToken().trim();

                    RentalStatus status = RentalStatus.valueOf(st.nextToken().trim());

                    Rental rental = new Rental(id, vehicleIdList, rentACarId ,startDate, endDate, duration, price, username, status);
                    rentalList.add(rental);
                    rentals.put(id, rental);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void writeRental(Rental rental) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            StringBuilder line = new StringBuilder();

            line.append(rental.getId()).append(";");

            ArrayList<Integer> vehicleIds = rental.getVehicleIds();
            if (vehicleIds != null) {
                for (int i = 0; i < vehicleIds.size(); i++) {
                    line.append(vehicleIds.get(i));
                    if (i < vehicleIds.size() - 1) {
                        line.append("|");
                    }
                }
            }
            line.append(";");

            line.append(rental.getRentACarId()).append(";");
            line.append(rental.getStartDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))).append(";");
            line.append(rental.getEndDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))).append(";");
            line.append(rental.getPrice()).append(";");
            line.append(rental.getUsername()).append(";");
            line.append(rental.getStatus()).append(";");

            writer.write(line.toString());
            writer.newLine();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Rental update(Rental updatedRental) {
        Rental rentalToUpdate = findRental(updatedRental.getId());

        if (rentalToUpdate != null) {
            rentalToUpdate.setVehicleIds(updatedRental.getVehicleIds());
            rentalToUpdate.setRentACarId(updatedRental.getRentACarId());
            rentalToUpdate.setStartDate(updatedRental.getStartDate());
            rentalToUpdate.setEndDate(updatedRental.getEndDate());
            rentalToUpdate.setPrice(updatedRental.getPrice());
            rentalToUpdate.setUsername(updatedRental.getUsername());
            rentalToUpdate.setStatus(updatedRental.getStatus());
            
            rewriteRentalsFile();

            return rentalToUpdate;
        }

        return null; // Rental not found
    }

    private void rewriteRentalsFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (Rental rental : rentals.values()) {
                StringBuilder line = new StringBuilder();

                line.append(rental.getId()).append(";");

                ArrayList<Integer> vehicleIds = rental.getVehicleIds();
                if (vehicleIds != null) {
                    for (int i = 0; i < vehicleIds.size(); i++) {
                        line.append(vehicleIds.get(i));
                        if (i < vehicleIds.size() - 1) {
                            line.append("|");
                        }
                    }
                }
                line.append(";");
                
                line.append(rental.getRentACarId()).append(";");
                line.append(rental.getStartDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))).append(";");
                line.append(rental.getEndDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))).append(";");
                line.append(rental.getPrice()).append(";");
                line.append(rental.getUsername()).append(";");
                line.append(rental.getStatus()).append(";");

                writer.write(line.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
