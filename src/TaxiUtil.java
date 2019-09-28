import java.util.Scanner;
import java.util.ArrayList;
public class TaxiUtil {

    public void booking(ArrayList<Taxi> taxiArrayList){
        Scanner scan = new Scanner(System.in);
        char pickUpPoint =' ';
        char dropPoint = ' ';
        int pickUpTime = 0;
        int time;
        Taxi temp;
        boolean result = false;
        int earn = 0;
        try{
            System.out.println("Enter your PickUp Point");
            pickUpPoint = scan.next().charAt(0);
            System.out.println("Enter PickUp Time");
            pickUpTime = scan.nextInt();
            System.out.println("Enter your Drop Point");
            dropPoint = scan.next().charAt(0);
        }catch (Exception ex){
            System.out.println("Type of Exception - "+ex);
            System.out.println("Invalid Input");
            booking(taxiArrayList);
        }

        for(int i = 0; i < taxiArrayList.size(); i++){
            if(pickUpPoint == taxiArrayList.get(i).pickupPoint && pickUpTime >= taxiArrayList.get(i).availableTime){
                System.out.println("..........."+taxiArrayList.get(i).availableTime);
                result = true;
                for(int j = i+1; j < taxiArrayList.size(); j++){
                    if(pickUpPoint == taxiArrayList.get(j).pickupPoint && (taxiArrayList.get(j).earning < taxiArrayList.get(i).earning) && pickUpTime >= taxiArrayList.get(i).availableTime){
                            temp = taxiArrayList.get(i);
                            taxiArrayList.set(i, taxiArrayList.get(j));
                            taxiArrayList.set(j, temp);
                    }
                }
                System.out.println("Taxi - "+taxiArrayList.get(i).taxiNo+" is allotted");
                if(pickUpPoint > dropPoint){
                    earn = (pickUpPoint - dropPoint) * 100;
                    taxiArrayList.get(i).setEarning(earn);
                    time = pickUpTime + (pickUpPoint - dropPoint);
                    taxiArrayList.get(i).setAvailableTime(time);
                }else{
                    earn = (dropPoint - pickUpPoint) * 100;
                    taxiArrayList.get(i).setEarning(earn);
                    time = pickUpTime + (dropPoint - pickUpPoint);
                    taxiArrayList.get(i).setAvailableTime(time);
                }
                taxiArrayList.get(i).recordBookArrayList.add(new RecordBook(pickUpPoint, pickUpTime, dropPoint, earn));
                taxiArrayList.get(i).pickupPoint = dropPoint;
                System.out.println("Your Total Earning is - "+taxiArrayList.get(i).earning);
                break;
            }
        }
        if(!result){
            findTaxi(taxiArrayList, pickUpPoint, dropPoint, pickUpTime);
        }
        TaxiBookingSystem.homePage();
    }

    public void findTaxi(ArrayList<Taxi> taxiArrayList, char pickUpPoint, char dropPoint, int pickUpTime){
        int earn = 0;
        int time = 0;
        boolean result = false;
        for(int i = 0; i < taxiArrayList.size(); i++){
            int shortestDistence = taxiArrayList.get(i).pickupPoint - pickUpPoint;
            if(shortestDistence > 0){
                taxiArrayList.get(i).shortestDistence = shortestDistence;
            }else{
                taxiArrayList.get(i).shortestDistence = shortestDistence * (-1);
            }
        }

        for(int i = 0; i < taxiArrayList.size(); i++){
            for(int j = i+1; j < taxiArrayList.size(); j++){
                if(taxiArrayList.get(i).shortestDistence > taxiArrayList.get(j).shortestDistence){
                    Taxi temp = taxiArrayList.get(i);
                    taxiArrayList.set(i, taxiArrayList.get(j));
                    taxiArrayList.set(j, temp);
                }
            }
        }

        for(int i = 0; i < taxiArrayList.size(); i++){
            if(pickUpTime >= taxiArrayList.get(i).availableTime){
                result = true;
                for(int j = i+1; j < taxiArrayList.size(); j++){
                    if(((taxiArrayList.get(i).shortestDistence == taxiArrayList.get(i).shortestDistence) && (taxiArrayList.get(j).earning < taxiArrayList.get(i).earning) && pickUpTime >= taxiArrayList.get(i).availableTime)){
                        Taxi temp = taxiArrayList.get(i);
                        taxiArrayList.set(i, taxiArrayList.get(j));
                        taxiArrayList.set(j, temp);
                    }
                }
                System.out.println("Taxi - "+taxiArrayList.get(i).taxiNo+" is allotted");
                if(pickUpPoint > dropPoint){
                    earn = (pickUpPoint - dropPoint) * 100;
                    taxiArrayList.get(i).setEarning(earn);
                    time = pickUpTime + (pickUpPoint - dropPoint);
                    taxiArrayList.get(i).setAvailableTime(time);
                }else{
                    earn = (dropPoint - pickUpPoint) * 100;
                    taxiArrayList.get(i).setEarning(earn);
                    time = pickUpTime + (dropPoint - pickUpPoint);
                    taxiArrayList.get(i).setAvailableTime(time);
                }
                taxiArrayList.get(i).recordBookArrayList.add(new RecordBook(pickUpPoint, pickUpTime, dropPoint, earn));
                taxiArrayList.get(i).pickupPoint = dropPoint;
                System.out.println("Your Total Earning is - "+taxiArrayList.get(i).earning);
                break;
            }
        }
        if(!result){
            System.out.println("Taxi Not Available in this time");
            System.out.println("Would you like book another time ...?");
            System.out.println("1) Yes");
            System.out.println("2) I want to exit");
            System.out.println("Enter your Option");
            Scanner scan = new Scanner(System.in);
            int option = scan.nextInt();
            switch (option)
            {
                case 1:
                {
                    TaxiBookingSystem.homePage();
                    break;
                }
                case 2:
                {
                    System.exit(0);
                    break;
                }
            }
        }
    }
}
