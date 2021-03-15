package standardFunc

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

public class AllstandardFunc {

	@Keyword
	def switchToStandard()
	{
		//Click on Standard Tab and make object for the same
		TestObject switchToStandard =(new generalFunc.AllgenralFunc()). makeTestObject('a', '', 'tab-cls-anch', '', 'aria-controls','itemsGridId' ,'')
		if(WebUI.waitForElementClickable(switchToStandard, 10, FailureHandling.CONTINUE_ON_FAILURE)==true)
		{
			WebUI.click(switchToStandard)
			(new generalFunc.AllgenralFunc()).shortDelay()
		}
		else
		{
			KeywordUtil.logInfo("Could not Find switchtostandard")
			KeywordUtil.markFailed("Could not Find switchtostandard")
		}
	}//switchToStandard()

	@Keyword
	//Created on 17-Apr
	def switchToLayoutFromStd()
	{
		//Click on Standard Tab and make object for the same
		TestObject switchTolayout = (new generalFunc.AllgenralFunc()).makeTestObject('li', '', '', 'goLayout', 'data-url','/layout/layout' ,'')
		if(WebUI.waitForElementClickable(switchTolayout, 10, FailureHandling.CONTINUE_ON_FAILURE)==true)
		{
			(new generalFunc.AllgenralFunc()).clickUsingJS(switchTolayout,10)
			(new generalFunc.AllgenralFunc()).shortDelay()
		}
		else
		{
			KeywordUtil.logInfo("Could not click on Layout Tab")
			KeywordUtil.markFailed("Could not click on Layout Tab")
		}
	}//switchToLayoutFromStd()


	@Keyword
	//Reset all Filters (Main Apply button in My selection)
	def ResetAllFlts(){
		TestObject resetAll = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','id("resetAll")/i[@class="reset-icon-lg-grey"]')
		WebUI.click(resetAll)
		(new generalFunc.AllgenralFunc()).shortDelay()
	}//ResetAllFlts

	@Keyword
	//Created on 06-05-2020
	//Click tooltip info btn in my selection
	def ClickInfoBtn()
	{
		//Click on Info button
		TestObject infoBtn = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//li[@class="lineRight infoButtonLi dropdown showInfo"]')
		WebUI.click(infoBtn)

	}//ClickInfoBtn()

	@Keyword
	//Validate is expected and actual tooltip info is matched / not matched
	def CompareTooltipInfo(def expectedToolTipInfo){
		if(expectedToolTipInfo != "")
		{
			//Click on info button (Calling function)
			ClickInfoBtn()
			(new generalFunc.AllgenralFunc()).shortDelay()

			// Get info details from kanvas
			//Create object for  getting text
			TestObject ulList = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//ul[@class="dropdown-menu" and @id="info-content"]')
			String tiActual = WebUI.getText(ulList)
			KeywordUtil.logInfo(tiActual)

			if(expectedToolTipInfo == tiActual)
			{

				KeywordUtil.markPassed('SUCCESS: Both actual and expected info is same' )

			}
			else
			{

				KeywordUtil.markFailed('ERROR: tooltip info is not same as expected and the actual filtered info is: '+tiActual + 'the expected info is:' +expectedToolTipInfo )

			}

		}
		else
		{
			KeywordUtil.logInfo("Expected tooltip info is not avaialable")
		}
	}//CompareTooltipInfo

	@Keyword
	//Function Created on 27-08-20
	//Click on Item window and compare msr values
	def validateMsriw(String miwitemID,String msrNM, String expmsrVals,String nSep,String vSep)
	{
		if( miwitemID != "")
		{
			def mulIDs = miwitemID.split(nSep)
			def msrNaMe = msrNM.split(nSep)
			def mulMVals = expmsrVals.split(nSep)
			int mulIdLen = mulIDs.size()
			KeywordLogger log = new KeywordLogger()
			for(def index :(0..mulIdLen-1))
			{
				String itemIDVal = mulIDs[index].trim()
				KeywordUtil.logInfo(itemIDVal)
				//Make object for item Id and then click
				TestObject itemIDObj = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//h6[@data-imgsku="'+itemIDVal+'"]/../preceding-sibling::a[@class="abvthumbnail"]')
				WebUI.click(itemIDObj)
				(new generalFunc.AllgenralFunc()).shortDelay()
				CompareMsrVals(msrNaMe[index],mulMVals[index],vSep)
				(new generalFunc.AllgenralFunc()).shortDelay()
				//Close Item window
				TestObject clsItemWin = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//i[@class="close close-icon-lg-blue"]')
				WebUI.click(clsItemWin)
				(new generalFunc.AllgenralFunc()).shortDelay()
			}

		}
		else
		{
			KeywordUtil.logInfo("Measure info is blank")
		}
	}//validateMsriw

	@Keyword
	//Function Created on 27-08-20
	//Click on Item window and compare attribute values
	def validateAttriw(String aiwitemID,String attrNMiw, String expattrVals,String nSep,String vSep)
	{
		if(aiwitemID != "" & attrNMiw != "")
		{
			def mulIDs = aiwitemID.split(nSep)
			def attrNaMe = attrNMiw.split(nSep)
			def mulAVals = expattrVals.split(nSep)
			int mulIdLen = mulIDs.size()
			KeywordLogger log = new KeywordLogger()
			for(def index :(0..mulIdLen-1))
			{
				String itemIDVal = mulIDs[index].trim()
				KeywordUtil.logInfo(itemIDVal)
				//Make object for item Id and then click
				TestObject itemIDObj = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//h6[@data-imgsku="'+itemIDVal+'"]/../preceding-sibling::a[@class="abvthumbnail"]')
				WebUI.click(itemIDObj)
				(new generalFunc.AllgenralFunc()).shortDelay()
				CompareAttrVals(attrNaMe[index], mulAVals[index],vSep)
				(new generalFunc.AllgenralFunc()).shortDelay()
				//Close Item window
				TestObject clsItemWin = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//i[@class="close close-icon-lg-blue"]')
				WebUI.click(clsItemWin)
				(new generalFunc.AllgenralFunc()).shortDelay()
			}
		}
		else
		{
			KeywordUtil.logInfo("Attribute info is blank")
		}
	}//validateAttriw

	@Keyword
	//Function Created on 27-April-2020
	//Function Updated on 28-April-2020
	//Click on Item Window
	def ClickonItemWin(String itemID,String msrNM, String expmsrVals,String nSep,String vSep)
	{
		if(itemID != "")

		{
			def mulIDs = itemID.split(nSep)
			def msrNaMe = msrNM.split(nSep)
			def mulVals =  expmsrVals.split(nSep)
			int mulIdLen = mulIDs.size()
			KeywordLogger log = new KeywordLogger()
			for(def index :(0..mulIdLen-1))
			{
				String itemIDVal = mulIDs[index].trim()
				KeywordUtil.logInfo(itemIDVal)
				//Make object for item Id and then click
				//TestObject itemID = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//h6[@data-imgsku="CHF3"]/../preceding-sibling::a[@class="abvthumbnail"]')
				TestObject itemIDObj = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//h6[@data-imgsku="'+itemIDVal+'"]/../preceding-sibling::a[@class="abvthumbnail"]')
				WebUI.click(itemIDObj)


				//String msrNMVal = msrNaMe[index].trim()
				//String expMsrVal = mulVals[index].trim()
				KeywordUtil.logInfo(msrNaMe[index]+mulVals[index])
				CompareMsrVals(msrNaMe[index], mulVals[index],vSep)
				(new generalFunc.AllgenralFunc()).shortDelay()

				//Close Item window
				TestObject clsItemWin = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//i[@class="close close-icon-lg-blue"]')
				WebUI.click(clsItemWin)
				(new generalFunc.AllgenralFunc()).shortDelay()

			}
		}else
		{
			KeywordUtil.logInfo("Item Window info is blank")

		}

	}//ClickonItemWin()




	@Keyword
	//compare measure values in Item window
	def CompareMsrVals(String msrNM, String expmsrVals,String vSep)
	{

		def msrNaMe = msrNM.split(vSep)
		def mulVals =  expmsrVals.split(vSep)
		int mulmsrLen = msrNaMe.size()
		KeywordLogger log = new KeywordLogger()
		for(def index :(0..mulmsrLen-1))
		{
			String msrNMVal = msrNaMe[index].trim()
			String expMsrVal = mulVals[index].trim()

			KeywordUtil.logInfo(msrNMVal)


			//Click on Number group in item window
			TestObject numGrpE = (new generalFunc.AllgenralFunc()).makeTestObject('span', '', 'attr-type-lbl showMe', 'num-attr-hr', 'data-ulid', 'numAttrModal','')
			WebUI.click(numGrpE)

			//get Text of measure value
			TestObject msrValue = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//div[@class="listAB"]//span[contains(@class, "attrnm-div") and @data-attrnm = "'+ msrNMVal +'" ]/following-sibling::span')
			String actualMsrVal = WebUI.getAttribute(msrValue,'oldtitle')
			(new generalFunc.AllgenralFunc()).shortDelay()
			KeywordUtil.logInfo(actualMsrVal)
			if(WebUI.verifyMatch(actualMsrVal, expMsrVal, false))
			{
				KeywordUtil.logInfo("Both expected and actual values are same")
			}else
			{
				KeywordUtil.logInfo("Both expected and actual values are not same...actualVal is"+actualMsrVal+"and the expVal is"+ expMsrVal)
			}
		}

	}//CompareMsrVals()

