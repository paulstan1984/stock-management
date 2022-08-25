/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package helpers;

import controllers.StocManagementMain;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.DefaultTableCellRenderer;
import models.Tproduse;

/**
 *
 * @author ioapau
 */
public class ProdusRenderer extends DefaultTableCellRenderer {
    
    private static Map<Object, Tproduse> loadedProducts;
    
    public ProdusRenderer(){
        super();
    }
    
    public static void clearLoadedProducts(){
        if (loadedProducts!=null) {
            loadedProducts.clear();
        }
    }
    
    @Override
    public void setValue(Object value) {
        if (loadedProducts==null) {
            loadedProducts = new HashMap<Object, Tproduse>();
        }
        
        String text = "";
        if(value != null){
            Tproduse p = null;
            if(!loadedProducts.containsKey(value)){
                StocManagementMain controller = StocManagementMain.getInstance();
                p = (Tproduse)controller.getById(Tproduse.class, (int)value);
                loadedProducts.put(value, p);
            }
            else{
                p = loadedProducts.get(value);
            }

            if(p!=null){
                text = String.format("%s (%s)", p.getDenumire(), p.getUm());
            }
        }
        
        setText(text);
    }
}
