package com.jatismobile.transmitter.service;

import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.jatismobile.transmitter.controller.MessagesController;
import com.jatismobile.transmitter.utils.AppLog;

@Component
public class Consumer {
	
	@Autowired
	MessagesController msgController;
	
	@JmsListener(destination = "${active-mq.topic}")
	public void receiveMessage(final Message jsonMessage) throws JMSException {
		
		try {
			if(jsonMessage instanceof MapMessage) {
				AppLog.logInfo("[queue][cunsumer][body] receive object message body " + jsonMessage.toString());
				
				MapMessage objMessage = (MapMessage) jsonMessage;
				String messageId = (String) objMessage.getObject("message_id");
				HashMap messagePayload = (HashMap) objMessage.getObject("payload");
				
				Map hitMessage = msgController.hitToFacebook(messagePayload);

				if (hitMessage != null) {
					msgController.saveToMessageOut(messageId, messagePayload, hitMessage);				
				}	

			}
		} catch (Exception e) {
			
			AppLog.logInfo(e.toString());
		}

		
	
	}

}