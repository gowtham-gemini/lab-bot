package net.apporbit.lab.bot.web.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.Tag;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.DescribeRegionsResult;
import net.apporbit.lab.bot.service.AWSAuthService;

@Controller
@RequestMapping("/aws/usage")
public class AWSUsageReportController {

	@RequestMapping("/region/flavorReport")
    public @ResponseBody ArrayList<HashMap<Object, Object>> getFlavorReport(@RequestParam(value = "accessKey", required = true ) String accessKey,
    		@RequestParam(value = "secretKey", required = true ) String secreetKey) throws Exception {


		AWSAuthService auth = new AWSAuthService(accessKey, secreetKey, null);

		DescribeRegionsResult describeResult = auth.getAmazonEC2Client().describeRegions();

        System.out.println("Region Based Report");

        ArrayList<HashMap<Object, Object>> regionsList = new ArrayList<>();

        for (com.amazonaws.services.ec2.model.Region awsRegion : describeResult.getRegions()) {

        	Integer totalVMS = 0;
        	HashMap<Object, Object> regionReport = new HashMap<>();
        	System.out.println("Populating Region Based Report for region : " + awsRegion.getRegionName());
        	regionReport.put("regionName", awsRegion.getRegionName());
        	HashMap<String, Integer> flavorCount = new HashMap<String, Integer>();
        	auth.setRegion(awsRegion.getRegionName());
            DescribeInstancesResult describeInstancesResult = auth.getAmazonEC2Client().describeInstances();
            List<Reservation> reservations = describeInstancesResult.getReservations();
            if (reservations != null) {
                for (Reservation reservation :  reservations) {
                    for (Instance  instance :  reservation.getInstances()) {
                    	instance.getInstanceType();
                    	if (flavorCount.containsKey(instance.getInstanceType())) {
                    		Integer count = flavorCount.get(instance.getInstanceType());
                    		System.out.println("instance.getInstanceType() : " + instance.getInstanceType());
                    		System.out.println("Count : " + count);
                    		flavorCount.replace(instance.getInstanceType(), count, count+1);
                    	} else {
                    		flavorCount.put(instance.getInstanceType(), 1);
                    	}
                    	totalVMS++;
                    }
                }
            }

            System.out.println(flavorCount.values());
            regionReport.put("totalVMS", totalVMS);
            regionReport.put("flavorUsageInfo", flavorCount);
            regionsList.add(regionReport);
        }
		return regionsList;
    }

