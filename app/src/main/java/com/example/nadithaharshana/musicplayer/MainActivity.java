package com.example.nadithaharshana.musicplayer;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<MusicHandler> arrayList;
    private customMusicAdapter Madapter;
    private ListView songListView;
    private MediaPlayer mediaplayer;
    private Context context;

    private class ViewHolder {
        ImageView play, next, pervious;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        songListView = (ListView) findViewById(R.id.song_list);
        arrayList = new ArrayList<>();
        arrayList.add(new MusicHandler("Idoraye Nagara Kone", "Amal Perera", R.raw.idorayenagarakone));
        arrayList.add(new MusicHandler("Mal Pita Mal", "Amal Perera Cover", R.raw.malpitamal));
        arrayList.add(new MusicHandler("Ran Meevitha", "Amal Perera", R.raw.ranmeevitha));
        arrayList.add(new MusicHandler("Sanda Midulata Enawa", "Amal Perera Cover", R.raw.sandamidulataenawa));

        Madapter = new customMusicAdapter(this, R.layout.music_item, arrayList);
        songListView.setAdapter(Madapter);
       // music(viewholder);

    }


    public void music(ViewHolder vh) {
        if (mediaplayer.isPlaying()){
            vh.play.setImageResource(R.drawable.pause);
        }

        vh.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaplayer.isPlaying()){
                    mediaplayer.pause();
                }
            }
        });
    }

}
