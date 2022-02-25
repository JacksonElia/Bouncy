package com.traptricker.datastorage;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class handles everything to do with storing data in csv files.
 */
public class CSVManager {

  private static final String filePath = "src/main/resources/highScore.csv";

  public static String[] readHighScore() throws IOException {
    CSVReader reader = new CSVReader(new FileReader(filePath));
     return reader.readNext();
  }

  public static void writeHighScore(int highScore, int highLevel) throws IOException {
    CSVWriter writer = new CSVWriter(new FileWriter(filePath));
    String[] csvLine = {String.valueOf(highScore), String.valueOf(highLevel)};
    writer.writeNext(csvLine);
  }

}
