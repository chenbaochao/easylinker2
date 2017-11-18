package com.easylinker.iot.v2.aop;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * Created by wwhai on 2017/11/18.
 * 这个类用来获取包含API相关的注解
 */
//@Component
public class APIContextListener implements ApplicationListener<ContextRefreshedEvent> {


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        if (contextRefreshedEvent.getApplicationContext().getParent() == null) {
//            Map<String, Object> easyLinkerApiEntryMap = contextRefreshedEvent.getApplicationContext().getBeansWithAnnotation(EasyLinkerApiEntry.class);
//            for (Object bean : easyLinkerApiEntryMap.values()) {
//                if (bean != null) {
//                    Method[] methods = bean.getClass().getDeclaredMethods();
//                    for (Method method : methods) {
//                        EasyApi easyApi = method.getDeclaredAnnotation(EasyApi.class);
//                        if (easyApi != null) {
//                            StringBuffer stringBuffer = new StringBuffer();
//                            stringBuffer.append("\n-------------------------------------\n");
//                            stringBuffer.append("API作者 " + easyApi.author() + "\n");
//                            stringBuffer.append("API名称 " + easyApi.name() + "\n");
//                            stringBuffer.append("API路由 " + easyApi.router() + "\n");
//                            stringBuffer.append("API参数 " + easyApi.param().toString() + "\n");
//                            stringBuffer.append("-------------------------------------\n");
//                            try {
//                                PDFDocumentGenerator.outputPDFDoeument(stringBuffer.toString());
//
//                            } catch (Exception e) {
//                                System.err.println("PDF生成失败!");
//
//                            }
//                        }
//                    }
//
//
//                }
//            }
        //  }
    }
}
