package com.BB
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By as By
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
//import com.sun.org.apache.xpath.internal.operations.String
import com.kms.katalon.keyword.excel.ExcelKeywords
import java.lang.String
import java.lang.Number
import java.util.concurrent.TimeUnit



public class genralFunctions {

	/***********My Selection Main Functions**************************/

	@Keyword
	//click ok button for higher columns warning messgae to switch to layout

	def warningForHigherCols() {


		WebDriver driver = DriverFactory.getWebDriver()
		TestObject warnOKBtn = makeTestObject('button', '', 'confirm', '', '','','')
		WebElement element = WebUiCommonHelper.findWebElement(warnOKBtn,10)
		JavascriptExecutor executor = ((driver) as JavascriptExecutor)
		executor.executeScript('arguments[0].click()', element)
		longDelay()
	}//warningForHigherCols
	@Keyword
	def loadTemplate(String tempName) {
		if(tempName != "")
		{
			switchToLayoutFromTabular()
			shortDelay()
			WebDriver driver = DriverFactory.getWebDriver()
			TestObject tempN = makeTestObject('img', '', 'templateDP', '', 'data-dptemplate',tempName ,'')
			WebElement element = WebUiCommonHelper.findWebElement(tempN,10)
			JavascriptExecutor executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click()', element)
			longDelay()
		}else
		{
			KeywordUtil.logInfo("tempName is blank")
		}


	}//loadTemplate(String tempName)

	@Keyword
	def currentTemplate() {


		TestObject currentTemp = makeTestObject('a', 'Current Template', '', 'vi-expcurrenttmp', '','' ,'')
		WebUI.delay(5)
		WebUI.click(currentTemp)
		longDelay()
	}//currentTemplate

	@Keyword
	def exportTemplateL(String tplExportFnm, String tplExportFormat) {

		if(tplExportFnm != "" & tplExportFormat != "" )
		{

			//Click on Export Tab
			clickOnExportBtnL()
			//Click on Current Template
			currentTemplate()

			//Set Export template filename
			TestObject tplExportFile = makeTestObject('input', '', 'form-control', 'tpl-export-exportfilename', '','' ,'')
			WebUI.delay(5)
			WebUI.click(tplExportFile)
			WebUI.setText(tplExportFile, tplExportFnm)
			WebUI.sendKeys(tplExportFile, Keys.chord(Keys.ENTER))
			shortDelay()

			//Click on exportFormat
			//validate the format then make object inorder export the ssame
			if(tplExportFormat == "png")
			{
				TestObject formatPNG = makeTestObject('button', '', 'btn blueBtn btn-sm fmt vi-export', 'png', '','' ,'')
				WebUI.delay(5)
				WebUI.click(formatPNG)
				WebUI.delay(5)
				//Make object for export success button
				TestObject exportedSucc = makeTestObject('button', '', 'confirm', '', '','' ,'')
				WebUI.click(exportedSucc)
				WebUI.delay(5)
			}
			else if(tplExportFormat == "jpg")
			{
				TestObject formatPNG = makeTestObject('button', '', 'btn blueBtn btn-sm fmt vi-export', 'jpg', '','' ,'')
				WebUI.delay(5)
				WebUI.click(formatPNG)
				WebUI.delay(5)
				//Make object for export success button
				TestObject exportedSucc = makeTestObject('button', '', 'confirm', '', '','' ,'')
				WebUI.click(exportedSucc)
				WebUI.delay(5)
			}
			else if(tplExportFormat == "pdf")
			{
				TestObject formatPNG = makeTestObject('button', '', 'btn blueBtn btn-sm fmt vi-export', 'pdf', '','' ,'')
				WebUI.delay(5)
				WebUI.click(formatPNG)
				WebUI.delay(5)
				//Make object for export success button
				TestObject exportedSucc = makeTestObject('button', '', 'confirm', '', '','' ,'')
				WebUI.click(exportedSucc)
				WebUI.delay(5)
			}
			else
			{
				KeywordUtil.logInfo("formattype is blank")
			}

		}else
		{
			KeywordUtil.logInfo("Format type and Exportfilename is blank")
		}

	}//exportTemplateL



	@Keyword
	//Reset all Filters (Main Apply button in My selection)
	def ResetAllFlts(){
		TestObject resetAll = makeTestObject('', '', '', '', '', '','id("resetAll")/i[@class="reset-icon-lg-grey"]')
		WebUI.click(resetAll)
		WebUI.delay(10)
	}//ResetAllFlts


	@Keyword
	//short Delay
	def shortDelay(){
		//Delays for 5 sec
		//WebUI.waitForPageLoad(GlobalVariable.shortDelay)
		WebUI.delay(GlobalVariable.shortDelay)
		KeywordUtil.logInfo("Delay for 5 seconds")
	}//shortDelay

	@Keyword
	//short Delay
	def waitTill(def waiT){
		//Delays for 5 sec
		//WebUI.waitForPageLoad(GlobalVariable.shortDelay)
		TimeUnit.MILLISECONDS.sleep(500)
		//WebUI.delay(waiT)
		//KeywordUtil.logInfo("Delay for 5 seconds")
	}//shortDelay

	@Keyword
	//long Delay
	def longDelay(){
		//Delays for 10 sec
		//WebUI.waitForPageLoad(GlobalVariable.longDelay)
		WebUI.delay(GlobalVariable.longDelay)
		KeywordUtil.logInfo("Delay for 10 seconds")
	}//longDelay

	@Keyword
	//long Delay
	def longestDelay(){
		//Delays for 20 sec
		//WebUI.waitForPageLoad(GlobalVariable.longestDelay)
		WebUI.delay(GlobalVariable.longestDelay)
		KeywordUtil.logInfo("Delay for 20 seconds")
	}//longDelay

	//to do fresh start of operations

	@Keyword
	def startFresh(String startFresh)
	{
		if(startFresh == '')
		{
			//Click on Kanvas Logo
			WebUI.click(findTestObject('Object Repository/Final Objects/MySelection/MainButns/kanvaslogo'))

			//alerts handling
			alertHandling()

			//Warning message check
			warningMsgChk()

			//Switch To Standard Mode
			switchToStandard()

			//Reset all filters in My Selection
			ResetAllFlts()

			//Make it Full screen mode
			//fullScrn()

		}else
		{
			KeywordUtil.logInfo("Not a Fresh start add additional operations to the existing testcase")
		}

	}//startFresh()

	@Keyword
	//Main Apply button in my Selection
	def fullScrn(){
		TestObject fullScrn = makeTestObject('i', '', 'fullscreen-icon-xl-white', 'header-full-screen-btn', '', '','')
		WebUI.click(fullScrn)
		//WebDriver driver = DriverFactory.getWebDriver()
		//WebElement element = WebUiCommonHelper.findWebElement(fullScrn,10)
		//JavascriptExecutor executor = ((driver) as JavascriptExecutor)
		//executor.executeScript('arguments[0].click()', element)
	}//fullScrn



	@Keyword
	//Main Apply button in my Selection
	def FilterApplyBtn(){
		WebDriver driver = DriverFactory.getWebDriver()
		TestObject fltApplyBtn = makeTestObject('button', '', 'mainAplyBtn', '', '', '', '')
		if(WebUI.verifyElementVisible(fltApplyBtn,FailureHandling.OPTIONAL))
		{
			WebElement element = WebUiCommonHelper.findWebElement(fltApplyBtn,10)
			JavascriptExecutor executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click()', element)
			//longestDelay()
			//longestDelay()
			WebUI.delay(20)
		}
		else
		{
			KeywordUtil.logInfo("Element is visible")
		}
	}//FilterApplyBtn


	@Keyword
	//Click on side menu options
	def sideMenuBar(){

		TestObject sideMenu = makeTestObject('span', '', 'glyphicon glyphicon-option-horizontal', '', '', '', '')
		shortDelay()
		WebUI.click(sideMenu)
		shortDelay()

	}//sideMenuBar()

	/* **********************   My Selection Main Functions   *****************************  */

	/* *******************Attributes and Measure Filters **********************************  */
	@Keyword
	static clickAttrVals(String vals, String vSep)
	{
		def mulVals = vals.split(vSep)

		int lenMul = mulVals.size()

		for (def index : (0..lenMul-1))
		{
			TestObject attrV = makeTestObject('li', mulVals[index].trim(), 'txt-attr-val', '', '', '', '')
			WebUI.click(attrV)
			WebUI.delay(5)
		}


	}//clickAttrVals
	@Keyword

	static selAttrNdVals(String attrNames,String attrVals, String nSep, String vSep)
	{

		if(attrNames != ""){

			def mulattrN = attrNames.split(nSep)
			def mulVals =  attrVals.split(nSep)
			int mulattrLen = mulattrN.size()
			KeywordLogger log = new KeywordLogger()
			for(def index :(0..mulattrLen-1))
			{
				String attr = mulattrN[index].trim()

				log.logInfo(attr)

				TestObject attrN = makeTestObject('div', attr, 'wrap-attr-name', '', '', '','')
				WebUI.click(attrN)
				//clickUsingJS(attrN,10)
				WebUI.delay(5)
				//shortDelay()
				clickAttrVals(mulVals[index],vSep)
				WebUI.click(attrN)
			}
		}else
		{
			KeywordUtil.logInfo("attribute name is blank")
		}

	}//selAttrNdVals

	@Keyword

	static selHAttrNdVals(String hAttrNames,String hAttrVals,  String nSep, String vSep)
	{

		if(hAttrNames != "" ){
			WebDriver driver = DriverFactory.getWebDriver()
			TestObject hIcon = makeTestObject('li', '', 'hierarchy-icon-lg-grey', 'hierarchyBtn', '', '','')
			WebElement element = WebUiCommonHelper.findWebElement(hIcon,10)
			JavascriptExecutor executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click()', element)

			//Select hierarchy icon
			//WebUI.click(findTestObject('Object Repository/Final Objects/MySelection/MainButns/li_Attributes_hierarchyBtn'))
			WebUI.delay(5)

			def mulattrN = hAttrNames.split(nSep)
			def mulVals =  hAttrVals.split(nSep)
			int mulattrLen = mulattrN.size()
			KeywordLogger log = new KeywordLogger()
			for(def index :(0..mulattrLen-1))
			{
				String attr = mulattrN[index].trim()

				KeywordUtil.logInfo(attr)
				String xpathHattrColl ='//li[@data-attrname="'+attr+'"]//div[@class="wrap-hattr-name"]'
				//String xpathHattrExpnd = '//li[@class="attr-name" and @data-attrname="'+attr+'"]//div[@class="wrap-hattr-name"]'
				TestObject attrNC = makeTestObject('', '', '', '', '', '',xpathHattrColl)
				//TestObject attrNExp = makeTestObject('', '', '', '', '', '',xpathHattrExpnd)
				//TestObject attrNE = makeTestObject('', '', '', '', '', '',xpathHattrExpnd)
				WebUI.click(attrNC)
				//shortDelay()
				clickAttrVals(mulVals[index],vSep)
				WebUI.click(attrNC)

				//TestObject attrN = makeTestObject('div', attr, 'wrap-hattr-name', '', '', '','')
				//WebUI.click(attrN)
				//shortDelay()
				//clickAttrVals(mulVals[index],vSep)
				//WebUI.click(attrN)
			}
			//Click on Hsave button
			TestObject hSave = makeTestObject('button', '', 'blueBtn', 'apply-hierar', '', '','')
			WebUI.click(hSave)
			//WebUI.click(findTestObject('Object Repository/Final Objects/MySelection/MainButns/hierarchysave'))
			WebUI.delay(5)

		}else
		{
			KeywordUtil.logInfo("Hattribute name is blank")
		}

	}//selHAttrNdVals

	@Keyword
	def SetMsrValbyFunc(String msrNames, String minVal, String maxVal,  String nSep ){

		if(msrNames != "")
		{
			KeywordUtil.logInfo("msrname available")
			def mulmsrN = msrNames.split(nSep)
			def mulMinVals = minVal.split(nSep)
			def mulMaxVals = maxVal.split(nSep)
			int mulmsrLen = mulmsrN.size()
			for(def index :(0..mulmsrLen-1))
			{
				String msr = mulmsrN[index].trim()
				KeywordUtil.logInfo(msr)
				WebDriver driver = DriverFactory.getWebDriver()
				//TestObject msrN = makeTestObject('div', msr, 'wrap-attr-name', '', '', '','')
				TestObject msrN = makeTestObject('', '', '', '', '','' ,'//li[@class="attr-name iamnumber searchAttrCls" and @data-attrname="'+msr+'"]//div[@class="wrap-attr-name"]')
				WebElement element = WebUiCommonHelper.findWebElement(msrN,10)
				JavascriptExecutor executor = ((driver) as JavascriptExecutor)
				executor.executeScript('arguments[0].click()', element)
				/*TestObject msrN = makeTestObject('div', msr, 'wrap-attr-name', '', '', '','')
				 WebUI.scrollToElement(msrN, 10)
				 WebUI.click(msrN)*/
				KeywordUtil.logInfo("msr"+msr+"clicked" )
				WebUI.delay(20)
				String xpathmsrMin = '//li[@class="num-attr-val" and @data-valof="'+msr+'"]//input[@type="text" and @name="minInput"]'
				KeywordUtil.logInfo(xpathmsrMin)
				//TestObject minValI = makeTestObject('input', '', 'minInput', '', '', '', '')
				TestObject minValI = makeTestObject('', '', '', '', '', '', xpathmsrMin)
				if(WebUI.verifyElementClickable(minValI)==true)
				{
					KeywordUtil.logInfo("Input box present")
					WebUI.scrollToElement(minValI,10)
					WebUI.delay(5)
					//KeywordUtil.logInfo('get minvalue'+ minValI)
					WebUI.click(minValI)
					WebUI.delay(10)
					String valMin = mulMinVals[index].trim()
					KeywordUtil.logInfo(valMin)
					WebUI.sendKeys(minValI, Keys.chord(Keys.CONTROL, 'a'))
					WebUI.sendKeys(minValI, Keys.chord(Keys.BACK_SPACE))
					WebUI.sendKeys(minValI, valMin)
					WebUI.sendKeys(minValI,Keys.chord(Keys.ENTER))
					WebUI.delay(10)
					String xpathmsrMax = '//li[@class="num-attr-val" and @data-valof="'+msr+'"]//input[@type="text" and @name="maxInput"]'
					KeywordUtil.logInfo(xpathmsrMax)
					//TestObject maxValI = makeTestObject('input', '', 'maxInput', '', '', '', '')
					TestObject maxValI = makeTestObject('', '', '', '', '', '', xpathmsrMax)
					WebUI.delay(5)
					WebUI.scrollToElement(maxValI,10)
					WebUI.delay(5)

					String valMax = mulMaxVals[index].trim()
					KeywordUtil.logInfo('Max value'+valMax)
					WebUI.sendKeys(maxValI, Keys.chord(Keys.CONTROL, 'a'))
					WebUI.sendKeys(maxValI, Keys.chord(Keys.BACK_SPACE))
					WebUI.sendKeys(maxValI, valMax)
					WebUI.sendKeys(maxValI,Keys.chord(Keys.ENTER))
					WebUI.delay(10)

					//WebUI.click(msrN)
				}
				else
				{
					KeywordUtil.logInfo("MinInput not present")
				}
			}//for(def index :(0..mulmsrLen-1))
		}
		else
		{
			KeywordUtil.logInfo("Measure name is not available")
		}
	}//SetMsrValbyFunc()
	@Keyword
	def setMsrMinVal(String minVal,String vSep)
	{

		def mulMinVals = minVal.split(vSep)
		int lenMulVal = minVal.size()
		for (def index : (0..lenMulVal-1))
		{
			TestObject minValI = makeTestObject('input', '', 'minInput', '', '', '', '')
			shortDelay()
			WebUI.click(minValI)
			shortDelay()
			WebUI.sendKeys(minValI, Keys.chord(Keys.CONTROL, 'a'))
			WebUI.sendKeys(minValI, Keys.chord(Keys.BACK_SPACE))
			WebUI.sendKeys(minValI, minVal)
			longDelay()
		}

	}//setMsrMinVal()

	@Keyword
	def setMsrMaxVal(String maxVal, String vSep)
	{

		def mulMaxVals = maxVal.split(vSep)
		int lenMulVal = maxVal.size()
		for (def index : (0..lenMulVal-1))
		{
			TestObject maxValI = makeTestObject('input', '', 'maxInput', '', '', '', '')
			shortDelay()
			WebUI.click(maxValI)
			shortDelay()
			WebUI.sendKeys(maxValI, Keys.chord(Keys.CONTROL, 'a'))
			WebUI.sendKeys(maxValI, Keys.chord(Keys.BACK_SPACE))
			WebUI.sendKeys(maxValI, maxVal[index].trim())
			longDelay()
		}

	}//setMsrMaxVal()



	def SetMsrVal(def abj,def obj,def timeout,def value){
		if (WebUI.waitForElementClickable(obj , timeout, FailureHandling.CONTINUE_ON_FAILURE ) == true) {
			WebUI.scrollToElement(obj , timeout)
			longDelay()
			WebUI.scrollToElement(obj , timeout)
			longDelay()
			WebUI.click(abj)
			longDelay()
			WebUI.sendKeys(abj, Keys.chord(Keys.CONTROL, 'a'))
			WebUI.sendKeys(abj, Keys.chord(Keys.BACK_SPACE))
			WebUI.sendKeys(abj, value)
			longDelay()
		} else {
			longDelay()
			WebUI.scrollToElement(obj , timeout)
			longDelay()
			WebUI.click(abj)
			longDelay()
			WebUI.sendKeys(abj, Keys.chord(Keys.CONTROL, 'a'))
			WebUI.sendKeys(abj, Keys.chord(Keys.BACK_SPACE))
			longDelay()
			WebUI.sendKeys(abj, value)

		}
	}//@Keyword



