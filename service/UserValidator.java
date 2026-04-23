package service;

public class UserValidator {
    public static boolean isValidName(String name){
      return name != null && name.length()>= 3;
    }
    public static boolean isValidMobileNumber(String number){
        return number != null && number.matches("\\d{10}");
    }
    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }
    public static boolean isValidPIN(String pin){
        return pin != null && pin.matches("\\d{4}");
    }

    static void main() {
       // System.out.println((isValidEmail("dd@ds.d")));
    }
}
