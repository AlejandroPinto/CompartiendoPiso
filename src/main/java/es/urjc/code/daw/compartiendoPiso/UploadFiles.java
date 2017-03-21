package es.urjc.code.daw.compartiendoPiso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class UploadFiles {
	private static final String FILES_FOLDER = "img/users/";

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
	
}
