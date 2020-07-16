/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fatec.hotel.DAO;

import fatec.hotel.DTO.Cliente;
import fatec.hotel.DTO.Hospedagem;
import fatec.hotel.DTO.Quarto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author memet
 */
public class HospedagemDAO {
    private ConexaoSQLite conexao = new ConexaoSQLite();
    
    public HospedagemDAO(){
         try{
            String sql = "create table if not exists hospedagem(codigo integer, "
                    + "dataEntrada varchar(100), dataSaida varchar(100), codigoCliente int, codigoDeposito int, codigoQuarto int,"
                    + "diarias int, valorTotal float, codigoNota int, codigoServico int)";
            
            if(conexao.conectar()){
                Statement stmt = conexao.retornaStatement();
                stmt.execute(sql);
            }
        }
        catch(SQLException err){
            System.out.println(err.getMessage());
        }
        finally{
            conexao.desconectar();
        }
    }
    
    
        public List<Hospedagem> consultar(String busca){
        List<Hospedagem> lista = new ArrayList<Hospedagem>();
        try{
            if(conexao.conectar()){
                PreparedStatement stmt;
                if(busca.equals("")){
                    stmt = conexao.preparedStatement("select * from hospedagem h inner join cliente c on h.codigoCliente = c.codigo inner join quarto q on h.codigoQuarto = q.numero order by codigo");
                }
                else
                {
                    stmt = conexao.preparedStatement("select * from hospedagem where codigo like ? order by codigo");
                    stmt.setString(1,"%" + busca + "%"); 
                  
                }
                ResultSet resultado = stmt.executeQuery();
                while(resultado.next()){
                    Cliente obj2 = new Cliente();
                    Quarto obj3 = new Quarto();
                    
                    obj2.setCodigo(resultado.getLong("codigoCliente"));
                    obj2.setNome(resultado.getString("nome"));
                    obj2.setCpf(resultado.getString("cpf"));
                    obj2.setDataNascimento(resultado.getString("dataNascimento"));
                    obj2.setLogradouro(resultado.getString("logradouro"));
                    obj2.setBairro(resultado.getString("bairro"));
                    obj2.setCidade(resultado.getString("cidade"));
                    obj2.setEstado(resultado.getString("estado"));
                    obj2.setTelefone(resultado.getString("telefone"));
                    obj2.setCep(resultado.getString("cep"));
                    
                    obj3.setNumero(resultado.getLong("numero"));
                    obj3.setDescritivo(resultado.getString("descritivo"));
                    obj3.setStatus(resultado.getInt("status"));
                    obj3.setValorDiaria(resultado.getDouble("valorDiaria"));
                    
                    Hospedagem obj = new Hospedagem(resultado.getLong("codigo"),resultado.getString("dataEntrada"), resultado.getString("dataSaida"), obj2, null, obj3, resultado.getInt("diarias"),resultado.getDouble("valorTotal"));
                    
                    lista.add(obj);
                }
            }
        }
        catch(SQLException err){
            System.err.println(err.getMessage());
        }
        finally{
            conexao.desconectar();
            return lista;
        }
    }
        
        
    public boolean CheckIn(Hospedagem obj){
        boolean isRegistered = false;
        try{
            if(conexao.conectar()){
                String sql = "insert into hospedagem(codigo, dataEntrada, dataSaida, codigoCliente, codigoDeposito,codigoQuarto, diarias, valorTotal, codigoNota ,codigoServico) values(?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement stmt = conexao.preparedStatement(sql);
                stmt.setLong(1, obj.getCodigo());
                stmt.setString(2, obj.getDataEntrada());
                stmt.setString(3, obj.getDataSaida());
                stmt.setLong(4, obj.getCliente().getCodigo());
                if(obj.getDeposito() == null){
                    stmt.setLong(5, 0);
                }
                else{
                    stmt.setLong(5, obj.getDeposito().getCodigo());
                }
                stmt.setLong(6, obj.getQuarto().getNumero());
                stmt.setInt(7, obj.getDiarias());
                stmt.setDouble(8, obj.getValorTotal());
                if(obj.getNotaFiscal() == null){
                    stmt.setLong(9, 0);
                }else
                {
                    stmt.setLong(9, obj.getNotaFiscal().getNumero());
                }
                
                stmt.setLong(10, 0);
                int result  = stmt.executeUpdate();
                if (result > 0) {
                    isRegistered = true;
                    QuartoDAO  dao = new QuartoDAO();
                    dao.Ocupar(obj.getQuarto().getNumero());
                    ReservaDAO reservaDao = new ReservaDAO();
                    if(reservaDao.remover(obj.getCodigo()) == 1){
                        JOptionPane.showMessageDialog(null,"Quarto foi ocupado");
                    };
                }
                else {
                    isRegistered = false;
                }
            }
        }
        catch(SQLException err){
            System.err.println(err.getMessage());
        }
        finally{
            conexao.desconectar();
            return isRegistered;
        }
    }
}    
 

