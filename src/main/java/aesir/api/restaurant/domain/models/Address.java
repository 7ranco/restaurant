package aesir.api.restaurant.domain.models;

import aesir.api.restaurant.domain.dto.AddressDTO;
import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

    private String indicacion;
    private int numero;
    private String complemento;
    private String Barrio;
    private String ciudad;

    public Address(AddressDTO addressDTO) {
        this.indicacion = addressDTO.indicacion();
        this.numero = addressDTO.numero();
        this.complemento = addressDTO.complemento();
        this.Barrio = addressDTO.barrio();
        this.ciudad = addressDTO.ciudad();
    }

    public Address(){

    }
    public String getIndicacion() {
        return indicacion;
    }

    public void setIndicacion(String indicacion) {
        this.indicacion = indicacion;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBarrio() {
        return Barrio;
    }

    public void setBarrio(String barrio) {
        Barrio = barrio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
