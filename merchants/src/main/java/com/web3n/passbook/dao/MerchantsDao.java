package com.web3n.passbook.dao;

import com.web3n.passbook.entity.Merchants;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Merchants Dao 接口
 * Created by macro on MerchantsDao.
 **/
// 只需要定义接口，Merchants 表 ，Integer 主键的类型
public interface MerchantsDao extends JpaRepository<Merchants, Integer> {
    /**
     * 根据 id 获取商户对象
     * @param id
     * @return {@link Merchants}
     */
    Merchants findById(Integer id);

    /**
     * 根据商户名称获取商户对象
     * @param name
     * @return {@link Merchants}
     */
    Merchants findName(String name);
}
