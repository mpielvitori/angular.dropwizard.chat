package com.barbu.chat;

import com.barbu.chat.config.*;
import com.barbu.chat.daos.*;
import com.barbu.chat.models.*;
import com.barbu.chat.resources.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.base.Optional;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatappService extends Application<ChatappConfiguration> {
    private static final Logger LOG = LoggerFactory.getLogger(ChatappService.class);

    public static void main(String[] args) throws Exception {
        new ChatappService().run(args);
    }

    private final HibernateBundle<ChatappConfiguration> hibernateBundle = new HibernateBundle<ChatappConfiguration>(
            
            TestEntity.class,
            Room.class,
            Message.class,
            Void.class
        ) {
        @Override
        public DataSourceFactory getDataSourceFactory(ChatappConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public String getName() {
        return "chatapp";
    }

    @Override
    public void initialize(Bootstrap<ChatappConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets/app/", "/", "index.html"));
        bootstrap.addBundle(hibernateBundle);
        ObjectMapper mapper = bootstrap.getObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Override
    public void run(ChatappConfiguration configuration,
                    Environment environment) throws Exception {
        environment.jersey().setUrlPattern("/chatapp/*");
        
        
        environment.jersey().register(new TestResource());
        
        environment.jersey().register(new TestEntityResource(
            new TestEntityDAO(hibernateBundle.getSessionFactory())));
        environment.jersey().register(new RoomResource(
            new RoomDAO(hibernateBundle.getSessionFactory())));
        environment.jersey().register(new MessageResource(
            new MessageDAO(hibernateBundle.getSessionFactory())));
    }
}
