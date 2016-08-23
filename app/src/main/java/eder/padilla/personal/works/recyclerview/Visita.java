package eder.padilla.personal.works.recyclerview;


public class Visita
{
     String nombre;
     String direccion;
     String tipo;
     int hora;

    public Visita(String nom, String dir, String ti,int ho){
        nombre = nom;
        direccion = dir;
        tipo=ti;
        hora=ho;
    }

    public String getNombre(){
        return nombre;
    }

    public String getDireccion(){
        return direccion;
    }
    public String getTipo() {return tipo;}
    public int getHora(){return hora;}

}
