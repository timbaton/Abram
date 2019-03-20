package utils;

import java.util.Scanner;

public class ScannerFactory {
    public Scanner getSystemIn() {
        return new java.util.Scanner(System.in);
    }
}
