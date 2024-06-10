/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Staffs;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOStaff;
import java.sql.ResultSet;

@WebServlet(name = "ControllerStaff", urlPatterns = {"/URLStaff"}, initParams = {
    @WebInitParam(name = "URL", value = "URLDemo"),
    @WebInitParam(name = "userName", value = "sa"),
    @WebInitParam(name = "pass", value = "123456")})
public class ControllerStaff extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOStaff dao = new DAOStaff();
//        get request from view
        String service = request.getParameter("service");
        if (service == null) {
            service = "listStaff";
        }
        try (PrintWriter out = response.getWriter()) {
            if (service.equals("insertStaff")) {
                String submit = request.getParameter("submit");
                if (submit == null) {//display insert form
                    //model
                    ResultSet rsStore = dao.getData("select * from Stores");
                    ResultSet rsManage = dao.getData("SELECT m.staff_id, m.first_name, m.last_name "
                            + " FROM dbo.staffs AS s INNER JOIN dbo.staffs AS m ON s.manager_id = m.staff_id "
                            + " GROUP BY m.staff_id, m.first_name, m.last_name  "
                            + " ORDER BY m.staff_id");
                    request.setAttribute("rsStore", rsStore);
                    request.setAttribute("rsManage", rsManage);
                    request.getRequestDispatcher("/jsp/insertStaff.jsp").forward(request, response);
                } else { // insert into database
                    int staff_id = Integer.parseInt(request.getParameter("staff_id"));
                    String first_name = request.getParameter("first_name"),
                            last_name = request.getParameter("last_name"),
                            email = request.getParameter("email"),
                            phone = request.getParameter("phone");
                    int active = Integer.parseInt(request.getParameter("active")),
                            store_id = Integer.parseInt(request.getParameter("store_id")),
                            manager_id = Integer.parseInt(request.getParameter("manager_id"));
                    Staffs staff = new Staffs(staff_id, first_name, last_name, email, phone, active, store_id, manager_id);
                    int n = dao.addStaff(staff);
                   request.getRequestDispatcher("URLStaff").forward(request, response);
                    //response.sendRedirect("URLStaff?service=listStaff");
                }
            }

            if (service.equals("listStaff")) {
                //  call Model
                String submit = request.getParameter("submit");
                Vector<Staffs> vector;
                if (submit == null) {//list all
                    vector = dao.getStaffs("select * from staffs");
                } else {//list search
                    String name = request.getParameter("first_name");
                    vector = dao.getStaffs("select * from staffs"
                            + " where first_name like '%" + name + "%'");
                }

//               set data for view
                request.setAttribute("data", vector);
                request.setAttribute("paperTitle", "Staff manage");
                request.setAttribute("tableTitle", "List of Staffs");
//select view
                RequestDispatcher dispth
                        = request.getRequestDispatcher("/jsp/ViewStaff.jsp");
                //run
                dispth.forward(request, response);

            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