	/* *******************End of Attributes and Measure Filters *****************************/

	/* *******************Filter by Tags *****************************/


	@Keyword
	def setTextTag(String TextTag, String nSep)
	{
		if(TextTag != "")
		{
			shortDelay()
			//Click on Tags Tab
			clickUsingJS(findTestObject('Final Objects/MySelection/Tags/tagstab'), 10)
			shortDelay()
			def mulTags = TextTag.split(nSep)
			int lenMul = mulTags.size()
			for (def index : (0..lenMul-1))
			{
				TestObject tagsInput = makeTestObject('', '', '', '', '', '','id("s2id_autogen1")')
				WebUI.setText(tagsInput,mulTags[index])
				KeywordUtil.logInfo('TextTag' +mulTags[index])
				shortDelay()
				WebUI.sendKeys(tagsInput,Keys.chord(Keys.ENTER))
				shortDelay()
				if(WebUI.verifyTextPresent('No matches found', true,FailureHandling.OPTIONAL)==true)
				{
					//Switch to other text tags-Make object for chk other show tags
					TestObject showTags = makeTestObject('', '', '', '', '', '','id("mCSB_11_container")/label[@class="labelCls hideInSmap"]')
					clickUsingJS(showTags, 10)
					longDelay()
					//Click on input box and enter Text tag
					WebUI.setText(tagsInput, mulTags[index])
					WebUI.sendKeys(tagsInput, Keys.chord(Keys.ENTER))


				}else
				{

					KeywordUtil.logInfo("Tag from logged in users list")
				}

			}

			//Make object for click attributes and click to switch Attributes section
			TestObject switchAttrs = makeTestObject('a', 'Attributes', '', 'attr-tab-anch', 'href', '#attributes','')
			clickUsingJS(switchAttrs, 10)

		}else{
			KeywordUtil.logInfo("Text Tag is not available ")
		}

	}//setTextTag



	/* *******************End of  Filter by Tags *****************************/


	/**   *****************Group By attributes and Tags ***************        **/

	@Keyword
	static clickGrpAttrs(String groupByAttrName,  String nSep)
	{
		if(groupByAttrName != "" )
		{
			KeywordUtil.logInfo("groupByAttrName is available")
			WebDriver driver = DriverFactory.getWebDriver()
			TestObject resCtrlMenu = makeTestObject('i', '', '', '', '', '','id("limitSetBtn")/i[@class="setting-icon-lg-grey"]')
			WebElement element = WebUiCommonHelper.findWebElement(resCtrlMenu,10)
			JavascriptExecutor executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click()', element)
			//Group by attribute
			//TestObject resCtrlMenu = makeTestObject('i', '', '', '', '', '','id("limitSetBtn")/i[@class="setting-icon-lg-grey"]')
			//WebUI.click(resCtrlMenu)
			//TestObject grpTextTag = makeTestObject('label', '', 'lbl_cls chkTag_lbl', '', 'for', 'chkAttrText','')
			//WebUI.click(grpTextTag)
			// clickUsingJS(findTestObject('Object Repository/Final Objects/MySelection/Tags/Group_Texts_tag'),10)
			//WebUI.delay(5)
			//Click on Result Control Menu
			//clickUsingJS(findTestObject('Object Repository/Final Objects/MySelection/MainButns/ResultControlMenu'),10)
			WebUI.delay(5)
			def mulAttrs = groupByAttrName.split(nSep)

			int lenMul = mulAttrs.size()

			for (def index : (0..lenMul-1))
			{
				TestObject GattrS = makeTestObject('label', '', 'lbl_cls', '', 'for', mulAttrs[index],'')
				WebUI.delay(5)
				WebUI.click(GattrS)
			}
			//WebUI.click(resCtrlMenu)
			executor.executeScript('arguments[0].click()', element)
		}else{
			KeywordUtil.logInfo("Attribute is not available to group")
		}

	}//clickGrpAttrs
	@Keyword
	static clickGrpTags(String TextTag)
	{
		if(TextTag != "")
		{
			//Click on Result Control Menu
			clickUsingJS(findTestObject('Object Repository/Final Objects/MySelection/MainButns/ResultControlMenu'),10)

			WebUI.delay(5)
			clickUsingJS(findTestObject('Object Repository/Final Objects/MySelection/Tags/Group_Texts_tag'),10)
			WebUI.delay(5)
		}else{
			KeywordUtil.logInfo("Text tag not available")
		}
	}//clickGrpTags

	/*******************End of Group By attributes and Tags *****************/


	/*  **************************** Sort and Stats Functions**************************  */
	@Keyword
	def sortDone(String sortAttrName, String statsAttrName)
	{
		if(sortAttrName != "" | statsAttrName != ""){
			clickUsingJS(findTestObject('Final Objects/MySelection/MainButns/button_Done'),
					10)
		}else{
			KeywordUtil.logInfo("Sort or Stats attribute name is not available")
		}


	}
	//Created on 6-April-2020
	//To call directly in sort and stats functions
	@Keyword
	def doneSortBtn()
	{

		clickUsingJS(findTestObject('Final Objects/MySelection/MainButns/button_Done'),10)
	}//doneSortBtn


	@Keyword
	static doSort(String sortAttrName, String attrStatus, String nSep, String vSep)
	{
		if(sortAttrName != "")
		{
			//Click on Sort Icon
			WebDriver driver = DriverFactory.getWebDriver()
			TestObject sorticon = makeTestObject('li', '', 'globstatsetting-icon-lg-grey', 'statSortBtn', '', '','')
			WebElement element = WebUiCommonHelper.findWebElement(sorticon,10)
			JavascriptExecutor executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click()', element)
			WebUI.delay(5)
			def mulMsrSort = sortAttrName.split(nSep)
			def mulmsrStatus = attrStatus.split(vSep)
			int mulMsrLen = mulMsrSort.size()
			KeywordLogger log = new KeywordLogger()

			for(def index :(0..mulMsrLen-1))
			{
				String msr = mulMsrSort[index].trim()
				log.logInfo(msr)
				mDoSort(mulMsrSort[index], mulmsrStatus[index], vSep)
			}
			WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_Done'))
		}else{
			KeywordUtil.logInfo("Sort Attribute is not available")
		}


	}//doSort
	@Keyword
	static doStats(String statsAttrName, String nSep)
	{
		if(statsAttrName != "")
		{
			//Click on Sort Icon
			WebDriver driver = DriverFactory.getWebDriver()
			TestObject sorticon = makeTestObject('li', '', 'globstatsetting-icon-lg-grey', 'statSortBtn', '', '','')
			WebElement element = WebUiCommonHelper.findWebElement(sorticon,10)
			JavascriptExecutor executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click()', element)


			def mulMsrStat = statsAttrName.split(nSep)
			int mulMsrLen = mulMsrStat.size()
			KeywordLogger log = new KeywordLogger()
			for(def index :(0..mulMsrLen-1))
			{
				String msr = mulMsrStat[index].trim()
				log.logInfo(msr)
				String woChkd = '//input[@data-stat='+msr+']/following-sibling::label'
				String whChkd = '//input[@data-stat='+msr+']'
				TestObject msrST = makeTestObject('', '', '', '', '', '', whChkd)
				WebUI.delay(10)
				if((WebUI.verifyElementChecked(msrST,10,FailureHandling.OPTIONAL))== true){
					log.logInfo("Element is checked")
				}
				else
				{
					TestObject msrSTL = makeTestObject('', '', '', '', '', '',woChkd)
					WebUI.click(msrSTL)
					WebUI.delay(10)
				}
			}
			WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_Done'))
		}else{
			KeywordUtil.logInfo("Stats Attribute is not available")
		}


	}//doStats

	@Keyword
	static mDoSort(String sortAttrName, String attrStatus,String vSep)
	{

		def mulmsrStatus = attrStatus.split(vSep)
		int lenMulmsrStatus = mulmsrStatus.size()
		for (def index : (0..lenMulmsrStatus-1))
		{
			String attrClass
			if(attrStatus == ' Ascend ' )
			{
				attrClass = 'sort-icon-sm-blue'
			}
			else if(attrStatus == ' descend ')
			{
				attrClass = 'descend'
			}
			else
			{
				attrClass = 'sort-icon-sm-darkgrey'
			}
			//Create Object
			TestObject sortAttr = makeTestObject('span', '', attrClass, '', 'data-sort', sortAttrName,'')
			TestObject stDisable = makeTestObject('span', '', 'sort-icon-sm-darkgrey', '', 'data-sort', sortAttrName,'')
			TestObject stAscend = makeTestObject('span', '', 'sort-icon-sm-blue', '', 'data-sort', sortAttrName,'')
			TestObject stDescend = makeTestObject('span', '', 'descend', '', 'data-sort', sortAttrName,'')
			//to do Ascend
			if(attrStatus == 'Ascend')
			{
				//SortOrderAscend(stDisable,stAscend,stDescend)
				//To do Ascend
				if ((WebUI.verifyElementPresent(stAscend, 10, FailureHandling.OPTIONAL)) == true) {
					FailureHandling.CONTINUE_ON_FAILURE({
						//clickUsingJS(findTestObject('Final Objects/MySelection/MainButns/button_Done'),
						//		10)
						WebUI.delay(5)
					})
				} else if ((WebUI.verifyElementPresent(stDescend, 10, FailureHandling.OPTIONAL)) == true) {
					FailureHandling.CONTINUE_ON_FAILURE({
						WebUI.click(stDescend)
						WebUI.click(stDisable)
						//WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_Done'))
					})
				} else {
					WebUI.click(stDisable)
					WebUI.delay(5)
					//WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_Done'))
				}

			}

			if(attrStatus == 'Descend')
			{
				//SortOrderDescend(stDisable,stAscend,stDescend)
				if ((WebUI.verifyElementPresent(stDescend, 10, FailureHandling.OPTIONAL)) == true)
				{
					//clickUsingJS(findTestObject('Final Objects/MySelection/MainButns/button_Done'),
					//	10)
					WebUI.delay(5)
				}
				else if ((WebUI.verifyElementPresent(stDisable, 10, FailureHandling.OPTIONAL)) == true){
					WebUI.click(stDisable)
					WebUI.click(stAscend)
					//WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_Done'))
				}

				else
				{
					WebUI.click(stAscend)
					//WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_Done'))

				}


			}
			if(attrStatus == 'Disable')
			{
				//SortOrderDisable(stDisable,stAscend,stDescend)
				if ((WebUI.verifyElementPresent(stDisable, 10, FailureHandling.OPTIONAL)) == true) {

					//clickUsingJS(findTestObject('Final Objects/MySelection/MainButns/button_Done'),
					//		10)
					WebUI.delay(5)
				} else if ((WebUI.verifyElementPresent(stAscend, 10, FailureHandling.OPTIONAL)) == true) {

					WebUI.click(stAscend)
					WebUI.click(stDescend)
					//WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_Done'))

				} else {
					WebUI.click(stDescend)
					WebUI.delay(5)
					//WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_Done'))
				}
			}



		}



	}//mDoSort

	//Group sort function Created on 6-April-2020
	@Keyword
	def groupSortTab(String grpsortAttrName, String grpattrStatus, String aggrVal,String vSep,String nSep)
	{
		//Click on Sort Icon
		WebDriver driver = DriverFactory.getWebDriver()
		TestObject sorticon = makeTestObject('li', '', 'globstatsetting-icon-lg-grey', 'statSortBtn', '', '','')
		WebElement element = WebUiCommonHelper.findWebElement(sorticon,10)
		JavascriptExecutor executor = ((driver) as JavascriptExecutor)
		executor.executeScript('arguments[0].click()', element)
		shortDelay()

		//Create and click on groupsort object
		TestObject grpSortTab = makeTestObject('a', '', '', 'groupsorttab', '', '','')
		WebUI.click(grpSortTab)

		//set sort order in groupsort
		WebUI.delay(5)
		def mulMsrSort = grpsortAttrName.split(nSep)
		def mulMsrAggr = aggrVal.split(nSep)
		def mulmsrStatus = grpattrStatus.split(vSep)
		int mulMsrLen = mulMsrSort.size()
		KeywordLogger log = new KeywordLogger()

		for(def index :(0..mulMsrLen-1))
		{
			String msr = mulMsrSort[index].trim()
			log.logInfo(msr)
			groupmDoSort(mulMsrSort[index], mulmsrStatus[index], vSep)
			//grpSortAggr(mulMsrSort[index],mulMsrAggr[index])
			//KeywordUtil.logInfo(mulMsrAggr[index]))

		}

		//Click on sort done button
		//WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_Done'))

	}
	@Keyword
	static groupmDoSort(String grpsortAttrName, String grpattrStatus,String vSep)
	{

		def mulmsrStatus = grpattrStatus.split(vSep)
		int lenMulmsrStatus = mulmsrStatus.size()
		for (def index : (0..lenMulmsrStatus-1))
		{
			String attrClass
			if(grpattrStatus == ' Ascend ' )
			{
				attrClass = 'sort-icon-sm-blue'
			}
			else if(grpattrStatus == ' descend ')
			{
				attrClass = 'descend'
			}
			else
			{
				attrClass = 'sort-icon-sm-darkgrey'
			}
			//Create Object
			TestObject grpsortAttr = makeTestObject('span', '', attrClass, '', 'data-grpsort', grpsortAttrName,'')
			TestObject grpstDisable = makeTestObject('span', '', 'sort-icon-sm-darkgrey', '', 'data-grpsort', grpsortAttrName,'')
			TestObject grpstAscend = makeTestObject('span', '', 'sort-icon-sm-blue', '', 'data-grpsort', grpsortAttrName,'')
			TestObject grpstDescend = makeTestObject('span', '', 'descend', '', 'data-grpsort', grpsortAttrName,'')
			//to do Ascend
			if(grpattrStatus == 'Ascend')
			{
				//SortOrderAscend(stDisable,stAscend,stDescend)
				//To do Ascend
				if ((WebUI.verifyElementPresent(grpstAscend, 10, FailureHandling.OPTIONAL)) == true) {
					FailureHandling.CONTINUE_ON_FAILURE({
						//clickUsingJS(findTestObject('Final Objects/MySelection/MainButns/button_Done'),
						//		10)
						WebUI.delay(5)
					})
				} else if ((WebUI.verifyElementPresent(grpstDescend, 10, FailureHandling.OPTIONAL)) == true) {
					FailureHandling.CONTINUE_ON_FAILURE({
						WebUI.click(grpstDescend)
						WebUI.click(grpstDisable)
						//WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_Done'))
					})
				} else {
					WebUI.click(grpstDisable)
					WebUI.delay(5)
					//WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_Done'))
				}

			}

			if(grpstDisable == 'Descend')
			{
				//SortOrderDescend(stDisable,stAscend,stDescend)
				if ((WebUI.verifyElementPresent(grpstDescend, 10, FailureHandling.OPTIONAL)) == true)
				{
					//clickUsingJS(findTestObject('Final Objects/MySelection/MainButns/button_Done'),
					//	10)
					WebUI.delay(5)
				}
				else if ((WebUI.verifyElementPresent(grpstDisable, 10, FailureHandling.OPTIONAL)) == true){
					WebUI.click(grpstDisable)
					WebUI.click(grpstAscend)
					//WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_Done'))
				}

				else
				{
					WebUI.click(grpstAscend)
					//WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_Done'))

				}


			}
			if(grpattrStatus == 'Disable')
			{
				//SortOrderDisable(stDisable,stAscend,stDescend)
				if ((WebUI.verifyElementPresent(grpstDisable, 10, FailureHandling.OPTIONAL)) == true) {

					//clickUsingJS(findTestObject('Final Objects/MySelection/MainButns/button_Done'),
					//		10)
					WebUI.delay(5)
				} else if ((WebUI.verifyElementPresent(grpstAscend, 10, FailureHandling.OPTIONAL)) == true) {

					WebUI.click(grpstAscend)
					WebUI.click(grpstDescend)
					//WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_Done'))

				} else {
					WebUI.click(grpstDescend)
					WebUI.delay(5)
					//WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_Done'))
				}
			}



		}



	}//groupmDoSort

	@Keyword
	def grpSortAggr(String grpsortAttrName,String aggrVal)
	{
		KeywordUtil.logInfo("Aggregation value"+aggrVal)
		//Click on aggregation dropdown
		TestObject clickOnAggrDropDown = makeTestObject('', '', '', '', '', '','//li[@data-gsattrname="'+grpsortAttrName+'" and @class="gsortli gsortLiVisible oddBackGrnd_li"]//span[@class="select2-chosen"]')
		WebUI.click(clickOnAggrDropDown)
		shortDelay()
		//select aggregation
		KeywordUtil.logInfo(aggrVal)

		TestObject aggregationdd = makeTestObject('', '', '', '', '', '','//ul[@class="select2-results"]//div[contains(text(),"'+aggrVal+'")]')
		//WebUI.selectOptionByValue(aggregationdd,aggrVal,false)
		//WebUI.selectOptionByValue(clickOnAggrDropDown, aggrVal, false)
		WebUI.click(aggregationdd)


	}



	/*  *******************************  End of Sort and Stats  Functions************************  */

