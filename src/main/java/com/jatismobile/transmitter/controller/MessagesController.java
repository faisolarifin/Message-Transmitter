package com.jatismobile.transmitter.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.jatismobile.transmitter.model.MessageOut;
import com.jatismobile.transmitter.repository.MessageOutRepository;
import com.jatismobile.transmitter.utils.AppLog;

@RestController
public class MessagesController {
		
	@Value("${facebook.token}")
    String TOKEN;
	
	@Value("${facebook.phoneNumberId}")
    String phoneNumberId;
	
	@Value("${facebook.senderId}")
    String senderId;
	
	@Autowired
	MessageOutRepository msgOutRepository;
		
	public Map<String, Object> response;
	
	public Map hitToFacebook(HashMap payloads) {
		
		String url = "https://graph.facebook.com/v14.0/"+ phoneNumberId +"/messages";
		Map errors = null;
		
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setBearerAuth(TOKEN);
			headers.setContentType(MediaType.APPLICATION_JSON);
	
	        HttpEntity<?> request = new HttpEntity<>(payloads, headers);
	        
	        AppLog.logInfo("[facebook][hit][request] sending request body " + request.toString());
	        
        	ResponseEntity<Map> responses = new RestTemplate().postForEntity(url, request, Map.class);
        	
        	AppLog.logInfo("[facebook][hit][response] response body from facebook " + responses.toString());
            return responses.getBody();
            
		} catch (Exception e) {
			AppLog.logInfo("Error : " + e.toString());
			return errors;
		}    
	}
	
	
	public void saveToMessageOut(String messageId, HashMap messagePayload, Map hitMessage) {
		
		try {
			MessageOut msgOut = new MessageOut();
			
			ArrayList<Map> messages = (ArrayList<Map>) hitMessage.get("messages");
			Map text = (Map) messagePayload.get("text");
			
			msgOut.setWa_message_id(messages.get(0).get("id").toString());
			msgOut.setMessage_id(messageId);
			msgOut.setRecipient_type(messagePayload.get("recipient_type").toString());
			msgOut.setMsisdn(messagePayload.get("to").toString());
			msgOut.setSender(senderId);
			msgOut.setMessage(text.get("body").toString());
			msgOut.setCreated_at(LocalDateTime.now());
			
			AppLog.logInfo("[messageout][save] data save to messageout table " + msgOut.toString());
			msgOutRepository.save(msgOut);		
			
		} catch (Exception e) {
			
			AppLog.logInfo("Error : " + e.toString());
		}
		
		
	}

}
