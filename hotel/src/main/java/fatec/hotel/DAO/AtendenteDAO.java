/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fatec.hotel.DAO;

import fatec.hotel.DTO.Atendente;
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
public class AtendenteDAO {
    private ConexaoSQLite conexao = new ConexaoSQLite();
    
    public AtendenteDAO(){
         try{
            String sql = "create table if not exists atendente(codigo integer primary key autoincrement,nome varchar(100), "
                    + "email varchar(100), senha varchar(100))";
            
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
    public List<Atendente> consultar(String busca){
        List<Atendente> lista = new ArrayList<Atendente>();
        try{
            if(conexao.conectar()){
                PreparedStatement stmt;
                if(busca.equals("")){
                    stmt = conexao.preparedStatement("select * from atendente order by codigo");
                }
                else
                {
                    stmt = conexao.preparedStatement("select * from atendente where codigo like ? order by codigo");
                    stmt.setString(1,"%" + busca + "%"); 
                  
                }
                ResultSet resultado = stmt.executeQuery();
                while(resultado.next()){
                    Atendente obj = new Atendente();
                    obj.setCodigo(resultado.getLong("codigo"));
                    obj.setNome(resultado.getString("nome"));
                    obj.setEmail(resultado.getString("email"));
                    obj.setSenha(resultado.getString("senha"));
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
    
    public boolean cadastrar(Atendente obj){
        boolean isRegistered = false;
        try{
            if(conexao.conectar()){
                String sql = "insert into atendente(nome, email, senha) values(?,?,?)";
                PreparedStatement stmt = conexao.preparedStatement(sql);
                stmt.setString(1, obj.getNome());
                stmt.setString(2, obj.getEmail());
                stmt.setString(3, obj.getSenha());
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
    
    public Atendente login(String email, String senha){
        Atendente obj = new Atendente();
        try{
            if(conexao.conectar()){
                String sql = "select * from atendente where email=? and senha=?";
                PreparedStatement stmt = conexao.preparedStatement(sql);
                stmt.setString(1, email);
                stmt.setString(2, senha);
                ResultSet resultado = stmt.executeQuery();
                if(!resultado.isClosed()){
                    obj.setNome(resultado.getString("nome"));
                    obj.setEmail(resultado.getString("email"));
                    obj.setSenha(resultado.getString("senha"));
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
    
    public Atendente retornaAtendente(String codigo){
        Atendente obj = new Atendente();
        try{
            if(conexao.conectar()){
                String sql = "select * from atendente where codigo=?";
                PreparedStatement stmt = conexao.preparedStatement(sql);
                stmt.setString(1, codigo);
                ResultSet resultado = stmt.executeQuery();
                if(!resultado.isClosed()){
                    obj.setCodigo(resultado.getLong("codigo"));
                    obj.setNome(resultado.getString("nome"));
                    obj.setEmail(resultado.getString("email"));
                    obj.setSenha(resultado.getString("senha"));
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
    
    public int alterar(Atendente obj){
        int cont = 0;
        try{
            if(conexao.conectar()){
                String sql = "update atendente set nome=?, email=?, senha=? where codigo=?";
                PreparedStatement stmt = conexao.preparedStatement(sql);
                stmt.setString(1, obj.getNome());
                stmt.setString(2, obj.getEmail());
                stmt.setString(3, obj.getSenha());
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
    
    public int remover(Atendente obj){
        int cont = 0;
        try{
            if(conexao.conectar()){
                String sql = "delete from atendente where codigo=?";
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
