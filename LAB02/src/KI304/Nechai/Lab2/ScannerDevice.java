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

    public void setMade(String made){
        characteristics.setMade(made);
        fout.print("The made is now: " + characteristics.getMade() + "\n");
        fout.flush();
    }

    public void setBrandRegistrationCountry(String BrandRegCountry){
        characteristics.setBrandRegistrationCountry(BrandRegCountry);
        fout.print("The Brand registration country now is: " + characteristics.getBrandRegistrationCountry() + "\n");
        fout.flush();
    }

    public void setFormat(String format){
        characteristics.setFormat(format);
        fout.print("The format now is : " + characteristics.getFormat() + "\n");
        fout.flush();
    }
    public void setPurpose(String purpose){
        characteristics.setPurpose(purpose);
        fout.print("The purpose now is for: " + characteristics.getPurpose() + "\n");
        fout.flush();
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
            Scan scanProcces = new Scan();
            fout.print("Scanner is turned off\n");
            fout.flush();
            scanProcces.StopScanner(stop);
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

    public void executeScan(String purpose,String format) {
        if (!started) {
            throw new RuntimeException("You need to start scanner first");
        } else {
            checkAndUpdateFirmware();

            fout.print("Scan is processing\n");
            fout.print("Successfull!\n");

            Scan scanProccess = new Scan();
            scanProccess.scan(scan, characteristics, purpose,format);
        }

    }
    private void updateFirmware(){
        System.out.println("Updating..");
        fout.print("Updating..\n");
        fout.flush();
        setFirmwareYear(2024);
    }

    private void checkAndUpdateFirmware() {
        Scanner scanner = new Scanner(System.in);
            if (getFirmwareYear() < 2024) {
                System.out.println("Firmware year is " + getFirmwareYear());
                System.out.println("Do you want to update it? (1 - yes, 2 - no) ");
                fout.print("Firmware year is " + getFirmwareYear() + "\n");
                fout.print("Do you want to update it? (1 - yes, 2 - no)\n");
                fout.flush();
                scanner = new Scanner(System.in);
                int upd = scanner.nextInt();

                if (upd == 1) {
                    fout.print("YES\n");
                    updateFirmware();
                } else {
                    fout.print("NO\n");
                    fout.print("Updating cancelled\n");
                    System.out.println("Updating cancelled");
                    fout.flush();
                }
            }

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

    public void scan(Button b, Characteristics c, String purpose,String format){
        if(b.getButton().equals("Scan")){
            if(c.getPurpose().equals(purpose) && c.getFormat().equals(format)){
                System.out.println("Scan is processing");
                System.out.println("Successful!");

            }else System.out.println("Scanner not purposed for this type");
        }else System.out.println("Unknown error");

    }
}