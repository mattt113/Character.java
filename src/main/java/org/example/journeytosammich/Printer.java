package org.example.journeytosammich;

public class Printer {
    boolean fancyPrint=false;
    long defaultTime=10;

    public void halt(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            System.out.println("Sleep failed  :(");
        }
    }

    public void print(long time,String words){
        if(fancyPrint){
            for(int i=0;i<words.length();i++) {
                System.out.print(words.charAt(i));
                halt(time);
            }
        }
        else{
            System.out.print(words);
        }
    }
    public void print(String words) {
        if(fancyPrint) {
            for (int i = 0; i < words.length(); i++) {
                System.out.print(words.charAt(i));
                halt(defaultTime);

            }
        }
        else{
            System.out.print(words);
        }
        System.out.println();
    }
    public void print(String words,boolean continuous) {
        if(fancyPrint){
            for (int i = 0; i < words.length(); i++) {
                System.out.print(words.charAt(i));
                halt(defaultTime);
            }
        }
        else{
            System.out.print(words);
        }
        if(continuous==true){
            return;
        }
        else{
            System.out.println();
        }
    }
    public void setPrintSpeed(long time){
        defaultTime=time;
    }


    public void setFancyPrint(boolean fancyPrint) {
        this.fancyPrint = fancyPrint;
    }
}
