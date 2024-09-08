package KI304.Nechai.Lab2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.SQLOutput;
import java.util.Scanner;

public class ScannerDevice  {
    private final Button stop;
    private final Button start;
    private final Button scan;
    private final Characteristics characteristics;
    private boolean started;
    private int firmwareYear;
    private PrintWriter fout;

    public ScannerDevice(Characteristics characteristics, int firmwareYear) throws FileNotFoundException {
        stop = new Button("Stop");
        start = new Button("Start");
        scan = new Button("Scan");
        this.characteristics = characteristics;
        this.firmwareYear = firmwareYear;

        fout = new PrintWriter(new File("D:/repos/repositryoLPNU/LAB02/src/KI304/Nechai/Lab2/Log.txt"));

    }

    public ScannerDevice() throws FileNotFoundException  {
        throw new RuntimeException("Scanner should have characteristics");
    }

    public void performSelfTest() {

        if (!started) {
            throw new RuntimeException("Scanner cannot perform self-test, because it is off");

        } else {System.out.println("Scanner preparing for self-test");
            fout.print("Scanner preparing for self-test \n");
            fout.flush();
        }
    }

    public int getFirmwareYear() {
        return firmwareYear;
    }

    public void setFirmwareYear(int firmwareYear) {
        this.firmwareYear = firmwareYear;
    }

   public void resetScanner(){
       if(!started){
           throw new RuntimeException("To reset scanner, you need start it first");

       }else {
           System.out.println("Scanner is reset to factory settings");
           fout.print("Scanner is reset to factory settings\n");
           fout.flush();
       }
   }

    public void startOrStopScanner(boolean started) {

       if (!started) {
            System.out.println("Scanner did not start because the 'started' flag is false.");
            this.started = started;
            return;
        }

        if (started) {
            fout.print("Scanner is started\n");
            fout.flush();
            Scan scanProccess = new Scan();
            scanProccess.StartScanner(start);
            this.started = started;
            return;
        }

    }

    public void executeScan(String purpose) {
        if (!started) {
            throw new RuntimeException("You need to start scanner first");
        } else
            checkAndUpdateFirmware();

            Scan scanProccess = new Scan();
            scanProccess.scan(scan, characteristics, purpose);
        }


    private void updateFirmware(){
        System.out.println("Updating..");
        fout.print("Updating..\n");
        fout.flush();
        setFirmwareYear(2024);
    }

    private void checkAndUpdateFirmware() {
        Scanner scan = null;
        try {
            if (getFirmwareYear() < 2024) {
                System.out.println("Firmware year is " + getFirmwareYear());
                System.out.println("Do you want to update it? (1 - yes, 2 - no) ");
                fout.print("Firmware year is " + getFirmwareYear() + "\n");
                fout.print("Do you want to update it? (1 - yes, 2 - no)\n");
                fout.flush();
                scan = new Scanner(System.in);
                int upd = scan.nextInt();

                if (upd == 1) {
                    updateFirmware();
                } else {
                    fout.print("Updating cancelled\n");
                    System.out.println("Updating cancelled");
                    fout.flush();
                }
            }
        } finally {
            if (scan != null) {
                scan.close();
            }
        }
    }

    public void getInfoAboutScanner(){
       System.out.println(characteristics.toString() + " " + getFirmwareYear());
       fout.print(characteristics.toString() + " " + getFirmwareYear() + "\n");
       fout.flush();
   }

    @Override
    public String toString() {
        fout.print("ScannerDevice: " + characteristics.toString() + ", Firmware Year: " + getFirmwareYear() + "\n");
        fout.flush();
        return "ScannerDevice: " + characteristics.toString() + ", Firmware Year: " + getFirmwareYear();
    }
}
class Characteristics{
    private String made;
    private String purpose;
    private String brandRegistrationCountry;
    private String format;

    public Characteristics(String made,String purpose,String BrandRegistrationCountry, String format ){
        this.made = made;
        this.purpose = purpose;
        this.brandRegistrationCountry = BrandRegistrationCountry;
        this.format = format;
    }

    public String getMade() {
        return made;
    }

    public void setMade(String made) {
        this.made = made;
        System.out.println("The made is now by: " + this.made);
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
        System.out.println("The purpose is now for: " + this.purpose);
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
        System.out.println("The format is now: " + this.format);
    }

    public String getBrandRegistrationCountry() {

        return brandRegistrationCountry;
    }

    public void setBrandRegistrationCountry(String brandRegistrationCountry) {
        this.brandRegistrationCountry = brandRegistrationCountry;
        System.out.println("The Brand Registration Country is now: " + this.brandRegistrationCountry);
    }

    @Override
    public String toString() {

        return this.made + ", " + this.format + ", " + this.purpose + ", " + this.brandRegistrationCountry ;
    }
}
class Button{
    private String button;

    Button(String button){
        this.button = button;
    }
    public void setButton(String button) {
        this.button = button;
    }

    public String getButton() {
        return button;
    }
}

class Scan {

    public void StartScanner(Button x){
        if(x.getButton().equals("Start")){
            System.out.println("Scanner is started!");
        }else System.out.println("Error");

    }
    public void StopScanner(Button x) {
        if (x.getButton().equals("Stop")) {
            System.out.println("Scanner is turned off");
        }else System.out.println("Error");
    }

    public void scan(Button b, Characteristics c, String purpose){
        if(b.getButton().equals("Scan")){
            if(c.getPurpose().equals(purpose)){
                System.out.println("Scan is processing");
                System.out.println("Successful!");
            }else System.out.println("Scanner not purposed for this type");
        }else System.out.println("Unknown error");



    }
}