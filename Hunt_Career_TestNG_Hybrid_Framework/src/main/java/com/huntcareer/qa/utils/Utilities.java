package com.huntcareer.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
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

		switch (type) {

			case "plainEmail":
				return genarateEmailTimeStamp();
			case "noLocalPart":
				return "@mailinator.com";
			case "localPartOnly":
				return genarateEmailTimeStamp() + "@";
			case "doubleAt":
				return "u@" + genarateEmailTimeStamp() + "@mailinator.com";
			case "withoutTopLevelDomain":
				return genarateEmailTimeStamp() + "@mailinator";
			case "tailingWithDot":
				return genarateEmailTimeStamp() + "@mailinator.";
			case "topLevelDomainTooShort":
				return genarateEmailTimeStamp() + "@mailinator.c";
			case "domainStartsWithHyphen":
				return genarateEmailTimeStamp() + "@-mailinator.com";
			case "doubleDotInDomain":
				return genarateEmailTimeStamp() + "@mailinator..com";
			case "noAtTheRate":
				return genarateEmailTimeStamp() + "#mailinator.com";
			case "illegalChars":
				return genarateEmailTimeStamp() + "<>@mailinator.com";
			case "twoConsicutiveDots":
				return "u.." + genarateEmailTimeStamp() + "@mailinator.com";
			case "dotAtBeginning":
				return "." + genarateEmailTimeStamp() + "@mailinator.com";
			case "withQuotation":
				return "\"" + genarateEmailTimeStamp() + "@mailinator.com" + "\"";
			case "unclosedQuotation":
				return "\"" + genarateEmailTimeStamp() + "@mailinator.com";
			case "veryLengthyLocal":
				return "verylonglocalpartthatexceedstheusuallimitstoverifythelimitdoesitexceedornotbyaddingverylongcharacters@example.com";
			case "veryLengthyDomain":
				return "example@verylonglocalpartthatexceedstheusuallimitstoverifythelimitdoesitexceedornotbyaddingverylongcharacters.com";
			case "veryLenghtyTLD":
				return genarateEmailTimeStamp()
						+ "@example.verylonglocalpartthatexceedstheusuallimitstoverifythelimitdoesitexceedornotbyaddingverylongcharacters";
			case "missingSecondLevelDomain":
				return genarateEmailTimeStamp() + "com";
			case "withComma":
				return genarateEmailTimeStamp() + "@mailinator,com";
			case "doubleConsicutiveAt":
				return genarateEmailTimeStamp() + "@@mailinator.com";
			case "underscoreInTLD":
				return genarateEmailTimeStamp() + "@mailinator.c_m";
			case "dotBetweenName":
				return "u." + genarateEmailTimeStamp() + ".s@mailinator.com";
			case "spaceBeforeName":
				return "" + genarateEmailTimeStamp() + "@mailinator.com";
			case "spaceAfterName":
				return genarateEmailTimeStamp() + "@mailinator.com" + "";
			case "dotBeforeAt":
				return genarateEmailTimeStamp() + ".@mailinator.com";
			case "withoutDomain":
				return genarateEmailTimeStamp() + "@.com";

			default:
				return genarateEmailTimeStamp() + "@mailinator.com";
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

	public static Object[][] getTestDataFromExcel(String sheetName) {
		File file = new File(System.getProperty("user.dir") + "/src/main/java/com/huntcareer/qa/testdata/DDTFile.xlsx");
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

	public static String captureScreenshot(WebDriver driver, String testName) {
		if (driver == null) {
			System.err.println("[WARNING] WebDriver is null, skipping screenshot for test: " + testName);
			return null;
		}
		File srcSreenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String destinationPath = System.getProperty("user.dir") + "/test-output/Screenshots/" + testName + "_"
				+ timestamp + ".png";
		try {
			FileHandler.copy(srcSreenShot, new File(destinationPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destinationPath;
	}
}