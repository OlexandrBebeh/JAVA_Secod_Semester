package kpi.view;

import kpi.view.Exeption.*;

public class Validator {
    private static final String userNameRegExp = "\\w{1,20}";

    private static final String emailRegExp = "\\w+@\\w+\\.+[a-zA-Z]{2,6}$";

    public static void isName(String userName) throws NotCurrentUsername{
        if (!userName.matches(userNameRegExp)){
            throw new NotCurrentUsername("Wrong symbols in name!");
        }
    }

    public static void isCardNumber(String userName) throws NotCurrentCardNumber{
        if (!userName.matches("((\\d{4}\\s){3}\\d{4})|(\\d{16})")){
            throw new NotCurrentCardNumber("Wrong card number.");
        }
    }

    public static void isNumber(String userName) throws NotDigitException{
        if (!userName.matches("\\d+")){
            throw new NotDigitException("This is not a digit!");
        }
    }

    public static void inRange(int dit,int left, int right) throws WrongRangeException {
        if(left>dit){
            throw new WrongRangeException("This digit is lower than " + left);
        } else if(dit>right){
            throw new WrongRangeException("This digit is more than " + right);
        }
    }
    public static void isEmail(String str) throws NotEmailException {
        if(!str.matches(emailRegExp)){
            throw new NotEmailException("This isn't email.");
        }
    }

    public static void isDouble(String str){
        if(!str.matches("(\\d)|(\\d+\\.+\\d{2})")){
            throw new NotDigitException("This digit not in correct form!");
        }
    }
}
