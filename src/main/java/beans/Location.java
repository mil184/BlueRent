package beans;

import java.util.Objects;

public class Location {
	
	private int id;
	private double longitude;
	private double latitude;
	private String address;
	private String city;
	private String zipCode;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public Location() {}
	public Location(int id, double longitude, double latitude, String address, String city, String zipCode) {
		super();
		this.id = id;
		this.longitude = longitude;
		this.latitude = latitude;
		this.address = address;
		this.city = city;
		this.zipCode = zipCode;
	}
	@Override
	public int hashCode() {
		return Objects.hash(address, city, id, latitude, longitude, zipCode);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		return Objects.equals(address, other.address) && Objects.equals(city, other.city) && id == other.id
				&& Double.doubleToLongBits(latitude) == Double.doubleToLongBits(other.latitude)
				&& Double.doubleToLongBits(longitude) == Double.doubleToLongBits(other.longitude)
				&& Objects.equals(zipCode, other.zipCode);
	}
	@Override
	public String toString() {
		return "Location [id=" + id + ", longitude=" + longitude + ", latitude=" + latitude + ", address=" + address
				+ ", city=" + city + ", zipCode=" + zipCode + "]";
	}
	
}