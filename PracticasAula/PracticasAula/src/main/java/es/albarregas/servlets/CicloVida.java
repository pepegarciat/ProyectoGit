/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shirone
 */
@WebServlet(name = "CicloVida", urlPatterns = {"/CicloVida"})
public class CicloVida extends HttpServlet {
   
    public CicloVida(){//** Constructor que muestra un mensaje en concreto **//
        super();
        System.out.println("Se crea el servlet");
    }
    
    
   public void init(ServletConfig config){//** Se llama cada vez que se inicia por primera vez el servlet y recibe una variable de la clase ServletConfig **//
       System.out.println("Se ha inicializado por primera vez al server /"+config.getServletName());//** con este método sacamos el nombre del servlet **//
   }
   
   public void service(HttpServletRequest request, HttpServletResponse response){//** Se llama siempre ya sea do get o do post y se ejecuta **//
       System.out.println("Se ha accedido al servlet "+request.getContextPath()+" a partir del método "+request.getMethod());//** devuelve si se ha accedido al servidor por get o post **//
   }
   
   
   public void destroy(){//** Al parar nuestro servidor se llama al destructor del servlet**//
       System.out.println("Se ha llamado al método destroy");
       System.out.println("Se ha destruido correctamente el Servlet");
   }

}
