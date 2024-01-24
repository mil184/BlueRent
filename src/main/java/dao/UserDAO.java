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

import beans.User;

public class UserDAO {

	private HashMap<String, User> users = new HashMap<String, User>();
	private ArrayList<User> userList = new ArrayList<User>();
	
	String path = System.getProperty("user.dir") + "\\src\\main\\csv\\users.txt";

	
	public Collection<User> findAll()
	{
		return users.values();	
	}
	public Collection<String> findAllUsernames()
	{
		ArrayList<String> usernames = new ArrayList<String>();
		
		for(User user : users.values()) 
		{
			usernames.add(user.getUsername());
		}
		
		return usernames;
	}
	
	public Collection<String> getManagers(){
		ArrayList<String> usernames = new ArrayList<String>();
		for(User user : users.values()) 
		{
			if (user.getRole().equals("Manager")) {
				usernames.add(user.getUsername());
			}
		}
		
		return usernames;
	}
	
	public User findUser(String username) 
	{
		return users.containsKey(username) ? users.get(username) : null;
	}
	
	public User Save(User user) 
	{
		users.put(user.getUsername(), user);
		writeUser(user);
		return user;
	}
	public void Delete(String username) 
	{
		users.remove(username);
		rewriteUsersFile();
	}

	public UserDAO() {
		BufferedReader in = null;
		try {
			File file = new File(path);
			in = new BufferedReader(new FileReader(file));
			readUsers(in);
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

	private void readUsers(BufferedReader in) {
	    String line;
	    try {
	        while ((line = in.readLine()) != null) {
	            line = line.trim();
	            if (line.equals("") || line.startsWith("#"))
	                continue;

	            StringTokenizer st = new StringTokenizer(line, ";");

	                String username = st.nextToken().trim();
	                String password = st.nextToken().trim();
	                String firstName = st.nextToken().trim();
	                String lastName = st.nextToken().trim();
	                String gender = st.nextToken().trim();
	                String dateOfBirth = st.nextToken().trim();
	                String role = st.nextToken().trim();
	                double points = Double.parseDouble(st.nextToken().trim());

	                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	                LocalDate date = LocalDate.parse(dateOfBirth, formatter);

	                User user = new User(username, password, firstName, lastName, gender, date, role, points);
	                users.put(username, user);
	                userList.add(user);
	          
	        }
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}


    public void writeUser(User user) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {

                StringBuilder line = new StringBuilder();

                line.append(user.getUsername()).append(";")
                    .append(user.getPassword()).append(";")
                    .append(user.getFirstName()).append(";")
                    .append(user.getLastName()).append(";")
                    .append(user.getGender()).append(";")
                    .append(user.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))).append(";")
                    .append(user.getRole()).append(";")
                    .append(user.getPoints()).append(";");


                writer.write(line.toString());
                writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public ArrayList<User> getProductList() {
		return userList;
	}
	
	public User update(User updatedUser) {
	    User userToUpdate = this.findUser(updatedUser.getUsername());

	    if (userToUpdate != null) {
	        users.remove(updatedUser.getUsername()); 
	        
	        User newUser = new User(
	            updatedUser.getUsername(),
	            updatedUser.getPassword(),
	            updatedUser.getFirstName(),
	            updatedUser.getLastName(),
	            updatedUser.getGender(),
	            updatedUser.getDateOfBirth(),
	            updatedUser.getRole(),
	            updatedUser.getPoints()
	        );

	        users.put(updatedUser.getUsername(), newUser);
	        
	        for(User user: users.values()) {
	        	System.out.println(user);
	        }


	        rewriteUsersFile();

	        return newUser;
	    }

	    return null; 
	}

	private void rewriteUsersFile() {
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
	        for (User user : users.values()) {
	            StringBuilder line = new StringBuilder();

	            line.append(user.getUsername()).append(";")
	                .append(user.getPassword()).append(";")
	                .append(user.getFirstName()).append(";")
	                .append(user.getLastName()).append(";")
	                .append(user.getGender()).append(";")
	                .append(user.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))).append(";")
	                .append(user.getRole()).append(";")
	                .append(user.getPoints()).append(";");

	            writer.write(line.toString());
	            writer.newLine();
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}
