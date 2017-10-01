package es.albarregas.servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shirone
 */
@WebServlet(name = "FormularioSimple", urlPatterns = {"/FormularioSimple"})
public class FormularioSimple extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response)//** Si la peticion viene por do get.. haremos que salte una página especial **//
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link rel=\"stylesheet\" href=\"CSS/Holamundo.css\"/>");
            out.println("<title>Servlet Holamundo</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<div id=\"todo\">");
            out.println("<h1>Datos no introducidso en el formulario</h1>");
            out.println("<p>No se han introducido datos en el formulario</p>");
            out.println("<p><a href=\"index.html\">volver al inicio!</a></p>");
            out.println("<div>");
            out.println("</body>");
         }
    }
    

    protected void doPost(HttpServletRequest request, HttpServletResponse response)//** Si la petición viene por post mostramos los datos **//
            throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    
    Enumeration<String>nombres= request.getParameterNames();//** Guardamos todos los nombres del formulario **//
    
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link rel=\"stylesheet\" href=\"CSS/Holamundo.css\"/>");
            out.println("<title>Servlet Holamundo</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<div id=\"todo\">");
            out.println("<h1>Datos Introducidos</h1>");
            while(nombres.hasMoreElements()){//** Recorremos todos los elementos de la colección que contiene los "names" **//
               String siguiente=nombres.nextElement();//** El contenido del siguiente elemento de la colección se le asigna a la variable siguiente **//
               out.println("<p>"+siguiente+"  --  "+request.getParameter(siguiente));//** mostramos el nombre de la caja y sacamos el valor directamente llamando al metodo request.getParameter(Siguiente) **//
            }
            out.println("<p><a href=\"index.html\">volver al inicio!</a></p>");
            out.println("<div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
