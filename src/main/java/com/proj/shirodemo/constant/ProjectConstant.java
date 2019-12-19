package com.proj.shirodemo.constant;

/**
 * 项目常量
 *
 * @author wuge
 * @date 2019/12/19
 */
public final class ProjectConstant {
	/**
	 * 项目父包名
	 */
	public static final String PROJECT_PACKAGE = "com.proj.shirodemo";
	/**
	 * 全局基础包名
	 */
	public static final String CORE_PACKAGE = PROJECT_PACKAGE + ".core";
	/**
	 * 生成代码所在的基础包名
	 */
	public static final String BASE_PACKAGE = PROJECT_PACKAGE + ".manage";
	/**
	 * 生成的entity所在包
	 */
	public static final String MODEL_PACKAGE = BASE_PACKAGE + ".entity";
	/**
	 * model基类
	 */
	public static final String MODEL_BASE_CLASS = CORE_PACKAGE + ".entity.BaseEntity";
	/**
	 * 生成的Mapper所在包
	 */
	public static final String MAPPER_PACKAGE = BASE_PACKAGE + ".dao";
	/**
	 * 生成的Service所在包
	 */
	public static final String SERVICE_PACKAGE = BASE_PACKAGE + ".service";
	/**
	 * 生成的ServiceImpl所在包
	 */
	public static final String SERVICE_IMPL_PACKAGE = SERVICE_PACKAGE + ".impl";
	/**
	 * 生成的Controller所在包
	 */
	public static final String CONTROLLER_PACKAGE = BASE_PACKAGE + ".controller";
	/**
	 * Mapper插件基础接口的完全限定名
	 */
	public static final String MAPPER_MANAGE_REFERENCE = CORE_PACKAGE + ".mapper.Mapper";
}
