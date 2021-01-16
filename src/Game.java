public class Game implements Runnable{

    //public: accesed throughout entire project
    //static: for this game class there will only be one
    public static int width = 300;
    public static int height = width / 16 * 9;
    public static int scale = 3;

    //a sub process
    private Thread thread;
    private boolean running = false;

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

        }
    }
}
