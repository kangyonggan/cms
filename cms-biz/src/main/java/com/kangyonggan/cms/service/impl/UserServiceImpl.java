package com.kangyonggan.cms.service.impl;

import com.github.pagehelper.PageHelper;
import com.kangyonggan.cms.dto.Params;
import com.kangyonggan.cms.dto.Query;
import com.kangyonggan.cms.model.User;
import com.kangyonggan.cms.service.UserService;
import com.kangyonggan.cms.util.StringUtil;
import com.kangyonggan.extra.core.annotation.Cache;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author kangyonggan
 * @date 3/22/18
 */
@Service
public class UserServiceImpl extends BaseService<User> implements UserService {

    @Override
    @Cache(key = "user:username:${username}")
    public User findUserByUsername(String username) {
        User user = new User();
        user.setUsername(username);
        return myMapper.selectOne(user);
    }

    @Override
    public List<User> searchUsers(Params params) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        Query query = params.getQuery();

        String username = query.getString("username");
        if (StringUtils.isNotEmpty(username)) {
            criteria.andLike("username", StringUtil.toLikeString(username));
        }

        String realname = query.getString("realname");
        if (StringUtils.isNotEmpty(realname)) {
            criteria.andLike("realname", StringUtil.toLikeString(realname));
        }

        Date beginDate = query.getDate("beginDate");
        if (beginDate != null) {
            criteria.andGreaterThanOrEqualTo("createdTime", beginDate);
        }
        Date endDate = query.getDate("endDate");
        if (endDate != null) {
            criteria.andLessThanOrEqualTo("createdTime", endDate);
        }

        String sort = params.getSort();
        String order = params.getOrder();
        if (!StringUtil.hasEmpty(sort, order)) {
            example.setOrderByClause(sort + " " + order.toUpperCase());
        }

        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        return myMapper.selectByExample(example);
    }
}
