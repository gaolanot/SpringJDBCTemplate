/*
 * Clase de creación del contenedor y test de la aplicación
 * http://acodigo.blogspot.com/2017/03/spring-acceso-datos-con-jdbc.html
 */
package com.golano.spring.springjdbc;

import com.golano.spring.springjdbc.dao.PersonaDao;
import com.golano.spring.springjdbc.model.Persona;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * 28/04/2019
 * @author golanot
 */
public class SpringJDBC {

    
    public static void main(String[] args) {
        AbstractApplicationContext ctx = 
                new AnnotationConfigApplicationContext(SpringConfiguration.class);
        
        PersonaDao personaDao = ctx.getBean(PersonaDao.class);
        personaDao.listarPersonas().forEach(System.out::println);
        System.out.println("Cantidad:" + personaDao.cantidad());
        System.out.println("Busqueda 1:" + personaDao.buscar(1));
        
        Persona p = new Persona();
        p.setNombre("Fernanda");
        p.setApellido("Bedoya");
        personaDao.insertar(p);
        p.setNombre("Maria Isabel");
        p.setApellido("Trejos Ocampo");
        p.setId(3);
        personaDao.actualizar(p);
        personaDao.eliminar(4);
        ctx.close();
    }
    
}
