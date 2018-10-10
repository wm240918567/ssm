package com.maybe.ssm.service;

import com.maybe.ssm.entity.SsmUser;
import com.maybe.ssm.mapper.SsmUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

@Service
@Slf4j
public class SsmUserService {
	
	@Autowired
	private SsmUserMapper ssmUserMapper;
	@Autowired
    private RedisTemplate redisTemplate;

    /**
     * 根据主键修改内容
     * @param ssmUser
     * @return
     */
	public int updateById(SsmUser ssmUser){
        return ssmUserMapper.updateById(ssmUser);
    }

    /**
     * 查找全部，放入缓存
     * @return 结果列表
     */
	public List<SsmUser> findAll(int num,int size){
        LocalTime a = LocalTime.now();

        List<SsmUser> list;
        if(!redisTemplate.hasKey("ssm_user_all_"+num+"_"+size)){
            list  = ssmUserMapper.findAll();
            redisTemplate.opsForValue().set("ssm_user_all_"+num+"_"+size,list);
        }else{
            list = (List<SsmUser>) redisTemplate.opsForValue().get("ssm_user_all_"+num+"_"+size);
        }
        LocalTime b = LocalTime.now();
        Duration between = Duration.between(a, b);
        log.info("花费时间{}",between.getNano());
        return list;
    }


    /**
     * 新增一个
     * @param ssmUser 新增的对象
     */
	@Transactional
	public SsmUser saveOne(SsmUser ssmUser) {
	    ssmUserMapper.saveOne(ssmUser);
	    return ssmUser;
	}

    /**
     * 根据姓名模糊查询
     * @param name 姓名
     * @return 模糊查询结果
     */
	public List<SsmUser> findLikeName(String name){
		return ssmUserMapper.findLikeName(name);
	}

    /**
     * 根据ID查找一个
     * @param id 主键
     * @return 一条记录
     */
	public SsmUser findById(int id) {
		return ssmUserMapper.findById(id);
	}

    /**
     * 根据ID删除一条记录
     * @param id 主键
     * @return 成功返回删除的条数
     */
    @Transactional
	public int deleteById(int id){
        return ssmUserMapper.deleteById(id);
    }

}
