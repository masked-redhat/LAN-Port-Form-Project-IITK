package me.skhull.compountinterestwebapplication.controllers;

import me.skhull.compountinterestwebapplication.model.Requisitioner;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "formServlet", urlPatterns = "/lanport")
public class FormServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Serving Form HTML
        getServletContext().getRequestDispatcher("/form.html").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Request Parameters
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String description = request.getParameter("description");
        String pfNumber = request.getParameter("pfNumber");
        String unit = request.getParameter("unit");

        // New Model
        Requisitioner requisitioner = new Requisitioner(name, email, phone, description, pfNumber, unit);

        // requisitioner.printRequisitioner();

        request.setAttribute("requisitioner", requisitioner);

        // Validating Requisitioner
        boolean isRequisitionerValid = requisitioner.validateRequisitioner();

        // If not valid, Not serving PDF
        if (isRequisitionerValid) {
            getServletContext().getRequestDispatcher("/pdf").forward(request, response);
        }
        else{
            response.setContentType("text/plain");
            PrintWriter out = response.getWriter();
            out.println("Invalid Form Submission");
        }
    }
}