//package com.beanpodtech.pkms.pkmsuser;
//
//import com.beanpodtech.pkms.common.constant.ActionType;
//import com.beanpodtech.pkms.common.constant.ExchangeConstant;
//import com.beanpodtech.pkms.common.constant.RoutingKeyConstant;
//import com.beanpodtech.pkms.common.message.rabbitmq.RabbitMqCommonMessage;
//import com.beanpodtech.pkms.common.message.rabbitmq.RabbitMqResponse;
//import com.beanpodtech.pkms.common.model.UserSession;
//import com.beanpodtech.pkms.common.param.*;
//import com.beanpodtech.pkms.pkmsuser.utils.DongleUtils;
//import com.beanpodtech.pkms.pkmsuser.utils.ParamUtils;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectWriter;
//import org.apache.http.util.Asserts;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class PkmsUserApplicationTests {
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//    @Autowired
//    private DongleUtils dongleUtils;
//    @Autowired
//    private UserDBExecutor dbExecutor;
//
//	@Test
//	public void rebindDongleRollbackTest()
//	{
//		System.out.println("rebind dongle rollback test start ...");
//        UserActiveParam param = new UserActiveParam();
//        param.setUserName("lhs123");
//        param.setOptDongleId("0011165E1A42230031F5");
//        param.setDongleId("1111165E1A42230031F5");
//        String ip = "192.168.0.1";
//
//        dongleUtils.deleteNewDongle(param, ip);
//        dongleUtils.inactiveDongle(param, true, ip);
//	}
//
//    @Test
//    public void createUserRollbackTest()
//    {
//        System.out.println("create user rollback test start ...");
//        UserActiveParam param = new UserActiveParam();
//        param.setUserName("lhs123");
//        param.setOptName("testssfff");
//        param.setOptDongleId("2211165E1A42230031F5");
//        String ip = "192.168.0.1";
//
//        dbExecutor.deleteUser(param.getUserName(), param.getOptName(), ip);
//        dbExecutor.removeUserRoles(78, param.getOptName(), ip);
//        dongleUtils.deleteNewDongle(param, ip);
//
//    }
//
//	private RabbitMqResponse sendMessage(RabbitMqCommonMessage message)
//	{
//		RabbitMqResponse result = null;
//
//		if (rabbitTemplate == null)
//		{
//			System.out.println("PkmsUserApplicationTests rabbitTemplate is null");
//		}
//		else
//		{
//			System.out.println("PkmsUserApplicationTests rabbitTemplate is not null");
//		}
//
//		result = (RabbitMqResponse) rabbitTemplate.convertSendAndReceive(ExchangeConstant.PKMS_EXCHANGE_DIRECT_RPC,
//				RoutingKeyConstant.PKMS_ROUTINGKEY_USER_RPC, message);
//
//		System.out.println(result.getErrorCode() + " " + result.getErrorMessage());
//		System.out.println("rtn = [" + result.getRtnValue() + "]");
//		return result;
//	}
//
//	private RabbitMqResponse setJsonParams(RabbitMqCommonMessage message, Object param)
//	{
//		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//		String json;
//		try {
//			json = ow.writeValueAsString(param);
//			message.setParam(json);
//
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
//		return sendMessage(message);
//	}
////
////	@Test
////	public void createUser() {
////
////		System.out.println("create user test start ...");
////
////		RabbitMqCommonMessage message = new RabbitMqCommonMessage();
////		message.setAction(ActionType.ACTION_USER_CREATE);
////
////		UserInfoParam param = new UserInfoParam();
////		param.setOptName("Xiao Ming");
////		param.setOptPassword("password");
////		param.setOptDongleID("123456789");
////		List<Integer> userTypeRoleIds = new ArrayList<>();
////		userTypeRoleIds.add(1);
////		userTypeRoleIds.add(2);
////		userTypeRoleIds.add(3);
////		param.setUserTypeRoleIds(userTypeRoleIds);
////		param.setCitizenType(1);
////		param.setCitizenId("210365154565458451242");
////		param.setAddress("Shenyang, Liaoning, china");
////		param.setEmail("456789@465.com");
////		param.setTelephone("12314123123123");
////		param.setOrganizationName("hehe");
////
////		setJsonParams(message, param);
////	}
////
////	@Test
////	public void bindUser() {
////
////		System.out.println("active user test start ...");
////
////		RabbitMqCommonMessage message = new RabbitMqCommonMessage();
////		message.setAction(ActionType.ACTION_USER_ACTIVE);
////		UserActiveParam param = new UserActiveParam();
////		param.setOptName("Xiao Ming");
////		param.setOptDongleId("987654321");
////		param.setOptDonglePublicKey("pubkey");
////		param.setOptDongleVendor("vendor");
////		param.setOptDongleSDKVersion("1.0.0");
////		param.setOptDongleType(1);
////		setJsonParams(message, param);
////	}
////
//	@Test
//	public void getUsers()
//	{
//		System.out.println("query user test start ...");
//
//		RabbitMqCommonMessage message = new RabbitMqCommonMessage();
//		message.setAction(ActionType.ACTION_USER_GET_USERS);
//		UserRoleParam param = new UserRoleParam();
//		List<Integer> userTypeRoleIds = new ArrayList<>();
//		userTypeRoleIds.add(1);
//		userTypeRoleIds.add(2);
//		userTypeRoleIds.add(3);
//		param.setRoleIdList(userTypeRoleIds);
//		setJsonParams(message, param);
//	}
//
//	@Test
//	public void login()
//	{
//		System.out.println("login test start ...");
//
//		RabbitMqCommonMessage message = new RabbitMqCommonMessage();
//		message.setAction(ActionType.ACTION_USER_LOGIN);
//		UserLoginParam param = new UserLoginParam();
//		param.setUserName("Xiao Ming");
//		param.setPassword("password");
//		param.setDongleId("00007A502E3A25001403");
//		RabbitMqResponse response = setJsonParams(message, param);
//		System.out.println(response.getErrorCode());
//		System.out.println(response.getErrorMessage());
//		System.out.println(response.getRtnValue());
//	}
////
////	@Test
////	public void checkSession()
////	{
////		System.out.println("check session test start ...");
////
////		RabbitMqCommonMessage message = new RabbitMqCommonMessage();
////		message.setAction(ActionType.ACTION_USER_GET_INFO);
////		UserInfoParam param = new UserInfoParam();
////		param.setOptName("Xiao Ming");
////		RabbitMqResponse response = setJsonParams(message, param);
////		UserInfoParam info = ParamUtils.jsonToUserParam(response.getRtnValue(), UserInfoParam.class);
////		System.out.println();
////
////		message = new RabbitMqCommonMessage();
////		message.setAction(ActionType.ACTION_USER_CHECK_SESSION);
////		UserSessionParam sessionParam = new UserSessionParam();
////		sessionParam.setUserId(info.getOptUserId());
////		response = setJsonParams(message, sessionParam);
////		System.out.println(response.getRtnValue());
////	}
//
//
//	/**
//	 * 根据用户名查询所拥有的全部Action_Type
//	 */
//
//	@Test
//	public void findActionTypeListByUsername() {
//
//		System.out.println("find a user test start ...");
//
//		RabbitMqCommonMessage message = new RabbitMqCommonMessage();
//		message.setAction(ActionType.ACTION_DBADAPTER_USER_ACTIONS_QUERY);
//		message.setIpAddress("192.168.0.1");
//
//		UserInfoParam param = new UserInfoParam();
//		param.setOptUserId(61);
//
//		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//		String json;
//		try {
//			json = ow.writeValueAsString(param);
//			message.setParam(json);
//
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
//
//		RabbitMqResponse result = (RabbitMqResponse) rabbitTemplate.convertSendAndReceive(ExchangeConstant.PKMS_EXCHANGE_DIRECT_RPC,
//				RoutingKeyConstant.PKMS_ROUTINGKEY_DBADAPATER_RPC, message);
//
//		System.out.println(result.getErrorCode() + " " + result.getRtnValue());
//	}
//
//
//	/**
//	 * 查询用户登录信息
//	 * @return
//	 */
//	@Test
//	public void querySession2()
//	{
//		UserSessionParam userSessionParam = new UserSessionParam();
//
//		userSessionParam.setHash("dbd26a8495d649dea4aa73eeb4cba59e");
////		userSessionParam.setUserId(61);
//
//		RabbitMqCommonMessage message = new RabbitMqCommonMessage();
//		message.setAction(ActionType.ACTION_DBADAPTER_USER_SESSION_QUERY);
//		message.setIpAddress("192.168.0.1");
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			String p = mapper.writeValueAsString(userSessionParam);
//			message.setParam(p);
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
//		RabbitMqResponse response = (RabbitMqResponse) rabbitTemplate.convertSendAndReceive(ExchangeConstant.PKMS_EXCHANGE_DIRECT_RPC, RoutingKeyConstant.PKMS_ROUTINGKEY_DBADAPATER_RPC,message);
//		if (response == null)
//		{
//			// failed to update user role in db
//			System.out.println("failed to create user in db");
//			return ;
//		}
//		String rtn = response.getRtnValue();
//		if (rtn == null || rtn.isEmpty())
//		{
//			return ;
//		}
//		return ;
//	}
//
//
//	/**
//	 * 根据用户名查询所拥有的全部Action_Type
//	 */
//
//	@Test
//	public void checkUserAction() {
//
//		// 验证当前action是否在用户的可操作Action集合中，通过放行，不通过直接返回错误提示
//
//        RabbitMqCommonMessage m = new RabbitMqCommonMessage();
//        m.setAction(ActionType.ACTION_USER_CHECK_ACTIONS);
//        BaseParam param2 = new BaseParam();
////        param2.setUserName("smallbeng");
//		param2.setSession("dbd26a8495d649dea4aa73eeb4cba59e");
//        param2.setAction("ACTION_USER_LOCK");
//        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//        try {
//			String json = ow.writeValueAsString(param2);
//            m.setParam(json);
//			m.setIpAddress("192.168.0.1");
//            RabbitMqResponse result = (RabbitMqResponse) rabbitTemplate.convertSendAndReceive(ExchangeConstant.PKMS_EXCHANGE_DIRECT_RPC,
//                    RoutingKeyConstant.PKMS_ROUTINGKEY_USER_RPC, m);
//
//            System.out.println("4444       "+result.getErrorCode() + " " + result.getErrorMessage());
//
//			System.out.println("");
//		} catch (Exception e) {
//            e.printStackTrace();
//			System.out.println(" error 44444444444444444444444444444444444444");
//		}
//	}
//
//
//}
