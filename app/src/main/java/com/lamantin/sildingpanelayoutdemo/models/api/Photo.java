package com.lamantin.sildingpanelayoutdemo.models.api;


import android.content.ContentValues;
import android.database.Cursor;
import android.os.Build;

import com.google.auto.value.AutoValue;
import com.lamantin.sildingpanelayoutdemo.db.Db;
import com.lamantin.sildingpanelayoutdemo.models.api.dto.PhotoDTO;

import java.util.Date;

import rx.functions.Func1;

@AutoValue
public abstract class Photo {

    public static final String QUERY_PHOTO_BY_ALBUM =
            "SELECT * FROM " + Photo.TABLE + " WHERE " + Photo.ALBUM_ID + "=?";

    public static final String QUERY_PHOTO_FROM__HISTORY =
            "SELECT * FROM " + Photo.HISTORY_TABLE;

    public static final String _ID = "_id";
    public static final String ID = "id";
    public static final String ALBUM_ID = "album_id";
    public static final String TITLE = "title";
    public static final String URL = "url";
    public static final String THUMBNAIL = "thumbnail";
    public static final String DATE = "date";
    public static final String TABLE = "photos";
    public static final String HISTORY_TABLE = "photos_history";

    public static Photo fromDTO(PhotoDTO photoDTO) {
        return new AutoValue_Photo(photoDTO.id, photoDTO.thumbnailUrl,
                photoDTO.title, photoDTO.url);
    }

    public abstract long id();
    public abstract String thumbnail();
    public abstract String title();
    public abstract String url();

    public static final class Builder {
        private final ContentValues values = new ContentValues();

        public Builder id(long id) {
            values.put(ID, id);
            return this;
        }

        public Builder albumId(long albumId) {
            values.put(ALBUM_ID, albumId);
            return this;
        }

        public Builder title(String title) {
            values.put(TITLE, title);
            return this;
        }

        public Builder url(String url) {
            values.put(URL, url);
            return this;
        }

        public Builder thumbnail(String thumbnail) {
            values.put(THUMBNAIL, thumbnail);
            return this;
        }

        public Builder date(Date date) {
            values.put(DATE, date.getTime());
            return this;
        }

        public ContentValues build() {
            return values;
        }
    }

    public static final Func1<Cursor, Photo> MAPPER = cursor -> {
        long id = Db.getLong(cursor, Photo.ID);
        String title = Db.getString(cursor, Photo.TITLE);
        String url = Db.getString(cursor, Photo.URL);
        String thumbnail = Db.getString(cursor, Photo.THUMBNAIL);
        return new AutoValue_Photo(id, thumbnail, title, url);
    };
}
