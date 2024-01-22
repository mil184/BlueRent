package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;

import beans.ShoppingCart;

public class ShoppingCartDAO {

	private HashMap<Integer, ShoppingCart> carts = new HashMap<Integer, ShoppingCart>();
	private ArrayList<ShoppingCart> cartList = new ArrayList<ShoppingCart>();
	
	String path = System.getProperty("user.dir") + "\\src\\main\\csv\\carts.txt";
	
	public Collection<ShoppingCart> findAll()
	{
		return carts.values();
	}

	public ShoppingCart findShoppingCart(int id)
	{
		return carts.containsKey(id) ? carts.get(id) : null;
	}
	
	public ShoppingCart Save(ShoppingCart cart)
	{
		Integer maxId = -1;
		for (Integer id : carts.keySet()) {
			int idNum = id;
			if (idNum > maxId) {
				maxId = idNum;
			}
		}
		maxId++;
		cart.setId(maxId);
		carts.put(cart.getId(), cart);
		writeCart(cart);
		System.out.println(maxId.toString());
		return cart;
	}
	
	public void Delete(Integer id)
	{
		carts.remove(id);
	}
	
	public ShoppingCartDAO() {
		System.out.println(path);
		BufferedReader in = null;
		try {
			File file = new File(path);
			in = new BufferedReader(new FileReader(file));
			readCarts(in);
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
	
	private void readCarts(BufferedReader in) {
	    String line;
	    try {
	        while ((line = in.readLine()) != null) {
	            line = line.trim();
	            if (line.equals("") || line.startsWith("#"))
	                continue;

	            StringTokenizer st = new StringTokenizer(line, ";");

	            if (st.countTokens() >= 4) {
	                String id = st.nextToken().trim();
	                String vehicleIds = st.nextToken().trim();
	                String username = st.nextToken().trim();
	                String priceStr = st.nextToken().trim();
	                double price = Double.parseDouble(priceStr);

	                ArrayList<Integer> vehicleIdList = new ArrayList<>();
	                StringTokenizer idTokenizer = new StringTokenizer(vehicleIds, "|");
	                while (idTokenizer.hasMoreTokens()) {
	                    String vehicleId = idTokenizer.nextToken().trim();
	                    vehicleIdList.add(Integer.parseInt(vehicleId));
	                }

	                ShoppingCart cart = new ShoppingCart();
	                cart.setId(Integer.parseInt(id));
	                cart.setVehicleIds(vehicleIdList);
	                cart.setUsername(username);
	                cart.setPrice(price);

	                cartList.add(cart);
	            }
	        }
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}


    public void writeCart(ShoppingCart cart) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {

            StringBuilder line = new StringBuilder();

            line.append(Integer.toString(cart.getId())).append(";");

            ArrayList<Integer> vehicleIds = cart.getVehicleIds();
            if (vehicleIds != null) {
                for (int i = 0; i < vehicleIds.size(); i++) {
                    line.append(vehicleIds.get(i));
                    if (i < vehicleIds.size() - 1) {
                        line.append("|");
                    }
                }
            }
            line.append(";");

            line.append(cart.getUsername()).append(";");
            line.append(Double.toString(cart.getPrice())).append(";");

            writer.write(line.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public ShoppingCart update(ShoppingCart updatedCart) {
        ShoppingCart cartToUpdate = findShoppingCart(updatedCart.getId());

        if (cartToUpdate != null) {
            carts.remove(updatedCart.getId()); 
  
            ShoppingCart newCart = new ShoppingCart();
            newCart.setId(updatedCart.getId());
            newCart.setVehicleIds(updatedCart.getVehicleIds());
            newCart.setVehicles(updatedCart.getVehicles());
            newCart.setUsername(updatedCart.getUsername());
            newCart.setPrice(updatedCart.getPrice());

            carts.put(updatedCart.getId(), newCart);

            for (ShoppingCart cart : carts.values()) {
                System.out.println(cart);
            }

            rewriteCartsFile();

            return newCart;
        }

        return null; 
    }

    private void rewriteCartsFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (ShoppingCart cart : carts.values()) {
                StringBuilder line = new StringBuilder();

                line.append(cart.getId()).append(";");

                ArrayList<Integer> vehicleIds = cart.getVehicleIds();
                if (vehicleIds != null) {
                    for (int i = 0; i < vehicleIds.size(); i++) {
                        line.append(vehicleIds.get(i));
                        if (i < vehicleIds.size() - 1) {
                            line.append("|");
                        }
                    }
                }
                line.append(";");

                line.append(cart.getUsername()).append(";")
                    .append(cart.getPrice()).append(";");

                writer.write(line.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
