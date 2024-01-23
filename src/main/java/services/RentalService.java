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

import beans.Rental;
import dao.RentalDAO;
import dao.VehicleDAO;

@Path("/rentals")
public class RentalService {

	@Context
	HttpServletRequest request;
	@Context
	ServletContext ctx;

	@PostConstruct
	private void init() 
	{
		if(ctx.getAttribute("vehicleDAO") == null) 
		{
			ctx.setAttribute("vehicleDAO", new VehicleDAO());
		}	
	
		if(ctx.getAttribute("rentalDAO") == null) 
		{
			ctx.setAttribute("rentalDAO", new RentalDAO((VehicleDAO) ctx.getAttribute("vehicleDAO")));
		}	

	}
	
	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Collection<Rental> getAll(){
		RentalDAO rentalDAO = (RentalDAO) ctx.getAttribute("rentalDAO");
		return rentalDAO.findAll();
	}
	
	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Rental register(Rental rental){
		RentalDAO rentalDAO = (RentalDAO) ctx.getAttribute("rentalDAO");
		return rentalDAO.save(rental);
	}
	
	
	@POST
	@Path("/edit/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Rental updateRental(@PathParam("id") String id, Rental updatedRental) {
	    RentalDAO rentalDAO = (RentalDAO) ctx.getAttribute("rentalDAO");
	    Rental foundRental = rentalDAO.findRental(id);

	    if (foundRental != null) {
	        foundRental.setVehicleIds(updatedRental.getVehicleIds());
	        foundRental.setRentACarIds(updatedRental.getRentACarIds());
	        foundRental.setStartDate(updatedRental.getStartDate());
	        foundRental.setEndDate(updatedRental.getEndDate());
	        foundRental.setPrice(updatedRental.getPrice());
	        foundRental.setUsername(updatedRental.getUsername());
	 
	        Rental rental = rentalDAO.update(foundRental);
	        return rental;
	    }

	    return null; 
	}

}
