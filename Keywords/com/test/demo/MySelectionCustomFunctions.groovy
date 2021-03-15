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
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import internal.GlobalVariable

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
//import com.kms.katalon.core.webui.key
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
//import org.eclipse.jetty.client.ContinueProtocolHandler.ContinueListener as ContinueListener
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.annotation.Keyword as Keyword


public class MySelectionCustomFunctions {
	//To print message in console
	KeywordLogger log = new KeywordLogger()
	@Keyword
	//Reset all Filters (Main Apply button in My selection)
	def ResetAllFlts(){
		//Reset all filters applied in My Selection
		WebUI.click(findTestObject('Object Repository/Final objects/MySelection/MainButns/i_reset-icon-lg-grey'))
		WebUI.delay(10)
	}//ResetAllFlts
	@Keyword
	//Hierarchy Button in My selection
	def HierarchyBtn(){
		//Click on hierarchy
		WebUI.click(findTestObject('Object Repository/Final objects/MySelection/MainButns/li_Attributes_hierarchyBtn'))
		WebUI.delay(10)
	}//HierarchyBtn
	@Keyword
	//Save button in hierarchy window
	def HierarchySave(){
		//Click on Save button in hierarchy window
		WebUI.click(findTestObject('Object Repository/Final objects/MySelection/MainButns/button_Save'))
		WebUI.delay(10)
	}//HierarchySave

	@Keyword
	//Main Apply button in my Selection
	def FilterApplyBtn(){
		WebDriver driver = DriverFactory.getWebDriver()
		WebElement element = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Final objects/MySelection/MainButns/button_Apply'),10)
		JavascriptExecutor executor = ((driver) as JavascriptExecutor)
		executor.executeScript('arguments[0].click()', element)
	}//FilterApplyBtn

	@Keyword
	//Check element presents in viewport else scroll to element then click
	def ScrollTo(def obj,def timeout){
		if (WebUI.verifyElementInViewport(obj , timeout,FailureHandling.CONTINUE_ON_FAILURE ) == true) {
			WebUI.click(obj)
		} else {
			WebUI.scrollToElement(obj , timeout)
			WebUI.click(obj)
		}
	}
	@Keyword
	//Validate is expected and actual item count is matched / not matched
	def CompareItemCnt(def expected,def actual){
		//expec = WebUI.verifyElementPresent(findTestObject(expected),10,FailureHandling.CONTINUE_ON_FAILURE)
		//actual  = WebUI.getText(findTestObject(actual))
		if(expected == actual)
		{
			WebUI.delay(10)
			//log.logInfo('Both actual and expected items are matched  '+actual)
			KeywordUtil.markPassed('SUCCESS: Both actual and expected items are matched  '+actual)
		}
		else
		{
			WebUI.delay(10)
			//log.logInfo('Items not matched with the expected items and the actual count is: '+actual)
			KeywordUtil.markFailed('ERROR : Items not matched with the expected items and the actual count is: '+actual + expected)

		}

	}//CompareItemCnt
	@Keyword
	//Validate is expected and actual tooltip info is matched / not matched
	def CompareTooltipInfo(def tiexpected, def tiactual){
		//expec = WebUI.verifyElementPresent(findTestObject(expected),10,FailureHandling.CONTINUE_ON_FAILURE)
		//actu  = WebUI.getText(findTestObject('Object Repository/Final Objects/Validation Objects/Actual/ActualItemCnt'))
		if(tiexpected == true)
		{
			//log.logInfo('Both actual and expected info is same' + tiactual)
			KeywordUtil.markPassed('SUCCESS: Both actual and expected info is same' + tiactual)
			//log.logInfo(actu)
		}
		else
		{
			//log.logInfo('tooltip info is not same as expected and the actual filtered info is: '+tiactual)
			KeywordUtil.markFailed('ERROR: tooltip info is not same as expected and the actual filtered info is: '+tiactual)

		}
	}//CompareTooltipInfo
	@Keyword
	//Validate is expected and actual tooltip info is matched / not matched
	def CompareStatsSummary(def tiexpected, def tiactual){
		//expec = WebUI.verifyElementPresent(findTestObject(expected),10,FailureHandling.CONTINUE_ON_FAILURE)
		//actu  = WebUI.getText(findTestObject('Object Repository/Final Objects/Validation Objects/Actual/ActualItemCnt'))
		if(tiexpected == true)
		{
			//log.logInfo('Both actual and expected info is same' + tiactual)
			KeywordUtil.markPassed('SUCCESS: Both actual and expected StatsSummary is same' + tiactual)
			//log.logInfo(actu)
		}
		else
		{
			//log.logInfo('tooltip info is not same as expected and the actual filtered info is: '+tiactual)
			KeywordUtil.markFailed('ERROR: StatsSummary is not same as expected and the actual filtered info is: '+tiactual)

		}
	}//CompareStatsSummary

