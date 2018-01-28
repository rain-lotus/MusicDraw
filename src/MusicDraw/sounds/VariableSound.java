package MusicDraw.sounds;

import MusicDraw.Drawing;
import MusicDraw.Main;
import MusicDraw.SoundPerformance;
import ddf.minim.AudioOutput;
import ddf.minim.Minim;
import ddf.minim.ugens.Oscil;
import ddf.minim.ugens.Waves;
import processing.core.PApplet;

public class VariableSound extends Sound {
    private AudioOutput out2;
    private Oscil sine2;
    private boolean isharmonize = false;
    private int harmonize_rank = 0;
    private int layer_num;
    private float record_frec;

    public VariableSound(int n) {
        layer_num = n;
        out2 = Main.minim.getLineOut(Minim.STEREO);
        sine2 = new Oscil(440, (float) 0.5, Waves.SINE);
        sine2.patch(out2); //変更点
        sine2.setAmplitude(0);
    }

    @Override
    public void disp(int x, int y) {
    }

    @Override
    public void sound_init() {
        if(!is_mute) {
            sine.setFrequency(SoundPerformance.frec());
            sine.setAmplitude((float) 0.7);
        }
        if (isharmonize) harmonize(harmonize_rank);
        if (is_record) record_frec = SoundPerformance.frec();
    }

    boolean is_serected() {
        return layer_num == Drawing.selected_layer_number;
    }

    @Override
    public void process() {
        if (is_record) {
            if (is_serected() && Main.parent.mousePressed) sample[count()] = record_frec;
            //録画ループモードなら音が足されて繰り返される
            //一音は可変長に出来た
            if (Main.parent.millis() % ms_p_qbeat < 30) {
                if (sample[count()] != 0) {
                    sine.setFrequency(sample[count()]);
                    sine.setAmplitude((float) 0.7);
                } else sine.setAmplitude(0);
            }
        }
    }

    void harmonize(int def) {
        sine2.setFrequency(SoundPerformance.harmonick_frec(def));
        sine2.setAmplitude((float) 0.5);
    }

    @Override
    public void sound_end() {
        sine.setAmplitude(0);
        sine2.setAmplitude(0);
    }
    // ハモりを刷る前に数字キーでどれだけハモるかを指定すれば一応は漏れる。美しさは保証されていない。
    @Override
    public void key_judge() {
        switch (Main.parent.key) {
            case 'r':
                reset();
                break;
            case ' ':
                rec_start();
                break;
            //TODO ハモり調節
            //TODO キーでオクターブ？変えられるようにする
            case '1':
                isharmonize = true;
                harmonize_rank = -1;
                break;
            case '2':
                isharmonize = true;
                harmonize_rank = -2;
                break;
            case '3':
                isharmonize = true;
                harmonize_rank = -3;
                break;
            case '4':
                isharmonize = true;
                harmonize_rank = -4;
                break;
            case '5':
                isharmonize = true;
                harmonize_rank = -5;
                break;
            case '6':
                isharmonize = true;
                harmonize_rank = -6;
                break;
            case '7':
                isharmonize = true;
                harmonize_rank = -7;
                break;
            case '8':
                isharmonize = false;
                harmonize_rank = 0;
                break;
            case '9':
                isharmonize = true;
                harmonize_rank = 12;
                break;
        }
    }

    public void key_end() {
    }
}
