/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import helpers.DateConverter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JFrame;
import javax.swing.table.TableModel;
import modelinterfaces.RaportIOEntity;
import models.Tcalendar;
import models.Toperatii;
import models.Tproduse;
import models.Vraporttotalday;
import models.Vraporttotalmonth;
import models.Vstoctotal;
import views.IesiriWindow;
import views.MainWindow;
import views.IntrariWindow;
import views.ProduseWindow;
import views.RaportIODayWindow;
import views.RaportIOMonthWindow;
import views.RaportStocuriDetaliatWindow;

/**
 *
 * @author ioapau
 */
public class StocManagementMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        new MainWindow().setVisible(true);
    }
    
    private static StocManagementMain m_Instance = null;
    private String tRaportTotalDay = "TRAPORTTOTALDAY";
        
    private StocManagementMain(){}
    public static StocManagementMain getInstance(){
        if(m_Instance==null){
            m_Instance = new StocManagementMain();
            m_Instance.populateCalendar();
        }
        
        return m_Instance;
    }
    
    public EntityManager getEntityManager(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("stoc-managementPU");
        return factory.createEntityManager();
    }
           
    public void displayProduse(){
        
        JFrame wProduse = null;
        wProduse = new JFrame();
        wProduse.setContentPane(new ProduseWindow());
        wProduse.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        wProduse.setTitle("Produse");
        wProduse.pack();
        wProduse.setLocationRelativeTo(null);
        wProduse.setVisible(true);        
    }
    
    public void displayIntrari(){
        
        JFrame wIntrari = null;
        wIntrari = new JFrame();
        wIntrari.setContentPane(new IntrariWindow());
        wIntrari.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        wIntrari.setTitle("Intrari");
        wIntrari.pack();
        wIntrari.setLocationRelativeTo(null);
        wIntrari.setVisible(true);
    }
    
    public void displayIesiri(){
        
        JFrame wIesiri = null;
        wIesiri = new JFrame();
        wIesiri.setContentPane(new IesiriWindow());
        wIesiri.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        wIesiri.setTitle("Iesiri");
        wIesiri.pack();
        wIesiri.setLocationRelativeTo(null);
        wIesiri.setVisible(true);        
    }
    
    public void displayReportIODay(){
        
        JFrame wRIODay = null;
        wRIODay = new JFrame();
        wRIODay.setContentPane(new RaportIODayWindow());
        wRIODay.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        wRIODay.setTitle("Raport Intrari & Iesiri / Zi");
        wRIODay.pack();
        wRIODay.setLocationRelativeTo(null);
        wRIODay.setVisible(true);        
    }
    
    public void displayReportIOMonth(){
        
        JFrame wRIOMonth = null;
        wRIOMonth = new JFrame();
        wRIOMonth.setContentPane(new RaportIOMonthWindow());
        wRIOMonth.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        wRIOMonth.setTitle("Raport Intrari & Iesiri / Luna");
        wRIOMonth.pack();
        wRIOMonth.setLocationRelativeTo(null);
        wRIOMonth.setVisible(true);        
    }
    
    public void displayReportStocuriDetaliat(){
        
        JFrame wRIOMonth = null;
        wRIOMonth = new JFrame();
        wRIOMonth.setContentPane(new RaportStocuriDetaliatWindow());
        wRIOMonth.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        wRIOMonth.setTitle("Raport Stocuri Detaliat");
        wRIOMonth.pack();
        wRIOMonth.setLocationRelativeTo(null);
        wRIOMonth.setVisible(true);      
    }
    
    public int getNextId(String tableName){
        
        int nextId = 1;
        
        String QueryStr = String.format(Locale.ENGLISH, "SELECT MAX(ID) AS ID FROM %s", tableName);
        EntityManager entityManager = Persistence.createEntityManagerFactory("stoc-managementPU").createEntityManager();
        Query query = entityManager.createNativeQuery(QueryStr);
        Object max = query.getSingleResult();
        
        if(max==null){
            nextId = 1;
        }
        else{
            nextId = 1 + (int)max;
        }
        
        return nextId;
    }
    
    public Object getById(Class type, int id){
        
        Object result = null;
        
        try{
            String QueryStr = String.format(Locale.ENGLISH, "SELECT t FROM %s t WHERE t.id = :id", type.getSimpleName());

            EntityManager entityManager = Persistence.createEntityManagerFactory("stoc-managementPU").createEntityManager();
            Query query = entityManager.createQuery(QueryStr).
                    setParameter("id", id);

            result = query.getSingleResult();
        }
        catch(Exception ex){
            
        }
        
        return result;
    }
    
    public void refreshList(List<?> list, EntityManager entityManager, Query query){
        
        entityManager.getTransaction().rollback();        
        entityManager.getTransaction().begin();
        java.util.Collection data = query.getResultList();
        if(data!=null){
            for (Object entity : data) {
                entityManager.refresh(entity);
            }
            list.clear();
            list.addAll(data);
        }
    }
    
    public void computeTotal(List list){
        
        RaportIOEntity TotalLine = null;
        if(list!=null && list.size()>0){
            for(Object elem : list){
                if(TotalLine==null){
                    if(elem.getClass().equals(Vraporttotalmonth.class)){
                        TotalLine = new Vraporttotalmonth();
                        ((Vraporttotalmonth)TotalLine).setData(Filter.STRAll);
                    }
                    else{
                        TotalLine = new Vraporttotalday();
                        ((Vraporttotalday)TotalLine).setData(Filter.DATAALL);
                    }
                    
                    TotalLine.setPretiesire((double)0);
                    TotalLine.setPretintrare((double)0);
                    TotalLine.setCantitateiesire((double)0);
                    TotalLine.setCantitateintrare((double)0);
                    TotalLine.setStoc((double)0);
                }
                   
                RaportIOEntity line = (RaportIOEntity)elem;
                    
                double pretI = TotalLine.getPretintrare() + line.getPretintrare();
                double pretO = TotalLine.getPretiesire() + line.getPretiesire();
                TotalLine.setPretintrare(pretI);
                TotalLine.setPretiesire(pretO);  
                
                double cantitateI = TotalLine.getCantitateintrare() + line.getCantitateintrare();
                double cantitateO = TotalLine.getCantitateiesire()+ line.getCantitateiesire();
                TotalLine.setCantitateintrare(cantitateI);
                TotalLine.setCantitateiesire(cantitateO);  
                
                double stoc = TotalLine.getStoc() + line.getStoc();
                TotalLine.setStoc(stoc);
            }
            
            list.add(TotalLine);
        }
    }
    
    public void computeStocTotal(List list){
        
        Vstoctotal TotalLine = null;
        if(list!=null && list.size()>0){
            for(Object elem : list){
                if(TotalLine==null){
                    TotalLine = new Vstoctotal();
                    TotalLine.setData(Filter.STRAll);
                    TotalLine.setStoc((double)0);
                }
                   
                Vstoctotal line = (Vstoctotal)elem;
                    
                double stoc = TotalLine.getStoc() + line.getStoc();
                TotalLine.setStoc(stoc);
            }
            
            list.add(TotalLine);
        }
    }
    
    public void populateCalendar(){
        
        Calendar calendar = Calendar.getInstance();
        EntityManager manager = this.getEntityManager();
        manager.getTransaction().begin();
        for(int i=1; i<100; i++){
            
            if(manager.find(Tcalendar.class, calendar.getTime())==null){
                Tcalendar c = new Tcalendar();
                c.setData(calendar.getTime());
                manager.persist(c);
                System.out.println(calendar.getTime());
            }
            
            calendar.add(Calendar.DATE, -1);
        }
        manager.getTransaction().commit();        
    }
    
    public void displayHTML(TableModel model){
        
        try{
            SimpleDateFormat dFormatter = new SimpleDateFormat(DateConverter.Format);
            DecimalFormat nFormatter = new DecimalFormat("0.00##");  
            
            File f = new File("raport.html");
            FileWriter fw = new FileWriter(f);
            
            fw.write("<style>"); 
            fw.write(".datagrid table { border-collapse: collapse; text-align: left; width: 100%; } .datagrid {font: normal 12px/150% Arial, Helvetica, sans-serif; background: #fff; overflow: hidden; border: 1px solid #006699; -webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px; }.datagrid table td, .datagrid table th { padding: 3px 10px; }.datagrid table thead th {background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #006699), color-stop(1, #00557F) );background:-moz-linear-gradient( center top, #006699 5%, #00557F 100% );filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#006699', endColorstr='#00557F');background-color:#006699; color:#FFFFFF; font-size: 15px; font-weight: bold; border-left: 1px solid #0070A8; } .datagrid table thead th:first-child { border: none; }.datagrid table tbody td { color: #00496B; border-left: 1px solid #E1EEF4;font-size: 12px;font-weight: normal; }.datagrid table tbody .alt td { background: #E1EEF4; color: #00496B; }.datagrid table tbody td:first-child { border-left: none; }.datagrid table tbody tr:last-child td { border-bottom: none; }.datagrid table tfoot td div { border-top: 1px solid #006699;background: #E1EEF4;} .datagrid table tfoot td { padding: 0; font-size: 12px } .datagrid table tfoot td div{ padding: 2px; }.datagrid table tfoot td ul { margin: 0; padding:0; list-style: none; text-align: right; }.datagrid table tfoot  li { display: inline; }.datagrid table tfoot li a { text-decoration: none; display: inline-block;  padding: 2px 8px; margin: 1px;color: #FFFFFF;border: 1px solid #006699;-webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px; background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #006699), color-stop(1, #00557F) );background:-moz-linear-gradient( center top, #006699 5%, #00557F 100% );filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#006699', endColorstr='#00557F');background-color:#006699; }.datagrid table tfoot ul.active, .datagrid table tfoot ul a:hover { text-decoration: none;border-color: #006699; color: #FFFFFF; background: none; background-color:#00557F;}div.dhtmlx_window_active, div.dhx_modal_cover_dv { position: fixed !important; }");
            fw.write("</style>");
            
            fw.write("<div class=\"datagrid\">");
            fw.write("<table>");
            fw.write("<thead>");
            fw.write("<tr>");
            for(int i=0;i<model.getColumnCount();i++){
                fw.write("<th>");
                fw.write(model.getColumnName(i));
                fw.write("</th>");
            }
            fw.write("</tr>");
            fw.write("</thead>");
            
            fw.write("<tbody>");
            for(int i=0;i<model.getRowCount();i++){
                
                if(i%2==0){
                    fw.write("<tr>");
                }
                else{
                    fw.write("<tr class=\"alt\">");
                }
                for(int j=0;j<model.getColumnCount();j++){
                    fw.write("<td>");
                    if(model.getValueAt(i, j)!=null){
                        Object value = model.getValueAt(i, j);
                        
                        if(value.getClass().equals(Date.class)){
                            fw.write(dFormatter.format((Date)value));
                        }
                        else if(value.getClass().equals(DecimalFormat.class) 
                             || value.getClass().equals(Double.class)){
                             fw.write(nFormatter.format(value));
                        }
                        else{
                            fw.write(""+value);
                        }
                    }
                    else if(model.getColumnName(j).equals("Data")){
                        fw.write("TOTAL");
                    }
                    fw.write("</td>");
                }
                fw.write("</tr>");
            }
            fw.write("</tbody>");
            
            fw.write("</table>");
            fw.write("</div>");
            
            fw.close();            
            Desktop.getDesktop().open(f);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void updateRaport(){
        
        EntityManager manager = this.getEntityManager();
        manager.getTransaction().begin();
        
        manager.createNativeQuery("TRUNCATE TABLE TRAPORTTOTALDAY").executeUpdate();
        
        List<Toperatii> TOperatiiList = manager.createQuery("SELECT T FROM Toperatii T").getResultList();
        for(Toperatii T : TOperatiiList){
            if(T.getIdprodus()!=null){
                this.updateRaport(T, manager);
            }
        }
        
        //updates DATA_MONTH Field
        manager.createNativeQuery("UPDATE TCALENDAR\n" +
                "SET DATA_MONTH = SUBSTR(CAST(DATA AS VARCHAR(10)), 1, 7)").executeUpdate();
        manager.createNativeQuery("UPDATE TOPERATII\n" +
                "SET DATA_MONTH = SUBSTR(CAST(DATA AS VARCHAR(10)), 1, 7)").executeUpdate();
        manager.createNativeQuery("UPDATE TRAPORTTOTALDAY\n" +
                "SET DATA_MONTH = SUBSTR(CAST(DATA AS VARCHAR(10)), 1, 7)").executeUpdate();
        
        manager.getTransaction().commit();
    }
    
    public void updateRaport(Toperatii T, EntityManager entityManager){
        
        SimpleDateFormat dFormatter = new SimpleDateFormat(DateConverter.Format);
          
        //verific daca exista o linie la data respectiva cu produsul respectiv
        String QueryStr = String.format(Locale.ENGLISH, "SELECT DATA "
                + "FROM %s "
                + "WHERE DATA='%s' AND IDPRODUS=%d", tRaportTotalDay, dFormatter.format(T.getData()), T.getIdprodus());
        Query query = entityManager.createNativeQuery(QueryStr);
        query.setMaxResults(1);
        
        if(query.getResultList().isEmpty()){
            //nu exista, asa ca inserez
            QueryStr = this.getInsertRaportSQL(T, tRaportTotalDay);
            query = entityManager.createNativeQuery(QueryStr);
            query.executeUpdate();
        }
        else{
            //exista, asa ca voi face update
            QueryStr = this.getUpdateRaportRowSQL(T, tRaportTotalDay);
            query = entityManager.createNativeQuery(QueryStr);
            query.executeUpdate();
        }
        
        //setez stocul din ziua anterioara
        QueryStr = this.getUpdateRaportStocRowSQL(entityManager, T, tRaportTotalDay);
        query = entityManager.createNativeQuery(QueryStr);
        query.executeUpdate();
        
        //update Stoc
        QueryStr = this.getUpdateRaportStocSQL(entityManager, T, tRaportTotalDay);
        query = entityManager.createNativeQuery(QueryStr);
        query.executeUpdate();
    }
    
    private String getInsertRaportSQL(Toperatii T, String tableName){
        String QueryStr;
        SimpleDateFormat dFormatter = new SimpleDateFormat(DateConverter.Format);
        
        if(T.getTip().equals("Intrare")){
            QueryStr = String.format(Locale.ENGLISH, "INSERT INTO %s "
                + " (DATA, IDPRODUS, PRETINTRARE, CANTITATEINTRARE) "
                + " VALUES "
                + " ('%s', %d, %.2f, %.2f)", tableName, 
                dFormatter.format(T.getData()), T.getIdprodus(), T.getPret(), T.getCantitate());
        }
        else{
            QueryStr = String.format(Locale.ENGLISH, "INSERT INTO %s "
                + " (DATA, IDPRODUS, PRETIESIRE, CANTITATEIESIRE) "
                + " VALUES "
                + " ('%s', %d, %.2f, %.2f)", tableName, 
                dFormatter.format(T.getData()), T.getIdprodus(), T.getPret(), T.getCantitate());
        }
        
        return QueryStr;
    }
    
    private String getUpdateRaportRowSQL(Toperatii T, String tableName){
        String QueryStr;
        SimpleDateFormat dFormatter = new SimpleDateFormat(DateConverter.Format);
        
        if(T.getTip().equals("Intrare")){
            QueryStr = String.format(Locale.ENGLISH, "UPDATE %s "
                + " SET PRETINTRARE=PRETINTRARE+%.2f,"
                + " CANTITATEINTRARE=CANTITATEINTRARE+%.2f "
                + " WHERE DATA='%s' AND IDPRODUS=%d", tableName, 
                T.getPret(), T.getCantitate(), 
                dFormatter.format(T.getData()), T.getIdprodus());
        }
        else{
            QueryStr = String.format(Locale.ENGLISH, "UPDATE %s "
                + " SET PRETIESIRE=PRETIESIRE+%.2f,"
                + " CANTITATEIESIRE=CANTITATEIESIRE+%.2f "
                + " WHERE DATA='%s' AND IDPRODUS=%d", tableName, 
                T.getPret(), T.getCantitate(), 
                dFormatter.format(T.getData()), T.getIdprodus());
        }
        
        return QueryStr;
    }
    
    private String getUpdateRaportStocSQL(EntityManager entityManager, Toperatii T, String tableName){
        String QueryStr;
        SimpleDateFormat dFormatter = new SimpleDateFormat(DateConverter.Format);
        
        /*
        if(T.getTip().equals("Intrare")){
            QueryStr = String.format(Locale.ENGLISH, "UPDATE %s "
                + " SET STOC=STOC+%.2f"
                + " WHERE DATA>='%s' AND IDPRODUS=%d", tableName, 
                T.getCantitate(), dFormatter.format(T.getData()), T.getIdprodus());
        }
        else{
            QueryStr = String.format(Locale.ENGLISH, "UPDATE %s "
                + " SET STOC=STOC-%.2f"
                + " WHERE DATA>='%s' AND IDPRODUS=%d", tableName, 
                T.getCantitate(), dFormatter.format(T.getData()), T.getIdprodus());
        }
        */
        
        double cantitate = 0;
        QueryStr = String.format(Locale.ENGLISH, "SELECT SUM(CANTITATEINTRARE-CANTITATEIESIRE) AS CANTITATE"
            + " FROM %s"
            + " WHERE DATA='%s' AND IDPRODUS=%d", tableName, 
            dFormatter.format(T.getData()), T.getIdprodus());
        Object cantitateObj = entityManager.createNativeQuery(QueryStr).getSingleResult();
        if(cantitateObj!=null){
            cantitate = (double)cantitateObj;
        }
        
        QueryStr = String.format(Locale.ENGLISH, "UPDATE %s "
                + " SET STOC=STOC+%.2f"
                + " WHERE DATA>='%s' AND IDPRODUS=%d", tableName, 
                cantitate, dFormatter.format(T.getData()), T.getIdprodus());
        
        return QueryStr;
    }
    
    private String getUpdateRaportStocRowSQL(EntityManager entityManager, Toperatii T, String tableName){
        
        String QueryStr;
        SimpleDateFormat dFormatter = new SimpleDateFormat(DateConverter.Format);
        double stoc = 0;
        
        QueryStr = String.format(Locale.ENGLISH, "SELECT SUM(CANTITATEINTRARE-CANTITATEIESIRE) AS STOC"
            + " FROM %s"
            + " WHERE DATA<'%s' AND IDPRODUS=%d", tableName, 
            dFormatter.format(T.getData()), T.getIdprodus());
        Object stocObj = entityManager.createNativeQuery(QueryStr).getSingleResult();
        if(stocObj!=null){
            stoc = (double)stocObj;
        }
        
        QueryStr = String.format(Locale.ENGLISH, "UPDATE %s "
            + " SET STOC=%.2f"
            + " WHERE DATA='%s' AND IDPRODUS=%d", tableName, stoc,
            dFormatter.format(T.getData()), T.getIdprodus());
        
        return QueryStr;
    }
}