	/*  *******************************    ItemLimit Functions *******************************   */
	@Keyword
	def setItemLimit(def limit)
	{
		if(limit > '0')
		{

			KeywordUtil.logInfo("Item limit is available")
			WebDriver driver = DriverFactory.getWebDriver()
			TestObject resCtrlMenu = makeTestObject('i', '', '', '', '', '','id("limitSetBtn")/i[@class="setting-icon-lg-grey"]')
			WebElement element = WebUiCommonHelper.findWebElement(resCtrlMenu,10)
			JavascriptExecutor executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click()', element)


			//TestObject resCtrlMenu = makeTestObject('i', '', '', '', '', '','id("limitSetBtn")/i[@class="setting-icon-lg-grey"]')
			//WebUI.click(resCtrlMenu)
			//WebUI.delay(5)
			//Click on Item Limit text box
			clickUsingJS(findTestObject('Object Repository/Final Objects/MySelection/MainButns/i_set-limit'),10)
			WebUI.delay(10)
			//Set Limit as 10
			TestObject itemLimitInput = makeTestObject('input', '', '', 'first-n', '', '','')
			WebUI.setText(itemLimitInput, limit)
			WebUI.sendKeys(itemLimitInput,Keys.chord(Keys.ENTER) )


		}else{
			KeywordUtil.logInfo("Item limit is not available")
		}
	}//setItemLimit()

	/*  ****************************** End of ItemLimit Functions ******************************* */

	/*  *******************************     Show Data Functions   *******************************  */
	@Keyword
	def enableShowData()
	{
		//Enable Show data
		TestObject enableSD = makeTestObject('label', '', 'newtoggleLbl', '', 'data-smvalue', 'showm', '')
		shortDelay()
		WebUI.click(enableSD)

	}//enableShowData()


	@Keyword
	def enableHideData()
	{

		//Enable Hide data



	}//enableHideData()

	@Keyword
	static doShowData(String showDataAttrName, String nSep)
	{
		if(showDataAttrName != "")
		{
			//WebUI.delay(5)
			//Showdata
			WebDriver driver = DriverFactory.getWebDriver()
			TestObject showData = makeTestObject('li', '', 'showmeasure-icon-xl-grey', 'showMeasrBtn', '', '','')
			WebElement element = WebUiCommonHelper.findWebElement(showData,10)
			JavascriptExecutor executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click()', element)

			//clickUsingJS(showData, 10)
			//WebUI.scrollToElement(showData, 10)
			//WebUI.click(showData)
			//Enable showdata
			TestObject enableSD = makeTestObject('label', '', 'newtoggleLbl', '', 'data-smvalue', 'showm', '')
			WebUI.delay(5)
			WebUI.click(enableSD)
			def mulMsrSD = showDataAttrName.split(nSep)
			int mulMsrLen = mulMsrSD.size()
			KeywordLogger log = new KeywordLogger()
			def done = false
			for(def index :(0..mulMsrLen-1))
			{
				String msr = mulMsrSD[index].trim()
				log.logInfo(msr)
				String valwochk = '//input[@data-quickg='+msr+']/following-sibling::label'
				String valwchk = '//input[@data-quickg='+msr+']'
				TestObject msrSD = makeTestObject('', '', '', '', '', '',valwchk)
				WebUI.delay(5)
				log.logInfo(valwochk)
				log.logInfo(valwchk)
				if((WebUI.verifyElementChecked(msrSD,10,FailureHandling.OPTIONAL))== true){
					KeywordUtil.logInfo("Attribute enabled for ShowData")
					WebUI.delay(5)
					//WebUI.click(findTestObject('Object Repository/Final Objects/MySelection/MainButns/Page_SLICeR Kanvas - My Selection/button_Done_close close-icon-lg-blue'))
					//WebUI.delay(5)
				}else
				{
					TestObject msrSDL = makeTestObject('', '', '', '', '', '', valwochk)
					WebUI.delay(5)
					KeywordUtil.logInfo("Click on Check box to Enable Show Data")
					WebUI.click(msrSDL)
					//WebUI.scrollToElement(msrSDL, 10)
					//WebUI.click(msrSDL)
					WebUI.delay(5)
					done = true
					//saveShowData()
					//WebUI.delay(5)
					//WebUI.click(findTestObject('Final Objects/MySelection/MainButns/SaveNdApplyShowdata'))
				}
			}

			if(done==true)
			{
				//saveShowData()
				WebUI.delay(5)
				WebUI.click(findTestObject('Final Objects/MySelection/MainButns/SaveNdApplyShowdata'))
			}else
			{
				WebUI.delay(5)
				WebUI.click(findTestObject('Object Repository/Final Objects/MySelection/MainButns/Page_SLICeR Kanvas - My Selection/button_Done_close close-icon-lg-blue'))
				WebUI.delay(5)
			}


		}else{
			KeywordUtil.logInfo("Showdata Attribute Name is not available ")
		}

	}//doShowData
	@Keyword
	static saveShowData()
	{
		shortDelay()
		WebUI.click(findTestObject('Final Objects/MySelection/MainButns/SaveNdApplyShowdata'))

	}//saveShowData



	/*  *******************************  End of Show Data Functions *******************************  */

	/* *******************************     Hierarchy Functions       ****************************** */

	@Keyword
	//Hierarchy Button in My selection
	def HierarchyBtn(){
		//Click on hierarchy
		WebUI.click(findTestObject('Object Repository/Final objects/MySelection/MainButns/li_Attributes_hierarchyBtn'))
		longDelay()
	}//HierarchyBtn
	@Keyword
	//Save button in hierarchy window
	def HierarchySave(){
		//Click on Save button in hierarchy window
		WebUI.click(findTestObject('Object Repository/Final objects/MySelection/MainButns/button_Save'))
		longDelay()
	}//HierarchySave

	/* *****************************************  End of Hierarchy Functions  **************************************** */


	/* *****************************************  End of My Selection Main Functions  ************************************* */

	/* ************************************************  General Functions   ********************************************** */

	@Keyword
	//Check element presents in viewport else scroll to element then click
	def ScrollTo(def obj,def timeout){
		if (WebUI.verifyElementInViewport(obj , timeout,FailureHandling.CONTINUE_ON_FAILURE ) == true) {
			WebUI.click(obj)
		} else {
			WebUI.scrollToElement(obj , timeout)
			WebUI.click(obj)
		}
	}//ScrollTo()

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
	//click on welcome user
	def clickUserLoginDropDown(){
		TestObject userDDMenu = makeTestObject('span','', 'caret', '', '', '', '')
		shortDelay()
		WebUI.click(userDDMenu)
		shortDelay()
	}//clickUserLoginDropDown()

	@Keyword
	def alertHandling()
	{
		def elementPresent=WebUI.waitForAlert(20)
		if (elementPresent==true) {
			WebUI.acceptAlert()
			shortDelay()
		} else {

			KeywordUtil.logInfo("No alert displayed")
		}
	}

	/* ***************************************  End of General Functions      ********************************** */

	/* ***************************************  Validation Functions  ********************************** */
	@Keyword
	//Validate is expected and actual item count is matched / not matched
	def CompareItemCnt(String expected){
		//if(expected > "0" & groupByAttrName != ""){
		if(expected .contains(",") & expected > "0"){

			actualItemCntFlt(expected)

		}else{

			actualItemCntGrp(expected)

		}

	}//CompareItemCnt

	@Keyword
	//make Actual Count according to filters or groups
	def actualItemCntFlt(String expected){
		if(expected != ""){
			def actualItemcnt = WebUI.getText(findTestObject('Final Objects/Validation Objects/Actual/ActualItemCnt'))
			def actCnt = actualItemcnt.split(/\s/)
			String actCntStr = actCnt[0] + ',' + actCnt[2]

			if(expected == actCntStr)
			{

				longDelay()
				//log.logInfo('Both actual and expected items are matched  '+actual)
				KeywordUtil.markPassed('SUCCESS: Both actual and expected items are matched....and the actual and expeted item count is  '+ actCntStr+'  '+ expected)

			}
			else
			{
				longDelay()
				//log.logInfo('Items not matched with the expected items and the actual count is: '+actual)
				KeywordUtil.markFailed('ERROR : Items not matched with the expected items and the actual and expected item  count is: '+actCntStr+' ' +expected)
			}

		}else
		{

			KeywordUtil.logInfo("Expected item count is not available")
		}

	}//actualItemCntFlt()

	@Keyword
	//make Actual Count according to filters or groups
	def actualItemCntGrp(String expected){
		if(expected != ""){
			def actualItemcnt = WebUI.getText(findTestObject('Final Objects/Validation Objects/Actual/ActualItemCnt'))
			def actCnt = actualItemcnt.split(/\s/)
			String actual = actCnt[0]
			if(expected == actual)
			{

				longDelay()
				//log.logInfo('Both actual and expected items are matched  '+actual)
				KeywordUtil.markPassed('SUCCESS: Both actual and expected items are matched....and the actual and expeted item count is  '+ actual+'  '+ expected)

			}
			else
			{
				longDelay()
				//log.logInfo('Items not matched with the expected items and the actual count is: '+actual)
				KeywordUtil.markFailed('ERROR : Items not matched with the expected items and the actual and expected item  count is: '+actual+' ' +expected)
			}

		}else
		{

			KeywordUtil.logInfo("Expected item count is not available")
		}

	}//actualItemCntGrp()




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
	//Click tooltip info btn in my selection
	def ClickInfoBtn()
	{
		WebDriver driver = DriverFactory.getWebDriver()
		WebElement element = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Final Objects/MySelection/MainButns/TooltipInfo'),10)
		JavascriptExecutor executor = ((driver) as JavascriptExecutor)
		executor.executeScript('arguments[0].click()', element)

	}//ClickInfoBtn()

	@Keyword
	def TabularItemCount() //gets tabular item count
	{
		//Click on open tabular configuration
		WebUI.click(findTestObject('Object Repository/Final Objects/MySelection/Tabular/MainButns/div_Cancel_pivot-config-icon'))
		shortDelay()
		//Click on ID to expand
		WebUI.click(findTestObject('Object Repository/Final Objects/MySelection/Tabular/Attributes/ID-attr'))
		shortDelay()
		//Call web driver to get item count form table
		WebDriver driver = DriverFactory.getWebDriver()
		//obj1 refers to first table in tabular
		WebElement elmt = driver.findElement(By.id("attrtabular0"))
		//Get no.of items are presents in selected class
		List elmtcount = elmt.findElements(By.className("val-selected"))
		//Convert into integer type
		int  elemcntsz = elmtcount.size()
		//Close tabular configuration window
		WebUI.click(findTestObject('BenjimanBarker/Tabular/OpenTabularConfig'))
		shortDelay()
		return elemcntsz
	}//TabularItemCount
	@Keyword
	def Stndardcnt() //gets actual standard item count
	{

		//Switch to standard
		WebUI.click(findTestObject('Object Repository/Final Objects/MySelection/MainButns/a_Standard'))
		longDelay()
		//Get Item count from Standard
		//JString is an another method to convert data types
		//JString stndardcnt = WebUI.getText(findTestObject('Final Objects/Validation Objects/Actual/ActualItemCnt'))
		def stndardcnt = WebUI.getText(findTestObject('Final Objects/Validation Objects/Actual/ActualItemCnt'))
		longDelay()

		//Set to string variable
		//JString partName = stndardcnt.split(/\s/)[0]
		def partName = stndardcnt.split(/\s/)[0]
		//Convert string to integer
		int intPartName = Integer.parseInt(partName)
		//WebUI.comment('Total no.of item count in Standard Mode'+intPartName)
		shortDelay()
		return intPartName
	}//stndardcnt()

	@Keyword
	def tabVsStdcnt() //Compares tabular and standard item count
	{
		//Assign tabular count to variable
		int tabcnt = TabularItemCount()
		//Assign standard count to variable
		int stdcnt = Stndardcnt()
		if(tabcnt == stdcnt)
		{
			longDelay()
			KeywordUtil.markPassed('SUCCESS: Both Tabular and Standard items are matched '+stdcnt)
		}
		else
		{
			longDelay()
			KeywordUtil.markFailed('ERROR : Both Tabular and Standard items are not matched, the Stndard count is' +stdcnt+ 'and the actual count is'+tabcnt)
		}

	}//tabVsStdcnt()

	@Keyword
	def getActualItemCntG()
	{
		//Get actual item count
		def actualItemcnt = WebUI.getText(findTestObject('Final Objects/Validation Objects/Actual/ActualItemCnt'))
		def actCnt = actualItemcnt.split(/\s/)
		//println (actICnt)
		//String actCntStr = actCnt[0] + ',' + actCnt[2]
		String actCntStr = actCnt[0]
		KeywordUtil.logInfo (actCntStr)
		return actCntStr
	}//getActualItemCntG()

	/* ************************************** End of Validation Functions  ********************************** */


	/* ***************************************  Workspace Functions   ********************************** */
	@Keyword
	//Save workspace and check if name already exists proceed
	def saveWS(def workspaceNameS){
		//WebUI.click(findTestObject('Object Repository/Final objects/MySelection/MainButns/span_Welcome kat4cid008.com_ca'))
		//Click on user dropdown menu
		if(workspaceNameS != '')
		{
			clickUserLoginDropDown()

			WebUI.click(findTestObject('Object Repository/Final objects/MySelection/MainButns/a_Save Workspace'))
			WebUI.delay(10)
			WebUI.setText(findTestObject('Final Objects/MySelection/MainButns/input_Name your workspace_wrkS'), workspaceNameS)
			WebUI.click(findTestObject('Final Objects/MySelection/MainButns/Workspace_button_save'))
			WebUI.delay(10)
			//if(WebUI.verifyElementPresent(findTestObject('Final Objects/MySelection/MainButns/button_OK'),10 ,FailureHandling.OPTIONAL)){
			if(WebUI.verifyTextNotPresent("Do you want to overwrite it", false, FailureHandling.OPTIONAL)){
				KeywordUtil.logInfo("No workspace named the same....If block")
				WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_OK'))
			}
			else
			{
				WebUI.delay(2)
				KeywordUtil.logInfo("workspace named as the same name....else block")
				//WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_Yes'))
				TestObject btnYes = makeTestObject('button', '', 'dialogboot', '', 'data-bb-handler', 'confirm', '')
				KeywordUtil.logInfo("yes button check")
				shortDelay()
				WebUI.click(btnYes)
				WebUI.delay(5)
				WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_OK'))

			}

		}
		else{

			KeywordUtil.markPassed("Workspace Save info is blank")
		}
	}//saveWS

	@Keyword
	//Save workspace and check if name already exists proceed
	def SaveWorkspace(def wsName){
		if(wsName != '')
		{
			//WebUI.click(findTestObject('Object Repository/Final objects/MySelection/MainButns/span_Welcome kat4cid008.com_ca'))
			//Click on user dropdown menu
			clickUserLoginDropDown()

			WebUI.click(findTestObject('Object Repository/Final objects/MySelection/MainButns/a_Save Workspace'))
			longDelay()
			WebUI.setText(findTestObject('Final Objects/MySelection/MainButns/input_Name your workspace_wrkS'), wsName)
			WebUI.click(findTestObject('Final Objects/MySelection/MainButns/Workspace_button_save'))
			longDelay()
			if(WebUI.verifyElementPresent(findTestObject('Final Objects/MySelection/MainButns/button_OK'),10 ,FailureHandling.OPTIONAL)){
				WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_OK'))
			}
			else
			{
				WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_Yes'))
				shortDelay()
				WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_OK'))
			}
		}else
		{
			KeywordUtil.logInfo("Save workspace info is blank")
		}

	}
	@Keyword
	//LoadWorkspace
	def loadWorkspace(String workspaceName, String nSep ){
		if(workspaceName != ''){
			def mulWSNames = workspaceName.split(nSep)
			int lenWS = mulWSNames.size()
			// KeywordUtil.logInfo(lenWS)
			for (def index : (0..lenWS-1))
			{
				//TestObject wsName = makeTestObject('h5', mulWSNames[index], 'workspaceTitleHead', '', '', '', '')
				TestObject wsName = makeTestObject('h5', '', 'workspaceTitleHead', '', 'data-workspace', mulWSNames[index], '')
				shortDelay()
				//Click on sideMenu bar
				sideMenuBar()
				//Click on Workspace Tab
				SwitchtoWorkspace()
				shortDelay()
				WebUI.click(wsName)
				longDelay()

				/*check workspace in shared workspace tab
				 shortDelay()
				 if(WebUI.verifyElementPresent(wsName, 10, FailureHandling.STOP_ON_FAILURE)==true)
				 {
				 //KeywordUtil.logInfo(wsName)
				 WebUI.click(wsName)
				 WebUI.delay(20)
				 }else{
				 sharedWSTab()
				 shortDelay()
				 WebUI.click(wsName)
				 WebUI.delay(20)
				 }
				 */
			}

		}else
		{
			KeywordUtil.logInfo("Workspace Name is not avaialble")
		}



	}//loadWorkspace

	/* ********************************************    Tabular Functions ********************************************* */

	@Keyword
	def warningMsgChk()
	{
		//Validation for warning message if it presents
		TestObject fltORgrpErr = makeTestObject('div', '', 'sa-confirm-button-container', '', '','' ,'')
		if(WebUI.verifyElementPresent(fltORgrpErr,10,FailureHandling.OPTIONAL)==true)
		{
			WebUI.click(fltORgrpErr)
			shortDelay()
		}
		else
		{
			KeywordUtil.logInfo("No warnig messages displayed")
		}
	}

