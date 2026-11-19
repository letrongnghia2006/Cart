package murach.server;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import murach.DTO.Cart;

import java.io.IOException;

@WebServlet("/remove")
public class RemoveItems extends HttpServlet {
    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String url = "/Cart.jsp" ;

        String action = request.getParameter("action");
        if(action == null) {
            action = "join";
        }

        if(action.equals("join")) {
            url = "/Cart.jsp" ;
        }else if (action.equals("remove")) {

            int productCode =  Integer.parseInt(request.getParameter("productCode"));

            HttpSession session = request.getSession();

            Cart cart = (Cart) session.getAttribute("cart");
            cart.removeProduct(productCode);

            session.setAttribute("productCode", productCode);
            url = "/Cart.jsp" ;
        }
        getServletContext().getRequestDispatcher(url)
                .forward(request, response);

    }

}
