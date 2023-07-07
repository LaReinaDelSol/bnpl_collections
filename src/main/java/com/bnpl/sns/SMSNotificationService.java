package com.bnpl.sns;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

public class SMSNotificationService {

	public static final String AWS_ACCESS_KEY = "";
	public static final String AWS_SECRET_KEY = "";

	static {
		System.setProperty(AWS_ACCESS_KEY, "");
		System.setProperty(AWS_SECRET_KEY, "");
	}

	public String sendSMSNotification(String phoneNumber, String message) {
		String msgId = "";
		AmazonSNS snsClient = AmazonSNSClient.builder().withRegion(Regions.US_EAST_1).build();
		Map<String, MessageAttributeValue> attriMap = getMessageAttributes();
		PublishResult result = snsClient.publish(
				new PublishRequest().withMessage(message).withPhoneNumber(phoneNumber).withMessageAttributes(attriMap));
		msgId = result.getMessageId();
		return msgId;
	}

	private Map<String, MessageAttributeValue> getMessageAttributes() {
		Map<String, MessageAttributeValue> attriMap = new HashMap<String, MessageAttributeValue>();
		attriMap.put("AWS.SNS.SMS.SenderID",
				new MessageAttributeValue().withStringValue("Whatever").withDataType("String"));

		attriMap.put("AWS.SNS.SMS.SMSType",
				new MessageAttributeValue().withStringValue("Transactional").withDataType("String"));
		return attriMap;

	}

	public String sendNotification(String phoneNumber, boolean feeLevied) {
		SMSNotificationService smsService = new SMSNotificationService();
		String msg = feeLevied ? "Penalty applied" : "Penalty not applied";
		String msgId = smsService.sendSMSNotification(phoneNumber, msg);
		return msgId;
	}
}
