package au.com.optus.ngcemigrations;

import org.kie.api.runtime.process.ProcessContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import au.com.optus.dev1.datamodels.CE_Mobile_Migration_Service_Discovery;

public class UtilHelper {
    public static void entryScripts(Long pid, String currentTaskName, String targetSystem, String targetURL, String taskStatus, String errorMsg, String config_diff, String config, ProcessContext c1) {
        System.out.println("Starting Helper Class - Entry");
        com.fasterxml.jackson.databind.ObjectMapper om = new com.fasterxml.jackson.databind.ObjectMapper();
        
        // Override pid sent to this method so that we don't always send the parent process instance ID as per earlier requirements - this change is made here so as to not have to make ~10 changes to the discoverServices process
       Long subPid = c1.getProcessInstance().getId();

        String processIDStr = c1.getProcessInstance().getProcessId();
        c1.setVariable("currentTask", currentTaskName);
        Integer eo_number = Integer.valueOf(c1.getVariable("correlationID").toString());
        System.out.println("EO Number is: " + eo_number.toString() + " for task: " + currentTaskName);
        c1.setVariable("taskStarted", java.time.LocalDateTime.now());
        c1.setVariable("targetOutboundSystem", targetSystem);
        c1.setVariable("restErrorMsgStr", null);
        System.out.println("Process instance " + pid.toString() + " of " + processIDStr + " is now processing task " + currentTaskName + ", with status: '" + taskStatus + "' to " + targetSystem + " for EO " + eo_number.toString() + ", at " + targetURL);
        //System.out.println("Ending Helper Class");
        
        // Start sub-process code check here
        Long selfPid = c1.getProcessInstance().getId();
/*
if (!pid .equals(selfPid))
        {
            taskStatus = taskStatus + " (" + selfPid.toString() + ")";
            System.out.println("Current Task Name is " + currentTaskName);
        }
        // End sub-process code check here
*/
        PushUpdate pushUpdate = new PushUpdate();
        pushUpdate.setPam_process_id(pid);
        pushUpdate.setTask_name(currentTaskName);
        pushUpdate.setTask_status(taskStatus);
        pushUpdate.setError_msg(errorMsg);
        pushUpdate.setConfig_diff(null);
        //pushUpdate.setConfig(null);
        pushUpdate.setResult(null);
        pushUpdate.setSub_process_instance_id(subPid);
        c1.setVariable("pushMsg", pushUpdate);

        try
        {
            String pushMsgStr = om.writeValueAsString(pushUpdate);
            System.out.println("Latest status Push Update is: " + pushMsgStr);
            c1.setVariable("pushUpdateStr", pushMsgStr);
        }
         catch (JsonProcessingException e) {
			System.out.println(e.toString());
		}
		
        System.out.println("Exiting Helper Class - Entry");
        


    }
    public static void exitScripts(Long pid, String currentTaskName, String targetSystem, String targetURL, String taskStatus, String errorMsg, String config_diff, au.com.optus.dev1.datamodels.CE_Mobile_Migration_Service_Discovery result, ProcessContext c1) {
        System.out.println("Starting Helper Class - Exit");
        com.fasterxml.jackson.databind.ObjectMapper om = new com.fasterxml.jackson.databind.ObjectMapper();
        
        // Override pid sent to this method so that we don't always send the parent process instance ID as per earlier requirements - this change is made here so as to not have to make ~10 changes to the discoverServices process
       Long subPid = c1.getProcessInstance().getId();
        
        String processIDStr = c1.getProcessInstance().getProcessId();
        c1.setVariable("currentTask", currentTaskName);
        Integer eo_number = Integer.valueOf(c1.getVariable("correlationID").toString());
        System.out.println("EO Number is: " + eo_number.toString() + " for task: " + currentTaskName);
        c1.setVariable("taskStarted", java.time.LocalDateTime.now());
        c1.setVariable("targetOutboundSystem", targetSystem);
        c1.setVariable("restErrorMsgStr", null);
        System.out.println("Process instance " + pid.toString() + " of " + processIDStr + " is now processing task " + currentTaskName + ", with status: '" + taskStatus + "' to " + targetSystem + " for EO " + eo_number.toString() + ", at " + targetURL);
        System.out.println("Ending Helper Class");
        
        // Start sub-process code check here
        Long selfPid = c1.getProcessInstance().getId();
/*
if (!pid .equals(selfPid))
        {
            taskStatus = taskStatus + " (" + selfPid.toString() + ")";
            System.out.println("Current Task Name is " + currentTaskName);
        }
        // End sub-process code check here
*/        
        PushUpdate pushUpdate = new PushUpdate();
        pushUpdate.setPam_process_id(pid);
        pushUpdate.setTask_name(currentTaskName);
        pushUpdate.setTask_status(taskStatus);
        pushUpdate.setError_msg(errorMsg);
        pushUpdate.setConfig_diff(config_diff);
        //pushUpdate.setConfig(null);
        pushUpdate.setResult(result);
        pushUpdate.setSub_process_instance_id(subPid);
        c1.setVariable("pushMsg", pushUpdate);

        try
        {
            String pushMsgStr = om.writeValueAsString(pushUpdate);
            System.out.println("Latest status Push Update is: " + pushMsgStr);
            c1.setVariable("pushUpdateStr", pushMsgStr);
        }
         catch (JsonProcessingException e) {
			System.out.println(e.toString());
		}
		
        System.out.println("Exiting Helper Class - Exit");

    }
    	public static String object2Json(Object obj) {
		try {
			ObjectMapper oMapper = new ObjectMapper();
			String json = oMapper.writeValueAsString(obj);
			System.out.println(json);
			return json;
		} catch (JsonProcessingException e) {
			return e.toString();
		}
	}
}