	@Keyword
	def switchToTabular()
	{

		//Click on Tabular Tab and make object for the same
		WebDriver driver = DriverFactory.getWebDriver()
		TestObject switchToTab = makeTestObject('a', '', 'tab-cls-anch', '', 'aria-controls','pivotTab' ,'')
		WebElement element = WebUiCommonHelper.findWebElement(switchToTab,10)
		JavascriptExecutor executor = ((driver) as JavascriptExecutor)
		executor.executeScript('arguments[0].click()', element)
		//Validation for warning message if it presents
		TestObject fltORgrpErr = makeTestObject('div', '', 'sa-confirm-button-container', '', '','' ,'')
		if(WebUI.verifyElementPresent(fltORgrpErr,10,FailureHandling.OPTIONAL)==true)
		{
			WebUI.click(fltORgrpErr)
			shortDelay()
		}
		else
		{
			KeywordUtil.logInfo("No warnig messages displayed")
		}
		longDelay()


	}//switchToTabular()

	@Keyword
	def switchToLayoutFromTabular()
	{
		//Switch to Layout from Tabular

		WebDriver driver = DriverFactory.getWebDriver()
		TestObject switchToLayout = makeTestObject('i', '', 'layout-icon-lg-grey', '', '','' ,'')
		WebElement element = WebUiCommonHelper.findWebElement(switchToLayout,10)
		JavascriptExecutor executor = ((driver) as JavascriptExecutor)
		executor.executeScript('arguments[0].click()', element)
		if(WebUI.verifyTextPresent("Warning!", false,FailureHandling.OPTIONAL))
		{
			shortDelay()
			KeywordUtil.logInfo("click ok in warning message then switch to Layout")
			warningForHigherCols()
		}
		else
		{
			KeywordUtil.logInfo("Switching to Layout without any warning message")
			WebUI.delay(10)
		}



	}//switchToLayoutFromTabular()


	@Keyword
	def downloadExportFileTR15(String exportFileName)
	{
		if(exportFileName!='')
		{
			//Click on notification icon and export file

			//Make object for notification and click
			TestObject notificationBtn = makeTestObject('', '', 'alertsvgicon', '', '','' ,'')
			longDelay()
			WebUI.click(notificationBtn)

			//Verify file availability then export
			TestObject tabExport = makeTestObject('', '', 'exportdownload', '', 'data-filename*',exportFileName ,'')
			if(WebUI.verifyElementClickable(tabExport))
			{
				WebUI.click(tabExport)
				WebUI.delay(10)
			}else
			{
				KeywordUtil.logInfo("Could not find exported file")

			}
		}
		else
		{
			KeywordUtil.logInfo("Export filename is blank")
		}
	}//downloadExportFileTR15()

	@Keyword
	def exportTabularFromDashboard(String exportFileName)
	{
		if(exportFileName != '')
		{
			KeywordUtil.logInfo(exportFileName)
			shortDelay()
			//Click on side menu and navigate to dashboard
			sideMenuBar()
			//Make object for dashboard and click
			TestObject dashboard = makeTestObject('i', '', 'dashboard_icon', '', '','' ,'')
			WebUI.click(dashboard)
			shortDelay()
			//alerts handling
			alertHandling()
			shortDelay()
			//Click on export status report expansion
			TestObject expandExportStatus = makeTestObject('i', '', '', 'maximize-export-report', '','' ,'')
			WebUI.click(expandExportStatus)
			shortDelay()
			//verify file name present then click on output link
			TestObject getOutput = makeTestObject('', '', '', '', '','' ,'//td/span[contains(@data-href,"'+exportFileName+'")]')
			if(WebUI.verifyElementPresent(getOutput,10,FailureHandling.OPTIONAL))
			{
				shortDelay()
				WebUI.click(getOutput)
				shortDelay()
				KeywordUtil.logInfo("Found output link")
			}
			else
			{
				KeywordUtil.logInfo("Not Found output link")
				WebUI.delay(15)
				WebUI.click(getOutput)

			}



		}else
		{
			KeywordUtil.logInfo("Exportfile name  info is blank")
		}

	}//exportTabularFromDashboard


	@Keyword
	def compareTabularR15(String exportFileName, String expectedPath,String actualPath)
	{

		if(expectedPath != '' & actualPath != '')
		{
			downloadExportFileTR15(exportFileName)
			//to get the workbook of expected tabular sheet
			def workbookExpected = ExcelKeywords.getWorkbook(expectedPath)
			//to get the workbook of actual tabular sheet
			//String Actual = 'C:/Users/DELL/Katalon Studio/Kanvas-R12/KanvasDownloads/T1.xlsx'
			def workbookActual = ExcelKeywords.getWorkbook(actualPath)
			WebUI.delay(10)
			String status = ExcelKeywords.compareTwoExcels(workbookExpected, workbookActual)
			if(status)
			{
				KeywordUtil.markPassed("Both Tabulars matched")
			}
			else
			{
				KeywordUtil.markFailed("Expected tabular is not matched with Actual")
			}
		}else
		{
			KeywordUtil.logInfo("Expected and actual paths are blank")
		}
	}//compareTabularR15()


	@Keyword
	def compareTabularR14( String expectedPath,String actualPath)
	{

		if(expectedPath != '' & actualPath != '')
		{

			//to get the workbook of expected tabular sheet
			def workbookExpected = ExcelKeywords.getWorkbook(expectedPath)
			//to get the workbook of actual tabular sheet
			//String Actual = 'C:/Users/DELL/Katalon Studio/Kanvas-R12/KanvasDownloads/T1.xlsx'
			def workbookActual = ExcelKeywords.getWorkbook(actualPath)
			WebUI.delay(10)
			String status = ExcelKeywords.compareTwoExcels(workbookExpected, workbookActual)
			if(status)
			{
				KeywordUtil.markPassed("Both Tabulars matched")
			}
			else
			{
				KeywordUtil.markFailed("Expected tabular is not matched with Actual")
			}
		}else
		{
			KeywordUtil.logInfo("Expected and actual paths are blank")
		}
	}//compareTabularR14()

	@Keyword
	def exportAllTabularR14(String exportFileName)
	{

		if(exportFileName != '')
		{

			//Export all Tabulars
			WebDriver driver = DriverFactory.getWebDriver()
			TestObject exportAllTabular = makeTestObject('i', '', 'exportPivotIcon', '', '','' ,'')
			WebUI.disableSmartWait()
			WebUI.enableSmartWait()
			WebElement element = WebUiCommonHelper.findWebElement(exportAllTabular,10)
			JavascriptExecutor executor = ((driver) as JavascriptExecutor)
			//WebUI.waitForElementClickable(exportAllTabular,60)
			executor.executeScript('arguments[0].click()', element)
			shortDelay()

			//Set ExportFileName
			TestObject setExportTFileName = makeTestObject('input', '', 'form-control', 'exportfilenm', '','' ,'')
			WebUI.sendKeys(setExportTFileName, Keys.chord(Keys.CONTROL, 'a'))
			WebUI.sendKeys(setExportTFileName, Keys.chord(Keys.BACK_SPACE))
			WebUI.sendKeys(setExportTFileName, exportFileName)

			shortDelay()

			//Click on Export button
			TestObject exportBtn = makeTestObject('button', '', 'blueBtn', 'exportBtn', '','' ,'')
			WebUI.click(exportBtn)

			if(WebUI.verifyTextPresent("Please Wait",false,FailureHandling.OPTIONAL))
			{
				KeywordUtil.logInfo("Continue Enable wait.............IF block")
				longDelay()

			}
			else
			{

				WebUI.disableSmartWait()
				KeywordUtil.logInfo("Tabular exported")
			}
			//longDelay()
		}else
		{
			KeywordUtil.logInfo("Exportfilename is blank")
		}

	}//exportAllTabularR14()



	@Keyword
	def exportAllTabularR15(String exportFileName)
	{
		if(exportFileName != '')
		{

			//Export all Tabulars
			WebDriver driver = DriverFactory.getWebDriver()
			TestObject exportAllTabular = makeTestObject('i', '', 'exportPivotIcon', '', '','' ,'')
			WebUI.disableSmartWait()
			WebUI.enableSmartWait()
			WebElement element = WebUiCommonHelper.findWebElement(exportAllTabular,10)
			JavascriptExecutor executor = ((driver) as JavascriptExecutor)
			//WebUI.waitForElementClickable(exportAllTabular,60)
			executor.executeScript('arguments[0].click()', element)
			shortDelay()

			//Set ExportFileName
			TestObject setExportTFileName = makeTestObject('input', '', 'form-control', 'myselexportnm', '','' ,'')
			WebUI.sendKeys(setExportTFileName, Keys.chord(Keys.CONTROL, 'a'))
			WebUI.sendKeys(setExportTFileName, Keys.chord(Keys.BACK_SPACE))
			WebUI.sendKeys(setExportTFileName, exportFileName)

			shortDelay()

			//Click on Export button
			TestObject exportBtn = makeTestObject('button', '', 'blueBtn', 'exportmyselbtn', '','' ,'')
			WebUI.click(exportBtn)

			if(WebUI.verifyTextPresent("Please Wait",false,FailureHandling.OPTIONAL))
			{
				KeywordUtil.logInfo("Continue Enable wait.............IF block")
				longDelay()
				TestObject exportSuccBtn = makeTestObject('button', '', 'confirm', '', '','' ,'')
				WebUI.click(exportSuccBtn)
				KeywordUtil.logInfo("Exported file added to queue")
			}
			else
			{
				TestObject exportSuccBtn = makeTestObject('button', '', 'confirm', '', '','' ,'')
				WebUI.click(exportSuccBtn)
				WebUI.disableSmartWait()
				KeywordUtil.logInfo("Exported file added to queue")
			}
			//longDelay()
		}else
		{
			KeywordUtil.logInfo("Exportfilename is blank")
		}

	}//exportAllTabularR15()


	@Keyword
	def switchToStandard()
	{
		//Click on Standard Tab and make object for the same
		TestObject switchToStandard = makeTestObject('a', '', 'tab-cls-anch', '', 'aria-controls','itemsGridId' ,'')
		WebUI.click(switchToStandard)
		shortDelay()
	}//switchToStandard()

	@Keyword
	//Created on 17-Apr
	def switchToLayoutFromStd()
	{
		//Click on Standard Tab and make object for the same
		TestObject switchTolayout = makeTestObject('li', '', '', 'goLayout', 'data-url','/layout/layout' ,'')
		WebUI.click(switchTolayout)
		shortDelay()
	}//switchToLayoutFromStd()

	@Keyword
	def openTabularConfig()
	{
		TestObject openSlider = makeTestObject('', '', '', '', '','' ,'id("myselection-content")/div[@class="displayTabMenu col-md-12 col-lg-12"]/div[@class="pivot-config"]/div[@class="pivot-config-box show"]/div[@class="pivot-config-icon"]/i[@class="setting-icon-lg-white"]')
		if(WebUI.verifyElementPresent(openSlider,10,FailureHandling.OPTIONAL)==true)
		{
			//KeywordUtil.logInfo("Tabular Slider is opened")
			//shortDelay()
			KeywordUtil.markPassed("Tabular Slider is open state")

		}else
		{
			//Open Table Configuration
			TestObject closeSlider = makeTestObject('', '', '', '', '','' ,'id("myselection-content")/div[@class="displayTabMenu col-md-12 col-lg-12"]/div[@class="pivot-config zerozindex"]/div[@class="pivot-config-box"]/div[@class="pivot-config-icon"]/i[@class="setting-icon-lg-white"]')
			WebUI.click(closeSlider)
			shortDelay()
			KeywordUtil.markPassed("Tabular Slider was closed...clicked on icon inorder to open tabular config")
		}
	}//openTabularConfig()

	@Keyword
	//Modified code on 9-April
	//Modified input and label accessor for row subtotals
	def rowSubTotalEnable(String rowSubTotal,String rFieldnm)
	{
		//R14 accessor for subtotal label
		//String chkLabel = '//input[@id="chkSubDisplayRow"]/following-sibling::label'
		//R15 accessor for Subtotal label
		String chkLabel = '//input[@class="checkbox rsubtotal" and @data-rfieldrnm="'+rFieldnm+'" ]/following-sibling::label'
		//R14 accessor for subtotal Input
		//String chkInput = '//input[@id="chkSubDisplayRow"]'
		//R15 accessor for subtotal Input
		String chkInput = '//input[@class="checkbox rsubtotal" and @data-rfieldrnm="'+rFieldnm+'" ]'
		//Make Object and verify Input element of Row fields sub total whether it's checked or no
		TestObject chkI = makeTestObject('', '', '', '', '', '',chkInput)
		shortDelay()
		if((WebUI.verifyElementChecked(chkI,10,FailureHandling.OPTIONAL))== true){
			KeywordUtil.logInfo("Row Fields SubTotal is checked")
		}else
		{
			TestObject chkLable = makeTestObject('', '', '', '', '', '', chkLabel)
			WebUI.click(chkLable)
			shortDelay()
		}
	}//rowSubTotalEnable()

	@Keyword
	def rowSubTotalDisable(String rowSubTotal,String rFieldnm)
	{
		//R14 accessor for subtotal label
		//String chkLabel = '//input[@id="chkSubDisplayRow"]/following-sibling::label'
		//R15 accessor for Subtotal label
		String chkLabel = '//input[@class="checkbox rsubtotal" and @data-rfieldrnm="'+rFieldnm+'" ]/following-sibling::label'
		//R14 accessor for subtotal Input
		//String chkInput = '//input[@id="chkSubDisplayRow"]'
		//R15 accessor for subtotal Input
		String chkInput = '//input[@class="checkbox rsubtotal" and @data-rfieldrnm="'+rFieldnm+'" ]'		//Make Object and verify Input element of Row fields sub total whether it's checked or no
		TestObject chkI = makeTestObject('', '', '', '', '', '',chkInput)
		shortDelay()
		if((WebUI.verifyElementChecked(chkI,10,FailureHandling.OPTIONAL))== true){
			TestObject chkLable = makeTestObject('', '', '', '', '', '', chkLabel)
			//WebUI.click(chkLable)
			clickUsingJS(chkLable,10)
			shortDelay()

		}else
		{
			KeywordUtil.logInfo("Row Fields SubTotal is unchecked")
		}
	}//rowSubTotalDisable()

	@Keyword
	def rowSubTotalStatus15(String rowSubTotal, String rFieldnm)
	{
		if(rowSubTotal == 'Enable' & rFieldnm != '' )
		{
			rowSubTotalEnable(rowSubTotal,rFieldnm)
		}
		else if(rowSubTotal == 'Disable' & rFieldnm != '')
		{
			rowSubTotalDisable(rowSubTotal,rFieldnm)
		}

		else
		{
			KeywordUtil.logInfo("rowSubTotal is blank")
		}

	}//rowSubTotalStatus15()

	@Keyword
	def rowSubTotalStatus(String rowSubTotal)
	{
		if(rowSubTotal != '' & rowSubTotal == 'Enable')
		{
			rowSubTotalEnable()
		}
		else if(rowSubTotal != '' & rowSubTotal == 'Disable')
		{
			rowSubTotalDisable()
		}
		else
		{
			KeywordUtil.logInfo("rowSubTotal is blank")
		}

	}//rowSubTotalStatus()

	@Keyword
	def previewTabularEnable()
	{

		String chkLabel = '//input[@id="chkpreviewtop"]/following-sibling::label'
		String chkInput = '//input[@id="chkpreviewtop"]'
		//Make Object and verify Input element of Preview whether it's checked or no
		TestObject chkI = makeTestObject('', '', '', '', '', '',chkInput)
		shortDelay()
		if((WebUI.verifyElementChecked(chkI,10,FailureHandling.OPTIONAL))== true){
			KeywordUtil.logInfo("Preview Tabular is checked")
		}else
		{
			TestObject chkLable = makeTestObject('', '', '', '', '', '', chkLabel)
			WebUI.click(chkLable)
			shortDelay()
		}


	}//previewTabularEnable()

	@Keyword
	def previewTabularDisable()
	{

		String chkLabel = '//input[@id="chkpreviewtop"]/following-sibling::label'
		String chkInput = '//input[@id="chkpreviewtop"]'
		//Make Object and verify Input element of Preview whether it's checked or no
		TestObject chkI = makeTestObject('', '', '', '', '', '',chkInput)
		shortDelay()
		if((WebUI.verifyElementChecked(chkI,10,FailureHandling.OPTIONAL))== true){
			TestObject chkLable = makeTestObject('', '', '', '', '', '', chkLabel)
			//WebUI.click(chkLable)
			clickUsingJS(chkLable, 10)
			shortDelay()

		}else
		{
			KeywordUtil.logInfo("Preview Tabular is unchecked")
		}

	}//previewTabularDisable()

	@Keyword
	def previewStatus(String preview)
	{
		if(preview != '' & preview == 'Enable')
		{
			previewTabularEnable()
		}
		else if(preview != '' & preview == 'Disable')
		{
			previewTabularDisable()
		}
		else
		{
			KeywordUtil.logInfo("preview is blank")
		}

	}//previewStatus

	@Keyword
	def imageTabularEnable(String ImageTabularEnable)
	{
		if(ImageTabularEnable != '')
		{

			String chkLabel = '//input[@id="chkImgDisplayRow"]/following-sibling::label'
			String chkInput = '//input[@id="chkImgDisplayRow"]'
			//Make Object and verify Input element of Image whether it's checked or no
			TestObject chkI = makeTestObject('', '', '', '', '', '',chkInput)
			shortDelay()
			if((WebUI.verifyElementChecked(chkI,10,FailureHandling.OPTIONAL))== true){
				KeywordUtil.logInfo("Image Tabular is checked")
			}else
			{
				TestObject chkLable = makeTestObject('', '', '', '', '', '', chkLabel)
				WebUI.click(chkLable)
				shortDelay()
			}
		}else
		{
			KeywordUtil.logInfo("Image Tabular Enable information is blank")
		}


	}//imageTabularEnable()

