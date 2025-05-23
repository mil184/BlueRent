package services;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.Location;
import beans.RentACar;
import dao.LocationDAO;
import dao.RentACarDAO;

@Path("/locations")
public class LocationService {
	
	@Context
	HttpServletRequest request;
	@Context
	ServletContext ctx;
	
	@PostConstruct
	private void init()
	{
		if(ctx.getAttribute("locationDAO") == null) 
		{
			ctx.setAttribute("locationDAO", new LocationDAO(ctx.getRealPath("")));
		}
	}
	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Location register(Location location){
		
		LocationDAO locationDAO = (LocationDAO) ctx.getAttribute("locationDAO");
		return locationDAO.Save(location);
	}
	
	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Collection<Location> getAll(){
		LocationDAO locationDAO = (LocationDAO) ctx.getAttribute("locationDAO");
		return locationDAO.findAll();
	}
}