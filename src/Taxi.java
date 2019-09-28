import java.util.ArrayList;

public class Taxi {
    int taxiNo;
    char pickupPoint;
    int availableTime;
    int earning;
    int shortestDistence;
    ArrayList<RecordBook> recordBookArrayList = new ArrayList<>();

    public Taxi(int taxiNo, char pickupPoint) {
        this.taxiNo = taxiNo;
        this.pickupPoint = pickupPoint;
    }

    public void setEarning(int amount){
        this.earning += amount;
    }

    public void setAvailableTime(int time){
        this.availableTime += time;
    }

    public void getRecord(){
        System.out.println("PickUp Point\t\tPickUp Time\t\tDrop Point\t\tEarning");
        for(int i = 0; i < recordBookArrayList.size(); i++){
            System.out.println(recordBookArrayList.get(i).pickUpPoint+"\t\t"+recordBookArrayList.get(i).pickUpTime+"\t\t"+recordBookArrayList.get(i).dropPoint+"\t\t"+recordBookArrayList.get(i).earn);
        }
    }
}
