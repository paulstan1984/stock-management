/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package views;

import controllers.Filter;
import controllers.StocManagementMain;
import helpers.MyTableCellRenderer;
import java.awt.Color;
import java.awt.Cursor;
import java.beans.Beans;
import javax.swing.JPanel;
import javax.swing.table.TableModel;
import models.Tproduse;
import models.Vcategorii;

/**
 *
 * @author ioapau
 */
public class RaportIOMonthWindow extends JPanel {
    
    private StocManagementMain controller = null;
    
    
    public RaportIOMonthWindow() {
        
        controller = StocManagementMain.getInstance();
        initComponents();
        
        lProduse.add(0, new Tproduse(Filter.INTAll));
        jCbxProduse.setSelectedIndex(Filter.INTAll);
        
        lCategories.add(0, new Vcategorii(Filter.STRAll));
        jCbxCategorii.setSelectedIndex(0);
        
        if (!Beans.isDesignTime()) {
            entityManager.getTransaction().begin();                    
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        entityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("stoc-managementPU").createEntityManager();
        query = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT t FROM Vraporttotalmonth t");
        list = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(new java.util.LinkedList(query.getResultList()));
        controller.computeTotal(list);
        dConverter = new helpers.DateConverter();
        dRenderer = new helpers.DateRenderer();
        qProduse = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT T FROM Tproduse T");
        lProduse = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : qProduse.getResultList();
        qCategories = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT t FROM Vcategorii t");
        lCategories = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : qCategories.getResultList();
        doubleRedRenderer = new helpers.DoubleRenderer();
        doubleGreenRenderer = new helpers.DoubleRenderer();
        masterScrollPane = new javax.swing.JScrollPane();
        masterTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jtxtData2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtxtData1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jCbxProduse = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jCbxCategorii = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();

        FormListener formListener = new FormListener();

        dRenderer.setText("dateRenderer1");

        doubleRedRenderer.setText("doubleRenderer1");
        doubleRedRenderer.setMyColor(Color.RED);

        doubleGreenRenderer.setText("doubleRenderer1");
        doubleGreenRenderer.setMyColor(Color.BLUE);

        setPreferredSize(new java.awt.Dimension(750, 410));
        addAncestorListener(formListener);

        masterTable.setAutoCreateRowSorter(true);
        masterTable.getTableHeader().setReorderingAllowed(false);

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, list, masterTable);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${data}"));
        columnBinding.setColumnName("Data");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${denumire}"));
        columnBinding.setColumnName("Denumire");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${um}"));
        columnBinding.setColumnName("Um");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pretintrare}"));
        columnBinding.setColumnName("Pret Intrare");
        columnBinding.setColumnClass(Double.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${cantitateintrare}"));
        columnBinding.setColumnName("Cantitate Intrare");
        columnBinding.setColumnClass(Double.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pretiesire}"));
        columnBinding.setColumnName("Pret Iesire");
        columnBinding.setColumnClass(Double.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${cantitateiesire}"));
        columnBinding.setColumnName("Cantitate Iesire");
        columnBinding.setColumnClass(Double.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${stoc}"));
        columnBinding.setColumnName("Stoc");
        columnBinding.setColumnClass(Double.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pretiesire-pretintrare}"));
        columnBinding.setColumnName("Profit");
        columnBinding.setColumnClass(Double.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        masterScrollPane.setViewportView(masterTable);
        masterTable.setDefaultRenderer(Object.class, new MyTableCellRenderer());
        if (masterTable.getColumnModel().getColumnCount() > 0) {
            masterTable.getColumnModel().getColumn(0).setCellRenderer(dRenderer);
            masterTable.getColumnModel().getColumn(3).setCellRenderer(doubleGreenRenderer);
            masterTable.getColumnModel().getColumn(4).setCellRenderer(doubleGreenRenderer);
            masterTable.getColumnModel().getColumn(5).setCellRenderer(doubleRedRenderer);
            masterTable.getColumnModel().getColumn(6).setCellRenderer(doubleRedRenderer);
            masterTable.getColumnModel().getColumn(7).setCellRenderer(doubleGreenRenderer);
            masterTable.getColumnModel().getColumn(8).setCellRenderer(null);
        }

        jButton1.setText("Aplica Filtre");
        jButton1.addActionListener(formListener);

        jLabel1.setText("Pana la Data:");

        jLabel2.setText("De la Data:");

        jLabel4.setText("Produs:");

        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, lProduse, jCbxProduse);
        bindingGroup.addBinding(jComboBoxBinding);

        jLabel3.setText("Categoria:");

        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, lCategories, jCbxCategorii);
        bindingGroup.addBinding(jComboBoxBinding);

        jCbxCategorii.addActionListener(formListener);

        jButton2.setText("Genereaza HTML");
        jButton2.addActionListener(formListener);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(masterScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCbxCategorii, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtxtData2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jtxtData1)
                            .addComponent(jCbxProduse, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtxtData1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtxtData2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jCbxCategorii, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCbxProduse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(masterScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                .addContainerGap())
        );

        bindingGroup.bind();
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener, javax.swing.event.AncestorListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == jButton1) {
                RaportIOMonthWindow.this.jButton1ActionPerformed(evt);
            }
            else if (evt.getSource() == jCbxCategorii) {
                RaportIOMonthWindow.this.jCbxCategoriiActionPerformed(evt);
            }
            else if (evt.getSource() == jButton2) {
                RaportIOMonthWindow.this.jButton2ActionPerformed(evt);
            }
        }

        public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            if (evt.getSource() == RaportIOMonthWindow.this) {
                RaportIOMonthWindow.this.formAncestorAdded(evt);
            }
        }

        public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
        }

        public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
        }
    }// </editor-fold>//GEN-END:initComponents

    

   
    private void formAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_formAncestorAdded

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        this.query = Filter.getRaportIOMonthQuery(entityManager, jCbxCategorii, jCbxProduse, jtxtData1, jtxtData2);
        controller.refreshList(list, entityManager, query);
        controller.computeTotal(list);
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        // TODO add your handling code here:
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        TableModel model = masterTable.getModel();
        controller.displayHTML(model);
        
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jCbxCategoriiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbxCategoriiActionPerformed
        
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        // TODO add your handling code here:
        if(jCbxCategorii.getSelectedItem()!=null && controller!=null){
            if(entityManager.getTransaction().isActive()){
                qProduse = Filter.getProductsQuery(entityManager, jCbxCategorii);
                controller.refreshList(lProduse, entityManager, qProduse);
                
                lProduse.add(0, new Tproduse(Filter.INTAll));
                jCbxProduse.setSelectedIndex(0);
                jCbxProduse.revalidate();
            }
         
        }
        
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jCbxCategoriiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private helpers.DateConverter dConverter;
    private helpers.DateRenderer dRenderer;
    private helpers.DoubleRenderer doubleGreenRenderer;
    private helpers.DoubleRenderer doubleRedRenderer;
    private javax.persistence.EntityManager entityManager;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jCbxCategorii;
    private javax.swing.JComboBox jCbxProduse;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jtxtData1;
    private javax.swing.JTextField jtxtData2;
    private java.util.List lCategories;
    private java.util.List lProduse;
    private java.util.List<models.Vraporttotalmonth> list;
    private javax.swing.JScrollPane masterScrollPane;
    private javax.swing.JTable masterTable;
    private javax.persistence.Query qCategories;
    private javax.persistence.Query qProduse;
    private javax.persistence.Query query;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
    
}