import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static List<Taxi> createTaxi(int n) {
        List<Taxi> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(new Taxi());
        }
        return list;
    }

    public static List<Taxi> GetFreeTaxis(List<Taxi> availableTaxis, char pickup, int pick_time) {
        List<Taxi> list = new ArrayList<>();
        for (Taxi t : availableTaxis) {
            if (t.free_time <= pick_time
                    && (Math.abs((t.current_location - '0') - (pickup - '0')) <= (pick_time - t.free_time))) {
                list.add(t);
            }
        }
        return list;
    }

    public static void BookTaxi(int id, List<Taxi> freeTaxis, char pickup, int picktime, char drop) {
        int minimum_Distance = Math.abs((freeTaxis.get(0).current_location - '0') - (pickup - '0')) * 15;
        Taxi booked_Taxi = null;
        int earnings = 0;
        int time = 0;
        int distance = 0;
        String taxi = "";
        for (Taxi t : freeTaxis) {
            if ((Math.abs((t.current_location - '0') - (pickup - '0'))) * 15 < minimum_Distance) {
                booked_Taxi = t;
                break;
            }

        }
        if (booked_Taxi == null) {
            booked_Taxi = freeTaxis.get(0);
        }
        distance = Math.abs((drop - '0') - (pickup - '0')) * 15;
        earnings = 100 + ((distance - 5) * 10);
        time = picktime + (distance / 15);
        taxi = "    " + id + "                " + id + "             " + pickup + "           " + drop + "          "
                + picktime
                + "              " + time + "             " + earnings;
        booked_Taxi.setDetails(true, drop, time, earnings + booked_Taxi.total_Earnings, taxi);
        System.out.println(
                booked_Taxi.taxi_Id + " is booked from " + pickup + " to " + drop + " with the pick time " + picktime);

    }

    public static void main(String[] args) {
        List<Taxi> availableTaxis = createTaxi(4);
        Scanner sc = new Scanner(System.in);
        int id = 1;
        boolean loop = true;
        while (loop) {
            System.out.println("0 -> Book Taxi");
            System.out.println("1 -> Print Details");
            System.out.println("2 -> Exit");
            System.out.println("Enter the Choice");
            int choice = sc.nextInt();
            switch (choice) {
                case 0: {
                    System.out.println("The valid pickUp and Drop Points are A, B, C, D, E, F");
                    System.out.println("Enter the PickUp Point");
                    char pickup = sc.next().charAt(0);
                    System.out.println("Enter the Drop Point");
                    char drop = sc.next().charAt(0);
                    System.out.println("Enter the PickUp Time");
                    int picktime = sc.nextInt();

                    if (pickup < 'A' || pickup > 'F') {
                        System.out.println("INVALID PICKUP POINT");
                        break;
                    }
                    if (drop < 'A' || drop > 'F') {
                        System.out.println("INVALID DROP POINT");
                        break;
                    }
                    List<Taxi> freeTaxis = GetFreeTaxis(availableTaxis, pickup, picktime);
                    if (freeTaxis.size() == 0) {
                        System.out.println("No taxi can be allocated due to unavailability of free taxis");
                        break;
                    }
                    Collections.sort(freeTaxis, (a, b) -> a.total_Earnings - b.total_Earnings);
                    BookTaxi(id, freeTaxis, pickup, picktime, drop);
                    break;
                }
                case 1: {
                    for (Taxi t : availableTaxis) {
                        t.printTaxiDetails();

                    }
                    for (Taxi t : availableTaxis) {
                        t.printTaxi();
                    }
                    break;
                }
                case 2: {
                    loop = false;
                    break;
                }
                default: {
                    System.out.println("You Entered the wrong Number");
                    System.exit(0);
                    break;
                }
            }
        }
    }

}
