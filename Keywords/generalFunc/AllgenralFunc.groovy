package generalFunc

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

public class AllgenralFunc {

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


	/**
	 * Katalon-compatible Test Object construct functions end here
	 */

	/* *****************End of Functions to Create Test Objects in memory ********************************** */

	/* ************************************** Delay Functions ***************************************** */
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



	/* ************************************** End of Delay Functions ***************************************** */



	/* ************************************** Kanvas General Functions ***************************************** */


	@Keyword
	//click ok button for higher columns warning message to switch to layout

	def warningForHigherCols() {


		WebDriver driver = DriverFactory.getWebDriver()
		TestObject warnOKBtn = makeTestObject('button', '', 'confirm', '', '','','')
		WebElement element = WebUiCommonHelper.findWebElement(warnOKBtn,10)
		JavascriptExecutor executor = ((driver) as JavascriptExecutor)
		executor.executeScript('arguments[0].click()', element)
		longDelay()
	}//warningForHigherCols

	@Keyword
	//created on 11-05-2020
	//click ok on license expiry message

	def licenseExpiryOK() {


		WebDriver driver = DriverFactory.getWebDriver()
		TestObject LicenseOK = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '','//button[@class="confirm"]')

		if(WebUI.verifyTextPresent("Kanvas License", false)==true)
		{
			WebElement element = WebUiCommonHelper.findWebElement(LicenseOK,10)
			JavascriptExecutor executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click()', element)
			shortDelay()
			KeywordUtil.logInfo("Clicked on License Expiry message")
		}else
		{
			KeywordUtil.logInfo("License Expiry message not displayed")
		}


	}//licenseExpiryOK

	@Keyword
	//Print Test case ID
	def logTestCaseID(String TestCaseID)
	{
		KeywordUtil.logInfo("Running Test Case ID is : "+ TestCaseID)
	}


	@Keyword
	//Any browser alert message accepts and proceed
	def alertHandling()
	{
		def elementPresent=WebUI.waitForAlert(20,FailureHandling.OPTIONAL)
		if (elementPresent==true) {
			WebUI.acceptAlert()
			shortDelay()
		} else {

			KeywordUtil.logInfo("No alert displayed")
		}
	}

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

		WebUI.click(userDDMenu)
		shortDelay()
	}//clickUserLoginDropDown()

	@Keyword
	//click on welcome user
	def kanvasLogo(){
		//Local Server
		//TestObject kLogo = makeTestObject('img','', '', '', 'src', '/kanvas/util/static/img/kanvaslogo.png', '')
		//Staging Server
		TestObject kLogo = makeTestObject('img','', '', '', 'src', '/util/static/img/kanvaslogo.png', '')
		if(WebUI.waitForElementClickable(kLogo, 10, FailureHandling.CONTINUE_ON_FAILURE)==true)
		{
			WebUI.click(kLogo)
			shortDelay()
		}else
		{
			KeywordUtil.logInfo("Could not find kanvas logo")
			KeywordUtil.markFailed("Could not find kanvas logo")
		}


	}//kanvasLogo()


	@Keyword
	//Standard Mode filter/group warning messages
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
	}//def warningMsgChk()

	@Keyword
	def SwitchtoWorkspace(){
		//WebUI.click(findTestObject('Object Repository/Final Objects/MySelection/MainButns/sidebar-Menu button'))
		//longDelay()
		WebUI.click(findTestObject('Object Repository/Final Objects/MySelection/MainButns/Workspace_tab'))
		(new generalFunc.AllgenralFunc()).shortDelay()
	}//SwitchtoWorkspace()





	@Keyword
	//StartFresh function works on the given inputs
	//If startFresh is blank - it always starts from standard mode by resetting all filters
	//If startFresh is with 'No' then it starts from that page
	def startFresh(String startFresh)
	{
		if(startFresh == '')
		{
			//Click on Kanvas Logo
			//WebUI.click(findTestObject('Object Repository/Final Objects/MySelection/MainButns/kanvaslogo'))
			kanvasLogo()
			shortDelay()
			//alerts handling
			alertHandling()
			//Fatal Error Check

			(new dashboardFunc.AlldashboardFunc()).fatalErrChk()



			//Warning message check
			warningMsgChk()

			//Switch To Standard Mode
			(new standardFunc.AllstandardFunc()).switchToStandard()

			//Reset all filters in My Selection
			(new standardFunc.AllstandardFunc()).ResetAllFlts()

			//Make it Full screen mode
			//fullScrn()


		}else
		{
			KeywordUtil.logInfo("Not a Fresh start add additional operations to the existing screen/module")
		}

	}//startFresh()

	@Keyword
	//Main Apply button in my Selection
	def fullScrn(){
		TestObject fullScrn = (new generalFunc.AllgenralFunc()).makeTestObject('i', '', 'fullscreen-icon-xl-white', 'header-full-screen-btn', '', '','')
		WebUI.click(fullScrn)
		//WebDriver driver = DriverFactory.getWebDriver()
		//WebElement element = WebUiCommonHelper.findWebElement(fullScrn,10)
		//JavascriptExecutor executor = ((driver) as JavascriptExecutor)
		//executor.executeScript('arguments[0].click()', element)
	}//fullScrn

	@Keyword
	//Click on side menu options
	def sideMenuBar(){

		TestObject sideMenu = makeTestObject('span', '', 'glyphicon glyphicon-option-horizontal', '', '', '', '')
		WebUI.click(sideMenu)
		shortDelay()

	}//sideMenuBar()





	/* ************************************** End of Kanvas General Functions ***************************************** */


}




