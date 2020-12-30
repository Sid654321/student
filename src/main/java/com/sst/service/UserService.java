package com.sst.service;

import com.sst.entity.User;

import javax.annotation.Resource;

import com.sst.mapper.UserMapper;
import com.sst.utils.PageHelperUtils;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public int create(User user) {
        return userMapper.create(user);
    }

    public int delete(String ids) {
        String[] strings = ids.split(",");
        int flag = 0;
        for (String id:strings) {
            flag = userMapper.delete(Integer.parseInt(id));
        }
        return flag;
    }

    public int update(User user) {
        return userMapper.update(user);
    }

    public int updateSelective(User user) {
        return userMapper.updateSelective(user);
    }

    public List<User> query(User user) {
        PageHelperUtils.pageHelper(user);
        return userMapper.query(user);
    }

    public User detail(Integer id) {
        User user = new User();
        user.setId(id);
        return userMapper.detail(user);
    }

    public User login(User user) {
        return userMapper.detail(user);
    }


    public int count(User user) {
        return userMapper.count(user);
    }
}
