package org.app.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.app.model.Course;
import org.app.service.ICourseService;

import java.util.List;
import java.util.Optional;

@RequestScoped
@Path("/courses")
@Produces(MediaType.APPLICATION_JSON)
public class AppController {

    @Inject
    private ICourseService courseService;

    @GET
    @Path("/find")
    public List<Course> findAll(){
        return this.courseService.findAll();
    }

    @GET
    @Path("/find/{id}")
    public Response findById(@PathParam("id") Long id){
        Optional<Course> optionalCourse = this.courseService.findById(id);
        if(optionalCourse.isPresent()){
            return Response.ok(optionalCourse.get()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(Course course){
        try {
            this.courseService.save(course);
        return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, Course course){
        try {
            Optional<Course> courseOptional = this.courseService.findById(id);
            if(courseOptional.isPresent()){

                Course newCourse = courseOptional.get();
                newCourse.setName( course.getName() );
                newCourse.setCategory( course.getCategory() );
                newCourse.setTeacher( course.getTeacher() );
                newCourse.setDateStart( course.getDateStart() );
                newCourse.setDateEnd( course.getDateEnd() );

                this.courseService.save(newCourse);
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteById(@PathParam("id") Long id){
        Optional<Course> courseOptional = this.courseService.findById(id);
        if(courseOptional.isPresent()){
            this.courseService.deleteById(courseOptional.get());
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
