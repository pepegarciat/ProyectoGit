/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
/**
 *
 * @author 1 daw
 */
@WebServlet(name = "registro", urlPatterns = {"/registro"})
public class registro extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet registro</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet registro at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        /*processRequest(request, response);*/
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
        /*processRequest(request, response);*/
        int dia=0; /* inicializamos las variables que vamos a usar*/
        int mes=0;
        int anio=0;
        String nom="";
        String apel="";
       String sexo="";
       String usuario="";
       String pass="";
       String deporte="";
       String lectura="";
       String cine="";
       String viajes="";
        int[] diaMes= {31,28,31,30,31,30,31,31,30,31,30,31}; /*inicializamos un array con los dias que contienen los meses*/
        response.setContentType("text/html;charset=UTF-8");    
        if (request.getParameter("nombre")!=null){nom = request.getParameter("nombre");}  /* si los parámetros pasados son distintos de null*/
        if (request.getParameter("apellidos")!=null){apel = request.getParameter("apellidos");}/* los pasamos a las variables*/
        if (request.getParameter("sexo")!=null) {sexo= request.getParameter("sexo");}
        String dia1=request.getParameter("dia");
        String mes1=request.getParameter("mes");
        String anio1=request.getParameter("annio");
        System.out.println(dia1+" "+mes1+" "+anio1);
        dia= Integer.parseInt(dia1.trim()); /*no se por qué me da un nullpointer en este punto. Son los únicos parámetros que se pasan*/
        System.out.println(dia+"--");
        mes= Integer.parseInt(mes1.trim());
        System.out.println(mes+"--");
        anio= Integer.parseInt(anio1.trim());
        System.out.println(anio+"--");
        if (request.getParameter("usuario")!=null){ usuario=request.getParameter("usuario");}
        if (request.getParameter("pass")!=null){pass=request.getParameter("contrasena");}
        if (request.getParameter("deporte")!=null){deporte=request.getParameter("deporte");}
        if (request.getParameter("lectura")!=null){lectura=request.getParameter("lectura");}
        if (request.getParameter("cine")!=null){ cine=request.getParameter("cine");}
        if (request.getParameter("viajes")!=null){viajes=request.getParameter("viajes");}
        
        if (request.getMethod().equals("POST")){
           boolean error=false;
           boolean volver=false;
           boolean errorf=false;
           if (dia>diaMes[mes-1]){ /* comprobamos que el día que se pasa no sea superior al max. dia del mes*/
                error=true;        /*si lo es pone el error a true tanto de fecha como error general*/
                errorf=true;
                    if (mes==2 && anio%4==0 && dia==29){ /* si el mes es febrero y el año es bisiesto comprueba que pueda ser día 29*/
                        error=false;   /*cambia el estado de los errores*/
                        errorf=false;
                    }
            }
        if (nom.length() ==0){ error=true; } /* si no hemos metido los datos obligatorios pone el error general a true*/
        if (usuario.length()==0) { error=true; }
        if (pass.length()==0) { error=true; }
        
        if (error){ /* si existe error pintamos el formulario de error*/
               
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<body>\n" +
                        "	<form action=\"\\PracticasAula\\registro\" method=\"POST\">\n" +
                        "       <h2>Errores en el registro</h2>\n" +
                        "       <input type=\"submit\" value=\"volver\"/>\n" +
                        "  </form>\n" +
                        "</body>\n" +
                        "</html>"
                    );
           
        }
         volver=true; /* pone la variable volver a true para que la siguiente vez que ejecute el servlet entre por el else siguiente*/
        }
        else { 
            if (volver==true){ /* si ya ha validado el formulario saca los problemas encontrados*/
            volver=false;      /*ponemos volver a false en espera de una nueva validación y dibujamos el formulario*/
            try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>\n"+
                        "<html>\n"+
                        "<body>\n"+
                        "<div>\n"+
                        "<h2>Problemas con el registro</h2\n");
            if (nom.length() ==0){ out.println("<p>El campo nombre es obligatorio</p>");} 
            if (usuario.length()==0){out.println("<p>El campo usuario es obligatorio</p>");}
            if (pass.length()==0) {out.println("<p>El campo contraseña es obligatorio</p>");}
            if (errorf==true){out.println("<p>Fecha de nacimiento es incorrecta</p>");}
            out.println("</div>\n+<br><br>");
            out.println(
            "<div class=\"container\">\n" +
            "        <form action=\"\\PracticasAula\registro\" method=\"POST\">\n" +
            "        <fieldset style=\"border:1px solid blue\">\n" +
            "            <legend style=\"color: blue\">Datos personales</legend>\n" +
            "          <label for=\"nombre\">* Nombre: </label><input type=\"text\" name=\"nombre\" id=\"nombre\" value=\""+nom+">\n" +
            "          <br><br>\n" +
            "          <label for=\"apellidos\">Apellidos: </label><input type=\"text\" name=\"apellidos\" id=\"apellidos\" value=\""+apel+">\n" +
            "          <br><br>\n" +
            "          <span>Sexo</span>\n");
            if (sexo=="mujer"){
            out.println("            <input type=\"radio\" name=\"sexo\" value=\"hombre\" \n" +
            "            <input type=\"radio\" name=\"sexo\" value=\"mujer\"checked>Mujer\n" +
            "            <br><br>\n");}
            else {
                out.println("            <input type=\"radio\" name=\"sexo\" value=\"hombre\" checked>Hombre\n" +
            "            <input type=\"radio\" name=\"sexo\" value=\"mujer\">Mujer\n" +
            "            <br><br>\n");
                }
            out.println("\n" +
            "            <label>Fecha de Nacimiento: </label>\n" +
            "\n" +
            "            <input type=\"number\" name=\"dia\" step=\"1\" min=\"1\" max=\"31\" value=\"1\" value=\""+dia+"\" size=\"2\">/\n" +
            "            <input type=\"number\" name=\"mes\" step=\"1\" min=\"1\" max=\"12\" value=\"1\" value=\""+mes+"\"size=\"2\">/\n" +
            "            <input type=\"number\" name=\"año\" step=\"1\" min=\"1950\" max=\"2010\" value=\""+anio+"\" size=\"4\">\n" +
            "\n"
            +
            "\n" +
            "        </fieldset >\n" +
            "            <br>\n" +
            "        <fieldset style=\"border:1px solid blue\">\n" +
            "          <legend style=\"color: blue\">Datos de acceso</legend>\n" +
            "          <label for=\"usuario\">* Usuario: </label> <input type=\"text\" name=\"usuario\" id=\"usuario\" value=\""+usuario+"\">\n" +
            "          <label for=\"contraseña\"> * Contraseña: </label> <input type=\"password\" name=\"contraseña\" value=\""+pass+"\">\n" +
            "        </fieldset>\n" +
            "            <br>\n" +
            "          <fieldset style=\"border:1px solid blue\">\n" +
            "              <legend style=\"color: blue\"> Información General</legend>\n");
            if (deporte=="Deporte"){out.println("<input type=\"checkbox\" name=\"deporte\" checked value=\"Deporte\" />Deporte<br> \n");}
            else {out.println("<input type=\"checkbox\" name=\"deporte\" value=\"Deporte\" />Deporte<br>\n");}
            if (lectura=="Lectura"){out.println("<input type=\"checkbox\" name=\"lectura\" checked value=\"Lectura\" />Lectura<br> \n");}
            else {out.println("<input type=\"checkbox\" name=\"lectura\" value=\"Lectura\" />Lectura<br>\n");}
            if (cine=="Cine"){out.println("<input type=\"checkbox\" name=\"cine\" checked value=\"Cine\" />Cine<br> \n");}
            else {out.println("<input type=\"checkbox\" name=\"cine\" value=\"Cine\" />Cine<br>\n");}
            if (viajes=="Viajes"){out.println("<input type=\"checkbox\" name=\"viajes\" checked value=\"Viajes\" />Viajes<br> \n");}
            else {out.println("<input type=\"checkbox\" name=\"viajes\" value=\"Viajes\" />Viajes<br>\n");}
            out.println("        </fieldset>\n" +
            "        <br>\n" +
            "        <input type=\"submit\" value=\"Enviar\">\n" +
            "        <input type=\"reset\" value=\"Limpiar\">\n" +
            "      </form>\n" +
            "    ");
            }
            }
            else { /* si no ha habido errores saca  el registro satisfactorio*/
                 String[] meses={"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>\n" +
                "<html>\n" +
                "  <head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <title>registro satisfactorio</title>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <div>\n" +
                "      <h2>Registro satisfactorio</h2>\n" +
                "      <p>Nombre y apellidos: "+nom+" "+apel+"</p>\n" +
                "      <p>Sexo: "+sexo+"</p>\n" +
                "      <p>Fecha de nacimiento: "+dia+" de "+meses[mes-1]+" de "+anio+"</p>\n" +
                "      <p>Usuario: "+usuario+"</p>\n" +
                "      <p>Contraseña: "+pass+"</p>\n" +
                "      <p>Preferencias: "+deporte+","+lectura+","+cine+","+viajes+"</p>\n" +
                "  </body>\n" +
                "<br><br>\n"+
                "<p><a href=\"index.html\">volver al inicio!</a></p>\n"+
                "</html>");
            }
            }
            }
            
            
                      
            }
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
