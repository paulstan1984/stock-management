/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelinterfaces;

/**
 *
 * @author ioapau
 */
public interface RaportIOEntity {
    public Double getPretintrare();
    public void setPretintrare(Double pretintrare);
    public Double getPretiesire();
    public void setPretiesire(Double pretiesire);
    public void setStoc(Double stoc);
    
    public Double getCantitateintrare();
    public void setCantitateintrare(Double cantitateintrare);
    public Double getCantitateiesire();
    public void setCantitateiesire(Double cantitateiesire);
    public Double getStoc();
}
