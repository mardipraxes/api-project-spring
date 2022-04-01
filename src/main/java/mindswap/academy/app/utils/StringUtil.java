package mindswap.academy.app.utils;

import java.util.regex.Pattern;

public class StringUtil {

    private static final Pattern PASSWORD_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,20}$");

    private static final Pattern EMAIL_REGEX =
            Pattern.compile("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$");

    public static final String names = "Michael\n" +
            "Christopher\n" +
            "Jessica\n" +
            "Matthew\n" +
            "Ashley\n" +
            "Jennifer\n" +
            "Joshua\n" +
            "Amanda\n" +
            "Daniel\n" +
            "David\n" +
            "James\n" +
            "Robert\n" +
            "John\n" +
            "Joseph\n" +
            "Andrew\n" +
            "Ryan\n" +
            "Brandon\n" +
            "Jason\n" +
            "Justin\n" +
            "Sarah\n" +
            "William\n" +
            "Jonathan\n" +
            "Stephanie\n" +
            "Brian\n" +
            "Nicole\n" +
            "Nicholas\n" +
            "Anthony\n" +
            "Heather\n" +
            "Eric\n" +
            "Elizabeth\n" +
            "Adam\n" +
            "Megan\n" +
            "Melissa\n" +
            "Kevin\n" +
            "Steven\n" +
            "Thomas\n" +
            "Timothy\n" +
            "Christina\n" +
            "Kyle\n" +
            "Rachel\n" +
            "Laura\n" +
            "Lauren\n" +
            "Amber";


    public static boolean isValidPassword(String password) {
        return PASSWORD_REGEX.matcher(password).matches();
    }

    public static boolean isValidEmail(String email) {
        return EMAIL_REGEX.matcher(email).matches();
    }


    public static String[] getNamesArray(){
        return names.split("\n");
    }
}
