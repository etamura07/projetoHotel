/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fatec.hotel.DAO;

import fatec.hotel.DTO.Quarto;
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
public class QuartoDAO {
    private ConexaoSQLite conexao = new ConexaoSQLite();
    
    public QuartoDAO(){
         try{
            String sql = "create table if not exists quarto(numero integer primary key autoincrement, "
                    + "descritivo varchar(100), status int, valorDiaria float)";
            
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
    public void consultar(){
        //DAO
    }
    
    public boolean atualizar(){
        //DAO
        return false; 
    }
    
    public List<Quarto> consultar(String busca){
        List<Quarto> lista = new ArrayList<Quarto>();
        try{
            if(conexao.conectar()){
                PreparedStatement stmt;
                if(busca.equals("")){
                    stmt = conexao.preparedStatement("select * from quarto order by numero");
                }
                else
                {
                    stmt = conexao.preparedStatement("select * from quarto where numero like ? order by numero");
                    stmt.setString(1,"%" + busca + "%"); 
                  
                }
                ResultSet resultado = stmt.executeQuery();
                while(resultado.next()){
                    Quarto obj = new Quarto();
                    obj.setNumero(resultado.getLong("numero"));
                    obj.setDescritivo(resultado.getString("descritivo"));
                    obj.setStatus(resultado.getInt("status"));
                    obj.setValorDiaria(resultado.getDouble("valorDiaria"));
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
    
        public List<Quarto> consultarQuartoDisponivel(String busca){
        List<Quarto> lista = new ArrayList<Quarto>();
        try{
            if(conexao.conectar()){
                PreparedStatement stmt;
                if(busca.equals("")){
                    stmt = conexao.preparedStatement("select * from quarto where status = 0 order by numero");
                }
                else
                {
                    stmt = conexao.preparedStatement("select * from quarto where numero like ? order by numero");
                    stmt.setString(1,"%" + busca + "%"); 
                  
                }
                ResultSet resultado = stmt.executeQuery();
                while(resultado.next()){
                    Quarto obj = new Quarto();
                    obj.setNumero(resultado.getLong("numero"));
                    obj.setDescritivo(resultado.getString("descritivo"));
                    obj.setStatus(resultado.getInt("status"));
                    obj.setValorDiaria(resultado.getDouble("valorDiaria"));
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
    
    public Quarto retornaQuarto(String codigo){
        Quarto obj = new Quarto();
        try{
            if(conexao.conectar()){
                String sql = "select * from quarto where numero=?";
                PreparedStatement stmt = conexao.preparedStatement(sql);
                stmt.setString(1, codigo);
                ResultSet resultado = stmt.executeQuery();
                if(!resultado.isClosed()){
                    obj.setNumero(resultado.getLong("numero"));
                    obj.setDescritivo(resultado.getString("descritivo"));
                    obj.setStatus(resultado.getInt("status"));
                    obj.setValorDiaria(resultado.getDouble("valorDiaria"));
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
    
    
    public boolean cadastrar(Quarto obj){
        boolean isRegistered = false;
        try{
            if(conexao.conectar()){
                String sql = "insert into quarto(descritivo, status, valorDiaria) values(?,?,?)";
                PreparedStatement stmt = conexao.preparedStatement(sql);
                stmt.setString(1, obj.getDescritivo());
                stmt.setInt(2, obj.getStatus());
                stmt.setDouble(3, obj.getValorDiaria());
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
    
        public void Reservar(long numero){
        int cont = 0;
        try{
            if(conexao.conectar()){
                String sql = "update quarto set status=? where numero=?";
                PreparedStatement stmt = conexao.preparedStatement(sql);
                stmt.setLong(1, 2);
                stmt.setLong(2, numero);
                stmt.executeUpdate();
            }
        }
        catch(SQLException err){
            System.err.println(err.getMessage());
        }
        finally{
            conexao.desconectar();
        }
    }
        
    public void Ocupar(long numero){
        int cont = 0;
        try{
            if(conexao.conectar()){
                String sql = "update quarto set status=? where numero=?";
                PreparedStatement stmt = conexao.preparedStatement(sql);
                stmt.setLong(1, 1);
                stmt.setLong(2, numero);
                stmt.executeUpdate();
            }
        }
        catch(SQLException err){
            System.err.println(err.getMessage());
        }
        finally{
            conexao.desconectar();
        }
    }
    
}
