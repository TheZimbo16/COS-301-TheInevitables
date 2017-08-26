package com.The_Inevitables.NavUP.web.api.dto.user;

import com.The_Inevitables.NavUP.web.api.dto.ApiResponse;
import com.The_Inevitables.NavUP.web.api.dto.problem.DTO;

public class UserDTO extends DTO implements ApiResponse {

	private static final long serialVersionUID = 1938248728652936986L;
	
	private int studentNumber;
	private String studentName;
	private String studentSurname;
	private short studentDisabled;
	private String studentPassword;

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
	
	public short getUserDisabled()
	{
		return studentDisabled;
	}
	
	public void setUserDisabled(short disabled)
	{
		this.studentDisabled = disabled;
	}
	
	public String getUserPassword()
	{
		return studentPassword;
	}
	
	public void setUserPassword(String password)
	{
		this.studentPassword = password;
	}
}
