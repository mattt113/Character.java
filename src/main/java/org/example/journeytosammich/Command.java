package org.example.journeytosammich;

//everything here is pretty simple.  just stores two strings
public class Command {
    private String commandWord;  //(firstWord)
    private String secondWord;

    public Command(String firstWord, String secondWord) {
        this.commandWord = firstWord;
        this.secondWord = secondWord;
    }

    public String getCommandWord() {
        return commandWord;
    }

    public String getSecondWord() {
        return secondWord;
    }

    //i dont know what I'd use this for
    public boolean isUnknown() {
        return commandWord == null;
    }

    public boolean hasSecondWord() {
        return secondWord != null;
    }
}
