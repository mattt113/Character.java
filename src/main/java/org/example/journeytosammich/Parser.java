package org.example.journeytosammich;
import java.util.Scanner;

public class Parser {
    private CommandWords commands;
    private Scanner reader;

    public Parser() {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }
    public void nefariousCommandsActivator(){
        commands.addNefariousCommands();
    }

    public Command getCommand(String rawInputLine) {
        //System.out.print("> ");
        //String rawInputLine = reader.nextLine();

        String inputLine=rawInputLine.toLowerCase();
        String word1 = null;
        String word2 = null;

        Scanner tokenizer = new Scanner(inputLine);
        if (tokenizer.hasNext()) {
            word1 = tokenizer.next();
            if (tokenizer.hasNext()) {
                word2 = tokenizer.next();
            }
        }

        if (commands.isCommand(word1)) {
            return new Command(word1, word2);
        } else {
            return new Command(null, word2);
        }
    }

    public String showCommands() {
        return commands.showAll();
    }
}
