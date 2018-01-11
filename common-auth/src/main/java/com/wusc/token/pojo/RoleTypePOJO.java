package com.wusc.token.pojo;

public enum RoleTypePOJO {
	thirduser(1),//第三方用户
	maneger(2); //后台管理用户
	private int roleId;
	private RoleTypePOJO(int roleId){
		this.roleId=roleId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
}
