package emp.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public final class Validation {
    private static final DateTimeFormatter BIRTH_DATE = DateTimeFormatter.ofPattern("uuuuMMdd")
            .withResolverStyle(ResolverStyle.STRICT);

    private Validation() {
    }

    public static String validateEmployee(String name, String birthDate, String address, String email, String tel, String position) {
        if (isBlank(name)) {
            return "이름을 입력하세요.";
        }
        if (overLength(name, 45) || overLength(address, 45) || overLength(email, 45)
                || overLength(tel, 45) || overLength(position, 45)) {
            return "입력값은 45자 이하여야 합니다.";
        }
        if (isBlank(birthDate) || parseBirthDate(birthDate) == null) {
            return "생년월일은 YYYYMMDD 형식의 실제 날짜로 입력하세요.";
        }
        if (isBlank(address)) {
            return "주소를 입력하세요.";
        }
        if (isBlank(email) || !email.matches("[^@\\s]+@[^@\\s]+\\.[^@\\s]+")) {
            return "이메일 형식을 확인하세요.";
        }
        if (isBlank(tel) || !tel.matches("[0-9\\-]{9,20}")) {
            return "휴대전화는 숫자와 하이픈만 사용해 입력하세요.";
        }
        if (isBlank(position)) {
            return "직위를 입력하세요.";
        }
        return null;
    }

    public static Integer parseBirthDate(String text) {
        try {
            LocalDate date = LocalDate.parse(text.trim(), BIRTH_DATE);
            if (date.getYear() < 1900 || date.isAfter(LocalDate.now())) {
                return null;
            }
            return Integer.parseInt(text.trim());
        } catch (DateTimeParseException | NumberFormatException e) {
            return null;
        }
    }

    public static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    private static boolean overLength(String value, int max) {
        return value != null && value.trim().length() > max;
    }
}
