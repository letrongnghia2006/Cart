package murach.server;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import murach.DTO.Cart;
import murach.DTO.LineProduct;
import murach.DTO.Product;

import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/addToCart")
public class addToCart extends HttpServlet {

    @Override
    protected void doPost (HttpServletRequest request , HttpServletResponse response )
        throws ServletException, IOException {

        String url = "/index.html";
        String action = request.getParameter("action");
        if(action == null){
            action = "join";
        }

        if(action.equals("join")){
            url = "/index.html";
        }else if(action.equals("add")){

            int productCode =  Integer.parseInt(request.getParameter("productCode"));
            String description = request.getParameter("description");
            Double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            HttpSession session = request.getSession();

            Product product = new Product(productCode, description, price);

            LineProduct lineProduct = new LineProduct(product, quantity);

            Cart cart = (Cart)session.getAttribute("cart");
            if (cart == null){
                cart = new Cart();
            }
            cart.addProduct(lineProduct);
            session.setAttribute("cart", cart);

            url = "/Cart.jsp";
        }

        getServletContext().getRequestDispatcher(url)
                        .forward(request, response);

        }
}
