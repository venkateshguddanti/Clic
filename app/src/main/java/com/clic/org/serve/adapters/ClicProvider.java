package com.clic.org.serve.adapters;

import android.app.SearchManager;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

import java.util.HashMap;

/**
 * Created by Venkatesh on 23-06-2016.
 */
public class ClicProvider extends ContentProvider {


    // Uri matcher to decode incoming URIs.
    private final UriMatcher mUriMatcher;

    private static final String AUTHORITY = ClicProvider.class.getName();

    // content mime types
    public static final String BASE_DATA_NAME = "clicdata";
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/com.clic.search." + BASE_DATA_NAME;
    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/com.clic.search." + BASE_DATA_NAME;

    // common URIs
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_DATA_NAME);
    public static final Uri SEARCH_SUGGEST_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_DATA_NAME + "/" + SearchManager.SUGGEST_URI_PATH_QUERY);

    // matcher
    private static final int PRODUCTS = 1; // The incoming URI matches the main table URI pattern
    private static final int PRODUCT_ID = 2; // The incoming URI matches the main table row ID URI pattern
    private static final int SEARCH_SUGGEST = 3;

    private static final HashMap<String, String> SEARCH_SUGGEST_PROJECTION_MAP;
    static {
        SEARCH_SUGGEST_PROJECTION_MAP = new HashMap<String, String>();
        SEARCH_SUGGEST_PROJECTION_MAP.put("_id", "_id");
        SEARCH_SUGGEST_PROJECTION_MAP.put(SearchManager.SUGGEST_COLUMN_TEXT_1, "item name" + " AS "   + SearchManager.SUGGEST_COLUMN_TEXT_1);
       }

    public ClicProvider() {
        // Create and initialize URI matcher.
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI(AUTHORITY, "Products", PRODUCTS);
        mUriMatcher.addURI(AUTHORITY, "Products" + "/#", PRODUCT_ID);

        // to get suggestions...
        mUriMatcher.addURI(AUTHORITY, SearchManager.SUGGEST_URI_PATH_QUERY, SEARCH_SUGGEST);
        mUriMatcher.addURI(AUTHORITY, SearchManager.SUGGEST_URI_PATH_QUERY + "/*", SEARCH_SUGGEST);
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
