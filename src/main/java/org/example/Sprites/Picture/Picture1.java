package org.example.Sprites.Picture;

import org.example.Sprites.Sprite;
import org.example.Windows.MainCanvas;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class Picture1 extends Sprite {
    private float vX;
    private float vY;
    private Image pict;
    File file;

    public Picture1() {
        //Долго я возился с передачей пути файла, - чтобы он и Maven читался
        InputStream fileURL = getClass().getClassLoader().getResourceAsStream("Images/DSC_0265_.JPG");
        InputStream file = fileURL;
        //Так не работает, - ошибка: URI не является иерархическим
//        URL fileURL = getClass().getClassLoader().getResource("Images/DSC_0265_.JPG");
//        try {
//            file = new File(fileURL.toURI());
//        } catch (URISyntaxException e) {
//            throw new RuntimeException(e);
//        }


//        file = new File(getProperty("user.dir") + "/src/main/resources/Images/DSC_0265_.JPG");

        try {
            pict = ImageIO.read(file);

        } catch (IOException e) {
            e.printStackTrace();

        }


        vX = 100f + (float) (Math.random() * 200f);
        vY = 100f + (float) (Math.random() * 200f);
    }

    @Override
    public void update(MainCanvas canvas, float deltaTime) {

        x += vX * deltaTime;
        y += vY * deltaTime;
        if (getLeft() < canvas.getLeft()) {
            setLeft(canvas.getLeft());
            vX = -vX;
        }
        if (getRight() > canvas.getRight()) {
            setRight(canvas.getRight());
            vX = -vX;
        }
        if (getTop() < canvas.getTop()) {
            setTop(canvas.getTop());
            vY = -vY;
        }
        if (getBottom() > canvas.getBottom()) {
            setBottom(canvas.getBottom());
            vY = -vY;
        }
    }

    @Override
    public void render(MainCanvas canvas, Graphics g) {
        if (pict == null) return;
        halfHeight = (float) pict.getHeight(canvas) / 2;
        halfWidth = (float) pict.getWidth(canvas) / 2;
        g.drawImage(pict, (int) getLeft(), (int) getTop(), canvas);


    }


}
