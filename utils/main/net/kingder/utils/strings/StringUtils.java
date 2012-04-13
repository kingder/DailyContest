package net.kingder.utils.strings;

/**
 * Created by IntelliJ IDEA.
 * User: Weichao Luo
 * Date: 12-2-24
 * Time: обнГ4:53
 * To change this template use File | Settings | File Templates.
 */
public class StringUtils {
    public static String unite(String[] array){
        StringBuilder result = new StringBuilder();
        for( String str : array ){
              result.append( str );
        }
        return result.toString();
    }
}
