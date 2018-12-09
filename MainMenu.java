package com.company;

import java.awt.*;

public class MainMenu {

    public Rectangle playButton = new Rectangle (290, 185, 250, 50);
    public Rectangle highscoresButton = new Rectangle (290, 260, 250, 50);
    public Rectangle quitButton = new Rectangle (290, 335, 250, 50);
    public Rectangle backButton = new Rectangle (600, 300, 110, 50);
    public static enum MENUSTATES {
        MENU,
        HIGHSCORES
    }
    public static MENUSTATES menuState = MENUSTATES.MENU;
    private ScoreSystem highscores;

    public MainMenu(){
        highscores = new ScoreSystem();
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Font fnt0 = new Font("arial", Font.BOLD, 36);
        g.setFont(fnt0);
        g.setColor(Color.white);

        if (menuState == MENUSTATES.MENU) {
            g2d.draw(playButton);
            g.drawString("PLAY", playButton.x + 72, playButton.y + 37);

            g2d.draw(highscoresButton);
            g.drawString("HIGHSCORES", highscoresButton.x + 4, highscoresButton.y + 37);

            g2d.draw(quitButton);
            g.drawString("QUIT", quitButton.x + 75, quitButton.y + 37);
        } else if (menuState == MENUSTATES.HIGHSCORES) {
            int y = 175;
            String str;
            int scoresToPrint = (highscores.getScoresCount() > 7) ? 7 : highscores.getScoresCount();
            for(int i = 0; i < scoresToPrint; i++) {                    // print only 7 highest scores, no need for others
                str = (i+1)+". "+highscores.getScore(i);
                g.drawString(str, 150, y);
                y += 35;
            }
            y = 150;
            g2d.draw(backButton);
            g.drawString("BACK", backButton.x + 3, backButton.y + 37);
        }

    }

}
