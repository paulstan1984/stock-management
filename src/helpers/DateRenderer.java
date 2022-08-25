/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package helpers;

import controllers.Filter;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author ioapau
 */
public class DateRenderer extends DefaultTableCellRenderer {
    
    private static SimpleDateFormat formatter;
    
    public DateRenderer(){
        super();
    }
    
    @Override
    public void setValue(Object value) {
        if(value == Filter.DATAALL || value.equals(Filter.STRAll)){
            setText("TOTAL");
            setForeground(Color.red); 
            
            setFont(getFont().deriveFont(Font.BOLD));
        }
        else{
            setForeground(Color.black);
            if (formatter==null) {
                formatter = new SimpleDateFormat(DateConverter.Format);
            }
            try{
                setText(formatter.format(value));
            }
            catch(Exception ex){
                setText(""+value);
            }
        }
    }
}
