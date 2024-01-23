package services;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.Comment;
import beans.RentACar;
import dao.CommentDAO;
import dao.RentACarDAO;

@Path("/comments")
public class CommentService {
	@Context
	HttpServletRequest request;
	@Context
	ServletContext ctx;
	
	@PostConstruct
	private void init() {
		if(ctx.getAttribute("commentDAO") == null) 
		{
			ctx.setAttribute("commentDAO", new CommentDAO(ctx.getRealPath("")));
		}	
	}
	
	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Comment create(Comment comment){
		CommentDAO commentDAO = (CommentDAO) ctx.getAttribute("commentDAO");
		return commentDAO.Save(comment);
	}
}
