package KI304.Nechai.Lab3;


import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Characteristics characteristics = new Characteristics("Canon","Documents"
                ,"Japan","A4");
        CopyMachine copyMachine = new CopyMachine(characteristics,2022);

        copyMachine.isPlugIn(true);
        copyMachine.copy(-5);

    }
}
