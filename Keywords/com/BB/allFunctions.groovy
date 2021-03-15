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
//import com.sun.org.apache.xpath.internal.operations.String
import internal.GlobalVariable

public class allFunctions {

	/** **************************** Object Creation Functions ******************** */
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

	/**
	 * @param xpath (String) The data attribute used to find the target element
	 * @param to (TestObject) constructed TestObject
	 * @return (TestObject) The constructed TestObject.
	 */

	@Keyword
	static TestObject makeToXpath(String xpath, TestObject to){
		to.addProperty("xpath", ConditionType.EQUALS, xpath)
		return to
	}

	/**
	 * Katalon-compatible Test Object construct functions end here
	 */
	/**
	 * Wrapper function for Katalon-compatible Test Object construct functions
	 * @param tag (String) The tag element used to find the target element.
	 * @param text (String) The text used to find the target element.
	 * @param cls (String) The class name used to find the target element.
	 * @param id (String) The id name used to find the target element.
	 * @param data, val (String, String) The data attribute and it's value used to find the target element.
	 * @param xpath (String) The xpath used to find the target element.
	 * @return (TestObject) The constructed TestObject.
	 */
	@Keyword

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

	}//makeTestObject()






	/** **************************** End of Object Creation Functions ******************** */



	/** **************************** General Functions ******************** */

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
	//Check element presents in viewport else click using js element
	def clickUsingJS(TestObject to, int timeout) {
		WebDriver driver = DriverFactory.getWebDriver()
		WebElement element = WebUiCommonHelper.findWebElement(to, timeout)
		JavascriptExecutor executor = ((driver) as JavascriptExecutor)
		//executor.executeScript('arguments[0].click()', Arrays.asList(element))
		executor.executeScript('arguments[0].click()', element)
	}//clickUsingJS




	/** **************************** End of General Functions ******************** */


	/** **************************** MySelection Functions ******************** */

	/** **************************** Main Functions ******************** */

	@Keyword
	//Reset all Filters (Main Apply button in My selection)
	def ResetAllFlts(){
		//Reset all filters applied in My Selection
		WebUI.scrollToElement(findTestObject('Object Repository/Final objects/MySelection/MainButns/i_reset-icon-lg-grey'),10)
		WebUI.click(findTestObject('Object Repository/Final objects/MySelection/MainButns/i_reset-icon-lg-grey'))
		WebUI.delay(10)
	}//ResetAllFlts

	@Keyword
	//Main Apply button in my Selection
	def FilterApplyBtn(){
		WebDriver driver = DriverFactory.getWebDriver()
		WebElement element = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Final objects/MySelection/MainButns/button_Apply'),10)
		JavascriptExecutor executor = ((driver) as JavascriptExecutor)
		executor.executeScript('arguments[0].click()', element)
	}//FilterApplyBtn()

	@Keyword
	//Click tooltip info btn in my selection
	def ClickInfoBtn()
	{
		WebDriver driver = DriverFactory.getWebDriver()
		WebElement element = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Final Objects/MySelection/MainButns/TooltipInfo'),10)
		JavascriptExecutor executor = ((driver) as JavascriptExecutor)
		executor.executeScript('arguments[0].click()', element)

	}//ClickInfoBtn()











	/** **************************** End of Main Functions ******************** */

	/** **************************** Validation Functions ******************** */

	@Keyword
	//makes Actual Count according to  groups
	//Reads expectedItemCnt from input file if count is greater than 0 then,
	//Gets actual count from kanvas then compares

	def actualItemCntGrp(String expected){

		if(expected > "0"){
			def actualItemcnt = WebUI.getText(findTestObject('Final Objects/Validation Objects/Actual/ActualItemCnt'))
			def actCnt = actualItemcnt.split(/\s/)
			String actual = actCnt[0]
			if(expected == actual)
			{
				WebUI.delay(10)
				KeywordUtil.markPassed('SUCCESS: Both actual and expected items are matched....and the actual and expeted item count is  '+ actual+'  '+ expected)
			}
			else
			{
				WebUI.delay(10)
				KeywordUtil.markFailed('ERROR : Items not matched with the expected items and the actual and expected item  count is: '+actual+' ' +expected)
			}

		}else
		{

			KeywordUtil.logInfo("Expected item count is not available or Zero")
		}

	}//actualItemCntGrp()

	@Keyword
	//makes Actual Count according to filters
	//Reads expectedItemCnt from input file if count is greater than 0 then,
	//Gets actual count from kanvas then compares

	def actualItemCntFlt(String expected){
		if(expected > "0"){
			def actualItemcnt = WebUI.getText(findTestObject('Final Objects/Validation Objects/Actual/ActualItemCnt'))
			def actCnt = actualItemcnt.split(/\s/)
			String actCntStr = actCnt[0] + ',' + actCnt[2]

			if(expected == actCntStr)
			{

				WebUI.delay(10)
				KeywordUtil.markPassed('SUCCESS: Both actual and expected items are matched....and the actual and expeted item count is  '+ actCntStr+'  '+ expected)

			}
			else
			{
				WebUI.delay(10)
				KeywordUtil.markFailed('ERROR : Items not matched with the expected items and the actual and expected item  count is: '+actCntStr+' ' +expected)
			}

		}else
		{

			KeywordUtil.logInfo("Expected item count is not available or Zero")
		}

	}//actualItemCntFlt()

	@Keyword

	//Validate expected and actual item count is matched / not matched
	//Checks for expected is greater than 0 and groupByAttrName is not equal to null
	def CompareItemCnt(String expected,String groupByAttrName){
		if(expected > "0" & groupByAttrName != ""){

			actualItemCntGrp(expected)

		}else{

			actualItemCntFlt(expected)
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
	def Stndardcnt()
	{

		//Switch to standard
		WebUI.click(findTestObject('Object Repository/Final Objects/MySelection/MainButns/a_Standard'))
		WebUI.delay(10)
		//Get Item count from Standard
		//JString is an another method to convert data types
		//JString stndardcnt = WebUI.getText(findTestObject('Final Objects/Validation Objects/Actual/ActualItemCnt'))
		def stndardcnt = WebUI.getText(findTestObject('Final Objects/Validation Objects/Actual/ActualItemCnt'))
		WebUI.delay(10)

		//Set to string variable
		//JString partName = stndardcnt.split(/\s/)[0]
		def partName = stndardcnt.split(/\s/)[0]
		//Convert string to integer
		int intPartName = Integer.parseInt(partName)
		//WebUI.comment('Total no.of item count in Standard Mode'+intPartName)
		WebUI.delay(5)
		return intPartName
	}//stndardcnt()

	@Keyword
	def tabVsStdcnt()
	{
		//Assign tabular count to variable
		int tabcnt = TabularItemCount()
		//Assign standard count to variable
		int stdcnt = Stndardcnt()
		if(tabcnt == stdcnt)
		{
			WebUI.delay(10)
			KeywordUtil.markPassed('SUCCESS: Both Tabular and Standard items are matched '+stdcnt)
		}
		else
		{
			WebUI.delay(10)
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
		log.logInfo (actCntStr)
		return actCntStr
	}//getActualItemCntG()





	/** ****************************End of Validation Functions ******************** */

	/** **************************** Limit Functions ******************** */
	@Keyword
	def setItemLimit(def limit)
	{
		if(limit != "")
		{
			WebUI.delay(5)
			TestObject resCtrlMenu = makeTestObject('i', '', '', '', '', '','id("limitSetBtn")/i[@class="setting-icon-lg-grey"]')
			WebUI.click(resCtrlMenu)
			WebUI.delay(5)
			//Click on Item Limit text box
			clickUsingJS(findTestObject('Object Repository/Final Objects/MySelection/MainButns/i_set-limit'),10)
			WebUI.delay(10)
			//Set Limit as 10
			WebUI.setText(findTestObject('Final Objects/MySelection/MainButns/input_first-n'),limit)
			WebUI.sendKeys(findTestObject('Final Objects/MySelection/MainButns/input_first-n'),Keys.chord(Keys.ENTER) )
		}else{
			KeywordUtil.logInfo("Item limit is not available")
		}
	}//setItemLimit()

	/** **************************** End of Limit Functions ******************** */

	/** **************************** Sort Functions ******************** */

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


	/** **************************** End of Sort Functions ******************** */

	/** **************************** ShowData Functions ******************** */

	@Keyword
	def enableShowData()
	{
		//Enable Show data
		//		Show = WebUI.verifyElementChecked(findTestObject('Object Repository/14-mar/showdata/Page_SLICeR Kanvas - My Selection/label_Show1'),10,FailureHandling.CONTINUE_ON_FAILURE)
		//		if(Show == true)
		//		{
		//			log.logInfo("checked")
		//		}else
		//		{
		//WebUI.click(findTestObject('Object Repository/14-mar/showdata/Page_SLICeR Kanvas - My Selection/label_Show'))
		TestObject enableSD = makeTestObject('label', '', 'newtoggleLbl', '', 'data-smvalue', 'showm', '')
		WebUI.delay(5)
		WebUI.click(enableSD)

		//	}
	}//enableShowData()

	@Keyword
	def enableHideData()
	{

		//Enable Hide data
		//Hide = WebUI.verifyElementChecked(findTestObject('Object Repository/14-mar/showdata/Page_SLICeR Kanvas - My Selection/label_Hide1'),10,FailureHandling.CONTINUE_ON_FAILURE)
		//		if((WebUI.verifyElementChecked(findTestObject('Object Repository/14-mar/showdata/Page_SLICeR Kanvas - My Selection/label_Hide1'),10,FailureHandling.CONTINUE_ON_FAILURE)) == true)
		//		{
		//			log.logInfo("checked")
		//			KeywordUtil.markPassed('SUCCESS:-------------HIDE data is already checked-------------')
		//		}
		//		else
		//		{
		WebUI.click(findTestObject('Object Repository/14-mar/showdata/Page_SLICeR Kanvas - My Selection/label_Hide'))
		//			KeywordUtil.markPassed('SUCCESS:-------------Hide data is now checked-------------')
		//		}

	}//enableHideData()


	/** **************************** End of ShowData Functions ******************** */

	/** **************************** Hierarchy Functions ******************** */

	@Keyword
	//Hierarchy Icon in My selection
	def HierarchyBtn(String hAttrNames){
		if(hAttrNames != "" )
		{
			//Click on hierarchy
			WebUI.click(findTestObject('Object Repository/Final objects/MySelection/MainButns/li_Attributes_hierarchyBtn'))
			WebUI.delay(10)
		}else
		{
			KeywordUtil.logInfo("Hierarchy attribute is not available")
		}
	}//HierarchyBtn

	@Keyword
	//Save button in hierarchy window
	def HierarchySave(){
		//Click on Save button in hierarchy window
		WebUI.click(findTestObject('Object Repository/Final objects/MySelection/MainButns/button_Save'))
		WebUI.delay(5)
	}//HierarchySave








	/** **************************** End of hierarchy Functions ******************** */

	/** **************************** Workspace Functions ******************** */
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

	}//SaveWorkspace()

	@Keyword
	def SwitchtoWorkspace(){
		WebUI.click(findTestObject('Object Repository/Final Objects/MySelection/MainButns/sidebar-Menu button'))
		WebUI.delay(10)
		WebUI.click(findTestObject('Object Repository/Final Objects/MySelection/MainButns/Workspace_tab'))
		WebUI.delay(10)
	}//SwitchtoWorkspace()

	/** **************************** Tabular Functions ******************** */
	@Keyword
	def TabularItemCount()
	{
		//Click on open tabular configuration
		WebUI.click(findTestObject('Object Repository/Final Objects/MySelection/Tabular/MainButns/div_Cancel_pivot-config-icon'))
		WebUI.delay(5)
		//Click on ID to expand
		WebUI.click(findTestObject('Object Repository/Final Objects/MySelection/Tabular/Attributes/ID-attr'))
		WebUI.delay(5)
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
		WebUI.delay(5)
		return elemcntsz
	}//TabularItemCount

	/** **************************** End of Tabular Functions ******************** */


	/** **************************** End of Workspace Functions ******************** */

	/** **************************** Filter Functions ******************** */

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
	}//SetMsrVal()


	@Keyword
	//Reads attribute values from data file in case of multiple values separates values using ','
	//then creates objects in memory and clicks

	static clickAttrVals(String vals, String vSep)
	{
		def mulVals = vals.split(vSep)

		int lenMul = mulVals.size()

		for (def index : (0..lenMul-1))
		{
			TestObject attrV = makeTestObject('li', mulVals[index].trim(), 'txt-attr-val', '', '', '', '')
			WebUI.delay(5)
			WebUI.click(attrV)
			//clickUsingJS(attrV,10)

		}

	}//clickAttrVals()

	@Keyword
	// Reads both attributes and values from data file and creates objects for attribute then clicks on attribute
	// In case of multiple attributes split with ';'
	static selAttrNdVals(String attrNames,String attrVals, String nSep, String vSep, String attrClass)
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

				TestObject attrN = makeTestObject('div', attr, attrClass, '', '', '','')

				WebUI.click(attrN)
				WebUI.delay(5)
				clickAttrVals(mulVals[index],vSep)
				WebUI.click(attrN)
			}
		}else
		{
			KeywordUtil.logInfo("attribute name is blank")
		}

	}//selAttrNdVals()







	/** **************************** End of  Filter Functions ******************** */


	/** ****************************  Group Functions ******************** */

	@Keyword
	//Checks for attribute availability in input file, if it's available
	//Creates result control menu object and clicks
	//Reads attribute names from input files(Ref for attribute name : chkAttrcategory)
	//In case of multiple names reads values by referring separator ';'
	static clickGrpAttrs(String groupByAttrName,  String nSep)
	{
		if(groupByAttrName != "" )
		{
			//Group by attribute
			TestObject resCtrlMenu = makeTestObject('i', '', '', '', '', '','id("limitSetBtn")/i[@class="setting-icon-lg-grey"]')
			WebUI.click(resCtrlMenu)
			WebUI.delay(5)
			def mulAttrs = groupByAttrName.split(nSep)
			int lenMul = mulAttrs.size()
			for (def index : (0..lenMul-1))
			{
				TestObject GattrS = makeTestObject('label', '', 'lbl_cls', '', 'for', mulAttrs[index],'')
				WebUI.delay(5)
				WebUI.click(GattrS)
			}
			WebUI.click(resCtrlMenu)
		}else{
			KeywordUtil.logInfo("Attribute is not available to group")
		}

	}//clickGrpAttrs


	/** **************************** End of  Group Functions ******************** */

	/** ****************************End of MySelection Functions ******************** */







}
