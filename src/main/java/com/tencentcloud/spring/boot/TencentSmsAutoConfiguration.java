package com.tencentcloud.spring.boot;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tencentcloud.spring.boot.sms.TencentSmsTemplate;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;

@Configuration
@ConditionalOnClass(SmsClient.class)
@ConditionalOnProperty(prefix = TencentSmsProperties.PREFIX, value = "enabled", havingValue = "true")
@EnableConfigurationProperties({ TencentSmsProperties.class })
public class TencentSmsAutoConfiguration {
	
	/* 
	 * 1、实例化 SMS 的 client 对象 第二个参数是地域信息，可以直接填写字符串 ap-guangzhou，或者引用预设的常量
	 */
	@Bean
	public SmsClient tencentSmsClient(TencentSmsProperties properties) {
		/*
		 * 实例化一个认证对象，入参需要传入腾讯云账户密钥对 secretId 和 secretKey
		 * 密钥查询：https://console.cloud.tencent.com/cam/capi
		 */
		Credential credential = new Credential(properties.getSecretId(), properties.getSecretKey());
		
		// 实例化要请求产品的client对象
		ClientProfile clientProfile = new ClientProfile();
		clientProfile.setSignMethod(ClientProfile.SIGN_TC3_256);
		
		return new SmsClient(credential, properties.getRegion(), clientProfile);
	}
	
	@Bean
	public TencentSmsTemplate tencentSmsTemplate(SmsClient smsClient, TencentSmsProperties properties) {
		return new TencentSmsTemplate(smsClient, properties);
	}

}