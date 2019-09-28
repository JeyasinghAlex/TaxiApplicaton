import java.util.ArrayList;
import java.util.Scanner;

public class TaxiBookingSystem {
    static ArrayList<Taxi> taxiArrayList = new ArrayList<>();
    static TaxiUtil taxiUtil  = new TaxiUtil();
    public static void main(String[] args) {
        System.out.println("Welcome to Call Taxi Booking");
        creatTaxi();
        homePage();
    }

    public static void creatTaxi() {
        for(int i = 0; i < 5; i++){
            taxiArrayList.add(new Taxi(i+1,'A'));
        }
    }

    public static void homePage(){
        Scanner scan = new Scanner(System.in);
        int option = 0;
        System.out.println("1) Booking");
        System.out.println("2) Record Book");
        System.out.println("3) Exit");
        System.out.println("Enter the Option");
        try{
            option = scan.nextInt();
        }catch(Exception ex){
            System.out.println("Type of Exception - "+ex);
            System.out.println("Not Valid Option");
            homePage();
        }
        switch (option)
        {
            case 1:
            {
                taxiUtil.booking(taxiArrayList);
                break;
            }
            case 2:
            {
                findRecord();
                break;
            }
            case 3:
            {
                break;
            }
        }
    }

    public static void findRecord(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Which Taxi Record You Want ..?");
        System.out.println("1) Taxi - 1");
        System.out.println("2) Taxi - 2");
        System.out.println("3) Taxi - 3");
        System.out.println("4) Taxi - 4");
        System.out.println("5) Taxi - 5");
        System.out.println("Enter your Option");
        int option = scan.nextInt();
        for(int i = 0; i < taxiArrayList.size(); i++){
            if(option == taxiArrayList.get(i).taxiNo){
                taxiArrayList.get(i).getRecord();
            }
        }
        homePage();
    }
}
