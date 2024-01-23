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

import javax.servlet.ServletContext;

import beans.Comment;
import beans.RentACar;
import beans.User;

public class CommentDAO {
	private HashMap<Integer, Comment> comments = new HashMap<Integer, Comment>();
	private ArrayList<Comment> commentList = new ArrayList<Comment>();
	
	String path = System.getProperty("user.dir") + "\\src\\main\\csv\\comments.txt";
	
	public Collection<Comment> findAll(){
		return comments.values();
	}
	
	public Comment findById(Integer id) {
		return comments.containsKey(id) ? comments.get(id) : null;
	}
	
	public Collection<Comment> findAllByCompanyName(String name) {
	    Collection<Comment> matchingComments = new ArrayList<>();

	    for (Comment comment : findAll()) {
	        if (comment.getCompanyName().equals(name)) {
	            matchingComments.add(comment);
	        }
	    }

	    return matchingComments;
	}
	
	public double getAverageGrade(String companyName)
	{	
		double sum = 0;
		int count = 0;
		for (Comment comment : findAllByCompanyName(companyName)) {
				sum += comment.getGrade();
				count += 1;
	        }
	
		if (count == 0) {
			return 0;
		}
		
		return sum/count;	
	}

	
	public Comment Save(Comment comment) {
		Integer maxId = -1;
		for (Integer id : comments.keySet()) {
			int idNum = id;
			if (idNum > maxId) {
				maxId = idNum;
			}
		}
		maxId++;
		comment.setId(maxId);
		comments.put(comment.getId(), comment);
		writeComment(comment);
		return comment;
	}
	
	public void Delete(Integer id) {
		comments.remove(id);
		//rewriteFile();
	}
	
	public CommentDAO(String path) {
		BufferedReader in = null;
		try {
			File file = new File(this.path);
			in = new BufferedReader(new FileReader(file));
			readComments(in);
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
	
	private void readComments(BufferedReader in) {
	    String line;
	    try {
	        while ((line = in.readLine()) != null) {
	            line = line.trim();
	            if (line.equals("") || line.startsWith("#"))
	                continue;

	            StringTokenizer st = new StringTokenizer(line, ";");

	                String id = st.nextToken().trim();
	                String companyName = st.nextToken().trim();
	                String userId = st.nextToken().trim();
	                String grade = st.nextToken().trim();
	                String text = st.nextToken().trim();
	                
	                int _id = Integer.parseInt(id);
	                int _userId = Integer.parseInt(userId);
            		double _grade = Double.parseDouble(grade);

            		Comment comment = new Comment(_id, companyName, _userId, _grade, text);
            		comments.put(_id, comment);
            		commentList.add(comment);
	          
	        }
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}
	
	public void writeComment(Comment comment) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.path, true))) {

                StringBuilder line = new StringBuilder();

                // Append user data to the line
                line.append(comment.getId()).append(";")
                	.append(comment.getCompanyName()).append(";")
                	.append(comment.getUserId()).append(";")
                	.append(comment.getGrade()).append(";")
                	.append(comment.getText()).append(";");

                writer.write(line.toString());
                writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
