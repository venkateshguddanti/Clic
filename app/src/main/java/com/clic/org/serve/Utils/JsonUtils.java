package com.clic.org.serve.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonUtils
{
    static int mCounter = 0;

    public static String getJsonString( Object obj )
    {
        Gson lGson = new GsonBuilder().create();
        return lGson.toJson( obj );
    }

    public static JsonObject getJsonObject( String obj )
    {
        JsonParser lParser = new JsonParser();
        return lParser.parse( obj ).getAsJsonObject();
    }

    public static JsonArray getJsonArray( String obj )
    {
        JsonParser lParser = new JsonParser();
        return lParser.parse( obj ).getAsJsonArray();
    }

    public static boolean isJsonValid( String json )
    {
        try
        {
            JsonParser lParser = new JsonParser();
            lParser.parse( json );
            return true;
        }
        catch( Exception e )
        {
            return false;
        }
    }

}
