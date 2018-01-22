package cn.jeeweb.modules.tbs.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.jeeweb.modules.tbs.entity.ZmGrsdskjmx;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

public interface ZmGrsdskjmxMapper  extends BaseMapper<ZmGrsdskjmx> {
	List<ZmGrsdskjmx> mySelectList(Page<ZmGrsdskjmx> page, @Param("ew") Wrapper<ZmGrsdskjmx> wrapper);
}
