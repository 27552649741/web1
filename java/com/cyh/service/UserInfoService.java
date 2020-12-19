package com.cyh.service;

import com.cyh.bean.UserInfo;
import com.cyh.bean.UserInfoExample;
import com.cyh.dao.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;

    public List<UserInfo> verifyUser(UserInfo userInfo) {
        UserInfoExample userInfoExample = new UserInfoExample();
        UserInfoExample.Criteria criteria = userInfoExample.createCriteria();
        criteria.andUsernameEqualTo(userInfo.getUsername());
        criteria.andPasswordEqualTo(userInfo.getPassword());
        List<UserInfo> userInfos = userInfoMapper.selectByExample(userInfoExample);
        return userInfos;
    }

    public boolean registerUser(UserInfo userInfo) {
        UserInfoExample userInfoExample = new UserInfoExample();
        UserInfoExample.Criteria criteria = userInfoExample.createCriteria();
        criteria.andUsernameEqualTo(userInfo.getUsername());
        List<UserInfo> userInfos = userInfoMapper.selectByExample(userInfoExample);
        if (userInfos.size() > 0) {
            return false;
        } else {
             userInfoMapper.insertSelective(userInfo);
            return true;
        }
    }

    public UserInfo getone(Integer id){
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(id);
        return userInfo;
    }

    public boolean updateUser(UserInfo userInfo){
        UserInfoExample userInfoExample = new UserInfoExample();
        UserInfoExample.Criteria criteria = userInfoExample.createCriteria();
        criteria.andIdEqualTo(userInfo.getId());
        int i = userInfoMapper.updateByExample(userInfo, userInfoExample);
        return i>0;
    }
}
