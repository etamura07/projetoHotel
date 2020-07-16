/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fatec.hotel.DAO;

import fatec.hotel.DTO.Deposito;
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
public class DepositoDAO {
    private ConexaoSQLite conexao = new ConexaoSQLite();
    
    public DepositoDAO(){
        try{
            String sql = "create table if not exists deposito(codigo integer primary key autoincrement, "
                    + "valor float, dataDeposito varchar(100))";
            
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
    
    public List<Deposito> consultar(String busca){
        List<Deposito> lista = new ArrayList<Deposito>();
        try{
            if(conexao.conectar()){
                PreparedStatement stmt;
                if(busca.equals("")){
                    stmt = conexao.preparedStatement("select * from deposito order by codigo");
                }
                else
                {
                    stmt = conexao.preparedStatement("select * from deposito where codigo like ? order by codigo");
                    stmt.setString(1,"%" + busca + "%"); 
                  
                }
                ResultSet resultado = stmt.executeQuery();
                while(resultado.next()){
                    Deposito obj = new Deposito();
                    obj.setCodigo(resultado.getLong("codigo"));
                    obj.setValor(resultado.getDouble("valor"));
                    obj.setDataDeposito(resultado.getString("dataDeposito"));
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
    
    
    public boolean cadastrar(Deposito obj){
        boolean isRegistered = false;
        try{
            if(conexao.conectar()){
                String sql = "insert into deposito(valor, dataDeposito) values(?,?)";
                PreparedStatement stmt = conexao.preparedStatement(sql);
                stmt.setDouble(1, obj.getValor());
                stmt.setString(2, obj.getDataDeposito());
                int result  = stmt.executeUpdate();
                if (result > 0) {
                    isRegistered = true;
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
    
    public void efetivar(){
        //DAO
    }
}
