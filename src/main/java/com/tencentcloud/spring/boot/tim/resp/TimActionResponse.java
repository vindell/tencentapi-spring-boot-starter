package com.tencentcloud.spring.boot.tim.resp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tencentcloud.spring.boot.tim.resp.push.UserAttrs;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * IM响应结果
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class TimActionResponse extends ApiResponse {


	/**
	 * 消息时间戳，unix 时间戳。
	 */
	@JsonProperty("MsgTime")
	private Integer msgTime;

	/**
	 * 群组总数
	 */
	@JsonProperty("TotalCount")
	private Integer totalCount;

	/**
	 * 分页拉取标志
	 */
	@JsonProperty("Next")
	private Integer next;

	/**
	 * 获取到的群组 ID 的集合。
	 */
	@JsonProperty("GroupIdList")
	private List<Map<String, String>> groupIdList;

	/**
	 * 创建成功之后的群 ID，由 IM 云后台分配。
	 */
	@JsonProperty("GroupId")
	private String groupId;

	/**
	 * 推送任务报告列表。
	 */
	@JsonProperty("Reports")
	private TimActionReportsResponse reports;

	/**
	 *
	 * @return
	 */
	@JsonProperty("UserAttrs")
	private List<UserAttrs> userAttrs;

	/**
	 * 应用属性名称
	 * 
	 * @return
	 */
	@JsonProperty("AttrNames")
	private Map<String, String> attrNames;

	/**
	 * 返回结果为群组信息数组
	 * 
	 * @return
	 */
	@JsonProperty("GroupInfo")
	private List<GroupInfoResponse> groupInfo;

	/**
	 * 返回成员信息
	 * 
	 * @return
	 */
	@JsonProperty("MemberList")
	private List<MemberResponse> memberList;

	@JsonProperty("MsgKey")
	private String msgKey;

	@JsonProperty("MemberNum")
	private Integer memberNum;


}
