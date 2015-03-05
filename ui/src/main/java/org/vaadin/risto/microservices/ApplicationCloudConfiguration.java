package org.vaadin.risto.microservices;

import de.javakaffee.web.msm.MemcachedBackupSessionManager;
import org.apache.catalina.Context;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("cloud")
public class ApplicationCloudConfiguration {

    @Value("${memcache-url}")
    private String memcacheUrl;

    @Bean
    public EmbeddedServletContainerFactory tomcatContainerFactory() {
        return new TomcatEmbeddedServletContainerFactory() {

            @Override
            protected void postProcessContext(Context context) {
                MemcachedBackupSessionManager manager = new MemcachedBackupSessionManager();
                manager.setMemcachedNodes(memcacheUrl);
                manager.setRequestUriIgnorePattern(".*\\.(ico|png|gif|jpg|css|js)$");
                context.setManager(manager);
            }

        };
    }
}
