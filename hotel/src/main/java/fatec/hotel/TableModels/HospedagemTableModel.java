/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fatec.hotel.TableModels;

import fatec.hotel.DTO.Hospedagem;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author memet
 */
public class HospedagemTableModel extends AbstractTableModel{
    
    private List<Hospedagem> dados = new ArrayList<Hospedagem>();
    private String[] colunas = {"codigo","dataEntrada","dataSaida","Hóspede","Quarto","StatusPagamento","diarias","valorTotal","notaFiscal"};
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
            case 1: return getDados().get(linha).getDataEntrada();
            case 2: return getDados().get(linha).getDataSaida();
            case 3: return getDados().get(linha).getCliente().getNome();
            case 4: return getDados().get(linha).getQuarto().getNumero();
            case 5: if (getDados().get(linha).getDeposito().getCodigo() == 0){
                return "Pagamento pendente";
            }
            else
            {
                return "Pagamento efetuado";
            }
            case 6: return getDados().get(linha).getDiarias();
            case 7: return getDados().get(linha).getValorTotal();
            case 8: return getDados().get(linha).getNotaFiscal();
            default: return null;
        }
    }
    public Hospedagem retornaObjeto(int linha){
        return dados.get(linha);
    }
    
    public String getColumnName(int coluna){
        return colunas[coluna];
    }
    /**
     * @return the dados
     */
    public List<Hospedagem> getDados() {
        return dados;
    }

    /**
     * @param dados the dados to set
     */
    public void setDados(List<Hospedagem> dados) {
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
