package com.easylinker.iot.v2.aop;

import com.easylinker.iot.v2.annotation.EasyApi;
import com.easylinker.iot.v2.annotation.EasyLinkerApiEntry;
import com.easylinker.iot.v2.annotation.ProjectInformation;
import com.easylinker.iot.v2.utils.PDFDocumentGenerator;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by wwhai on 2017/11/18.
 */
//@Component
public class APIContextAware implements ApplicationContextAware {
    StringBuffer stringBuffer = new StringBuffer();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (applicationContext.getParent() == null) {
            Map<String, Object> easyLinkerApiEntryMap = applicationContext.getBeansWithAnnotation(EasyLinkerApiEntry.class);
            Map<String, Object> projectInformationMap = applicationContext.getBeansWithAnnotation(ProjectInformation.class);
            for (Object object : projectInformationMap.values()) {
                for (Annotation annotation : object.getClass().getAnnotations()) {
                    if (annotation.annotationType().equals(ProjectInformation.class)) {
                        stringBuffer.append("\n-------------------------------------\n");
                        stringBuffer.append("\n项目名称:" + ((ProjectInformation) annotation).name() + "\n");
                        stringBuffer.append("开始时间:" + ((ProjectInformation) annotation).startTime() + "\n");
                        stringBuffer.append("结束时间:" + ((ProjectInformation) annotation).endTime() + "\n");
                        stringBuffer.append("负责人:" + ((ProjectInformation) annotation).charger() + "\n");
                        stringBuffer.append("备注:" + ((ProjectInformation) annotation).remarks() + "\n");
                        stringBuffer.append("\n-------------------------------------\n");
                    }
                }

                for (Object bean : easyLinkerApiEntryMap.values()) {
                    if (bean != null) {
                        Method[] methods = bean.getClass().getDeclaredMethods();
                        for (Method method : methods) {
                            EasyApi easyApi = method.getDeclaredAnnotation(EasyApi.class);
                            if (easyApi != null) {
                                stringBuffer.append("\n-------------------------------------\n");
                                stringBuffer.append("API作者 " + easyApi.author() + "\n");
                                stringBuffer.append("API名称 " + easyApi.name() + "\n");
                                stringBuffer.append("API路由 " + easyApi.router() + "\n");
                                stringBuffer.append("API参数 " + easyApi.param().toString() + "\n");
                                stringBuffer.append("-------------------------------------\n");
                                try {
                                    //PDFDocumentGenerator.outputPDFDocument(stringBuffer.toString());

                                } catch (Exception e) {
                                    System.err.println("PDF生成失败!");
                                    e.printStackTrace();

                                }
                            }
                        }


                    }
                }
            }

        }
    }
}
