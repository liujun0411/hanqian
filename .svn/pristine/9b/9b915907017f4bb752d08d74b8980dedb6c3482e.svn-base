/**
 * 
 */
package com.hanqian.business;

import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.hanqian.drools.DroolsCommon;

/**
 *
 */
@Service

public class OctopusInitBusibess {
	private static final Log log = LogFactory.getLog(OctopusInitBusibess.class);
	
	public static KnowledgeBase Base=null;
	
	
	
	@javax.annotation.Resource
//	private OctopusService octopusService;
	
//	@Value("classpath*:rules/*.xls")
//	private Resource[] ruleResources;
	
	@Value("classpath*:rules/*.drl")
	private Resource[] ruleDrlResources;
	
//	@javax.annotation.Resource
//	private Ehcache iBessCache;
	
	
	
	

	@PostConstruct
	public void init() {
				
		//加载关注相关数据
//		initFocusInfo();
		
		//加载规则文件及数据库内规则
		initRules();
	}
	
	
//	@SuppressWarnings("deprecation")
	public void initRules() {

		
			DroolsCommon droolsCommon = new DroolsCommon();			
			//构建加载文件库                   第一步
			KnowledgeBase knowBase = droolsCommon.getKnowledgeBase();
			
			
			
            //放入缓存     第二步
//			Element e1 = new Element("drools_rule_base", knowBase);
//			e1.setEternal(true);
//			iBessCache.put(e1);
//			log.info("初始化规则对象已经写入缓存.");
			
			try {
				//加载drl规则文件
				if (ruleDrlResources != null) {
					
					//创建工厂
					KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

					//读取文件 drl
					for (int i = 0; i < ruleDrlResources.length; i++) {
//						if (log.isDebugEnabled()) log.debug("初始化加载规则DRL文件,文件名:{}", ruleDrlResources[i].getFilename());
						
						
				InputStream	fiel=	ruleDrlResources[i].getInputStream();
						
						kbuilder.add(ResourceFactory.newInputStreamResource(ruleDrlResources[i].getInputStream()), ResourceType.DRL);
						//读取文件失败
//						if (kbuilder.hasErrors()) {
////							log.error("初始化加载规则DRL文件时出现异常,文件名:{},异常信息:{}", ruleDrlResources[i].getFilename(), kbuilder.getErrors().toString());
//						}
					}
                   //加载读取文件的类
					knowBase.addKnowledgePackages(kbuilder.getKnowledgePackages());
					Base=knowBase;
				}
			} catch (Exception e) {
				log.error("初始化加载规则drl文件时出现异常", e);
			}
			
			
	}		
			
			
			
//			try {
//				
//				//第三步
//				//加载excel规则文件
//				if (ruleResources != null) {
//					DecisionTableConfiguration dtableconfiguration = KnowledgeBuilderFactory
//							.newDecisionTableConfiguration();
//					dtableconfiguration.setInputType(DecisionTableInputType.XLS);
//					
//				//	第四步
//					//创建加载文件的工厂
//					KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
//                  //第五步
//					// 循环读取文件
//					for (int i = 0; i < ruleResources.length; i++) {
//						if (log.isDebugEnabled()) log.debug("初始化加载规则EXCEL文件,文件名:{}", ruleResources[i].getFilename());
//						
//						//第六布
//						//读取文件 
//						kbuilder.add(ResourceFactory.newInputStreamResource(ruleResources[i].getInputStream()),
//								ResourceType.DTABLE, dtableconfiguration);
//						//读取文件失败
//						if (kbuilder.hasErrors()) {
//							log.error("初始化加载规则EXCEL文件时出现异常,文件名:{},异常信息:{}", ruleResources[i].getFilename(), kbuilder.getErrors().toString());
//						}
//					}
//                 
//					
//					//第七步
//					
//					//加载读取文件的类
//					knowBase.addKnowledgePackages(kbuilder.getKnowledgePackages());
//				}
//			} catch (Exception e) {
//				log.error("初始化加载规则EXCEL文件时出现异常", e);
//			}		


}
