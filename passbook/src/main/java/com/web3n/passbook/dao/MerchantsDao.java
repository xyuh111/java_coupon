package com.web3n.passbook.dao;

import com.web3n.passbook.entity.Merchants;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by macro on MerchantsDao.
 * Merchants Dao 接口
 **/
/** 第一个是实体对象，第二个是主键类型 */
public interface MerchantsDao extends JpaRepository<Merchants, Integer> {
    /**
     * 通过 id 获取商户对象
     * @param id 商户id
     * @return {@link Merchants}
     */
    Optional<Merchants> findById(Integer id);

    /**
     * 根据商户名称获取商户对象
     * @param name 商户名称
     * @return {@link Merchants}
     */
    Merchants findByName(String name);

    /**
     * 根据商户 ids 获取多个商户对象
     * @param ids 商户 ids
     * @return {@link Merchants}
     */
    List<Merchants> findByIdIn(List<Integer> ids);

}
