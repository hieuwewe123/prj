package model;

import entity.Orders;
import java.sql.*;
import java.util.Date;
import java.util.Vector;

public class DAOOrders extends DBConnect {

    // Insert Order
    public int addOrder(Orders order) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[orders]"
                + " ([order_id], [customer_id], [order_status], [store_id], [staff_id], [order_date], [required_date], [shipped_date])"
                + " VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, order.getOrder_id());
            pre.setInt(2, order.getCustomer_id());
            pre.setInt(3, order.getOrder_status());
            pre.setInt(4, order.getStore_id());
            pre.setInt(5, order.getStaff_id());
            pre.setDate(6, new java.sql.Date(order.getOrder_date().getTime()));
            pre.setDate(7, new java.sql.Date(order.getRequired_date().getTime()));
            pre.setDate(8, new java.sql.Date(order.getShipped_date().getTime()));
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    // Update Order
    public int updateOrder(Orders order) {
        int n = 0;
        String sql = "UPDATE [dbo].[orders]"
                + " SET [customer_id] = ?, [order_status] = ?, [store_id] = ?, [staff_id] = ?, [order_date] = ?, [required_date] = ?, [shipped_date] = ?"
                + " WHERE [order_id] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, order.getCustomer_id());
            pre.setInt(2, order.getOrder_status());
            pre.setInt(3, order.getStore_id());
            pre.setInt(4, order.getStaff_id());
            pre.setDate(5, new java.sql.Date(order.getOrder_date().getTime()));
            pre.setDate(6, new java.sql.Date(order.getRequired_date().getTime()));
            pre.setDate(7, new java.sql.Date(order.getShipped_date().getTime()));
            pre.setInt(8, order.getOrder_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    // List all Orders
    public Vector<Orders> getOrders(String sql) {
        Vector<Orders> vector = new Vector<>();
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int order_id = rs.getInt("order_id");
                int customer_id = rs.getInt("customer_id");
                int order_status = rs.getInt("order_status");
                int store_id = rs.getInt("store_id");
                int staff_id = rs.getInt("staff_id");
                Date order_date = rs.getDate("order_date");
                Date required_date = rs.getDate("required_date");
                Date shipped_date = rs.getDate("shipped_date");
                Orders order = new Orders(order_id, customer_id, order_status, store_id, staff_id, order_date, required_date, shipped_date);
                vector.add(order);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public void listAll() {
        String sql = "SELECT * FROM orders";
        try {
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int order_id = rs.getInt("order_id");
                int customer_id = rs.getInt("customer_id");
                int order_status = rs.getInt("order_status");
                int store_id = rs.getInt("store_id");
                int staff_id = rs.getInt("staff_id");
                Date order_date = rs.getDate("order_date");
                Date required_date = rs.getDate("required_date");
                Date shipped_date = rs.getDate("shipped_date");
                    Orders order = new Orders(order_id, customer_id, order_status, store_id, staff_id, order_date, required_date, shipped_date);
                System.out.println(order);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DAOOrders dao = new DAOOrders();
        // int n = dao.addOrder(new Orders(1, 1, 1, 1, 1, new Date(), new Date(), new Date()));
        // if (n > 0) {
        //     System.out.println("Order inserted");
        // }
        
        // int n = dao.updateOrder(new Orders(1, 2, 2, 2, 2, new Date(), new Date(), new Date()));
        // if (n > 0) {
        //     System.out.println("Order updated");
        // }
        
        dao.listAll();
    }
}
