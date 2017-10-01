/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shirone
 */
@WebServlet(name = "FormularioComplejo", urlPatterns = {"/FormularioComplejo"})
public class FormularioComplejo extends HttpServlet {


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
            out.println("<h1>Datos no introducidos en el formulario</h1>");
            out.println("<p>No se han introducido datos en el formulario</p>");
            out.println("<p><a href=\"index.html\">volver al inicio!</a></p>");
            out.println("<div>");
            out.println("</body>");
         }
    }
    

    protected void doPost(HttpServletRequest request, HttpServletResponse response)//** Si la petición viene por post mostramos los datos **//
            throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    
    Map<String, String[]> mapa =request.getParameterMap();//** mapa que contiene los nombres y un array de String con todos sus valores asociados **//
    Set s = mapa.keySet();//** Se añaden los nombres(keys) a un set para ir recorriendo el mapa **//
    Iterator it = s.iterator();
    
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
            out.println("<link rel=\"stylesheet\" href=\"CSS/Holamundo.css\"/>");
            out.println("<title>Servlet Holamundo</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<div id=\"todo\">");
            out.println("<h1>Datos Introducidos</h1>");
            while(it.hasNext()){//** mientras que existan nombres se hara el bucle **//
                String nombre= (String) it.next(); //** guardamos cada nombre en la variable nombre para su uso **//
                String[] values=mapa.get(nombre);//** array de String para obtener la coleccion de values por cada nombre **//
                for(int i=0;i<=values.length-1;i++){//** recorremos todos los value para ser mostrados con cada nombre o key **//
                   out.println("<p>"+nombre+" -- "+values[i]+"</p>"); 
                }
            }
            
            out.println("<p><a href=\"index.html\">volver</a></p>");
            out.println("<div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
