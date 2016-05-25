/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.bullets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author anon
 */
@Configuration
public class BeanFactory {
    
    @Bean
    //@Scope(value = "prototype")
    public IBulletFactory createBeanFactory() {
        return new BulletFactory();
    }
}
