package com.reawei.service;

import com.baomidou.kisso.Token;
import com.baomidou.mybatisplus.service.IService;
import com.reawei.entity.RwPermission;
import com.reawei.entity.vo.MenuVO;

import java.util.List;

/**
 *
 * Permission 表数据服务层接口
 *
 */
public interface IRwPermissionService extends IService<RwPermission> {


	boolean isPermitted(Token token, String permission);

}