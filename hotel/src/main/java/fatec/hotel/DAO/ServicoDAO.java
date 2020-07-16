/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fatec.hotel.DAO;

import fatec.hotel.DTO.Servico;
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
public class ServicoDAO {
    private ConexaoSQLite conexao = new ConexaoSQLite();
    
    public ServicoDAO(){
         try{
            String sql = "create table if not exists servico(codigo integer primary key autoincrement, "
                    + "descritivo varchar(100), tipo varchar(100), valor float, data varchar(100))";
            
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
    
    public boolean atualizar(){
        //DAO
        return false; 
    }
    
    public List<Servico> consultar(String busca){
        List<Servico> lista = new ArrayList<Servico>();
        try{
            if(conexao.conectar()){
                PreparedStatement stmt;
                if(busca.equals("")){
                    stmt = conexao.preparedStatement("select * from servico order by descritivo");
                }
                else
                {
                    stmt = conexao.preparedStatement("select * from servico where descritivo like ? order by codigo");
                    stmt.setString(1,"%" + busca + "%"); 
                  
                }
                ResultSet resultado = stmt.executeQuery();
                while(resultado.next()){
                    Servico obj = new Servico();
                    obj.setCodigo(resultado.getLong("codigo"));
                    obj.setDescritivo(resultado.getString("descritivo"));
                    obj.setTipo(resultado.getString("tipo"));
                    obj.setValor(resultado.getDouble("valor"));
                    obj.setData(resultado.getString("data"));
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
    
    public boolean cadastrar(Servico obj){
        boolean isRegistered = false;
        try{
            if(conexao.conectar()){
                String sql = "insert into servico(descritivo, tipo, valor, data) values(?,?,?,?)";
                PreparedStatement stmt = conexao.preparedStatement(sql);
                stmt.setString(1, obj.getDescritivo());
                stmt.setString(2, obj.getTipo());
                stmt.setDouble(3, obj.getValor());
                stmt.setString(4, obj.getData());
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
    public Servico retornaServico(String codigo){
        Servico obj = new Servico();
        try{
            if(conexao.conectar()){
                String sql = "select * from servico where codigo=?";
                PreparedStatement stmt = conexao.preparedStatement(sql);
                stmt.setString(1, codigo);
                ResultSet resultado = stmt.executeQuery();
                if(!resultado.isClosed()){
                    obj.setCodigo(resultado.getLong("codigo"));
                    obj.setDescritivo(resultado.getString("descritivo"));
                    obj.setValor(resultado.getInt("valor"));
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
    
    public int alterar(Servico obj){
        int cont = 0;
        try{
            if(conexao.conectar()){
                String sql = "update servico set descritivo=?, valor=?, data=? where codigo=?";
                PreparedStatement stmt = conexao.preparedStatement(sql);
                stmt.setString(1, obj.getDescritivo());
                stmt.setDouble(2, obj.getValor());
                stmt.setString(3, obj.getData());
                stmt.setLong(10, obj.getCodigo());
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
    
    public int remover(Servico obj){
        int cont = 0;
        try{
            if(conexao.conectar()){
                String sql = "delete from servico where codigo=?";
                PreparedStatement stmt = conexao.preparedStatement(sql);
                stmt.setLong(1, obj.getCodigo());
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
