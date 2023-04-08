package entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
@NamedQuery(name = "User.findAll" , query = "SELECT u FROM User u")
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="users_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="users_id_seq")
	// @GeneratedValue(strategy = GenerationType.IDENTITY) Esta es para mysql
	private Integer id;
	
	@Column
	private String name;
	
	@Column
	private String email;
	
	@Column(name="country")
	private String pais;
	
	public User(String name, String email, String pais) {
		this.name = name;
		this.email = email;
		this.pais = pais;
	}
	
	public User(int id) {
		this.id = id;
	}
}