	@Keyword
	def imageTabularDisable()
	{

		String chkLabel = '//input[@id="chkImgDisplayRow"]/following-sibling::label'
		String chkInput = '//input[@id="chkImgDisplayRow"]'
		//Make Object and verify Input element of Image whether it's checked or no
		TestObject chkI = makeTestObject('', '', '', '', '', '',chkInput)
		shortDelay()
		if((WebUI.verifyElementChecked(chkI,10,FailureHandling.OPTIONAL))== true){
			TestObject chkLable = makeTestObject('', '', '', '', '', '', chkLabel)
			//WebUI.click(chkLable)
			clickUsingJS(chkLable, 10)
			shortDelay()

		}else
		{
			KeywordUtil.logInfo("Image Tabular is unchecked")
		}

	}//imageTabularDisable()

	@Keyword
	def imageTStatus(String imgInTbl)
	{
		if(imgInTbl != '' & imgInTbl == 'Enable')
		{
			imageTabularEnable()
		}
		else if(imgInTbl != '' & imgInTbl == 'Disable')
		{
			imageTabularDisable()
		}
		else
		{
			KeywordUtil.logInfo("Image Status is blank")
		}

	}//imageTStatus

	@Keyword
	def colSubTotalEnable()
	{
		String chkLabel = '//input[@id="chkSubDisplayCol"]/following-sibling::label'
		String chkInput = '//input[@id="chkSubDisplayCol"]'
		//Make Object and verify Input element of Sub Total of Columns whether it's checked or no
		TestObject chkI = makeTestObject('', '', '', '', '', '',chkInput)
		shortDelay()
		if((WebUI.verifyElementChecked(chkI,10,FailureHandling.OPTIONAL))== true){
			KeywordUtil.logInfo("Column Subtotal is checked")
		}else
		{
			TestObject chkLable = makeTestObject('', '', '', '', '', '', chkLabel)
			WebUI.click(chkLable)
			shortDelay()
		}
	}//colSubTotalEnable()

	@Keyword
	def colSubTotalDisable()
	{
		String chkLabel = '//input[@id="chkSubDisplayCol"]/following-sibling::label'
		String chkInput = '//input[@id="chkSubDisplayCol"]'
		//Make Object and verify Input element of Sub Total of Columns whether it's checked or no
		TestObject chkI = makeTestObject('', '', '', '', '', '',chkInput)
		shortDelay()
		if((WebUI.verifyElementChecked(chkI,10,FailureHandling.OPTIONAL))== true){
			TestObject chkLable = makeTestObject('', '', '', '', '', '', chkLabel)
			//WebUI.click(chkLable)
			clickUsingJS(chkLable,10)
			shortDelay()

		}else
		{
			KeywordUtil.logInfo("Column Subtotal is unchecked")
		}
	}//colSubTotalDisable()

	@Keyword
	def colSubTotalStatus(String colSubTotal)
	{
		if(colSubTotal != '' & colSubTotal == 'Enable')
		{
			colSubTotalEnable()
		}
		else if(colSubTotal != '' & colSubTotal == 'Disable')
		{
			colSubTotalDisable()
		}
		else
		{
			KeywordUtil.logInfo("colSubTotal is blank")
		}

	}//colSubTotalStatus

	@Keyword
	//Modified this function with the multiple values reading on 20-Apr
	//Also merged Enable and Disable functionality and created as a single function

	def colSubTotal15(String cFieldnm,String colSubTotal,String vSep)
	{
		if(colSubTotal != '' & cFieldnm != '')
		{
			def mulAttrColST = cFieldnm.split(vSep)
			def mulAttrColSTstatus = cFieldnm.split(vSep)
			int mulAttren = mulAttrColST.size()
			KeywordLogger log = new KeywordLogger()
			def done = false
			for(def index :(0..mulAttren-1))
			{
				String cAttrnm = mulAttrColST[index].trim()
				String cAttrstatus = mulAttrColSTstatus[index].trim()
				KeywordUtil.logInfo('ColumnName'+cAttrnm+'SubTotalSTtaus'+cAttrstatus)
				//R15 accessor for col subtotal label
				String chkLabel = '//input[@class="checkbox csubtotal" and @data-cfieldrnm = "'+cAttrnm+'" ]/following-sibling::label'
				//R15 accessor for col subtotal input
				String chkInput = '//input[@class="checkbox csubtotal" and @data-cfieldrnm = "'+cAttrnm+'" ]'
				//Make Object and verify Input element of Sub Total of Columns whether it's checked or no
				TestObject chkI = makeTestObject('', '', '', '', '', '',chkInput)
				shortDelay()
				if(cAttrstatus=="Enable")
				{

					if((WebUI.verifyElementChecked(chkI,10,FailureHandling.OPTIONAL))== true){
						KeywordUtil.logInfo("Column Subtotal is already checked")
					}else
					{
						TestObject chkLable = makeTestObject('', '', '', '', '', '', chkLabel)
						WebUI.click(chkLable)
						KeywordUtil.logInfo("Column Subtotal Checked")
						shortDelay()
					}
				}//if(cAttrstatus=="Enable")
				else if(cAttrstatus=="Disable")
				{
					if((WebUI.verifyElementChecked(chkI,10,FailureHandling.OPTIONAL))== true){
						KeywordUtil.logInfo("Column Subtotal is Enabled....Click on checkbox to disable")
						TestObject chkLable = makeTestObject('', '', '', '', '', '', chkLabel)
						//WebUI.click(chkLable)
						clickUsingJS(chkLable,10)
						shortDelay()

					}else
					{
						KeywordUtil.logInfo("Column Subtotal is is already disabled")
					}

				}// else if(cAttrstatus=="Disable")
				else
				{
					KeywordUtil.logInfo("Column Subtotalinput details are Invalid")
				}
			}
		}
		else
		{
			KeywordUtil.logInfo("Column Subtotal Details are blank")
		}
	}//colSubTotal15()

	@Keyword
	//Modified this function with the multiple values reading on 20-Apr
	//Also merged Enable and Disable functionality and created as a single function

	def rowSubTotal15(String rFieldnm,String rowSubTotal,String vSep)
	{
		if(rowSubTotal != '' & rFieldnm != '')
		{
			def mulAttrRowST = rFieldnm.split(vSep)
			def mulAttrRowSTstatus = rFieldnm.split(vSep)
			int mulAttren = mulAttrRowST.size()
			KeywordLogger log = new KeywordLogger()
			def done = false
			for(def index :(0..mulAttren-1))
			{
				String rAttrnm = mulAttrRowST[index].trim()
				String rAttrstatus = mulAttrRowSTstatus[index].trim()
				KeywordUtil.logInfo('ColumnName'+rAttrnm+'SubTotalSTtaus'+rAttrstatus)
				//R15 accessor for Subtotal label
				String chkLabel = '//input[@class="checkbox rsubtotal" and @data-rfieldrnm="'+rAttrnm+'" ]/following-sibling::label'
				//R15 accessor for subtotal Input
				String chkInput = '//input[@class="checkbox rsubtotal" and @data-rfieldrnm="'+rAttrnm+'" ]'
				//Make Object and verify Input element of Row fields sub total whether it's checked or no
				TestObject chkI = makeTestObject('', '', '', '', '', '',chkInput)
				shortDelay()
				if(rAttrstatus=="Enable")
				{

					if((WebUI.verifyElementChecked(chkI,10,FailureHandling.OPTIONAL))== true){
						KeywordUtil.logInfo("Row Subtotal is already checked")
					}else
					{
						TestObject chkLable = makeTestObject('', '', '', '', '', '', chkLabel)
						WebUI.click(chkLable)
						KeywordUtil.logInfo("Row Subtotal Checked")
						shortDelay()
					}
				}//if(rAttrstatus=="Enable")
				else if(rAttrstatus=="Disable")
				{
					if((WebUI.verifyElementChecked(chkI,10,FailureHandling.OPTIONAL))== true){
						KeywordUtil.logInfo("Row Subtotal is Enabled....Click on checkbox to disable")
						TestObject chkLable = makeTestObject('', '', '', '', '', '', chkLabel)
						//WebUI.click(chkLable)
						clickUsingJS(chkLable,10)
						shortDelay()

					}else
					{
						KeywordUtil.logInfo("Row Subtotal is already disabled")
					}

				}// else if(rAttrstatus=="Disable")
				else
				{
					KeywordUtil.logInfo("Row Subtotalinput details are Invalid")
				}
			}
		}
		else
		{
			KeywordUtil.logInfo("Row Subtotal Details are blank")
		}
	}//rowSubTotal15()


	@Keyword
	def colSubTotalDisable15(String colSubTotal,String cFieldnm)
	{
		//R15 accessor for col subtotal label
		String chkLabel = '//input[@class="checkbox csubtotal" and @data-cfieldrnm = "'+cFieldnm+'" ]/following-sibling::label'
		//R15 accessor for col subtotal input
		String chkInput = '//input[@class="checkbox csubtotal" and @data-cfieldrnm = "'+cFieldnm+'" ]'
		//Make Object and verify Input element of Sub Total of Columns whether it's checked or no
		TestObject chkI = makeTestObject('', '', '', '', '', '',chkInput)
		shortDelay()
		if((WebUI.verifyElementChecked(chkI,10,FailureHandling.OPTIONAL))== true){
			TestObject chkLable = makeTestObject('', '', '', '', '', '', chkLabel)
			//WebUI.click(chkLable)
			clickUsingJS(chkLable,10)
			shortDelay()

		}else
		{
			KeywordUtil.logInfo("Column Subtotal is unchecked")
		}
	}//colSubTotalDisable15()

	@Keyword
	def colSubTotalStatus15(String colSubTotal, String cFieldnm)
	{

		if(colSubTotal == 'Enable' & cFieldnm != '')
		{
			colSubTotalEnable15(colSubTotal,cFieldnm)
		}
		else if(colSubTotal == 'Disable' & cFieldnm != '')
		{
			colSubTotalDisable15(colSubTotal,cFieldnm)
		}
		else
		{
			KeywordUtil.logInfo("colSubTotal is blank")
		}

	}//colSubTotalStatus15


	@Keyword
	def grandTotalEnable()
	{

		String chkLabel = '//input[@id="chkTotDisplay"]/following-sibling::label'
		String chkInput = '//input[@id="chkTotDisplay"]'
		//Make Object and verify Input element of GrandTotal whether it's checked or no
		TestObject chkI = makeTestObject('', '', '', '', '', '',chkInput)
		shortDelay()
		if((WebUI.verifyElementChecked(chkI,10,FailureHandling.OPTIONAL))== true){
			KeywordUtil.logInfo("Grand Total is checked")
		}else
		{
			TestObject chkLable = makeTestObject('', '', '', '', '', '', chkLabel)
			WebUI.click(chkLable)
			shortDelay()
		}
	}//grandTotalEnable()

	@Keyword
	def grandTotalDisable()
	{
		String chkLabel = '//input[@id="chkTotDisplay"]/following-sibling::label'
		String chkInput = '//input[@id="chkTotDisplay"]'
		//Make Object and verify Input element of GrandTotal whether it's checked or no
		TestObject chkI = makeTestObject('', '', '', '', '', '',chkInput)
		shortDelay()
		if((WebUI.verifyElementChecked(chkI,10,FailureHandling.OPTIONAL))== true){
			TestObject chkLable = makeTestObject('', '', '', '', '', '', chkLabel)
			//WebUI.click(chkLable)
			clickUsingJS(chkLable, 10)
			shortDelay()

		}else
		{
			KeywordUtil.logInfo("Grand Total is unchecked")
		}
	}//grandTotalDisable()

	@Keyword
	def grandTotalStatus(String grandTotal)
	{

		if(grandTotal != '' & grandTotal == 'Enable')
		{
			grandTotalEnable()
		}
		else if(grandTotal != '' & grandTotal == 'Disable')
		{
			grandTotalDisable()
		}
		else
		{
			KeywordUtil.logInfo("grandTotal is blank")
		}

	}//grandTotalStatus

	@Keyword
	def itemcountEnable()
	{
		String chkLabel = '//input[@id="itemcount"]/following-sibling::label'
		String chkInput = '//input[@id="itemcount"]'
		//Make Object and verify Input element of GrandTotal whether it's checked or no
		TestObject chkI = makeTestObject('', '', '', '', '', '',chkInput)
		shortDelay()
		if((WebUI.verifyElementChecked(chkI,10,FailureHandling.OPTIONAL))== true){
			KeywordUtil.logInfo("Itemcount is checked")
		}else
		{
			TestObject chkLable = makeTestObject('', '', '', '', '', '', chkLabel)
			WebUI.click(chkLable)
			shortDelay()
		}
	}//itemcountEnable()

	@Keyword
	def itemcountDisable()
	{
		String chkLabel = '//input[@id="itemcount"]/following-sibling::label'
		String chkInput = '//input[@id="itemcount"]'
		//Make Object and verify Input element of GrandTotal whether it's checked or no
		TestObject chkI = makeTestObject('', '', '', '', '', '',chkInput)
		shortDelay()
		if((WebUI.verifyElementChecked(chkI,10,FailureHandling.OPTIONAL))== true){

			TestObject chkLable = makeTestObject('', '', '', '', '', '', chkLabel)
			//WebUI.click(chkLable)
			clickUsingJS(chkLable,10)
			shortDelay()

		}else
		{
			KeywordUtil.logInfo("ItemCount  is unchecked")
		}
	}//itemcountDisable()

	@Keyword
	def itemCountStatus(String itemCount)
	{
		if(itemCount != '' & itemCount == 'Enable')
		{
			itemcountEnable()
		}
		else if(itemCount != '' & itemCount == 'Disable')
		{
			itemcountDisable()
		}
		else
		{
			KeywordUtil.logInfo("itemCount is blank")
		}

	}//itemCountStatus

	@Keyword
	def setTableName(String tableName)
	{
		if(tableName != '')
		{
			TestObject setTableName = makeTestObject('input', '', 'form-control', 'pvt-config-tableTitle', '','' ,'')
			WebUI.click(setTableName)
			WebUI.setText(setTableName,tableName)
			WebUI.sendKeys(setTableName,Keys.chord(Keys.ENTER))
		}else
		{
			KeywordUtil.logInfo("tablename  is blank")
		}

	}//setTableName




	@Keyword
	def chkAttributesT(String attrNames, String nSep, String to)
	{
		WebDriver driver = DriverFactory.getWebDriver()
		//Reading multiple rowfiled values from data sheet when it's not null
		//Split with nSep(;)
		//Create object for check box to select attribute
		//Check for element clickable then click on checkbox else print msg in log
		def mulAttrNs = attrNames.split(nSep)
		int lenMulAns = mulAttrNs.size()
		for (def index : (0..lenMulAns-1))
		{
			String attrTo = mulAttrNs[index].trim()
			TestObject attrName = makeTestObject('', '', '', '', '','' ,'//li[@data-attributenm="'+attrTo+'"]//label[@class="lbl_cls chkSelAttr_lbl"]')
			//if(WebUI.verifyElementClickable(attrName)==true)
			//{

			WebElement element = WebUiCommonHelper.findWebElement(attrName,10)
			JavascriptExecutor executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click()', element)
			//WebUI.click(attrName)
			//longDelay()
			KeywordUtil.logInfo("Attribute Clicked")
			shortDelay()

			//}
			//else
			//{
			//	KeywordUtil.logInfo("Not clickable")
			//}
		}//for (def index : (0..lenMulAns-1))
		//return first attribute name inorder to drag all checked items
		to = mulAttrNs[0].trim()
		return to

	}//chkAttributesT()

	@Keyword
	def chkMeasuresT(String msrNames, String nSep, String to)
	{
		if(msrNames != '')
		{
			//Reading multiple Measure Names from data sheet when it's not null
			//Split with nSep(;)
			//Create object for check box to select measure
			//Check for element clickable then click on checkbox else print msg in log
			def mulMsrNs = msrNames.split(nSep)
			int lenMulMns = mulMsrNs.size()
			for (def index : (0..lenMulMns-1))
			{
				String msrTo = mulMsrNs[index].trim()
				TestObject msrName = makeTestObject('', '', '', '', '','' ,'//li[@data-measurenm="'+msrTo+'" ]//label[@class="lbl_cls chkSelAttr_lbl"]')
				if(WebUI.verifyElementClickable(msrName)==true)
				{
					WebUI.click(msrName)
					longDelay()
					KeywordUtil.logInfo("MeasureName Clicked")
					shortDelay()

				}
				else
				{
					KeywordUtil.logInfo("Not clickable")
				}
			}//for (def index : (0..lenMulAns-1))
			//return first measure name in order to drag all checked items
			to = mulMsrNs[0].trim()
			return to
		}else
		{
			KeywordUtil.logInfo("Measure Name is not available")
		}
	}//chkMeasuresT()

	@Keyword
	def rowFieldsDD(String rowFields, String nSep , String to)
	{
		//Check for rowfields not equal to null
		//Call returned value from 'chkAttributes' function in make object of listofAttrs
		//Make object for rowfield area.verifyelementpresent ,if yes then drop listofAttrs in to rowfileds area


		String toval = chkAttributesT(rowFields,  nSep, to)
		KeywordUtil.logInfo('Returned attribute name in rowfiledsDD to value'+toval)
		//select list of selected attributes
		TestObject listOfAttrs = makeTestObject('li', '', 'ui-state-default ui-draggable ui-draggable-handle multdrag', '', 'data-attributenm',toval ,'')
		shortDelay()
		//Rowfields Area
		TestObject rowFieldsArea = makeTestObject('ul', '', 'pvtRows pvtAxisContainer ui-sortable ui-droppable', 'pvt-config-rowfields', '','' ,'')
		shortDelay()
		if(WebUI.verifyElementPresent(rowFieldsArea,10)==true)
		{
			WebUI.dragAndDropToObject(listOfAttrs, rowFieldsArea, FailureHandling.STOP_ON_FAILURE)
			KeywordUtil.logInfo("Attributes dropped in row field area")
		}
		else
		{
			KeywordUtil.logInfo("rowfields is not available")
		}


	}//rowFieldsDD()




