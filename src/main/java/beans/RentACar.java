package beans;

import java.time.LocalTime;
import java.util.Objects;

enum Status { OPEN, CLOSED }

public class RentACar {
	private String name;
	// vehicles
	private LocalTime startTime;
	private LocalTime endTime;
	private Status status;
	private String address;
	private String city;
	private Location location;
	private String logoPath;
	private double grade;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public LocalTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
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
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getLogoPath() {
		return logoPath;
	}
	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}
	public double getGrade() {
		return grade;
	}
	public void setGrade(double grade) {
		this.grade = grade;
	}
	public RentACar() {}
	public RentACar(String name, LocalTime startTime, LocalTime endTime, String address, String city,
			String logoPath, double grade) {
		super();
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
		this.address = address;
		this.city = city;
		this.logoPath = logoPath;
		this.grade = grade;
		
		if(LocalTime.now().compareTo(this.startTime) > 0 && LocalTime.now().compareTo(this.endTime) < 0) {
			this.status = Status.OPEN;
		}
		else {
			this.status = Status.CLOSED;
		}
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(address, city, endTime, grade, location, logoPath, name, startTime, status);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RentACar other = (RentACar) obj;
		return Objects.equals(address, other.address) && Objects.equals(city, other.city)
				&& Objects.equals(endTime, other.endTime)
				&& Double.doubleToLongBits(grade) == Double.doubleToLongBits(other.grade)
				//&& Objects.equals(location, other.location) 
				&& Objects.equals(logoPath, other.logoPath)
				&& Objects.equals(name, other.name) && Objects.equals(startTime, other.startTime)
				&& status == other.status;
	}
	@Override
	public String toString() {
		return "RentACar [name=" + name + ", startTime=" + startTime + ", endTime=" + endTime + ", status=" + status
				+ ", address=" + address + ", city=" + city + ", location=" + location + ", logoPath=" + logoPath
				+ ", grade=" + grade + "]";
	}
	
	
	
}