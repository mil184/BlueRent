package beans;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

import enums.RentalStatus;

public class Rental {
	
	private String id;
	private ArrayList<Vehicle> vehicles;
	private ArrayList<Integer> vehicleIds;
	private ArrayList<Integer> rentACarIds;
	private LocalDate  startDate;
	private LocalDate endDate;
	private int duration;
	private double price;
	private String username;
	private RentalStatus status;
	
	public Rental() {
		super();
	}

	public Rental(String id, ArrayList<Integer> vehicleIds, ArrayList<Integer> rentACarIds,
			LocalDate startDate, LocalDate endDate, int duration, double price, String username,
			RentalStatus status) {
		super();
		this.id = id;
		this.vehicleIds = vehicleIds;
		this.rentACarIds = rentACarIds;
		this.startDate = startDate;
		this.endDate = endDate;
		this.duration = duration;
		this.price = price;
		this.username = username;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(ArrayList<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public ArrayList<Integer> getVehicleIds() {
		return vehicleIds;
	}

	public void setVehicleIds(ArrayList<Integer> vehicleIds) {
		this.vehicleIds = vehicleIds;
	}

	public ArrayList<Integer> getRentACarIds() {
		return rentACarIds;
	}

	public void setRentACarIds(ArrayList<Integer> rentACarIds) {
		this.rentACarIds = rentACarIds;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public RentalStatus getStatus() {
		return status;
	}

	public void setStatus(RentalStatus status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(duration, endDate, id, price, rentACarIds, startDate, status, username,
				vehicleIds, vehicles);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rental other = (Rental) obj;
		return duration == other.duration && Objects.equals(endDate, other.endDate) && Objects.equals(id, other.id)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price)
				&& Objects.equals(rentACarIds, other.rentACarIds)
				&& Objects.equals(startDate, other.startDate) && status == other.status
				&& Objects.equals(username, other.username) && Objects.equals(vehicleIds, other.vehicleIds)
				&& Objects.equals(vehicles, other.vehicles);
	}

	@Override
	public String toString() {
		return "Rental [id=" + id + ", vehicles=" + vehicles + ", vehicleIds=" + vehicleIds + ", rentACarIds="
				+ rentACarIds + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", duration=" + duration + ", price=" + price + ", username=" + username + ", status=" + status + "]";
	}
	
	
	
}
