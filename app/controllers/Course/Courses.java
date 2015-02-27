package controllers.Course;

import java.util.List;
import java.util.Map;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.ExpressionList;

import controllers.ResponseHelper;

import models.Course.Course;
import models.Location.Location;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class Courses extends Controller {

	public Courses() {
		// TODO Auto-generated constructor stub
	}
	public static Result newCourse(String subject,int code,String title,long location){
		if(subject==null || subject.isEmpty() || code == 0 || title==null || title.isEmpty()){
			return ResponseHelper.jsonResponse(ResponseHelper.BadRequest, Json.newObject(), "Missing Parameters");
		}
		Course course = new Course(subject,code,title,location);
		Ebean.save(course);
		return ResponseHelper.jsonResponse(ResponseHelper.OK, course, "Course Added");
		
	}
	
	public static Result deleteCourse(long id){
		if(id==0){
			return ResponseHelper.jsonResponse(ResponseHelper.BadRequest, Json.newObject(), "Id required");
		}
		Course course = Ebean.find(Course.class, id);
		if(course!=null){
			Ebean.delete(course);
			return ResponseHelper.jsonResponse(ResponseHelper.OK, Json.newObject(), "Course deleted");
		}
		return ResponseHelper.jsonResponse(ResponseHelper.NotFound, Json.newObject(), "Course not found");
		
	}
	
	public static Result updateCourse(long id,String subject,int code,String title,long location){
		if(id==0){
			return ResponseHelper.jsonResponse(ResponseHelper.BadRequest, Json.newObject(), "Id required");
		}
		Course course = Ebean.find(Course.class, id);
		if(course!=null){
			if(subject!=null && !subject.isEmpty()){
				course.subject=subject;
			}
			if(code!=0){
				course.code=code;
			}
			if(title!=null && !title.isEmpty()){
				course.title=title;
			}
			if(location!=0){
				course.location = Ebean.find(Location.class, location);
			}
			Ebean.update(course);
			return ResponseHelper.jsonResponse(ResponseHelper.OK, course, "Course updated");
		}
		return ResponseHelper.jsonResponse(ResponseHelper.NotFound, Json.newObject(), "Course not found");
		
	}
	public static Result list(){
		List<Course> courses = Ebean.find(Course.class).findList();
		return ResponseHelper.jsonResponse(ResponseHelper.OK, courses, "Request executed successfully");
	}
	
	public static Result find(){
		try{
	        Map<String, String[]> stringMap = request().queryString();
	        ExpressionList<Course> query = Ebean.find(Course.class).where();
	        
	            for (Map.Entry<String, String[]> entry : stringMap.entrySet()) {
	                query = query.eq(entry.getKey(), entry.getValue()[0]);
	            }
	            
	            List<Course> list = query.findList();
				return ResponseHelper.jsonResponse(ResponseHelper.OK, list, "request was successfully executed");
			}catch(Exception e){
				return ResponseHelper.jsonResponse(ResponseHelper.InternalServerError, Json.newObject(), "Internal Server Error");
			}
	}
}
