package KI304.Nechai.Lab2;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Characteristics characteristics = new Characteristics("Canon","Documents"
        ,"Japan","A4");
        ScannerDevice scanner = new ScannerDevice(characteristics,2020);

       scanner.startOrStopScanner(true);
       scanner.resetScanner();
       scanner.performSelfTest();
       scanner.executeScan("Documents","A4");
       scanner.startOrStopScanner(false);

       scanner.startOrStopScanner(true);
       scanner.setPurpose("Photos");
       scanner.setFormat("A3");
       scanner.executeScan("Photos","A3");
       scanner.startOrStopScanner(false);

       System.out.println(scanner);



    }
}
