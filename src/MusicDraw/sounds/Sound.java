package MusicDraw.sounds;

import MusicDraw.Main;
import ddf.minim.AudioOutput;
import ddf.minim.Minim;
import ddf.minim.ugens.Oscil;
import ddf.minim.ugens.Waves;
import processing.core.PApplet;

public class Sound implements SoundInterface {
    //共通する所と基本を埋めるよ
    AudioOutput out;
    Oscil sine;
    float[] sample = new float[32];
    boolean is_record = false;
    public static boolean is_mute;

    Sound() {
        init();
        ex_init();
    }
    private void ex_init() {
        //録音の初期化
        for (int i = 0; i < sample.length; i++) {
            sample[i] = 0;
        }
    }
    void reset() {
        ex_init();
    }
    void rec_start() {
        if(is_record) is_record = false;
        else is_record = true;
    }
    static int count() {
        //return 1~32
        return (int) ((Main.parent.millis() % loop_time) / ms_p_qbeat);
    }
    void init() {
        out = Main.minim.getLineOut(Minim.STEREO);
        sine = new Oscil(440, (float) 0.5, Waves.SINE);
        sine.patch(out); //変更点
        sine.setAmplitude(0);
    }
    public void disp(int x, int y){
    }
    public void sound_init() {
    }
    public void process(){
    }
    public void sound_end() {
        sine.setAmplitude(0);
    }
    public void key_judge(){
    }
    public void key_end(){
    }
}
