package me.itxuye.gankdbinding.constant;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class DateDeserializer implements JsonDeserializer {

    private List<SimpleDateFormat> mDateFormatList;

    public DateDeserializer(String... patterns) {
        mDateFormatList = new ArrayList<>(patterns.length);
        for(String pattern : patterns) {
            mDateFormatList.add(new SimpleDateFormat(pattern, Locale.US));
        }
    }

    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
        for (SimpleDateFormat dateFormat : mDateFormatList) {
            try {
                return dateFormat.parse(json.getAsString());
            }catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
