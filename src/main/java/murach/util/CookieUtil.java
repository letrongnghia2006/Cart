
package murach.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

public class CookieUtil {

    // Lấy giá trị Cookie theo tên
    public static String getCookieValue(
            Cookie[] cookies, String cookieName) {

        // Nếu không có Cookie
        if (cookies == null) {
            return "";
        }

        // Duyệt từng Cookie
        for (Cookie cookie : cookies) {

            // Kiểm tra tên Cookie
            if (cookieName.equals(cookie.getName())) {

                // Trả về giá trị Cookie
                return cookie.getValue();
            }
        }

        // Không tìm thấy Cookie
        return "";
    }

    public void deleteCookie(HttpServletResponse response, String cookieName) {
        Cookie c = new Cookie(cookieName, ""); // ghi đè cookie cũ
        c.setMaxAge(0);
        c.setPath("/");
        response.addCookie(c);
    }
}
