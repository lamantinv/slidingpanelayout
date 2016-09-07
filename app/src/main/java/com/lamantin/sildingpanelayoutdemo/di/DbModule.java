package com.lamantin.sildingpanelayoutdemo.di;


import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.lamantin.sildingpanelayoutdemo.App;
import com.lamantin.sildingpanelayoutdemo.db.DbOpenHelper;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.schedulers.Schedulers;

@Module
public final class DbModule {

    private static final String TAG = "DataBase";
    private final App app;

    public DbModule(App app) {
        this.app = app;
    }

    @Provides @Singleton SQLiteOpenHelper provideOpenHelper() {
        return new DbOpenHelper(app);
    }

    @Provides @Singleton SqlBrite provideSqlBrite() {
        return SqlBrite.create(message -> Log.d(TAG, message));
    }

    @Provides @Singleton BriteDatabase provideDatabase(SqlBrite sqlBrite, SQLiteOpenHelper helper) {
        BriteDatabase db = sqlBrite.wrapDatabaseHelper(helper, Schedulers.io());
        db.setLoggingEnabled(true);
        return db;
    }
}
