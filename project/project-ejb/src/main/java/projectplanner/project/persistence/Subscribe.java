package projectplanner.project.persistence;


import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Subscribe
 *
 */
@Entity

public class Subscribe implements Serializable {

	@EmbeddedId
	private IdSubscribe Id;
	private String state;
	
	private static final long serialVersionUID = 1L;

	public Subscribe() {
		super();
	}   
	public IdSubscribe getId() {
		return this.Id;
	}

	public void setId(IdSubscribe Id) {
		this.Id = Id;
	}   
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}
   
}
