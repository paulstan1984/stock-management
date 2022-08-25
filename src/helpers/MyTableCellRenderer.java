/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package helpers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author ioapau
 */
public class MyTableCellRenderer extends DefaultTableCellRenderer {
    
    
    public MyTableCellRenderer(){
        super();
    }
    
    public Component getTableCellRendererComponent(JTable table,   
        Object value, boolean isSelected, boolean hasFocus, int row, int column) {  
     
        JLabel parent = (JLabel) super.getTableCellRendererComponent(table,   
            value, isSelected, hasFocus, row, column);  
        if(row == table.getRowCount()-1) {
            parent.setFont(parent.getFont().deriveFont(Font.BOLD));  
            setForeground(Color.red); 
        }
        else{
            setForeground(Color.black); 
        }
        
        return parent;  
   } 
}
