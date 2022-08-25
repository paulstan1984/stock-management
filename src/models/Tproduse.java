/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import controllers.Filter;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author ioapau
 */
@Entity
@Table(name = "TPRODUSE", catalog = "", schema = "BOGDAN")
@NamedQueries({
    @NamedQuery(name = "Tproduse.findAll", query = "SELECT t FROM Tproduse t ORDER BY t.id ASC"),
    @NamedQuery(name = "Tproduse.findById", query = "SELECT t FROM Tproduse t WHERE t.id = :id"),
    @NamedQuery(name = "Tproduse.findByDenumire", query = "SELECT t FROM Tproduse t WHERE t.denumire = :denumire"),
    @NamedQuery(name = "Tproduse.findByUm", query = "SELECT t FROM Tproduse t WHERE t.um = :um")})
public class Tproduse implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "DENUMIRE")
    private String denumire;
    @Column(name = "UM")
    private String um;
    @Column(name = "CATEGORIE")
    private String categorie;
    
    public Tproduse() {
    }
    
    public Tproduse(int id){
        this.setId(id);
    }

    public Tproduse(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        String oldDenumire = this.denumire;
        this.denumire = denumire;
        changeSupport.firePropertyChange("denumire", oldDenumire, denumire);
    }

    public String getUm() {
        return um;
    }

    public void setUm(String um) {
        String oldUm = this.um;
        this.um = um;
        changeSupport.firePropertyChange("um", oldUm, um);
    }
    
    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        String oldCategorie = this.categorie;
        this.categorie = categorie;
        changeSupport.firePropertyChange("categorie", oldCategorie, categorie);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tproduse)) {
            return false;
        }
        Tproduse other = (Tproduse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        if(this.getId()==Filter.INTAll){
            return Filter.STRAll;
        }
        else{
            return String.format("%s (%s)", this.getDenumire(), this.getUm());
        }        
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
   
}
