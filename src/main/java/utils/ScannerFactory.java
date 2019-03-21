package utils;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ScannerFactory {

    public Scanner getSystemIn() {
        return new java.util.Scanner(System.in);
    }
}
