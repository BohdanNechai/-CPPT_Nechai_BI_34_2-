package KI304.Nechai.Lab3;

import java.io.FileNotFoundException;

public class CopyMachine extends ScannerDevice implements Powered {
        private boolean plugIn;

    public CopyMachine(Characteristics characteristics, int firmwareYear) throws FileNotFoundException {
        super(characteristics, firmwareYear);
        setName("Copy Machine");

    }

    public void copy(int copies){
        if(plugIn){
            if(copies <= 100 && copies >=0){
                for(int i = 0;i <=copies;i++){
                    System.out.println(i + " page is copied");
                }
            }else if(copies > 100){
                System.out.println("You can copy no more than 100 pages per time");
            }else System.out.println("Something went wrong");
        }else System.out.println(getName() +" Does not plugged in");

    }


    @Override
    public void isPlugIn(boolean isPlug) {
        if(isPlug){
            plugIn = true;

            return;
        }else{
            plugIn = false;
            return;
        }

    }
}
