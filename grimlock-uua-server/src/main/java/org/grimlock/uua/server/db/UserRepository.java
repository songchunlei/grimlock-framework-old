package org.grimlock.uua.server.db;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author:chunlei.song@live.com
 * @Description:
 * @Date Create in 14:57 2018-1-22
 * @Modified By:
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    // 根据用户名和密码查找用户
    User findByPhoneAndPassword(String phone, String password);
}
