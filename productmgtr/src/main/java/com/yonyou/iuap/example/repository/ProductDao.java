package com.yonyou.iuap.example.repository;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.yonyou.iuap.example.entity.Product;
import com.yonyou.iuap.persistence.bs.dao.BaseDAO;
import com.yonyou.iuap.persistence.bs.dao.DAOException;
import com.yonyou.iuap.persistence.jdbc.framework.SQLParameter;

@Repository
public class ProductDao {
    @Autowired
    private BaseDAO dao;

    public Product queryByPK(String pk) throws DAOException {
        return dao.queryByPK(Product.class, pk);
    }

    public Page<Product> queryPage(Map<String, Object> searchParams, PageRequest pageRequest) throws DAOException {
        StringBuffer sqlBuffer = new StringBuffer("select * from good_demo where 1=1 ");
        SQLParameter sqlParameter = new SQLParameter();
        buildSql(searchParams, sqlBuffer, sqlParameter);
        String sql = sqlBuffer.toString();
        return dao.queryPage(sql, sqlParameter, pageRequest, Product.class);
    }

    public void save(Product vo) throws DAOException {
        dao.save(vo);
    }

    public void remove(Product vo) throws DAOException {
        dao.remove(vo);
    }

    public void remove(List<Product> vos) throws DAOException {
        dao.remove(vos);
    }

    //业务开发根据自己的需求，修改查询条件的拼接方式
    private void buildSql(Map<String, Object> searchParams, StringBuffer sqlBuffer, SQLParameter sqlParameter) {

        int index = 0;
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Object> entry : searchParams.entrySet()) {
            String[] keySplit = entry.getKey().split("_");
            if (keySplit.length == 2) {
                String columnName = keySplit[1];
                String compartor = keySplit[0];
                Object value = entry.getValue();
                if (value != null && StringUtils.isNotBlank(value.toString())) {

                    sb.append(columnName).append(" ").append(compartor).append(" ? ");
                    // 处理模糊查询
                    value = "like".equalsIgnoreCase(compartor) ? "%" + value + "%" : value;
                    sqlParameter.addParam(value);
                    index ++;

                    if(index != searchParams.keySet().size()){
                        sb.append(" or ");
                    }
                }
            }
        }

        String conditionSql = sb.toString();
        if(StringUtils.isNoneBlank(conditionSql)){
            sqlBuffer.append(" and (" + conditionSql.toString() + ");");
        }

    }
}
