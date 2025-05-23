package beans;

import java.time.LocalDate;
import java.util.Objects;

public class User {
	
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String gender;
	private LocalDate dateOfBirth;
	private String role;
	private String type;
	private double points;

	public User() {}
	
	public User(String username, String password, String firstName, String lastName, String gender, LocalDate dateOfBirth, String role, double points) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.role = role;
		this.points = points;
		
		if(this.points <= 2000.0) {
			this.type = "Bronze";
		} else if(this.points <= 3500.0) {
			this.type = "Silver";
		} else {
			this.type = "Gold";
		}
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public double getPoints() {
		return points;
	}

	public void setPoints(double points) {
		this.points = points;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateOfBirth, firstName, gender, lastName, password, points, role, type, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(dateOfBirth, other.dateOfBirth) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(gender, other.gender) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(password, other.password)
				&& Double.doubleToLongBits(points) == Double.doubleToLongBits(other.points)
				&& Objects.equals(role, other.role) && Objects.equals(type, other.type)
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", role=" + role + ", type=" + type
				+ ", points=" + points + "]";
	}


}
