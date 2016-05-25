package com.mango.framework.basic.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.mango.core.common.util.JsonUtils;
import com.mango.core.common.util.MD5;
import com.mango.core.logger.SimpleLogger;
import com.mango.core.tree.JcTreeNode;
import com.mango.core.tree.TreeFormat;
import com.mango.exception.DataBaseAccessException;
import com.mango.fortune.user.model.User;
import com.mango.framework.basic.model.Role;
import com.mango.framework.basic.model.UserRole;
import com.mango.framework.basic.service.LoginUserService;
import com.mango.framework.basic.service.RoleService;
import com.mango.framework.basic.service.UserRoleService;
import com.mango.orm.BaseDao;
import com.mango.orm.page.Page;

@Service("loginUserService")
public class LoginUserServiceImpl implements LoginUserService {
	private SimpleLogger logger = SimpleLogger.getLogger(getClass());

	@Autowired
	private BaseDao<User> userDao;

	@Autowired
	@Qualifier("checkedTreeformat")
	TreeFormat treeFormat;

	@Autowired
	RoleService roleService;

	@Autowired
	UserRoleService userRoleService;
	private static Map<String, User> userMap = new ConcurrentHashMap<String, User>();

	private String U_A = "0000000000000000000000000000J919";

	private String mapper = User.class.getName() + "Mapper.";

	public boolean isAdmin(String userId) {
		if (StringUtils.isBlank(userId)) {
			return Boolean.FALSE.booleanValue();
		}
		return this.U_A.equals(userId);
	}

	public User getUserByAccount(String account) {
		return (User) this.userDao.get(this.mapper + "getUserByAccount",
				account);
	}

	public User getRegisterUserByAccount(String account) {
		return (User) this.userDao.get(
				this.mapper + "getRegisterUserByAccount", account);
	}

	private User getUserById(String userId) {
		return (User) this.userDao.get(this.mapper + "selectByPrimaryKey",
				userId);
	}

	public User findByKey(String key) {
		return (User) this.userDao.get(this.mapper + "selectByPrimaryKey", key);
	}

	public Page<User> getPage(User user, Page<User> page) {
		if (page != null)
			this.userDao.getList(this.mapper + "getUserList", user, page);
		return page;
	}

	public boolean updateLoginInfo(User user) {
		try {
			return this.userDao.update(this.mapper + "updateLoginInfo", user) > 0;
		} catch (DataBaseAccessException e) {
			this.logger.error("updateUser DataBaseAccessException", e);
		}
		return Boolean.FALSE.booleanValue();
	}

	public boolean updatePassword(User user) {
		try {
			if (this.userDao.update(this.mapper + "updatePassword", user) > 0) {
				removeUserLocal(user.getUserId());
				return Boolean.TRUE.booleanValue();
			}
		} catch (DataBaseAccessException e) {
			this.logger.error("updatePassword DataBaseAccessException", e);
		}
		return Boolean.FALSE.booleanValue();
	}

	public boolean save(String user, User t) {
		try {
			return this.userDao.save(this.mapper + "insertSelective", t) > 0;
		} catch (DataBaseAccessException e) {
			this.logger.error("create user DataBaseAccessException", e);
		}
		return Boolean.FALSE.booleanValue();
	}

	public boolean update(String user, User t) {
		try {
			if (this.userDao.update(
					this.mapper + "updateByPrimaryKeySelective", t) > 0) {
				removeUserLocal(t.getUserId());
				return Boolean.TRUE.booleanValue();
			}
		} catch (DataBaseAccessException e) {
			this.logger.error("update user DataBaseAccessException", e);
		}
		return Boolean.FALSE.booleanValue();
	}

	public boolean delete(String user, User t) {
		try {
			if (this.userDao.delete(this.mapper + "deleteByPrimaryKey",
					t.getUserId()) > 0) {
				removeUserLocal(t.getUserId());
				return Boolean.TRUE.booleanValue();
			}
		} catch (DataBaseAccessException e) {
			this.logger.error("delete user DataBaseAccessException", e);
		}
		return Boolean.FALSE.booleanValue();
	}

	private void addUserLocal(User user) {
		String uid = user.getUserId();
		if (uid == null)
			return;
		if (!userMap.containsKey(uid))
			userMap.put(uid, user);
	}

	public User getUserLocal(String userId) {
		if (StringUtils.isBlank(userId))
			return null;
		if (userMap.containsKey(userId)) {
			return (User) userMap.get(userId);
		}
		User uu = getUserById(userId);
		addUserLocal(uu);
		return uu;
	}

