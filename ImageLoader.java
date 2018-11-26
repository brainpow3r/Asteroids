package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;
import java.io.IOException;

public class ImageLoader {

    private static LinkedList<BufferedImage> images = new LinkedList<>();

    public ImageLoader() {
        try {
            images.add(ImageIO.read(new File("C:\\Users\\brainpow3r\\Desktop\\JAVA\\AsteroidsIDE\\src\\com\\company\\sprites\\background.png")));
            images.add(ImageIO.read(new File("C:\\Users\\brainpow3r\\Desktop\\JAVA\\AsteroidsIDE\\src\\com\\company\\sprites\\player.png")));
            images.add(ImageIO.read(new File("C:\\Users\\brainpow3r\\Desktop\\JAVA\\AsteroidsIDE\\src\\com\\company\\sprites\\lives.png")));
            images.add(ImageIO.read(new File("C:\\Users\\brainpow3r\\Desktop\\JAVA\\AsteroidsIDE\\src\\com\\company\\sprites\\enemy.png")));
            images.add(ImageIO.read(new File("C:\\Users\\brainpow3r\\Desktop\\JAVA\\AsteroidsIDE\\src\\com\\company\\sprites\\enemy_special.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static BufferedImage getImage(int index) {
        if (images.size() != 0) {
            return images.get(index);
        } else {
            return null;
        }
    }

}
