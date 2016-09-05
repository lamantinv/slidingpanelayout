package com.lamantin.sildingpanelayoutdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.lamantin.sildingpanelayoutdemo.models.api.ApiProvider;
import com.lamantin.sildingpanelayoutdemo.models.api.dto.AlbumDTO;
import com.lamantin.sildingpanelayoutdemo.models.api.dto.PhotoDTO;
import com.lamantin.sildingpanelayoutdemo.other.utils.Constants;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ApiProvider.getApi(Constants.BASE_URL).getAlbumsByUser(1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(albumDTOs -> {
                for(AlbumDTO albumDTO : albumDTOs) {
                    Log.d(TAG, "albums: " + albumDTO.toString());
                }
            });
        ApiProvider.getApi(Constants.BASE_URL).getPhotosByAlbum(1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(photoDTOs -> {
                for(PhotoDTO photoDTO : photoDTOs) {
                    Log.d(TAG, "photos: " + photoDTO.toString());
                }
            });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
