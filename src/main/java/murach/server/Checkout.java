package murach.server;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/checkOut")
public class Checkout extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{

        String url = "/Cart.jsp";
        String action = request.getParameter("action");
        if(action == null ){
            action = "join" ;
        }

        if(action.equals("join")){
            url = "/Cart.jsp";
        }else if (action.equals("checkOut")){

            url = "/Invoice.jsp";
        }
        getServletContext().getRequestDispatcher(url)
                .forward(request,response);
    }
}
