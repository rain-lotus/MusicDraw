package myPrecessingTest;

public class Ball {
    private float x,y;

    Ball(){
        x = random(width);
        y = random(height);
    }

    void disp(){
        fill(0);
        ellipse(x,y,20,20);
    }

    void move() {
        this.x = (this.x + random(-20, 20))%width;
        this.y = (this.y + random(-20, 20))%height;
    }

}
