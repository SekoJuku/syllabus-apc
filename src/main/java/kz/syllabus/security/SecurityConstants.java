package kz.syllabus.security;

public class SecurityConstants {

    public static class Roles {
        public static String DEAN = "DEAN";
        public static String COORDINATOR = "COORDINATOR";
        public static String TEACHER = "TEACHER";
        public static String STUDENT = "STUDENT";
    }

    public static class ExceptionMessages {
        public static String INVALID_CREDENTIALS = "Invalid credentials";
        public static String ACCOUNT_DISABLED = "Account disabled";
        public static String ACCOUNT_LOCKED = "Account locked";
        public static String ACCOUNT_EXPIRED = "Account expired";
        public static String CREDENTIALS_EXPIRED = "Credentials expired";
    }
}
