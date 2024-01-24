package beans;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

import enums.RentalStatus;

public class Rental {
	
	private String id;
	private ArrayList<Vehicle> vehicles;
	private ArrayList<Integer> vehicleIds;
	private int rentACarId;
	private LocalDate  startDate;
	private LocalDate endDate;
	private int duration;
	private double price;
	private String username;
	private RentalStatus status;
	private String reason;
	
	public Rental() {
		super();
	}

	public Rental(String id, ArrayList<Integer> vehicleIds, int rentACarId,
			LocalDate startDate, LocalDate endDate, int duration, double price, String username,
			RentalStatus status, String reason) {
		super();
		this.id = id;
		this.vehicleIds = vehicleIds;
		this.rentACarId = rentACarId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.duration = duration;
		this.price = price;
		this.username = username;
		this.status = status;
		this.reason = reason;
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

	public int getRentACarId() {
		return rentACarId;
	}

	public void setRentACarId(int rentACarId) {
		this.rentACarId = rentACarId;
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
	
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public int hashCode() {
		return Objects.hash(duration, endDate, id, price, reason, rentACarId, startDate, status, username, vehicleIds,
				vehicles);
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
				&& Objects.equals(reason, other.reason) && rentACarId == other.rentACarId
				&& Objects.equals(startDate, other.startDate) && status == other.status
				&& Objects.equals(username, other.username) && Objects.equals(vehicleIds, other.vehicleIds)
				&& Objects.equals(vehicles, other.vehicles);
	}

	@Override
	public String toString() {
		return "Rental [id=" + id + ", vehicles=" + vehicles + ", vehicleIds=" + vehicleIds + ", rentACarId="
				+ rentACarId + ", startDate=" + startDate + ", endDate=" + endDate + ", duration=" + duration
				+ ", price=" + price + ", username=" + username + ", status=" + status + ", reason=" + reason + "]";
	}
	
}
