import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//in charge of managing any sprite sheets we might have
//and cacheing them to memory
public class SpriteSheet {
    private String path; //system path to our sprite sheet
    public final int SIZE;
    public int[] pixels;

    public static SpriteSheet tiles = new SpriteSheet("Sprite16.6.png", 256);

    public SpriteSheet(String path, int size) {
        this.path = path;
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        load();
    }

    /*
    private void load(String path) {
        try {
            BufferedImage  image = ImageIO.read(SpriteSheet.class.getResource(path));
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0,0,w,h,pixels,0,w);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */

    private void load(){
        try {
            BufferedImage image = ImageIO.read(new File(path));
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0, 0, w, h, pixels, 0, w);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
