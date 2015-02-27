package models.Course;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.avaje.ebean.Ebean;

import models.Location.Location;
import play.data.validation.Constraints;

import play.db.ebean.Model;
@Entity
public class Course extends Model {
	@Id
	public long id;
	@Constraints.Required
	public String subject;
	@Constraints.Required
	public int code;
	@Constraints.Required
	public String title;
	@ManyToOne
	public Location location;

	
	
	public Course() {
		// TODO Auto-generated constructor stub
	}
	public Course(String subject,int code,String title,long location) {
		this.subject=subject;
		this.code=code;
		this.title=title;
		this.location = Ebean.find(Location.class, id);
	}


}
