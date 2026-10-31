public class PasswordChecker {
    public static boolean hasMinLength(String pw) { return pw != null && pw.length() >= 8; }
    public static boolean hasUppercase(String pw) { return pw != null && pw.matches(".*[A-Z].*"); }
    public static boolean hasDigit(String pw) { return pw != null && pw.matches(".*[0-9].*"); }
    public static boolean hasSpecialChar(String pw) { return pw != null && pw.matches(".*[^a-zA-Z0-9].*"); }

    public static String strength(int score) {
        if (score <= 1) return "Weak";
        if (score <= 3) return "Medium";
        return "Strong";
    }

    public static String evaluatePassword(String pw) {
        int score = 0;
        if (hasMinLength(pw)) score++;
        if (hasUppercase(pw)) score++;
        if (hasDigit(pw)) score++;
        if (hasSpecialChar(pw)) score++;
        return strength(score);
    }

    public static void main(String[] args) {
        String[] tests = {"abc", "password123", "Abcd1234!", "JAVA"};
        for (String pw : tests) {
            System.out.println("Testing password: \"" + pw + "\"");
            System.out.println("Passed rules: " + countRules(pw) + "/4");
            System.out.println("Result: " + evaluatePassword(pw));
            System.out.println();
        }
    }
    private static int countRules(String pw) {
        int n = 0;
        if (hasMinLength(pw)) n++;
        if (hasUppercase(pw)) n++;
        if (hasDigit(pw)) n++;
        if (hasSpecialChar(pw)) n++;
        return n;
    }
}
