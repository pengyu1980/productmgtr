package com.yonyou.iuap.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yonyou.iuap.example.entity.Product;
import com.yonyou.iuap.example.repository.ProductDao;
import com.yonyou.iuap.persistence.bs.dao.DAOException;

@Service
public class ProductService {

    @Autowired
    private ProductDao dao;

    public Product getGoodById(String id) throws DAOException {
        return dao.queryByPK(id);
    }

    @Transactional
    public void deleteById(String id) throws DAOException {
        Product product = new Product();
        product.setProductid(id);
        dao.remove(product);
    }

    @Transactional
    public void batchDelete(List<String> ids) throws DAOException {
        List<Product> deleteVos = new ArrayList<Product>();
        for (int i = 0; i < ids.size(); i++) {
            Product product = new Product();
            product.setProductid(ids.get(i));
            deleteVos.add(product);
        }
        if (deleteVos.size() > 0) {
            dao.remove(deleteVos);
        }
    }

    @Transactional
    public Product saveEntity(Product entity) throws DAOException {
        dao.save(entity);
        return entity;
    }

    public Page<Product> getDemoPage(Map<String, Object> searchParams, PageRequest pageRequest) throws DAOException {
        return dao.queryPage(searchParams, pageRequest);
    }
}
