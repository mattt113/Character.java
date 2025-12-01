package org.example.journeytosammich;

public class EvilCommandStore {

    public void wordSpeed(Printer printer, Command command){
        if (!command.hasSecondWord()) {
            printer.print("Syntax: wordspeed [number (ms)]");
            return;
        }
        long time=Long.parseLong(command.getSecondWord());
        System.out.println(time);
        if (time>0){
            printer.setFancyPrint(true);
            printer.setPrintSpeed(time);
        }
        else{
            printer.setFancyPrint(false);
        }


    }

}
