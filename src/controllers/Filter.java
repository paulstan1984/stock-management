/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import models.Tproduse;

/**
 *
 * @author ioapau
 */
public class Filter {
    
    static class WhereParam{
        public String fieldName;
        public String paramName;
        public String operation;
        public String tprefix = "T";
        public Object value;        
    }
    
    public static String STRAll = " - Toate - ";
    public static int INTAll = 0;
    public static Date DATAALL = null;
    public static int MAXRESULTS = 1000;
    
    public static Query getProductsQuery(EntityManager entityManager,  JComboBox cbx_categoria){
        
        Query returnQuery = entityManager.createQuery("SELECT T FROM Tproduse T");
        
        if(cbx_categoria!=null && cbx_categoria.getSelectedItem()!=null){
            String str_categoria = cbx_categoria.getSelectedItem().toString();
            
            if(!str_categoria.equals(Filter.STRAll)){
                returnQuery = entityManager.createQuery("SELECT T FROM Tproduse T WHERE T.categorie = :categorie");
                returnQuery.setParameter("categorie", cbx_categoria.getSelectedItem().toString());
            }
        }
        
        returnQuery.setMaxResults(MAXRESULTS);
        return returnQuery;
    }
    
    
    public static Query getIOQuery(String Tip,
            EntityManager entityManager,  
            JComboBox cbx_categoria,
            JComboBox cbx_produsul,
            JTextField txt_Data1,
            JTextField txt_Data2){
        
        String str_query = "SELECT T FROM Toperatii T JOIN Tproduse P ON T.idprodus=P.id WHERE T.tip='"+Tip+"' ";
        List<WhereParam> where = new ArrayList<WhereParam>();
        
        if(cbx_categoria!=null && cbx_categoria.getSelectedItem()!=null){
            String str_categoria = cbx_categoria.getSelectedItem().toString();
            
            if(!str_categoria.equals(Filter.STRAll)){
                WhereParam wp = new WhereParam();
                wp.fieldName = "categorie";
                wp.paramName = "categorie";
                wp.tprefix="P";
                wp.operation = "=";
                wp.value = str_categoria;
                
                where.add(wp);
            }
        }
        
        if(cbx_produsul!=null && cbx_produsul.getSelectedItem()!=null){
            Tproduse p = (Tproduse)cbx_produsul.getSelectedItem();
            
            if(p.getId()!=Filter.INTAll){
                WhereParam wp = new WhereParam();
                wp.fieldName = "idprodus";
                wp.paramName = "idprodus";
                wp.operation = "=";
                wp.value = p.getId();
                
                where.add(wp);
            }
        }
        
        if(!txt_Data1.getText().equals("")){
            
            try{
                WhereParam wp = new WhereParam();
                wp.fieldName = "data";
                wp.paramName = "data1";
                wp.operation = ">=";
                wp.value = new SimpleDateFormat("yyyy-MM-dd").parse(txt_Data1.getText());

                where.add(wp);
            }
            catch(Exception ex){}
        }
        
        if(!txt_Data2.getText().equals("")){
            
            try{
                WhereParam wp = new WhereParam();
                wp.fieldName = "data";
                wp.paramName = "data2";
                wp.operation = "<=";
                wp.value = new SimpleDateFormat("yyyy-MM-dd").parse(txt_Data2.getText());

                where.add(wp);
            }
            catch(Exception ex){}
        }
        
        if(where.size()>0){
            for(WhereParam p : where){
                str_query+= String.format(" AND %s.%s %s :%s", p.tprefix, p.fieldName, p.operation, p.paramName);
            }
        }
        
        Query returnQuery = entityManager.createQuery(str_query);
        if(where.size()>0){
            for(WhereParam p : where){
                returnQuery.setParameter(p.paramName, p.value);
            }
        }
        
        returnQuery.setMaxResults(MAXRESULTS);
        return returnQuery;
    }
    
