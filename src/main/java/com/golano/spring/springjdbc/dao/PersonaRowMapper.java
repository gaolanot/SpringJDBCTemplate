/*
 * Clase RowMapper para Persona
 * http://acodigo.blogspot.com/2017/03/spring-base-de-datos-con-jdbctemplate.html
 */
package com.golano.spring.springjdbc.dao;

import com.golano.spring.springjdbc.model.Persona;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * 30/04/2019
 * @author gaolanot
 */
public class PersonaRowMapper implements RowMapper<Persona>{

    @Override
    public Persona mapRow(ResultSet rs, int i) throws SQLException {
        Persona p = new Persona();
        p.setId(rs.getInt("id"));
        p.setNombre(rs.getString("nombre"));
        p.setApellido(rs.getString("apellido"));
        return p;
    }
}
