package com.BB

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
//import com.sun.org.apache.xpath.internal.operations.String
import com.kms.katalon.core.exception.StepErrorException
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.interactions.Actions as Actions
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor

import internal.GlobalVariable

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class Login {
	
	
	@Keyword
	//Function Updated on 16-10-20 with license button code
	//Function created on 23-07-20
	//Function updated on 10-08-20
	//Read URL,Uersname and Password from data file
	//Check if let's proceed exists click and continue
	def kanvasLoginU(String url,String userName,String pwd){
		WebUI.navigateToUrl(url)
		//Create object for username and password and set values from data file
		TestObject userNMinput = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//input[@class="input-tag" and @id="user" and @name="user"]')
		TestObject pwdinput = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//input[@class="input-tag" and @id="encPass" and @name="encPass"]')
		WebUI.setText(userNMinput, userName)
		WebUI.setText(pwdinput, pwd)

		// Create object and Click on Sign In from Login screen
		TestObject signInBtn = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//button[@class="btn grayBtn" and @id="submitBtn"]')
		(new generalFunc.AllgenralFunc()).clickUsingJS(signInBtn,10)
		WebUI.delay(10)
		//Create lets proceed check
		TestObject letsProceed = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//button[@id="submitBtn" and @class="confirm"]')

		if(WebUI.verifyElementPresent(letsProceed,10,FailureHandling.OPTIONAL))
		{
			WebUI.click(letsProceed)
			KeywordUtil.markPassed("Clicked on Let's proceed button")
			(new generalFunc.AllgenralFunc()).shortDelay()
			//chk for License expiry
			TestObject LicenseOK = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//button[@class="confirm"]')
			if(WebUI.verifyTextPresent("Information!",false,FailureHandling.OPTIONAL))
			{
				(new generalFunc.AllgenralFunc()).clickUsingJS(LicenseOK,10)
				KeywordUtil.markPassed("Clicked on License button")
			}
			else
			{
				KeywordUtil.logInfo("License Expiry info message not displayed")
				KeywordUtil.markPassed("License Expiry info message not displayed")
			}

		} else{
			KeywordUtil.markPassed("Continue on signin")
			(new generalFunc.AllgenralFunc()).shortDelay()
			//chk for License expiry
			TestObject LicenseOK = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//button[@class="confirm"]')
			if(WebUI.verifyTextPresent("Information!",false,FailureHandling.OPTIONAL))
			{
				WebUI.click(LicenseOK)
				KeywordUtil.markPassed("Clicked on Licensse OK button")
			}
			else
			{
				KeywordUtil.logInfo("License Expiry info message not displayed")
				KeywordUtil.markPassed("License Expiry info message not displayed")
			}
		}
	}//kanvasLoginU

	@Keyword
	//Function created on 23-09-20
	//Logout from Kanvas and relogin with other user
	def kanvasRelogin(String url,String userName,String pwd)
	{
		//Call function to Click on welcome user context menu
		(new generalFunc.AllgenralFunc()).clickUserLoginDropDown()
		(new generalFunc.AllgenralFunc()).shortDelay()

		//Make an object for Logout and click on the same
		TestObject logOut = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','', '//a[@id="logout-kanvas"]')
		(new generalFunc.AllgenralFunc()).clickUsingJS(logOut,10)
		(new generalFunc.AllgenralFunc()).shortDelay()

		//Validate any warning messages or browser alerts
		//alerts handling
		(new generalFunc.AllgenralFunc()).alertHandling()

		//Warning message check
		//(new generalFunc.AllgenralFunc()).warningMsgChk()
		//(new generalFunc.AllgenralFunc()).shortDelay()

		//Call login function and login with different user based on inputs given
		kanvasLogin(url,userName,pwd)



	}//kanvasRelogin()


	@Keyword
	//Function created on 23-07-20
	//Function updated on 10-08-20
	//Read URL,Uersname and Password from data file
	//Check if let's proceed exists click and continue
	def kanvasLogin(String url,String userName,String pwd)
	{
		WebUI.navigateToUrl(url)
		//Create object for username and password and set values from data file
		TestObject userNMinput = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//input[@class="input-tag" and @id="user" and @name="user"]')
		TestObject pwdinput = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//input[@class="input-tag" and @id="encPass" and @name="encPass"]')
		WebUI.setText(userNMinput, userName)
		WebUI.setText(pwdinput, pwd)

		// Create object and Click on Sign In from Login screen
		TestObject signInBtn = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//button[@class="btn grayBtn" and @id="submitBtn"]')
		(new generalFunc.AllgenralFunc()).clickUsingJS(signInBtn,10)
		WebUI.delay(10)
		//Create lets proceed check
		TestObject letsProceed = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//button[@id="submitBtn" and @class="confirm"]')

		if(WebUI.verifyElementPresent(letsProceed,10,FailureHandling.OPTIONAL))
		{
			WebUI.click(letsProceed)
			//chk for License expiry
			TestObject LicenseOK = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//button[@class="confirm"]')
			if(WebUI.verifyElementPresent(LicenseOK,10,FailureHandling.OPTIONAL))
			{
				(new generalFunc.AllgenralFunc()).clickUsingJS(LicenseOK,10)
				KeywordUtil.markPassed("Clicked on License button")
			}
			else
			{
				KeywordUtil.logInfo("License Expiry info message not displayed")
				KeywordUtil.markPassed("License Expiry info message not displayed")
			}

		} else{
			KeywordUtil.markPassed("Continue on signin")
			//chk for License expiry
			TestObject LicenseOK = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//button[@class="confirm"]')
			if(WebUI.verifyElementPresent(LicenseOK,10,FailureHandling.OPTIONAL))
			{
				WebUI.click(LicenseOK)
				KeywordUtil.markPassed("Clicked on Licensse OK button")
			}
			else
			{
				KeywordUtil.logInfo("License Expiry info message not displayed")
				KeywordUtil.markPassed("License Expiry info message not displayed")
			}
		}
	}//kanvasLogin

	@Keyword
	//Enter URL and Login with the credentials using Global variable also check if let's proceed exists click and continue
	def KLoginBenjaminuser43(){
		WebUI.navigateToUrl('https://kanvas-staging.slicerpl.com')
		WebUI.setText(findTestObject('Object Repository/Final objects/Login credentials/input_user'), GlobalVariable.BUser43)
		WebUI.setText(findTestObject('Object Repository/Final objects/Login credentials/input_encPass'), GlobalVariable.B43PWD)
		// Click on Sign In from Login screen
		WebUI.click(findTestObject('Object Repository/Final objects/Login credentials/button_Sign In'))
		WebUI.delay(10)
		//lets proceed check
		if(WebUI.verifyElementPresent(findTestObject('Object Repository/Final objects/Login credentials/button_Lets Proceed'),10 ,FailureHandling.OPTIONAL))
		{
			WebUI.click(findTestObject('Object Repository/Final objects/Login credentials/button_Lets Proceed'))

		} else{
			KeywordUtil.markPassed("Continue on signin")
		}
	}//KLoginBenjimanuser43
	/*
	 @Keyword
	 //Enter URL and Login with the credentials using test data also check if let's proceed exists click and continue
	 def multiuserlogin(){
	 def info = ['username' : '','password' : '']
	 def data = TestDataFactory.findTestData("Data Files/cid003usrs")
	 info.username = data.getValue(2,2)
	 info.password = data.getValue(3,2)
	 return info
	 WebUI.navigateToUrl('https://kanvas-staging.slicerpl.com')
	 WebUI.setText(findTestObject('Object Repository/Final objects/Login credentials/input_user'), GlobalVariable.BUser43)
	 WebUI.setText(findTestObject('Object Repository/Final objects/Login credentials/input_encPass'), GlobalVariable.B43PWD)
	 // Click on Sign In from Login screen
	 WebUI.click(findTestObject('Object Repository/Final objects/Login credentials/button_Sign In'))
	 WebUI.delay(10)
	 //lets proceed check
	 if(WebUI.verifyElementPresent(findTestObject('Object Repository/Final objects/Login credentials/button_Lets Proceed'),10 ,FailureHandling.OPTIONAL))
	 {
	 WebUI.click(findTestObject('Object Repository/Final objects/Login credentials/button_Lets Proceed'))
	 } else{
	 KeywordUtil.markPassed("Continue on signin")
	 }
	 }//KLoginBenjimanuser43*/

	@Keyword
	//Enter URL and Login with the credentials using Global variable also check if let's proceed exists click and continue
	def KproductionLogin(){
		WebUI.navigateToUrl('https://kanvas.slicerpl.com')
		WebUI.setText(findTestObject('Object Repository/Final objects/Login credentials/input_user'), GlobalVariable.pUser)
		WebUI.setText(findTestObject('Object Repository/Final objects/Login credentials/input_encPass'), GlobalVariable.pPWD)
		// Click on Sign In from Login screen
		WebUI.click(findTestObject('Object Repository/Final objects/Login credentials/button_Sign In'))
		WebUI.delay(10)
		//lets proceed check
		if(WebUI.verifyElementPresent(findTestObject('Object Repository/Final objects/Login credentials/button_Lets Proceed'),10 ,FailureHandling.OPTIONAL))
		{
			WebUI.click(findTestObject('Object Repository/Final objects/Login credentials/button_Lets Proceed'))

		} else{
			KeywordUtil.markPassed("Continue on signin")
		}
	}//KproductionLogin

	@Keyword
	//Enter URL and Login with the credentials using Global variable also check if let's proceed exists click and continue
	def KlocalLogin(){
		WebUI.navigateToUrl('192.168.1.9/kanvas/')
		WebUI.setText(findTestObject('Object Repository/Final objects/Login credentials/input_user'), GlobalVariable.LocalU)
		WebUI.setText(findTestObject('Object Repository/Final objects/Login credentials/input_encPass'), GlobalVariable.Localpwd)
		// Click on Sign In from Login screen
		WebUI.click(findTestObject('Object Repository/Final objects/Login credentials/button_Sign In'))
		WebUI.delay(10)
		//lets proceed check
		if(WebUI.verifyElementPresent(findTestObject('Object Repository/Final objects/Login credentials/button_Lets Proceed'),10 ,FailureHandling.OPTIONAL))
		{
			WebUI.click(findTestObject('Object Repository/Final objects/Login credentials/button_Lets Proceed'))

		} else{
			KeywordUtil.markPassed("Continue on signin")
		}
	}//KlocalLogin


}
