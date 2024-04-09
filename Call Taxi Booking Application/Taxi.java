import java.util.ArrayList;
import java.util.List;

public class Taxi {
    static int id = 1;
    boolean is_Booked;
    int taxi_Id;
    char current_location;
    int free_time;
    int total_Earnings;
    List<String> taxi_Trips;

    public Taxi() {

        is_Booked = false;
        current_location = 'A';
        taxi_Id = id++;
        free_time = 6;
        total_Earnings = 0;
        taxi_Trips = new ArrayList<String>();
    }

    public void setDetails(boolean booked, char spot, int time, int amount, String trips) {
        this.is_Booked = booked;
        this.current_location = spot;
        this.free_time = time;
        this.total_Earnings = amount;

    }

    public void printTaxi() {
        System.out.println("Taxi :" + this.taxi_Id + " Total Earnings :" + this.total_Earnings);
        System.out.println(
                "TaxiId      Booking I D       CustomerId      From        To      PickUpTime      DropTime        Amount");
        for (String trip : taxi_Trips) {
            System.out.println("   " + taxi_Id + "        " + trip);
        }
        System.out.println(
                "------------------------------------------------------------------------------------------------------");
    }

    public void printTaxiDetails() {
        System.out.println("Taxi " + this.taxi_Id + " Current Location " + this.current_location + " TotalEarnings "
                + this.total_Earnings + " Available Time " + this.free_time);
        System.out.println(
                "---------------------------------------------------------------------------------");
    }
}
