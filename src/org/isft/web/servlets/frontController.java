
package org.isft.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class frontController extends HttpServlet {

    @Override
       public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 	String param=request.getParameter("codpage");
	try{	
		despachar(request, response, param);	 
        } catch(Exception exc){
        	response.sendError(404, exc.toString());
           	throw new ServletException(exc.getMessage());
        }
    }
   
    
   

    
    public void despachar(HttpServletRequest request, HttpServletResponse response, String codPage) throws ServletException, IOException {
	String path="";  
        String error="";
        try{
            ResourceBundle bundle=ResourceBundle.getBundle("web.pathMenu");
            try{
                path=bundle.getString(codPage);
            }catch(Exception exc){
                path=bundle.getString("9505");
                error="Pagina no encontrada";
            }

            RequestDispatcher despachador = getServletContext().getRequestDispatcher(path);
            System.out.println("PATH A BUSCAR: " + path);
            request.setAttribute("error", error);
            despachador.forward(request, response);             
     	
} catch(MissingResourceException mExc){
            System.out.println("MISSING EXCEPTION:" + mExc.toString());
            throw new ServletException(mExc.getMessage());
} catch(Exception exc){
            throw new ServletException(exc.getMessage());
}
    }
}