    public static Query getRaportIODayQuery(
            EntityManager entityManager,  
            JComboBox cbx_categoria,
            JComboBox cbx_produsul,
            JTextField txt_Data1,
            JTextField txt_Data2){
        
        String str_query = "SELECT T FROM Vraporttotalday T JOIN Tproduse P ON T.idprodus=P.id WHERE 1 = 1 ";
        List<WhereParam> where = new ArrayList<WhereParam>();
        
        if(cbx_categoria!=null && cbx_categoria.getSelectedItem()!=null){
            String str_categoria = cbx_categoria.getSelectedItem().toString();
            
            if(!str_categoria.equals(Filter.STRAll)){
                WhereParam wp = new WhereParam();
                wp.fieldName = "categorie";
                wp.paramName = "categorie";
                wp.tprefix="P";
                wp.operation = "=";
                wp.value = str_categoria;
                
                where.add(wp);
            }
        }
        
        if(cbx_produsul!=null && cbx_produsul.getSelectedItem()!=null){
            Tproduse p = (Tproduse)cbx_produsul.getSelectedItem();
            
            if(p.getId()!=Filter.INTAll){
                WhereParam wp = new WhereParam();
                wp.fieldName = "idprodus";
                wp.paramName = "idprodus";
                wp.operation = "=";
                wp.value = p.getId();
                
                where.add(wp);
            }
        }
        
        if(!txt_Data1.getText().equals("")){
            
            try{
                WhereParam wp = new WhereParam();
                wp.fieldName = "data";
                wp.paramName = "data1";
                wp.operation = ">=";
                wp.value = new SimpleDateFormat("yyyy-MM-dd").parse(txt_Data1.getText());

                where.add(wp);
            }
            catch(Exception ex){}
        }
        
        if(!txt_Data2.getText().equals("")){
            
            try{
                WhereParam wp = new WhereParam();
                wp.fieldName = "data";
                wp.paramName = "data2";
                wp.operation = "<=";
                wp.value = new SimpleDateFormat("yyyy-MM-dd").parse(txt_Data2.getText());

                where.add(wp);
            }
            catch(Exception ex){}
        }
        
        if(where.size()>0){
            for(WhereParam p : where){
                str_query+= String.format(" AND %s.%s %s :%s", p.tprefix, p.fieldName, p.operation, p.paramName);
            }
        }
        
        Query returnQuery = entityManager.createQuery(str_query);
        if(where.size()>0){
            for(WhereParam p : where){
                returnQuery.setParameter(p.paramName, p.value);
            }
        }
        
        returnQuery.setMaxResults(MAXRESULTS);
        return returnQuery;
    }  
    
    public static Query getRaportIOMonthQuery(
            EntityManager entityManager,  
            JComboBox cbx_categoria,
            JComboBox cbx_produsul,
            JTextField txt_Data1,
            JTextField txt_Data2){
        
        String str_query = "SELECT T FROM Vraporttotalmonth T JOIN Tproduse P ON T.idprodus=P.id WHERE 1 = 1 ";
        List<WhereParam> where = new ArrayList<>();
        
        if(cbx_categoria!=null && cbx_categoria.getSelectedItem()!=null){
            String str_categoria = cbx_categoria.getSelectedItem().toString();
            
            if(!str_categoria.equals(Filter.STRAll)){
                WhereParam wp = new WhereParam();
                wp.fieldName = "categorie";
                wp.paramName = "categorie";
                wp.tprefix="P";
                wp.operation = "=";
                wp.value = str_categoria;
                
                where.add(wp);
            }
        }
        
        if(cbx_produsul!=null && cbx_produsul.getSelectedItem()!=null){
            Tproduse p = (Tproduse)cbx_produsul.getSelectedItem();
            
            if(p.getId()!=Filter.INTAll){
                WhereParam wp = new WhereParam();
                wp.fieldName = "idprodus";
                wp.paramName = "idprodus";
                wp.operation = "=";
                wp.value = p.getId();
                
                where.add(wp);
            }
        }
        
        if(!txt_Data1.getText().equals("")){
            
            try{
                WhereParam wp = new WhereParam();
                wp.fieldName = "data";
                wp.paramName = "data1";
                wp.operation = ">=";
                wp.value = txt_Data1.getText();

                where.add(wp);
            }
            catch(Exception ex){}
        }
        
        if(!txt_Data2.getText().equals("")){
            
            try{
                WhereParam wp = new WhereParam();
                wp.fieldName = "data";
                wp.paramName = "data2";
                wp.operation = "<=";
                wp.value = txt_Data2.getText();

                where.add(wp);
            }
            catch(Exception ex){}
        }
        
        if(where.size()>0){
            for(WhereParam p : where){
                str_query+= String.format(" AND %s.%s %s :%s", p.tprefix, p.fieldName, p.operation, p.paramName);
            }
        }
        
        Query returnQuery = entityManager.createQuery(str_query);
        if(where.size()>0){
            for(WhereParam p : where){
                returnQuery.setParameter(p.paramName, p.value);
            }
        }
        
        returnQuery.setMaxResults(MAXRESULTS);
        return returnQuery;
    }  
    
