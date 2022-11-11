package com.mycompany.tacotitosv2.Modelos;

import java.util.Objects;

public class tipoIngrediente {
    protected int id;
    protected String descripcion;
    protected int cantMax;

    public tipoIngrediente(int id, String descripcion, int cantMax){
        this.id = id;
        this.descripcion = descripcion;
        this.cantMax = cantMax;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public int getCantMax() {
        return cantMax;
    }
    public void setCantMax(int cantMax) {
        this.cantMax = cantMax;
    }
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.descripcion);
        hash = 73 * hash + this.cantMax;
        return hash;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final tipoIngrediente other = (tipoIngrediente) obj;
        if (this.cantMax != other.cantMax) {
            return false;
        }
        return Objects.equals(this.descripcion, other.descripcion);
    }
    @Override
    public String toString() {
        return  this.descripcion;
    }
    
    public int getId(){
        return id;
    }
}
