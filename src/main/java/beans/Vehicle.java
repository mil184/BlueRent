package beans;

import java.util.Objects;

import enums.DriveType;
import enums.FuelType;
import enums.VehicleStatus;
import enums.VehicleType;

public class Vehicle {

	private int id;
	private String brand;
	private String model;
	private double price;
	private VehicleType vehicleType;
	private int rentACarId;
	private DriveType driveType;
	private FuelType fuelType;
	private double consumption;
	private int doorCount;
	private int peopleCount;
	private String description;
	private String imageUrl;
	private VehicleStatus status;
	
	public Vehicle() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Vehicle(int id, String brand, String model, double price, VehicleType vehicleType,
			int rentACarId, DriveType driveType, FuelType fuelType, double consumption, int doorCount, int peopleCount,
			String description, String imageUrl, VehicleStatus status) {
		super();
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.price = price;
		this.vehicleType = vehicleType;
		this.rentACarId = rentACarId;
		this.driveType = driveType;
		this.fuelType = fuelType;
		this.consumption = consumption;
		this.doorCount = doorCount;
		this.peopleCount = peopleCount;
		this.description = description;
		this.imageUrl = imageUrl;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public int getRentACarId() {
		return rentACarId;
	}

	public void setRentACarId(int rentACarId) {
		this.rentACarId = rentACarId;
	}

	public DriveType getDriveType() {
		return driveType;
	}

	public void setDriveType(DriveType driveType) {
		this.driveType = driveType;
	}

	public FuelType getFuelType() {
		return fuelType;
	}

	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
	}

	public double getConsumption() {
		return consumption;
	}

	public void setConsumption(double consumption) {
		this.consumption = consumption;
	}

	public int getDoorCount() {
		return doorCount;
	}

	public void setDoorCount(int doorCount) {
		this.doorCount = doorCount;
	}

	public int getPeopleCount() {
		return peopleCount;
	}

	public void setPeopleCount(int peopleCount) {
		this.peopleCount = peopleCount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public VehicleStatus getStatus() {
		return status;
	}

	public void setStatus(VehicleStatus status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(brand, consumption, description, doorCount, driveType, fuelType, id, imageUrl, model, peopleCount,
				price, rentACarId, status, vehicleType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		return Objects.equals(brand, other.brand)
				&& Double.doubleToLongBits(consumption) == Double.doubleToLongBits(other.consumption)
				&& Objects.equals(description, other.description) && doorCount == other.doorCount && driveType == other.driveType
				&& fuelType == other.fuelType && id == other.id && Objects.equals(imageUrl, other.imageUrl)
				&& Objects.equals(model, other.model) && peopleCount == other.peopleCount
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price)
				&& Objects.equals(rentACarId, other.rentACarId) && status == other.status
			    && vehicleType == other.vehicleType;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", brand=" + brand + ", model=" + model + ", price=" + price + ", vehicleType="
				+ vehicleType + ", rentACarId=" + rentACarId + ", driveType=" + driveType + ", fuelType=" + fuelType
				+ ", consumption=" + consumption + ", doorCount=" + doorCount + ", peopleCount=" + peopleCount
				+ ", description=" + description + ", imageUrl=" + imageUrl + ", status=" + status + "]";
	}

	
	

}
