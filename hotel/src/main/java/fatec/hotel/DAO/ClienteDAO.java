/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fatec.hotel.DAO;

import fatec.hotel.DTO.Cliente;
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
public class ClienteDAO {
    private ConexaoSQLite conexao = new ConexaoSQLite();
    
    public ClienteDAO(){
         try{
            String sql = "create table if not exists cliente(codigo integer primary key autoincrement,nome varchar(100), "
                    + "cpf varchar(20), dataNascimento varchar(20), email varchar(100), senha varchar(100), "
                    + "logradouro varchar(100), bairro varchar(100), cidade varchar(100), estado varchar(100), telefone varchar(20), cep varchar(15))"                    ;
            
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
    
    public List<Cliente> consultar(String busca){
        List<Cliente> lista = new ArrayList<Cliente>();
        try{
            if(conexao.conectar()){
                PreparedStatement stmt;
                if(busca.equals("")){
                    stmt = conexao.preparedStatement("select * from cliente order by codigo");
                }
                else
                {
                    stmt = conexao.preparedStatement("select * from cliente where nome like ? order by codigo");
                    stmt.setString(1,"%" + busca + "%"); 
                  
                }
                ResultSet resultado = stmt.executeQuery();
                while(resultado.next()){
                    Cliente obj = new Cliente();
                    obj.setCodigo(resultado.getLong("codigo"));
                    obj.setNome(resultado.getString("nome"));
                    obj.setCpf(resultado.getString("cpf"));
                    obj.setDataNascimento(resultado.getString("dataNascimento"));
                    obj.setLogradouro(resultado.getString("logradouro"));
                    obj.setBairro(resultado.getString("bairro"));
                    obj.setCidade(resultado.getString("cidade"));
                    obj.setEstado(resultado.getString("estado"));
                    obj.setTelefone(resultado.getString("telefone"));
                    obj.setCep(resultado.getString("cep"));
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
    
    public Cliente retornaCliente(String codigo){
        Cliente obj = new Cliente();
        try{
            if(conexao.conectar()){
                String sql = "select * from cliente where codigo=?";
                PreparedStatement stmt = conexao.preparedStatement(sql);
                stmt.setString(1, codigo);
                ResultSet resultado = stmt.executeQuery();
                if(!resultado.isClosed()){
                    obj.setCodigo(resultado.getLong("codigo"));
                    obj.setNome(resultado.getString("nome"));
                    obj.setCpf(resultado.getString("cpf"));
                    obj.setDataNascimento(resultado.getString("dataNascimento"));
                    obj.setLogradouro(resultado.getString("logradouro"));
                    obj.setBairro(resultado.getString("bairro"));
                    obj.setCidade(resultado.getString("cidade"));
                    obj.setEstado(resultado.getString("estado"));
                    obj.setTelefone(resultado.getString("telefone"));
                    obj.setCep(resultado.getString("cep"));
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
    
    public boolean cadastrar(Cliente obj){
        boolean isRegistered = false;
        try{
            if(conexao.conectar()){
                String sql = "insert into cliente(nome, cpf, dataNascimento, logradouro, bairro, cidade, estado, telefone, cep) values(?,?,?,?,?,?,?,?,?)";
                PreparedStatement stmt = conexao.preparedStatement(sql);
                stmt.setString(1, obj.getNome());
                stmt.setString(2, obj.getCpf());
                stmt.setString(3, obj.getDataNascimento());
                stmt.setString(4, obj.getLogradouro());
                stmt.setString(5, obj.getBairro());
                stmt.setString(6, obj.getCidade());
                stmt.setString(7, obj.getEstado());
                stmt.setString(8, obj.getTelefone());
                stmt.setString(9, obj.getCep());
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
    
    public int alterar(Cliente obj){
        int cont = 0;
        try{
            if(conexao.conectar()){
                String sql = "update cliente set nome=?, cpf=?, dataNascimento=?, logradouro=?, bairro=?, cidade=?, estado=?, telefone=?, cep=? where codigo=?";
                PreparedStatement stmt = conexao.preparedStatement(sql);
                stmt.setString(1, obj.getNome());
                stmt.setString(2, obj.getCpf());
                stmt.setString(3, obj.getDataNascimento());
                stmt.setString(4, obj.getLogradouro());
                stmt.setString(5, obj.getBairro());
                stmt.setString(6, obj.getCidade());
                stmt.setString(7, obj.getEstado());
                stmt.setString(8, obj.getTelefone());
                stmt.setString(9, obj.getCep());
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
    
    public int remover(Cliente obj){
        int cont = 0;
        try{
            if(conexao.conectar()){
                String sql = "delete from cliente where codigo=?";
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
