package beans;

import java.time.LocalTime;
import java.util.Objects;

enum Status { OPEN, CLOSED }

public class RentACar {
	private int id;
	private String name;
	// vehicles
	private LocalTime startTime;
	private LocalTime endTime;
	private Status status;
	private int locationId;
	private Location location;
	private String logoPath;
	private double grade;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
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
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
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
	public RentACar(int id, String name, LocalTime startTime, LocalTime endTime, int locationId,
			String logoPath, double grade) {
		super();
		this.id = id;
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
		this.locationId = locationId;
		this.logoPath = logoPath;
		this.grade = 0;
		
		if(LocalTime.now().compareTo(this.startTime) > 0 && LocalTime.now().compareTo(this.endTime) < 0) {
			this.status = Status.OPEN;
		}
		else {
			this.status = Status.CLOSED;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(endTime, grade, id, location, locationId, logoPath, name, startTime, status);
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
		return Objects.equals(endTime, other.endTime)
				&& Double.doubleToLongBits(grade) == Double.doubleToLongBits(other.grade) && id == other.id
				&& Objects.equals(location, other.location) && locationId == other.locationId
				&& Objects.equals(logoPath, other.logoPath) && Objects.equals(name, other.name)
				&& Objects.equals(startTime, other.startTime) && status == other.status;
	}

	@Override
	public String toString() {
		return "RentACar [id=" + id + ", name=" + name + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", status=" + status + ", locationId=" + locationId + ", location=" + location + ", logoPath="
				+ logoPath + ", grade=" + grade + "]";
	}
	
	
	
	
}