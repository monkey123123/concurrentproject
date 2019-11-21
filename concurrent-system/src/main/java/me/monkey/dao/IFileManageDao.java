package me.monkey.dao;

import com.beanpodtech.pkms.common.model.FileInfo;

import java.util.HashMap;
import java.util.List;

/**
 * Created by renzhibin on 17-8-18.
 */
public interface IFileManageDao {

    public void insertFileInfo(FileInfo fileInfo);

    public void updateFileInfo(FileInfo fileInfo);

    public List<FileInfo> selectFileInfo(HashMap param);
}
