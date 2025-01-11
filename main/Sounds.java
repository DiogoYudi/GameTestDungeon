package main;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sounds {
    Clip clip;
    URL soundUrl[] = new URL[30];
    FloatControl fc;
    int volumeScale = 3;
    float volume;

    public Sounds(){
        soundUrl[0] = getClass().getResource("/sounds/BlueBoyAdventure.wav");
        soundUrl[1] = getClass().getResource("/sounds/coin.wav");
        soundUrl[2] = getClass().getResource("/sounds/powerup.wav");
        soundUrl[3] = getClass().getResource("/sounds/unlock.wav");
        soundUrl[4] = getClass().getResource("/sounds/fanfare.wav");
        soundUrl[5] = getClass().getResource("/sounds/hitmonster.wav");
        soundUrl[6] = getClass().getResource("/sounds/receivedamage.wav");
        soundUrl[7] = getClass().getResource("/sounds/swingweapon.wav");
        soundUrl[8] = getClass().getResource("/sounds/levelup.wav");
        soundUrl[9] = getClass().getResource("/sounds/cursor.wav");
        soundUrl[10] = getClass().getResource("/sounds/burning.wav");
        soundUrl[11] = getClass().getResource("/sounds/cuttree.wav");
        soundUrl[12] = getClass().getResource("/sounds/gameover.wav");
        soundUrl[13] = getClass().getResource("/sounds/stairs.wav");
        soundUrl[14] = getClass().getResource("/sounds/sleep.wav");
        soundUrl[15] = getClass().getResource("/sounds/blocked.wav");
        soundUrl[16] = getClass().getResource("/sounds/parry.wav");
        soundUrl[17] = getClass().getResource("/sounds/speak.wav");
        soundUrl[18] = getClass().getResource("/sounds/Merchant.wav");
        soundUrl[19] = getClass().getResource("/sounds/Dungeon.wav");
        soundUrl[20] = getClass().getResource("/sounds/chipwall.wav");
        soundUrl[21] = getClass().getResource("/sounds/dooropen.wav");
        soundUrl[22] = getClass().getResource("/sounds/FinalBattle.wav");
    }

    public void setFile(int i){
        try {
            AudioInputStream sis = AudioSystem.getAudioInputStream(soundUrl[i]);
            clip = AudioSystem.getClip();
            clip.open(sis);
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
        } catch (Exception e) {}
    }

    public void play(){
        clip.start();
    }
    
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    public void stop(){
        clip.stop();
    }

    public void checkVolume(){
        switch (volumeScale) {
            case 0:
                volume = -80f;
                break;
            case 1:
                volume = -20f;
                break;
            case 2:
                volume = -12f;
                break;
            case 3:
                volume = -5f;
                break;
            case 4:
                volume = 1f;
                break;
            case 5:
                volume = 6f;
                break;
        }
        fc.setValue(volume);
    }
}
