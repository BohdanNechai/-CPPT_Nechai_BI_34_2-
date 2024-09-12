package KI304.Nechai.Lab3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.SQLOutput;
import java.util.Scanner;

public abstract class ScannerDevice  {
    //Поля на основі створенних класів
    private String name;
    private final Button stop;
    private final Button start;
    private final Button scan;
    private final Characteristics characteristics;
    //Власні поля пристрою
    private boolean started;
    private int firmwareYear;
    private PrintWriter fout;

    // Геттер і Сеттер для String name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Конструктор, який приймає об`єкт типу Characteristics і рік прошивки
    public ScannerDevice(Characteristics characteristics, int firmwareYear) throws FileNotFoundException {
        name = "Scanner";
        stop = new Button("Stop");
        start = new Button("Start");
        scan = new Button("Scan");
        this.characteristics = characteristics;
        this.firmwareYear = firmwareYear;

        fout = new PrintWriter(new File("D:/repos/repositryoLPNU/LAB02/src/KI304/Nechai/Lab2/Log.txt"));

    }

    public ScannerDevice() throws FileNotFoundException  {
        throw new RuntimeException(getName() + " should have characteristics");
    }

    //Методи Setter`и для зміни характеристик пристрою
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

    // Метод для самотестування
    public void performSelfTest() {

        if (!started) {
            throw new RuntimeException(getName() + " cannot perform self-test, because it is off");

        } else {System.out.println(getName() + " preparing for self-test");
            fout.print(getName() + " preparing for self-test \n");
            fout.flush();
        }
    }

    public int getFirmwareYear() {
        return firmwareYear;
    }

    public void setFirmwareYear(int firmwareYear) {
        this.firmwareYear = firmwareYear;
    }

    // Метод для скидання пристрою до заводських налаштувань
    public void resetScanner(){
        if(!started){
            throw new RuntimeException("To reset " + getName() + " scanner, you need start it first");

        }else {
            System.out.println(getName() +" is reset to factory settings");
            fout.print(getName() + "  is reset to factory settings\n");
            fout.flush();
        }
    }

    // Старт або зупинка сканеру True - старт, False -stop
    public void startOrStopScanner(boolean started) {

        if (!started) {
            Scan scanProcces = new Scan();
            fout.print(getName() +"  is turned off\n");
            fout.flush();
            scanProcces.StopScanner(stop);
            this.started = started;
            return;
        }

        if (started) {
            fout.print(getName() +"  is started\n");
            fout.flush();
            Scan scanProccess = new Scan();
            scanProccess.StartScanner(start);
            this.started = started;
            return;
        }

    }

    // Власне сам процес сканування
    public void executeScan(String purpose,String format) {
        if (!started) {
            throw new RuntimeException("You need to start " + getName() + "  first");
        } else {
            checkAndUpdateFirmware();

            fout.print("Scan is processing\n");
            fout.print("Successfull!\n");

            Scan scanProccess = new Scan();
            scanProccess.scan(scan, characteristics, purpose,format);
        }

    }
    // Процес оновлення прошивки
    private void updateFirmware(){
        System.out.println("Updating..");
        fout.print("Updating..\n");
        fout.flush();
        setFirmwareYear(2024);
    }

    // Перевірка на рік прошивкиі запит користувавча на згоду оновлення
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

    @Override // Метод toString
    public String toString() {
        fout.print(getName() + ": "  + characteristics.toString() + ", Firmware Year: " + getFirmwareYear() + "\n");
        fout.flush();
        return getName() + ": " + characteristics.toString() + ", Firmware Year: " + getFirmwareYear();
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

    // Геттери і Сеттери
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

// Клас для кнопок пристрою
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

// Клас для сканування
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