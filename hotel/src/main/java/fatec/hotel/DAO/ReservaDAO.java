/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fatec.hotel.DAO;

import fatec.hotel.DTO.Cliente;
import fatec.hotel.DTO.Quarto;
import fatec.hotel.DTO.Reserva;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author memet
 */
public class ReservaDAO {
    private ConexaoSQLite conexao = new ConexaoSQLite();
    
    public ReservaDAO(){
         try{
            String sql = "create table if not exists reserva(codigo integer primary key autoincrement, "
                    + "codigoCliente int, codigoDeposito int, codigoQuarto int, dataEntrada varchar(100), dataSaida varchar(100))";
            
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
    
    public List<Reserva> consultar(String busca){
        List<Reserva> lista = new ArrayList<Reserva>();
        try{
            if(conexao.conectar()){
                PreparedStatement stmt;
                if(busca.equals("")){
                    stmt = conexao.preparedStatement("select * from reserva r inner join cliente c on r.codigoCliente = c.codigo inner join quarto q on r.codigoQuarto = q.numero where q.status = ? order by r.codigo");
                    stmt.setString(1,"2"); 
                }
                else
                {
                    stmt = conexao.preparedStatement("select * from reserva r inner join cliente c on r.codigoCliente = c.codigo inner join quarto q on r.codigoQuarto = q.numero where r.codigo like ? order by r.codigo");
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
                    
                    Reserva obj = new Reserva(resultado.getLong("codigo"), resultado.getString("dataEntrada"), resultado.getString("dataSaida"), obj2, null, obj3);
                    
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
    
    public boolean registrarReserva(Reserva obj){
        boolean isRegistered = false;
        try{
            if(conexao.conectar()){
                String sql = "insert into reserva(codigoCliente, codigoDeposito, codigoQuarto, dataEntrada, dataSaida) values(?,?,?,?,?)";
                PreparedStatement stmt = conexao.preparedStatement(sql);
                stmt.setLong(1, obj.getCliente().getCodigo());
                if(obj.getDeposito() == null){
                    stmt.setLong(3, 0);
                }
                else{
                    stmt.setLong(3, obj.getDeposito().getCodigo());
                }
                stmt.setLong(3, obj.getQuarto().getNumero());
                stmt.setString(4, obj.getDataEntrada());
                stmt.setString(5, obj.getDataSaida());
                int result  = stmt.executeUpdate();
                if (result > 0) {
                    isRegistered = true;
                    QuartoDAO  dao = new QuartoDAO();
                    dao.Reservar(obj.getQuarto().getNumero());
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
    
    public Reserva retornaReserva(String codigo){
        Reserva obj = null;
        try{
            if(conexao.conectar()){
                String sql = "select * from reserva r inner join cliente c on r.codigoCliente = c.codigo inner join quarto q on r.codigoQuarto = q.numero where r.codigo = ?";
                PreparedStatement stmt = conexao.preparedStatement(sql);
                stmt.setString(1, codigo);
                ResultSet resultado = stmt.executeQuery();
                if(!resultado.isClosed()){
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
                    
                    obj = new Reserva(resultado.getLong("codigo"), resultado.getString("dataEntrada"), resultado.getString("dataSaida"), obj2, null, obj3);
                    
                }
            }
        }
        catch(SQLException err){
            System.err.println(err.getMessage());
        }
        finally{
            conexao.desconectar();
            return obj;
        }
    }
    
    public int remover(long codigo){
        int cont = 0;
        try{
            if(conexao.conectar()){
                String sql = "delete from reserva where codigo=?";
                PreparedStatement stmt = conexao.preparedStatement(sql);
                stmt.setLong(1, codigo);
                cont  = stmt.executeUpdate();
            }
        }
        catch(SQLException err){
            System.err.println(err.getMessage());
        }
        finally{
            conexao.desconectar();
            return cont;
        }
    }
}
