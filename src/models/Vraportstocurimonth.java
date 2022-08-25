/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ioapau
 */
@Entity
@Table(name = "VRAPORTSTOCURIMONTH")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vraportstocurimonth.findAll", query = "SELECT v FROM Vraportstocurimonth v")})
public class Vraportstocurimonth implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "DATA")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Id
    @Column(name = "ID")
    private int id;
    @Column(name = "DENUMIRE")
    private String denumire;
    @Column(name = "UM")
    private String um;
    @Column(name = "CATEGORIE")
    private String categorie;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "STOC")
    private Double stoc;

    public Vraportstocurimonth() {
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Double getStoc() {
        return stoc;
    }

    public void setStoc(Double stoc) {
        this.stoc = stoc;
    }
    
}
