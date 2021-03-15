package com.test.demo

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
import com.sun.org.apache.xpath.internal.operations.String
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

public class KanvasLoginCID010CustomFunctions {
	@Keyword
	//Enter URL and Login with the credentials using Global variable also check if let's proceed exists click and continue
	def KLogin(){
		WebUI.navigateToUrl('https://kanvas-staging.slicerpl.com')
		WebUI.setText(findTestObject('Object Repository/Final objects/Login credentials/input_user'), GlobalVariable.CID010_usn)
		WebUI.setText(findTestObject('Object Repository/Final objects/Login credentials/input_encPass'), GlobalVariable.CID010_pwd)
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
	}//KLogin


}//public KanvasLoginCustomFunctions
