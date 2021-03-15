package com.test.demo

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class sortfunctions {
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

}
