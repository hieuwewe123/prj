package model;

import entity.Customers;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class DAOCustomers extends DBConnect {
    public Vector<Customers> getCustomersWithPreparedStatement(String sql, String name) {
    Vector<Customers> vector = new Vector<>();
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, name);
        ps.setString(2, name);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int customer_id = rs.getInt("customer_id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String street = rs.getString("street");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String zip_code = rs.getString("zip_code");
                Customers customer = new Customers(customer_id, first_name, last_name, phone, email, street, city, state, zip_code);
                vector.add(customer);
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return vector;
}
       public int deleteCustomer(int customer_id) {
        int result = 0;
        String sql = "DELETE FROM customers WHERE customer_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, customer_id);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Insert Customer
    public int addCustomer(Customers customer) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[customers]"
                + " ([customer_id],[first_name],[last_name],[phone],[email],[street],[city],[state],[zip_code])"
                + " VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, customer.getCustomer_id());
            pre.setString(2, customer.getFirst_name());
            pre.setString(3, customer.getLast_name());
            pre.setString(4, customer.getPhone());
            pre.setString(5, customer.getEmail());
            pre.setString(6, customer.getStreet());
            pre.setString(7, customer.getCity());
            pre.setString(8, customer.getState());
            pre.setString(9, customer.getZip_code());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    // Update Customer
    public int updateCustomer(Customers customer) {
        int n = 0;
        String sql = "UPDATE [dbo].[customers]"
                + " SET [first_name] = ?, [last_name] = ?, [phone] = ?, [email] = ?, [street] = ?, [city] = ?, [state] = ?, [zip_code] = ?"
                + " WHERE [customer_id] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, customer.getFirst_name());
            pre.setString(2, customer.getLast_name());
            pre.setString(3, customer.getPhone());
            pre.setString(4, customer.getEmail());
            pre.setString(5, customer.getStreet());
            pre.setString(6, customer.getCity());
            pre.setString(7, customer.getState());
            pre.setString(8, customer.getZip_code());
            pre.setInt(9, customer.getCustomer_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    // List all Customers
    public Vector<Customers> getCustomers(String sql) {
        Vector<Customers> vector = new Vector<>();
        try {
            Statement states = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = states.executeQuery(sql);
            while (rs.next()) {
                int customer_id = rs.getInt("customer_id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String street = rs.getString("street");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String zip_code = rs.getString("zip_code");
                Customers customer = new Customers(customer_id, first_name, last_name, phone, email, street, city, state, zip_code);
                vector.add(customer);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public void listAll() {
        String sql = "SELECT * FROM customers";
        try {
            Statement states = conn.createStatement();
            ResultSet rs = states.executeQuery(sql);
            while (rs.next()) {
                int customer_id = rs.getInt("customer_id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String street = rs.getString("street");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String zip_code = rs.getString("zip_code");
                Customers customer = new Customers(customer_id, first_name, last_name, phone, email, street, city, state, zip_code);
                System.out.println(customer);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DAOCustomers dao = new DAOCustomers();
//         int n = dao.addCustomer(new Customers(1, "John", "Doe", "1234567890", "john.doe@example.com", "123 Main St", "Anytown", "Anystate", "12345"));
//         if (n > 0) {
//             System.out.println("Customer inserted");
//         }
        
        // int n = dao.updateCustomer(new Customers(1, "Jane", "Doe", "0987654321", "jane.doe@example.com", "456 Main St", "Othertown", "Otherstate", "54321"));
        // if (n > 0) {
        //     System.out.println("Customer updated");
        // }
         // Assuming customer_id 1 exists and you want to delete it
//    int customer_id = 1;
//    int result = dao.deleteCustomer(customer_id);
//    
//    if (result > 0) {
//        System.out.println("Customer deleted successfully.");
//    } else {
//        System.out.println("Failed to delete customer.");
//    }
        dao.listAll();
    }
}
