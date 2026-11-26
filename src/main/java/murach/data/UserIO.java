
package murach.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import murach.DTO.User;

public class UserIO {

    // Ghi thông tin User vào file
    public static void add(User user, String path)
            throws IOException {

        BufferedWriter out = new BufferedWriter(
                new FileWriter(path, true));

        try {
            out.write(user.getEmail() + "|"
                    + user.getFirstName() + "|"
                    + user.getLastName());

            out.newLine();

        } finally {
            out.close();
        }
    }

    // Tìm User theo email trong file
    public static User getUser(String email, String path)
            throws IOException {

        BufferedReader in = new BufferedReader(
                new FileReader(path));

        try {
            String line;

            // Đọc từng dòng trong file
            while ((line = in.readLine()) != null) {

                // Tách dữ liệu theo dấu |
                String[] data = line.split("\\|", -1);

                // Kiểm tra dòng có đủ 3 thông tin
                if (data.length == 3) {

                    // So sánh email
                    if (data[0].equalsIgnoreCase(email)) {

                        // Tạo đối tượng User
                        User user = new User();

                        user.setEmail(data[0]);
                        user.setFirstName(data[1]);
                        user.setLastName(data[2]);

                        // Trả về User tìm được
                        return user;
                    }
                }
            }

        } finally {
            in.close();
        }

        // Không tìm thấy User
        return null;
    }
}
