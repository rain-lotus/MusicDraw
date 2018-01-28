package MusicDraw;

import java.util.ArrayList;
import java.util.HashMap;

public class UI {
    static final int layer_sizeX = 800;
    static final int layer_sizeY = 800;
    static int layer_posX;
    static int layer_posY;

    private static ArrayList<KeyBoardBlock> keyboard;
    private static String[] qwery =
            {"q", "w", "e", "r", "t", "y", "u", "i", "o",
                    "p", "a", "s", "d", "f", "g", "h", "j", "k",
                    "l", "z", "x", "c", "v", "b", "n", "m"};
    public static HashMap<String,Integer> colorset = new HashMap<>();

//    TODO 何してるのか見える物が作れたらいいな
//    TODO 本当に音の調節をしてくれ

    public static void init() {
        layer_posX = (Main.parent.width - layer_sizeX) / 2;
        layer_posY = 100;
        LayerView.sizeX = 150;
        LayerView.sizeY = 150;
        keyboard_init();
        colorset_init();
    }

    private static void keyboard_init(){
        //1~9
        KeyBoardBlock k;
        keyboard = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            k = new KeyBoardBlock(49 + i, String.valueOf(i));
            keyboard.add(k);
        }
        // 0
        k = new KeyBoardBlock(48, "0");
        keyboard.add(k);
        //q~m
        for (String s : qwery) {
            k = new KeyBoardBlock((int) s.charAt(0) - 32, s);
            keyboard.add(k);
        }
        //shift,ctrl,alt,sp,enter
        k = new KeyBoardBlock(16, "Shift", 45, 35);
        keyboard.add(k);
        k = new KeyBoardBlock(17, "Ctrl");
        keyboard.add(k);
        k = new KeyBoardBlock(18, "Alt");
        keyboard.add(k);
        k = new KeyBoardBlock(32, "", 35 * 3 + 10, 35);
        keyboard.add(k);
        k = new KeyBoardBlock(10, "Enter", 50, 35 * 2);
        keyboard.add(k);
        //↑←↓→
        k = new KeyBoardBlock(38, "↑");
        keyboard.add(k);
        k = new KeyBoardBlock(37, "←");
        keyboard.add(k);
        k = new KeyBoardBlock(40, "↓");
        keyboard.add(k);
        k = new KeyBoardBlock(39, "→");
        keyboard.add(k);
    }
    private static void colorset_init(){
        colorset.put("black",Main.parent.color(0));
        colorset.put("white",Main.parent.color(0,0,100));
        colorset.put("background",Main.parent.color(100));
        colorset.put("lightGray",Main.parent.color(150));
        colorset.put("blue",Main.parent.color(189,88,96));
        colorset.put("blue2",Main.parent.color(189,88,40));
        colorset.put("blue3",Main.parent.color(189,88,20));
        colorset.put("pink",Main.parent.color(320,88,96));
        colorset.put("pink2",Main.parent.color(320,88,40));
        colorset.put("pink3",Main.parent.color(320,88,20));
    }

    public static void draw() {
        UI.draw_canvas(); //background
        UI.draw_rightMenu();
        UI.draw_keyboard();
        ColorPalette.draw();
    }
    private static void draw_canvas() {
        Main.parent.background(colorset.get("background"));
        //キャンバスの背景
        Main.parent.fill(colorset.get("white"));
        Main.parent.stroke(colorset.get("black"));
        //鍵盤の所
        for (int i = 0; i < layer_sizeX / 40; i++) {
            int flag = i+5;
            if (flag % 12 > 4) flag++;
            if (flag % 2 == 0) Main.parent.fill(colorset.get("white"));
            else Main.parent.fill(colorset.get("black"));
            Main.parent.rect(layer_posX + i * 40, layer_posY - 10, 40, layer_sizeY + 20);
        }
        //キャンバス
        Main.parent.noStroke();
        Main.parent.rect(layer_posX, layer_posY, layer_sizeX, layer_sizeY);
        //レイヤー表示
        for (Layer l : Drawing.Layers) {
            if (l.view.is_display) l.disp();
        }
    }
    private static void draw_rightMenu() {
        int menu_width = 200;
        Main.parent.noStroke();

        Main.parent.fill(colorset.get("lightGray"));
        Main.parent.rect(Main.parent.width - menu_width, 0, menu_width, Main.parent.height);

        Main.parent.fill(colorset.get("blue"));
        Main.parent.rect(Main.parent.width - menu_width,
                20 + (Drawing.layer_count - Drawing.selected_layer_number - 1) * (LayerView.sizeY + 50),
                menu_width, menu_width);

        for (int i = 0; i < Drawing.Layers.size(); i++) {
            Layer l = Drawing.Layers.get(Drawing.Layers.size() - 1 - i);
            l.view.disp(Main.parent.width - menu_width + 30,
                    40 + i * (LayerView.sizeY + 50), l.layer);
        }
    }
    private static void draw_keyboard() {
        int x = 40;
        int y = 800;
        int def = 40;
        for (int i = 0; i < keyboard.size(); i++) {
            KeyBoardBlock k = keyboard.get(i);
            if (i < 10)
                k.draw(x + def * i, y);
            else if (i < 20)
                k.draw(x + def * (i - 10) + 15, y + def);
            else if (i < 29)
                k.draw(x + def * (i - 20) + 30, y + def * 2);
            else if (i < 36)
                k.draw(x + def * (i - 29) + 45, y + def * 3);
            else if (i == 36)
                k.draw(x - 5, y + def * 3);
            else if (i == 37)
                k.draw(x + 5, y + def * 4);
            else if (i == 38)
                k.draw(x + 5 + def * 2, y + def * 4);
            else if (i == 39)
                k.draw(x + 5 + def * 4, y + def * 4);
            else if (i == 40)
                k.draw(x + def * 10 + 15, y + def);
            else if (i == 41)
                k.draw(x + def * 9 + 25, y + def * 3);
            else
                k.draw(x + def * (7 + i-41) + 25, y + def * 4);
        }
    }

    public static void keyPressed() {
        for (int i = 0; i < keyboard.size(); i++) {
            KeyBoardBlock k = keyboard.get(i);
            if (k.is_selected()) k.is_pressed = true;
        }
    }

    public static void keyReleased() {
        for (int i = 0; i < keyboard.size(); i++) {
            KeyBoardBlock k = keyboard.get(i);
            k.is_pressed = false;
        }
    }


}
