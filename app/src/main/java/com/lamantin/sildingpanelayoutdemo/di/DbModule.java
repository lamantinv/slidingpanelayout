package com.lamantin.sildingpanelayoutdemo.di;


import android.app.Application;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.lamantin.sildingpanelayoutdemo.db.DbOpenHelper;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import rx.schedulers.Schedulers;

@Module
public final class DbModule {

    private static final String TAG = "DataBase";

    @Provides @Singleton SQLiteOpenHelper provideOpenHelper(Application application) {
        return new DbOpenHelper(application);
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
