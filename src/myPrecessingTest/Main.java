package myPrecessingTest;
import processing.core.PApplet;

public class Main extends PApplet {
    @Override
    public void settings() {
        super.settings();
        size(200,200);
    }

    Ball[] balls = new Ball[3];

    @Override
    public void setup() {
        super.setup();
        for(int i = 0; i < balls.length; i++){
            balls[i] = new Ball();
        }
    }

    @Override
    public void draw() {
        background(200);
        for(int i = 0; i < balls.length; i++){
            balls[i].disp();
            balls[i].move();
        }
    }

    public static void main(String[] args) {
        PApplet.main("myPrecessingTest.Main");
    }
}
