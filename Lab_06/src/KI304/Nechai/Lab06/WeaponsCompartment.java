package KI304.Nechai.Lab06;

import java.util.ArrayList;
import java.util.Random;

public class WeaponsCompartment {
    public static void main(String[] args) {
        Compartment<? super Data> compartment = new Compartment<>();
        compartment.addItem(new ColdWeapon("Knife",200));
        compartment.addItem(new ColdWeapon("Sword",870));
        compartment.addItem(new ColdWeapon("Battle Hummer",150));
        compartment.addItem(new FireArm("M4 Carbine",3000));
        compartment.addItem(new FireArm("Glock 17",800));
        compartment.addItem(new FireArm("M1 Garand",2320));

        Data res =  compartment.findMin();
        Data randPick = compartment.pickRandom();

        System.out.println();
        System.out.println("The cheapest weapon is: " );
        res.print();
        System.out.println();
        System.out.println("The random picked weapon is: ");
        randPick.print();

        System.out.println("\nOnly fire armed weapon:");
        compartment.getOnlyFireArm();
        System.out.println("\nOnly cold weapon::");
        compartment.getOnlyColdWeapon();

    }
}
class Compartment<T extends Data>{
    private ArrayList<T> arr;
    public Compartment(){

        arr = new ArrayList<T>();

    }
        public T findMin(){

            if(!arr.isEmpty()){
                T min = arr.get(0);

                for(int i = 1;i<arr.size();i++){
                        if( arr.get(i).compareTo(min) < 0){
                            min = arr.get(i);
                    }
                } return min;

            }else return null;
        }
        public void addItem(T t){
            arr.add(t);
            System.out.println("Item added");
        }
        public void deleteItem(int i){
            arr.remove(i);
        }
        public void clearAllItems(){
            for (int i = 0; i < arr.size(); i++) {
                arr.remove(i);
            }
        }
        public T pickRandom(){
            int First = 0;
            int Last = arr.size() -1;
            Random random = new Random();
            int num = random.nextInt(First,Last);
            return arr.get(num);
        }
        public void getOnlyFireArm(){
            for (int i = 0; i < arr.size(); i++) {
                if(arr.get(i).isFireArm()){
                    arr.get(i).print();

                }
            }

        }
    public void getOnlyColdWeapon(){
        for (int i = 0; i < arr.size(); i++) {
            if(arr.get(i).isColdWeapon()){
                arr.get(i).print();

            }
        }

    }
}

class ColdWeapon implements Data, WhatKindOfWeapon{
    private String name;
    private int cost;

    ColdWeapon(String name,int cost){
        this.name = name;
        this.cost = cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public void print() {
        System.out.println("Cold weapon "+getName() + " " + getCost()+ " $");
    }


    @Override
    public int compareTo(Data p) {
        Integer s = cost;
        return s.compareTo(p.getCost());
    }

    @Override
    public boolean isFireArm() {
        return false;
    }

    @Override
    public boolean isColdWeapon() {
        return true;
    }
}
class FireArm implements Data, WhatKindOfWeapon{
    private String name;
    private int cost;

    FireArm(String name,int cost){
        this.name = name;
        this.cost = cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public int getCost() {
        return cost;
    }
    public void print() {
        System.out.println("Fire arm "+getName() + " " + getCost()+ " $");
    }

    @Override
    public int compareTo(Data p) {
        Integer s = cost;
        return s.compareTo(p.getCost());
    }

    @Override
    public boolean isFireArm() {
        return true;
    }

    @Override
    public boolean isColdWeapon() {
        return false;
    }
}






