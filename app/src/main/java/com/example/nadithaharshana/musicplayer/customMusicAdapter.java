package com.example.nadithaharshana.musicplayer;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Naditha Harshana on 3/21/2018.
 */

public class customMusicAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<MusicHandler> arrayList;
    private MediaPlayer mediaplayer;
    private boolean isSongSet = false;
    private boolean isPaused = false;

    public customMusicAdapter(Context context, int layout, ArrayList<MusicHandler> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
        TextView txtName, txtSinger;
        ImageView imageViewPlay, imageViewStop, ivmusic, paly;
        ListView lv;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        final ViewHolder viewholder;
        if (convertView == null) {
            viewholder = new ViewHolder();
            LayoutInflater layoutinflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            convertView = layoutinflater.inflate(layout, null);
            viewholder.txtName = (TextView) convertView.findViewById(R.id.txtName);
            viewholder.txtSinger = (TextView) convertView.findViewById(R.id.txtSinger);
            viewholder.imageViewPlay = (ImageView) convertView.findViewById(R.id.imageViewPlay);
            viewholder.imageViewStop = (ImageView) convertView.findViewById(R.id.imageViewStop);
            viewholder.ivmusic = (ImageView) convertView.findViewById(R.id.ivMusic);
            viewholder.lv = (ListView) convertView.findViewById(R.id.song_list);
            viewholder.paly = (ImageView) convertView.findViewById(R.id.playUni);

            convertView.setTag(viewholder);
        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }

        final MusicHandler music = arrayList.get(position);

        viewholder.txtName.setText(music.getName());
        viewholder.txtSinger.setText(music.getSinger());

        // play music
        viewholder.imageViewPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!isSongSet) {
                    mediaplayer = MediaPlayer.create(context, music.getSong());
                    isSongSet = true;
                    mediaplayer.setVolume(50, 50);
                    mediaplayer.start();
                    viewholder.imageViewPlay.setImageResource(R.drawable.pause);
                    viewholder.ivmusic.setImageResource(R.drawable.playing);
                    viewholder.paly.setImageResource(R.drawable.pause);
                } else {
                    if (isPaused) {
                        mediaplayer.start();
                        viewholder.imageViewPlay.setImageResource(R.drawable.pause);
                        viewholder.paly.setImageResource(R.drawable.pause);
                        viewholder.ivmusic.setImageResource(R.drawable.playing);
                        isPaused = false;
                    } else {
                        mediaplayer.pause();
                        viewholder.imageViewPlay.setImageResource(R.drawable.play);
                        viewholder.paly.setImageResource(R.drawable.play);
                        viewholder.ivmusic.setImageResource(R.drawable.music);
                        isPaused = true;
                    }
                }

            }
        });




        viewholder.imageViewStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stop(viewholder);
                viewholder.ivmusic.setImageResource(R.drawable.music);
            }
        });
        return convertView;
    }




    public void stop(ViewHolder viewholder){
        if (isSongSet) {
            mediaplayer.stop();
            mediaplayer.release();
            isSongSet = false;
            viewholder.imageViewPlay.setImageResource(R.drawable.play);
        }
    }

}
