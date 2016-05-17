package com.example.afanasenko.lesson5;

import android.content.Context;
import android.view.DragEvent;
import android.view.View;

/**
 * Created by Afanasenko on 15.05.2016.
 */
class MyDragGridListener implements View.OnDragListener  {
    public MyDragGridListener() {
        super();
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {

        switch (event.getAction()) {
            case DragEvent.ACTION_DROP:
                View droped = (View) event.getLocalState();
                droped.setVisibility(View.VISIBLE);
            default:
                break;
        }
        return true;
    }
}
