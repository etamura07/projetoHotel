/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fatec.hotel.TableModels;

import fatec.hotel.DTO.Quarto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author memet
 */
public class QuartoTableModel extends AbstractTableModel{
    private List<Quarto> dados = new ArrayList<Quarto>();
    private String[] colunas = {"numero","descritivo","status","valorDiaria"};
    @Override
    public int getRowCount() {
        return getDados().size();
    }

    @Override
    public int getColumnCount() {
        return getColunas().length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch(coluna){
            case 0: return getDados().get(linha).getNumero();
            case 1: return getDados().get(linha).getDescritivo();
            case 2: 
                if(getDados().get(linha).getStatus() == 0)
                { 
                    return "Disponível"; 
                } 
                else if(getDados().get(linha).getStatus() == 1) { 
                    return "Ocupado"; 
                }
                else if(getDados().get(linha).getStatus() == 2){
                    return "Reservado";
                }
            case 3: return getDados().get(linha).getValorDiaria();
            default: return null;
        }
    }
    public Quarto retornaObjeto(int linha){
        return dados.get(linha);
    }
    
    public String getColumnName(int coluna){
        return colunas[coluna];
    }
    /**
     * @return the dados
     */
    public List<Quarto> getDados() {
        return dados;
    }

    /**
     * @param dados the dados to set
     */
    public void setDados(List<Quarto> dados) {
        this.dados = dados;
    }

    /**
     * @return the colunas
     */
    public String[] getColunas() {
        return colunas;
    }

    /**
     * @param colunas the colunas to set
     */
    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }
    
}