	@RequestMapping("/accountReport")
    public @ResponseBody ArrayList<HashMap<Object, Object>> getAccountReports(@RequestParam(value = "account", required = false ) String accountNames,
    		@RequestParam(value = "accessKey", required = true ) String accessKey,
    		@RequestParam(value = "secretKey", required = true ) String secreetKey) throws Exception {

		String[] accounts = {"demo", "sujai", "gow", "gopi", "prakash", "manish", "rhel7", "ashish", "rakesh", "ramendra", "saas", "rohit",
				"tapan", "pradeep", "bharathi", "mradul", "sajith", "sap", "texuna"};

		if (accountNames != null && accountNames.length() > 0) {
			accounts = accountNames.split(",");
		}
		ArrayList<HashMap<Object, Object>> accounReport = new ArrayList<>();

		ArrayList<Instance> aviliableInstanceList = new ArrayList<>();
		AWSAuthService auth = new AWSAuthService(accessKey, secreetKey, null);
		DescribeRegionsResult describeResult = auth.getAmazonEC2Client().describeRegions();
		Integer totalVMAWS = 0;
		for (com.amazonaws.services.ec2.model.Region awsRegion : describeResult.getRegions()) {
			System.out.println("Populating ALL Instance List for  Region : " + awsRegion.getRegionName());
        	auth.setRegion(awsRegion.getRegionName());
			DescribeInstancesResult describeInstancesResult = auth.getAmazonEC2Client().describeInstances();
			List<Reservation> reservations = describeInstancesResult.getReservations();
			if (reservations != null) {
			    for (Reservation reservation :  reservations) {
			        for (Instance  instance :  reservation.getInstances()) {
			        	if (!instance.getState().getName().equalsIgnoreCase("terminated")) {
			        		aviliableInstanceList.add(instance);
				        	totalVMAWS++;
			        	}
			        }

			    }

			}
        }
		System.err.println("Total Instance in AWS :" + totalVMAWS);
        for (String account : accounts) {
        	Integer totalVMS = 0;
        	HashMap<Object, Object> accountUsageInfo = new HashMap<>();
        	accountUsageInfo.put("accountName", account);
        	System.out.println("==================== Populating Account Based Report for account : " + account + "========================");
        	Integer index = 0;
        	for (Iterator iterator = aviliableInstanceList.iterator(); iterator.hasNext();) {
				Instance instance = (Instance) iterator.next();
				Iterator rmIterator = null;
        		Integer rmIndex = null;
        		if (instance.getTags() != null) {
	        		for (Tag tag : instance.getTags()) {
		        		if (tag.getKey().equalsIgnoreCase("name") && tag.getValue().toLowerCase().contains(account)) {
		        			totalVMS++;
		        			rmIterator = iterator;
		        			rmIndex = index;
		        			if (accountUsageInfo.containsKey(instance.getInstanceType())) {
	        					Integer count = (Integer) accountUsageInfo.get(instance.getInstanceType());
	        					accountUsageInfo.replace(instance.getInstanceType(), count, count+1);
	        				} else {
	        					accountUsageInfo.put(instance.getInstanceType(), 1);

	        				}
		        			if (accountUsageInfo.containsKey(instance.getVpcId())) {
	        					Integer count = (Integer) accountUsageInfo.get(instance.getVpcId());
	        					accountUsageInfo.replace(instance.getVpcId(), count, count+1);
	        				} else {
	        					accountUsageInfo.put(instance.getVpcId(), 1);
	        				}
		        			if (accountUsageInfo.containsKey(instance.getSubnetId())) {
	        					Integer count = (Integer) accountUsageInfo.get(instance.getSubnetId());
	        					accountUsageInfo.replace(instance.getSubnetId(), count, count+1);
	        				} else {
	        					accountUsageInfo.put(instance.getSubnetId(), 1);
	        				}
		        			if (accountUsageInfo.containsKey(instance.getState().getName())) {
	        					Integer count = (Integer) accountUsageInfo.get(instance.getState().getName());
	        					accountUsageInfo.replace(instance.getState().getName(), count, count+1);
	        				} else {
	        					accountUsageInfo.put(instance.getState().getName(), 1);
	        				}
		        		}
	        		}
	        	}
        		if (rmIndex != null && rmIndex.equals(index)) {
        			rmIterator.remove();
        		}
        		index++;
			}
    		accountUsageInfo.put("Total VMS", totalVMS);
    		accounReport.add(accountUsageInfo);
    		System.out.println("==================Done Populating Account Based Report for account : " + account + "============================");
		}
        System.out.println("==================== Populating Account Based Report for account : " + "other" + "========================");
        Integer totalVMS = 0;
    	HashMap<Object, Object> accountUsageInfo = new HashMap<>();
    	accountUsageInfo.put("accountName", "other");
    	for (Instance instance : aviliableInstanceList) {
    		if (accountUsageInfo.containsKey(instance.getInstanceType())) {
				Integer count = (Integer) accountUsageInfo.get(instance.getInstanceType());
				accountUsageInfo.replace(instance.getInstanceType(), count, count+1);
			} else {
				accountUsageInfo.put(instance.getInstanceType(), 1);
			}
    		if (accountUsageInfo.containsKey(instance.getVpcId())) {
				Integer count = (Integer) accountUsageInfo.get(instance.getVpcId());
				accountUsageInfo.replace(instance.getVpcId(), count, count+1);
			} else {
				accountUsageInfo.put(instance.getVpcId(), 1);
			}
			if (accountUsageInfo.containsKey(instance.getSubnetId())) {
				Integer count = (Integer) accountUsageInfo.get(instance.getSubnetId());
				accountUsageInfo.replace(instance.getSubnetId(), count, count+1);
			} else {
				accountUsageInfo.put(instance.getSubnetId(), 1);
			}
			if (accountUsageInfo.containsKey(instance.getState().getName())) {
				Integer count = (Integer) accountUsageInfo.get(instance.getState().getName());
				accountUsageInfo.replace(instance.getState().getName(), count, count+1);
			} else {
				accountUsageInfo.put(instance.getState().getName(), 1);
			}
    		totalVMS++;
		}
		accountUsageInfo.put("Total VMS", totalVMS);
		accounReport.add(accountUsageInfo);
		System.out.println("==================Done Populating Account Based Report for account : " + "other" + "============================");
		return accounReport;
	}
}
