package cn.jeeweb.modules.tbs.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.tbs.entity.ZmGrsdskjmx;
import cn.jeeweb.modules.tbs.helper.DynamicDataSource;
import cn.jeeweb.modules.tbs.mapper.ZmGrsdskjmxMapper;
import cn.jeeweb.modules.tbs.service.IZmGrsdskjmxService;


@Transactional(propagation = Propagation.SUPPORTS)
@Service("zmGrsdskjmxService")
public class ZmGrsdskjmxServiceImpl   extends CommonServiceImpl<ZmGrsdskjmxMapper,ZmGrsdskjmx> implements  IZmGrsdskjmxService{
	
	@Override
	public Page<ZmGrsdskjmx> selectPage(Page<ZmGrsdskjmx> page,
			Wrapper<ZmGrsdskjmx> wrapper) {
		// TODO Auto-generated method stub
		System.out.println("_________________________________________1");
		
		try{
			DynamicDataSource.setDataSource("db_qz");
			page = super.selectPage(page, wrapper);
		}catch(Exception e){
			throw e;
		}finally{
			DynamicDataSource.setDataSource("db_sys");
		}
		
		return page;
	}
	
	@Override
	public Page<ZmGrsdskjmx> selectPage(Page<ZmGrsdskjmx> page) {
		// TODO Auto-generated method stub
		System.out.println("_________________________________________2");
		try{
			DynamicDataSource.setDataSource("db_qz");
			page = super.selectPage(page);
		}catch(Exception e){
			throw e;
		}finally{
			DynamicDataSource.setDataSource("db_sys");
		}

		
		return page;
	}
	
	@Override
	public cn.jeeweb.core.query.data.Page<ZmGrsdskjmx> list(
			Queryable queryable, Wrapper<ZmGrsdskjmx> wrapper) {
		// TODO Auto-generated method stub
		System.out.println("_________________________________________3");
		cn.jeeweb.core.query.data.Page<ZmGrsdskjmx> p = null;
		try{
			DynamicDataSource.setDataSource("db_qz");
			p = super.list(queryable, wrapper);
		}catch(Exception e){
			throw e;
		}finally{
			DynamicDataSource.setDataSource("db_sys");
		}

		
		return p;
	}
	
	@Override
	public cn.jeeweb.core.query.data.Page<ZmGrsdskjmx> list(Queryable queryable) {
		// TODO Auto-generated method stub
		System.out.println("_________________________________________4");
		cn.jeeweb.core.query.data.Page<ZmGrsdskjmx> p = null;
		try{
			DynamicDataSource.setDataSource("db_qz");
			p = super.list(queryable);
		}catch(Exception e){
			throw e;
		}finally{
			DynamicDataSource.setDataSource("db_sys");
		}

		return  p;
	}
	
	@Override
	public int selectCount(Wrapper<ZmGrsdskjmx> wrapper) {
		// TODO Auto-generated method stub
		System.out.println("_________________________________________5");
		int c = 0;
		try{
			DynamicDataSource.setDataSource("db_qz");
			c = super.selectCount(wrapper);
		}catch(Exception e){
			throw e;
		}finally{
			
			DynamicDataSource.setDataSource("db_sys");}
		
		
		return c;
	}
	
	

}
