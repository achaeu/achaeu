/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author igor-bueno
 */
public class Localizacao extends IEntidade{
    private Double latitude;
    private Double longitude;

    public Localizacao() {
        super();
    }

    public Localizacao(Integer id, Double latitude, Double longitude) {
        super(id);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }    

    @Override
    public String toString() {
        return "Localizacao{"+ "id=" + super.getId() + "latitude=" + latitude + ", longitude=" + longitude + '}';
    }
    
    
}
