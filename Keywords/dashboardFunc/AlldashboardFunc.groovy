package dashboardFunc

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

public class AlldashboardFunc {


	@Keyword
	//Created on 9-6-20
	//Fatal error chk
	//Apply text tag filters to switch to Myselection in cases of fatal errors
	def fatalErrChk()
	{
		//Create object for dashboard
		TestObject dashboardTxt = (new generalFunc.AllgenralFunc()).makeTestObject('h3', 'Dashboard', 'heading', '', '','' ,'')
		//Verify dashboard screen
		if(WebUI.verifyElementPresent(dashboardTxt,10,FailureHandling.OPTIONAL)==true)
		{
			KeywordUtil.logInfo("You are in dashboard screen")
			//Create object to click on Text Tag Tab
			TestObject txtTagTab = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','' ,'//li[@role="presentation"]/a[@role="tab" and @aria-controls="tagsTab"]')
			WebUI.click(txtTagTab)
			//Click on Show other Tags icon
			TestObject showOtherTags = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','' ,'//div[@class="chk-div"]//label[@for="chkAttrPrivate" and @class = "lbl_cls chkTag_lbl"]')
			WebUI.click(showOtherTags)
			//Create Object for Input box
			TestObject inputTextTag = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','' ,'//input[@id="s2id_autogen1" and @type="text"]')
			WebUI.click(inputTextTag)
			WebUI.setText(inputTextTag, "fatalerror")
			WebUI.sendKeys(inputTextTag, Keys.chord(Keys.ENTER))
			//Click on Apply button in dash board
			TestObject applydashboard = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','' ,'//*[@id="tagsTab"]/div/div[1]/div[1]/button')
			WebUI.click(applydashboard)
			(new generalFunc.AllgenralFunc()).shortDelay()

		}
		else
		{
			KeywordUtil.logInfo("No fatal error observed")
		}


	}

	@Keyword
	//Exportfile from Notifications
	//Requires one argument as a file name
	def downloadExportFileTR15(String exportFileName)
	{
		if(exportFileName!='')
		{
			//Click on notification icon and export file

			//Make object for notification and click
			TestObject notificationBtn = (new generalFunc.AllgenralFunc()).makeTestObject('', '', 'alertsvgicon', '', '','' ,'')
			WebUI.click(notificationBtn)
			(new generalFunc.AllgenralFunc()).shortDelay()

			//Verify file availability then export
			TestObject tabExport = (new generalFunc.AllgenralFunc()).makeTestObject('', '', 'exportdownload', '', 'data-filename*',exportFileName ,'')
			if(WebUI.verifyElementClickable(tabExport))
			{
				WebUI.click(tabExport)
				(new generalFunc.AllgenralFunc()).shortDelay()
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
	//Tabular export file downloads from dashboard->Export status report
	//Requires one argument as a export file nmae
	def exportTabularFromDashboard(String exportFileName)
	{
		if(exportFileName != '')
		{
			KeywordUtil.logInfo(exportFileName)

			//Click on side menu and navigate to dashboard
			(new generalFunc.AllgenralFunc()).sideMenuBar()
			//Make object for dashboard and click
			TestObject dashboard = (new generalFunc.AllgenralFunc()).makeTestObject('i', '', 'dashboard_icon', '', '','' ,'')
			WebUI.click(dashboard)
			(new generalFunc.AllgenralFunc()).shortDelay()
			//alerts handling
			(new generalFunc.AllgenralFunc()).alertHandling()

			//Click on export status report expansion
			TestObject expandExportStatus = (new generalFunc.AllgenralFunc()).makeTestObject('i', '', '', 'maximize-export-report', '','' ,'')
			WebUI.click(expandExportStatus)
			(new generalFunc.AllgenralFunc()).shortDelay()
			//verify file name present then click on output link
			TestObject getOutput = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','' ,'//td/span[contains(@data-href,"'+exportFileName+'")]')
			if(WebUI.verifyElementPresent(getOutput,10,FailureHandling.OPTIONAL))
			{

				WebUI.click(getOutput)
				KeywordUtil.logInfo("Clicked on Get Output")
				(new generalFunc.AllgenralFunc()).longDelay()
				KeywordUtil.logInfo("Found output link")
			}
			else
			{
				KeywordUtil.logInfo("Not Found output link")
				(new generalFunc.AllgenralFunc()).longDelay
				WebUI.click(getOutput)
				(new generalFunc.AllgenralFunc()).shortDelay()

			}



		}else
		{
			KeywordUtil.logInfo("Exportfile name  info is blank")
		}

	}//exportTabularFromDashboard


}
