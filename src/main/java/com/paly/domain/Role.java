package com.paly.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 角色
 * @author linyu
 *
 */
public class Role implements Serializable {

	private static final long serialVersionUID = -1435811024322160690L;

    private Integer roleId;

    /**
     * 角色名
     */
    private String roleName;
    
    /**
     * 属于多个用户
     */    
    private List<User> users;
    
    /**
     * 拥有多个菜单
     */
    private List<Menu> menus;

    public Role() {
		super();
	}

	public Role(String roleName) {
		this.roleName = roleName;
	}



	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.roleId
     *
     * @return the value of role.roleId
     *
     * @mbggenerated
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.roleId
     *
     * @param roleId the value for role.roleId
     *
     * @mbggenerated
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.roleName
     *
     * @return the value of role.roleName
     *
     * @mbggenerated
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.roleName
     *
     * @param roleName the value for role.roleName
     *
     * @mbggenerated
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
}