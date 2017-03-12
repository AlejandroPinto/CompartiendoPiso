package es.urjc.code.daw.compartiendoPiso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;


public class UploadFiles {
	private static final String FILES_FOLDER = "imgs/users/";
	public final static String USER_AVATAR = "user-avatar";
	public final static String OFFER_IMGS = "offer-imgs";

	private List<String> imageTitles = new ArrayList<>();
	
	public List<String> getImageTitles() {
		return imageTitles;
	}

	public void setImageTitles(List<String> imageTitles) {
		this.imageTitles = imageTitles;
	}

	public static String getFilesFolder() {
		return FILES_FOLDER;
	}

	public int handleFileUpload(List<MultipartFile> files, String type) {
		
		int fail = -1;
		
		int num =0;
		for(MultipartFile file: files ){
			String fileName = num + ".jpg";
			num +=1;
			if (!file.isEmpty()) {
				try {
		
					File filesFolder = new File(FILES_FOLDER+type);
					if (!filesFolder.exists()) {
						filesFolder.mkdirs();
					}
		
					File uploadedFile = new File(filesFolder.getAbsolutePath(), fileName);
					file.transferTo(uploadedFile);
		
				} catch (Exception e) {
					
					fail = 1;
				}
			} else {
				
				fail = 1;
			}
			
		}
		return num;
	}
	
	@RequestMapping("/image/{user_id}/{filename}")
	public void handleFileDownload(@PathVariable String user_id,@PathVariable String filename, 
			 HttpServletResponse res) throws FileNotFoundException, IOException {

		File file = new File(FILES_FOLDER, "0.jpg");

		if (file.exists()) {
			res.setContentType("image/jpeg");
			res.setContentLength(new Long(file.length()).intValue());
			FileCopyUtils.copy(new FileInputStream(file), res.getOutputStream());
		} else {
			res.sendError(404, "File" + "0" + "(" + file.getAbsolutePath() + ") does not exist");
		}
	}
		
//	@RequestMapping("/image/{generatedId}/{fileName}")
//	public void handleFileDownload(@PathVariable String type, @PathVariable String generatedId,
//			@PathVariable String fileName, HttpServletResponse res) throws FileNotFoundException, IOException {
//
//		String filePath = null;
//		
//		File file = new File(FILES_FOLDER, fileName+".jpg");
//
////		switch (type) {
////		case USER_AVATAR:
////			filePath = FILES_FOLDER + "/" + fileName + ".jpeg";
////			break;
////		case OFFER_IMGS:
////			filePath = FILES_FOLDER+ "/" + generatedId + "/" + fileName + ".jpeg";
////			break;
////		default:
////			break;
////		}
////		File file = new File(filePath);
//
//		if (file.exists()) {
//			res.setContentType("image/jpeg");
//			res.setContentLength(new Long(file.length()).intValue());
//			FileCopyUtils.copy(new FileInputStream(file), res.getOutputStream());
//		} else {
//			res.sendError(404, "File" + fileName + "(" + file.getAbsolutePath() + ") does not exist");
//		}
//	}
	
}
