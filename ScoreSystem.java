package com.company;

import java.io.*;
import java.lang.*;
import java.util.*;
import java.io.IOException;

public class ScoreSystem {
    private FileWriter out = null;
    private PrintWriter out_pw = null;
    private FileReader in = null;
    private BufferedReader in_br = null;
    private LinkedList<String> scores = new LinkedList<>();

    public ScoreSystem() {
        loadScores();
    }

    public void saveScore(String score) {

        try {
            out = new FileWriter("C:\\Users\\brainpow3r\\Desktop\\JAVA\\AsteroidsIDE\\src\\com\\company\\highscores.txt");
            out_pw = new PrintWriter(out);

            scores.add(score);
            Collections.sort(scores, Collections.reverseOrder());
            scores.forEach(str -> out_pw.append(str+"\n"));

            out_pw.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadScores() {
        try {
            in = new FileReader("C:\\Users\\brainpow3r\\Desktop\\JAVA\\AsteroidsIDE\\src\\com\\company\\highscores.txt");
            in_br = new BufferedReader(in);

            String str;
            while((str = in_br.readLine()) != null) {
                scores.add(str);
            }

            in_br.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getScore(int index) {
        return scores.get(index);
    }

    public int getScoresCount() {
        return scores.size();
    }
}
