/*
 * Implementación Persona DAO
 * http://acodigo.blogspot.com/2017/03/spring-acceso-datos-con-jdbc.html
 * 30/04/2019 Modificación para el uso de JdbcTemplate
 */
package com.golano.spring.springjdbc.dao;

import com.golano.spring.springjdbc.model.Persona;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * 29/04/2019
 * @author gaolanot
 */
@Repository //Indica que se debe crear un bean de esta clase y trabaja en la 
            //capa de acceso a datos
public class PersonaDaoImpl implements PersonaDao {   
    private JdbcTemplate jdbcTemplate;
    //Inyección automática Bean DataSource (SpringConfiguration)
    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    //Método listar personas
    @Override
    public List<Persona> listarPersonas() {
        String sql = "Select * from 23people.persona";        
        return this.jdbcTemplate.query(sql, new PersonaRowMapper());    
    }
    //Método para calcular cantidad de registros  en tabla persona
    @Override
    public Integer cantidad() {
        String sql = "Select count(*) from 23people.persona";
        return this.jdbcTemplate.queryForObject(sql, Integer.class);
    }
    //Mpetodo para buscar registro por id de persona
    @Override
    public Persona buscar(Integer id) {
        String sql = "Select * from 23people.persona where id = ?";
        return this.jdbcTemplate.queryForObject(sql, new PersonaRowMapper(),id);
    }    

    @Override
    public void insertar(Persona persona) {
        String sql = "Insert into 23people.persona(nombre, apellido) values (?,?)";
        this.jdbcTemplate.update(sql,
                persona.getNombre(),
                persona.getApellido());
    }

    @Override
    public void actualizar(Persona persona) {
        String sql = "Update 23people.persona set nombre=?, apellido=? where id=?";
        this.jdbcTemplate.update(sql,
                persona.getNombre(), 
                persona.getApellido(),
                persona.getId());
    }

    @Override
    public void eliminar(Integer id) {
        String sql = "Delete from 23people.persona where id=?";
        this.jdbcTemplate.update(sql, id);
    }
}
