/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package helpers;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author ioapau
 */
public class DoubleRenderer extends DefaultTableCellRenderer {
    
    private static DecimalFormat formatter;
    private Color myColor = null;
    
    public DoubleRenderer(){
        super();
        
        formatter = new DecimalFormat("0.00##");        
    }
    
    public void setMyColor(Color c){
        myColor=c;
    }
    
    @Override
    public void setValue(Object value) {
        setText(formatValue(value));
        if(myColor!=null){
            setForeground(myColor);
        }
    }
    
    public String formatValue(Object value) {
        if(value!=null){
            return formatter.format(value);
        }
        else{
            return "";
        }
    }
}
