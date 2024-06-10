/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Staffs;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
//DAO: Database Access Object

public class DAOStaff extends DBConnect {
//    insert Staff
    public void changeActive(int staffId, int newActive){
        //code here
    }
    public int removeStaff(int id){
        int n=0;
        String sql="delete from staffs where staff_id="+id;
        try {
            //check foreign key contrains
            ResultSet rs=getData("select * from orders where staff_id="+id);
            if(rs.next()){ // exist
                changeActive(id, 0);
                return n;
            }
            Statement state=conn.createStatement();
            n=state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOStaff.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    

    public int addStaff(Staffs staff) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[staffs]\n"
                + "           ([staff_id],[first_name],[last_name],[email] ,[phone]\n"
                + "           ,[active],[store_id],[manager_id])\n"
                + "     VALUES(" + staff.getStaff_id()
                + ",'" + staff.getFirst_name() + "','" + staff.getLast_name()
                + "','" + staff.getEmail() + "','" + staff.getPhone()
                + "'," + staff.getActive() + "," + staff.getStore_id()
                + "," + staff.getManager_id() + ")";
        //  System.out.println(sql);
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int insertStaff(Staffs staff) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[staffs]"
                + " ([staff_id],[first_name],[last_name],[email] ,[phone]"
                + ",[active],[store_id],[manager_id]) "
                + "    VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
//            index of ? start 1
//            pre.setDataType(index of ?,parameter);
            pre.setInt(1, staff.getStaff_id());
            pre.setString(2, staff.getFirst_name());
            pre.setString(3, staff.getLast_name());
            pre.setString(4, staff.getEmail());
            pre.setString(5, staff.getPhone());
            pre.setInt(6, staff.getActive());
            pre.setInt(7, staff.getStore_id());
            pre.setInt(8, staff.getManager_id());
            n = pre.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int updateStaff(Staffs staff) {
        int n = 0;
        String sql = "UPDATE [dbo].[staffs]\n"
                + "   SET [first_name] = ?\n"
                + "      ,[last_name] = ?\n"
                + "      ,[email] = ?\n"
                + "      ,[phone] = ?\n"
                + "      ,[active] = ?\n"
                + "      ,[store_id] = ?\n"
                + "      ,[manager_id] =?\n"
                + " WHERE [staff_id] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, staff.getFirst_name());
            pre.setString(2, staff.getLast_name());
            pre.setString(3, staff.getEmail());
            pre.setString(4, staff.getPhone());
            pre.setInt(5, staff.getActive());
            pre.setInt(6, staff.getStore_id());
            pre.setInt(7, staff.getManager_id());
             pre.setInt(8,staff.getStaff_id());
             n=pre.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public static void main(String[] args) {
        DAOStaff dao = new DAOStaff();
        //int n = dao.addStaff(new Staffs(12, "FDemo", "LDemo", "EDemo1", "PDemo", 1, 2, 7));
//        int n = dao.insertStaff(new Staffs(13, "FDemo", "LDemo", "EDemo12", "PDemo", 1, 2, 7));
//        if (n > 0) {
//            System.out.println("inserted");
//        }
    int n = dao.updateStaff(new Staffs(13, "FDemo1", "LDemo1", "EDemo12", "PDemo", 1, 2, 7));
        if (n > 0) {
            System.out.println("updated");
        }
        dao.listAll();
        //Vector<Staffs> vector=dao.getStaffs("select * from staffs");
//        String name = "la";
//        System.out.println("select * from staffs "
//                + " where first_name like '%" + name + "%'");
//        Vector<Staffs> vector = dao.getStaffs("select * from staffs "
//                + " where first_name like '%" + name + "%'");

//        for (Staffs staff : vector) {
//            System.out.println(staff);
//        }
    }

    //DBConnect dbc=new DBConnect();
    //insert, update, delete, select (view, search)
    public Vector<Staffs> getStaffs(String sql) {
        Vector<Staffs> vector = new Vector<Staffs>();
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                //dataType varName=rs.getDataType(index|fieldName);
                // int staff_id=rs.getInt(1);
                int staff_id = rs.getInt("staff_id");
                // String first_name=rs.getString("first_name");
                String first_name = rs.getString(2),
                        last_name = rs.getString(3),
                        email = rs.getString(4),
                        phone = rs.getString(5);
                int active = rs.getInt(6),
                        store_id = rs.getInt(7),
                        manager_id = rs.getInt(8);
                // Staffs staffs=new Staffs(staff_id, first_name, last_name, email, phone, active, store_id, manager_id)
                Staffs staff = new Staffs(staff_id, first_name,
                        last_name, email, phone, active,
                        store_id, manager_id);
                //System.out.println(staff);
                vector.add(staff);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public void listAll() {
        String sql = "select * from staffs";
        try {
            //Statement: execute command
            Statement state = conn.createStatement();
            // execute
//            executeQuery: Select
//            executeUpdate: insert,update,delete    
//            execute: create, drop,alter        
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                //dataType varName=rs.getDataType(index|fieldName);
                // int staff_id=rs.getInt(1);
                int staff_id = rs.getInt("staff_id");
                // String first_name=rs.getString("first_name");
                String first_name = rs.getString(2),
                        last_name = rs.getString(3),
                        email = rs.getString(4),
                        phone = rs.getString(5);
                int active = rs.getInt(6),
                        store_id = rs.getInt(7),
                        manager_id = rs.getInt(8);
                // Staffs staffs=new Staffs(staff_id, first_name, last_name, email, phone, active, store_id, manager_id)
                Staffs staff = new Staffs(staff_id, first_name,
                        last_name, email, phone, active,
                        store_id, manager_id);
                System.out.println(staff);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
