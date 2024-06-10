package model;

import entity.order_items;
import java.sql.*;
import java.util.Vector;

public class DAOOrder_items extends DBConnect {

    // Insert OrderItem
    public int addOrderItem(order_items orderItem) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[order_items]"
                + " ([order_id], [item_id], [product_id], [quantity], [list_price], [discount])"
                + " VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, orderItem.getOrder_id());
            pre.setInt(2, orderItem.getItem_id());
            pre.setInt(3, orderItem.getProduct_id());
            pre.setInt(4, orderItem.getQuantity());
            pre.setDouble(5, orderItem.getList_price());
            pre.setDouble(6, orderItem.getDiscount());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    // Update OrderItem
    public int updateOrderItem(order_items orderItem) {
        int n = 0;
        String sql = "UPDATE [dbo].[order_items]"
                + " SET [product_id] = ?, [quantity] = ?, [list_price] = ?, [discount] = ?"
                + " WHERE [order_id] = ? AND [item_id] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, orderItem.getProduct_id());
            pre.setInt(2, orderItem.getQuantity());
            pre.setDouble(3, orderItem.getList_price());
            pre.setDouble(4, orderItem.getDiscount());
            pre.setInt(5, orderItem.getOrder_id());
            pre.setInt(6, orderItem.getItem_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    

    // List all OrderItems
    public Vector<order_items> getOrderItems(String sql) {
        Vector<order_items> vector = new Vector<>();
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int order_id = rs.getInt("order_id");
                int item_id = rs.getInt("item_id");
                int product_id = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                double list_price = rs.getDouble("list_price");
                double discount = rs.getDouble("discount");
                order_items orderItem = new order_items(order_id, item_id, product_id, quantity, list_price, discount);
                vector.add(orderItem);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public void listAll() {
        String sql = "SELECT * FROM order_items";
        try {
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int order_id = rs.getInt("order_id");
                int item_id = rs.getInt("item_id");
                int product_id = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                double list_price = rs.getDouble("list_price");
                double discount = rs.getDouble("discount");
                order_items orderItem = new order_items(order_id, item_id, product_id, quantity, list_price, discount);
                System.out.println(orderItem);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    

    public static void main(String[] args) {
        DAOOrder_items dao = new DAOOrder_items();
        // int n = dao.addOrderItem(new order_items(1, 1, 1, 10, 100.0, 0.1));
        // if (n > 0) {
        //     System.out.println("OrderItem inserted");
        // }

        // int n = dao.updateOrderItem(new order_items(1, 1, 2, 20, 200.0, 0.2));
        // if (n > 0) {
        //     System.out.println("OrderItem updated");
        // }

        dao.listAll();
    }
}
