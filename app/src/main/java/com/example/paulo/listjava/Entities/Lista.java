package com.example.paulo.listjava.Entities;

/**
 * Created by Paulo on 15/09/2017.
 */

public class Lista {
    private Integer id;
    private String nome;

   public void setId(Integer id){
       this.id = id;
    }

    public Integer getId(){
        return this.id;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return this.nome;
    }

}
