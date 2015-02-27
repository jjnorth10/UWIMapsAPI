package models.Location;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import play.data.validation.Constraints;

import play.db.ebean.Model;
@Entity
public class LocationType extends Model {
	@Id
	public long id;
	@Constraints.Required
	public String type;
	@Constraints.Required
	public String description;
	@JsonIgnore
	@OneToMany(mappedBy="locationType")
	public List<Location> locations;

	public LocationType() {
		// TODO Auto-generated constructor stub
	}
	
	public LocationType(String type,String description) {
		this.type = type;
		this.description = description;
	}

}