	@Keyword
	//Check element presents in viewport else click using js element
	def clickUsingJS(TestObject to, int timeout) {
		WebDriver driver = DriverFactory.getWebDriver()
		WebElement element = WebUiCommonHelper.findWebElement(to, timeout)
		JavascriptExecutor executor = ((driver) as JavascriptExecutor)
		//executor.executeScript('arguments[0].click()', Arrays.asList(element))
		executor.executeScript('arguments[0].click()', element)
	}//clickUsingJS
	@Keyword
	//Click tooltip info btn in my selection
	def ClickInfoBtn()
	{
		WebDriver driver = DriverFactory.getWebDriver()
		WebElement element = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Final Objects/MySelection/MainButns/TooltipInfo'),10)
		JavascriptExecutor executor = ((driver) as JavascriptExecutor)
		executor.executeScript('arguments[0].click()', element)

	}//ClickInfo
	@Keyword
	//Save workspace and check if name already exists proceed
	def SaveWorkspace(def name){
		WebUI.click(findTestObject('Object Repository/Final objects/MySelection/MainButns/span_Welcome kat4cid008.com_ca'))
		WebUI.click(findTestObject('Object Repository/Final objects/MySelection/MainButns/a_Save Workspace'))
		WebUI.delay(10)
		WebUI.setText(findTestObject('Final Objects/MySelection/MainButns/input_Name your workspace_wrkS'), name)
		WebUI.click(findTestObject('Final Objects/MySelection/MainButns/Workspace_button_save'))
		WebUI.delay(10)
		if(WebUI.verifyElementPresent(findTestObject('Final Objects/MySelection/MainButns/button_OK'),10 ,FailureHandling.OPTIONAL)){
			WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_OK'))
		}
		else
		{
			WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_Yes'))
			WebUI.delay(5)
			WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_OK'))
		}

	}
	@Keyword
	def SetMsrVal(def abj,def obj,def timeout,def value){
		if (WebUI.waitForElementClickable(obj , timeout, FailureHandling.CONTINUE_ON_FAILURE ) == true) {
			WebUI.scrollToElement(obj , timeout)
			WebUI.delay(10)
			WebUI.scrollToElement(obj , timeout)
			WebUI.delay(10)
			WebUI.click(abj)
			WebUI.delay(10)
			WebUI.sendKeys(abj, Keys.chord(Keys.CONTROL, 'a'))
			WebUI.sendKeys(abj, Keys.chord(Keys.BACK_SPACE))
			WebUI.sendKeys(abj, value)
			WebUI.delay(10)
		} else {
			WebUI.delay(10)
			WebUI.scrollToElement(obj , timeout)
			WebUI.delay(10)
			WebUI.click(abj)
			WebUI.delay(10)
			WebUI.sendKeys(abj, Keys.chord(Keys.CONTROL, 'a'))
			WebUI.sendKeys(abj, Keys.chord(Keys.BACK_SPACE))
			WebUI.delay(10)
			WebUI.sendKeys(abj, value)

		}
	}
	@Keyword
	def SwitchtoWorkspace(){
		WebUI.click(findTestObject('Object Repository/Final Objects/MySelection/MainButns/sidebar-Menu button'))
		WebUI.delay(10)
		WebUI.click(findTestObject('Object Repository/Final Objects/MySelection/MainButns/Workspace_tab'))
		WebUI.delay(10)
	}

	@Keyword
	def SortOrderAscend(TestObject msrDes,TestObject msrAsc, TestObject msrDis){
		//To do Ascend
		if ((WebUI.verifyElementPresent(msrAsc, 10, FailureHandling.OPTIONAL)) == true) {
			FailureHandling.CONTINUE_ON_FAILURE({
				clickUsingJS(findTestObject('Final Objects/MySelection/MainButns/button_Done'),
						10)
			})
		} else if ((WebUI.verifyElementPresent(msrDes, 10, FailureHandling.OPTIONAL)) == true) {
			FailureHandling.CONTINUE_ON_FAILURE({
				clickUsingJS(msrDes)
				clickUsingJS(msrDis)
				clickUsingJS(findTestObject('Final Objects/MySelection/MainButns/button_Done'),
						10)
			})
		} else {
			WebUI.click(msrDis)
			WebUI.delay(10)
			WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_Done'))
		}
	}//SortOrderAscend

	@Keyword
	def SortOrderDescend(TestObject msrDes,TestObject msrAsc, TestObject msrDis){
		//To do Descend
		if ((WebUI.verifyElementPresent(msrDes, 10, FailureHandling.OPTIONAL)) == true)
		{
			clickUsingJS(findTestObject('Final Objects/MySelection/MainButns/button_Done'),
					10)
		}
		else if ((WebUI.verifyElementPresent(msrDis, 10, FailureHandling.OPTIONAL)) == true){
			clickUsingJS(msrDis,10)
			clickUsingJS(msrAsc,10)
			clickUsingJS(findTestObject('Final Objects/MySelection/MainButns/button_Done'),
					10)
		}

		else
		{
			clickUsingJS(msrAsc,10)
			clickUsingJS(findTestObject('Final Objects/MySelection/MainButns/button_Done'),
					10)

		}

	}//SortOrderDescend

	@Keyword
	def SortOrderDisable(TestObject msrDes,TestObject msrAsc, TestObject msrDis){
		//To do Sort Order Disable
		if ((WebUI.verifyElementPresent(msrDis, 10, FailureHandling.OPTIONAL)) == true) {

			clickUsingJS(findTestObject('Final Objects/MySelection/MainButns/button_Done'),
					10)

		} else if ((WebUI.verifyElementPresent(msrAsc, 10, FailureHandling.OPTIONAL)) == true) {

			clickUsingJS(msrAsc,10)
			clickUsingJS(msrDes,10)
			clickUsingJS(findTestObject('Final Objects/MySelection/MainButns/button_Done'),
					10)

		} else {
			WebUI.click(msrDes,10)
			WebUI.delay(10)
			WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_Done'))
		}
	}//SortOrderDisable
	@Keyword
	def TabularItemCount(TestObject obj1,TestObject obj2)
	{
		WebDriver driver = DriverFactory.getWebDriver()
		WebElement elmt = driver.findElement(obj1)
		//WebElement elmt = driver.findElement(By.id("attrtabular0"))
		List elmtcount = elmt.findElements(obj2)
		//List elmtcount = elmt.findElements(By.className("val-selected"))
		log.logWarning('No. of Rows in the WebTable: ' + elmtcount.size())

	}//TabularItemCount

}//public class MySelectionCustomFunctions
