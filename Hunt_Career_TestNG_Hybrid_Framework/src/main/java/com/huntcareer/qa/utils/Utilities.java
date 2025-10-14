package com.huntcareer.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import com.github.javafaker.Faker;

public class Utilities {
	public static final int IMPLICIT_WAIT_TIME = 10;
	public static final int PAGE_WAIT_TIME = 5;

	private static final Faker faker = new Faker(new Locale("en-IND"));
	private static final Random random = new Random();

	public static String getCompanyName() {
		return faker.company().name();
	}

	public static String getFirstName() {
		return faker.name().firstName();
	}

	public static String getLastName() {
		return faker.name().lastName();
	}

	public static String getEmail(String type) {
	    String timestamp = genarateEmailTimeStamp();
	    switch (type) {
	        case "plainEmail":
	            return timestamp;
	        case "noLocalPart":
	            return "@mailinator.com";
	        case "localPartOnly":
	            return timestamp + "@";
	        case "doubleAt":
	            return "u@" + timestamp + "@mailinator.com";
	        case "withoutTopLevelDomain":
	            return timestamp + "@mailinator";
	        case "tailingWithDot":
	            return timestamp + "@mailinator.";
	        case "topLevelDomainTooShort":
	            return timestamp + "@mailinator.c";
	        case "domainStartsWithHyphen":
	            return timestamp + "@-mailinator.com";
	        case "doubleDotInDomain":
	            return timestamp + "@mailinator..com";
	        case "noAtTheRate":
	            return timestamp + "#mailinator.com";
	        case "illegalChars":
	            return timestamp + "<>@mailinator.com";
	        case "twoConsicutiveDots":
	            return "u.." + timestamp + "@mailinator.com";
	        case "dotAtBeginning":
	            return "." + timestamp + "@mailinator.com";
	        case "withQuotation":
	            return "\"" + timestamp + "@mailinator.com" + "\"";
	        case "unclosedQuotation":
	            return "\"" + timestamp + "@mailinator.com";
	        case "veryLengthyLocal":
	            return "verylonglocalpartthatexceedstheusuallimitstoverifythelimitdoesitexceedornotbyaddingverylongcharacters@example.com";
	        case "veryLengthyDomain":
	            return "example@verylonglocalpartthatexceedstheusuallimitstoverifythelimitdoesitexceedornotbyaddingverylongcharacters.com";
	        case "veryLenghtyTLD":
	            return timestamp + "@example.verylonglocalpartthatexceedstheusuallimitstoverifythelimitdoesitexceedornotbyaddingverylongcharacters";
	        case "missingSecondLevelDomain":
	            return timestamp + "com";
	        case "withComma":
	            return timestamp + "@mailinator,com";
	        case "doubleConsicutiveAt":
	            return timestamp + "@@mailinator.com";
	        case "underscoreInTLD":
	            return timestamp + "@mailinator.c_m";
	        case "dotBetweenName":
	            return "u." + timestamp + ".s@mailinator.com";
	        case "spaceBeforeName":
	            return " " + timestamp + "@mailinator.com";
	        case "spaceAfterName":
	            return timestamp + "@mailinator.com" + " ";
	        case "dotBeforeAt":
	            return timestamp + ".@mailinator.com";
	        case "withoutDomain":
	            return timestamp + "@.com";
	        default:
	            return timestamp + "@mailinator.com";
	    }
	}

	public static String generateRandomPassword() {
		return "Test@" + random.nextInt(1000);
	}

	public static String generateRandomPhoneNumber() {
		return faker.phoneNumber().cellPhone().replaceAll("[^0-9]", "").substring(0, 10);
	}

	public static String generateRandomJobNames() {
		return faker.job().title();
	}

	public static String generateRandomJobDescription() {
		return faker.job().keySkills();
	}

	public static String generateRandomLocation() {
		return faker.country().name();
	}

	public static String getRandomJobType() {
		String[] jobs = { "Full-Time", "Part-Time", "Contract", "Internship", "Freelance" };
		return jobs[ThreadLocalRandom.current().nextInt(jobs.length)];
	}

	public static String generateTimeStamp() {
		Date date = new Date();
		return date.toString().replace(" ", "_").replace(":", "_");
	}

	public static String genarateEmailTimeStamp() {
		Date date = new Date();
		String timeStamp = date.toString().replace(" ", "_").replace(":", "_");
		return "testUser" + timeStamp;
	}

	public static Object[][] getTestDataFromExcel(String sheetName, String excelFilePath) {
		File file = new File(System.getProperty("user.dir") + excelFilePath);
		XSSFWorkbook workbook = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			workbook = new XSSFWorkbook(fis);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		XSSFSheet sheet = workbook.getSheet(sheetName);

		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[rows][cols];
		for (int i = 0; i < rows; i++) {
			XSSFRow row = sheet.getRow(i + 1);
			for (int j = 0; j < cols; j++) {
				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();

				switch (cellType) {
				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j] = Integer.toString((int) cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;
				default:
					break;
				}
			}
		}
		return data;
	}

	public static String captureScreenshot(WebDriver driver, String testName, String screenshotsPath) {
		File srcSreenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshotPath = System.getProperty("user.dir") + screenshotsPath + testName + ".png";
		try {
			FileHandler.copy(srcSreenShot, new File(destinationScreenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destinationScreenshotPath;
	}

}
