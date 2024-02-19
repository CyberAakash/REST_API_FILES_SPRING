package net.cyberaakash.files;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

@SpringBootApplication
public class FilesApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilesApplication.class, args);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of files to create: ");
        int numFiles = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (int i = 1; i <= numFiles; i++) {
            System.out.println("File " + i + ":");
            createFile(scanner);
        }

        scanner.close();
    }

    private static void createFile(Scanner scanner) {
        String fileName;
        String fileFormat;

        do {
            System.out.print("Enter the file name: ");
            fileName = scanner.nextLine().trim();

            if (fileName.isEmpty()) {
                System.out.println("File name cannot be empty. Please try again.");
            }
        } while (fileName.isEmpty());

        do {
            System.out.print("Enter the file format (excel, pdf, word, text): ");
            fileFormat = scanner.nextLine().trim().toLowerCase();

            if (!isValidFormat(fileFormat)) {
                System.out.println("Unsupported file format. Please try again.");
            }
        } while (!isValidFormat(fileFormat));

        try {
            switch (fileFormat) {
                case "excel":
                    File excelFile = new File(fileName + ".xlsx");
                    excelFile.createNewFile();
                    System.out.println("Excel file created: " + excelFile.getAbsolutePath());
                    break;
                case "pdf":
                    File pdfFile = new File(fileName + ".pdf");
                    pdfFile.createNewFile();
                    System.out.println("PDF file created: " + pdfFile.getAbsolutePath());
                    break;
                case "word":
                    File wordFile = new File(fileName + ".docx");
                    wordFile.createNewFile();
                    System.out.println("Word file created: " + wordFile.getAbsolutePath());
                    break;
                case "text":
                    File textFile = new File(fileName + ".txt");
                    textFile.createNewFile();
                    System.out.println("Text file created: " + textFile.getAbsolutePath());
                    break;
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    private static boolean isValidFormat(String format) {
        return format.equals("excel") || format.equals("pdf") || format.equals("word") || format.equals("text");
    }
}
