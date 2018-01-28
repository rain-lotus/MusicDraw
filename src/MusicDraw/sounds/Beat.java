package MusicDraw.sounds;

import MusicDraw.ColorPalette;
import MusicDraw.Main;
import MusicDraw.UI;

public class Beat extends Sound {
    //TODO ビートの音を買えるのを色か場所に対応させる
    private int layer_num;

    public Beat(int n) {
        layer_num = n;
    }

    public Beat(int n, int frec) {
        layer_num = n;
        for(int i = 0; i < sample.length; i++){
            if(i%4 == 0) sample[i] = frec;
        }
    }

    @Override
    public void disp(int x, int y) {
        for (int i = 0; i < sample.length; i++) {
            if (sample[i] != 0) {
                if (count() == i) Main.parent.fill(UI.colorset.get("pink"));
                else if (count() == i - 1 || count() == i + 1) Main.parent.fill(UI.colorset.get("pink2"));
                else if (count() == i - 2 || count() == i + 2) Main.parent.fill(UI.colorset.get("pink3"));
                else Main.parent.fill(UI.colorset.get("pink3"));
            } else {
                if (count() == i) Main.parent.fill(UI.colorset.get("blue"));
                else if (count() == i - 1 || count() == i + 1) Main.parent.fill(UI.colorset.get("blue2"));
                else if (count() == i - 2 || count() == i + 2) Main.parent.fill(UI.colorset.get("blue3"));
                else Main.parent.fill(UI.colorset.get("black"));
            }
            if (i % 4 == 0) Main.parent.rect(x + 25 * i, y - 5, 20, 50);
            else Main.parent.rect(x + 25 * i, y, 20, 40);
        }
    }

    @Override
    public void sound_init() {
        if (is_record) sample[count()] = ColorPalette.frec();
        if(!is_mute) {
            sine.setFrequency(ColorPalette.frec());
            sine.setAmplitude((float) 0.7);
        }
    }

    @Override
    public void process() {
        if (Main.parent.millis() % ms_p_qbeat < 50) {
            if (sample[count()] != 0) {
                sine.setFrequency(sample[count()]);
                sine.setAmplitude((float) 0.7);
            } else sine.setAmplitude(0);
        }
        sine.setAmplitude(sine.amplitude.getLastValue() * (float) 0.7);
        sine.setFrequency(sine.frequency.getLastValue() * (float) 0.6);
    }

    @Override
    public void sound_end() {
    }

    @Override
    public void key_judge() {
        switch (Main.parent.key) {
            case 'r':
                reset();
            case ' ':
                rec_start();
        }
    }
}
