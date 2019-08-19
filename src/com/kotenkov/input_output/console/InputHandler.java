package com.kotenkov.input_output.console;

import java.util.Scanner;

public class InputHandler {

    public static final String WORD_PAT = "[A-Za-z0-9]+";
    public static final String EMAIL_PAT = "[\\w\\d]{5,15}@\\w{2,5}.\\w{2,4}";
    public static final String BACK_PAT = "[Bb][Aa][Cc][Kk]";

    Scanner s;

    public InputHandler() {
        s = new Scanner(System.in);
    }

    public int enterInt(int from, int to){
        s.reset();
        int number = -1;

        while (!(number >= from && number <= to)) {
            System.out.println("\nEnter number from " + from + " to " + to + ":");
            while (!s.hasNextInt()) {
                s.next();
                System.out.println("\nEnter number from " + from + " to " + to + ":");
            }

            number = s.nextInt();

            if (number >= from && number <= to) {
                break;
            }
        }

        return number;
    }

    public String enterWord(){
        StringBuilder sb = new StringBuilder();

        System.out.println("\nWaiting for input...");
        while (!(s.hasNext(WORD_PAT))) {
            s.next();
            System.out.println("\nWaiting for input...");
        }

        sb.append(s.next());

        return sb.toString();
    }

    public String enterEmail(){
        StringBuilder sb = new StringBuilder();

        System.out.println("\nWaiting for input...");
        while (!(s.hasNext(EMAIL_PAT) || s.hasNext(BACK_PAT))) {
            s.next();
            System.out.println("\nWaiting for input...");
        }

        sb.append(s.next());

        return sb.toString();
    }

    public String enterPhrase() {
        StringBuilder sb = new StringBuilder();

        System.out.println("\nWaiting for input...");

        while(s.hasNextLine()){
            String str = s.nextLine();
            if(!str.isEmpty()){
                sb.append(str);
                break;
            }
        }

        return sb.toString();
    }
}
