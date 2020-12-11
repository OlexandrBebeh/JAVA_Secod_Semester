package kpi.view;

import kpi.view.Exeption.NotDigitException;
import kpi.view.Exeption.WrongRangeException;

import java.util.Scanner;

public class ViewInput {

    ViewOutput viewOutput;

    public ViewInput(ViewOutput viewOutput){
        this.viewOutput = viewOutput;
    }
    public String getName(){
        do{
            try{
                Scanner scan = new Scanner(System.in);
                String s = scan.nextLine();
                Validator.isName(s);
                return s;
            }catch (Exception e){
                viewOutput.printMessage(e.getMessage());
            }
        }while (true);

    }
    public String getCardNumber(){
        do{
            try{
                Scanner scan = new Scanner(System.in);
                String s = scan.nextLine();
                Validator.isName(s);
                return s;
            }catch (Exception e){
                viewOutput.printMessage(e.getMessage());
            }
        }while (true);

    }
    public int getNumber(){
        do{
            try{
                Scanner scan = new Scanner(System.in);
                String s = scan.nextLine();
                Validator.isNumber(s);
                return Integer.parseInt(s);
            }catch (Exception e){
                viewOutput.printMessage(e.getMessage());
            }
        }while (true);
    }
    public double getDouble(){
        do{
            try{
                Scanner scan = new Scanner(System.in);
                String s = scan.nextLine();
                Validator.isDouble(s);
                return Double.parseDouble(s);
            }catch (Exception e){
                viewOutput.printMessage(e.getMessage());
            }
        }while (true);
    }
    public int getNumberOfCommand(int left, int right){
      while(true) {
          Scanner scan = new Scanner(System.in);
          String s = scan.nextLine();
          try {
              Validator.isNumber(s);
              int dit = Integer.parseInt(s);
              if (left > right) {
                  Validator.inRange(dit, right, left);
              } else {
                  Validator.inRange(dit, left, right);
              }
              return dit;
          } catch (NotDigitException | WrongRangeException e) {
              viewOutput.printMessage(e.getMessage());
          }
      }
    }

    public String getEmail(){
        while(true) {
            try{
            Scanner scan = new Scanner(System.in);
            String s = scan.nextLine();
            Validator.isEmail(s);
            return s;
            } catch (Exception e) {
                viewOutput.printMessage(e.getMessage());
            }
        }
    }
    public String getString(String str){
        viewOutput.printMessage(str);
        while(true) {
            try{
                Scanner scan = new Scanner(System.in);
                String s = scan.nextLine();
                Validator.isName(s);
                return s;
            } catch (Exception e) {
                viewOutput.printMessage(e.getMessage());
            }
        }
    }

    public String getPassword(){

        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        return s;
    }
}
