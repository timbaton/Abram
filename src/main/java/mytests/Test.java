package mytests;

import dao.DesksDao;
import utils.PrintManager;

public class Test {
    public static void main(String[] args) {
        PrintManager printManager = new PrintManager();

        printManager.print("1)Hello\n2)Chinese\n");
    }
}