	public void removeUserLocal(String userId) {
		if (StringUtils.isBlank(userId))
			return;
		userMap.remove(userId);
	}

	private Collection<GrantedAuthority> loadAuthoritiesByUserName(String email) {
		Collection<GrantedAuthority> authsColl = new ArrayList<GrantedAuthority>();
		return authsColl;
	}

	public boolean produceDefautUser(String account, String name,
			String companyId) {
		User def = new User();
		if (StringUtils.isNotBlank(account)) {
			def.initPrimaryKey();
			if (account.length() <= 5)
				def.setUserAccount(account + "Admin");
			else {
				def.setUserAccount(account.substring(0, 5) + "Admin");
			}
			def.setUserName(name + "管理员");
			def.setLoginPassword(MD5.getStrMD5("123456"));
			def.setStatus("1");
			def.setUpdateDate(new Timestamp(System.currentTimeMillis()));
			if (save("system auto", def)) {
				UserRole userRole = new UserRole();
				userRole.initPrimaryKey();
				userRole.setUserId(def.getUserId());
				userRole.setRoleId(this.roleService.getDefautRole());
				if (this.userRoleService.save(userRole))
					this.userRoleService.clear();
			}
			return Boolean.TRUE.booleanValue();
		}
		return Boolean.FALSE.booleanValue();
	}

	public String getUserRoleTree(String curUser, String opUser) {
		UserRole userRole = new UserRole();
		List<String> allro = new ArrayList<String>();
		if (isAdmin(curUser)) {
			List<Role> allRoles = this.roleService.getRoleList(new Role());
			for (Role r : allRoles)
				allro.add(r.getRoleId());
		} else {
			userRole.setUserId(curUser);
			List<UserRole> allRoles = this.userRoleService
					.getUserRoles(userRole);
			for (UserRole ur : allRoles) {
				allro.add(ur.getRoleId());
			}
		}
		userRole.setUserId(opUser);

		List<UserRole> hadRoles = this.userRoleService.getUserRoles(userRole);
		StringBuffer rtnJson = new StringBuffer();
		Object had = new HashSet<String>();
		for (UserRole ur : hadRoles) {
			((Set) had).add(ur.getRoleId());
		}
		List<Role> roleList = null;
		if (allro.isEmpty())
			roleList = new ArrayList<Role>();
		else {
			roleList = this.roleService.getListByIds(allro);
		}
		JcTreeNode ksTreeNode = null;
		StringBuffer jbf = null;
		for (Role role : roleList) {
			ksTreeNode = new JcTreeNode();
			String gr = role.getRoleId();
			ksTreeNode.setId(gr);
			ksTreeNode.setNodeValue(role.getName());
			ksTreeNode.setParentId("");
			ksTreeNode.setChecked(((Set) had).contains(gr));
			jbf = this.treeFormat.getLeafNode(ksTreeNode);
			rtnJson.append(jbf);
			rtnJson.append(",");
		}
		return rtnJson.length() > 0 ? rtnJson.toString().substring(0,
				rtnJson.length() - 1) : "";
	}

	public String saveUserRoleTree(String userId, String roleStr) {
		if (StringUtils.isBlank(userId))
			return JsonUtils.buildJsonRtnEro("");
		if (roleStr == null)
			roleStr = "";
		String roles[] = roleStr.split(",");
		List newList = new ArrayList();
		UserRole userRole = null;
		Set hadSet = userRoleService.getRoles(userId);
		if (hadSet == null)
			hadSet = new HashSet();
		int ef = 0;
		String as[];
		int j = (as = roles).length;
		for (int i = 0; i < j; i++) {
			String temroleId = as[i];
			newList.add(temroleId);
			if (!StringUtils.isBlank(temroleId) && !hadSet.contains(temroleId)) {
				userRole = new UserRole();
				userRole.setUserId(userId);
				userRole.setRoleId(temroleId);
				if (userRoleService.save(userRole))
					ef++;
			}
		}

		Iterator iteratorHad = hadSet.iterator();
		List hadList = new ArrayList();
		for (; iteratorHad.hasNext(); hadList.add((String) iteratorHad.next()))
			;
		hadList.removeAll(newList);
		if (!hadList.isEmpty()) {
			for (Iterator iterator = hadList.iterator(); iterator.hasNext();) {
				String delRole = (String) iterator.next();
				userRole = new UserRole();
				userRole.setUserId(userId);
				userRole.setRoleId(delRole);
				if (userRoleService.delete(userRole))
					ef++;
			}

		}
		if (ef > 0)
			userRoleService.clear();
		return JsonUtils.buildJsonRtnSuc();
	}
}