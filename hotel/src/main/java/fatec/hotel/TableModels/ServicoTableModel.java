/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fatec.hotel.TableModels;

import fatec.hotel.DTO.Servico;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author memet
 */
public class ServicoTableModel extends AbstractTableModel{
    private List<Servico> dados = new ArrayList<Servico>();
    private String[] colunas = {"codigo","descritivo","tipo","valor","data"};
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
            case 1: return getDados().get(linha).getDescritivo();
            case 2: return getDados().get(linha).getTipo();
            case 3: return getDados().get(linha).getValor();
            case 4: return getDados().get(linha).getData();
            default: return null;
        }   
    }
    public Servico retornaObjeto(int linha){
        return dados.get(linha);
    }
    
    public String getColumnName(int coluna){
        return colunas[coluna];
    }

    /**
     * @return the dados
     */
    public List<Servico> getDados() {
        return dados;
    }

    /**
     * @param dados the dados to set
     */
    public void setDados(List<Servico> dados) {
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
