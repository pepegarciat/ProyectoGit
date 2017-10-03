/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jesus
 */
@WebServlet(name = "Registro", urlPatterns = {"/registro"})
public class Registro extends HttpServlet {

    private final String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo",
        "Junio", "Julio", "Agosto", "Septiembre", "Octubre",
        "Noviembre", "Diciembre"
    };
    private final String[] error = {"nombre", "usuario", "contraseña", "Fecha de nacimiento incorrecta"};
    private Enumeration<String> parametros;

    private final String[] pf = {"Deportes", "Lectura", "Cine", "Viajes"};

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//            doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        cabecera(request, response);

        if (request.getParameter("enviar") != null && request.getParameter("volver") == null) {
            // Comprobamos la entrada de datos
            boolean hayError = false;
            int tipoError[] = new int[4];
            for (int i = 0; i < tipoError.length; i++) {
                tipoError[i] = -1;
            }
            parametros = request.getParameterNames();
            while (parametros.hasMoreElements()) {
                String nombre = parametros.nextElement();
                if (nombre.equals("nombre") && request.getParameter(nombre).equals("")) {
                    hayError = true;
                    tipoError[0] = 0;
                } else if (nombre.equals("usuario") && request.getParameter(nombre).equals("")) {
                    hayError = true;
                    tipoError[1] = 1;
                } else if (nombre.equals("password") && request.getParameter(nombre).equals("")) {
                    hayError = true;
                    tipoError[2] = 2;
                } else if (nombre.equals("dia") && !fechaCorrecta(request.getParameter("dia"),
                        request.getParameter("mes"),
                        request.getParameter("anio"))) {
                    hayError = true;
                    tipoError[3] = 3;
                }

            }

            // Mostraremos los datos del registro o la pantalla de error
            if (!hayError) { // Última pantalla con los datos correctos
                // Escribimos los datos del registro
                out.println("<div id='contenido'>");
                out.println("<h2>Registro satisfactorio</h2>");
                out.println("<p>Nombre y Apellidos: <strong>"
                        + request.getParameter("nombre") + " "
                        + request.getParameter("apellidos") + "</strong></p>");
                out.println("<p>Sexo: <strong>" + request.getParameter("sexo") + "</strong></p>");
                out.println("<p>Fecha de nacimiento: <strong>" + request.getParameter("dia")
                        + " de " + meses[Integer.parseInt(request.getParameter("mes")) - 1]
                        + " de " + request.getParameter("anio") + "</strong></p>");
                out.println("<p>Usuario: <strong>" + request.getParameter("usuario") + "</strong></p>");
                out.println("<p>Contraseña: <strong>" + request.getParameter("password") + "</strong></p>");
                StringBuilder sb = new StringBuilder("Preferencias: ");
                sb.append("<strong>");
                parametros = request.getParameterNames();
                while (parametros.hasMoreElements()) {
                    String para = parametros.nextElement();
                    if (para.startsWith("prefe")) {
                        sb.append(request.getParameter(para));
                        sb.append(", ");
                    }
                }
                if (!sb.toString().equals("Preferencias: <strong>")) {
                    out.println("<p>"
                            + sb.replace(sb.length() - 2, sb.length(), "</strong>") + "</p>");
                }
                out.println("<br />");
                out.println("<p><a href='" + request.getContextPath() + "'>Men&uacute; inicial</a></p>");
                out.println("</div>");
            } else {
                // Mostramos la pantalla de error
                parametros = request.getParameterNames();
                out.println("<div id='contenido'>");
                out.println("<p>Errores en el registro</p>");
                // Creamos un formulario con datos ocultos para enviarlos al cliente
                out.println("<form action=\"registro\" method=\"post\" />");
                while (parametros.hasMoreElements()) {
                    String nombre = parametros.nextElement();

                    if (!nombre.equals("enviar")) {
                        out.println("<input type=\"hidden\" "
                                + "name=\"" + nombre + "\" value=\"" + request.getParameter(nombre) + "\" />");
                    }
                }
                // Agrupamos los errores en una cadena
                StringBuilder cadenaError = new StringBuilder();
                for (int i = 0; i < tipoError.length; i++) {
                    if (tipoError[i] != -1) {
                        cadenaError.append(String.valueOf(tipoError[i]));
                        cadenaError.append("-");
                    }
                }

                out.println("<input type=\"hidden\" name=\"error\" value=\"" + cadenaError.toString() + "\" />");
                out.println("<td><input type=\"submit\" name=\"volver\" value=\"Volver\" /></td>");
                out.println("</div>");
                out.println("</form>");
            }
        } else if (request.getParameter("volver") != null) {
            // Mostramos la capa donde nos informa del error cometido
            out.println("<div id='errores'>");
            out.println("<h3>Problemas con el registro</h3>");

            String errores[] = request.getParameter("error").split("-");

            for (int i = 0; i < errores.length; i++) {
                out.println("<p class=\"error\">");
                int codError = Integer.parseInt(errores[i]);

                if (codError < 3) {
                    out.println("El campo " + error[codError] + " es obligatorio</p>");
                } else {
                    out.println(error[codError] + "</p>");
                }
            }
            out.println("</div>");
            // A continuación el resto del formulario con los datos anteriormente introducidos
            cuerpo(request, response);

        }
        out.println("</body>");
        out.println("</html>");
    }

    private void cabecera(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        response.setContentType("text/html;charset=UTF-8");
        out.println();
        out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Strict//EN\"");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Registro de usuarios</title>");
        out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"" + request.getContextPath() + "/CSS/estilo.css\" media=\"screen\" />");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
    }

    private void cuerpo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        out.println("<div id='contenido'>");
        out.println("<form action=\"registro\" method=\"post\">");
        out.println("<fieldset>");
        out.println("<legend>Información personal</legend>");
        out.println("<table>");
        out.println("<tr>");

        out.println("<td><label for=\"nb\">* Nombre:</label></td>");
        out.println("<td colspan=\"2\"><input type=\"text\" name=\"nombre\" "
                + "id=\"nb\" value=\"" + request.getParameter("nombre") + "\" /></td>");
        out.println("</tr>");
        out.println("<tr>");

        out.println("<td><label for=\"ap\">Apellidos:</label></td>");
        out.println("<td colspan=\"2\"><input type=\"text\" name=\"apellidos\" "
                + "id=\"ap\" value=\"" + request.getParameter("apellidos") + "\" /></td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td><label>Sexo:</label></td>");
        if (request.getParameter("sexo") != null) {
            if (request.getParameter("sexo").equals("Hombre")) {
                out.println("<td><input type=\"radio\" name=\"sexo\" "
                        + "value=\"Hombre\" checked=\"checked\" /></td>");
                out.println("<td>Hombre</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>&nbsp;</td>");

                out.println("<td><input type=\"radio\" name=\"sexo\" "
                        + "value=\"Mujer\" /></td>");
                out.println("<td>Mujer</td>");
                out.println("</tr>");
            } else {

                out.println("<td><input type=\"radio\" name=\"sexo\" "
                        + "value=\"Hombre\" /></td>");
                out.println("<td>Hombre</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>&nbsp;</td>");

                out.println("<td><input type=\"radio\" name=\"sexo\" "
                        + "value=\"Mujer\" checked=\"checked\" /></td>");
                out.println("<td>Mujer</td>");
                out.println("</tr>");
            }
        }

        out.println("<tr>");
        out.println("<td><label>Fecha de nacimiento:</label></td>");
        out.println("<td colspan=\"2\">");
        out.println("<select name=\"dia\">");
        int dia = Integer.parseInt(request.getParameter("dia"));
        for (int i = 1; i < 32; i++) {
            String valor = (dia == i) ? "selected=\"selected\"" : "";
            out.println("<option value=\"" + i + "\"" + valor + ">" + i + "</option>");
        }
        out.println("</select>");
        out.println(" / ");
        out.println("<select name=\"mes\">");
        int mes = Integer.parseInt(request.getParameter("mes"));
        for (int i = 1; i < 13; i++) {
            String valor = (mes == i) ? "selected=\"selected\"" : "";
            out.println("<option value=\"" + i + "\"" + valor + ">" + i + "</option>");
        }
        out.println("</select>");
        out.println(" / ");
        out.println("<select name=\"anio\">");
        int anio = Integer.parseInt(request.getParameter("anio"));
        for (int i = 1950; i < 2015; i++) {
            String valor = (anio == i) ? "selected=\"selected\"" : "";
            out.println("<option value=\"" + i + "\"" + valor + ">" + i + "</option>");
        }
        out.println("</select>");
        out.println("</td>");

        out.println("</tr>");
        out.println("</table>");
        out.println("</fieldset>");
        out.println("<p>&nbsp;</p>");
        out.println("<fieldset>");
        out.println("<legend>Datos de acceso</legend>");
        out.println("<table>");
        out.println("<tr>");

        out.println("<td><label for=\"usu\">* Usuario:</label></td>");
        out.println("<td colspan=\"2\"><input type=\"text\" name=\"usuario\" "
                + "id=\"usu\" value=\"" + request.getParameter("usuario") + "\" /></td>");
        out.println("</tr>");
        out.println("<tr>");

        out.println("<td><label for=\"pass\">* Contraseña:</label></td>");
        out.println("<td colspan=\"2\"><input type=\"password\" name=\"password\" "
                + "id=\"pass\" value=\"" + request.getParameter("password") + "\" /></td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("</fieldset>");
        out.println("<p>&nbsp;</p>");
        out.println("<fieldset>");
        out.println("<legend>Información general</legend>");
        out.println("<table>");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append("<tr>");
            if (i == 0) {
                sb.append("<td><label>Preferencias:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></td>");
            } else {
                sb.append("<td>&nbsp;</td>");
            }
            String nb = "prefe" + i;
            String valor = (request.getParameter(nb) != null) ? "checked=\"checked\"" : "";
            sb.append("<td><input type=\"checkbox\" name=\"prefe").append(i).append("\" " + "value=\"").append(pf[i]).append("\" ").append(valor).append(" /></td><td>").append(pf[i]).append("</td></tr>");
        }
        out.println(sb.toString());
        out.println("</table>");
        out.println("</fieldset>");
        out.println("<p>&nbsp;</p>");
        out.println("<table class=\"pie\">");
        out.println("<tr>");
        out.println("<td><input type=\"submit\" name=\"enviar\" value=\"Enviar\" /></td>");
        out.println("<td><input type=\"button\" name=\"limpiar\" value=\"Limpiar\" onClick=\"location.href='" + request.getContextPath() + "/html/registro.html';\"/></td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("</form>");
        out.println("</div>");
    }

    private boolean fechaCorrecta(String d, String m, String a) {

        boolean correcto = true;
        int dia = Integer.parseInt(d);
        int mes = Integer.parseInt(m);
        int anio = Integer.parseInt(a);
        int diasMes = 0;
        int bisi = 0;
        int[] numDiasMes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (esBisiesto(anio) && mes == 2) {
            bisi = 1;
        }
        diasMes = numDiasMes[mes - 1] + bisi;
        if (dia > diasMes) {
            correcto = false;
        }

        return correcto;
    }

    private boolean esBisiesto(int anio) {
        boolean anioBisiesto = false;
        if ((anio % 100 != 0 || anio % 400 == 0) && anio % 4 == 0) {
            anioBisiesto = true;
        }
        return anioBisiesto;
    }

}
