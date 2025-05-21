package data;

public interface Acceptable {
    // Định dạng kiểm tra mã khách hàng (phải bắt đầu bằng C, G, K + 4 số)
    String CUSTOMER_ID = "^[CGK]\\d{4}$";

    // Định dạng kiểm tra tên khách hàng (Từ 2-25 ký tự, không rỗng)
    String CUSTOMER_NAME = "^.{2,25}$";

    // Định dạng kiểm tra số điện thoại (10 số thuộc nhà mạng hợp lệ của Việt Nam)
    String PHONE_NUMBER = "^(03[2-9]|05[2-9]|07[0-9]|08[1-9]|09[0-9])\\d{7}$";

    // Định dạng kiểm tra email
    String EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    // Định dạng kiểm tra mã thực đơn (PW + 3 số)
    String MENU_CODE = "^PW\\d{3}$";

    // Định dạng kiểm tra số bàn (Số nguyên dương)
    String TABLE_NUMBER = "^[1-9]\\d*$";

    // Định dạng kiểm tra số nguyên
    String INTEGER_VALID = "^-?\\d+$";

    // Định dạng kiểm tra số thực (double)
    String DOUBLE_VALID = "^-?\\d+(\\.\\d+)?$";

    /**
     * Phương thức kiểm tra tính hợp lệ của dữ liệu bằng Regular Expression.
     *
     * @param data Chuỗi dữ liệu cần kiểm tra.
     * @param pattern Mẫu kiểm tra Regular Expression.
     * @return `true` nếu dữ liệu hợp lệ, `false` nếu không hợp lệ.
     */
    static boolean isValid(String data, String pattern) {
        return data != null && data.matches(pattern);
    }
}
