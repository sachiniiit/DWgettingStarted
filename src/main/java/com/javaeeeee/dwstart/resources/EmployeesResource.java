package com.javaeeeee.dwstart.resources;

import com.google.common.base.Optional;
import com.javaeeeee.dwstart.core.Employee;
import com.javaeeeee.dwstart.db.EmployeeDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
public class EmployeesResource {

    /**
     * The DAO object to manipulate employees.
     */
    private EmployeeDAO employeeDAO;

    /**
     * Constructor.
     *
     * @param employeeDAO DAO object to manipulate employees.
     */
    public EmployeesResource(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    /**
     * Looks for employees whose first or last name contains the passed
     * parameter as a substring. If name argument was not passed, returns all
     * employees stored in the database.
     *
     * @param name query parameter
     * @return list of employees whose first or last name contains the passed
     * parameter as a substring or list of all employees stored in the database.
     */
    @GET
    @UnitOfWork
    public List<Employee> findByName(
            @QueryParam("name") Optional<String> name
    ) {
        if (name.isPresent()) {
            return employeeDAO.findByName(name.get());
        } else {
            return employeeDAO.findAll();
        }
    }

    /**
     * Method looks for an employee by her id.
     *
     * @param id the id of an employee we are looking for.
     * @return Optional containing the found employee or an empty Optional
     * otherwise.
     */
    @GET
    @Path("/{id}")
    @UnitOfWork
    public Optional<Employee> findById(@PathParam("id") LongParam id) {

        Optional<Employee> e = employeeDAO.findById(id.get());
        try {
            return employeeDAO.findById(id.get());
        } catch (Exception ex){
            throw ex;
        }
    }
}
