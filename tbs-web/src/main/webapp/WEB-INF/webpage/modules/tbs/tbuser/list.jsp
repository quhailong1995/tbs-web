<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>税银用户列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="税银用户">
<grid:grid id="tbUserGridId" url="${adminPath}/tbs/tbuser/ajaxList"  multiselect="false">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	
	
	<grid:column label="证件号码"  name="zjhm" query="true" queryMode="input" condition="like"/>
	 <grid:column label="姓名"  name="xm" query="true" queryMode="input"  condition="like"/>
	  <grid:column label="证件类型"  name="zjlxDm" />
	  <grid:column label="手机号码"  name="sjhm" />
	   <grid:column label="户籍ID"  name="hjid" />
	
    <grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
	<grid:button groupname="opt" title="户籍历史" function="hjlspage" outclass="btn-info"/>
    
	
	<grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>
<script type="text/javascript">
 $(function(){
	
})

function hjlspage(title,url,gridId,id){
	
			openDialog2("户籍历史","${adminPath}/tbs/tbuser/hjlspage?id="+id,gridId,"80%","660px");
		}

</script>
</body>
</html>