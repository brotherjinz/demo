package com.web.util;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

public class GsonUtil {

    public static Gson create() {
        GsonBuilder gb = new GsonBuilder();
//        gb.registerTypeAdapter(java.util.Date.class, new DateSerializer()).setDateFormat(DateFormat.LONG);
//      gb.registerTypeAdapter(java.util.Date.class, new DateDeserializer()).setDateFormat(DateFormat.LONG);
        gb.setDateFormat("yyyy-MM-dd HH:mm:ss");
        Gson gson = gb.create();
        return gson;
    }
    
    public static String objectToDecodedString(Object object) {
    	GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(String.class, new TypeAdapter<String>() {

			@Override
			public void write(JsonWriter out, String value) throws IOException {
				if (value == null) {
					// out.nullValue();
					out.value(""); // 序列化时将 null 转为 ""
				} else {
					out.value(value);
				}
			}

			@Override
			public String read(JsonReader in) throws IOException {
				if (in.peek() == JsonToken.NULL) {
					in.nextNull();
					return null;
				}
				// return in.nextString();
				String str = in.nextString();
				if (str.equals("")) { // 反序列化时将 "" 转为 null
					return null;
				} else {
					return str;
				}
			}

		});

		Gson gson = gsonBuilder.create();
		String s = decodeUnicode(gson.toJson(object));
		return s;
    }
    
    /**
     * json字符串转成对象
     * @param str  
     * @param type
     * @return 
     */
    public static <T> T objectFromJson(String str, Type type) {
        Gson gson = new Gson();
        return gson.fromJson(str, type);
    }
    
    private static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                            value = (value << 4) + aChar - '0';
                            break;
                        case 'a':
                        case 'b':
                        case 'c':
                        case 'd':
                        case 'e':
                        case 'f':
                            value = (value << 4) + 10 + aChar - 'a';
                            break;
                        case 'A':
                        case 'B':
                        case 'C':
                        case 'D':
                        case 'E':
                        case 'F':
                            value = (value << 4) + 10 + aChar - 'A';
                            break;
                        default:
                            throw new IllegalArgumentException(
                                    "Malformed   \\uxxxx   encoding.");
                        }

                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }
	
}
