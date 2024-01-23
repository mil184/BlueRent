package services;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import beans.User;
import dao.UserDAO;


@Path("/users")
public class UserService {
	
	@Context
	HttpServletRequest request;
	@Context
	ServletContext ctx;

	@PostConstruct
	private void init() 
	{

		if(ctx.getAttribute("userDAO") == null) 
		{
			ctx.setAttribute("userDAO", new UserDAO());
		}	


	}
	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User register(User user){
		
		UserDAO userDAO = (UserDAO) ctx.getAttribute("userDAO");
		return userDAO.Save(user);
	}

	@GET
	@Path("/getRegistered")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Collection<User> getRegisteredUsers(){
		
		UserDAO userDAO = (UserDAO) ctx.getAttribute("userDAO");
		return userDAO.findAll();
	}
	
	@GET
	@Path("/getRegisteredUsernames")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Collection<String> getRegistersUsernames(){
		
		UserDAO userDAO = (UserDAO) ctx.getAttribute("userDAO");
		return userDAO.findAllUsernames();
	}
	
	@GET
	@Path("/getUser/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam ("username") String username) {
		UserDAO userDAO = (UserDAO) ctx.getAttribute("userDAO");
		return userDAO.findUser(username);
	}
	
	@POST
	@Path("/edit/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User updateUser(@PathParam ("username") String username, User updatedUser) {
		UserDAO userDAO = (UserDAO) ctx.getAttribute("userDAO");
		User foundUser = userDAO.findUser(username);
		
		foundUser.setFirstName(updatedUser.getFirstName());
		foundUser.setLastName(updatedUser.getLastName());
		foundUser.setDateOfBirth(updatedUser.getDateOfBirth());
		foundUser.setGender(updatedUser.getGender());
		foundUser.setPassword(updatedUser.getPassword());
		foundUser.setType(updatedUser.getType());

		
		User user = userDAO.update(foundUser);
		return user;
	}
	
	
	@DELETE
	@Path("/remove/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void remove(@PathParam ("username") String username){
		UserDAO userDAO = (UserDAO) ctx.getAttribute("userDAO");
		userDAO.Delete(username);
	}
	
}
