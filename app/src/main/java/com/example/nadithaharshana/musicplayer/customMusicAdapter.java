package com.example.nadithaharshana.musicplayer;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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
    private boolean flag = true;
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
        ImageView imageViewPlay, imageViewStop;
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
                Log.i("from On click", "flag : " + flag + " isPuased : " + isPaused);
                if (flag && !isPaused) {
                    mediaplayer = MediaPlayer.create(context, music.getSong());
                    flag = false;
                    mediaplayer.setVolume(50, 50);
                    mediaplayer.start();
                    viewholder.imageViewPlay.setImageResource(R.drawable.pause);
                } else {
                    if (isPaused) {
                        mediaplayer.start();
                        viewholder.imageViewPlay.setImageResource(R.drawable.pause);
                        isPaused = false;
                    } else {
                        mediaplayer.pause();
                        viewholder.imageViewPlay.setImageResource(R.drawable.play);
                        isPaused = true;
                    }
                }

            }
        });

        viewholder.imageViewStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!flag) {
                    mediaplayer.stop();
                    mediaplayer.release();
                    flag = true;
                    viewholder.imageViewPlay.setImageResource(R.drawable.play);
                }

            }
        });
        return convertView;
    }
}
