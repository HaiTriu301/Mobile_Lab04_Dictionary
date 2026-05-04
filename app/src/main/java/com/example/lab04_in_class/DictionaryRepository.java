package com.example.lab04_in_class;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DictionaryRepository {

    private final DatabaseHelper dbHelper;

    public DictionaryRepository(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    /**
     * Look up exact word. Returns the Word if found, null otherwise.
     */
    public Word findExactWord(String query) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Word result = null;

        Cursor cursor = db.query(
                DatabaseHelper.TABLE_WORDS,
                null,
                DatabaseHelper.COLUMN_WORD + " = ?",
                new String[]{query.toLowerCase().trim()},
                null, null, null
        );

        if (cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID));
            String word = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_WORD));
            String definition = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DEFINITION));
            result = new Word(id, word, definition);
        }

        cursor.close();
        return result;
    }

    /**
     * Search for words that contain the query as a substring.
     */
    public List<Word> findWordsContaining(String query) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<Word> results = new ArrayList<>();

        Cursor cursor = db.query(
                DatabaseHelper.TABLE_WORDS,
                null,
                DatabaseHelper.COLUMN_WORD + " LIKE ?",
                new String[]{"%" + query.toLowerCase().trim() + "%"},
                null, null,
                DatabaseHelper.COLUMN_WORD + " ASC"
        );

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID));
            String word = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_WORD));
            String definition = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DEFINITION));
            results.add(new Word(id, word, definition));
        }

        cursor.close();
        return results;
    }

    public void close() {
        dbHelper.close();
    }
}