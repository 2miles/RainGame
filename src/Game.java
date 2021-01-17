import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;

public class Game extends Canvas implements Runnable{

    //public: accesed throughout entire project
    //static: for this game class there will only be one
    public static int width = 300;
    public static int height = width / 16 * 9;
    public static int scale = 3;

    //a sub process
    private Thread thread;
    private JFrame frame;
    private boolean running = false;
    private Screen screen;

    private BufferedImage image = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
    //retun a writable raster
    //converting this image object into and array of itnegers, and that array
    //of integers is gonna signal which pixel revievs whic color, bucause we can
    //now modfy the values of this array and thus create an image.
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

    public Game() {
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);

        screen = new Screen(width,height);
        frame = new JFrame();

    }


    //ensures no overlaps for the thread object
    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }
    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    //the code that gets exicuted when we start our game
    public void run() {
        while (running) {
            //logic  //at a certain speed
            update();
            //display images //as fast a possible
            render();
        }
    }

    public  void update() {

    }

    public void render() {
        //retrive the bufferstratagy of the canvas object that we are
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        //creating a link between the buffer and the graphics
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0,0,getWidth(),getHeight());
            //here is where we do all the graphics
        g.dispose(); //remove the graphics we arnt using anymore
        bs.show(); //make the next available buffer visible

    }

    public static void main(String[] args) {
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.setTitle("Rain");
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);

        game.start();
    }
}
