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
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ioapau
 */
@Embeddable
public class TraporttotaldayPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "DATA")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Basic(optional = false)
    @Column(name = "IDPRODUS")
    private int idprodus;

    public TraporttotaldayPK() {
    }

    public TraporttotaldayPK(Date data, int idprodus) {
        this.data = data;
        this.idprodus = idprodus;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getIdprodus() {
        return idprodus;
    }

    public void setIdprodus(int idprodus) {
        this.idprodus = idprodus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (data != null ? data.hashCode() : 0);
        hash += (int) idprodus;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TraporttotaldayPK)) {
            return false;
        }
        TraporttotaldayPK other = (TraporttotaldayPK) object;
        if ((this.data == null && other.data != null) || (this.data != null && !this.data.equals(other.data))) {
            return false;
        }
        if (this.idprodus != other.idprodus) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.TraporttotaldayPK[ data=" + data + ", idprodus=" + idprodus + " ]";
    }
    
}
