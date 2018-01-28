package MusicDraw;

import processing.core.PGraphics;

public class Layer {
    static int layer_amount = 0;
    private int number = -1;
    PGraphics layer;
    LayerView view;

    Layer(String n) {
        this.number = Layer.layer_amount;
        Layer.layer_amount++;
        view = new LayerView(number,n);

        this.layer = Main.parent.createGraphics(UI.layer_sizeX, UI.layer_sizeY, Main.parent.JAVA2D);
        this.layer.beginDraw();
        //なんでもいいから何か描いてないとエラー出る
        this.layer.stroke(-1);
        this.layer.point(0, 0);
        this.layer.endDraw();
    }

    void disp() {
        Main.parent.image(this.layer, UI.layer_posX, UI.layer_posY);
    }

    void drawing() {
        this.layer.beginDraw();
        this.layer.stroke(ColorPalette.drawing_color);
        this.layer.strokeWeight(Drawing.stroke_weight);
        this.layer.line(Main.parent.pmouseX - UI.layer_posX, Main.parent.pmouseY - UI.layer_posY,
                Main.parent.mouseX - UI.layer_posX, Main.parent.mouseY - UI.layer_posY);
        this.layer.endDraw();
    }
}