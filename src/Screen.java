import java.util.Random;
import java.util.Scanner;

public class Screen {

    private int width, height;
    public int[] pixels;
    public int[] tiles = new int[64 * 64];

    private Random random = new Random();

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];

        for(int i = 0; i < 64 * 64; ++i) {
            tiles[i] = random.nextInt(0xffffff);
        }

    }

    //set every pixel to black
    public void clear() {
        for( int i = 0; i < pixels.length; ++i) {
            pixels[i] = 0;
        }
    }
    public void render() {

        for (int y = 0; y < height; y++) {
            if (y < 0 || y >= height) break;
            for (int x = 0; x < width; x++) {
                if (x < 0 || x >= width) break;
                int tileIndex = (x / 16) + (y / 16) * 64;
                pixels[x + y * width] = tiles[tileIndex];
            }
        }
    }


}
