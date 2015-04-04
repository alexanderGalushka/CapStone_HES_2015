//package edu.harvard.cscie99.adam.controller;
//
//import java.util.Date;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import edu.harvard.cscie99.adam.error.LogoutFailedException;
//import edu.harvard.cscie99.adam.error.SessionTimeouException;
//import edu.harvard.cscie99.adam.error.UnauthorizedOperationException;
//import edu.harvard.cscie99.adam.model.Plate;
//import edu.harvard.cscie99.adam.model.PlateResult;
//import edu.harvard.cscie99.adam.model.Project;
//import edu.harvard.cscie99.adam.model.WellResult;
//import edu.harvard.cscie99.adam.profile.User;
//import edu.harvard.cscie99.adam.service.AuthenticationService;
//import edu.harvard.cscie99.adam.service.ProfileService;
//import edu.harvard.cscie99.adam.service.ResultService;
//
///**
// * 
// * @author Gerson
// *
// */
//@RestController
//@RequestMapping(value = "/")
//public class ResultController {
//	
//	@Autowired
//	private ResultService resultService;
//	
//	@Autowired
//	private AuthenticationService authService;
//	
//	@Autowired
//	private ProfileService profileService;
//
//	@RequestMapping(value = "/result/list", method = RequestMethod.GET)
//	@ResponseBody
//	public List<PlateResult> searchResult(
//			@RequestParam(value="search", required=false) String search)
////			@RequestParam(value="id", required=false) int id,
////			@RequestParam(value="plate_id", required=false) int plateId,
////			@RequestParam(value="creationDate", required=false) Date creationDate,
////			@RequestParam(value="comment", required=false) String comment,
////			@RequestParam(value="user", required=true) String userName) 
//			throws UnauthorizedOperationException{
//		
////		User user = profileService.getUserDetails(userName);
////		
////		List<PlateResult> results = resultService.search(projectId, id, plateId, creationDate, comment, user);
////		return results;
//		return null;
//	}
//	
//	@RequestMapping(value = "/project/{projectId}/result/{result_id}/detail", method = RequestMethod.POST)
//	@ResponseBody
//	public PlateResult getResult(
//			@PathVariable("project_id") int projectId,
//			@PathVariable("result_id") int resultId,
//			@RequestParam(value="user", required=true) String user) throws UnauthorizedOperationException{
//		
//		boolean hasAccess = false;
//		try {
//			hasAccess = authService.checkUserAccess(user, projectId, "getResult");
//		} catch (SessionTimeouException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		if (hasAccess){
//			PlateResult plateResult = resultService.retrieveResult(projectId, resultId);
//			return plateResult;
//		}
//		else{
//			throw new UnauthorizedOperationException ("User don't have permission", user, "getResult");
//		}
//	}
//	
//	//VALIDATE 
////	@RequestMapping(value = "/project/{projectId}/result/{result_id}/normalize/{function}", method = RequestMethod.POST)
////	@ResponseBody
////	public PlateResult normalizeResult(
////			@PathVariable("project_id") int projectId,
////			@PathVariable("result_id") int resultId,
////			@PathVariable("function") String function,
////			@RequestParam(value="user", required=true) String user) throws UnauthorizedOperationException{
////		
////		boolean hasAccess = authService.checkUserAccess(user, projectId, "getResult");
////		
////		if (hasAccess){
////			PlateResult plateResult = resultService.retrieveResult(projectId, resultId);
////			return plateResult;
////		}
////		else{
////			throw new UnauthorizedOperationException ("User don't have permission", user, "getResult");
////		}
////	}
////	
////	@RequestMapping(value = "/project/{projectId}/result/{result_id}/removenan", method = RequestMethod.POST)
////	@ResponseBody
////	public PlateResult removeNan(
////			@PathVariable("project_id") int projectId,
////			@PathVariable("result_id") int resultId,
////			@RequestParam(value="user", required=true) String user) throws UnauthorizedOperationException{
////		
////		//TODO
////		return null;
////	}
//
//}
