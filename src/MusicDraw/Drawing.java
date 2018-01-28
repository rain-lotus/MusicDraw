package MusicDraw;

import java.util.ArrayList;

public class Drawing {
    static final int layer_count = 5; //全体のレイヤーの枚数
    public static int selected_layer_number = 0;//選択されているレイヤー
    static float stroke_weight = 5;
    static ArrayList<Layer> Layers;

//    TODO ノイズだけでも実装

    public static void reset() {
        Layer.layer_amount = 0;
        Layers = new ArrayList<>();
        Layers.add(new Layer("Beat"));
        Layers.add(new Layer("Beat"));
        Layers.add(new Layer("Melody"));
        Layers.add(new Layer("Melody"));
        Layers.add(new Layer("Melody"));
    }

    //--変数--//
    public static Layer selected_layer() {
        Layer l = Layers.get(selected_layer_number);
        return l;
    }

    public static boolean on_canvas() {
        //マウス判定
        if (Main.parent.mouseY < UI.layer_posY
                || Main.parent.mouseY > UI.layer_posY + UI.layer_sizeY
                || Main.parent.mouseX < UI.layer_posX
                || Main.parent.mouseX > UI.layer_posX + UI.layer_sizeX) return false;
        else return true;
    }

    public static void keyPressed(){
        switch (Main.parent.key){
            case 'g':
                selected_layer_number = 4;
                break;
            case 'h':
                selected_layer_number = 3;
                break;
            case 'j':
                selected_layer_number = 2;
                break;
            case 'k':
                selected_layer_number = 1;
                break;
            case 'l':
                selected_layer_number = 0;
                break;
        }
    }

    //--change strokeWeight--//
    private static int centerx, centery;

    public static void change_mouseP() {
        centerx = Main.parent.mouseX;
        centery = Main.parent.mouseY;
    }

    public static void change_strokeWeight() {
        stroke_weight = Main.parent.sqrt(Main.parent.sq(centerx - Main.parent.mouseX)
                + Main.parent.sq(centery - Main.parent.mouseY)) / 2;
        Main.parent.noFill();
        Main.parent.stroke(UI.colorset.get("lightGray"));
        Main.parent.ellipse(centerx, centery, stroke_weight, stroke_weight);
    }

    public static void change_mouseR() {
    }

}