    public static Query getRaportStocuriTotalQuery(EntityManager entityManager,  
            JComboBox cbx_categoria,
            JComboBox cbx_produsul,
            JTextField txt_Data1,
            JTextField txt_Data2){
        
        String str_query = "SELECT T FROM Vstoctotal T WHERE 1=1 ";
        List<WhereParam> where = new ArrayList<>();
        
        if(cbx_categoria!=null && cbx_categoria.getSelectedItem()!=null){
            String str_categoria = cbx_categoria.getSelectedItem().toString();
            
            if(!str_categoria.equals(Filter.STRAll)){
                WhereParam wp = new WhereParam();
                wp.fieldName = "categorie";
                wp.paramName = "categorie";
                wp.tprefix="T";
                wp.operation = "=";
                wp.value = str_categoria;
                
                where.add(wp);
            }
        }
        
        if(cbx_produsul!=null && cbx_produsul.getSelectedItem()!=null){
            Tproduse p = (Tproduse)cbx_produsul.getSelectedItem();
            
            if(p.getId()!=Filter.INTAll){
                WhereParam wp = new WhereParam();
                wp.fieldName = "idprodus";
                wp.paramName = "idprodus";
                wp.operation = "=";
                wp.value = p.getId();
                
                where.add(wp);
            }
        }
        
        if(!txt_Data1.getText().equals("")){
            
            try{
                WhereParam wp = new WhereParam();
                wp.fieldName = "data";
                wp.paramName = "data1";
                wp.operation = ">=";
                wp.value = txt_Data1.getText();

                where.add(wp);
            }
            catch(Exception ex){}
        }
        
        if(!txt_Data2.getText().equals("")){
            
            try{
                WhereParam wp = new WhereParam();
                wp.fieldName = "data";
                wp.paramName = "data2";
                wp.operation = "<=";
                wp.value = txt_Data2.getText();

                where.add(wp);
            }
            catch(Exception ex){}
        }
        
        if(where.size()>0){
            for(WhereParam p : where){
                str_query+= String.format(" AND %s.%s %s :%s", p.tprefix, p.fieldName, p.operation, p.paramName);
            }
        }
        
        Query returnQuery = entityManager.createQuery(str_query);
        if(where.size()>0){
            for(WhereParam p : where){
                returnQuery.setParameter(p.paramName, p.value);
            }
        }
        
        returnQuery.setMaxResults(MAXRESULTS);
        return returnQuery;
    }
    
