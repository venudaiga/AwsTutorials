package com.aws.tutorial.aws.controller;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.*;
import com.amazonaws.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sns")
public class SNSController {
    @Autowired
    AmazonSNS amazonSNS;

    String topicArn="arn:aws:sns:ap-south-1:604867242215:supriyaSNS";

@PostMapping("/create/{topicName}")
 public String createTopic(@PathVariable String topic){
    CreateTopicResult topicResult=amazonSNS.createTopic(topic);
    System.out.println("Topic ARN:" +topicResult.getTopicArn());
    return topicResult.getTopicArn();
}
@DeleteMapping("delete")
    public void deleteTopic(){
    System.out.println("Inside Delete topic");
    amazonSNS.deleteTopic(topicArn);
}
@GetMapping("topicDetails")
    public GetTopicAttributesResult getTopicDetails(){
    System.out.println("Inside Get Topic Details");
    return amazonSNS.getTopicAttributes(topicArn);
}

   @PostMapping("/publish")
    public PublishResult publishMessage(@RequestParam String message, @RequestParam String body,
                                        @RequestParam (value = "phoneNum",required = false,defaultValue = "") String phoneNum){
    System.out.println("Inside Publish Message");
    PublishRequest request=new PublishRequest();
       request.setMessage(message);
       request.setSubject(body);
       if(!StringUtils.isNullOrEmpty(phoneNum)){
        request.setPhoneNumber(phoneNum);
        return amazonSNS.publish(request);
    }
       request.setTopicArn(topicArn);
    return amazonSNS.publish(request);
}
@PostMapping("/subscribe")
    public SubscribeResult subscribeTopic(@RequestParam String protocol,String endpoint){
    SubscribeRequest subscribeRequest= new SubscribeRequest(topicArn,protocol,endpoint);
        return amazonSNS.subscribe(subscribeRequest);
}
}