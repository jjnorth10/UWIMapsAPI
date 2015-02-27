package models.Location;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import models.Course.Course;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.annotation.JsonIgnore;

import play.db.ebean.Model;
import play.data.validation.Constraints;
@Entity
public class Location extends Model {
	@Id
	public long id;
	@Constraints.Required
	public String name;
	public String description;
	@Constraints.Required
	public double latitude;
	@Constraints.Required
	public double longitude;
	//@Column(length=100000)
	//public byte[] picture;
	public String image;
	
	@ManyToOne
	public LocationType locationType;
	@JsonIgnore
	@OneToMany(mappedBy="location")
	public List<Course> courses;
	
	public Location(){
		
	}
	
	public Location(String name,String description,double latitude,double longitude,long type,String image) {
		//this.id=id;
		this.name=name;
		this.description=description;
		this.latitude=latitude;
		this.longitude=longitude;
		this.image=image;
		this.locationType = Ebean.find(LocationType.class, type);
	}

}
