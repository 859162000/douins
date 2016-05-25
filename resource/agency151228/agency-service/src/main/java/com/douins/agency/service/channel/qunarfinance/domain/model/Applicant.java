package com.douins.agency.service.channel.qunarfinance.domain.model;

public class Applicant {
	private String  cellphonenumber;				
	private String  email;			
	private String  id;			
	private String  idtype;			
	private String  name;			
	private String  sex;
	public String getcellphonenumber() {
		return cellphonenumber;
	}
	public void setcellphonenumber(String cellphonenumber) {
		this.cellphonenumber = cellphonenumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getid() {
		return id;
	}
	public void setid(String id) {
		this.id = id;
	}
	public String getidtype() {
		return idtype;
	}
	public void setidtype(String idtype) {
		this.idtype = idtype;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cellphonenumber == null) ? 0 : cellphonenumber.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idtype == null) ? 0 : idtype.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Applicant other = (Applicant) obj;
		if (cellphonenumber == null) {
			if (other.cellphonenumber != null)
				return false;
		} else if (!cellphonenumber.equals(other.cellphonenumber))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idtype == null) {
			if (other.idtype != null)
				return false;
		} else if (!idtype.equals(other.idtype))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Applicant [cellphonenumber=" + cellphonenumber + ", email="
				+ email + ", id=" + id + ", idtype=" + idtype + ", name="
				+ name + ", sex=" + sex + "]";
	}	

}
