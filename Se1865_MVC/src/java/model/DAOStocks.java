package model;

import entity.Stocks;
import java.sql.*;
import java.util.Vector;

public class DAOStocks extends DBConnect {

    // Insert Stock
    public int addStock(Stocks stock) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[stocks]"
                + " ([store_id], [product_id], [quantity])"
                + " VALUES (?, ?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, stock.getStore_id());
            pre.setInt(2, stock.getProduct_id());
            pre.setInt(3, stock.getQuantity());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    // Update Stock
    public int updateStock(Stocks stock) {
        int n = 0;
        String sql = "UPDATE [dbo].[stocks]"
                + " SET [quantity] = ?"
                + " WHERE [store_id] = ? AND [product_id] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, stock.getQuantity());
            pre.setInt(2, stock.getStore_id());
            pre.setInt(3, stock.getProduct_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    // List all Stocks
    public Vector<Stocks> getStocks(String sql) {
        Vector<Stocks> vector = new Vector<>();
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int store_id = rs.getInt("store_id");
                int product_id = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                Stocks stock = new Stocks(store_id, product_id, quantity);
                vector.add(stock);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public void listAll() {
        String sql = "SELECT * FROM stocks";
        try {
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int store_id = rs.getInt("store_id");
                int product_id = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                Stocks stock = new Stocks(store_id, product_id, quantity);
                System.out.println(stock);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DAOStocks dao = new DAOStocks();
        // Example for inserting a stock
        // int n = dao.addStock(new Stocks(1, 1001, 500));
        // if (n > 0) {
        //     System.out.println("Stock inserted");
        // }

        // Example for updating a stock
        // int n = dao.updateStock(new Stocks(1, 1001, 600));
        // if (n > 0) {
        //     System.out.println("Stock updated");
        // }

        dao.listAll();
    }
}
