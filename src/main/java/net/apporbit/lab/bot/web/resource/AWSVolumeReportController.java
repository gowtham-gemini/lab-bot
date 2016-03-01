package net.apporbit.lab.bot.web.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.amazonaws.services.ec2.model.Filter;
import com.amazonaws.services.ec2.model.DeleteVolumeRequest;
import com.amazonaws.services.ec2.model.DescribeRegionsResult;
import com.amazonaws.services.ec2.model.DescribeVolumesRequest;
import com.amazonaws.services.ec2.model.DescribeVolumesResult;

import net.apporbit.lab.bot.service.AWSAuthService;

@Controller
@RequestMapping("/aws/volumeReport")
public class AWSVolumeReportController {

	@RequestMapping("/unusedVolumes")
    public @ResponseBody ArrayList<HashMap<Object, Object>> getUnusedVolumes(@RequestParam(value = "accessKey", required = true ) String accessKey,
    		@RequestParam(value = "secreetKey", required = true ) String secreetKey) throws Exception {

		AWSAuthService auth = new AWSAuthService(accessKey, secreetKey, null);

		DescribeVolumesRequest describeVolumesRequest = new DescribeVolumesRequest();


		DescribeRegionsResult describeResult = auth.getAmazonEC2Client().describeRegions();

        System.out.println("Region Based Report");

        ArrayList<HashMap<Object, Object>> regionsList = new ArrayList<>();

        for (com.amazonaws.services.ec2.model.Region awsRegion : describeResult.getRegions()) {

        	auth.setRegion(awsRegion.getRegionName());

        	List<Filter> arrayList = new ArrayList<Filter>();
    		arrayList.add(new Filter().withName("status").withValues("available"));
    		describeVolumesRequest.withFilters(arrayList);
    		DescribeVolumesResult volumesResult = auth.getAmazonEC2Client().describeVolumes(describeVolumesRequest);
    		Integer total = 0;
    		Integer totalUnUsedSize = 0;
    		ArrayList<HashMap<String, String>> volumes = new ArrayList<>();
            for (com.amazonaws.services.ec2.model.Volume volume : volumesResult.getVolumes()) {

            	System.err.println("volume :" + volume.getVolumeId());
            	System.err.println("volume :" + volume.getState());

            	HashMap<String, String> volumeInfo = new HashMap<>();
            	volumeInfo.put("id", volume.getVolumeId());
            	volumeInfo.put("size", volume.getSize().toString());

            	volumes.add(volumeInfo);
            	totalUnUsedSize = totalUnUsedSize + volume.getSize();
            	total++;
            }
            HashMap<Object, Object> regionResponse = new HashMap<>();
            regionResponse.put("regionName", awsRegion.getRegionName());
            regionResponse.put("totalUnUsedVolumes", Integer.toString(total));
            regionResponse.put("totalUnUsedSize", totalUnUsedSize);
            regionResponse.put("volumesInfo", volumes);
            regionsList.add(regionResponse);
        }
		return regionsList;
    }


	@RequestMapping("/cleanVolumes")
    public @ResponseBody HashMap<Object, Object> cleanVolumes(@RequestParam(value = "accessKey", required = true ) String accessKey,
    		@RequestParam(value = "secreetKey", required = true ) String secreetKey) throws Exception {


		AWSAuthService auth = new AWSAuthService(accessKey, secreetKey, null);

		DescribeVolumesRequest describeVolumesRequest = new DescribeVolumesRequest();


		DescribeRegionsResult describeResult = auth.getAmazonEC2Client().describeRegions();



        ArrayList<HashMap<Object, Object>> regionsList = new ArrayList<>();

        for (com.amazonaws.services.ec2.model.Region awsRegion : describeResult.getRegions()) {

        	System.out.println("=================== Removing All volumes from region : " + awsRegion.getRegionName() + " =====================");

        	auth.setRegion(awsRegion.getRegionName());

        	List<Filter> arrayList = new ArrayList<Filter>();
    		arrayList.add(new Filter().withName("status").withValues("available"));
    		describeVolumesRequest.withFilters(arrayList);
    		DescribeVolumesResult volumesResult = auth.getAmazonEC2Client().describeVolumes(describeVolumesRequest);
            for (com.amazonaws.services.ec2.model.Volume volume : volumesResult.getVolumes()) {

            	DeleteVolumeRequest deleteVolumeRequest = new DeleteVolumeRequest();
                deleteVolumeRequest.withVolumeId(volume.getVolumeId());
                auth.getAmazonEC2Client().deleteVolume(deleteVolumeRequest);

            }
            System.out.println("================ Removing All volumes from region : " + awsRegion.getRegionName() + "Done ===============");
        }
		HashMap<Object, Object> response = new HashMap<>();
        response.put("message", "volume clean success");
		return response;
	}
}
