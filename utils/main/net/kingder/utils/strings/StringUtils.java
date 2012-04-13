package net.kingder.utils.strings;


public class StringUtils {
    public static String unite(String[] array){
        StringBuilder result = new StringBuilder();
        for( String str : array ){
              result.append( str );
        }
        return result.toString();
    }
}
