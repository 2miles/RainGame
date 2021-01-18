import java.util.Random;
import java.util.Scanner;

public class Screen {

    private int width, height;
    public int[] pixels;
    public final int MAP_SIZE = 64;
    public final int MAP_SIZE_MASK = MAP_SIZE - 1;
    public int[] tiles = new int[MAP_SIZE * MAP_SIZE];

    private Random random = new Random();

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];

        for(int i = 0; i < MAP_SIZE * MAP_SIZE; ++i) {
            tiles[i] = random.nextInt(0xffffff);
        }
    }

    //set every pixel to black
    public void clear() {
        for( int i = 0; i < pixels.length; ++i) {
            pixels[i] = 0;
        }
    }
    public void render(int xOffset, int yOffset) {

        for (int y = 0; y < height; y++) {
            int yy = y + yOffset;
            //if (yy < 0 || yy >= height) break;
            for (int x = 0; x < width; x++) {
                int xx = x + xOffset;
                //if (xx < 0 || xx >= width) break;

                //(xx >> 4) is same as (x / 16).
                //when  x>>4 gets to 63 loop back around to 0
                int tileIndex = ((xx >> 4) & MAP_SIZE_MASK) + ((yy >> 4) & MAP_SIZE_MASK) * MAP_SIZE;
                //int tileIndex = (x / 16) + (y / 16) * 64;
                pixels[x + y * width] = tiles[tileIndex];
            }
        }
    }


}
