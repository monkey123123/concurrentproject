package me.monkey.dao;

import com.beanpodtech.pkms.common.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * Created by renzhibin on 17-8-14.
 */
public interface IUserDao {

    public void insertUserSession(UserSession userSession);

    public void updateUserSession(UserSession userSession);

    public void deleteUserSession(int userId);

    public UserSession selectUserSession(UserSession userSession);

    public void insertUserRole(@Param("list") List<Integer> list, @Param("userId") int userId, @Param("operId") String operId);

    public void deleteUserRole(int userId);

    public List<UserRole> selectUserRole(int userId);

    public List<Role> selectRole();

    public UserInfo selectUserInfoByUserName(String userName);

    public List<UserInfo> selectUserInfoByRoles(List<Integer> roleIdList);

    public int insertUserInfo(UserInfo userInfo);

    public int updateUserInfoByUserName(UserInfo userInfo);

    public void deleteUserInfo(UserInfo userInfo);
	
	public List<Actions> selectActionsByUserName(HashMap<String, Object> map);

    public void deleteUser(UserInfo userInfo);
}
