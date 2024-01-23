package services;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import dao.ShoppingCartDAO;

@Path("/shoppingCarts")
public class ShoppingCartService {

	@Context
	HttpServletRequest request;
	@Context
	ServletContext ctx;

	@PostConstruct
	private void init() 
	{
		if(ctx.getAttribute("shoppingCartDAO") == null) 
		{
			ctx.setAttribute("shoppingCartDAO", new ShoppingCartDAO());
		}	
	}
	
	
}