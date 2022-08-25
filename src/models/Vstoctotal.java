/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ioapau
 */
@Entity
@Table(name = "VSTOCTOTAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vstoctotal.findAll", query = "SELECT v FROM Vstoctotal v"),
    @NamedQuery(name = "Vstoctotal.findByData", query = "SELECT v FROM Vstoctotal v WHERE v.data = :data"),
    @NamedQuery(name = "Vstoctotal.findByDenumire", query = "SELECT v FROM Vstoctotal v WHERE v.denumire = :denumire"),
    @NamedQuery(name = "Vstoctotal.findByUm", query = "SELECT v FROM Vstoctotal v WHERE v.um = :um"),
    @NamedQuery(name = "Vstoctotal.findByStoc", query = "SELECT v FROM Vstoctotal v WHERE v.stoc = :stoc")})
public class Vstoctotal implements Serializable {
    @Column(name = "CATEGORIE")
    private String categorie;
    @Basic(optional = false)
    @Id
    @Column(name = "IDPRODUS")
    private int idprodus;
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "DATA")
    private String data;
    @Column(name = "DENUMIRE")
    private String denumire;
    @Column(name = "UM")
    private String um;
    @Basic(optional = false)
    @Column(name = "STOC")
    private double stoc;

    public Vstoctotal() {
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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

    public double getStoc() {
        return stoc;
    }

    public void setStoc(double stoc) {
        this.stoc = stoc;
    }

    public int getIdprodus() {
        return idprodus;
    }

    public void setIdprodus(int idprodus) {
        this.idprodus = idprodus;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
    
}
