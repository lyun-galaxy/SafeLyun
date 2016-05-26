# SafeLyun
## ueditor编辑器的依赖jar包

jar包放在ueditor-lib目录下，找出其中的两个jar包，配置好maven环境后使用以下命令按照到本地库。

若无法安装则下载并解压`com.paly.tar.gz`文件，放入本地库`.m2/repository/com`。

	mvn install:install-file -Dfile=‪G:\学习jar\json.jar -DgroupId=com.paly -DartifactId=json -Dversion=1.0 -Dpackaging=jar -DgeneratePom=true -DcreateChecksum=true
	
	mvn install:install-file -Dfile=‪G:\学习jar\ueditor-1.1.2.jar -DgroupId=com.paly -DartifactId=ueditor -Dversion=1.1.2 -Dpackaging=jar -DgeneratePom=true -DcreateChecksum=true
	
最后在pom.xml文件添加以下配置：

	<!-- ueditor 依赖jar包 -->
	<dependency>
		 <groupId>com.paly</groupId>
		 <artifactId>json</artifactId>
		 <version>1.0</version>
	</dependency>
	<dependency>
		 <groupId>com.paly</groupId>
		 <artifactId>ueditor</artifactId>
		 <version>1.1.2</version>
	</dependency>

## mybatis分页插件的使用方法

	// 获取第1页，10条内容，默认查询总数count
	PageHelper.startPage(1, 10);
	List<Section> list = sectionMapper.selectAll();
	//用PageInfo对结果进行包装
	PageInfo page = new PageInfo(list);
	// 测试PageInfo全部属性
	// PageInfo包含了非常全面的分页属性
	assertEquals(1, page.getPageNum());
	assertEquals(10, page.getPageSize());
	assertEquals(1, page.getStartRow());
	assertEquals(10, page.getEndRow());
	assertEquals(183, page.getTotal());
	assertEquals(19, page.getPages());
	assertEquals(1, page.getFirstPage());
	assertEquals(8, page.getLastPage());
	assertEquals(true, page.isFirstPage());
	assertEquals(false, page.isLastPage());
	assertEquals(false, page.isHasPreviousPage());
	assertEquals(true, page.isHasNextPage());
	