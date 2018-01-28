package MusicDraw;

import processing.core.PApplet;
import processing.core.PGraphics;

public class LayerView {
    static float sizeX, sizeY;
    private float posX, posY;
    private int number;
    boolean is_display = true;
    private String name;

    LayerView(int num,String n) {
        number = num;
        name = n;

    }

    void disp(float x, float y, PGraphics layer) {
        //サムネイル表示
        posX = x;
        posY = y;
        Main.parent.noFill();
        Main.parent.noStroke();
        Main.parent.fill(UI.colorset.get("white"));
        Main.parent.rect(posX, posY, sizeX, sizeY);
        Main.parent.image(layer, posX, posY, sizeX, sizeY);

        Main.parent.fill(UI.colorset.get("black"));
        Main.parent.textSize(12);
        Main.parent.text(name, posX + sizeX / 4+10, posY + sizeY + 20);

        if (this.is_display) Main.parent.fill(UI.colorset.get("black"));
        else Main.parent.noFill();
        Main.parent.stroke(UI.colorset.get("black"));
        Main.parent.rect(posX - 20, posY + sizeY / 2 - 5, 10, 10);
        this.mouse();
    }

    boolean is_overlapped() {
        //マウス判定
        if (Main.parent.mouseY < posY
                || Main.parent.mouseY > posY + sizeY
                || Main.parent.mouseX < posX
                || Main.parent.mouseX > posX + sizeX) return false;
        else return true;
    }

    boolean on_disp_switch() {
        //マウス判定
        if (Main.parent.mouseY < posY
                || Main.parent.mouseY > posY + sizeY
                || Main.parent.mouseX < posX - 30
                || Main.parent.mouseX > posX) return false;
        else return true;
    }

    private void mouse() {
        if (is_overlapped() && Main.parent.mousePressed) {
            Drawing.selected_layer_number = number;
            Main.parent.mousePressed = false;
        }
        if (on_disp_switch() && Main.parent.mousePressed) {
            this.is_display = !this.is_display;
            Main.parent.mousePressed = false;
        }
    }
}