    public static Query getRaportStocuriDayQuery(
            EntityManager entityManager,  
            JComboBox cbx_categoria,
            JComboBox cbx_produsul,
            JTextField txt_Data1,
            JTextField txt_Data2){
        
        String str_query = "SELECT T FROM Vraportstocuriday T JOIN Tproduse P ON T.id=P.id WHERE 1 = 1 ";
        List<WhereParam> where = new ArrayList<WhereParam>();
        
        if(cbx_categoria!=null && cbx_categoria.getSelectedItem()!=null){
            String str_categoria = cbx_categoria.getSelectedItem().toString();
            
            if(!str_categoria.equals(Filter.STRAll)){
                WhereParam wp = new WhereParam();
                wp.fieldName = "categorie";
                wp.paramName = "categorie";
                wp.tprefix="P";
                wp.operation = "=";
                wp.value = str_categoria;
                
                where.add(wp);
            }
        }
        
        if(cbx_produsul!=null && cbx_produsul.getSelectedItem()!=null){
            Tproduse p = (Tproduse)cbx_produsul.getSelectedItem();
            
            if(p.getId()!=Filter.INTAll){
                WhereParam wp = new WhereParam();
                wp.fieldName = "id";
                wp.paramName = "id";
                wp.tprefix="P";
                wp.operation = "=";
                wp.value = p.getId();
                
                where.add(wp);
            }
        }
        
        if(!txt_Data1.getText().equals("")){
            
            try{
                WhereParam wp = new WhereParam();
                wp.fieldName = "data";
                wp.paramName = "data1";
                wp.operation = ">=";
                wp.value = new SimpleDateFormat("yyyy-MM-dd").parse(txt_Data1.getText());

                where.add(wp);
            }
            catch(Exception ex){}
        }
        
        if(!txt_Data2.getText().equals("")){
            
            try{
                WhereParam wp = new WhereParam();
                wp.fieldName = "data";
                wp.paramName = "data2";
                wp.operation = "<=";
                wp.value = new SimpleDateFormat("yyyy-MM-dd").parse(txt_Data2.getText());

                where.add(wp);
            }
            catch(Exception ex){}
        }
        
        if(where.size()>0){
            for(WhereParam p : where){
                str_query+= String.format(" AND %s.%s %s :%s", p.tprefix, p.fieldName, p.operation, p.paramName);
            }
        }
        
        Query returnQuery = entityManager.createQuery(str_query);
        if(where.size()>0){
            for(WhereParam p : where){
                returnQuery.setParameter(p.paramName, p.value);
            }
        }
        
        returnQuery.setMaxResults(MAXRESULTS);
        
        return returnQuery;
    }
    
    public static Query getRaportStocuriMonthQuery(
            EntityManager entityManager,  
            JComboBox cbx_categoria,
            JComboBox cbx_produsul,
            JTextField txt_Data1,
            JTextField txt_Data2){
        
        String str_query = "SELECT T FROM Vraportstocurimonth T JOIN Tproduse P ON T.id=P.id WHERE 1 = 1 ";
        List<WhereParam> where = new ArrayList<WhereParam>();
        
        if(cbx_categoria!=null && cbx_categoria.getSelectedItem()!=null){
            String str_categoria = cbx_categoria.getSelectedItem().toString();
            
            if(!str_categoria.equals(Filter.STRAll)){
                WhereParam wp = new WhereParam();
                wp.fieldName = "categorie";
                wp.paramName = "categorie";
                wp.tprefix="P";
                wp.operation = "=";
                wp.value = str_categoria;
                
                where.add(wp);
            }
        }
        
        if(cbx_produsul!=null && cbx_produsul.getSelectedItem()!=null){
            Tproduse p = (Tproduse)cbx_produsul.getSelectedItem();
            
            if(p.getId()!=Filter.INTAll){
                WhereParam wp = new WhereParam();
                wp.fieldName = "id";
                wp.paramName = "id";
                wp.tprefix="P";
                wp.operation = "=";
                wp.value = p.getId();
                
                where.add(wp);
            }
        }
        
        if(!txt_Data1.getText().equals("")){
            
            try{
                WhereParam wp = new WhereParam();
                wp.fieldName = "data";
                wp.paramName = "data1";
                wp.operation = ">=";
                wp.value = new SimpleDateFormat("yyyy-MM-dd").parse(txt_Data1.getText());

                where.add(wp);
            }
            catch(Exception ex){}
        }
        
        if(!txt_Data2.getText().equals("")){
            
            try{
                WhereParam wp = new WhereParam();
                wp.fieldName = "data";
                wp.paramName = "data2";
                wp.operation = "<=";
                wp.value = new SimpleDateFormat("yyyy-MM-dd").parse(txt_Data2.getText());

                where.add(wp);
            }
            catch(Exception ex){}
        }
        
        if(where.size()>0){
            for(WhereParam p : where){
                str_query+= String.format(" AND %s.%s %s :%s", p.tprefix, p.fieldName, p.operation, p.paramName);
            }
        }
        
        Query returnQuery = entityManager.createQuery(str_query);
        if(where.size()>0){
            for(WhereParam p : where){
                returnQuery.setParameter(p.paramName, p.value);
            }
        }
        
        returnQuery.setMaxResults(MAXRESULTS);
        
        return returnQuery;
    }
}
