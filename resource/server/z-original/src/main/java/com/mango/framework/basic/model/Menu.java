package com.mango.framework.basic.model;

import com.mango.core.abstractclass.AbstractModel;
import com.mango.orm.KeyGenerator;
import java.util.Date;

public class Menu extends AbstractModel {
	private static final long serialVersionUID = -4523739778181207026L;
	private Integer id;
	private String menuId;
	private String menuCode;
	private String menuName;
	private String menuLink;
	private String menuResrc;
	private String menuIcon;
	private Integer menuSeq;
	private String menuStatus;
	private String parentId;
	private String parentName;
	private Date updateTime;
	private String opUser;

	public Menu() {
	}

	public Menu(Menu s) {
		if (s != null) {
			this.menuId = s.getMenuId();
			this.parentId = s.getParentId();
			this.menuCode = s.getMenuCode();
			this.menuName = s.getMenuName();
			this.menuLink = s.getMenuLink();
			this.menuResrc = s.getMenuResrc();
			this.menuIcon = s.getMenuIcon();
			this.menuSeq = s.getMenuSeq();
		}
	}

	public Menu(int total) {
	}

	public final Integer getId() {
		return this.id;
	}

	public final void setId(Integer id) {
		this.id = id;
	}

	public final String getMenuId() {
		return this.menuId;
	}

	public final void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public final String getMenuCode() {
		return this.menuCode;
	}

	public final void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public final String getMenuName() {
		return this.menuName;
	}

	public final void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public final String getMenuLink() {
		return this.menuLink;
	}

	public final void setMenuLink(String menuLink) {
		this.menuLink = menuLink;
	}

	public final String getMenuResrc() {
		return this.menuResrc;
	}

	public final void setMenuResrc(String menuResrc) {
		this.menuResrc = menuResrc;
	}

	public final String getMenuIcon() {
		return this.menuIcon;
	}

	public final void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public final Integer getMenuSeq() {
		return this.menuSeq;
	}

	public final void setMenuSeq(Integer menuSeq) {
		this.menuSeq = menuSeq;
	}

	public final String getMenuStatus() {
		return this.menuStatus;
	}

	public final void setMenuStatus(String menuStatus) {
		this.menuStatus = menuStatus;
	}

	public final String getParentId() {
		return this.parentId;
	}

	public final void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public final String getParentName() {
		return this.parentName;
	}

	public final void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public final Date getUpdateTime() {
		return this.updateTime;
	}

	public final void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public final String getOpUser() {
		return this.opUser;
	}

	public final void setOpUser(String opUser) {
		this.opUser = opUser;
	}

	public void initPrimaryKey() {
		setMenuId(KeyGenerator.randomSeqNum());
	}
}