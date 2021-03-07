package com.udacity.jdnd.course3.critter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Dummy controller class to verify installation success. Do not use for
 * your project work.
 */
@RestController
public class CritterController {

    @Autowired
    DataSource dataSource;

    @GetMapping("/test")
    public String test(){


        try {
            dataSource.getConnection();
            System.out.println("connection");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("error connection");
        }
        return "Critter Starter installed successfully";
    }
}
