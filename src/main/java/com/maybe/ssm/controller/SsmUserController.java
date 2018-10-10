package com.maybe.ssm.controller;

import com.github.pagehelper.PageHelper;
import com.maybe.ssm.entity.SsmUser;
import com.maybe.ssm.service.SsmUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class SsmUserController {
	
	@Autowired
	private SsmUserService ssmUserService;

    /**
     * 带分页的查找全部
     * @param num 第几页 1开头
     * @param size 每页大小
     * @return 对象列表
     */
	@GetMapping("/{num}/{size}")
	public List<SsmUser> findAll(@PathVariable("num")int num,@PathVariable("size")int size){
	    log.info("{}的信息：","123");
		PageHelper.startPage(num,size);
		return ssmUserService.findAll(num,size);
	}

    /**
     * 根据姓名模糊查询
     * @param name 姓名
     * @return 实体列表
     */
	@GetMapping("/l/{name}")
	public List<SsmUser> findLikeName(@PathVariable("name") String name){
		return ssmUserService.findLikeName("%"+name+"%");
	}

    /**
     * 根据ID查找
     * @param id 主键
     * @return 实体
     */
	@GetMapping("/{id}")
	public SsmUser findDemoById(@PathVariable("id") int id) {
		return ssmUserService.findById(id);
	}

    /**
     * 保存一个
     * @param ssmUser 要保存的对象，如果使用@Options，则也是返回的实体
     * @return 要保存的对象，如果使用@Options，则也是返回的实体
     */
    @PostMapping("/")
    public SsmUser save(@RequestBody SsmUser ssmUser) {
        return ssmUserService.saveOne(ssmUser);
    }

    /**
     * 根据ID删除
     * @param id 主键
     * @return 成功的条数
     */
	@DeleteMapping("/{id}")
	public int deleteById(@PathVariable("id") int id) {
		return ssmUserService.deleteById(id);
	}

    /**
     * 根据主键修改内容
     * @param id 主键
     * @param ssmUser 修改的内容
     * @return 成功的条数
     */
	@PutMapping("/{id}")
    public int updateById(@PathVariable("id")int id,@RequestBody SsmUser ssmUser){
	    ssmUser.setId(id);
        System.out.println(ssmUser.toString());
	    return ssmUserService.updateById(ssmUser);
    }
}
