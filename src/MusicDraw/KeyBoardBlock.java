package MusicDraw;

public class KeyBoardBlock {
    private int keyCode;
    private String exe_key;
    private float sizeX, sizeY;
    public boolean is_pressed;

    KeyBoardBlock(int keycode, String key) {
        keyCode = keycode;
        exe_key = key;
        sizeX = 35;
        sizeY = 35;
    }

    KeyBoardBlock(int keycode, String key, float sizex, float sizey) {
        keyCode = keycode;
        exe_key = key;
        sizeX = sizex;
        sizeY = sizey;
    }

    public void draw(int x, int y) {
        Main.parent.pushStyle();
        Main.parent.textSize(10);
        Main.parent.fill(UI.colorset.get("lightGray"));
        Main.parent.strokeWeight(3);

        int c;
        if (is_pressed)
            c = UI.colorset.get("blue");
        else
            c = UI.colorset.get("black");

        Main.parent.stroke(c);
        Main.parent.rect(x, y, sizeX, sizeY, 5);
        Main.parent.fill(c);
        Main.parent.text(exe_key, x + 10, y + 15);
        Main.parent.popStyle();
    }

    public boolean is_selected() {
        return this.keyCode == Main.parent.keyCode;
    }

//    TODO 何してるのか表示する何か。時間がアレば。
}
