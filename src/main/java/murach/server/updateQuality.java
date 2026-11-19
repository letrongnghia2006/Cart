package murach.server;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import murach.DTO.Cart;

import java.io.IOException;

@WebServlet("/update")
public class updateQuality extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request , HttpServletResponse response )
            throws ServletException, IOException {
        String url = "/Cart.jsp" ;
        String action = request.getParameter("action");

        if(action == null){
            action = "join";
        }

        if(action.equals("join")){
            url = "/Cart.jsp" ;
        }else if (action.equals("update")){
            int productCode = Integer.parseInt(request.getParameter("productCode"));
            int  quantity = Integer.parseInt(request.getParameter("quantity"));

            HttpSession session = request.getSession();

            Cart cart = (Cart) session.getAttribute("cart");

            cart.updateQuantity(quantity, productCode);

            session.setAttribute("cart", cart);

            url = "/Cart.jsp" ;
        }

        getServletContext().getRequestDispatcher(url)
                .forward(request, response);
    }



}
