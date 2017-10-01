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
            /* TODO output your page here. You may use following sample code. */
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
        
        int[] diaMes= {31,28,31,30,31,30,31,31,30,31,30,31};
        response.setContentType("text/html;charset=UTF-8");    
        String nom = request.getParameter("nombre");
        String apel = request.getParameter("apellidos");
        String sexo= request.getParameter("sexo");
        int dia= Integer.parseInt(request.getParameter("dia"));
        int mes= Integer.parseInt(request.getParameter("mes"));
        int anio= Integer.parseInt(request.getParameter("año"));
        String usuario=request.getParameter("usuario");
        String password=request.getParameter("contraseña");
        String deporte=request.getParameter("deporte");
        String lectura=request.getParameter("lectura");
        String cine=request.getParameter("cine");
        String viajes=request.getParameter("viajes");
        
        if (request.getMethod().equals("POST")){
           boolean error=false;
           boolean volver=false;
           boolean errorf=false;
           if (dia>diaMes[mes-1]){
                error=true;
                errorf=true;
                    if (mes==2 && anio%4==0){
                        error=false;
                        errorf=false;
                    }
            }
        if (nom.length() ==0){ error=true; }
        if (usuario.length()==0) { error=true; }
        if (password.length()==0) { error=true; }
        
        if (error){
               
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>\n"+
                        "<html>\n"+
                        "<body>\n"+
                        "<form>\n"+
                        "<h2>Errores en el registro</h2\n"+
                        "<input type=&quotesubmit&quote value=&quotvolver&quot>\n"+
                        "</form>\n"+
                        "</body>\n"+
                        "</html>\n"
                    );
            volver=true;
        }
        }
        else { 
            if (volver==true){
            volver=false;
            try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>\n"+
                        "<html>\n"+
                        "<body>\n"+
                        "<div>\n"+
                        "<h2>Problemas con el registro</h2\n");
            if (nom.length() ==0){ out.println("<p>El campo nombre es obligatorio</p>");}; 
            if (usuario.length()==0){out.println("<p>El campo usuario es obligatorio</p>");};
            if (password.length()==0) {out.println("<p>El campo contraseña es obligatorio</p>");};
            if (errorf==true){out.println("<p>Fecha de nacimiento es incorrecta</p>");
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
            "          <label for=\"contraseña\"> * Contraseña: </label> <input type=\"password\" name=\"contraseña\" value=\""+password+"\">\n" +
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
            else {
                 String[] meses={"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
            
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
                "      <p>Contraseña: "+password+"</p>\n" +
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
