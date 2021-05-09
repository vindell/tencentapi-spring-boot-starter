/*
 * Copyright (c) 2018, hiwepy (https://github.com/hiwepy).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.tencentcloud.spring.boot.tim;

import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.tencentcloud.spring.boot.tim.req.message.MsgBody;
import com.tencentcloud.spring.boot.tim.req.message.OfflinePushInfo;
import com.tencentcloud.spring.boot.tim.req.push.Condition;
import com.tencentcloud.spring.boot.tim.resp.AllMemberPushResponse;
import com.tencentcloud.spring.boot.tim.resp.ApiResponse;
import com.tencentcloud.spring.boot.tim.resp.AppAttrNameResponse;
import com.tencentcloud.spring.boot.tim.resp.UserAttrsResponse;
import com.tencentcloud.spring.boot.tim.resp.UserTagsResponse;
import com.tencentcloud.spring.boot.tim.resp.push.UserAttrs;
import com.tencentcloud.spring.boot.tim.resp.push.UserTags;

public class TencentTimAllMemberPushAsyncOperations extends TencentTimAllMemberPushOperations {

	public TencentTimAllMemberPushAsyncOperations(TencentTimTemplate timTemplate) {
		super(timTemplate);
	}
	
	/**
	 * 1、全员推送
	 * API：https://cloud.tencent.com/document/product/269/45934
	 * @param userId 业务用户ID
	 * @param msgRandom 消息随机数，由随机函数产生。用于推送任务去重。对于不同的推送请求，MsgRandom7 天之内不能重复，否则视为相同的推送任务（调用推送 API 返回失败的时候可以用相同的 MsgRandom 进行重试）
	 * @param msgBody  消息内容，具体格式请参考 MsgBody 消息内容说明（一条消息可包括多种消息元素，所以 MsgBody 为 Array 类型）
	 * @param consumer 响应处理回调函数
	 */
	public void asyncPush(String userId, String msgRandom, MsgBody[] msgBody, Consumer<AllMemberPushResponse> consumer) {
		this.asyncPush(userId, msgRandom, null, null, null, msgBody, consumer);
	}
	
	/**
	 * 1、全员推送
	 * API：https://cloud.tencent.com/document/product/269/45934
	 * @param userId 业务用户ID
	 * @param msgRandom 消息随机数，由随机函数产生。用于推送任务去重。对于不同的推送请求，MsgRandom7 天之内不能重复，否则视为相同的推送任务（调用推送 API 返回失败的时候可以用相同的 MsgRandom 进行重试）
	 * @param msgLifeTime 消息离线存储时间，单位秒，最多保存7天（604800秒）。默认为0，表示不离线存储
	 * @param msgBody  消息内容，具体格式请参考 MsgBody 消息内容说明（一条消息可包括多种消息元素，所以 MsgBody 为 Array 类型）
	 * @param consumer 响应处理回调函数
	 */
	public void asyncPush(String userId, String msgRandom, Integer msgLifeTime, MsgBody[] msgBody, Consumer<AllMemberPushResponse> consumer) {
		this.asyncPush(userId, msgRandom, msgLifeTime, null, null, msgBody, consumer);
	}
	
	/**
	 * 1、全员推送
	 * API：https://cloud.tencent.com/document/product/269/45934
	 * @param userId 业务用户ID
	 * @param msgRandom 消息随机数，由随机函数产生。用于推送任务去重。对于不同的推送请求，MsgRandom7 天之内不能重复，否则视为相同的推送任务（调用推送 API 返回失败的时候可以用相同的 MsgRandom 进行重试）
	 * @param msgLifeTime 消息离线存储时间，单位秒，最多保存7天（604800秒）。默认为0，表示不离线存储
	 * @param offlinePushInfo 离线推送信息配置，具体可参考 消息格式描述
	 * @param msgBody  消息内容，具体格式请参考 MsgBody 消息内容说明（一条消息可包括多种消息元素，所以 MsgBody 为 Array 类型）
	 * @param consumer 响应处理回调函数
	 */
	public void asyncPush(String userId, String msgRandom, Integer msgLifeTime, OfflinePushInfo offlinePushInfo, 
			MsgBody[] msgBody, Consumer<AllMemberPushResponse> consumer) {
		this.asyncPush(userId, msgRandom, msgLifeTime, null, offlinePushInfo, msgBody, consumer);
	}
	
	/**
	 * 1、全员推送
	 * API：https://cloud.tencent.com/document/product/269/45934
	 * @param userId 业务用户ID
	 * @param msgRandom 消息随机数，由随机函数产生。用于推送任务去重。对于不同的推送请求，MsgRandom7 天之内不能重复，否则视为相同的推送任务（调用推送 API 返回失败的时候可以用相同的 MsgRandom 进行重试）
	 * @param msgLifeTime 消息离线存储时间，单位秒，最多保存7天（604800秒）。默认为0，表示不离线存储
	 * @param condition AttrsOr 和 AttrsAnd 可以并存，TagsOr 和 TagsAnd 也可以并存。但是标签和属性条件不能并存。如果没有 Condition，则推送给全部用户
	 * @param offlinePushInfo 离线推送信息配置，具体可参考 消息格式描述
	 * @param msgBody  消息内容，具体格式请参考 MsgBody 消息内容说明（一条消息可包括多种消息元素，所以 MsgBody 为 Array 类型）
	 * @param consumer 响应处理回调函数
	 */
	public void asyncPush(String userId, String msgRandom, Integer msgLifeTime, Condition condition,
			OfflinePushInfo offlinePushInfo, MsgBody[] msgBody, Consumer<AllMemberPushResponse> consumer) {
		Map<String, Object> requestBody = new ImmutableMap.Builder<String, Object>()
				.put("From_Account", this.getImUserByUserId(userId))
				.put("MsgRandom", msgRandom)
				.put("MsgLifeTime", Objects.isNull(msgLifeTime) ? 0 : msgLifeTime)
				.put("MsgBody", msgBody)
				.put("Condition", condition)
				.put("OfflinePushInfo", offlinePushInfo)
				.build();
		this.asyncRequest(TimApiAddress.IM_PUSH, requestBody, AllMemberPushResponse.class, consumer);
	}
	
	/**
	 * 2、设置应用属性名称
	 * API：https://cloud.tencent.com/document/product/269/45935
	 * @param attrNames 属性名数组，单个属性最长不超过50字节。应用最多可以有10个推送属性（编号从0到9），用户自定义每个属性的含义
	 * @param consumer 响应处理回调函数
	 */
	public void asyncSetAppAttrNames(String[] attrNames, Consumer<ApiResponse> consumer) {
		Map<String, String> attrNameMap = Maps.newHashMap();
		for (int i = 0; i < attrNames.length; i++) {
			attrNameMap.put(String.valueOf(i), attrNames[i]);
		}
		Map<String, Object> requestBody = new ImmutableMap.Builder<String, Object>()
				.put("AttrNames", attrNameMap)
				.build();
		this.asyncRequest(TimApiAddress.IM_SET_ATTR_NAME, requestBody, ApiResponse.class, consumer);
	}
	
	/**
	 * 3、获取应用属性名称
	 * API：https://cloud.tencent.com/document/product/269/45936
	 * @param consumer 响应处理回调函数
	 */
	public void asyncGetAppAttrNames(Consumer<AppAttrNameResponse> consumer) {
		this.asyncRequest(TimApiAddress.IM_GET_ATTR_NAME, Maps.newHashMap(), AppAttrNameResponse.class, consumer);
	}
	
	/**
	 * 4、设置用户属性
	 * API：https://cloud.tencent.com/document/product/269/45938
	 * @param userAttrs 属性名数组，单个属性最长不超过50字节。应用最多可以有10个推送属性（编号从0到9），用户自定义每个属性的含义
	 * @param consumer 响应处理回调函数
	 */
	public void asyncSetUserAttrs(UserAttrs[] userAttrs, Consumer<ApiResponse> consumer) {
		Map<String, Object> requestBody = new ImmutableMap.Builder<String, Object>()
				.put("UserAttrs", userAttrs)
				.build();
		this.asyncRequest(TimApiAddress.IM_SET_ATTR, requestBody, ApiResponse.class, consumer);
	}
	
	/**
	 * 5、获取用户属性
	 * API：https://cloud.tencent.com/document/product/269/45937
	 * @param userIds 用户ID数组
	 * @param consumer 响应处理回调函数
	 */
	public void asyncGetUserAttrs(String[] userIds, Consumer<UserAttrsResponse> consumer) {
		Map<String, Object> requestBody = new ImmutableMap.Builder<String, Object>()
				.put("To_Account", Stream.of(userIds).map(uid -> this.getImUserByUserId(uid)).collect(Collectors.toList()))
				.build();
		this.asyncRequest(TimApiAddress.IM_GET_ATTR, requestBody, UserAttrsResponse.class, consumer);
	}
	
	/**
	 * 6、删除用户属性
	 * API：https://cloud.tencent.com/document/product/269/45939
	 * @param userAttrs 属性名数组，单个属性最长不超过50字节。应用最多可以有10个推送属性（编号从0到9），用户自定义每个属性的含义
	 * @param consumer 响应处理回调函数
	 */
	public void asyncRemoveUserAttrs(UserAttrs[] userAttrs, Consumer<ApiResponse> consumer) {
		Map<String, Object> requestBody = new ImmutableMap.Builder<String, Object>()
				.put("UserAttrs", userAttrs)
				.build();
		this.asyncRequest(TimApiAddress.IM_REMOVE_ATTR, requestBody, ApiResponse.class, consumer);
	}
	
	/**
	 * 7、添加用户标签
	 * API：https://cloud.tencent.com/document/product/269/45941
	 * a、每次请求最多只能给100个用户添加标签，请求体中单个用户添加标签数最多为10个。
	 * b、单个用户可设置最大标签数为100个，若用户当前标签超过100，则添加新标签之前请先删除旧标签。
	 * c、单个标签最大长度为50字节。
	 * @param userTags 用户标签数组
	 * @param consumer 响应处理回调函数
	 */
	public void asyncAddUserTags(UserTags[] userTags, Consumer<ApiResponse> consumer) {
		Map<String, Object> requestBody = new ImmutableMap.Builder<String, Object>()
				.put("UserTags", userTags)
				.build();
		this.asyncRequest(TimApiAddress.IM_ADD_TAG, requestBody, ApiResponse.class, consumer);
	}

	/**
	 * 8、获取用户标签
	 * API：https://cloud.tencent.com/document/product/269/45940
	 * @param userIds 用户ID数组
	 * @param consumer 响应处理回调函数
	 */
	public void asyncGetUserTags(String[] userIds, Consumer<UserTagsResponse> consumer) {
		Map<String, Object> requestBody = new ImmutableMap.Builder<String, Object>()
				.put("To_Account", Stream.of(userIds).map(uid -> this.getImUserByUserId(uid)).collect(Collectors.toList()))
				.build();
		this.asyncRequest(TimApiAddress.IM_GET_TAG, requestBody, UserTagsResponse.class, consumer);
	}
	
	
	/**
	 * 9、删除用户标签
	 * API：https://cloud.tencent.com/document/product/269/45942
	 * @param userTags 用户标签数组
	 * @param consumer 响应处理回调函数
	 */
	public void asyncRemoveUserTags(UserTags[] userTags, Consumer<ApiResponse> consumer) {
		Map<String, Object> requestBody = new ImmutableMap.Builder<String, Object>()
				.put("UserTags", userTags)
				.build();
		this.asyncRequest(TimApiAddress.IM_REMOVE_TAG, requestBody, ApiResponse.class, consumer);
	}

	/**
	 * 10、删除用户所有标签
	 * API：https://cloud.tencent.com/document/product/269/45943
	 * @param userIds 用户ID数组
	 * @param consumer 响应处理回调函数
	 */
	public void asyncRemoveUserTags(String[] userIds, Consumer<ApiResponse> consumer) {
		Map<String, Object> requestBody = new ImmutableMap.Builder<String, Object>()
				.put("To_Account", Stream.of(userIds).map(uid -> this.getImUserByUserId(uid)).collect(Collectors.toList()))
				.build();
		this.asyncRequest(TimApiAddress.IM_REMOVE_ALL_TAGS, requestBody, ApiResponse.class, consumer);
	}

}
