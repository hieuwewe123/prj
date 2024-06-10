package model;

import entity.Stores;
import java.sql.*;
import java.util.Vector;

public class DAOStores extends DBConnect {

    // Insert Store
    public int addStore(Stores store) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[stores]"
                + " ([store_name], [phone], [email], [street], [city], [state], [zip_code], [store_id])"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, store.getStore_name());
            pre.setString(2, store.getPhone());
            pre.setString(3, store.getEmail());
            pre.setString(4, store.getStreet());
            pre.setString(5, store.getCity());
            pre.setString(6, store.getState());
            pre.setString(7, store.getZip_code());
            pre.setInt(8, store.getStore_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    // Update Store
    public int updateStore(Stores store) {
        int n = 0;
        String sql = "UPDATE [dbo].[stores]"
                + " SET [store_name] = ?, [phone] = ?, [email] = ?, [street] = ?, [city] = ?, [state] = ?, [zip_code] = ?"
                + " WHERE [store_id] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, store.getStore_name());
            pre.setString(2, store.getPhone());
            pre.setString(3, store.getEmail());
            pre.setString(4, store.getStreet());
            pre.setString(5, store.getCity());
            pre.setString(6, store.getState());
            pre.setString(7, store.getZip_code());
            pre.setInt(8, store.getStore_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    // List all Stores
    public Vector<Stores> getStores(String sql) {
        Vector<Stores> vector = new Vector<>();
        try {
            Statement states = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = states.executeQuery(sql);
            while (rs.next()) {
                int store_id = rs.getInt("store_id");
                String store_name = rs.getString("store_name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String street = rs.getString("street");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String zip_code = rs.getString("zip_code");
                Stores store = new Stores(store_name, phone, email, street, city, state, zip_code, store_id);
                vector.add(store);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public void listAll() {
        String sql = "SELECT * FROM stores";
        try {
            Statement states = conn.createStatement();
            ResultSet rs = states.executeQuery(sql);
            while (rs.next()) {
                int store_id = rs.getInt("store_id");
                String store_name = rs.getString("store_name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String street = rs.getString("street");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String zip_code = rs.getString("zip_code");
                Stores store = new Stores(store_name, phone, email, street, city, state, zip_code, store_id);
                System.out.println(store);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DAOStores dao = new DAOStores();
        // Example for inserting a store
        // int n = dao.addStore(new Stores("Demo Store", "123-456-7890", "demo@store.com", "123 Main St", "Anytown", "Anystate", "12345", 1));
        // if (n > 0) {
        //     System.out.println("Store inserted");
        // }

        // Example for updating a store
        // int n = dao.updateStore(new Stores("Updated Store", "987-654-3210", "update@store.com", "456 Main St", "Anytown", "Anystate", "54321", 1));
        // if (n > 0) {
        //     System.out.println("Store updated");
        // }

        dao.listAll();
    }
}
