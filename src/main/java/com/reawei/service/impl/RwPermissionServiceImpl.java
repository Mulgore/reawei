package com.reawei.service.impl;

import com.baomidou.kisso.SSOAuthorization;
import com.baomidou.kisso.Token;
import com.reawei.entity.RwPermission;
import com.reawei.entity.vo.MenuVO;
import com.reawei.mapper.RwPermissionMapper;
import com.reawei.service.IRwPermissionService;
import com.reawei.service.support.BaseServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author xingwu
 * @since 2017-03-28
 */
@Service
public class RwPermissionServiceImpl extends BaseServiceImpl<RwPermissionMapper, RwPermission> implements IRwPermissionService, SSOAuthorization {


    @Override
    public boolean isPermitted(Token token, String permission) {
        return true;
    }

}
