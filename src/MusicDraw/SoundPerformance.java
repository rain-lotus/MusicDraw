package MusicDraw;

import MusicDraw.sounds.Beat;
import MusicDraw.sounds.SoundInterface;
import MusicDraw.sounds.VariableSound;

import java.util.HashMap;

public class SoundPerformance {
    private static HashMap<String, SoundInterface> sounds;
    private static int pitch = 440;

    static String layernum_to_key() {
        switch (Drawing.selected_layer_number) {
            case 0:
                return "dram";
            case 1:
                return "base";
            case 2:
                return "accent";
            case 3:
                return "vocal";
            case 4:
                return "effect";
            default:
                return "0";
        }
    }

    static void init() {
        sounds = new HashMap<>();
        sounds.put("dram", new Beat(0,440));
        sounds.put("base", new Beat(1));
        sounds.put("accent", new VariableSound(2));
        sounds.put("vocal", new VariableSound(3));
        sounds.put("effect", new VariableSound(4));
    }

    static void draw() {
        for (SoundInterface sound : sounds.values()) {
            sound.process();
        }
        sounds.get("dram").disp(UI.layer_posX,
                UI.layer_posY + UI.layer_sizeY + 95);
        sounds.get("base").disp(UI.layer_posX,
                UI.layer_posY + UI.layer_sizeY + 40);
    }

    static void mousePressed() {
        if (layernum_to_key().equals("0")) System.out.println("error");
        else sounds.get(layernum_to_key()).sound_init();
    }

    static void mouseRereased() {
        if (layernum_to_key().equals("0")) System.out.println("error");
        else sounds.get(layernum_to_key()).sound_end();
    }

    static void keyPressed() {
        if (layernum_to_key().equals("0")) System.out.println("error");
        else sounds.get(layernum_to_key()).key_judge();

        //--音高のシフト--//
        switch (Main.parent.key) {
            case 'a':
                pitch = 220;
                break;
            case 's':
                pitch = 440;
                break;
            case 'd':
                pitch = 880;
                break;
        }
    }

    static void KeyRereased() {
        for (SoundInterface sound : sounds.values()) {
            sound.key_end();
        }
    }

    public static float frec() {
        float posx = Main.parent.mouseX - UI.layer_posX;
        float d = posx / 40 - 5;
        return pitch * Main.parent.pow(2, d / 12);
    }

    public static float harmonick_frec(int def) {
        float posx = Main.parent.mouseX - UI.layer_posX;
        float d = posx / 40 - 5;
        return pitch * Main.parent.pow(2, (d + def) / 12);
    }

}