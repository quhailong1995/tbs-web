package cn.jeeweb.modules.tbs.service.impl;
import java.io.Serializable;
import java.util.List;
import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.utils.CacheUtils;
import cn.jeeweb.modules.tbs.mapper.TbAuthContentMapper;
import cn.jeeweb.modules.tbs.entity.TbAuthContent;
import cn.jeeweb.modules.tbs.entity.TbAuthService;
import cn.jeeweb.modules.tbs.service.ITbAuthContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.mapper.Wrapper;

/**   
 * @Title: 授权内容管理
 * @Description: 授权内容管理
 * @author QuHaiLong
 * @date 2017-08-14 14:38:37
 * @version V1.0   
 *
 */
@Transactional
@Service("tbAuthContentService")
public class TbAuthContentServiceImpl  extends CommonServiceImpl<TbAuthContentMapper,TbAuthContent> implements  ITbAuthContentService {
//	@Autowired
//	private TbAuthContentExtMapper tbAuthContentExtMapper;
	@Autowired
	private TbAuthContentMapper tbAuthContentMapper;
	
	
	@Override
	public boolean deleteBatchIds(List<? extends Serializable> idList) {
		// TODO Auto-generated method stub
//		//把授权内容附表也删除了
//		tbAuthContentExtMapper.deleteBatchIds(idList);
		//再调用父类删除全部授权内容信息
		for(Serializable asid : idList){
			CacheUtils.remove("tbAuthContentCache", "asId_"+asid);
		}
		
		return super.deleteBatchIds(idList);
	}
	
	@Override
	public void saveOrUpdateTbAuthContent(final TbAuthContent entity , TbAuthService tbAuthService){
	


		
	
		String allScontent = entity.getScontentId();
		String[] sc = allScontent.split(",");//以‘，’获取里面的各个字段吗，前两个是authContentExt表的type 和term  后面都是服务内容id
		
		//保存authContent表
		//List<TbAuthContent> tbAuthContentList = new ArrayList<TbAuthContent>();
		TbAuthContent tbAuthContent = new TbAuthContent();
		tbAuthContent.setId(tbAuthService.getId());
		tbAuthContent.setChannelId(tbAuthService.getChannelId());
		tbAuthContent.setServiceId(tbAuthService.getServiceId());
		
		for(int i=0;i<sc.length;i++){
			tbAuthContent.setScontentId(sc[i]);
			//tbAuthContentList.add(tbAuthContent);
			//System.out.println("((((((((((((((((((((((((((((((((((((((((((((((((((("+sc[i]);
			tbAuthContentMapper.insert(tbAuthContent);
		}
		//System.out.println("((((((((((((((((((((((((((((((((((((((((((((((((((("+tbAuthContentList);
		
		
		
//		//保存authContentExt表
//		
//		TbAuthContentExt tbAuthContentExt = tbAuthContentExtMapper.selectById(tbAuthService.getId());
//		
//		if(tbAuthContentExt==null){
//			tbAuthContentExt = new TbAuthContentExt();
//			tbAuthContentExt.setId(tbAuthService.getId());
//			tbAuthContentExt.setAuthType(sc[0]);
//			tbAuthContentExt.setAuthTerm(Integer.parseInt(sc[1]));
//			
//			tbAuthContentExtMapper.insert(tbAuthContentExt);
//	
//		}else{
//			tbAuthContentExt.setAuthType(sc[0]);
//			tbAuthContentExt.setAuthTerm(Integer.parseInt(sc[1]));
//			
//			tbAuthContentExtMapper.updateById(tbAuthContentExt);
//			
//		}
		
		CacheUtils.remove("tbAuthContentCache", "asId_"+tbAuthService.getId());
	
	}
	
	
	
	@Override
	public boolean deleteById(Serializable id) {
		// TODO Auto-generated method stub
//		tbAuthContentExtMapper.deleteById(id);
		return super.deleteById(id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TbAuthContent> selectList(Wrapper<TbAuthContent> wrapper) {
		// TODO Auto-generated method stub
		List<TbAuthContent> tbAuthContentList = null;
		String asId = wrapper.getSqlSegment().split("'")[1];
		tbAuthContentList = (List<TbAuthContent>) CacheUtils.get("tbAuthContentCache", "asId_"+asId);
		if(tbAuthContentList!=null){
			return tbAuthContentList;
		}
		tbAuthContentList = super.selectList(wrapper);
		CacheUtils.put("tbAuthContentCache", "asId_"+asId, tbAuthContentList);
		return tbAuthContentList;
	}
	

}
