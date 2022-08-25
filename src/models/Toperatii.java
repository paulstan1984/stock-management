/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author ioapau
 */
@Entity
@Table(name = "TOPERATII", catalog = "", schema = "BOGDAN")
@NamedQueries({
    @NamedQuery(name = "Toperatii.findAll", query = "SELECT t FROM Toperatii t"),
    @NamedQuery(name = "Toperatii.findById", query = "SELECT t FROM Toperatii t WHERE t.id = :id"),
    @NamedQuery(name = "Toperatii.findByTip", query = "SELECT t FROM Toperatii t WHERE t.tip = :tip"),
    @NamedQuery(name = "Toperatii.findByData", query = "SELECT t FROM Toperatii t WHERE t.data = :data"),
    @NamedQuery(name = "Toperatii.findByIdprodus", query = "SELECT t FROM Toperatii t WHERE t.idprodus = :idprodus"),
    @NamedQuery(name = "Toperatii.findByCantitate", query = "SELECT t FROM Toperatii t WHERE t.cantitate = :cantitate"),
    @NamedQuery(name = "Toperatii.findByPret", query = "SELECT t FROM Toperatii t WHERE t.pret = :pret")})
public class Toperatii implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "TIP")
    private String tip;
    @Column(name = "DATA")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Column(name = "IDPRODUS")
    private Integer idprodus;
    @Column(name = "CANTITATE")
    private Double cantitate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRET")
    private Double pret;

    public Toperatii() {
        this.cantitate = (double)0;
        this.pret = (double)0;
        this.tip = "Intrare";
    }

    public Toperatii(Integer id) {
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

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        String oldTip = this.tip;
        this.tip = tip;
        changeSupport.firePropertyChange("tip", oldTip, tip);
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        Date oldData = this.data;
        this.data = data;
        changeSupport.firePropertyChange("data", oldData, data);
    }

    public Integer getIdprodus() {
        return idprodus;
    }

    public void setIdprodus(Integer idprodus) {
        Integer oldIdprodus = this.idprodus;
        this.idprodus = idprodus;
        changeSupport.firePropertyChange("idprodus", oldIdprodus, idprodus);
    }

    public Double getCantitate() {
        return cantitate;
    }

    public void setCantitate(Double cantitate) {
        Double oldCantitate = this.cantitate;
        this.cantitate = cantitate;
        changeSupport.firePropertyChange("cantitate", oldCantitate, cantitate);
    }

    public Double getPret() {
        return pret;
    }

    public void setPret(Double pret) {
        Double oldPret = this.pret;
        this.pret = pret;
        changeSupport.firePropertyChange("pret", oldPret, pret);
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
        if (!(object instanceof Toperatii)) {
            return false;
        }
        Toperatii other = (Toperatii) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "userinterface.Toperatii[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
    public static String Intrare="Intrare";
    public static String Iesire="Iesire";
}
