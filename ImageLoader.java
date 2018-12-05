package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.io.IOException;
import java.util.List;

public class ImageLoader {

    private static String defSprite = "C:\\Users\\brainpow3r\\Desktop\\JAVA\\AsteroidsIDE\\src\\com\\company\\sprites\\0def.png";
    private String path = "C:\\Users\\brainpow3r\\Desktop\\JAVA\\AsteroidsIDE\\src\\com\\company\\sprites\\";
    private List<String> fileNames = Arrays.asList("background.png", "player.png", "lives.png",
                                  "enemy.png", "enemy_special.png", "player_immune.png",
                                  "projectile.png");
    private LinkedList<String> absPath = new LinkedList<>();

    private static LinkedList<BufferedImage> images = new LinkedList<>();

    public ImageLoader() {

        boolean success = false;

        for (int i = 0; i < fileNames.size(); i++) {

            try {
                File imgFile = new File(path+fileNames.get(i));

                try {
                    images.add(ImageIO.read(imgFile));
                } catch (IOException excIO) {
                    try {
                        images.add(ImageIO.read(new File(defSprite)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            } catch (NullPointerException excNPE) {
                try {
                    images.add(ImageIO.read(new File(defSprite)));
                } catch (IOException excIO) {
                    excIO.printStackTrace();
                }
            }
        }

        success = images.stream().allMatch(img -> img != null);
        if (success) {
            System.out.println("All images have been loaded successfully!");
        }
    }

    public static BufferedImage getImage(int index) throws ImageApplyingException {
        if (images.size() != 0) {
            try {
                return images.get(index);
            } catch (IndexOutOfBoundsException e) {
                throw new ImageApplyingException("Image does not exist at required index: " + index + ". Applying default Sprite", e);
            }
        } else {
            return null;
        }
    }

    public static BufferedImage getDefaultImage() {
        try {
            return ImageIO.read(new File(defSprite));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
