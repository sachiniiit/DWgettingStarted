package com.javaeeeee.dwstart;

import com.javaeeeee.dwstart.core.Employee;
import com.javaeeeee.dwstart.db.EmployeeDAO;
import com.javaeeeee.dwstart.resources.EmployeesResource;
import com.javaeeeee.dwstart.resources.HelloResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DWGettingStartedApplication extends Application<DWGettingStartedConfiguration> {


    public static void main(final String[] args) throws Exception {
        new DWGettingStartedApplication().run(args);
    }

    /**
     * Hibernate bundle.
     */
    private final HibernateBundle<DWGettingStartedConfiguration> hibernateBundle
            = new HibernateBundle<DWGettingStartedConfiguration>(
            Employee.class
    ) {

        @Override
        public DataSourceFactory getDataSourceFactory(
                DWGettingStartedConfiguration configuration
        ) {
            return configuration.getDataSourceFactory();
        }

    };

    @Override
    public String getName() {
        return "DWGettingStarted";
    }

    @Override
    public void initialize(final Bootstrap<DWGettingStartedConfiguration> bootstrap) {
        // TODO: application initialization
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(final DWGettingStartedConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
        final EmployeeDAO employeeDAO
                = new EmployeeDAO(hibernateBundle.getSessionFactory());
        environment.jersey().register(new HelloResource());
        environment.jersey().register(new EmployeesResource(employeeDAO));
    }

}