	@Keyword
	def colFieldsDD(String colFields, String nSep , String to)
	{
		//Check for colFields not equal to null
		//Call returned value from 'chkAttributes' function in make object of listofAttrs
		//Make object for colField area.verifyelementpresent ,if yes then drop listofAttrs in to colFields area
		if(colFields != '')
		{
			String toval = chkAttributesT(colFields,  nSep, to)
			KeywordUtil.logInfo('Returned attribute name in colfiledsDD to value'+toval)
			//select list of selected attributes for colfields
			TestObject listOfAttrs = makeTestObject('li', '', 'ui-state-default ui-draggable ui-draggable-handle multdrag', '', 'data-attributenm',toval ,'')
			shortDelay()
			//Colfields Area
			TestObject colFieldsArea = makeTestObject('ul', '', 'pvtColumns pvtAxisContainer ui-sortable ui-droppable', 'pvt-config-columnfields', '','' ,'')
			shortDelay()
			if(WebUI.verifyElementPresent(colFieldsArea,10)==true)
			{
				WebUI.dragAndDropToObject(listOfAttrs, colFieldsArea, FailureHandling.STOP_ON_FAILURE)
				KeywordUtil.logInfo("Attributes dropped in col field area")
			}
			else
			{
				KeywordUtil.logInfo("colfields is not available")
			}
		}
		else
		{
			KeywordUtil.logInfo("colfields column is blank")
		}

	}//colFieldsDD()

	@Keyword
	def dataFieldsDD(String dataFields, String nSep , String to)
	{
		//Check for dataFields not equal to null
		//Call returned value from 'chkMeasureT' function in make object of listofMeasures
		//Make object for dataField area.verifyelementpresent ,if yes then drop listofAttrs in to dataFields area
		if(dataFields != '')
		{
			String toval = chkMeasuresT(dataFields,  nSep, to)
			KeywordUtil.logInfo('Returned attribute name in colfiledsDD to value'+toval)
			//select list of selected measures for datafields
			TestObject listOfMsrs = makeTestObject('li', '', 'ui-state-default ui-draggable ui-draggable-handle multdrag', '', 'data-measurenm',toval ,'')
			shortDelay()
			//Datafields Area
			TestObject dataFieldsArea = makeTestObject('ul', '', 'pvtAggregations pvtAxisContainer ui-sortable ui-droppable', 'pvt-config-datafields', '','' ,'')
			shortDelay()
			if(WebUI.verifyElementPresent(dataFieldsArea,10)==true)
			{
				WebUI.dragAndDropToObject(listOfMsrs, dataFieldsArea, FailureHandling.STOP_ON_FAILURE)
				KeywordUtil.logInfo("Measures dropped in data field area")
			}
			else
			{
				KeywordUtil.logInfo("data fileds are not available")
			}
		}
		else
		{
			KeywordUtil.logInfo("datafields column is blank")
		}

	}//dataFieldsDD()

	@Keyword
	def setAggrForMsr(String msrNameT,String aggrType,String nSep)
	{
		if(msrNameT != '' & aggrType != '' )
		{
			def mulmsrNames = msrNameT.split(nSep)
			def mulaggrs = aggrType.split(nSep)
			int lenMul = mulmsrNames.size()
			for (def index : (0..lenMul-1))
			{
				String clickMsrName = mulmsrNames[index].trim()
				String clickAggrName = mulaggrs[index].trim()
				KeywordUtil.logInfo('msrname in data fields' + clickMsrName+''+clickAggrName)
				//Make object and Click on measure name to set aggregation
				TestObject clickOnMsr = makeTestObject('span', '', 'aggrAttr wide', '', 'data-attr',clickMsrName ,'')
				WebUI.click(clickOnMsr)
				longDelay()
				//Make object for list of aggregation dropdown and click
				TestObject aggrDropdown = makeTestObject('div', '', 'select2-container form-control', 's2id_aggr-func-selection-2', '','' ,'')

				//TestObject aggrDropdown = makeTestObject('', 'Average', '', '', 'href','javascript:void(0)' ,'')
				if(WebUI.verifyElementVisible(aggrDropdown,FailureHandling.OPTIONAL)==true)
				{
					KeywordUtil.logInfo("If block...........Element found")
					//WebUI.verifyElementClickable(aggrDropdown)
					WebUI.click(aggrDropdown)
					shortDelay()
					//Make object for list of search input in aggregation dropdown and set search keyword
					TestObject searchAggr = makeTestObject('input', '', 'select2-input', 's2id_autogen3_search', '','' ,'')
					WebUI.click(searchAggr)
					shortDelay()
					WebUI.setText(searchAggr,clickAggrName)
					WebUI.sendKeys(searchAggr,Keys.chord(Keys.ENTER))
					shortDelay()
					//Make object for Apply Button and then click
					TestObject applyAggr = makeTestObject('button', '', 'btn-apply-2', '', '','' ,'')
					WebUI.click(applyAggr)
					shortDelay()
					KeywordUtil.markPassed("Set aggregation for Deepdata measure")
				}else
				{
					KeywordUtil.logInfo("Else block...........Element found")
					TestObject aggrDropdownDD = makeTestObject('div', '', 'select2-container form-control', 's2id_aggr-func-selection', '','' ,'')
					WebUI.click(aggrDropdownDD)
					shortDelay()
					//Make object for list of search input in aggregation dropdown and set search keyword
					TestObject searchAggr = makeTestObject('input', '', 'select2-input', 's2id_autogen2_search', '','' ,'')
					WebUI.click(searchAggr)
					shortDelay()
					WebUI.setText(searchAggr,clickAggrName)
					WebUI.sendKeys(searchAggr,Keys.chord(Keys.ENTER))
					shortDelay()
					//Make object for Apply Button and then click
					TestObject applyAggr = makeTestObject('button', '', 'btn-apply', '', '','' ,'')
					WebUI.click(applyAggr)
					shortDelay()
					KeywordUtil.markPassed("Set aggregation for Global measure")
				}




			}
		}
		else
		{
			KeywordUtil.logInfo("Msrname and aggregation is blank")
		}




	}//setAggrForMsr()

	@Keyword
	def TabularApply()
	{

		openTabularConfig()
		TestObject applyT = makeTestObject('button', '', 'tabularBtn', 'applyTabular', '','' ,'')
		WebUI.click(applyT)
		shortDelay()
	}

	@Keyword
	def downloadExportFile(String exportFileName)
	{
		if(exportFileName!='')
		{
			//Click on notification icon and export file

			//Make object for notification and click
			TestObject notificationBtn = makeTestObject('', '', 'alertsvgicon', '', '','' ,'')
			longDelay()
			WebUI.click(notificationBtn)

			//Verify file availability then export
			TestObject tabExport = makeTestObject('', '', 'exportdownload', '', 'data-filename*',exportFileName ,'')
			if(WebUI.verifyElementClickable(tabExport))
			{
				WebUI.click(tabExport)
				WebUI.delay(10)
			}else
			{
				KeywordUtil.logInfo("Could not find exported file")

			}
		}
		else
		{
			KeywordUtil.logInfo("Export filename is blank")
		}
	}//downloadExportFile()
	@Keyword
	def compareTabular(String exportFileName, String expectedPath,String actualPath)
	{


		if(expectedPath != '' & actualPath != '')
		{
			downloadExportFile(exportFileName)
			//to get the workbook of expected tabular sheet
			def workbookExpected = ExcelKeywords.getWorkbook(expectedPath)
			//to get the workbook of actual tabular sheet
			//String Actual = 'C:/Users/DELL/Katalon Studio/Kanvas-R12/KanvasDownloads/T1.xlsx'
			def workbookActual = ExcelKeywords.getWorkbook(actualPath)
			WebUI.delay(10)
			String status = ExcelKeywords.compareTwoExcels(workbookExpected, workbookActual)
			if(status)
			{
				KeywordUtil.markPassed("Both Tabulars matched")
			}
			else
			{
				KeywordUtil.markFailed("Expected tabular is not matched with Actual")
			}
		}else
		{
			KeywordUtil.logInfo("Expected and actual paths are blank")
		}
	}//compareTabular()


	/*
	 @Keyword
	 def warningButtonT()
	 {
	 TestObject warningMsg = makeTestObject('h2', '', '', '', '','' ,'')
	 if(WebUI.verifyElementText('Warning!')
	 TestObject applyT = makeTestObject('button', '', 'tabularBtn', 'applyTabular', '','' ,'')
	 shortDelay()
	 WebUI.click(applyT)
	 }//warningButtonT
	 */
	@Keyword
	//def configTabular(String rowFields, String colFields, String dataFields, String nSep, String to,String msrNameT,String aggrType,String rowSubTotal,String preview, String colSubTotal, String grandTotal,String itemCount, String tableName,String exportFileName,String expectedPath,String actualPath )
	def configTabular(String rowFields, String colFields, String dataFields, String nSep, String to,String msrNameT,String aggrType,String rowSubTotal,String rFieldnm,String cFieldnm,String preview,String ImageTabularEnable, String colSubTotal, String grandTotal,String itemCount, String tableName)
	{
		if(rowFields != '' | colFields != '' & dataFields != '' ){
			switchToTabular()
			openTabularConfig()
		}

		if(rowFields != '')
		{

			rowFieldsDD(rowFields, nSep , to)
			shortDelay()
			rowSubTotalStatus15(rowSubTotal,rFieldnm)
		}
		if(colFields != '')
		{
			colFieldsDD(colFields,nSep ,  to)
			shortDelay()
			colSubTotalStatus15(colSubTotal,cFieldnm)
		}
		if(dataFields != '')
		{
			dataFieldsDD(dataFields, nSep ,  to)
			shortDelay()
			setAggrForMsr(msrNameT,aggrType,nSep)
		}
		if(preview != '')
		{
			previewStatus(preview)
		}
		if(ImageTabularEnable != '')
		{
			imageTabularEnable(ImageTabularEnable)
		}
		if(grandTotal != '')
		{
			grandTotalStatus(grandTotal)
		}
		if(itemCount != '')
		{
			itemCountStatus(itemCount)
		}
		if(tableName != '')
		{
			setTableName(tableName)
		}
		//if(rowFields != '' | colFields != '' & dataFields != '' )
		//{
		// TabularApply()
		//}
		//if(exportFileName != '')
		//{
		// exportAllTabular(exportFileName)
		//}
		//if(expectedPath !='' & actualPath != '' )
		//{
		// compareTabular(expectedPath,actualPath)
		//}

		else
		{
			KeywordUtil.logInfo("No tabular Info")
			shortDelay()
		}

	}//def configTabular()

	@Keyword
	def copyPivotTbl(String copyPivot)
	{
		if(copyPivot == 'Yes')
		{
			//Click on Copy Pivot Icon inorder to copy to clipboard
			TestObject copyPivotObj = makeTestObject('li', '', '', 'copyPivot', '','' ,'')
			WebUI.click(copyPivotObj)
			shortDelay()
			//Click on 'OK' button of copy to clipboard success message
			TestObject copyPivotOKBtn = makeTestObject('button', '', 'confirm', '', '','' ,'')
			WebUI.click(copyPivotOKBtn)
			shortDelay()
		}
		else
		{
			KeywordUtil.logInfo("Copypivot table info is blank")
		}

	}// copyPivotTbl()

	@Keyword
	def resetAllTbls(String removeAlltbls)
	{
		if(removeAlltbls != '')
		{
			//Click on Remove all tbls icon
			TestObject resetAllTbls = makeTestObject('li', '', '', 'resetPivot', '','' ,'')
			WebUI.click(resetAllTbls)
			shortDelay()
			//Click on 'Ok' to remove all tables
			TestObject Okbtn = makeTestObject('button', '', 'cancel', '', '','' ,'')
			WebUI.click(Okbtn)
			shortDelay()

		}else
		{
			KeywordUtil.logInfo("RemoveAll tables info is blank")
		}

	}// resetAllTbls()

	@Keyword
	def duplicatePivot(String duplicateTbl)
	{
		if(duplicateTbl == 'Yes')
		{
			//Open Tabular configuration
			openTabularConfig()
			//Click on Duplicate icon
			TestObject duplicateBtn = makeTestObject('button', '', 'createcopy', 'duplicateTabular', '','' ,'')
			WebUI.click(duplicateBtn)
			shortDelay()

		}else
		{
			KeywordUtil.logInfo("Duplicate Table info is blank")
		}

	}// duplicatePivot()

	@Keyword
	def newTabular(String newTbl)
	{
		if(newTbl == 'Enable')
		{
			//Check for open tabular config
			//openTabularConfig()
			//Click on create new tabular Icon
			WebDriver driver = DriverFactory.getWebDriver()
			TestObject newTable = makeTestObject('li', '', '', 'newTabular', '','' ,'')

			if(WebUI.verifyElementPresent(newTable,10,FailureHandling.OPTIONAL))
			{

				WebElement element = WebUiCommonHelper.findWebElement(newTable,10)
				JavascriptExecutor executor = ((driver) as JavascriptExecutor)
				executor.executeScript('arguments[0].click()', element)

				//WebUI.click(newTable)
				shortDelay()

			}else
			{
				KeywordUtil.logInfo("Could not find new table")

				//Click on Kanvas Logo
				WebUI.click(findTestObject('Object Repository/Final Objects/MySelection/MainButns/kanvaslogo'))

				//alerts handling
				alertHandling()
				//Click on Tabular
				switchToTabular()
				//Click on new tabular
				WebElement element = WebUiCommonHelper.findWebElement(newTable,10)
				JavascriptExecutor executor = ((driver) as JavascriptExecutor)
				executor.executeScript('arguments[0].click()', element)

				//WebUI.click(newTable)
				shortDelay()
			}
		}else
		{

			KeywordUtil.logInfo("Create NewTable info is blank")
		}

	}// newTabular()

	@Keyword
	static tblclickAttrVals(String tattrNames,String tvals, String vSep)
	{
		def mulVals = tvals.split(vSep)

		int lenMul = mulVals.size()

		for (def index : (0..lenMul-1))
		{
			WebUI.delay(5)
			//Click on search input with in the attribute
			//TestObject SKeyword = makeTestObject('li', '', 'form-control searchInput searchInTabularAttr', '', 'placeholder','Search Values' ,'')
			TestObject SKeyword = makeTestObject('', '', '', '', '','' ,'//li[@class="attr-name attrnmtabular open-attr-nm" and @data-attrname="'+tattrNames+'"]//input[@class="form-control searchInput searchInTabularAttr"]')
			WebUI.click(SKeyword)
			WebUI.setText(SKeyword,mulVals[index].trim())
			WebUI.sendKeys(SKeyword,Keys.chord(Keys.ENTER))
			//Click on attribute values
			TestObject attrV = makeTestObject('li', '', 'txt-attr-val', '', 'data-attrval', mulVals[index].trim(), '')
			WebUI.click(attrV)
			WebUI.delay(5)
		}


	}//tblclickAttrVals

	@Keyword
	def clickAttrValSearchInput()
	{

		//Click on search input with in the attribute
		TestObject SKeyword = makeTestObject('li', '', 'searchInTabularAttr', '', 'placeholder','Search Values' ,'')
		WebUI.click(SKeyword)
		shortDelay()




	}// clickAttrValSearchInput()

	@Keyword

