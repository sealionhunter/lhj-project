package lhj.learn.webservice;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/service")
public class MyApplication extends ResourceConfig {

    public MyApplication() {
        System.out.println("start Application");
        packages("lhj.learn.webservice").register(
                JacksonFeature.class).register(LoggingFilter.class);
    }
    //    @Override
    //    public Set<Class<?>> getClasses() {
    //        final Set<Class<?>> classes = new HashSet<Class<?>>();
    //        // register root resource
    //        classes.add(HelloWorld.class);
    //        classes.add(JacksonFeature.class);
    //        classes.add(LoggingFilter.class);
    //        return classes;
    //    }

}
