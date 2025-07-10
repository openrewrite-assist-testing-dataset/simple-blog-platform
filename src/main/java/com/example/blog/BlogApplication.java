package com.example.blog;

import com.example.blog.api.PostResource;
import com.example.blog.auth.ApiKeyAuthenticator;
import com.example.blog.auth.BlogPrincipal;
import com.example.blog.db.PostDAO;
import io.dropwizard.core.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import org.jdbi.v3.core.Jdbi;

public class BlogApplication extends Application<BlogConfiguration> {

    public static void main(String[] args) throws Exception {
        new BlogApplication().run(args);
    }

    @Override
    public String getName() {
        return "simple-blog-platform";
    }

    @Override
    public void initialize(Bootstrap<BlogConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<BlogConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(BlogConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
    }

    @Override
    public void run(BlogConfiguration configuration, Environment environment) throws Exception {
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "h2");
        final PostDAO postDAO = jdbi.onDemand(PostDAO.class);
        final ApiKeyAuthenticator authenticator = new ApiKeyAuthenticator(configuration.getApiKey());

        environment.jersey().register(new AuthDynamicFeature(
                new BasicCredentialAuthFilter.Builder<BlogPrincipal>()
                        .setAuthenticator(authenticator)
                        .setRealm("Blog API")
                        .buildAuthFilter()));
        
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(BlogPrincipal.class));
        environment.jersey().register(new PostResource(postDAO));
    }
}