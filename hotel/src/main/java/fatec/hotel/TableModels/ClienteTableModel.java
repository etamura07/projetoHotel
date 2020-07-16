/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fatec.hotel.TableModels;

import fatec.hotel.DTO.Cliente;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author memet
 */
public class ClienteTableModel extends AbstractTableModel {
    private List<Cliente> dados = new ArrayList<Cliente>();
    private String[] colunas = {"codigo","nome","cpf","dataNascimento","logradouro","bairro","cidade","estado","telefone","cep"};
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
            case 2: return getDados().get(linha).getCpf();
            case 3: return getDados().get(linha).getDataNascimento();
            case 4: return getDados().get(linha).getLogradouro();
            case 5: return getDados().get(linha).getBairro();
            case 6: return getDados().get(linha).getCidade();
            case 7: return getDados().get(linha).getEstado();
            case 8: return getDados().get(linha).getTelefone();
            case 9: return getDados().get(linha).getCep();
            
            default: return null;
        }
    }
    
    public Cliente retornaObjeto(int linha){
        return dados.get(linha);
    }
    
    public String getColumnName(int coluna){
        return colunas[coluna];
    }
    /**
     * @return the dados
     */
    public List<Cliente> getDados() {
        return dados;
    }

    /**
     * @param dados the dados to set
     */
    public void setDados(List<Cliente> dados) {
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
