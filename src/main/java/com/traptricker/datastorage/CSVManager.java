package com.traptricker.datastorage;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class handles everything to do with storing data in csv files.
 */
public class CSVManager {

  private static final String filePath = "src/main/resources/highScore.csv";

  // Reads the stored csv values, index 0 is high score, index 1 high level
  public static String[] readHighScore() throws IOException {
    // Checks if a file exists and if it doesn't, creates the file
    try {
      CSVReader reader = new CSVReader(new FileReader(filePath));
      String[] nextLine = reader.readNext();
      reader.close();
      return nextLine;
    } catch (FileNotFoundException e) {
      writeHighScore(1, 1);
      return new String[]{"0", "0"};
    }
  }

  public static void writeHighScore(int highScore, int highLevel) throws IOException {
    CSVWriter writer = new CSVWriter(new FileWriter(filePath));
    String[] csvLine = {String.valueOf(highScore), String.valueOf(highLevel)};
    writer.writeNext(csvLine);
    writer.close();
  }

}
