package beans;

import java.util.ArrayList;
import java.util.Objects;

public class ShoppingCart {

	private int id;
	private ArrayList<Integer> vehicleIds;
	private ArrayList<Vehicle> vehicles;
	private String username;
	private double price;
	
	public ShoppingCart() {
		super();
	}

	public ShoppingCart(int id, ArrayList<Integer> vehicleIds, ArrayList<Vehicle> vehicles, String username,
			double price) {
		super();
		this.id = id;
		this.vehicleIds = vehicleIds;
		this.vehicles = vehicles;
		this.username = username;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Integer> getVehicleIds() {
		return vehicleIds;
	}

	public void setVehicleIds(ArrayList<Integer> vehicleIds) {
		this.vehicleIds = vehicleIds;
	}

	public ArrayList<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(ArrayList<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, price, username, vehicleIds, vehicles);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShoppingCart other = (ShoppingCart) obj;
		return id == other.id && Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price)
				&& Objects.equals(username, other.username) && Objects.equals(vehicleIds, other.vehicleIds)
				&& Objects.equals(vehicles, other.vehicles);
	}

	@Override
	public String toString() {
		return "ShoppingCart [id=" + id + ", vehicleIds=" + vehicleIds + ", vehicles=" + vehicles + ", username="
				+ username + ", price=" + price + "]";
	}
	
	
}
