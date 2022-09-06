package ru.gb.may_chat.client.History;

import org.apache.commons.io.input.ReversedLinesFileReader;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class History {

    public static void saveHistory(String user, String message) {

        File history = new File("History_" + user + ".txt");

        if (!history.exists()) {
            try {
                if (!history.createNewFile()) return;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            String fileName = "History_" + user + ".txt";
            File userHistory = new File(fileName);

            PrintWriter fileWriter1 = new PrintWriter(new FileWriter(userHistory, true));

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter1);
            bufferedWriter.write(message + "\n");
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public static String loadHistory(String user) {

            StringBuilder sb = new StringBuilder();
            int maxLines = 100;

            List<String> result = new ArrayList<>();

            try (ReversedLinesFileReader reader = new ReversedLinesFileReader(new File("History_" + user + ".txt"), StandardCharsets.UTF_8)) {

                String line;
                while ((line = reader.readLine()) != null && result.size() < maxLines) {
                    result.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (String str : result) {
                sb.insert(0, maxLines-- + ") " + str + "\n");
            }
            return sb.toString();


        }
    }
