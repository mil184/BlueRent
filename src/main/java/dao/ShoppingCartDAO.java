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
	
	private HashMap<Integer, ShoppingCart> shoppingCarts = new HashMap<Integer, ShoppingCart>();
	private ArrayList<ShoppingCart> shoppingCartList = new ArrayList<ShoppingCart>();
	
	String path = System.getProperty("user.dir") + "\\src\\main\\csv\\shoppingCarts.txt";
	
	public Collection<ShoppingCart> findAll()
	{
		return shoppingCarts.values();
	}

	public ShoppingCart findShoppingCart(int id)
	{
		return shoppingCarts.containsKey(id) ? shoppingCarts.get(id) : null;
	}
	
	public ShoppingCart Save(ShoppingCart shoppingCart)
	{
		Integer maxId = -1;
		for (Integer id : shoppingCarts.keySet()) {
			int idNum = id;
			if (idNum > maxId) {
				maxId = idNum;
			}
		}
		maxId++;
		shoppingCart.setId(maxId);
		shoppingCarts.put(shoppingCart.getId(), shoppingCart);
		writeCart(shoppingCart);
		System.out.println(maxId.toString());
		return shoppingCart;
	}
	
	public void Delete(Integer id)
	{
		shoppingCarts.remove(id);
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

	                ShoppingCart shoppingCart = new ShoppingCart();
	                shoppingCart.setId(Integer.parseInt(id));
	                shoppingCart.setVehicleIds(vehicleIdList);
	                shoppingCart.setUsername(username);
	                shoppingCart.setPrice(price);

	                shoppingCartList.add(shoppingCart);
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
        	shoppingCarts.remove(updatedCart.getId()); 
  
            ShoppingCart newCart = new ShoppingCart();
            newCart.setId(updatedCart.getId());
            newCart.setVehicleIds(updatedCart.getVehicleIds());
            newCart.setVehicles(updatedCart.getVehicles());
            newCart.setUsername(updatedCart.getUsername());
            newCart.setPrice(updatedCart.getPrice());

            shoppingCarts.put(updatedCart.getId(), newCart);

            for (ShoppingCart cart : shoppingCarts.values()) {
                System.out.println(cart);
            }

            rewriteCartsFile();

            return newCart;
        }

        return null; 
    }

    private void rewriteCartsFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (ShoppingCart shoppingCart : shoppingCarts.values()) {
                StringBuilder line = new StringBuilder();

                line.append(shoppingCart.getId()).append(";");

                ArrayList<Integer> vehicleIds = shoppingCart.getVehicleIds();
                if (vehicleIds != null) {
                    for (int i = 0; i < vehicleIds.size(); i++) {
                        line.append(vehicleIds.get(i));
                        if (i < vehicleIds.size() - 1) {
                            line.append("|");
                        }
                    }
                }
                line.append(";");

                line.append(shoppingCart.getUsername()).append(";")
                    .append(shoppingCart.getPrice()).append(";");

                writer.write(line.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}