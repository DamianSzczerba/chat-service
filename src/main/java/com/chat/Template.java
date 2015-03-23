package com.chat;

import com.chat.config.TemplateConfig;
import com.chat.resource.ChatLogsResource;
import com.chat.resource.ChatResource;
import com.chat.resource.LoginResource;
import com.chat.core.Token;
//import com.chat.security.BasicAuthenticator;

import com.chat.resource.UserResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;
//import io.dropwizard.auth.oauth.OAuthProvider;

public class Template extends Application<TemplateConfig> {
    public void run(TemplateConfig templateConfig, Environment environment) throws Exception {
        final FilterRegistration.Dynamic cors = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin,Access-Control-Request-Method,Authorization,Access-Control-Request-Method");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        //environment.jersey().register(new ChatResource());
        environment.jersey().register(new LoginResource());
        environment.jersey().register(new UserResource());
        environment.jersey().register(new ChatLogsResource());

    }
    /*
    @Override
    public void run(TemplateConfig templateConfig, Environment environment) throws Exception {
        environment.jersey().register(new ChatResource());
        environment.jersey().register(new OAuthProvider<Token>(
                new BasicAuthenticator(databaseManager),
                "ondemand-oauth"));
    }*/
}