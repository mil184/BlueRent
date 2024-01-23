package services;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.Vehicle;
import dao.VehicleDAO;

@Path("/vehicles")
public class VehicleService {

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
	}
	
	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Collection<Vehicle> getAll(){
		VehicleDAO vehicleDAO = (VehicleDAO) ctx.getAttribute("vehicleDAO");
		return vehicleDAO.findAll();
	}

	
	@GET
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Vehicle getVehicle(@PathParam ("id") int id) {
		VehicleDAO vehicleDAO = (VehicleDAO) ctx.getAttribute("vehicleDAO");
		return vehicleDAO.findVehicle(id);
	}
	
	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Vehicle register(Vehicle vehicle){
		
		VehicleDAO vehicleDAO = (VehicleDAO) ctx.getAttribute("vehicleDAO");
		return vehicleDAO.Save(vehicle);
	}
	
	@DELETE
	@Path("/remove/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void remove(@PathParam ("id") int id){
		VehicleDAO vehicleDAO = (VehicleDAO) ctx.getAttribute("vehicleDAO");
		vehicleDAO.Delete(id);
	}
	
	@POST
	@Path("/edit/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Vehicle updateVehicle(@PathParam ("id") int id, Vehicle updatedVehicle) {
		VehicleDAO vehicleDAO = (VehicleDAO) ctx.getAttribute("vehicleDAO");
		Vehicle foundVehicle = vehicleDAO.findVehicle(id);
		
		foundVehicle.setBrand(updatedVehicle.getBrand());
		foundVehicle.setModel(updatedVehicle.getModel());
		foundVehicle.setPrice(updatedVehicle.getPrice());
		foundVehicle.setVehicleType(updatedVehicle.getVehicleType());
		foundVehicle.setDriveType(updatedVehicle.getDriveType());
		foundVehicle.setFuelType(updatedVehicle.getFuelType());
		foundVehicle.setConsumption(updatedVehicle.getConsumption());
		foundVehicle.setDoorCount(updatedVehicle.getDoorCount());
		foundVehicle.setPeopleCount(updatedVehicle.getPeopleCount());
		foundVehicle.setDescription(updatedVehicle.getDescription());
		foundVehicle.setImageUrl(updatedVehicle.getImageUrl());
		foundVehicle.setStatus(updatedVehicle.getStatus());
		
		Vehicle vehicle = vehicleDAO.update(foundVehicle);
		return vehicle;
	}
	
}
