package com.buffll.service.impl;

import com.buffll.dao.TypeDao;
import com.buffll.entity.Type;
import com.buffll.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 分类功能的业务层实现类
 * @author pxz
 * @create 2021-03-09 16:56
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TypeServiceImpl implements TypeService {
	@Autowired(required = false)
	private TypeDao typeDao;
	
	@Override
	public Type getTypeById(Long id) {
		return typeDao.getTypeById(id);
	}
	
	@Override
	public List<Type> getAllTypes() {
		return typeDao.getAllTypes();
	}
	
	@Override
	public Type getTypeByName(String name) {
		return typeDao.getTypeByName(name);
	}
	
	@Override
	public List<Type> getBlogType() {
		return typeDao.getBlogType();
	}
	
	@Override
	@Transactional
	public Integer saveType(Type type) {
		return typeDao.saveType(type);
	}
	
	@Override
	@Transactional
	public Integer updateType(Type type) {
		return typeDao.updateType(type);
	}
	
	@Override
	@Transactional
	public void deleteType(Long id) {
		typeDao.deleteType(id);
	}
}
