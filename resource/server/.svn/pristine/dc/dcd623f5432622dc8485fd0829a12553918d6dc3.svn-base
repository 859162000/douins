package com.mango.framework.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mango.core.logger.SimpleLogger;
import com.mango.fortune.user.model.User;
import com.mango.framework.basic.service.RoleService;
import com.mango.framework.basic.service.UserRoleService;
import com.mango.orm.BaseDao;

public class JcUserDetailsService implements UserDetailsService {
	private SimpleLogger logger = SimpleLogger.getLogger(JcUserDetailsService.class);
	@Autowired
	private BaseDao<User> userDao;
	private String mapper = User.class.getName() + "Mapper.";
	private static Map<String, User> userMap = new ConcurrentHashMap();
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	@Qualifier("roleService")
	private RoleService roleService;
	

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User loginUser = getUserByAccount(username);
		if (loginUser == null) {
			logger.warn((new StringBuilder("loadUserByUsername ")).append(username).append(" not found.").toString());
			throw new UsernameNotFoundException((new StringBuilder("User ")).append(username).append(" is not exist!").toString());
		} else {
			addUserLocal(loginUser);
			Collection authsColl = loadAuthoritiesByUserName(loginUser.getUserId());
			org.springframework.security.core.userdetails.User securityUser = new org.springframework.security.core.userdetails.User(loginUser.getUserAccount(),loginUser.getLoginPassword(), true, true, true, true, authsColl);
			return securityUser;
		}
	}

	public User getUserByAccount(String account) {
		return (User) userDao.get((new StringBuilder(String.valueOf(mapper))).append("getUserByAccount").toString(), account);
	}

	private Collection<GrantedAuthority> loadAuthoritiesByUserName(String userId) {
		Collection authsColl = new ArrayList();
		return authsColl;
	}
	
	private void addUserLocal(User user)
    {
        String uid = user.getUserId();
        if(uid == null)
            return;
        if(!userMap.containsKey(uid))
            userMap.put(uid, user);
    }

}
