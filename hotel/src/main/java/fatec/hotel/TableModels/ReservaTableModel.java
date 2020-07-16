/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fatec.hotel.TableModels;

import fatec.hotel.DTO.Reserva;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author memet
 */
public class ReservaTableModel extends AbstractTableModel{
    private List<Reserva> dados = new ArrayList<Reserva>();
    private String[] colunas = {"codigo","hóspede","quarto","dataEntrada","dataSaida","StatusPagamento"};
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
            case 1: return getDados().get(linha).getCliente().getNome();
            case 2: return getDados().get(linha).getQuarto().getNumero() + " - " + getDados().get(linha).getQuarto().getDescritivo();
            case 3: return getDados().get(linha).getDataEntrada();
            case 4: return getDados().get(linha).getDataSaida();
            case 5: 
                if (getDados().get(linha).getDeposito() == null){
                    return "Pagamento pendente";
                }
                else{
                    return "Pagamento efetuado";
                }
            default: return null;
        }   
    }
    
    public Reserva retornaObjeto(int linha){
        return dados.get(linha);
    }
    
    public String getColumnName(int coluna){
        return colunas[coluna];
    }

    /**
     * @return the dados
     */
    public List<Reserva> getDados() {
        return dados;
    }

    /**
     * @param dados the dados to set
     */
    public void setDados(List<Reserva> dados) {
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
