package MusicDraw;

import MusicDraw.sounds.Sound;
import processing.core.PApplet;
import processing.core.PFont;
import ddf.minim.Minim;
import processing.core.PImage;

public class Main extends PApplet {
    public static final int tempo = 80;//bpm

    public PFont font;
    public static Minim minim;
    public static PApplet parent;
    private static PImage pal;

    @Override
    public void settings() {
        fullScreen();
    }

    @Override
    public void setup() {
        //--設定--//
        colorMode(HSB,360,100,100);
        font = createFont("メイリオ", 50);
        textFont(font);
        //--重要な宣言--//
        minim = new Minim(this);//絶対minimが最初
        parent = this;//こうすれば他の所のparenrtいらなかった
        //--init--//
        Drawing.reset();
        UI.init();
        SoundPerformance.init();

        pal = loadImage("palette.PNG");
    }

    @Override
    public void draw() {
        UI.draw();
        //UI.draw();
        SoundPerformance.draw();
        image(pal,80,60);
    }

    @Override
    public void mousePressed() {
        if (mouseButton == RIGHT) {
            ColorPalette.drawing_color = get(mouseX, mouseY);
            ColorPalette.mousePressed();
        }
        else if ((int) key == 26)
            Drawing.change_mouseP();
        else if (Drawing.on_canvas())
        SoundPerformance.mousePressed();
    }

    @Override
    public void mouseReleased() {
        SoundPerformance.mouseRereased();
        Drawing.change_mouseR();
    }

    @Override
    public void mouseDragged() {
        if ((int) key == 26)
            Drawing.change_strokeWeight();
        else {
            Layer l = Drawing.selected_layer();
            l.drawing();
        }
    }

    @Override
    public void keyPressed() {
        if (key == 'p') Drawing.reset();
        if (key == 'q') {
            if (Sound.is_mute)Sound.is_mute = false;
            else Sound.is_mute = true;
        }
        SoundPerformance.keyPressed();
        //なぜか回りくどいことをしないと出来ない
        UI.keyPressed();
        Drawing.keyPressed();
    }

    @Override
    public void keyReleased() {
        SoundPerformance.KeyRereased();
        UI.keyReleased();
    }

    public void stop() {
        minim.stop();
        super.stop();
    }

    public static void main(String[] args) {
        PApplet.main("MusicDraw.Main");
    }

}
