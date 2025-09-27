import java.util.Scanner;

interface Control {
    void turnOn();
    void turnOff();
}

abstract class Device {
    private String deviceName;
    private String location;
    
    public Device(String name, String location) {
        this.deviceName = name;
        this.location = location;
    }
    
    public abstract void showStatus();
    
    public String getDeviceName() { return deviceName; }
    public String getLocation() { return location; }
}

class Light extends Device implements Control {
    private boolean isOn;
    private int brightness;
    
    public Light(String name, String location) {
        super(name, location);
        this.isOn = false;
        this.brightness = 50;
    }
    
    @Override
    public void showStatus() {
        String status = isOn ? "ON" : "OFF";
        System.out.println(getDeviceName() + " in " + getLocation() + " is " + status);
        if (isOn) {
            System.out.println("Brightness: " + brightness + "%");
        }
    }
    
    @Override
    public void turnOn() {
        isOn = true;
        System.out.println(getDeviceName() + " turned ON");
    }
    
    @Override
    public void turnOff() {
        isOn = false;
        System.out.println(getDeviceName() + " turned OFF");
    }
    
    public void setBrightness(int level) {
        if (isOn && level >= 0 && level <= 100) {
            brightness = level;
            System.out.println("Brightness set to " + level + "%");
        } else if (!isOn) {
            System.out.println("Turn on the light first before adjusting brightness!");
        } else {
            System.out.println("Brightness must be between 0 and 100!");
        }
    }
}

public class SmartHome2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("        SMART HOME SYSTEM       \n");
        
        Device[] homeDevices = {
            new Light("Living Room Light", "Living Room"),
            new Light("Kitchen Light", "Kitchen")
        };
        
        boolean running = true;
        
        while (running) {
            System.out.println("\n========= MENU =========");
            System.out.println("1. Show Status of Devices");
            System.out.println("2. Turn On a Device");
            System.out.println("3. Turn Off a Device");
            System.out.println("4. Adjust Brightness of a Light");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    System.out.println("\nDevice Status:");
                    for (Device device : homeDevices) {
                        device.showStatus();
                    }
                    break;
                    
                case 2:
                    System.out.println("Which device to turn ON?");
                    for (int i = 0; i < homeDevices.length; i++) {
                        System.out.println((i + 1) + ". " + homeDevices[i].getDeviceName());
                    }
                    int onChoice = sc.nextInt() - 1;
                    if (onChoice >= 0 && onChoice < homeDevices.length) {
                        ((Control) homeDevices[onChoice]).turnOn();
                    }
                    break;
                    
                case 3:
                    System.out.println("Which device to turn OFF?");
                    for (int i = 0; i < homeDevices.length; i++) {
                        System.out.println((i + 1) + ". " + homeDevices[i].getDeviceName());
                    }
                    int offChoice = sc.nextInt() - 1;
                    if (offChoice >= 0 && offChoice < homeDevices.length) {
                        ((Control) homeDevices[offChoice]).turnOff();
                    }
                    break;
                    
                case 4:
                    System.out.println("Which light to adjust brightness?");
                    for (int i = 0; i < homeDevices.length; i++) {
                        if (homeDevices[i] instanceof Light) {
                            System.out.println((i + 1) + ". " + homeDevices[i].getDeviceName());
                        }
                    }
                    int lightChoice = sc.nextInt() - 1;
                    if (lightChoice >= 0 && lightChoice < homeDevices.length && homeDevices[lightChoice] instanceof Light) {
                        System.out.print("Enter brightness (0–100): ");
                        int level = sc.nextInt();
                        ((Light) homeDevices[lightChoice]).setBrightness(level);
                    }
                    break;
                    
                case 5:
                    running = false;
                    System.out.println("Exiting Smart Home System...");
                    break;
                    
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
        
        sc.close();
    }
}
