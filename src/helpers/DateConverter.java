/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package helpers;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.jdesktop.beansbinding.Converter;

/**
 *
 * @author ioapau
 */
public class DateConverter extends Converter {
    
    public static String Format = "yyyy-MM-dd";
    private static SimpleDateFormat formatter;
    
    @Override
    public String convertForward(Object arg) {
        return new SimpleDateFormat(Format).format((Date)arg);
    }

    @Override
    public Date convertReverse(Object arg) {
        if (formatter==null) {
            formatter = new SimpleDateFormat(DateConverter.Format);
        }
        
        Date date;
        try {
            date = formatter.parse(""+arg);
        } catch (Exception ex) {
            ex.printStackTrace();
            date = null;
        }
        return date;
    }
}
