package javaTester;

public class Topic_07_String {
    public static void main(String[] args) {
        String firstname = "Automation";
        String lastname = "FC";

        String fullname = firstname + " " + lastname;
        System.out.println(fullname);

        fullname = firstname.concat(" " + lastname);
        fullname = firstname.concat(" ").concat(lastname);
        System.out.println(fullname);

        String hotelMsg = "Welcome " + fullname + " to InterContinental Hotel";
        System.out.println(hotelMsg);

    }
}


        
