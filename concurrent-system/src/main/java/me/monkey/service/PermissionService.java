package me.monkey.service;

import org.springframework.stereotype.Service;

//参考：  https://blog.csdn.net/qq_37857224/article/details/107885972
@Service("ss")
public class PermissionService
{	
	 /**
     * 验证用户是否具备某权限
     * 
     * @param permission 权限字符串
     * @return 用户是否具备某权限
     */
    public boolean hasPermi(String permission)
    {

        return false;
    }
}