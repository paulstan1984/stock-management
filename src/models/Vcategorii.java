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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "VCATEGORII")
@XmlRootElement
public class Vcategorii implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "Categorie")
    private String categorie;
    
    public Vcategorii() {
    }
    
    public Vcategorii(String categorie) {
        this.setCategorie(categorie);
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }    
 
    public String toString(){
        return this.getCategorie();
    }
}
