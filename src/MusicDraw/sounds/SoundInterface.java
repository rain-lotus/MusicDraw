package MusicDraw.sounds;

import MusicDraw.Main;

public interface SoundInterface {
    //--基本的な数--//
    int base_beat_quantity = 8;              //ループさせる拍数
    float ms_p_beat = 60000 / Main.tempo;    //1拍あたりのms
    float ms_p_qbeat = ms_p_beat / 4;  //1/4拍あたりのms
    float loop_time = ms_p_beat * base_beat_quantity;
    void disp(int x, int y);
    void sound_init();
    void process();
    void sound_end();
    void key_judge();
    void key_end();
}
