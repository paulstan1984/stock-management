/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import modelinterfaces.RaportIOEntity;

/**
 *
 * @author ioapau
 */
@Entity
@Table(name = "VRAPORTTOTALMONTH")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vraporttotalmonth.findAll", query = "SELECT v FROM Vraporttotalmonth v"),
    @NamedQuery(name = "Vraporttotalmonth.findByData", query = "SELECT v FROM Vraporttotalmonth v WHERE v.data = :data"),
    @NamedQuery(name = "Vraporttotalmonth.findByIdprodus", query = "SELECT v FROM Vraporttotalmonth v WHERE v.idprodus = :idprodus"),
    @NamedQuery(name = "Vraporttotalmonth.findByDenumire", query = "SELECT v FROM Vraporttotalmonth v WHERE v.denumire = :denumire"),
    @NamedQuery(name = "Vraporttotalmonth.findByUm", query = "SELECT v FROM Vraporttotalmonth v WHERE v.um = :um"),
    @NamedQuery(name = "Vraporttotalmonth.findByPretintrare", query = "SELECT v FROM Vraporttotalmonth v WHERE v.pretintrare = :pretintrare"),
    @NamedQuery(name = "Vraporttotalmonth.findByPretiesire", query = "SELECT v FROM Vraporttotalmonth v WHERE v.pretiesire = :pretiesire"),
    @NamedQuery(name = "Vraporttotalmonth.findByCantitateintrare", query = "SELECT v FROM Vraporttotalmonth v WHERE v.cantitateintrare = :cantitateintrare"),
    @NamedQuery(name = "Vraporttotalmonth.findByCantitateiesire", query = "SELECT v FROM Vraporttotalmonth v WHERE v.cantitateiesire = :cantitateiesire"),
    @NamedQuery(name = "Vraporttotalmonth.findByStoc", query = "SELECT v FROM Vraporttotalmonth v WHERE v.stoc = :stoc")})
public class Vraporttotalmonth implements RaportIOEntity, Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "DATA")
    private String data;
    @Id
    @Column(name = "IDPRODUS")
    private Integer idprodus;
    @Column(name = "DENUMIRE")
    private String denumire;
    @Column(name = "UM")
    private String um;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRETINTRARE")
    private Double pretintrare;
    @Column(name = "PRETIESIRE")
    private Double pretiesire;
    @Column(name = "CANTITATEINTRARE")
    private Double cantitateintrare;
    @Column(name = "CANTITATEIESIRE")
    private Double cantitateiesire;
    @Column(name = "STOC")
    private Double stoc;

    public Vraporttotalmonth() {
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getIdprodus() {
        return idprodus;
    }

    public void setIdprodus(Integer idprodus) {
        this.idprodus = idprodus;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getUm() {
        return um;
    }

    public void setUm(String um) {
        this.um = um;
    }

    public Double getPretintrare() {
        return pretintrare;
    }

    public void setPretintrare(Double pretintrare) {
        this.pretintrare = pretintrare;
    }

    public Double getPretiesire() {
        return pretiesire;
    }

    public void setPretiesire(Double pretiesire) {
        this.pretiesire = pretiesire;
    }

    public Double getCantitateintrare() {
        return cantitateintrare;
    }

    public void setCantitateintrare(Double cantitateintrare) {
        this.cantitateintrare = cantitateintrare;
    }

    public Double getCantitateiesire() {
        return cantitateiesire;
    }

    public void setCantitateiesire(Double cantitateiesire) {
        this.cantitateiesire = cantitateiesire;
    }

    public Double getStoc() {
        return stoc;
    }

    public void setStoc(Double stoc) {
        this.stoc = stoc;
    }
    
}