	@Keyword
	//Created on 21-April-2020
	//Click on Search Icon
	def doSearch(String searchStatus, String searchKeyword){
		if(searchStatus != "")
		{

			if(searchStatus == "Global")
			{
				KeywordUtil.logInfo("Do Search keyword Global")
				//Make object for  Search Icon and then Click
				TestObject searchIcn= (new generalFunc.AllgenralFunc()).makeTestObject('li', '', 'lineRight', 'globalSearchEvt', '', '','')
				WebUI.click(searchIcn)
				//Make Object for SearchGlobal radio button and then click
				//TestObject searchGlbl= (new generalFunc.AllgenralFunc()).makeTestObject('label', 'Search Global', 'radio radioSrch', '', '', '','')
				TestObject searchGlbl= (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//label[@class="radio radioSrch"]/input[@id="radioG"]')
				if(WebUI.verifyElementChecked(searchGlbl, 10))
				{

					//WebUI.click(searchGlbl)
					//Make object for Set Keyword Input and set keyword to search
					TestObject searchInput= (new generalFunc.AllgenralFunc()).makeTestObject('input', '', 'form-control searchInput', 'search-box', 'placeholder', 'Enter Keywords','')
					WebUI.click(searchInput)
					WebUI.setText(searchInput,searchKeyword)
					WebUI.sendKeys(searchInput,Keys.chord(Keys.ENTER))
					//Make Object for Search btn and click on Search
					TestObject searchBtn= (new generalFunc.AllgenralFunc()).makeTestObject('button', 'Search', 'btn grayBtn', 'vi-btn-search', '', '','')
					WebUI.click(searchBtn)
					KeywordUtil.logInfo("Search for entered keywords")
					(new generalFunc.AllgenralFunc()).shortDelay()
					//Close Search Window
					TestObject clsSearchBtn= (new generalFunc.AllgenralFunc()).makeTestObject('button', '', 'closeSearch close close-icon-lg-blue', '', 'aria-label', 'Close','')
					WebUI.click(clsSearchBtn)

				}
				else
				{
					WebUI.click(searchGlbl)
					//Make object for Set Keyword Input and set keyword to search
					TestObject searchInput= (new generalFunc.AllgenralFunc()).makeTestObject('input', '', 'form-control searchInput', 'search-box', 'placeholder', 'Enter Keywords','')
					WebUI.click(searchInput)
					WebUI.setText(searchInput,searchKeyword)
					WebUI.sendKeys(searchInput,Keys.chord(Keys.ENTER))
					//Make Object for Search btn and click on Search
					TestObject searchBtn= (new generalFunc.AllgenralFunc()).makeTestObject('button', 'Search', 'btn grayBtn', 'vi-btn-search', '', '','')
					WebUI.click(searchBtn)
					KeywordUtil.logInfo("Search for entered keywords")
					(new generalFunc.AllgenralFunc()).shortDelay()
					//Close Search Window
					TestObject clsSearchBtn= (new generalFunc.AllgenralFunc()).makeTestObject('button', '', 'closeSearch close close-icon-lg-blue', '', 'aria-label', 'Close','')
					WebUI.click(clsSearchBtn)

				}
			}
			else if(searchStatus == "Results")
			{
				KeywordUtil.logInfo("Do Search keyword  with in the results")
				//Make object for  Search Icon and then Click
				TestObject searchIcn= (new generalFunc.AllgenralFunc()).makeTestObject('li', '', 'lineRight', 'globalSearchEvt', '', '','')
				WebUI.click(searchIcn)
				//Make Object for Search with in the Results  radio button and then click
				//TestObject searchResults= (new generalFunc.AllgenralFunc()).makeTestObject('label', 'Search In Result', 'radio radioRclass', '', '', '','')
				TestObject searchResults= (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//label[@class="radio radioRclass"]')
				/*if(WebUI.verifyElementChecked(searchResults, 10))
				 {
				 //WebUI.click(searchResults)
				 //Make object for Set Keyword Input and set keyword to search
				 TestObject searchInput= (new generalFunc.AllgenralFunc()).makeTestObject('input', '', 'form-control searchInput', 'search-box', 'placeholder', 'Enter Keywords','')
				 WebUI.click(searchInput)
				 WebUI.setText(searchInput,searchKeyword)
				 WebUI.sendKeys(searchInput,Keys.chord(Keys.ENTER))
				 //Make Object for Search btn and click on Search
				 TestObject searchBtn= (new generalFunc.AllgenralFunc()).makeTestObject('button', 'Search', 'btn grayBtn', 'vi-btn-search', '', '','')
				 WebUI.click(searchBtn)
				 KeywordUtil.logInfo("Search for entered keywords")
				 (new generalFunc.AllgenralFunc()).shortDelay
				 //Close Search Window
				 TestObject clsSearchBtn= (new generalFunc.AllgenralFunc()).makeTestObject('button', '', 'closeSearch close close-icon-lg-blue', '', 'aria-label', 'Close','')
				 WebUI.click(clsSearchBtn)
				 }else*/
				// {
				KeywordUtil.logInfo("Click on Search with in the Results option")
				WebUI.click(searchResults)

				//Make object for Set Keyword Input and set keyword to search
				TestObject searchInput= (new generalFunc.AllgenralFunc()).makeTestObject('input', '', 'form-control searchInput', 'search-box', 'placeholder', 'Enter Keywords','')
				WebUI.click(searchInput)
				WebUI.setText(searchInput,searchKeyword)
				WebUI.sendKeys(searchInput,Keys.chord(Keys.ENTER))
				//Make Object for Search btn and click on Search
				TestObject searchBtn= (new generalFunc.AllgenralFunc()).makeTestObject('button', 'Search', 'btn grayBtn', 'vi-btn-search', '', '','')
				WebUI.click(searchBtn)
				KeywordUtil.logInfo("Search for entered keywords")
				//Close Search Window
				TestObject clsSearchBtn= (new generalFunc.AllgenralFunc()).makeTestObject('button', '', 'closeSearch close close-icon-lg-blue', '', 'aria-label', 'Close','')
				WebUI.click(clsSearchBtn)
				//}
			}else
			{
				KeywordUtil.logInfo("Search Info is Invalid")
			}

		}
		else
		{
			KeywordUtil.logInfo("searchStatus Info is not available")
		}

	}//doSearch(String searchStatus, String searchKeyword)




	@Keyword
	//Main Apply button in my Selection
	def FilterApplyBtn(){
		WebDriver driver = DriverFactory.getWebDriver()
		TestObject fltApplyBtn = (new generalFunc.AllgenralFunc()).makeTestObject('button', '', 'mainAplyBtn', '', '', '', '')
		if(WebUI.verifyElementVisible(fltApplyBtn,FailureHandling.OPTIONAL))
		{
			WebElement element = WebUiCommonHelper.findWebElement(fltApplyBtn,10)
			JavascriptExecutor executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click()', element)
			(new generalFunc.AllgenralFunc()).longDelay()
			KeywordUtil.logInfo("Clicked on main Apply button")

		}
		else
		{
			KeywordUtil.logInfo("Apply button is visible....But not able to clickable")
		}
	}//FilterApplyBtn

	@Keyword
	//Function to select attribute values
	//Requires Value/s with coma separated
	//This function called by 'selAttrNdVals'
	static clickAttrVals(String vals, String vSep)
	{
		def mulVals = vals.split(vSep)

		int lenMul = mulVals.size()

		for (def index : (0..lenMul-1))
		{
			TestObject attrV = (new generalFunc.AllgenralFunc()).makeTestObject('li', mulVals[index].trim(), 'txt-attr-val', '', '', '', '')
			if(WebUI.waitForElementClickable(attrV, 10, FailureHandling.CONTINUE_ON_FAILURE)==true)
			{
				(new generalFunc.AllgenralFunc()).clickUsingJS(attrV,10)
				KeywordUtil.logInfo("Selected attribute values")
			}else
			{
				KeywordUtil.logInfo("Could not click on attribute value")
			}
		}

	}//clickAttrVals
	@Keyword
	//Updated on 13-08-20  with the attribute name with xpath accessor
	//Click on Attribute names and calls function to select values
	//Requires attribute name/s with semicolon separated , attribute value/s with coma separated

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

				KeywordUtil.logInfo(attr)

				//TestObject attrN = (new generalFunc.AllgenralFunc()).makeTestObject('div', attr, 'wrap-attr-name', '', '', '','')
				TestObject attrN = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//li[@class="attr-name searchAttrCls" and @data-attrname="'+attr+'"]')
				TestObject attrNCollpse = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//li[@class="attr-name searchAttrCls open-attr-nm" and @data-attrname="'+attr+'"]')
				//WebUI.click(attrN)
				(new generalFunc.AllgenralFunc()).clickUsingJS(attrN,10)
				(new generalFunc.AllgenralFunc()).shortDelay()
				clickAttrVals(mulVals[index],vSep)
				(new generalFunc.AllgenralFunc()).clickUsingJS(attrNCollpse,10)
			}
		}else
		{
			KeywordUtil.logInfo("attribute name is blank")
		}

	}//selAttrNdVals

	@Keyword
	//Updated on 20-10-20 with chk div accessor
	//Updated on 14-08-20
	//Created on 03-08-20
	//Click on Attribute names and click on select all values
	//Requires attribute name/s with semicolon separated , attribute value/s with coma separated

	static clickOnSelAllVals(String attrNameSelAllVals,String nSep)
	{

		if(attrNameSelAllVals != ""){

			def mulattrN = attrNameSelAllVals.split(nSep)
			int mulattrLen = mulattrN.size()

			KeywordLogger log = new KeywordLogger()
			for(def index :(0..mulattrLen-1))
			{
				String attr = mulattrN[index].trim()
				KeywordUtil.logInfo("Attribute name"+attr)
				//Click on Search Attribute Input
				TestObject Sinput = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//input[@class="form-control searchInput searchAttr" and @id="searchAttributes"]')
				WebUI.click(Sinput)
				if(WebUI.waitForElementClickable(Sinput, 10, FailureHandling.CONTINUE_ON_FAILURE)==true)
				{
					//Set Search Keyword
					WebUI.setText(Sinput,attr)
					WebUI.sendKeys(Sinput,Keys.chord(Keys.ENTER))
					(new generalFunc.AllgenralFunc()).shortDelay()

				}
				else
				{
					KeywordUtil.logInfo("Could not find search Input")
					KeywordUtil.markFailed("Could not find search Input")
				}

				//Create an object for attribute name to click on
				TestObject attrN = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//li[@class="attr-name searchAttrCls" and @data-attrname="'+attr+'"]')

				//TestObject attrN = (new generalFunc.AllgenralFunc()).makeTestObject('div', attr, 'wrap-attr-name', '', '', '','')
				//Click on attribute name to expand
				//(new generalFunc.AllgenralFunc()).clickUsingJS(attrN,10)
				WebUI.click(attrN)
				(new generalFunc.AllgenralFunc()).shortDelay()
				//Created object for select all check box and click
				TestObject selAllVals = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//li[contains(@class,"attr-name") and @data-attrname="'+attr+'"]//div[contains(@class,"chk-div")]/label[@class="lbl_cls chkSelAttr_lbl"]')
				(new generalFunc.AllgenralFunc()).clickUsingJS(selAllVals,10)
				(new generalFunc.AllgenralFunc()).shortDelay()
			}
		}else
		{
			KeywordUtil.logInfo("attribute name is blank")
		}

	}//clickOnSelAllVals

	@Keyword
	//Function Created on 04-05-2020
	//Reset Attribute Name

	static resetAttributeNM(String attrNMReset,String nSep)
	{

		if(attrNMReset != ""){

			def mulattrN = attrNMReset.split(nSep)
			int mulattrLen = mulattrN.size()
			KeywordLogger log = new KeywordLogger()
			for(def index :(0..mulattrLen-1))
			{
				String ResetattrNM = mulattrN[index].trim()
				TestObject resetAttribute = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//span[@class="close-icon-sm-red close-attr" and @data-resetattr="'+ResetattrNM+'"]')
				WebUI.click(resetAttribute)

			}
		}else
		{
			KeywordUtil.logInfo("Reset attribute name info  is blank")
		}

	}//resetAttributeNM

	@Keyword
	//Function created on 04-08-20
	//Function to reset particular text tag
	//Requires Semicolon for multiple text tags nSep
	def resetTTag(String rTTag,String nSep)
	{

		if(rTTag != "")
		{

			//Click on Tags Tab
			TestObject tagsTab = (new generalFunc.AllgenralFunc()).makeTestObject('a', '', '', 'tag-tab-anch', 'href', '#tagsTab','')
			(new generalFunc.AllgenralFunc()).clickUsingJS(tagsTab, 10)
			(new generalFunc.AllgenralFunc()).shortDelay()
			//Incase of multiple text tags read one by one using for loop
			def mulTexts = rTTag.split(nSep)
			int mulTextsSize= mulTexts.size()
			for(def index : (0..mulTextsSize-1))
			{
				String mulRTT=mulTexts[index].trim()
				KeywordUtil.logInfo("Click on Reset Text Tag...."+mulRTT)

				//Verify reset text tag clickable or no
				//TestObject resetText = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//div[@class="select2-container select2-container-multi" and @id="s2id_textTagSelect"]//ul//li//div[contains(.,"'+mulRTT+'")]/following-sibling::a[@class="select2-search-choice-close"]')
				TestObject resetText = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//div[contains(.,"'+mulRTT+'")]/following-sibling::a[@class="select2-search-choice-close"]')

				if(WebUI.verifyElementClickable(resetText, FailureHandling.CONTINUE_ON_FAILURE)==true)
				{
					(new generalFunc.AllgenralFunc()).clickUsingJS(resetText, 10)
					(new generalFunc.AllgenralFunc()).shortDelay()
					KeywordUtil.logInfo("Clicked on reset text tag" )
					(new generalFunc.AllgenralFunc()).shortDelay()

				}else
				{
					KeywordUtil.logInfo("Could not click on reset text tag")
				}
			}

		}else
		{
			KeywordUtil.logInfo("Reset text Tag info is not available")
		}

	}//resetTTag()




	@Keyword
	//Function updated on 13-08-20 with the attribute name accessor with the xpath
	//Function Created on 30-04-2020
	//Requires Value/s with semicolon and  coma separated

	static searchAttrNdFlt(String sAttrNM,String attrVals, String nSep, String vSep)
	{

		if(sAttrNM != "")
		{

			def mulSrch = sAttrNM.split(nSep)
			def mulVals =  attrVals.split(nSep)
			int mulattrLen = mulSrch.size()
			KeywordLogger log = new KeywordLogger()
			for(def index :(0..mulattrLen-1))
			{
				String Sname = mulSrch[index].trim()

				//Click on Search Attribute Input
				TestObject Sinput = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//input[@class="form-control searchInput searchAttr" and @id="searchAttributes"]')
				WebUI.click(Sinput)

				//Set Search Keyword
				WebUI.setText(Sinput,Sname)
				WebUI.sendKeys(Sinput,Keys.chord(Keys.ENTER))

				//Expand Filtered Attribute and select values
				TestObject attrN = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//li[@class="attr-name searchAttrCls" and @data-attrname="'+Sname+'"]')
				TestObject attrNCollpse = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//li[@class="attr-name searchAttrCls open-attr-nm" and @data-attrname="'+Sname+'"]')
				//(new generalFunc.AllgenralFunc()).clickUsingJS(attrN,10)
				//(new generalFunc.AllgenralFunc()).shortDelay()
				WebUI.click(attrN)
				(new generalFunc.AllgenralFunc()).shortDelay()
				clickAttrVals(mulVals[index],vSep)
				(new generalFunc.AllgenralFunc()).clickUsingJS(attrNCollpse,10)
				//(new generalFunc.AllgenralFunc()).shortDelay()

				//Remove searched keyword
				TestObject resetSearch = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//i[@class="close-icon-md-red resetAttributeSrch"]')
				WebUI.click(resetSearch)

			}

		}else
		{
			KeywordUtil.logInfo("Search attribute name infor is not available")
		}

	}//searchAttrNdFlt

	@Keyword
	//Function Created on 30-04-2020
	//Requires Value/s with semicolon and  coma separated

	static searchAttrVals(String srchAttrNM,String srchattrVals,String srchvalsStatus, String nSep, String vSep)
	{

		if(srchAttrNM != "")
		{

			def mulSrch = srchAttrNM.split(nSep)
			def mulVals =  srchattrVals.split(nSep)
			def mulStatus =  srchvalsStatus.split(nSep)
			int mulattrLen = mulSrch.size()
			KeywordLogger log = new KeywordLogger()
			for(def index :(0..mulattrLen-1))
			{
				String Sname = mulSrch[index].trim()
				String Svals = mulVals[index].trim()
				String Sstatus = mulStatus[index].trim()

				//Click on Attribute name to expand
				TestObject attrli = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//li[@class="attr-name searchAttrCls open-attr-nm" and @data-attrname="'+Sname+'"]')
				TestObject attrN = (new generalFunc.AllgenralFunc()).makeTestObject('div', Sname, 'wrap-attr-name', '', '', '','')
				if(WebUI.verifyElementPresent(attrli,10,FailureHandling.OPTIONAL))
				{


					//Select or deselect values with in the attribute
					//verify value checked status
					def mulValsSel =  Svals.split(vSep)
					int mulattrLenS = mulValsSel.size()
					//KeywordLogger log = new KeywordLogger()
					for(def index1 :(0..mulattrLenS-1))
					{
						String valsSrchSel = mulValsSel[index].trim()

						//Click on Search Input box with in the attribute
						TestObject SinputwithInAttr = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//li[@class="attr-name searchAttrCls open-attr-nm" and @data-attrname="'+Sname+'"]//ul[@class="attr-val-lst"]//li[@class="searchLi"]//div/input')
						WebUI.click(SinputwithInAttr)

						//Set Search Keyword
						WebUI.setText(SinputwithInAttr,valsSrchSel)
						WebUI.sendKeys(SinputwithInAttr,Keys.chord(Keys.ENTER))


						TestObject attrValSpan = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//li[@class="txt-attr-val" and @data-valof="'+Sname+'" and @data-attrval="'+valsSrchSel+'"]/span[@class="check-span val-selected"]')
						TestObject attrValli = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//li[@class="txt-attr-val" and @data-valof="'+Sname+'" and @data-attrval="'+valsSrchSel+'"]')


						if(Sstatus == "Select")
						{
							if(WebUI.verifyElementPresent(attrValSpan,10,FailureHandling.OPTIONAL))
							{
								KeywordUtil.logInfo("Attribut value already Selected")
							}else
							{
								//Click on value to select
								WebUI.click(attrValli)
								KeywordUtil.logInfo("Attribute value Selected as per given Input")
							}
						}
						else
						{
							if(WebUI.verifyElementPresent(attrValSpan,10))
							{
								KeywordUtil.logInfo("Attribut value is Selected...click on deselect")
								//Click on value to deselect
								WebUI.click(attrValli)
								KeywordUtil.logInfo("Attribute Value deselected now")
							}else
							{

								KeywordUtil.logInfo("Attribute value deSelected already")
							}
						}
					}//for(def index1 :(0..mulattrLen-1))
				}
				else
				{
					//Click on attribute name to expand
					WebUI.click(attrN)
					WebUI.delay(5)
					KeywordUtil.logInfo("Clicked on attribute name to expand")

					//Select or deselect values with in the attribute
					//verify value checked status
					def mulValsSel =  Svals.split(vSep)
					int mulattrLenS = mulValsSel.size()
					//KeywordLogger log = new KeywordLogger()
					for(def indexS :(0..mulattrLenS-1))
					{
						String valsSrchSel = mulValsSel[indexS].trim()

						//Click on Search Input box with in the attribute
						TestObject SinputwithInAttr = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//li[@class="attr-name searchAttrCls open-attr-nm" and @data-attrname="'+Sname+'"]//ul[@class="attr-val-lst"]//li[@class="searchLi"]//div/input')
						WebUI.click(SinputwithInAttr)

						//Set Search Keyword
						WebUI.setText(SinputwithInAttr,valsSrchSel)
						WebUI.sendKeys(SinputwithInAttr,Keys.chord(Keys.ENTER))


						TestObject attrValSpan = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//li[@class="txt-attr-val" and @data-valof="'+Sname+'" and @data-attrval="'+valsSrchSel+'"]/span[@class="check-span val-selected"]')
						TestObject attrValli = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//li[@class="txt-attr-val" and @data-valof="'+Sname+'" and @data-attrval="'+valsSrchSel+'"]')


						if(Sstatus == "Select")
						{
							if(WebUI.verifyElementPresent(attrValSpan,10,FailureHandling.OPTIONAL))
							{
								KeywordUtil.logInfo("Attribut value already Selected")
							}else
							{
								//Click on value to select
								WebUI.click(attrValli)
								KeywordUtil.logInfo("Attribute value Selected as per given Input")
							}
						}
						else
						{
							if(WebUI.verifyElementPresent(attrValSpan,10))
							{
								KeywordUtil.logInfo("Attribut value is Selected...click on deselect")
								//Click on value to deselect
								WebUI.click(attrValli)
								KeywordUtil.logInfo("Attribute Value deselected now")
							}else
							{

								KeywordUtil.logInfo("Attribute value deSelected already")
							}
						}
					}//for(def index1 :(0..mulattrLen-1))
				}

			}

		}

		else
		{
			KeywordUtil.logInfo("Search attribute name infor is not available")
		}

	}//searchAttrVals



	@Keyword
	//Function Created on 30-04-2020
	//Requires Value/s with semicolon and  coma separated

	static deselAttrVals(String SresetAttrNM,String SresetVals, String nSep, String vSep)
	{

		if(SresetAttrNM != "")
		{

			def mulRname = SresetAttrNM.split(nSep)
			def mulRVals =  SresetVals.split(nSep)
			int mulattrLen = mulRname.size()
			KeywordLogger log = new KeywordLogger()
			for(def index :(0..mulattrLen-1))
			{
				String RsetAttrNM = mulRname[index].trim()
				String RsetValNM = mulRVals[index].trim()


				//Expand attribute name

				TestObject attrli = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//li[@class="attr-name searchAttrCls open-attr-nm" and @data-attrname="'+RsetAttrNM+'"]')
				TestObject attrN = (new generalFunc.AllgenralFunc()).makeTestObject('div', RsetAttrNM, 'wrap-attr-name', '', '', '','')
				if(WebUI.verifyElementPresent(attrli,10,FailureHandling.CONTINUE_ON_FAILURE))
				{

					//Verify Value is Checked if it is, then uncheck the values
					//Make an object for values span element and verify checked or no
					def Rvals = RsetValNM.split(vSep)
					int RvalsLen = Rvals.size()
					for(def index1 :(0..RvalsLen-1))
					{
						String attrValsR = Rvals[index].trim()

						//verify value checked status
						TestObject attrValSpan = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//li[@class="txt-attr-val" and @data-valof="'+RsetAttrNM+'" and @data-attrval="'+attrValsR+'"]/span[@class="check-span val-selected"]')
						TestObject attrValli = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//li[@class="txt-attr-val" and @data-valof="'+RsetAttrNM+'" and @data-attrval="'+attrValsR+'"]')
						if(WebUI.verifyElementPresent(attrValSpan,10,FailureHandling.CONTINUE_ON_FAILURE))
						{
							//Deselects values
							WebUI.click(attrValli)
							(new generalFunc.AllgenralFunc()).shortDelay()

						}
						else
						{
							KeywordUtil.logInfo("Attribute values is unchecked / not listed in the attribute")
						}
					}
				}else
				{
					WebUI.click(attrN)
					(new generalFunc.AllgenralFunc()).shortDelay()

					//Verify Value is Checked if it is, then uncheck the values
					//Make an object for values span element and verify checked or no
					def Rvals = RsetValNM.split(vSep)
					int RvalsLen = Rvals.size()
					for(def index1 :(0..RvalsLen-1))
					{
						String attrValsR = Rvals[index].trim()

						//verify value checked status
						TestObject attrValSpan = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//li[@class="txt-attr-val" and @data-valof="'+RsetAttrNM+'" and @data-attrval="'+attrValsR+'"]/span[@class="check-span val-selected"]')
						TestObject attrValli = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//li[@class="txt-attr-val" and @data-valof="'+RsetAttrNM+'" and @data-attrval="'+attrValsR+'"]')
						if(WebUI.verifyElementPresent(attrValSpan,10,FailureHandling.CONTINUE_ON_FAILURE))
						{
							WebUI.click(attrValli)
							(new generalFunc.AllgenralFunc()).shortDelay()

						}
						else
						{
							KeywordUtil.logInfo("Attribute values is unchecked / not listed in the attribute")
						}

					}//for(def index1 :(0..RvalsLen-1))
				}

			}// for(def index :(0..mulattrLen-1))

			TestObject fltApplyBtn = (new generalFunc.AllgenralFunc()).makeTestObject('button', '', 'mainAplyBtn', '', '', '', '')
			WebUI.click(fltApplyBtn)
			WebUI.delay(5)

		}else
		{
			KeywordUtil.logInfo("Search attribute name info is not available")
		}

	}//searchAttrNdFlt

	@Keyword
	//Function Created on 13-05-2020
	//Requires Value/s with semicolon and  coma separated

	static searchMsrNdFlt(String sMsrNM,String minVal,String maxVal, String nSep, String vSep)
	{

		if(minVal != "" & maxVal != "")
		{

			def mulSrch = sMsrNM.split(nSep)
			def mulMinVals = minVal.split(nSep)
			def mulMaxVals = maxVal.split(nSep)
			int mulattrLen = mulSrch.size()
			KeywordLogger log = new KeywordLogger()
			for(def index :(0..mulattrLen-1))
			{
				String Sname = mulSrch[index].trim()

				//Click on Search Attribute Input
				TestObject Sinput = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//input[@class="form-control searchInput searchAttr" and @id="searchAttributes"]')
				//WebUI.click(Sinput)
				(new generalFunc.AllgenralFunc()).clickUsingJS(Sinput,10)
				//Set Search Keyword
				WebUI.setText(Sinput,Sname)
				WebUI.sendKeys(Sinput,Keys.chord(Keys.ENTER))
				(new generalFunc.AllgenralFunc()).shortDelay()

				//Create object for msrname and click
				WebDriver driver = DriverFactory.getWebDriver()
				//TestObject msrN = makeTestObject('div', msr, 'wrap-attr-name', '', '', '','')
				TestObject msrN = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','' ,'//li[contains(@class, "attr-name iamnumber searchAttrCls")  and @data-attrname="'+Sname+'"]//div[@class="wrap-attr-name"]')
				WebElement element = WebUiCommonHelper.findWebElement(msrN,10)
				JavascriptExecutor executor = ((driver) as JavascriptExecutor)
				executor.executeScript('arguments[0].click()', element)
				/*TestObject msrN = makeTestObject('div', msr, 'wrap-attr-name', '', '', '','')
				 WebUI.scrollToElement(msrN, 10)
				 WebUI.click(msrN)*/
				KeywordUtil.logInfo("msr"+Sname+"clicked" )
				WebUI.delay(10)
				String xpathmsrMin = '//li[@class="num-attr-val" and @data-valof="'+Sname+'"]//input[@type="text" and @name="minInput"]'
				KeywordUtil.logInfo(xpathmsrMin)
				//TestObject minValI = makeTestObject('input', '', 'minInput', '', '', '', '')
				TestObject minValI = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', xpathmsrMin)
				if(WebUI.verifyElementClickable(minValI)==true)
				{
					KeywordUtil.logInfo("Input box present")
					WebUI.scrollToElement(minValI,10)

					//KeywordUtil.logInfo('get minvalue'+ minValI)
					WebUI.click(minValI)
					(new generalFunc.AllgenralFunc()).shortDelay()
					String valMin = mulMinVals[index].trim()
					KeywordUtil.logInfo(valMin)
					WebUI.sendKeys(minValI, Keys.chord(Keys.CONTROL, 'a'))
					WebUI.sendKeys(minValI, Keys.chord(Keys.BACK_SPACE))
					WebUI.sendKeys(minValI, valMin)
					WebUI.sendKeys(minValI,Keys.chord(Keys.ENTER))
					(new generalFunc.AllgenralFunc()).shortDelay()
					String xpathmsrMax = '//li[@class="num-attr-val" and @data-valof="'+Sname+'"]//input[@type="text" and @name="maxInput"]'
					KeywordUtil.logInfo(xpathmsrMax)
					//TestObject maxValI = makeTestObject('input', '', 'maxInput', '', '', '', '')
					TestObject maxValI = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', xpathmsrMax)
					WebUI.scrollToElement(maxValI,10)
					String valMax = mulMaxVals[index].trim()
					KeywordUtil.logInfo('Max value'+valMax)
					WebUI.sendKeys(maxValI, Keys.chord(Keys.CONTROL, 'a'))
					WebUI.sendKeys(maxValI, Keys.chord(Keys.BACK_SPACE))
					WebUI.sendKeys(maxValI, valMax)
					WebUI.sendKeys(maxValI,Keys.chord(Keys.ENTER))
					(new generalFunc.AllgenralFunc()).shortDelay()
					//WebUI.click(msrN)
				}
				else
				{
					KeywordUtil.logInfo("Measure Filter details are Invalid")
				}
				//Remove searched keyword
				TestObject resetSearch = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//i[@class="close-icon-md-red resetAttributeSrch"]')
				(new generalFunc.AllgenralFunc()).clickUsingJS(resetSearch,10)
				(new generalFunc.AllgenralFunc()).shortDelay()

			}//for(def index :(0..mulmsrLen-1))

		}
		else
		{
			KeywordUtil.logInfo("Search attribute name infor is not available")
		}

	}//searchMsrNdFlt



	@Keyword
	//Function is to select hiwerarchy attributes and values
	//Requires Hattrnames/s with semicolon separated and values with coma seperated
	//It calls values selection function from here
	static selHAttrNdVals(String hAttrNames,String hAttrVals,  String nSep, String vSep)
	{

		if(hAttrNames != "" ){
			WebDriver driver = DriverFactory.getWebDriver()
			//Create object for hierarchy icon and click on it
			TestObject hIcon = (new generalFunc.AllgenralFunc()).makeTestObject('li', '', 'hierarchy-icon-lg-grey', 'hierarchyBtn', '', '','')
			WebElement element = WebUiCommonHelper.findWebElement(hIcon,10)
			JavascriptExecutor executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click()', element)

			//Select hierarchy icon
			//WebUI.click(findTestObject('Object Repository/Final Objects/MySelection/MainButns/li_Attributes_hierarchyBtn'))
			(new generalFunc.AllgenralFunc()).shortDelay()

			def mulattrN = hAttrNames.split(nSep)
			def mulVals =  hAttrVals.split(nSep)
			int mulattrLen = mulattrN.size()
			KeywordLogger log = new KeywordLogger()
			for(def index :(0..mulattrLen-1))
			{
				String attr = mulattrN[index].trim()

				KeywordUtil.logInfo(attr)
				//Object for attribute collapse mode
				String xpathHattrColl ='//li[@data-attrname="'+attr+'"]//div[@class="wrap-hattr-name"]'
				//String xpathHattrExpnd = '//li[@class="attr-name" and @data-attrname="'+attr+'"]//div[@class="wrap-hattr-name"]'
				TestObject attrNC = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '',xpathHattrColl)
				//TestObject attrNExp = makeTestObject('', '', '', '', '', '',xpathHattrExpnd)
				//TestObject attrNE = makeTestObject('', '', '', '', '', '',xpathHattrExpnd)
				if(WebUI.waitForElementClickable(attrNC, 10, FailureHandling.CONTINUE_ON_FAILURE)==true)
				{
					WebUI.click(attrNC)
				}else
				{
					KeywordUtil.logInfo("Could not find hierarchy attribute")
					KeywordUtil.markFailed("Could not find hierarchy attribute")
				}
				//shortDelay()
				(new generalFunc.AllgenralFunc()).shortDelay()
				clickAttrVals(mulVals[index],vSep)
				WebUI.click(attrNC)

				//TestObject attrN = makeTestObject('div', attr, 'wrap-hattr-name', '', '', '','')
				//WebUI.click(attrN)
				//shortDelay()
				//clickAttrVals(mulVals[index],vSep)
				//WebUI.click(attrN)
			}
			//Click on Hsave button
			TestObject hSave = (new generalFunc.AllgenralFunc()).makeTestObject('button', '', 'btn blueBtn', 'apply-hierar', '', '','')
			(new generalFunc.AllgenralFunc()).clickUsingJS(hSave,10)
			//WebUI.click(findTestObject('Object Repository/Final Objects/MySelection/MainButns/hierarchysave'))
			(new generalFunc.AllgenralFunc()).shortDelay()

		}else
		{
			KeywordUtil.logInfo("Hattribute name is blank")
		}

	}//selHAttrNdVals





	@Keyword
	//Function to select measure filter values
	//Requires Measure name and values with semicolon separator
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
				TestObject msrN = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','' ,'//li[@class="attr-name iamnumber searchAttrCls" and @data-attrname="'+msr+'"]//div[@class="wrap-attr-name"]')
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
				TestObject minValI = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', xpathmsrMin)
				if(WebUI.verifyElementClickable(minValI)==true)
				{
					KeywordUtil.logInfo("Input box present")
					WebUI.scrollToElement(minValI,10)

					//KeywordUtil.logInfo('get minvalue'+ minValI)
					WebUI.click(minValI)
					(new generalFunc.AllgenralFunc()).shortDelay()
					String valMin = mulMinVals[index].trim()
					KeywordUtil.logInfo(valMin)
					WebUI.sendKeys(minValI, Keys.chord(Keys.CONTROL, 'a'))
					WebUI.sendKeys(minValI, Keys.chord(Keys.BACK_SPACE))
					WebUI.sendKeys(minValI, valMin)
					WebUI.sendKeys(minValI,Keys.chord(Keys.ENTER))
					(new generalFunc.AllgenralFunc()).shortDelay()
					String xpathmsrMax = '//li[@class="num-attr-val" and @data-valof="'+msr+'"]//input[@type="text" and @name="maxInput"]'
					KeywordUtil.logInfo(xpathmsrMax)
					//TestObject maxValI = makeTestObject('input', '', 'maxInput', '', '', '', '')
					TestObject maxValI = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', xpathmsrMax)
					WebUI.scrollToElement(maxValI,10)
					String valMax = mulMaxVals[index].trim()
					KeywordUtil.logInfo('Max value'+valMax)
					WebUI.sendKeys(maxValI, Keys.chord(Keys.CONTROL, 'a'))
					WebUI.sendKeys(maxValI, Keys.chord(Keys.BACK_SPACE))
					WebUI.sendKeys(maxValI, valMax)
					WebUI.sendKeys(maxValI,Keys.chord(Keys.ENTER))
					(new generalFunc.AllgenralFunc()).shortDelay()
					//WebUI.click(msrN)
				}
				else
				{
					KeywordUtil.logInfo("Measure Filter details are Invalid")
				}
			}//for(def index :(0..mulmsrLen-1))
		}
		else
		{
			KeywordUtil.logInfo("Measure name is not available")
		}
	}//SetMsrValbyFunc()

	/* *******************Filter by Tags *****************************/


	@Keyword
	//Function updated on 06-10-20 to chk error message for oops went wrong!!
	//Function to click on Tags tab then sets text
	//Requires text tag with semicolon separator
	//Updated on Show Tags behaviour on 10-09-20

	def setTextTag(String TextTag, String nSep)
	{
		if(TextTag != "")
		{

			//Click on Tags Tab
			TestObject tagsTab = (new generalFunc.AllgenralFunc()).makeTestObject('a', '', '', 'tag-tab-anch', 'href', '#tagsTab','')
			(new generalFunc.AllgenralFunc()).clickUsingJS(tagsTab, 10)
			(new generalFunc.AllgenralFunc()).shortDelay()
			//Switch to other text tags-Make object for chk other show tags
			TestObject showTagsinput = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//label[@class="labelCls hideInSmap"]//div[@class="chk-div"]//input')
			TestObject showTagslbl = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//label[@class="labelCls hideInSmap"]//div[@class="chk-div"]//label[@class="lbl_cls chkTag_lbl"]')
			if(WebUI.verifyElementNotChecked(showTagsinput,10,FailureHandling.OPTIONAL)==true)
			{
				//Click on show Tags checkbox
				(new generalFunc.AllgenralFunc()).clickUsingJS(showTagslbl, 10)
				(new generalFunc.AllgenralFunc()).shortDelay()
			}
			else
			{
				KeywordUtil.logInfo("Show other tags checkbox already checked")
			}


			def mulTags = TextTag.split(nSep)
			int lenMul = mulTags.size()
			for (def index : (0..lenMul-1))
			{
				TestObject tagsInput = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','id("s2id_autogen1")')
				WebUI.setText(tagsInput,mulTags[index])
				KeywordUtil.logInfo('TextTag' +mulTags[index])
				(new generalFunc.AllgenralFunc()).shortDelay()
				WebUI.sendKeys(tagsInput,Keys.chord(Keys.ENTER))
				(new generalFunc.AllgenralFunc()).shortDelay()
				KeywordUtil.logInfo("Selected Given Text Tag/s"+tagsInput)
				//Verify Error msg check
				//String errMsg= "Oops! Something went wrong. Please try later"
				if(WebUI.verifyTextPresent("Oops! Something went wrong. Please try later", false, FailureHandling.OPTIONAL)==true)
				{
					TestObject errOKBtn = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//button[@class ="confirm"]')
					WebUI.click(errOKBtn)
					KeywordUtil.logInfo("Clicked on OK button in error message")
				}
				else
				{
					KeywordUtil.logInfo("Error message not displayed...")
				}

			}

			//Make object for click attributes and click to switch Attributes section
			TestObject switchAttrs = (new generalFunc.AllgenralFunc()).makeTestObject('a', 'Attributes', '', 'attr-tab-anch', 'href', '#attributes','')
			(new generalFunc.AllgenralFunc()).clickUsingJS(switchAttrs, 10)

		}else{
			KeywordUtil.logInfo("Text Tag/s info is not available ")
		}

	}//setTextTag



	@Keyword
	//Function updated on 14-10-20 with 2 nd for loop correction
	//Function updated on 07-10-20 with the shoe tags input accessor and updated the color gro accessor class
	//Function updated on 06-10-20 to chk error message for oops went wrong!!
	//Function updated on 17-08-20 for verifying show other tags enabled / disabled then proceed
	//Function Created on 29-04-2020
	//Function to click on Tags tab then sets text
	//Requires text tag with semicolon separator

	def setColorTag(String clrGrpNM,String ColorTag, String nSep, String vSep)
	{
		if(clrGrpNM != "" | ColorTag != "")
		{
			(new generalFunc.AllgenralFunc()).shortDelay()
			//Click on Tags Tab
			(new generalFunc.AllgenralFunc()).clickUsingJS(findTestObject('Final Objects/MySelection/Tags/tagstab'), 10)
			(new generalFunc.AllgenralFunc()).shortDelay()
			def mulTags = ColorTag.split(nSep)
			def mulGrps = clrGrpNM.split(nSep)
			int lenMul = mulTags.size()
			for (def index : (0..lenMul-1))
			{
				String mGrps = mulGrps[index].trim()
				String mTags = mulTags[index].trim()


				//Switch to other text tags-Make object for chk other show tags
				TestObject showTagsinput = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//label[@class="labelCls hideInSmap"]//div[@class="chk-div"]//input')
				//TestObject showTagslbl = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','id("mCSB_11_container")/label[@class="labelCls hideInSmap"]')
				TestObject showTagslbl = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//label[@class="labelCls hideInSmap"]//div[@class="chk-div"]//label[@class="lbl_cls chkTag_lbl"]')
				if(WebUI.verifyElementNotChecked(showTagsinput,10,FailureHandling.OPTIONAL)==true)
				{
					//Click on show Tags checkbox
					(new generalFunc.AllgenralFunc()).clickUsingJS(showTagslbl, 10)
					(new generalFunc.AllgenralFunc()).shortDelay()
				}
				else
				{
					KeywordUtil.logInfo("Show other tags checkbox already checked")
				}


				//Click on Color group to expand
				TestObject colorgrpName = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//li[contains(@class,"colour-grp-name") and @data-clrgrpname="'+mGrps+'"]/div')
				(new generalFunc.AllgenralFunc()).clickUsingJS(colorgrpName, 10)
				KeywordUtil.logInfo("Clicked on color grp..."+mGrps)

				def mulclrtags = mTags.split(vSep)
				int lenMulclr =  mulclrtags.size()
				for (def index1 : (0..lenMulclr-1))
				{
					String ClrTagsM = mulclrtags[index1].trim()

					//Click on Color as per the given input
					TestObject CLRgrpName = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//li[@class="colour-tag-li" and @data-clrnnm="'+ClrTagsM+'"]/span')
					(new generalFunc.AllgenralFunc()).clickUsingJS(CLRgrpName, 10)
					KeywordUtil.logInfo("Clicked on color tag..."+ClrTagsM)
					//Verify Error msg check
					/*
					 //String errMsg= "Oops! Something went wrong. Please try later"
					 if(WebUI.verifyTextPresent("Oops! Something went wrong. Please try later", false, FailureHandling.OPTIONAL)==true)
					 {
					 TestObject errOKBtn = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//button[@class ="confirm"]')
					 WebUI.click(errOKBtn)
					 KeywordUtil.logInfo("Clicked on OK button in error message")
					 }
					 else
					 {
					 KeywordUtil.logInfo("Error message not displayed...")
					 }
					 */
				}
				//Collapse the color group
				(new generalFunc.AllgenralFunc()).clickUsingJS(colorgrpName, 10)
				KeywordUtil.logInfo("Collapsed the color group..."+mGrps)


			}

			//Make object for click attributes and click to switch Attributes section
			TestObject switchAttrs = (new generalFunc.AllgenralFunc()).makeTestObject('a', 'Attributes', '', 'attr-tab-anch', 'href', '#attributes','')
			(new generalFunc.AllgenralFunc()).clickUsingJS(switchAttrs, 10)

		}
		else{
			KeywordUtil.logInfo("Text Tag/s info is not available ")
		}

	}//setColorTag












	@Keyword
	//Function Created on 3-8-2020
	//Select multiple colors
	def chooseClr(String clrName,String vSep)
	{
		def mulclrtags = clrName.split(vSep)
		int mulclrtagslen = mulclrtags.size()
		for (def index : (0..mulclrtagslen-1))
		{
			String ClrTagsM = mulclrtags[index].trim()
			KeywordUtil.logInfo("color name"+ClrTagsM)


			//Click on Colorname as per the given input
			TestObject CLRgrpName = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//li[@class="colour-tag-li" and @data-clrnnm="'+ClrTagsM+'"]/span')
			(new generalFunc.AllgenralFunc()).clickUsingJS(CLRgrpName, 10)
			KeywordUtil.logInfo("Clicked on Color tag"+ClrTagsM)
			(new generalFunc.AllgenralFunc()).shortDelay()
		}
	}//chooseClr


	@Keyword
	//Function Created on 30-04-2020
	//Function to click on Tags tab then sets Icon
	//Requires text tag with semicolon separator

	def setIconTag(String iconGrpNM,String IconTag, String nSep, String vSep)
	{
		if(iconGrpNM != "" | IconTag != "")
		{
			(new generalFunc.AllgenralFunc()).shortDelay()
			//Click on Tags Tab
			(new generalFunc.AllgenralFunc()).clickUsingJS(findTestObject('Final Objects/MySelection/Tags/tagstab'), 10)
			(new generalFunc.AllgenralFunc()).shortDelay()
			def mulTags = IconTag.split(nSep)
			def mulGrps = iconGrpNM.split(nSep)
			int lenMul = mulTags.size()
			for (def index : (0..lenMul-1))
			{
				String mGrps = mulGrps[index].trim()
				String mTags = mulTags[index].trim()

				//Switch to other Icon tags-Make object for chk other show tags
				TestObject showTags = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','id("mCSB_11_container")/label[@class="labelCls hideInSmap"]')
				(new generalFunc.AllgenralFunc()).clickUsingJS(showTags, 10)
				(new generalFunc.AllgenralFunc()).shortDelay()

				//Click on Icon group to expand
				TestObject icongrpName = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//li[@class="icon-grp-name attrNamBotmBrdr" and @data-icngrpname="'+mGrps+'"]/div')
				(new generalFunc.AllgenralFunc()).clickUsingJS(icongrpName, 10)
				def mulicntags = mTags.split(vSep)
				for (def index1 : (0..lenMul-1))
				{
					String IconTagsM = mulicntags[index].trim()

					//Click on icon as per the given input
					TestObject iconTag = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//li[@class="icon-tag-li" and @data-icnnm="'+IconTagsM+'"]/span')
					(new generalFunc.AllgenralFunc()).clickUsingJS(iconTag, 10)
				}

			}

			//Make object for click attributes and click to switch Attributes section
			TestObject switchAttrs = (new generalFunc.AllgenralFunc()).makeTestObject('a', 'Attributes', '', 'attr-tab-anch', 'href', '#attributes','')
			(new generalFunc.AllgenralFunc()).clickUsingJS(switchAttrs, 10)

		}
		else{
			KeywordUtil.logInfo("Icon Tag/s info is not available ")
		}

	}//setIconTag





	/* *******************End of  Filter by Tags *****************************/

	/*   *****************Group By attributes and Tags ***************        */

	@Keyword
	//Function applies grouping on the attributes
	//Requires attribute name with semi colon separated

	static clickGrpAttrs(String groupByAttrName,  String nSep)
	{
		if(groupByAttrName != "" )
		{
			KeywordUtil.logInfo("groupByAttrName is available")
			WebDriver driver = DriverFactory.getWebDriver()
			TestObject resCtrlMenu = (new generalFunc.AllgenralFunc()).makeTestObject('i', '', '', '', '', '','id("limitSetBtn")/i[@class="setting-icon-lg-grey"]')
			WebElement element = WebUiCommonHelper.findWebElement(resCtrlMenu,10)
			JavascriptExecutor executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click()', element)
			(new generalFunc.AllgenralFunc()).shortDelay()
			def mulAttrs = groupByAttrName.split(nSep)
			int lenMul = mulAttrs.size()
			for (def index : (0..lenMul-1))
			{
				String grpName = 'chkAttr'+mulAttrs[index]
				KeywordUtil.logInfo(grpName)
				TestObject GattrS = (new generalFunc.AllgenralFunc()).makeTestObject('label', '', 'lbl_cls', '', 'for',grpName ,'')
				(new generalFunc.AllgenralFunc()).shortDelay()
				if(WebUI.verifyElementClickable(GattrS,FailureHandling.CONTINUE_ON_FAILURE))
				{
					(new generalFunc.AllgenralFunc()).clickUsingJS(GattrS,10)
				}
				else
				{
					KeywordUtil.logInfo("Could not click on the object")
				}
			}
			//WebUI.click(resCtrlMenu)
			executor.executeScript('arguments[0].click()', element)
		}else{
			KeywordUtil.logInfo("Attribute is not available to group")
		}

	}//clickGrpAttrs

	@Keyword
	//Function to group by Text Tags
	//Modified Function name from clickGrpTags to clickGrpTextTags on 22-07-2020
	//Also modified argument to  grpByTextTag on 22-07-2020
	static clickGrpTextTags(String grpByTextTag)
	{
		if(grpByTextTag == "Yes")
		{
			//Click on Result Control Menu
			WebDriver driver = DriverFactory.getWebDriver()
			TestObject resCtrlMenu = (new generalFunc.AllgenralFunc()).makeTestObject('i', '', '', '', '', '','id("limitSetBtn")/i[@class="setting-icon-lg-grey"]')
			WebElement element = WebUiCommonHelper.findWebElement(resCtrlMenu,10)
			JavascriptExecutor executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click()', element)

			//Click on Grp Text Tag
			String grpTextTag = 'chkAttrText'
			TestObject GtextTag = (new generalFunc.AllgenralFunc()).makeTestObject('label', '', 'lbl_cls chkTag_lbl', '', 'for',grpTextTag ,'')
			if(WebUI.verifyElementClickable(GtextTag,FailureHandling.CONTINUE_ON_FAILURE))
			{
				(new generalFunc.AllgenralFunc()).clickUsingJS(GtextTag,10)
				executor.executeScript('arguments[0].click()', element)
				KeywordUtil.logInfo("Clicked on group by text tag")

			}
			else
			{
				KeywordUtil.logInfo("Could not click on the grpTextTag object")
			}

		}else{
			KeywordUtil.logInfo("Text tag Group info is not available")
		}
	}//clickGrpTextTags
	@Keyword
	//Group by color tags
	//Created  on 22-07-2020
	static clickGrpClrTags(String grpByClrTag)
	{
		if(grpByClrTag == "Yes")
		{
			//Click on Result Control Menu
			WebDriver driver = DriverFactory.getWebDriver()
			TestObject resCtrlMenu = (new generalFunc.AllgenralFunc()).makeTestObject('i', '', '', '', '', '','id("limitSetBtn")/i[@class="setting-icon-lg-grey"]')
			WebElement element = WebUiCommonHelper.findWebElement(resCtrlMenu,10)
			JavascriptExecutor executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click()', element)

			//Click on Grp Color Tag
			String grpColourTag = 'chkAttrColour'
			TestObject GclrTag = (new generalFunc.AllgenralFunc()).makeTestObject('label', '', 'lbl_cls chkTag_lbl', '', 'for',grpColourTag ,'')
			if(WebUI.verifyElementClickable(GclrTag,FailureHandling.CONTINUE_ON_FAILURE))
			{
				(new generalFunc.AllgenralFunc()).clickUsingJS(GclrTag,10)
				executor.executeScript('arguments[0].click()', element)
				KeywordUtil.logInfo("Clicked on group by color")

			}
			else
			{
				KeywordUtil.logInfo("Could not click on the grpColourTag object")
			}

		}else{
			KeywordUtil.logInfo("Colour tag group info is not available")
		}

	}//clickGrpClrTags
	/* ******************End of Group By attributes and Tags ****************** */

	/*  **************************** Sort and Stats Functions**************************  */
	@Keyword
	//Done button in sort and stats window
	//Function Requires sortAttrname,Stats Attrname
	//Function calls only when arguments are not null
	def sortDone(String sortAttrName, String statsAttrName)
	{
		if(sortAttrName != "" | statsAttrName != ""){
			(new generalFunc.AllgenralFunc()).clickUsingJS(findTestObject('Final Objects/MySelection/MainButns/button_Done'),
					10)
		}else{
			KeywordUtil.logInfo("Sort or Stats attribute name info is not available")
		}


	}
	//Created on 6-April-2020
	//To call directly in sort and stats functions
	@Keyword
	def doneSortBtn()
	{

		(new generalFunc.AllgenralFunc()).clickUsingJS(findTestObject('Final Objects/MySelection/MainButns/button_Done'),10)
	}//doneSortBtn


	@Keyword
	//Main function to call in the test case
	//Function requires attribute name with semi colon separated ans status with coma sseparated
	//Also calls sort order function inside this method
	static doSort(String sortAttrName, String attrStatus, String nSep, String vSep)
	{
		if(sortAttrName != "")
		{
			//Click on Sort Icon
			WebDriver driver = DriverFactory.getWebDriver()
			TestObject sorticon = (new generalFunc.AllgenralFunc()).makeTestObject('li', '', 'globstatsetting-icon-lg-grey', 'statSortBtn', '', '','')
			WebElement element = WebUiCommonHelper.findWebElement(sorticon,10)
			JavascriptExecutor executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click()', element)
			(new generalFunc.AllgenralFunc()).shortDelay()
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
	//Main function to call in test cases to do stats
	//Requires Attributename/s with semicolon separator
	static doStats(String statsAttrName, String nSep)
	{
		if(statsAttrName != "")
		{
			//Click on Sort Icon
			WebDriver driver = DriverFactory.getWebDriver()
			TestObject sorticon = (new generalFunc.AllgenralFunc()).makeTestObject('li', '', 'globstatsetting-icon-lg-grey', 'statSortBtn', '', '','')
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
				TestObject msrST = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', whChkd)

				if((WebUI.verifyElementChecked(msrST,10,FailureHandling.OPTIONAL))== true){
					log.logInfo("Stats is already set for attribute")
				}
				else
				{
					TestObject msrSTL = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '',woChkd)
					//WebUI.click(msrSTL)
					(new generalFunc.AllgenralFunc()).clickUsingJS(msrSTL,10)
					(new generalFunc.AllgenralFunc()).shortDelay()
					KeywordUtil.logInfo("Stats Checked for the attribute now")
				}
			}
			WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_Done'))
		}else{
			KeywordUtil.logInfo("Stats Attribute info  is not available")
		}


	}//doStats

	@Keyword
	//Sub function for sort which called in tDosort function to set the sort order
	//Requires Attribute name with semicolon separator, status with coma separator
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
			TestObject sortAttr = (new generalFunc.AllgenralFunc()).makeTestObject('span', '', attrClass, '', 'data-sort', sortAttrName,'')
			TestObject stDisable = (new generalFunc.AllgenralFunc()).makeTestObject('span', '', 'sort-icon-sm-darkgrey', '', 'data-sort', sortAttrName,'')
			TestObject stAscend = (new generalFunc.AllgenralFunc()).makeTestObject('span', '', 'sort-icon-sm-blue', '', 'data-sort', sortAttrName,'')
			TestObject stDescend = (new generalFunc.AllgenralFunc()).makeTestObject('span', '', 'descend', '', 'data-sort', sortAttrName,'')
			//to do Ascend
			if(attrStatus == 'Ascend')
			{
				//SortOrderAscend(stDisable,stAscend,stDescend)
				//To do Ascend
				if ((WebUI.verifyElementPresent(stAscend, 10, FailureHandling.OPTIONAL)) == true) {
					FailureHandling.CONTINUE_ON_FAILURE({
						//clickUsingJS(findTestObject('Final Objects/MySelection/MainButns/button_Done'),
						//		10)
						KeywordUtil.logInfo("It's already in Ascend Order")
						(new generalFunc.AllgenralFunc()).shortDelay()
					})
				} else if ((WebUI.verifyElementPresent(stDescend, 10, FailureHandling.OPTIONAL)) == true) {
					FailureHandling.CONTINUE_ON_FAILURE({
						WebUI.click(stDescend)
						WebUI.click(stDisable)
						(new generalFunc.AllgenralFunc()).shortDelay()
						(new generalFunc.AllgenralFunc()).shortDelay()
						//WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_Done'))
					})
				} else {
					WebUI.click(stDisable)
					(new generalFunc.AllgenralFunc()).shortDelay()
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
					KeywordUtil.logInfo("It's already in Descend Status")
					(new generalFunc.AllgenralFunc()).shortDelay()
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
					KeywordUtil.logInfo("It's already in Disable Status")
					(new generalFunc.AllgenralFunc()).shortDelay()

				} else if ((WebUI.verifyElementPresent(stAscend, 10, FailureHandling.OPTIONAL)) == true) {

					WebUI.click(stAscend)
					WebUI.click(stDescend)
					//WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_Done'))

				} else {
					WebUI.click(stDescend)
					(new generalFunc.AllgenralFunc()).shortDelay()
					//WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_Done'))
				}
			}



		}



	}//mDoSort


	@Keyword
	//Group sort function Created on 6-April-2020
	//Click on groupsortTab and set aggregation call another sub function
	//Requires attrnames and values with semicolon and status with coma separated
	def DogroupSort(String grpsortAttrName, String grpattrStatus, String aggrVal,String vSep,String nSep)
	{
		if(grpsortAttrName!="")
		{
			//Call Stats Function
			String statAttr = "'"+grpsortAttrName+"'"
			doStats(statAttr,nSep)
			//Click on Sort Icon
			WebDriver driver = DriverFactory.getWebDriver()
			TestObject sorticon = (new generalFunc.AllgenralFunc()).makeTestObject('li', '', 'globstatsetting-icon-lg-grey', 'statSortBtn', '', '','')
			WebElement element = WebUiCommonHelper.findWebElement(sorticon,10)
			JavascriptExecutor executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click()', element)
			(new generalFunc.AllgenralFunc()).shortDelay()

			//Create and click on groupsort object
			TestObject grpSortTab = (new generalFunc.AllgenralFunc()).makeTestObject('a', '', '', 'groupsorttab', '', '','')
			WebUI.click(grpSortTab)

			//set sort order in groupsort
			(new generalFunc.AllgenralFunc()).shortDelay()
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
				grpSortAggr(mulMsrSort[index],mulMsrAggr[index])
				//KeywordUtil.logInfo(mulMsrAggr[index]))

			}

			//Click on sort done button
			WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_Done'))
		}
		else
		{
			KeywordUtil.logInfo("Group sort info is blank")
		}

	}//DogroupSort()
	@Keyword
	//Requires attrnames with semicolon and status with coma separated
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
			TestObject grpsortAttr = (new generalFunc.AllgenralFunc()).makeTestObject('span', '', attrClass, '', 'data-grpsort', grpsortAttrName,'')
			TestObject grpstDisable = (new generalFunc.AllgenralFunc()).makeTestObject('span', '', 'sort-icon-sm-darkgrey', '', 'data-grpsort', grpsortAttrName,'')
			TestObject grpstAscend = (new generalFunc.AllgenralFunc()).makeTestObject('span', '', 'sort-icon-sm-blue', '', 'data-grpsort', grpsortAttrName,'')
			TestObject grpstDescend = (new generalFunc.AllgenralFunc()).makeTestObject('span', '', 'descend', '', 'data-grpsort', grpsortAttrName,'')
			//to do Ascend
			if(grpattrStatus == 'Ascend')
			{
				//SortOrderAscend(stDisable,stAscend,stDescend)
				//To do Ascend
				if ((WebUI.verifyElementPresent(grpstAscend, 10, FailureHandling.OPTIONAL)) == true) {
					FailureHandling.CONTINUE_ON_FAILURE({
						//clickUsingJS(findTestObject('Final Objects/MySelection/MainButns/button_Done'),
						//		10)
						KeywordUtil.logInfo("It's in Ascend Status")
						(new generalFunc.AllgenralFunc()).shortDelay()
					})
				} else if ((WebUI.verifyElementPresent(grpstDescend, 10, FailureHandling.OPTIONAL)) == true) {
					FailureHandling.CONTINUE_ON_FAILURE({
						WebUI.click(grpstDescend)
						WebUI.click(grpstDisable)
						//WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_Done'))
					})
				} else {
					WebUI.click(grpstDisable)
					(new generalFunc.AllgenralFunc()).shortDelay()
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
					KeywordUtil.logInfo("It's already in Descend Status")
					(new generalFunc.AllgenralFunc()).shortDelay()
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
					KeywordUtil.logInfo("It's already in Disable Status Only")
					(new generalFunc.AllgenralFunc()).shortDelay()
				} else if ((WebUI.verifyElementPresent(grpstAscend, 10, FailureHandling.OPTIONAL)) == true) {

					WebUI.click(grpstAscend)
					WebUI.click(grpstDescend)
					//WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_Done'))

				} else {
					WebUI.click(grpstDescend)
					(new generalFunc.AllgenralFunc()).shortDelay()
					//WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_Done'))
				}
			}



		}



	}//groupmDoSort

	@Keyword
	//Requires aggrname and val
	def grpSortAggr(String grpsortAttrName,String aggrVal)
	{
		KeywordUtil.logInfo("Aggregation value"+aggrVal)
		//Click on aggregation dropdown
		TestObject clickOnAggrDropDown = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//li[@data-gsattrname="'+grpsortAttrName+'" and @class="gsortli gsortLiVisible oddBackGrnd_li"]//span[@class="select2-chosen"]')
		WebUI.click(clickOnAggrDropDown)
		(new generalFunc.AllgenralFunc()).shortDelay()
		//select aggregation
		KeywordUtil.logInfo(aggrVal)

		TestObject aggregationdd = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//ul[@class="select2-results"]//div[contains(text(),"'+aggrVal+'")]')
		//WebUI.selectOptionByValue(aggregationdd,aggrVal,false)
		//WebUI.selectOptionByValue(clickOnAggrDropDown, aggrVal, false)
		WebUI.click(aggregationdd)


	}//grpSortAggr()



	/*  *******************************  End of Sort and Stats  Functions************************  */

	/*  *******************************    ItemLimit Functions *******************************   */
	@Keyword
	//Clicks on result control menu and set limit
	//Requires number argument to set the limit
	def setItemLimit(def limit)
	{
		if(limit > '0')
		{

			KeywordUtil.logInfo("Item limit is available")
			WebDriver driver = DriverFactory.getWebDriver()
			TestObject resCtrlMenu = (new generalFunc.AllgenralFunc()).makeTestObject('i', '', '', '', '', '','id("limitSetBtn")/i[@class="setting-icon-lg-grey"]')
			WebElement element = WebUiCommonHelper.findWebElement(resCtrlMenu,10)
			JavascriptExecutor executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click()', element)


			//TestObject resCtrlMenu = makeTestObject('i', '', '', '', '', '','id("limitSetBtn")/i[@class="setting-icon-lg-grey"]')
			//WebUI.click(resCtrlMenu)
			//WebUI.delay(5)
			//Click on Item Limit text box
			(new generalFunc.AllgenralFunc()).clickUsingJS(findTestObject('Object Repository/Final Objects/MySelection/MainButns/i_set-limit'),10)
			(new generalFunc.AllgenralFunc()).shortDelay()
			//Set Limit as 10
			TestObject itemLimitInput = (new generalFunc.AllgenralFunc()).makeTestObject('input', '', '', 'first-n', '', '','')
			WebUI.setText(itemLimitInput, limit)
			WebUI.sendKeys(itemLimitInput,Keys.chord(Keys.ENTER) )
			KeywordUtil.logInfo("Item limit applied now")


		}else{
			KeywordUtil.logInfo("Item limit is not available")
		}
	}//setItemLimit()

	/*  ****************************** End of ItemLimit Functions ******************************* */

	/*  *******************************     Show Data Functions   *******************************  */
	@Keyword
	//Click on Show button in show data window
	def enableShowData()
	{
		//Enable Show data
		TestObject enableSD = (new generalFunc.AllgenralFunc()).makeTestObject('label', '', 'newtoggleLbl', '', 'data-smvalue', 'showm', '')
		WebUI.click(enableSD)
		(new generalFunc.AllgenralFunc()).shortDelay()

	}//enableShowData()


	@Keyword
	//Click on 'Hide' button in show data window
	def enableHideData()
	{

		//Enable Hide data



	}//enableHideData()

	@Keyword
	//Function sets the show data as per the inputs
	//Requires attrname with semicolon separator
	static doShowData(String showDataAttrName, String nSep)
	{
		if(showDataAttrName != "")
		{
			//WebUI.delay(5)
			//Showdata
			WebDriver driver = DriverFactory.getWebDriver()
			TestObject showData = (new generalFunc.AllgenralFunc()).makeTestObject('li', '', 'showmeasure-icon-xl-grey', 'showMeasrBtn', '', '','')
			WebElement element = WebUiCommonHelper.findWebElement(showData,10)
			JavascriptExecutor executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click()', element)

			//clickUsingJS(showData, 10)
			//WebUI.scrollToElement(showData, 10)
			//WebUI.click(showData)
			//Enable showdata
			TestObject enableSD = (new generalFunc.AllgenralFunc()).makeTestObject('label', '', 'newtoggleLbl', '', 'data-smvalue', 'showm', '')
			WebUI.click(enableSD)
			(new generalFunc.AllgenralFunc()).shortDelay()
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
				TestObject msrSD = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '',valwchk)

				log.logInfo(valwochk)
				log.logInfo(valwchk)
				if((WebUI.verifyElementChecked(msrSD,10,FailureHandling.OPTIONAL))== true){
					KeywordUtil.logInfo("Attribute already enabled for ShowData")

					//WebUI.click(findTestObject('Object Repository/Final Objects/MySelection/MainButns/Page_SLICeR Kanvas - My Selection/button_Done_close close-icon-lg-blue'))
					//WebUI.delay(5)
				}else
				{
					TestObject msrSDL = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', valwochk)
					KeywordUtil.logInfo("Click on Check box to Enable Show Data")
					WebUI.scrollToElement(msrSDL,10)
					(new generalFunc.AllgenralFunc()).clickUsingJS(msrSDL,10)
					//WebUI.scrollToElement(msrSDL, 10)
					//WebUI.click(msrSDL)
					(new generalFunc.AllgenralFunc()).shortDelay()
					done = true
					//saveShowData()
					//WebUI.delay(5)
					//WebUI.click(findTestObject('Final Objects/MySelection/MainButns/SaveNdApplyShowdata'))
				}
			}

			if(done==true)
			{
				//saveShowData()
				(new generalFunc.AllgenralFunc()).shortDelay()
				WebUI.click(findTestObject('Final Objects/MySelection/MainButns/SaveNdApplyShowdata'))
			}else
			{

				WebUI.click(findTestObject('Object Repository/Final Objects/MySelection/MainButns/Page_SLICeR Kanvas - My Selection/button_Done_close close-icon-lg-blue'))
				(new generalFunc.AllgenralFunc()).shortDelay()
			}


		}else{
			KeywordUtil.logInfo("Showdata Attribute Info is not available ")
		}

	}//doShowData
	@Keyword
	static saveShowData()
	{

		WebUI.click(findTestObject('Final Objects/MySelection/MainButns/SaveNdApplyShowdata'))
		(new generalFunc.AllgenralFunc()).shortDelay()

	}//saveShowData

	@Keyword
	//Updated on 26-08-20 with all bug fixes for multiple value reading
	//Created on 24-08-20
	//Compare show tags values
	//def compareShowData(String compareSD,String SDitemId)
	def compareShowData(String sdItemID,String sdVals,String nSep,String vSep)
	{
		if(sdItemID!="")
		{
			def multIDs = sdItemID.split(nSep)
			def multVals = sdVals.split(nSep)
			int lenIdsMul = multIDs.size()
			for(def index :(0..lenIdsMul-1))
			{
				String sdItemid = multIDs[index].trim()
				String sdValsmul = multVals[index].trim()
				KeywordUtil.logInfo(sdItemid+"...."+sdValsmul)
				//Create object for given item id and scroll to the element
				TestObject itemIdObj = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//h6[@data-imgsku="'+sdItemid+'"]/../preceding-sibling::a[@class="abvthumbnail"]')
				WebUI.scrollToElement(itemIdObj, 10)
				//Check for multiple SD values for the same item
				def sameIdmulVals = sdValsmul.split(vSep)
				int lenSameIdmulVals = sameIdmulVals.size()
				for(def index1 :(0..lenSameIdmulVals-1))
				{
					String sameidMVals = sameIdmulVals[index1].trim()
					KeywordUtil.logInfo("same id"+sameidMVals)
					//TestObject sdLiObj = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//h6[@data-imgsku="'+sdItemid+'"]//following-sibling::div[@class="showmh6div showthis"]//h6[contains(@class,"nomargin")]')
					TestObject sdLiObj = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//h6[@data-imgsku="'+sdItemid+'"]//following-sibling::div[@class="showmh6div showthis"]/h6[@title="'+sameidMVals+'"]')
					if(WebUI.verifyElementPresent(sdLiObj,10,FailureHandling.CONTINUE_ON_FAILURE)==true)
					{

						KeywordUtil.logInfo("expected value presents in Show data")
					}else
					{
						KeywordUtil.logInfo("expected value not present in Show data")
					}

				}

			}
		}
		else
		{
			KeywordUtil.logInfo("Could not find Show data for the given Item ID")
		}



	}//compareShowData




	/*  *******************************  End of Show Data Functions *******************************  */

	/* *******************************     Hierarchy Functions       ****************************** */

	@Keyword
	//Hierarchy Button in My selection
	def HierarchyBtn(){
		//Click on hierarchy
		WebUI.click(findTestObject('Object Repository/Final objects/MySelection/MainButns/li_Attributes_hierarchyBtn'))
		(new generalFunc.AllgenralFunc()).shortDelay()
	}//HierarchyBtn
	@Keyword
	//Save button in hierarchy window
	def HierarchySave(){
		//Click on Save button in hierarchy window
		WebUI.click(findTestObject('Object Repository/Final objects/MySelection/MainButns/button_Save'))
		(new generalFunc.AllgenralFunc()).shortDelay()
	}//HierarchySave

	/* *****************************************  End of Hierarchy Functions  **************************************** */

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
			(new generalFunc.AllgenralFunc()).shortDelay()
			String actCntStr = actCnt[0] + ',' + actCnt[2]

			if(expected == actCntStr)
			{

				(new generalFunc.AllgenralFunc()).shortDelay()
				//log.logInfo('Both actual and expected items are matched  '+actual)
				KeywordUtil.markPassed('SUCCESS: Both actual and expected items are matched....and the actual and expeted item count is  '+ actCntStr+'  '+ expected)

			}
			else
			{
				(new generalFunc.AllgenralFunc()).shortDelay()
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

				(new generalFunc.AllgenralFunc()).shortDelay()
				//log.logInfo('Both actual and expected items are matched  '+actual)
				KeywordUtil.markPassed('SUCCESS: Both actual and expected items are matched....and the actual and expeted item count is  '+ actual+'  '+ expected)

			}
			else
			{
				(new generalFunc.AllgenralFunc()).shortDelay()
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
	def Stndardcnt() //gets actual standard item count
	{

		//Switch to standard
		WebUI.click(findTestObject('Object Repository/Final Objects/MySelection/MainButns/a_Standard'))
		(new generalFunc.AllgenralFunc()).longDelay()
		//Get Item count from Standard
		//JString is an another method to convert data types
		//JString stndardcnt = WebUI.getText(findTestObject('Final Objects/Validation Objects/Actual/ActualItemCnt'))
		def stndardcnt = WebUI.getText(findTestObject('Final Objects/Validation Objects/Actual/ActualItemCnt'))
		(new generalFunc.AllgenralFunc()).longDelay()

		//Set to string variable
		//JString partName = stndardcnt.split(/\s/)[0]
		def partName = stndardcnt.split(/\s/)[0]
		//Convert string to integer
		int intPartName = Integer.parseInt(partName)
		//WebUI.comment('Total no.of item count in Standard Mode'+intPartName)
		(new generalFunc.AllgenralFunc()).shortDelay()
		return intPartName
	}//stndardcnt()

	@Keyword
	def tabVsStdcnt() //Compares tabular and standard item count
	{
		//Assign tabular count to variable
		int tabcnt = (new tabularFunc.AlltabularFunc()).TabularItemCount()
		//Assign standard count to variable
		int stdcnt = Stndardcnt()
		if(tabcnt == stdcnt)
		{
			(new generalFunc.AllgenralFunc()).longDelay()
			KeywordUtil.markPassed('SUCCESS: Both Tabular and Standard items are matched '+stdcnt)
		}
		else
		{
			(new generalFunc.AllgenralFunc()).longDelay()
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
	//Requires 1 argument with the workspace name
	def saveWS(def workspaceNameS){
		//WebUI.click(findTestObject('Object Repository/Final objects/MySelection/MainButns/span_Welcome kat4cid008.com_ca'))
		//Click on user dropdown menu
		if(workspaceNameS != '')
		{
			(new generalFunc.AllgenralFunc()).clickUserLoginDropDown()

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
				TestObject btnYes = (new generalFunc.AllgenralFunc()).makeTestObject('button', '', 'dialogboot', '', 'data-bb-handler', 'confirm', '')
				KeywordUtil.logInfo("yes button check")

				WebUI.click(btnYes)
				(new generalFunc.AllgenralFunc()).shortDelay
				WebUI.click(findTestObject('Final Objects/MySelection/MainButns/button_OK'))

			}

		}
		else{

			KeywordUtil.markPassed("Workspace Save info is blank")
		}
	}//saveWS

	@Keyword
	//Created on 07-08-20
	//Updated on 10-08-20
	//Save workspace and check if name already exists proceed
	def SaveWorkspace(def wsName,String keepShareStngs){
		if(wsName != '')
		{
			//WebUI.click(findTestObject('Object Repository/Final objects/MySelection/MainButns/span_Welcome kat4cid008.com_ca'))
			//Call function to Click on user context menu
			(new generalFunc.AllgenralFunc()).clickUserLoginDropDown()
			(new generalFunc.AllgenralFunc()).shortDelay()
			//Create object for save workspace option then click on it
			TestObject saveWS = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','', '//a[@id="svwrkmodal"]')
			(new generalFunc.AllgenralFunc()).clickUsingJS(saveWS,10)
			(new generalFunc.AllgenralFunc()).shortDelay()
			KeywordUtil.logInfo("Clicked on save workspace option")
			//Create object for setTextbox and setName

			TestObject wsInputNM = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','', '//input[@id="wrkSpcNm" and @placeholder="Workspace name"]')
			(new generalFunc.AllgenralFunc()).clickUsingJS(wsInputNM,10)
			WebUI.setText(wsInputNM, wsName)
			KeywordUtil.logInfo("Entered Workspace Name")
			//Check for share settings then click on else continue
			if(keepShareStngs=="Yes")
			{
				//Create object for check box then click on it
				TestObject keepSSinput = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','', '//div[@id="sharesetting"]//input[@id="inheritChk"]')
				TestObject keepSSlbl = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','', '//div[@id="sharesetting"]//label[@class="lbl_cls chkTag_lbl"]')
				//Verify input not checked then click it
				if(WebUI.verifyElementNotChecked(keepSSinput, 10, FailureHandling.CONTINUE_ON_FAILURE))
				{
					(new generalFunc.AllgenralFunc()).clickUsingJS(keepSSlbl,10)
					KeywordUtil.logInfo("Clicked on share settings option")
				}
				else
				{
					KeywordUtil.logInfo("Could not click on  Share settings option ")
				}
			}
			else
			{
				KeywordUtil.logInfo("Keep share settings info is blank")
			}

			//Create object for Save button and then click
			TestObject saveWSbtn = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','', '//button[@id="savework" and @class="btn blueBtn"]')
			(new generalFunc.AllgenralFunc()).clickUsingJS(saveWSbtn,10)
			KeywordUtil.logInfo("Clicked on Save Workspace")
			(new generalFunc.AllgenralFunc()).shortDelay()
			//Create object for 'OK' button and then click
			//button[@class="confirm"]
			TestObject okBtn = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','', '//button[@class="confirm"]')
			if(WebUI.verifyElementPresent(okBtn,10 ,FailureHandling.OPTIONAL)){
				(new generalFunc.AllgenralFunc()).clickUsingJS(okBtn,10)
				KeywordUtil.logInfo("Clicked on OK button after save workspace")
			}
			else
			{
				//Create object for 'Yes' btn in overwrite msg window
				TestObject yesBtn = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','', '//button[@data-bb-handler="confirm"]')
				(new generalFunc.AllgenralFunc()).clickUsingJS(yesBtn,10)
				KeywordUtil.logInfo("Clicked on Yes on overwrite msg")
				(new generalFunc.AllgenralFunc()).shortDelay()
				//Click on OK button
				(new generalFunc.AllgenralFunc()).clickUsingJS(okBtn,10)
				KeywordUtil.logInfo("Clicked on OK button after save workspace")
				(new generalFunc.AllgenralFunc()).shortDelay()
			}
		}else
		{
			KeywordUtil.logInfo("Save workspace info is blank")
		}

	}//SaveWorkspace(def wsName,String keepShareStngs)


	@Keyword
	//LoadWorkspace from workspace module
	def loadWorkspace(String workspaceName, String nSep ){
		if(workspaceName != ''){
			def mulWSNames = workspaceName.split(nSep)
			int lenWS = mulWSNames.size()
			// KeywordUtil.logInfo(lenWS)
			for (def index : (0..lenWS-1))
			{
				//TestObject wsName = makeTestObject('h5', mulWSNames[index], 'workspaceTitleHead', '', '', '', '')
				TestObject wsName = (new generalFunc.AllgenralFunc()).makeTestObject('h5', '', 'workspaceTitleHead', '', 'data-workspace', mulWSNames[index], '')

				//Click on sideMenu bar
				(new generalFunc.AllgenralFunc()).sideMenuBar()
				//Click on Workspace Tab
				(new generalFunc.AllgenralFunc()).SwitchtoWorkspace()
				WebUI.click(wsName)
				(new generalFunc.AllgenralFunc()).shortDelay()

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
	@Keyword
	//Created on 10-08-20
	//Updated on 13-08-20
	def restoreWorkspace(String restoreWS,String nSep)
	{
		if(restoreWS!="")
		{
			def mulRSWS = restoreWS.split(nSep)
			int lenWS = mulRSWS.size()

			//Call function to Click on user context menu
			(new generalFunc.AllgenralFunc()).clickUserLoginDropDown()
			(new generalFunc.AllgenralFunc()).shortDelay()
			//Make Object for Restore workspace and then click for it's context menu
			TestObject restreWS = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','', '//a[@id="vi-restoreworkspace"]')
			if(WebUI.verifyElementPresent(restreWS, 10, FailureHandling.CONTINUE_ON_FAILURE)==true)
			{
				(new generalFunc.AllgenralFunc()).clickUsingJS(restreWS,10)
				KeywordUtil.logInfo("Clicked on Restore Workspace")
				//Create object to check workspace name available or no in the menu then click on it
				for (def index : (0..lenWS-1))
				{
					String RwsNMStr = mulRSWS[index].trim()
					//Object for ws name
					TestObject nameWSobj = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','', '//a[@title="'+RwsNMStr+'" and @data-toggle="tooltip"]')
					//Click on WS name if it is present else print message for ws is not available
					if(WebUI.verifyElementPresent(nameWSobj,10,FailureHandling.CONTINUE_ON_FAILURE)==true)
					{
						(new generalFunc.AllgenralFunc()).clickUsingJS(nameWSobj,10)
						(new generalFunc.AllgenralFunc()).shortDelay()
						KeywordUtil.logInfo("Clicked on worksspace......" + RwsNMStr)
					}
					else
					{
						KeywordUtil.logInfo("Could not Click on worksspace......" + RwsNMStr)
					}

				}//for (def index : (0..lenWS-1))
			}
			else
			{
				KeywordUtil.logInfo("Restore Worksapce option is not available")
			}

		}
		else
		{
			KeywordUtil.logInfo("Restore Worksapce info is blank")
		}

	}//restoreWorkspace


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
				TestObject Edit = (new generalFunc.AllgenralFunc()).makeTestObject('label', '', '', '', '','' ,'(.//*[normalize-space(text()) and normalize-space(.)='+mulUsers[index]+'])[2]/following::span[1]')
				WebUI.check(Edit)
				//Share workspaces
				WebUI.click(findTestObject('Object Repository/Final Objects/LoadWSfromWSmodule/WSshareBtn'))
				(new generalFunc.AllgenralFunc()).shortDelay()
				//Ok btn on confirmMsg
				WebUI.click(findTestObject('Object Repository/Final Objects/LoadWSfromWSmodule/shareconfirmBtn'))
				(new generalFunc.AllgenralFunc()).shortDelay()
			}
			else
			{
				//makeobject for shareWS with View permissions then click
				TestObject View = (new generalFunc.AllgenralFunc()).makeTestObject('label', '', '', '', '','' ,'(.//*[normalize-space(text()) and normalize-space(.)='+mulUsers[index]+'])[2]/following::span[3]')
				WebUI.check(View)
				//Share workspaces
				WebUI.click(findTestObject('Object Repository/Final Objects/LoadWSfromWSmodule/WSshareBtn'))
				(new generalFunc.AllgenralFunc()).shortDelay()
				//Ok btn on confirmMsg
				WebUI.click(findTestObject('Object Repository/Final Objects/LoadWSfromWSmodule/shareconfirmBtn'))
				(new generalFunc.AllgenralFunc()).shortDelay()
			}
		}



	}//shareWS()

	@Keyword
	//Click on share Icon of mentioned workspace name
	def clickOnShareIcon(String wsNameShare, String nSep)
	{
		//Click on sideMenu bar
		(new generalFunc.AllgenralFunc()).sideMenuBar()
		//Click on Workspace Tab
		(new generalFunc.AllgenralFunc()).SwitchtoWorkspace()
		(new generalFunc.AllgenralFunc()).shortDelay()
		def mulShareWS = wsNameShare.split(nSep)
		int lenMul = mulShareWS.size()
		for (def index : (0..lenMul-1))
		{

			//make shareWS object then click
			TestObject shareWSIcon = (new generalFunc.AllgenralFunc()).makeTestObject('span', '', 'workspaceShareClass', '', 'data-workspace',mulShareWS[index] ,'')
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
			(new generalFunc.AllgenralFunc()).shortDelay()
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

	}//doShareWS()

	@Keyword
	//MyWorkspace Tab
	def myWSTab(){

		TestObject myWSTab = (new generalFunc.AllgenralFunc()).makeTestObject('a', '', '', 'mywrk_tab', 'aria-controls', 'myWrkTab', '')
		(new generalFunc.AllgenralFunc()).shortDelay()
		WebUI.click(myWSTab)
		(new generalFunc.AllgenralFunc()).shortDelay()

	}//myWSTab()

	@Keyword
	//Shared Workspaces Tab
	def sharedWSTab(){

		TestObject sharedWSTab = (new generalFunc.AllgenralFunc()).makeTestObject('a', '', '', 'shdwrk_tab', 'aria-controls', 'acl', '')
		(new generalFunc.AllgenralFunc()).shortDelay()
		WebUI.click(sharedWSTab)
		(new generalFunc.AllgenralFunc()).shortDelay()

	}//sharedWSTab()







	/* ***************************** End of Workspace Functions ****************************** */	


	@Keyword
	def switchToTabular()
	{

		//Click on Tabular Tab and make object for the same
		WebDriver driver = DriverFactory.getWebDriver()
		TestObject switchToTab = (new generalFunc.AllgenralFunc()).makeTestObject('a', '', 'tab-cls-anch', '', 'aria-controls','pivotTab' ,'')
		if(WebUI.waitForElementClickable(switchToTab, 10, FailureHandling.OPTIONAL)==true)
		{
			WebElement element = WebUiCommonHelper.findWebElement(switchToTab,10)
			JavascriptExecutor executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click()', element)
			(new generalFunc.AllgenralFunc()).shortDelay()
		}
		else
		{
			KeywordUtil.logInfo("Switch to tabular element not found")
		}


		//Validation for warning message if it presents
		TestObject fltORgrpErr = (new generalFunc.AllgenralFunc()).makeTestObject('div', '', 'sa-confirm-button-container', '', '','' ,'')
		if(WebUI.verifyElementPresent(fltORgrpErr,10,FailureHandling.OPTIONAL)==true)
		{
			(new generalFunc.AllgenralFunc()).shortDelay()
			WebUI.click(fltORgrpErr)
			(new generalFunc.AllgenralFunc()).shortDelay()
		}
		else
		{
			(new generalFunc.AllgenralFunc()).shortDelay()
			KeywordUtil.logInfo("No warnig messages displayed")
		}
		(new generalFunc.AllgenralFunc()).longDelay()


	}//switchToTabular()

	@Keyword
	//Function Created on 28-04-2020
	//Export from standard mode
	def exportFrmSM(String ESMfileNM,String ESMcmnts){

		if(ESMfileNM != "")
		{


			//Click on export Icon in standard mode
			TestObject exportIcon = (new generalFunc.AllgenralFunc()).makeTestObject('li', '', '', 'exportMySel', 'oldtitle', 'Export My Selection', '')
			WebUI.click(exportIcon)

			//Set name for Export from Standard Mode
			TestObject exportFNM = (new generalFunc.AllgenralFunc()).makeTestObject('input', '', 'form-control', 'myselexportnm', '', '', '')
			WebUI.click(exportFNM)
			WebUI.sendKeys(exportFNM, ESMfileNM)
			WebUI.sendKeys(exportFNM,Keys.chord(Keys.ENTER))

			//Set Comments on Export from Standard Mode
			if(ESMcmnts != "")
			{
				TestObject exportcmnts = (new generalFunc.AllgenralFunc()).makeTestObject('textarea', '', 'form-control scrollbarAll', 'myselexportcmnts', '', '', '')
				WebUI.click(exportcmnts)
				WebUI.sendKeys(exportcmnts, ESMcmnts)
				WebUI.sendKeys(exportcmnts,Keys.chord(Keys.ENTER))
				//Click on Export button
				TestObject exportBtn = (new generalFunc.AllgenralFunc()).makeTestObject('button', '', 'btn blueBtn', 'exportmyselbtn', '', '', '')
				WebUI.click(exportBtn)
				(new generalFunc.AllgenralFunc()).shortDelay()

				//Click on 'OK' button of queued message
				TestObject OKbtn = (new generalFunc.AllgenralFunc()).makeTestObject('button', '', 'confirm', '', '', '', '')
				WebUI.click(OKbtn)

			}
			else
			{
				//Click on Export button
				TestObject exportBtn = (new generalFunc.AllgenralFunc()).makeTestObject('button', '', 'btn blueBtn', 'exportmyselbtn', '', '', '')
				WebUI.click(exportBtn)
				(new generalFunc.AllgenralFunc()).shortDelay()

				//Click on 'OK' button of queued message
				TestObject OKbtn = (new generalFunc.AllgenralFunc()).makeTestObject('button', '', 'confirm', '', '', '', '')
				WebUI.click(OKbtn)
			}



		}else
		{
			KeywordUtil.logInfo("Export from Standard mode info details are not provided")
		}


	}//exportFrmSM()

	@Keyword
	//click on download link
	def downloadIcon(){

		TestObject downloadIcn = (new generalFunc.AllgenralFunc()).makeTestObject('div', '', '', 'icon', '', '', '')
		//(new generalFunc.AllgenralFunc()).shortDelay()
		WebUI.click(downloadIcn)
		(new generalFunc.AllgenralFunc()).shortDelay()

	}//downloadIcon()


	@Keyword
	//Function created on 04-05-2020
	//Edit attribute
	def doEditAttrVal(String EdititemID,String EAttrMsrNM,String newEditVal ){


		//Click on Item window
		TestObject itemIDObj = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//h6[@data-imgsku="'+EdititemID+'"]/../preceding-sibling::a[@class="abvthumbnail"]')
		WebUI.click(itemIDObj)

		//Click on Edit Icon
		TestObject editIcon = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//span[@class="edit-icon-lg-white" and @id="attrEdit"]')
		WebUI.click(editIcon)
		(new generalFunc.AllgenralFunc()).shortDelay()


		//Click on Dropdown box
		TestObject clickOnValDropdowndiv = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//span[@class="pull-left attrnm-div" and @data-attrnm="'+EAttrMsrNM+'"]/following-sibling::div')
		WebUI.click(clickOnValDropdowndiv)
		(new generalFunc.AllgenralFunc()).shortDelay()
		TestObject clickOnValDropdown = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//span[@class="pull-left attrnm-div" and @data-attrnm="'+EAttrMsrNM+'"]/following-sibling::div/input')
		String getlabel = WebUI.getAttribute(clickOnValDropdown,'aria-labelledby')
		String labelID = getlabel.substring(15)
		KeywordUtil.logInfo("Dropdown aria labelled"+labelID)
		//String srchInputXpath = '//div[@class="select2-drop select2-display-none select2-with-searchbox select2-drop-active or @class="select2-drop select2-display-none select2-with-searchbox select2-drop-active select2-drop-above"]//input[@class="select2-input" and @aria-owns="select2-results-'+labelID+'"]'
		//String srchInputXpath = '//div[@class="select2-drop select2-display-none select2-with-searchbox select2-drop-active"]//input[@class="select2-input" and @aria-owns="select2-results-'+labelID+'"]'
		String srch = '//input[@class="select2-input" and @aria-owns="select2-results-'+labelID+'"]'
		KeywordUtil.logInfo("search Input XPATH"+srch)
		(new generalFunc.AllgenralFunc()).shortDelay()
		//Click on Search box in the drop down
		TestObject searchInput = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '',srch )
		//TestObject searchInput = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//div[@class="select2-drop select2-display-none select2-with-searchbox select2-drop-active"]//input[@class="select2-input"]')
		//String areaOwn = WebUI.getAttribute(searchInput,'aria-owns')
		//KeywordUtil.logInfo("aria owned"+areaOwn)

		//if(WebUI.verifyElementVisible(searchInput,FailureHandling.CONTINUE_ON_FAILURE))
		//{
		//KeywordUtil.logInfo("Element is clickable")
		(new generalFunc.AllgenralFunc()).clickUsingJS(searchInput,10)
		WebUI.setText(searchInput,newEditVal)
		WebUI.sendKeys(searchInput,Keys.chord(Keys.ENTER))
		//}
		//else
		//{
		//KeywordUtil.logInfo("Element is not clickable")
		//}


		/*
		 //Click on Search Input box
		 //TestObject searchInput = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//span[@class="pull-left attrnm-div" and @data-attrnm="'+EAttrMsrNM+'"]/following-sibling::div[@class="select2-container form-control editableAttrSel doneLoading"]/input')
		 TestObject searchInput = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//div[@class="select2-drop select2-display-none select2-with-searchbox select2-drop-active"]//input[@class="select2-input"]')
		 //TestObject searchInput = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//div[@class="select2-drop select2-display-none select2-with-searchbox select2-drop-active"]//div[@class="select2-search"]//input[@class="select2-input"]')
		 //TestObject searchInput = (new generalFunc.AllgenralFunc()).makeTestObject('input', '', 'select2-input', '', '', '', '')
		 if(WebUI.verifyElementClickable(searchInput, FailureHandling.CONTINUE_ON_FAILURE))
		 {
		 KeywordUtil.logInfo("Element is clickable")
		 WebUI.click(searchInput)
		 //(new generalFunc.AllgenralFunc()).clickUsingJS(searchInput,10)
		 KeywordUtil.logInfo("Clicked on SearchInput box")
		 (new generalFunc.AllgenralFunc()).shortDelay()
		 WebUI.setText(searchInput,newEditVal)
		 WebUI.sendKeys(searchInput,Keys.chord(Keys.ENTER))
		 //Click on Save Editted values button
		 TestObject saveEditIcon = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//span[@id="saveEditAttr"]')
		 WebUI.click(saveEditIcon)
		 //Click on Success OK button
		 TestObject btnOK = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//button[@class="confirm"]')
		 WebUI.click(btnOK)
		 //Close Item Window
		 TestObject clsItemWin = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//i[@class="close close-icon-lg-blue"]')
		 WebUI.click(clsItemWin)
		 }else
		 {
		 KeywordUtil.logInfo("Element is not clickable")
		 }
		 */

	}//doEditAttrVal()

	@Keyword
	//Function created on 06-05-2020
	//click on loadmore button
	def loadmoreBtn(){

		TestObject loadmoreBtn = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//*[@id="mCSB_6_container"]/center/button')
		(new generalFunc.AllgenralFunc()).clickUsingJS(loadmoreBtn,10)
		(new generalFunc.AllgenralFunc()).shortDelay()

	}//loadmoreBtn()


	@Keyword
	//Function created on 06-05-2020
	//click on restorelastworkspace
	def restoreLastWS(){

		//Create object to click on restore workspace icon
		TestObject restoreWSIcon = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//li[@id="wrkSpace-restore"]')
		WebUI.click(restoreWSIcon)
		(new generalFunc.AllgenralFunc()).shortDelay()

		//check for the workspace name loaded or no
		TestObject restoreWSNM = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', '//li[@class="wrkSpaceNmCls" and @id="wrkspcnmSpan"]')
		if(WebUI.verifyElementVisible(restoreWSNM))
		{
			String titleWS = WebUI.getAttribute(restoreWSNM,'title')
			KeywordUtil.logInfo("Restored Last workspace named..."+ titleWS)
			(new generalFunc.AllgenralFunc()).shortDelay()
		}
		else
		{
			KeywordUtil.logInfo("Error:Could not click on Restore last workspace Name")
			(new generalFunc.AllgenralFunc()).shortDelay()
		}


	}//restoreLastWS()










	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

















}
