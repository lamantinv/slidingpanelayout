package com.lamantin.sildingpanelayoutdemo.models.api;


import android.content.ContentValues;
import android.database.Cursor;

import com.google.auto.value.AutoValue;
import com.lamantin.sildingpanelayoutdemo.db.Db;
import com.lamantin.sildingpanelayoutdemo.models.api.dto.AlbumDTO;

import java.io.Serializable;

import rx.functions.Func1;

@AutoValue
public abstract class Album implements Serializable {

    public static final String TABLE = "albums";
    public static final String _ID = "_id";
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String QUERY_ALL = "SELECT * FROM " + TABLE;

    public static final Func1<Cursor, Album> MAPPER = cursor -> {
        long id = Db.getLong(cursor, Album.ID);
        String title = Db.getString(cursor, Album.TITLE);
        return new AutoValue_Album(id, title);
    };

    public static Album fromDto(AlbumDTO albumDTO) {
        return new AutoValue_Album(albumDTO.id, albumDTO.title);
    }

    public abstract long id();

    public abstract String title();

    public static final class Builder {
        private final ContentValues values = new ContentValues();

        public Builder _id(long id) {
            values.put(_ID, id);
            return this;
        }

        public Builder id(long id) {
            values.put(ID, id);
            return this;
        }

        public Builder title(String title) {
            values.put(TITLE, title);
            return this;
        }

        public ContentValues build() {
            return values;
        }
    }
}
