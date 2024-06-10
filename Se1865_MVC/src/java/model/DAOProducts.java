package model;

import entity.Products;
import java.sql.*;
import java.util.Vector;

public class DAOProducts extends DBConnect {

    // Insert Product
    public int addProduct(Products product) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[products]"
                + " ([product_id], [model_year], [list_price], [product_name], [brand_name], [category_name])"
                + " VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, product.getProduct_id());
            pre.setInt(2, product.getModel_year());
            pre.setDouble(3, product.getList_price());
            pre.setString(4, product.getProduct_name());
            pre.setString(5, product.getBrand_name());
            pre.setString(6, product.getCategory_name());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    // Update Product
    public int updateProduct(Products product) {
        int n = 0;
        String sql = "UPDATE [dbo].[products]"
                + " SET [model_year] = ?, [list_price] = ?, [product_name] = ?, [brand_name] = ?, [category_name] = ?"
                + " WHERE [product_id] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, product.getModel_year());
            pre.setDouble(2, product.getList_price());
            pre.setString(3, product.getProduct_name());
            pre.setString(4, product.getBrand_name());
            pre.setString(5, product.getCategory_name());
            pre.setInt(6, product.getProduct_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    // List all Products
    public Vector<Products> getProducts(String sql) {
        Vector<Products> vector = new Vector<>();
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                int model_year = rs.getInt("model_year");
                double list_price = rs.getDouble("list_price");
                String product_name = rs.getString("product_name");
                String brand_name = rs.getString("brand_name");
                String category_name = rs.getString("category_name");
                Products product = new Products(product_id, model_year, list_price, product_name, brand_name, category_name);
                vector.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public void listAll() {
        String sql = "SELECT * FROM products";
        try {
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                int model_year = rs.getInt("model_year");
                double list_price = rs.getDouble("list_price");
                String product_name = rs.getString("product_name");
                String brand_name = rs.getString("brand_name");
                String category_name = rs.getString("category_name");
                Products product = new Products(product_id, model_year, list_price, product_name, brand_name, category_name);
                System.out.println(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DAOProducts dao = new DAOProducts();
        // Example for inserting a product
        // int n = dao.addProduct(new Products(1, 2024, 99.99, "Example Product", "Example Brand", "Example Category"));
        // if (n > 0) {
        //     System.out.println("Product inserted");
        // }

        // Example for updating a product
        // int n = dao.updateProduct(new Products(1, 2024, 109.99, "Updated Product", "Updated Brand", "Updated Category"));
        // if (n > 0) {
        //     System.out.println("Product updated");
        // }

        dao.listAll();
    }
}
