package services;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.RentACar;
import beans.User;
import dao.RentACarDAO;
import dao.UserDAO;

@Path("/rentACars")
public class RentACarService {
	@Context
	HttpServletRequest request;
	@Context
	ServletContext ctx;
	
	@PostConstruct
	private void init() {
		if(ctx.getAttribute("rentACarDAO") == null) 
		{
			ctx.setAttribute("rentACarDAO", new RentACarDAO(ctx.getRealPath("")));
		}	
	}
	
	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RentACar create(RentACar rentACar){
		RentACarDAO rentACarDAO = (RentACarDAO) ctx.getAttribute("rentACarDAO");
		return rentACarDAO.Save(rentACar);
	}

	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Collection<RentACar> getAll(){
		RentACarDAO rentACarDAO = (RentACarDAO) ctx.getAttribute("rentACarDAO");
		return rentACarDAO.findAll();
	}
	
	@GET
	@Path("/getRentACar/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RentACar getByName(@PathParam("name") String name) {
	    RentACarDAO rentACarDAO = (RentACarDAO) ctx.getAttribute("rentACarDAO");
	    return rentACarDAO.findByName(name);
	}

	
}
