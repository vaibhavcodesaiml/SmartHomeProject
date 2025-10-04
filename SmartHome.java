import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a smart home system where multiple appliances can be controlled.
 * Provides features to add appliances, turn them on/off, and monitor active devices.
 */
class Appliance {
    private String name;
    private boolean status; // true = ON, false = OFF

    public Appliance(String name) {
        this.name = name;
        this.status = false; // default OFF
    }

    /** Returns the appliance name */
    public String getName() {
        return name;
    }

    /** Returns true if appliance is ON */
    public boolean isOn() {
        return status;
    }

    /**
     * Sets the status of the appliance
     * @param status true = ON, false = OFF
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
}

/**
 * Main SmartHome class that manages all appliances
 */
class SmartHome {
    private ArrayList<Appliance> appliances;

    public SmartHome() {
        appliances = new ArrayList<>();
    }

    /** Adds a new appliance to the system */
    public void addAppliance(String name) {
        appliances.add(new Appliance(name));
        System.out.println("Appliance \"" + name + "\" added successfully.");
    }

    /** Displays all appliances with their current status */
    public void showAppliances() {
        System.out.println("All appliances in Smart Home:");
        for (Appliance appliance : appliances) {
            System.out.println("- " + appliance.getName() + " [" + (appliance.isOn() ? "ON" : "OFF") + "]");
        }
    }

    /** Turns a specific appliance ON or OFF */
    public void controlAppliance(String name, boolean turnOn) {
        for (Appliance appliance : appliances) {
            if (appliance.getName().equalsIgnoreCase(name)) {
                appliance.setStatus(turnOn);
                System.out.println(appliance.getName() + " is now " + (turnOn ? "ON" : "OFF"));
                return;
            }
        }
        System.out.println("Appliance \"" + name + "\" not found!");
    }

    /** Displays all currently active (ON) appliances */
    public void showActiveAppliances() {
        System.out.println("Active appliances:");
        boolean anyActive = false;
        for (Appliance appliance : appliances) {
            if (appliance.isOn()) {
                System.out.println("- " + appliance.getName());
                anyActive = true;
            }
        }
        if (!anyActive) {
            System.out.println("No appliances are currently ON.");
        }
    }
}

/**
 * Main entry point for the Smart Home system
 */
public class SmartHomeMain {
    public static void main(String[] args) {
        SmartHome smartHome = new SmartHome();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Smart Home System Menu ---");
            System.out.println("1. Add Appliance");
            System.out.println("2. Show All Appliances");
            System.out.println("3. Turn Appliance ON");
            System.out.println("4. Turn Appliance OFF");
            System.out.println("5. Show Active Appliances");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter appliance name: ");
                    String name = sc.nextLine();
                    smartHome.addAppliance(name);
                    break;
                case 2:
                    smartHome.showAppliances();
                    break;
                case 3:
                    System.out.print("Enter appliance name to turn ON: ");
                    name = sc.nextLine();
                    smartHome.controlAppliance(name, true);
                    break;
                case 4:
                    System.out.print("Enter appliance name to turn OFF: ");
                    name = sc.nextLine();
                    smartHome.controlAppliance(name, false);
                    break;
                case 5:
                    smartHome.showActiveAppliances();
                    break;
                case 6:
                    System.out.println("Exiting Smart Home System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);

        sc.close();
    }
}