	def tblselAttrNdVals(String tattrNames,String tattrVals, String nSep, String vSep)
	{



		if(tattrNames != ''){

			WebDriver driver = DriverFactory.getWebDriver()


			//TestObject closeSlider = makeTestObject('', '', '', '', '','' ,'id("myselection-content")/div[@class="displayTabMenu col-md-12 col-lg-12"]/div[@class="pivot-config zerozindex"]/div[@class="pivot-config-box"]/div[@class="pivot-config-icon"]/i[@class="setting-icon-lg-white"]')
			//WebUI.click(closeSlider)
			shortDelay()
			openTabularConfig()

			def mulattrN = tattrNames.split(nSep)
			def mulVals =  tattrVals.split(nSep)
			int mulattrLen = mulattrN.size()
			KeywordLogger log = new KeywordLogger()

			for(def index :(0..mulattrLen-1))
			{
				String attr = mulattrN[index].trim()

				/*String chkLbl =  '//input[@class="checkbox chkSelAttr" and @data-selattr="'+attr+'"]/following-sibling::label'
				 //String chkLbl =  '//input[@data-selattr="'+attr+'"]/following-sibling::label'
				 KeywordUtil.logInfo(chkLbl)
				 //String chkI = '//input[@class="checkbox chkSelAttr" and @data-selattr="'+attr+'"]'
				 String chkI = '//input[@data-selattr="'+attr+'"]'
				 //String unChkDiv = '//li[@class="attr-name attrnmtabular" and @data-attrname="'+attr+'"]/child::div[2]'
				 String unChkDiv = '//li[@class="attr-name attrnmtabular" and @data-attrname="'+attr+'"]//input[@class="checkbox chkSelAttr" and @data-selattr="'+attr+'"]/following-sibling::label'
				 String unChkDiv1 = '//li[@class="attr-name attrnmtabular open-attr-nm" and @data-attrname="'+attr+'"]//input[@class="checkbox chkSelAttr" and @data-selattr="'+attr+'"]/following-sibling::label'
				 KeywordUtil.logInfo(unChkDiv1)
				 TestObject chkbxInput = makeTestObject('', '', '', '', '', '',chkI)
				 WebUI.delay(5)
				 //if((WebUI.verifyElementChecked(chkbxInput,10,FailureHandling.OPTIONAL))== true){
				 KeywordUtil.logInfo("Attribute Input is checked...click on uncheck")
				 WebUI.delay(5)
				 //Click on checkbox inorder to deselect all values selection
				 TestObject chkbxLbl = makeTestObject('', '', '', '', '', '', unChkDiv)
				 //TestObject chkbxLbl = makeTestObject('', '', '', '', '', '', chkLbl)
				 //WebUI.click(chkbxLbl)
				 //clickUsingJS(chkbxLbl,10)
				 WebUI.check(chkbxLbl)
				 //WebUI.delay(10)*/
				//Click on Select all Check box
				String unChkDiv = '//li[@data-attrname="'+attr+'"]//label[@class="lbl_cls chkSelAttr_lbl"]'
				TestObject chkbxLbl = makeTestObject('', '', '', '', '', '', unChkDiv)
				WebElement element = WebUiCommonHelper.findWebElement(chkbxLbl,10)
				JavascriptExecutor executor = ((driver) as JavascriptExecutor)
				executor.executeScript('arguments[0].click()', element)
				//WebUI.click(attrName)
				//longDelay()
				KeywordUtil.logInfo("Checkbox Clicked")
				shortDelay()

				//Click on attribute name to expand
				String xpathexpand = '//li[@class="attr-name attrnmtabular" and @data-attrname="'+attr+'"]//div[@class = "wrap-attr-name-tabular"]'
				String xpathcollapse = '//li[@class="attr-name attrnmtabular open-attr-nm" and @data-attrname="'+attr+'"]//div[@class = "wrap-attr-name-tabular"]'
				//Make Object to expand attribute name
				TestObject attrNE = makeTestObject('', '', '', '', '', '',xpathexpand)
				////Make Object to collpase attribute name
				TestObject attrNC = makeTestObject('', '', '', '', '', '',xpathcollapse)

				WebUI.click(attrNE)
				WebUI.delay(5)

				//Call attribute values function
				//clickAttrVals(mulVals[index],vSep)
				tblclickAttrVals(attr,mulVals[index],vSep)
				WebUI.click(attrNC)


				/*}else
				 {
				 KeywordUtil.logInfo("Attribute was unchecked..Continue Filtering")
				 //Click on attribute name to expand
				 String xpathexpand = '//li[@class="attr-name attrnmtabular" and @data-attrname="'+attr+'"]//div[@class = "wrap-attr-name-tabular"]'
				 String xpathcollapse = '//li[@class="attr-name attrnmtabular open-attr-nm" and @data-attrname="'+attr+'"]//div[@class = "wrap-attr-name-tabular"]'
				 //Make Object to expand attribute name
				 TestObject attrNE = makeTestObject('', '', '', '', '', '',xpathexpand)
				 ////Make Object to collpase attribute name
				 TestObject attrNC = makeTestObject('', '', '', '', '', '',xpathcollapse)
				 WebUI.click(attrNE)
				 WebUI.delay(5)
				 //Call attribute values function
				 //clickAttrVals(mulVals[index],vSep)
				 tblclickAttrVals(mulVals[index],vSep)
				 WebUI.click(attrNC)
				 }*/

			}
			//TabularApply()
			//WebUI.delay(10)

		}else
		{
			KeywordUtil.logInfo("attribute name is blank")
		}
	}//tblselAttrNdVals



	@Keyword
	def resetAttrOrMsr(String resetAttrOrMsrnm,String nSep)
	{
		if(resetAttrOrMsrnm != '')
		{
			openTabularConfig()
			def mulNames = resetAttrOrMsrnm.split(nSep)
			int lenMul = mulNames.size()
			for (def index : (0..lenMul-1))
			{
				String mulNamesTo = mulNames[index].trim()
				//xpaths for row,col an data filed names
				//String rowfields = '//li[@class="rowLi ui-state-default" or @class="ovrdCmlCase rowLi ui-state-default" and @data-field="'+mulNamesTo+'"]//i[@class = "close-icon-sm-red pvtRowAttrRemover"]'
				String rowfields = '//li[@data-field="'+mulNamesTo+'"]//i[@class = "close-icon-sm-red pvtRowAttrRemover"]'
				String colfields = '//li[@data-field="'+mulNamesTo+'"]//i[@class = "close-icon-sm-red pvtColAttrRemover"]'
				String datafields = '//span[@class="aggrAttr wide" and @data-attr="'+mulNamesTo+'"]/following-sibling::i'
				KeywordUtil.logInfo(rowfields)
				//Object creation for the xpaths
				TestObject rowfieldObj = makeTestObject('', '', '', '', '','' ,rowfields)
				TestObject colfieldObj = makeTestObject('', '', '', '', '','' ,colfields)
				TestObject datafieldObj = makeTestObject('', '', '', '', '','' ,datafields)

				if(WebUI.verifyElementPresent(rowfieldObj,10,FailureHandling.OPTIONAL))
				{
					WebUI.click(rowfieldObj)
					WebUI.delay(5)
				}
				else if (WebUI.verifyElementPresent(colfieldObj,10,FailureHandling.OPTIONAL))
				{
					WebUI.click(colfieldObj)
					WebUI.delay(5)
				}
				else
				{
					WebUI.click(datafieldObj)
					WebUI.delay(5)
				}
			}
			TabularApply()

		}
		else
		{
			KeywordUtil.logInfo("reset Attribute OR Measure name is blank")
		}
	}// resetAttrOrMsr()






	@Keyword
	static tdoSort(String tsortAttrName, String tattrStatus, String nSep, String vSep)
	{
		if(tsortAttrName != "")
		{

			def mulMsrSort = tsortAttrName.split(nSep)
			def mulmsrStatus = tattrStatus.split(vSep)
			int mulMsrLen = mulMsrSort.size()
			KeywordLogger log = new KeywordLogger()

			for(def index :(0..mulMsrLen-1))
			{
				String msr = mulMsrSort[index].trim()
				log.logInfo(msr)
				tmDoSort(mulMsrSort[index], mulmsrStatus[index], vSep)
				//tmDoSortR15(mulMsrSort[index], mulmsrStatus[index], vSep)
			}
			TabularApply()

		}else{
			KeywordUtil.logInfo("Tabular Sort Attribute is not available")
		}

	}//tdoSort

	@Keyword
	static tdoSortR15(String tsortAttrName, String tattrStatus, String nSep, String vSep)
	{
		if(tsortAttrName != "")
		{

			def mulMsrSort = tsortAttrName.split(nSep)
			def mulmsrStatus = tattrStatus.split(vSep)
			int mulMsrLen = mulMsrSort.size()
			KeywordLogger log = new KeywordLogger()

			for(def index :(0..mulMsrLen-1))
			{
				String msr = mulMsrSort[index].trim()
				log.logInfo(msr)
				tmDoSort15(mulMsrSort[index], mulmsrStatus[index], vSep)
				WebUI.delay(10)
			}



		}else{
			KeywordUtil.logInfo("Tabular Sort Attribute is not available")
		}


	}//tdoSortR15


	@Keyword
	static tmDoSort(String tsortAttrName, String tattrStatus,String vSep)
	{

		def mulmsrStatus = tattrStatus.split(vSep)
		int lenMulmsrStatus = mulmsrStatus.size()
		for (def index : (0..lenMulmsrStatus-1))
		{
			//Make XPath for column 'th'
			String xpathTH = '//th [@class="pvtColLabel controlRow" or @class="pvtColLabel controlRow lastTotalCell"]'

			//Make Test Object based on Xpath and msrname from data sheet
			TestObject thObj = makeTestObject('', '', '', '', 'data-control-col', tsortAttrName ,xpathTH)
			//Verify header presents with the passed attribute /measure name
			def getAttrdata = WebUI.verifyElementAttributeValue(thObj,'data-control-col',tsortAttrName,1)
			//Get sort icon class using innerHTML method
			//String s = WebUI.getAttribute(thObj, 'innerHTML')
			CharSequence s = WebUI.getAttribute(thObj, 'innerHTML')

			//To do Sort Order
			if(tattrStatus == "ascend")
			{
				if(s.contains(tattrStatus))
				{

					KeywordUtil.logInfo("It's in Ascend status only")
					WebUI.delay(10)
					//WebUI.click(thObj)
					//WebUI.delay(10)
				}
				else if(s.contains("sort-icon-xs-darkgrey"))
				{
					KeywordUtil.logInfo("It's in Disable Status")
					WebUI.click(thObj)
					WebUI.delay(10)
				}
				else
				{
					KeywordUtil.logInfo("It's in Descend Status")
					WebUI.click(thObj)
					WebUI.delay(5)
					WebUI.click(thObj)
					WebUI.delay(10)
				}
			}// if(tattrStatus == "ascend")
			else if(tattrStatus == "descend")
			{
				if(s.contains("descend"))
				{

					KeywordUtil.logInfo("It's in Descend status only")
					WebUI.delay(10)
					//WebUI.click(thObj)
					//WebUI.delay(10)
				}
				else if(s.contains("sort-icon-xs-darkgrey"))
				{
					KeywordUtil.logInfo("It's in Disable Status")
					WebUI.click(thObj)
					WebUI.delay(5)
					WebUI.click(thObj)
					WebUI.delay(5)
				}
				else
				{
					KeywordUtil.logInfo("It's in Ascend Status")
					WebUI.click(thObj)
					WebUI.delay(5)

				}
			}//else if(tattrStatus == "descend")
			else
			{
				if(s.contains("sort-icon-xs-darkgrey"))
				{

					KeywordUtil.logInfo("It's in Disable status only")
					WebUI.delay(10)
					//WebUI.click(thObj)
					//WebUI.delay(10)
				}
				else if(s.contains("ascend"))
				{
					KeywordUtil.logInfo("It's in Ascend Status")
					WebUI.click(thObj)
					WebUI.delay(5)
					WebUI.click(thObj)
					WebUI.delay(5)
				}
				else
				{
					KeywordUtil.logInfo("It's in Descend Status")
					WebUI.click(thObj)
					WebUI.delay(5)

				}

			}//disable sort


		}//for (def index : (0..lenMulmsrStatus-1))




	}//tmDoSort

	//Created on 14-Apr as per R15 version
	//Selection of sort order is changed in R15
	//Capture the new selection of sort order accessor
	@Keyword
	static tmDoSort15(String tsortAttrName, String tattrStatus,String vSep)
	{

		def mulmsrStatus = tattrStatus.split(vSep)
		int lenMulmsrStatus = mulmsrStatus.size()
		for (def index : (0..lenMulmsrStatus-1))
		{
			//Make XPath for column 'th'
			String xpathTH = '//th [@class="pvtColLabel controlRow row-1th" or @class="pvtColLabel controlRow row-1th lastTotalCell"]'

			//Make Test Object based on Xpath and msrname from data sheet
			TestObject thObj = makeTestObject('', '', '', '', 'data-control-col', tsortAttrName ,xpathTH)
			//Verify header presents with the passed attribute /measure name
			def getAttrdata = WebUI.verifyElementAttributeValue(thObj,'data-control-col',tsortAttrName,1)

			//Get sort icon class using innerHTML method
			//String s = WebUI.getAttribute(thObj, 'innerHTML')
			CharSequence s = WebUI.getAttribute(thObj, 'innerHTML')

			//To do Sort Order
			//To do Ascend
			if(tattrStatus == "ascend")
			{
				if(s.contains(tattrStatus))
				{

					KeywordUtil.logInfo("It's in Ascend status only")
					shortDelay()
				}else
				{
					//click on column header
					WebUI.click(thObj)
					//Click on Ascend Text
					//Make object for Ascend Order
					TestObject ascOrder = makeTestObject('', '', '', '', '', '' ,'//div[@class="sortmenudiv"]//span[@class="pvtSorterClassSpan ascendSpan"]')
					//Click on Ascend Object
					WebUI.click(ascOrder)
					KeywordUtil.logInfo("Sort Order Set to Ascend")
					WebUI.delay(10)
				}

			}//if(tattrStatus == "ascend")

			else if(tattrStatus == "descend")
			{
				if(s.contains("descend"))
				{

					KeywordUtil.logInfo("It's in Descend status only")
					shortDelay()
				}
				else
				{

					//click on column header
					WebUI.click(thObj)
					//Click on Descend Text
					//Make object for Descend Order
					TestObject descOrder = makeTestObject('', '', '', '', '', '' ,'//div[@class="sortmenudiv"]//span[@class="pvtSorterClassSpan descendSpan"]')
					//Click on Descend Object
					WebUI.click(descOrder)
					KeywordUtil.logInfo("Sort Order Set to Descend")
				}
			}//else if(tattrStatus == "descend")
			else
			{
				if(s.contains("sort-icon-xs-darkgrey"))
				{

					KeywordUtil.logInfo("It's in Disable status only")
					shortDelay()
				}
				else
				{
					//click on column header
					WebUI.click(thObj)
					//Click on Disable Text
					//Make object for Disable Order
					TestObject disableOrder = makeTestObject('', '', '', '', '', '' ,'//div[@class="sortmenudiv"]//span[@class="pvtSorterClassSpan nosortSpan"]')
					//Click on Disbale Object
					WebUI.click(disableOrder)
					KeywordUtil.logInfo("Sort Order Set to Disable")
					shortDelay()
				}

			}//disable sort



		}//for (def index : (0..lenMulmsrStatus-1))




	}//tmDoSort15



	//row sort

	@Keyword
	static trowdoSort(String tsortRAttrName, String tattrRStatus, String nSep, String vSep)
	{
		if(tsortRAttrName != "")
		{

			def mulMsrSort = tsortRAttrName.split(nSep)
			def mulmsrStatus = tattrRStatus.split(vSep)
			int mulMsrLen = mulMsrSort.size()
			KeywordLogger log = new KeywordLogger()

			for(def index :(0..mulMsrLen-1))
			{
				String msr = mulMsrSort[index].trim()
				log.logInfo(msr)
				tmrowDoSort(mulMsrSort[index], mulmsrStatus[index], vSep)
				KeywordUtil.logInfo(mulMsrSort[index]+'---------'+mulmsrStatus[index])
			}
		}else{
			KeywordUtil.logInfo("Tabular Sort Row Attribute is not available")
		}

	}//trowdoSort

	@Keyword
	static trowdoSortR15(String tsortRAttrName, String tattrRStatus, String nSep, String vSep)
	{
		if(tsortRAttrName != "")
		{

			def mulMsrSort = tsortRAttrName.split(nSep)
			def mulmsrStatus = tattrRStatus.split(vSep)
			int mulMsrLen = mulMsrSort.size()
			KeywordLogger log = new KeywordLogger()

			for(def index :(0..mulMsrLen-1))
			{
				String msr = mulMsrSort[index].trim()
				log.logInfo(msr)
				tmrowDoSortR15(mulMsrSort[index], mulmsrStatus[index], vSep)
				KeywordUtil.logInfo(mulMsrSort[index]+'---------'+mulmsrStatus[index])
			}
		}else{
			KeywordUtil.logInfo("Tabular Sort Row Attribute is not available")
		}

	}//trowdoSortR15

	@Keyword
	static tmrowDoSort(String tsortRAttrName, String tattrRStatus,String vSep)
	{

		def mulmsrStatus = tattrRStatus.split(vSep)
		int lenMulmsrStatus = mulmsrStatus.size()
		for (def index : (0..lenMulmsrStatus-1))
		{
			//Make Test Object for all sort orders for the measure name mentioned
			TestObject rowthObjASC = makeTestObject('i', '', 'ascend', '', 'data-content', tsortRAttrName ,'')
			TestObject rowthObjDESC = makeTestObject('i', '', 'descend', '', 'data-content', tsortRAttrName ,'')
			TestObject rowthObjDIS = makeTestObject('i', '', 'sort-icon-xs-darkgrey', '', 'data-content', tsortRAttrName ,'')

			String attrClass
			//to do ASCEND
			if(tattrRStatus == 'ascend' )
			{

				if((WebUI.verifyElementPresent(rowthObjASC, 10, FailureHandling.OPTIONAL)) == true)
				{
					KeywordUtil.logInfo("It's in Ascend status only")
					WebUI.delay(10)
				}
				else if((WebUI.verifyElementPresent(rowthObjDIS, 10, FailureHandling.OPTIONAL)) == true)
				{
					KeywordUtil.logInfo("It's in Disable Status")
					WebUI.click(rowthObjDIS)
					//clickUsingJS(rowthObjDIS,10)
					WebUI.delay(10)
				}
				else
				{
					KeywordUtil.logInfo("It's in Descend Status")
					WebUI.click(rowthObjDESC)
					WebUI.delay(5)
					WebUI.click(rowthObjDIS)
					WebUI.delay(10)
				}
			}//if(tattrRStatus == ' ascend ' )
			else if(tattrRStatus == 'descend' )
			{
				if((WebUI.verifyElementPresent(rowthObjDESC, 10, FailureHandling.OPTIONAL)) == true)
				{
					KeywordUtil.logInfo("It's in Descend status only")
					WebUI.delay(10)
				}
				else if((WebUI.verifyElementPresent(rowthObjASC, 10, FailureHandling.OPTIONAL)) == true)
				{
					KeywordUtil.logInfo("It's in Ascend Status")
					WebUI.click(rowthObjASC)
					WebUI.delay(10)
				}
				else
				{
					KeywordUtil.logInfo("It's in Disable Status")
					WebUI.click(rowthObjDIS)
					WebUI.delay(5)
					WebUI.click(rowthObjASC)
					WebUI.delay(10)
				}
			}//else if(tattrRStatus == ' descend ' )
			else if(tattrRStatus == 'disable')
			{
				if((WebUI.verifyElementPresent(rowthObjDIS, 10, FailureHandling.OPTIONAL)) == true)
				{
					KeywordUtil.logInfo("It's in Disable status only")
					WebUI.delay(10)
				}
				else if((WebUI.verifyElementPresent(rowthObjDESC, 10, FailureHandling.OPTIONAL)) == true)
				{
					KeywordUtil.logInfo("It's in Descend Status")
					WebUI.click(rowthObjDESC)
					WebUI.delay(10)
				}
				else
				{
					KeywordUtil.logInfo("It's in Ascend Status")
					WebUI.click(rowthObjASC)
					WebUI.delay(5)
					WebUI.click(rowthObjDESC)
					WebUI.delay(10)
				}
			}
			else
			{
				KeywordUtil.logInfo("Status column has wrong inputs")
			}


		}//for (def index : (0..lenMulmsrStatus-1))

	}//tmrowDoSort


