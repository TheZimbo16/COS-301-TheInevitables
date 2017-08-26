package com.The_Inevitables.NavUP.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.The_Inevitables.NavUP.model.bcrypt.BCrypt;
import com.The_Inevitables.NavUP.model.super_entity.SuperEntity;

@Entity
@Table(name = "Users")
public class User implements SuperEntity {

	public User() {
		super();
	}
	
	@Id
	@Column(name = "studentNumber", unique = true, nullable = false, updatable = false)
	private int studentNumber;
	
	@Column(name = "studentName")
	private String studentName;
	
	@Column(name = "studentSurname")
	private String studentSurname;
	
	@Column(name = "studentDisabled")
	private boolean studentDisabled;
	
	@Column(name = "studentPassword")
	private String studentPassword;
	
	
	/*******************************************************/
	/*				GETTERS AND SETTERS					   */
	/*******************************************************/
	
	public int getStudentNumber()
	{
		return studentNumber;
	}
	
	public void setStudentNumber(int number)
	{
		this.studentNumber = number;
	}
	
	public String getStudentName() 
	{
		return studentName;
	}
	
	public void setStudentName(String studentName) 
	{
		this.studentName = studentName;
	}
	
	public String getUserSurname()
	{
		return studentSurname;
	}
	
	public void setUserSurname(String surname)
	{
		this.studentSurname = surname;
	}
	
	public boolean getUserDisabled()
	{
		return studentDisabled;
	}
	
	public void setUserDisabled(boolean disabled)
	{
		this.studentDisabled = disabled;
	}
	
	public String getUserPassword()
	{
		return studentPassword;
	}
	
	public void setUserPassword(String password)
	{
		this.studentPassword = encryptPassword(password);
	}
	
	private String encryptPassword(String password)
	{
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	public boolean checkMatch(String password)
	{
		if (BCrypt.checkpw(password, this.getUserPassword()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

//	@Column(name = "username")
//	private String username;
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//
//	private Long id;
//
//	
//	
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}

}
