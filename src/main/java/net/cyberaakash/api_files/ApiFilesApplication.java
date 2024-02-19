package net.cyberaakash.api_files;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/api/excel")
public class ApiFilesApplication {

	// Directory where Excel files will be stored
	private static final String EXCEL_DIRECTORY = "/Users/aakashvnthgmail.com/Downloads/test";

	public static void main(String[] args) {
        SpringApplication.run(ApiFilesApplication.class, args);
    }

	// Endpoint to store Excel files
	@PostMapping("/upload")
	public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			return new ResponseEntity<>("Please select a file to upload.", HttpStatus.BAD_REQUEST);
		}

		try {
			// Save the file to the specified directory
			if(file.getName().endsWith(".xlsx")) {
				String filePath = EXCEL_DIRECTORY + File.separator + file.getOriginalFilename();
				file.transferTo(new File(filePath));
				return new ResponseEntity<>("File uploaded successfully.", HttpStatus.OK);
			}
			return new ResponseEntity<>("Only Excel file allowed.", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed to upload the file.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Endpoint to list available Excel files
	@GetMapping("/list")
	public ResponseEntity<List<String>> listExcelFiles() {
		File folder = new File(EXCEL_DIRECTORY);
		File[] files = folder.listFiles();
		List<String> filenames = new ArrayList<>();

		if (files != null) {
			for (File file : files) {
				if (file.isFile() && file.getName().endsWith(".xlsx")) {
					filenames.add(file.getName());
				}
			}
		}

		return new ResponseEntity<>(filenames, HttpStatus.OK);
	}

}