	@Keyword
	static tmrowDoSortR15(String tsortRAttrName, String tattrRStatus,String vSep)
	{

		def mulmsrStatus = tattrRStatus.split(vSep)
		int lenMulmsrStatus = mulmsrStatus.size()
		for (def index : (0..lenMulmsrStatus-1))
		{
			//Make XPath for column 'th'
			String xpathTH = '//th[@class="pvtAxisLabel row-2th"]//i[@data-content="brand"]'

			//Make Test Object for row header sort icon
			TestObject thObj = makeTestObject('', '', '', '', '', '',xpathTH)
			//Verify header presents with the passed attribute /measure name
			def getAttrdata = WebUI.verifyElementAttributeValue(thObj,'data-content',tsortRAttrName,1)

			//Get sort icon class using innerHTML method
			//String s = WebUI.getAttribute(thObj, 'innerHTML')
			//CharSequence s = WebUI.getAttribute(thObj, 'innerHTML')
			CharSequence s = WebUI.getAttribute(thObj, 'class')
			KeywordUtil.logInfo('class is' +s)

			//To do Sort Order
			//To do Ascend
			if(tattrRStatus == "ascend")
			{
				if(s.contains(tattrRStatus))
				{

					KeywordUtil.logInfo("It's in Ascend status only")
					shortDelay()
				}else
				{
					//click on column header
					WebUI.click(thObj)
					//Click on Ascend Text
					//Make object for Ascend Order
					TestObject ascOrder = makeTestObject('', '', '', '', '', '' ,'//div[@class="sortmenudiv"]//span[@class="pvtSorterClassSpan ascendSpan"]')
					//Click on Ascend Object
					WebUI.click(ascOrder)
					KeywordUtil.logInfo("Sort Order Set to Ascend")
					WebUI.delay(10)
				}

			}//if(tattrStatus == "ascend")

			else if(tattrRStatus == "descend")
			{
				if(s.contains("descend"))
				{

					KeywordUtil.logInfo("It's in Descend status only")
					shortDelay()
				}
				else
				{

					//click on column header
					WebUI.click(thObj)
					//Click on Descend Text
					//Make object for Descend Order
					TestObject descOrder = makeTestObject('', '', '', '', '', '' ,'//div[@class="sortmenudiv"]//span[@class="pvtSorterClassSpan descendSpan"]')
					//Click on Descend Object
					WebUI.click(descOrder)
					KeywordUtil.logInfo("Sort Order Set to Descend")
				}
			}//else if(tattrStatus == "descend")
			else
			{
				if(s.contains("sort-icon-xs-darkgrey"))
				{

					KeywordUtil.logInfo("It's in Disable status only")
					shortDelay()
				}
				else
				{
					//click on column header
					WebUI.click(thObj)
					//Click on Disable Text
					//Make object for Disable Order
					TestObject disableOrder = makeTestObject('', '', '', '', '', '' ,'//div[@class="sortmenudiv"]//span[@class="pvtSorterClassSpan nosortSpan"]')
					//Click on Disbale Object
					WebUI.click(disableOrder)
					KeywordUtil.logInfo("Sort Order Set to Disable")
					shortDelay()
				}

			}//disable sort



		}//for (def index : (0..lenMulmsrStatus-1))





	}//tmrowDoSortR15








	/* ******************************************** End of  Tabular Functions ********************************************* */

	/* ********************************************    Layout Functions ********************************************* */


	@Keyword
	def clickOnExportBtnL()
	{
		//Click on Export button in Layout
		TestObject exportInLayout = makeTestObject('a', '', 'export layoutmenu-link', '', 'href','#export' ,'')
		WebUI.click(exportInLayout)
		longDelay()
	}//clickOnExportBtnL()

	@Keyword
	def switchToScheduleL()
	{
		//Click on Export button in Layout
		TestObject schdTab = makeTestObject('a', '', '', 'vi-schedule', 'href','#' ,'')
		WebUI.click(schdTab)
		longDelay()
	}//clickOnExportBtnL()

	@Keyword
	def selectExportMode()
	{
		//Click on ExportMode dropdown
		TestObject clickOnExportModeDD = makeTestObject('span', 'Select Export Mode', '', 'select2-chosen-8', '','' ,'')
		WebUI.click(clickOnExportModeDD)
		longDelay()
		//Click on Search Input inside dropdown
		TestObject clickOnExportModesearch = makeTestObject('input', '', '', 's2id_autogen8_search', '','' ,'')
		WebUI.click(clickOnExportModesearch)
		shortDelay()
		WebUI.setText(clickOnExportModesearch,'Only Tabular (Pivot Table)')
		WebUI.sendKeys(clickOnExportModesearch,Keys.chord(Keys.ENTER))
		shortDelay()
	}//selectExportMode()

	/*@Keyword
	 static loadTemplate(String tempName)
	 {
	 if(tempName != ""){
	 //Navigate to layout
	 WebUI.click(findTestObject('Object Repository/Final Objects/Layout/Main Buttons/navtoLayout'))
	 shortDelay()
	 TestObject tempN = makeTestObject('img', '', 'templateDP', '', 'data-dptemplate', tempName,'')
	 shortDelay()
	 WebUI.click(tempN)
	 }
	 else{
	 KeywordUtil.logInfo("Template Name is not available ")
	 }
	 }//loadTemplate(String tempName)*/

	@Keyword
	def exportFormat(String formatType)
	{
		if(formatType != "")
		{
			//Click on export Tab
			WebUI.click(findTestObject('Object Repository/Final Objects/Layout/Main Buttons/exportbtn'))
			shortDelay()
			//For PNG
			//click on current template
			WebUI.click(findTestObject('Object Repository/Final Objects/Layout/Main Buttons/a_Current Template'))
			shortDelay()
			//Set export file name
			WebUI.setText(findTestObject('Object Repository/Final Objects/Layout/Main Buttons/Exportformats/outputfilename'), 'PNG')
			shortDelay()
			TestObject formatN = makeTestObject('button', formatType, '', 'png', '', '','')
			shortDelay()
			WebUI.click(formatN)
			shortDelay()
			//Click on output file export request popup OK button
			WebUI.click(findTestObject('Object Repository/Final Objects/Layout/Main Buttons/Exportformats/OutptreqconfirmButtonOK'))
			shortDelay()
		}else{
			KeywordUtil.logInfo("Format type is not available ")
		}
	}//static exportFormat(String formatType)

	/* ********************************************    End of Layout Functions ********************************************* */

	/* ********************************************    Workspace Functions ********************************************* */

	@Keyword
	//Share Workspace
	def shareWS( String shareStatus,String shareUser, String vSep){


		/*//Click on sideMenu bar
		 sideMenuBar()
		 //Click on Workspace Tab
		 SwitchtoWorkspace()
		 shortDelay()
		 //make shareWS object then click
		 TestObject shareWSIcon = makeTestObject('span', '', 'workspaceShareClass', '', 'data-workspace',wsNameShare ,'')
		 WebUI.click(shareWSIcon)*/
		def mulUsers = shareUser.split(vSep)
		int lenMul = mulUsers.size()
		for (def index : (0..lenMul-1))
		{
			if(shareStatus == 'Edit')
			{

				//makeobject for shareWS with Edit permissions then click
				TestObject Edit = makeTestObject('label', '', '', '', '','' ,'(.//*[normalize-space(text()) and normalize-space(.)='+mulUsers[index]+'])[2]/following::span[1]')
				WebUI.check(Edit)
				//Share workspaces
				WebUI.click(findTestObject('Object Repository/Final Objects/LoadWSfromWSmodule/WSshareBtn'))
				shortDelay()
				//Ok btn on confirmMsg
				WebUI.click(findTestObject('Object Repository/Final Objects/LoadWSfromWSmodule/shareconfirmBtn'))
				shortDelay()
			}
			else
			{
				//makeobject for shareWS with View permissions then click
				TestObject View = makeTestObject('label', '', '', '', '','' ,'(.//*[normalize-space(text()) and normalize-space(.)='+mulUsers[index]+'])[2]/following::span[3]')
				WebUI.check(View)
				//Share workspaces
				WebUI.click(findTestObject('Object Repository/Final Objects/LoadWSfromWSmodule/WSshareBtn'))
				shortDelay()
				//Ok btn on confirmMsg
				WebUI.click(findTestObject('Object Repository/Final Objects/LoadWSfromWSmodule/shareconfirmBtn'))
				shortDelay()
			}
		}



	}//shareWS()

	@Keyword
	//Click on share Icon of mentioned workspace name
	def clickOnShareIcon(String wsNameShare, String nSep)
	{
		//Click on sideMenu bar
		sideMenuBar()
		//Click on Workspace Tab
		SwitchtoWorkspace()
		shortDelay()
		def mulShareWS = wsNameShare.split(nSep)
		int lenMul = mulShareWS.size()
		for (def index : (0..lenMul-1))
		{

			//make shareWS object then click
			TestObject shareWSIcon = makeTestObject('span', '', 'workspaceShareClass', '', 'data-workspace',mulShareWS[index] ,'')
			WebUI.click(shareWSIcon)

		}

	}//clickOnShareIcon()



	@Keyword
	//share workspace to multiple users with permissions
	def doShareWS(String wsNameShare, String shareStatus, String shareUser,  String vSep, String nSep){
		if(wsNameShare != "")
		{
			/*//Click on sideMenu bar
			 sideMenuBar()
			 //Click on Workspace Tab
			 SwitchtoWorkspace()
			 shortDelay()
			 //make shareWS object then click
			 TestObject shareWSIcon = makeTestObject('span', '', 'workspaceShareClass', '', 'data-workspace',wsNameShare ,'')
			 WebUI.click(shareWSIcon)*/
			clickOnShareIcon(wsNameShare,nSep)
			shortDelay()
			def mulshareStatus = shareStatus.split(nSep)
			def mulshareUser = shareUser.split(nSep)
			int mulStatusLen = mulshareStatus.size()
			KeywordLogger log = new KeywordLogger()

			for(def index :(0..mulStatusLen-1))
			{
				String wsStatus = mulshareStatus[index].trim()
				log.logInfo(wsStatus)
				shareWS(mulshareStatus[index], mulshareUser[index], vSep)

			}
		}else{
			KeywordUtil.logInfo("Share Workspace name is not available")
		}

	}

	@Keyword
	//MyWorkspace Tab
	def myWSTab(){

		TestObject myWSTab = makeTestObject('a', '', '', 'mywrk_tab', 'aria-controls', 'myWrkTab', '')
		shortDelay()
		WebUI.click(myWSTab)
		shortDelay()

	}//myWSTab()

	@Keyword
	//Shared Workspaces Tab
	def sharedWSTab(){

		TestObject sharedWSTab = makeTestObject('a', '', '', 'shdwrk_tab', 'aria-controls', 'acl', '')
		shortDelay()
		WebUI.click(sharedWSTab)
		shortDelay()

	}//sharedWSTab()



	@Keyword
	def SwitchtoWorkspace(){
		//WebUI.click(findTestObject('Object Repository/Final Objects/MySelection/MainButns/sidebar-Menu button'))
		//longDelay()
		WebUI.click(findTestObject('Object Repository/Final Objects/MySelection/MainButns/Workspace_tab'))
		longDelay()
	}//SwitchtoWorkspace()


	/* ******************************************* End of Workspace Functions    ********************************** */

	/* *****************Functions to Create Test Objects in memory ********************************** */

	/**
	 * Construct a Katalon-compatible TestObject in memory.
	 */

	/**
	 * @param tag (String) The tag element used to find the target element.
	 * @param to (TestObject) constructed TestObject
	 * @return (TestObject) The constructed TestObject.
	 */
	@Keyword
	static TestObject makeToTag(String tag, TestObject to) {
		to.addProperty("tag", ConditionType.EQUALS, tag)
		return to
	}
	/**
	 * @param text (String) The text used to find the target element.
	 * @param to (TestObject) constructed TestObject
	 * @return (TestObject) The constructed TestObject.
	 */
	@Keyword
	static TestObject makeToText(String text, TestObject to) {
		to.addProperty("text", ConditionType.EQUALS, text)
		return to
	}
	/**
	 * @param css (String) The class name used to find the target element.
	 * @param to (TestObject) constructed TestObject
	 * @return (TestObject) The constructed TestObject.
	 */
	@Keyword
	static TestObject makeToCssCls(String css, TestObject to) {
		to.addProperty("class", ConditionType.CONTAINS, css)
		return to
	}
	/**
	 * @param css (String) The id name used to find the target element.
	 * @param to (TestObject) constructed TestObject
	 * @return (TestObject) The constructed TestObject.
	 */
	@Keyword
	static TestObject makeToCssId(String css, TestObject to) {
		to.addProperty("id", ConditionType.EQUALS, css)
		return to
	}
	/**
	 * @param data (String) The data attribute used to find the target element.(ex: for,data-value,title)
	 * @param val (String) The value of data attribute used to find the target element.
	 * @param to (TestObject) constructed TestObject
	 * @return (TestObject) The constructed TestObject.
	 */
	@Keyword
	static TestObject makeToDataVal(String data, String val, TestObject to) {
		to.addProperty(data, ConditionType.EQUALS, val)
		return to
	}

	@Keyword
	static TestObject makeToXpath(String xpath, TestObject to){
		to.addProperty("xpath", ConditionType.EQUALS, xpath)
	}

	/**
	 * Katalon-compatible Test Object construct functions end here
	 */

	/* *****************End of Functions to Create Test Objects in memory ********************************** */

	/* ******************* Not Required Functions *********************** */
	@Keyword
	static TestObject makeToSDXpath(String SDxpath, TestObject to) {
		String val = '//input[@data-quickg='+SDxpath+']/following-sibling::label'
		KeywordLogger log = new KeywordLogger()
		log.logInfo(val)
		to.addProperty("xpath", ConditionType.EQUALS,val )
	}
	@Keyword
	static TestObject makeToSDXpathI(String SDxpathI, TestObject to) {
		String inputVal = '//input[@data-quickg='+SDxpathI+']'
		KeywordLogger log = new KeywordLogger()
		log.logInfo(inputVal)
		to.addProperty("xpath", ConditionType.EQUALS,inputVal )
	}

	@Keyword
	static TestObject makeToSTXpath(String STxpath, TestObject to) {
		String val = '//input[@data-stat='+STxpath+']/following-sibling::label'
		KeywordLogger log = new KeywordLogger()
		log.logInfo(val)
		to.addProperty("xpath", ConditionType.EQUALS,val )
		return to

	}
	@Keyword
	static TestObject makeToSTXpathI(String STxpathI, TestObject to) {
		String val = '//input[@data-stat='+STxpathI+']'
		KeywordLogger log = new KeywordLogger()
		log.logInfo(val)
		to.addProperty("xpath", ConditionType.EQUALS,val )
		return to

	}

	/* ******************* End Of Not Required Functions *********************** */



	/**
	 * Wrapper function for Katalon-compatible Test Object construct functions
	 * @param tag (String) The tag element used to find the target element.
	 * @param text (String) The text used to find the target element.
	 * @param cls (String) The class name used to find the target element.
	 * @param id (String) The id name used to find the target element.
	 * @param data, val (String, String) The data attribute and it's value used to find the target element.
	 * @return (TestObject) The constructed TestObject.
	 */
	@Keyword
	//Create object in runtime memory
	static TestObject makeTestObject(String tag, String text, String cls, String id, String data, String val, String xpath)
	{
		TestObject to = new TestObject()
		if (tag != '') {
			to = makeToTag (tag, to)
		}
		if (text != '') {
			to = makeToText(text, to)
		}
		if (cls != '') {
			to = makeToCssCls(cls, to)
		}
		if (id != '') {
			to = makeToCssId(id, to)
		}
		if (data != '') {

			to = makeToDataVal(data, val, to)
		}
		if(xpath != '') {

			to = makeToXpath(xpath, to)
		}

		return to;
	}//makeTestObject



	/* ******************Layout Functions**************************** */



	/* ******************End of Layout Functions**************************** */


}//public class genralFunctions



/*@Keyword
 def takeScreenshot(def path, def filename)
 {
 def fullpath = path + filename + (int) (Math.random() * 10000) + ".jpg"
 println WebUI.takeScreenshot(fullpath);
 }//takescreenshot()*/



