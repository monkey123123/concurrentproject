package me.monkey.gateway.util;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

public class PageHelperUtils {

    private static final int DEFAULT_PAGE = 10;

    public static boolean pageStart(Integer pageIndex)
    {
        if (pageIndex != null && pageIndex > 0)
        {
            PageHelper.startPage(pageIndex, DEFAULT_PAGE);
            return true;
        }
        return false;
    }

    public static <T> boolean pageCheckLast(int pageIndex, List<T> list)
    {
        PageInfo<T> pageInfo = new PageInfo<T>(list);
        return pageIndex > pageInfo.getPages();
    }
}
