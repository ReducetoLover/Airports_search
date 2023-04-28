package com.reduce;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
public class App {

    private static final String Airports_csv = "airports.csv";
    private static final String Quit_program = "!quit";

    public static void main(String[] args) {
        Filter();
    }
    private  static void Filter()
    {
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.print("Введите фильтр: ");
            String filter_text = sc.nextLine();

            if (!Quit_program.equals(filter_text))
            {
                System.out.print("Введите начало имени аэропорта: ");
                String airports_text = sc.nextLine();
                Search(airports_text);
            }
            else
            {
                break;
            }
        }
    }
    private static void Search(String airports_text)
    {
        int str=0;
        try {
            BufferedReader br = Files.newBufferedReader(Paths.get(Airports_csv));
            String line;
            long startTime = System.currentTimeMillis();
            while ((line = br.readLine()) != null) {
                String[] column = line.split(",");
                if (column[1].substring(1).startsWith(airports_text)) {
                    str++;
                    System.out.println(column[1] + "[" + line + "]");
                }
            }
            br.close();
            long endTime = System.currentTimeMillis();
            String message = String.format("Количество найденных строк: %d. Время, затраченное на поиск: %d мс", str,(endTime - startTime));
            System.out.println(message);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}