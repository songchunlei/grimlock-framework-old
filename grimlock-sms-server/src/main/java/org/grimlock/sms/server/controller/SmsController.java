package org.grimlock.sms.server.controller;

import org.grimlock.sms.server.domain.SmsDomain;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @Author:chunlei.song@live.com
 * @Description:
 * @Date Create in 15:54 2018-1-2
 * @Modified By:
 */

@RepositoryRestResource(collectionResourceRel = "sms",path = "sms")
public interface SmsController extends PagingAndSortingRepository<SmsDomain,Long> {

}
