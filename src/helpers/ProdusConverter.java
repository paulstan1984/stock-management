/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package helpers;

import controllers.StocManagementMain;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import models.Tproduse;
import org.jdesktop.beansbinding.Converter;

/**
 *
 * @author ioapau
 */
public class ProdusConverter extends Converter {
    
    private static Map<Object, Tproduse> loadedProducts;
    
    public ProdusConverter(){
        super();
    }
    
    public static void clearLoadedProducts(){
        if (loadedProducts!=null) {
            loadedProducts.clear();
        }
    }
    
    @Override
    public Object convertForward(Object value) {
        if (loadedProducts==null) {
            loadedProducts = new HashMap<Object, Tproduse>();
        }
        
        Tproduse p = null;
        if(!loadedProducts.containsKey(value)){
            StocManagementMain controller = StocManagementMain.getInstance();
            p = (Tproduse)controller.getById(Tproduse.class, (int)value);
            loadedProducts.put(value, p);
        }
        else{
            p = loadedProducts.get(value);
        }
        
        return p;
    }

    @Override
    public Object convertReverse(Object value) {
        Tproduse p = (Tproduse)value;
        return p.getId();
    }
}
