
package murach.download;

import java.io.IOException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import murach.DTO.User;
import murach.data.UserIO;
import murach.util.CookieUtil;


@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

    private String deleteCookies(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (!cookie.getName().equals("JSESSIONID")) {
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
        }
        return "/index.jsp";
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {

        // Lấy action từ request
        String action = request.getParameter("action");

        if (action == null) {
            action = "viewAlbums";
        }
        // Xác định URL cần chuyển đến
        String url = "/index.jsp";

        if (action.equals("join")) {
            url = "/index.jsp";
        } else if (action.equals("checkUser")) {
            url = checkUser(request, response);
        } else if (action.equals("viewCookies")) {
            url = "/view_cookies.jsp";
        } else if (action.equals("deleteCookies")) {
            url = deleteCookies(request, response);
        }

        if ("viewAlbums".equals(action)) {
            url = "/index.jsp";
        } else if ("checkUser".equals(action)) {
            url = checkUser(request, response);
        }

        // Chuyển request đến trang JSP
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException {

        // Lấy action từ request
        String action = request.getParameter("action");

        // Xác định URL cần chuyển đến
        String url = "/index.jsp";

        if ("registerUser".equals(action)) {
            url = registerUser(request, response);
        }

        // Chuyển request đến trang JSP
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    // Phương thức kiểm tra người dùng
    private String checkUser(HttpServletRequest request,
                             HttpServletResponse response) throws IOException {

        // Lấy mã sản phẩm
        String productCode = request.getParameter("productCode");

        // Lấy hoặc tạo Session
        HttpSession session = request.getSession();

        // Lưu mã sản phẩm vào Session
        session.setAttribute("productCode", productCode);

        // Lấy User từ Session
        User user = (User) session.getAttribute("user");

        String url;

        // Nếu User chưa tồn tại trong Session
        if (user == null) {

            // Lấy danh sách Cookie
            Cookie[] cookies = request.getCookies();

            // Lấy email từ Cookie
            String emailAddress =
                    CookieUtil.getCookieValue(
                            cookies, "emailCookie");

            // Nếu không có Cookie email
            if (emailAddress == null
                    || emailAddress.equals("")) {

                // Chuyển đến trang đăng ký
                url = "/register.jsp";
            } else {

                // Lấy đường dẫn file EmailList.txt
                ServletContext sc = getServletContext();

                String path = sc.getRealPath(
                        "/WEB-INF/EmailList.txt");

                // Lấy thông tin User từ file
                user = UserIO.getUser(emailAddress, path);

                // Lưu User vào Session
                session.setAttribute("user", user);

                // Chuyển đến trang Download
                url = "/" + productCode
                        + "_download.jsp";
            }

        } else {

            // Nếu User đã tồn tại trong Session
            url = "/" + productCode
                    + "_download.jsp";
        }

        // Trả về URL
        return url;
    }

    // Phương thức đăng ký người dùng
    private String registerUser(HttpServletRequest request,
                                HttpServletResponse response) throws IOException {

        // Lấy dữ liệu người dùng từ form
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        // Tạo đối tượng User
        User user = new User();

        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        // Lấy đường dẫn file EmailList.txt
        ServletContext sc = getServletContext();

        String path = sc.getRealPath(
                "/WEB-INF/EmailList.txt");

        // Ghi thông tin User vào file
        UserIO.add(user, path);

        // Lấy hoặc tạo Session
        HttpSession session = request.getSession();

        // Lưu User vào Session
        session.setAttribute("user", user);

        // Tạo Cookie lưu email
        Cookie c = new Cookie("emailCookie", email);

        // Cookie tồn tại trong 2 năm
        c.setMaxAge(60 * 60 * 24 * 365 * 2);

        // Cho phép Cookie được sử dụng trong toàn bộ ứng dụng
        c.setPath("/");

        // Gửi Cookie về trình duyệt
        response.addCookie(c);

        // Lấy mã sản phẩm từ Session
        String productCode =
                (String) session.getAttribute("productCode");

        // Tạo URL trang Download
        String url = "/" + productCode
                + "_download.jsp";

        // Trả về URL
        return url;
    }
}
