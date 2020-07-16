/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fatec.hotel.DTO;

/**
 *
 * @author norton
 */
public class Quarto {   
    private Long numero;
    private String descritivo;
    private int status;
    private double valorDiaria;
    
    public Quarto(){
        
    }
    public Quarto(Long numero, String descritivo, double valorDiaria){
        this.setDescritivo(descritivo);
        this.setNumero(numero);
        this.setValorDiaria(valorDiaria);
    }

    
    public void consultar(){
        //DAO
    }
    
    public boolean atualizar(){
        //DAO
        return false; 
    }
    
    public boolean cadastrar(){
        //DAO
        return false;
    }
    
    /**
     * @return the numero
     */
    public Long getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(Long numero) {
        this.numero = numero;
    }

    /**
     * @return the descritivo
     */
    public String getDescritivo() {
        return descritivo;
    }

    /**
     * @param descritivo the descritivo to set
     */
    public void setDescritivo(String descritivo) {
        this.descritivo = descritivo;
    }

    /**
     * @return the disponibilidae
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param disponibilidade the disponibilidae to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the valorDiaria
     */
    public double getValorDiaria() {
        return valorDiaria;
    }

    /**
     * @param valorDiaria the valorDiaria to set
     */
    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }
}
