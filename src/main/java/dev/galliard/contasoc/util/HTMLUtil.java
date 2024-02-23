package dev.galliard.contasoc.util;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class HTMLUtil {
    public static String strong(String text) {
        return "<strong>" + text + "</strong>";
    }

    public static String italic(String text) {
        return "<em>" + text + "</em>";
    }

    public static String underline(String text) {
        return "<u>" + text + "</u>";
    }

    public static String reset(String text) {
        return text.replaceAll("<[^>]*>", "");
    }

    public static String color(String text, String color) {
        return "<span style='color: " + color + "'>" + text + "</span>";
    }

    public static String font(String text, String font) {
        return "<span style='font-family: " + font + "'>" + text + "</span>";
    }

    public static String size(String text, int size) {
        return "<span style='font-size: " + size + "px'>" + text + "</span>";
    }

    public static String hr() {
        return "<hr>";
    }

    static class WebColors {
        private static final Map<String, String> colorMap = new HashMap<>();
        static {
            colorMap.put("aliceblue", "#F0F8FF");
            colorMap.put("antiquewhite", "#FAEBD7");
            colorMap.put("aqua", "#00FFFF");
            colorMap.put("aquamarine", "#7FFFD4");
            colorMap.put("azure", "#F0FFFF");
            colorMap.put("beige", "#F5F5DC");
            colorMap.put("bisque", "#FFE4C4");
            colorMap.put("black", "#000000");
            colorMap.put("blanchedalmond", "#FFEBCD");
            colorMap.put("blue", "#0000FF");
            colorMap.put("blueviolet", "#8A2BE2");
            colorMap.put("brown", "#A52A2A");
            colorMap.put("burlywood", "#DEB887");
            colorMap.put("cadetblue", "#5F9EA0");
            colorMap.put("chartreuse", "#7FFF00");
            colorMap.put("chocolate", "#D2691E");
            colorMap.put("coral", "#FF7F50");
            colorMap.put("cornflowerblue", "#6495ED");
            colorMap.put("cornsilk", "#FFF8DC");
            colorMap.put("crimson", "#DC143C");
        }
        public static String getColorCode(String colorName) {
            return colorMap.get(colorName.toLowerCase());
        }
    }

    static class WebFonts {
        private static final Map<String, String> fontMap = new HashMap<>();
        static {
            fontMap.put("arial", "Arial, sans-serif");
            fontMap.put("courier", "Courier New, monospace");
            fontMap.put("georgia", "Georgia, serif");
            fontMap.put("helvetica", "Helvetica, sans-serif");
            fontMap.put("times", "Times New Roman, serif");
            fontMap.put("verdana", "Verdana, sans-serif");
        }
        public static String getFontCode(String fontName) {
            return fontMap.get(fontName.toLowerCase());
        }
    }
}
