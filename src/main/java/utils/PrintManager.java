package utils;

public class PrintManager {
    private final int BORDER_SIZE = 40;


    public void print(String text) {
        printInAFrame(text);
    }

    public void printInNewScreen(String text) {
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
        printInAFrame(text);
    }

    private void printInAFrame(String text) {
        //printing top border
        System.out.print(" ");
        for (int i = 0; i < BORDER_SIZE; i++) {
            System.out.print("-");
        }

        //start of the first line
        System.out.print("\n|");

        char[] textChar = text.toCharArray();

        int unCountedChars = 0;
        int newLinesCount = 0;
        //printing text with left and right boards
        for (int charPosition = 0; charPosition < textChar.length; charPosition++) {

            //if the char is new line - we have to manage it.
            if (textChar[charPosition] == '\n') {
                //number of printed word before the every new line
                int countOfPrintedChars = (charPosition - unCountedChars) % BORDER_SIZE - newLinesCount;
                newLinesCount++;
                int spaceCount = BORDER_SIZE - countOfPrintedChars;
                unCountedChars += countOfPrintedChars;

                for (int j = 0; j < spaceCount; j++) {
                    System.out.print(" ");
                }
                System.out.print("|\n|");
                charPosition++;
            }

            System.out.print(textChar[charPosition]);
            int i1 = (charPosition - unCountedChars - newLinesCount + 1) % (BORDER_SIZE);
            if ((i1 == 0 && charPosition != unCountedChars + newLinesCount - 1)) {
                System.out.print("|\n|");
            }
        }

        //counting space
        int j = BORDER_SIZE - (textChar.length - unCountedChars - newLinesCount) % BORDER_SIZE;

        //printing space in the end
        for (int i = 0; i < j; i++) {
            System.out.print(" ");
        }
        System.out.println("|");

        System.out.print(" ");
        //printing bottom border
        for (int i = 0; i < BORDER_SIZE; i++) {
            System.out.print("-");
        }
    }
}
