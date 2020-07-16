/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fatec.hotel.TableModels;

import fatec.hotel.DTO.Atendente;
import fatec.hotel.DTO.Quarto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author memet
 */
public class AtendenteTableModel extends AbstractTableModel {
    private List<Atendente> dados = new ArrayList<Atendente>();
    private String[] colunas = {"codigo","nome","email","senha"};
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
            case 0: return getDados().get(linha).getCodigo();
            case 1: return getDados().get(linha).getNome();
            case 2: return getDados().get(linha).getEmail();
            case 3: return getDados().get(linha).getSenha();
            default: return null;
        }
    }
    public Atendente retornaObjeto(int linha){
        return dados.get(linha);
    }
    
    public String getColumnName(int coluna){
        return colunas[coluna];
    }
    /**
     * @return the dados
     */
    public List<Atendente> getDados() {
        return dados;
    }

    /**
     * @param dados the dados to set
     */
    public void setDados(List<Atendente> dados) {
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
