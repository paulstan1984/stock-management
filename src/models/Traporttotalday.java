/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ioapau
 */
@Entity
@Table(name = "TRAPORTTOTALDAY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Traporttotalday.findAll", query = "SELECT t FROM Traporttotalday t"),
    @NamedQuery(name = "Traporttotalday.findByData", query = "SELECT t FROM Traporttotalday t WHERE t.traporttotaldayPK.data = :data"),
    @NamedQuery(name = "Traporttotalday.findByIdprodus", query = "SELECT t FROM Traporttotalday t WHERE t.traporttotaldayPK.idprodus = :idprodus"),
    @NamedQuery(name = "Traporttotalday.findByPretintrare", query = "SELECT t FROM Traporttotalday t WHERE t.pretintrare = :pretintrare"),
    @NamedQuery(name = "Traporttotalday.findByPretiesire", query = "SELECT t FROM Traporttotalday t WHERE t.pretiesire = :pretiesire"),
    @NamedQuery(name = "Traporttotalday.findByCantitateintrare", query = "SELECT t FROM Traporttotalday t WHERE t.cantitateintrare = :cantitateintrare"),
    @NamedQuery(name = "Traporttotalday.findByCantitateiesire", query = "SELECT t FROM Traporttotalday t WHERE t.cantitateiesire = :cantitateiesire"),
    @NamedQuery(name = "Traporttotalday.findByStoc", query = "SELECT t FROM Traporttotalday t WHERE t.stoc = :stoc")})
public class Traporttotalday implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TraporttotaldayPK traporttotaldayPK;
    @Basic(optional = false)
    @Column(name = "PRETINTRARE")
    private double pretintrare;
    @Basic(optional = false)
    @Column(name = "PRETIESIRE")
    private double pretiesire;
    @Basic(optional = false)
    @Column(name = "CANTITATEINTRARE")
    private double cantitateintrare;
    @Basic(optional = false)
    @Column(name = "CANTITATEIESIRE")
    private double cantitateiesire;
    @Basic(optional = false)
    @Column(name = "STOC")
    private double stoc;

    public Traporttotalday() {
    }

    public Traporttotalday(TraporttotaldayPK traporttotaldayPK) {
        this.traporttotaldayPK = traporttotaldayPK;
    }

    public Traporttotalday(TraporttotaldayPK traporttotaldayPK, double pretintrare, double pretiesire, double cantitateintrare, double cantitateiesire, double stoc) {
        this.traporttotaldayPK = traporttotaldayPK;
        this.pretintrare = pretintrare;
        this.pretiesire = pretiesire;
        this.cantitateintrare = cantitateintrare;
        this.cantitateiesire = cantitateiesire;
        this.stoc = stoc;
    }

    public Traporttotalday(Date data, int idprodus) {
        this.traporttotaldayPK = new TraporttotaldayPK(data, idprodus);
    }

    public TraporttotaldayPK getTraporttotaldayPK() {
        return traporttotaldayPK;
    }

    public void setTraporttotaldayPK(TraporttotaldayPK traporttotaldayPK) {
        this.traporttotaldayPK = traporttotaldayPK;
    }

    public double getPretintrare() {
        return pretintrare;
    }

    public void setPretintrare(double pretintrare) {
        this.pretintrare = pretintrare;
    }

    public double getPretiesire() {
        return pretiesire;
    }

    public void setPretiesire(double pretiesire) {
        this.pretiesire = pretiesire;
    }

    public double getCantitateintrare() {
        return cantitateintrare;
    }

    public void setCantitateintrare(double cantitateintrare) {
        this.cantitateintrare = cantitateintrare;
    }

    public double getCantitateiesire() {
        return cantitateiesire;
    }

    public void setCantitateiesire(double cantitateiesire) {
        this.cantitateiesire = cantitateiesire;
    }

    public double getStoc() {
        return stoc;
    }

    public void setStoc(double stoc) {
        this.stoc = stoc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (traporttotaldayPK != null ? traporttotaldayPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Traporttotalday)) {
            return false;
        }
        Traporttotalday other = (Traporttotalday) object;
        if ((this.traporttotaldayPK == null && other.traporttotaldayPK != null) || (this.traporttotaldayPK != null && !this.traporttotaldayPK.equals(other.traporttotaldayPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Traporttotalday[ traporttotaldayPK=" + traporttotaldayPK + " ]";
    }
    
}
