package chapter3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LegacyAutoboxing {
    public static void main(String[] args) {
        java.util.List<Integer> numbers = new java.util.ArrayList<>();
        numbers.add(5);
        int result = numbers.get(0);    // DOES NOT COMPILE



    }
}