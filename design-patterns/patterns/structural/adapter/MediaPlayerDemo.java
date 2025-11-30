package patterns.structural.adapter;
interface MediaPlayer {
    public void play(String fileName);
}

class VLCPlayer {
    void playVLC(String filename) {
        System.out.println("Playing VLC file: " + filename);

    }
}
class MP4Player {
    void playMP4(String filename) {
        System.out.println("Playing MP4 file: " + filename);

    }
}

class VLCApapter implements MediaPlayer {
    private final VLCPlayer player;

    public VLCApapter(VLCPlayer player) {
        this.player = player;
    }

    @Override
    public void play(String fileName) {
        player.playVLC(fileName);

    }
}

class MP4Apapter implements MediaPlayer {
    private final MP4Player player;
    public MP4Apapter(MP4Player player) {
        this.player = player;
    }

    @Override
    public void play(String fileName) {
        player.playMP4(fileName);

    }
}

public class MediaPlayerDemo {
    public static void main(String[] args) {
        MP4Player mp4Player = new MP4Player();
        VLCPlayer vlcPlayer = new VLCPlayer();

        MediaPlayer vlcplayer = new VLCApapter(vlcPlayer);
        vlcplayer.play("VLC File");
        MediaPlayer mp4player = new MP4Apapter(mp4Player);
        mp4player.play("MP4 File");
    }
}
