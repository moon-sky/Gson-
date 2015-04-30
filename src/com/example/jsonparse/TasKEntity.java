package com.example.jsonparse;

import java.util.List;

public class TasKEntity {
	List<UserEntity> users;
	int type;
	public List<UserEntity> getEntitys() {
		return users;
	}
	public void setEntitys(List<UserEntity> entitys) {
		this.users = entitys;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	

}
