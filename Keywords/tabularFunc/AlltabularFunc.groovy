package tabularFunc

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
import com.BB.genralFunctions

public class AlltabularFunc {

	//tabularFunc Keyword created on 20-Apr

	@Keyword
	//Function to click on Layout icon in tabular
	def switchToLayoutFromTabular()
	{
		//Switch to Layout from Tabular

		WebDriver driver = DriverFactory.getWebDriver()
		TestObject switchToLayout = (new generalFunc.AllgenralFunc()).makeTestObject('i', '', 'layout-icon-lg-grey', '', '','' ,'')
		WebElement element = WebUiCommonHelper.findWebElement(switchToLayout,10)
		JavascriptExecutor executor = ((driver) as JavascriptExecutor)
		executor.executeScript('arguments[0].click()', element)
		if(WebUI.verifyTextPresent("Warning!", false,FailureHandling.OPTIONAL))
		{
			(new generalFunc.AllgenralFunc()).shortDelay()
			KeywordUtil.logInfo("click ok in warning message then switch to Layout")
			(new generalFunc.AllgenralFunc()).warningForHigherCols()
		}
		else
		{
			KeywordUtil.logInfo("Switching to Layout without any warning message")
			(new generalFunc.AllgenralFunc()).shortDelay()
		}



	}//switchToLayoutFromTabular()


	@Keyword
	//Modified this function with the multiple values reading on 20-Apr
	//Also merged Enable and Disable functionality and created as a single function

	def colSubTotal15(String colSubTotal,String cFieldnm,String vSep)
	{
		if(colSubTotal != '' & cFieldnm != '')
		{
			def mulAttrColST = cFieldnm.split(vSep)
			def mulAttrColSTstatus = colSubTotal.split(vSep)
			int mulAttren = mulAttrColST.size()
			KeywordLogger log = new KeywordLogger()
			def done = false
			for(def index :(0..mulAttren-1))
			{
				String cAttrnm = mulAttrColST[index].trim()
				String cAttrstatus = mulAttrColSTstatus[index].trim()
				KeywordUtil.logInfo('ColumnName'+cAttrnm+'SubTotalSTtaus'+cAttrstatus)
				//R15 accessor for col subtotal label
				String chkLabelCol = '//input[@class="checkbox csubtotal" and @data-cfieldrnm = "'+cAttrnm+'" ]/following-sibling::label'
				//R15 accessor for col subtotal input
				String chkInputCol = '//input[@class="checkbox csubtotal" and @data-cfieldrnm = "'+cAttrnm+'" ]'
				//Make Object and verify Input element of Sub Total of Columns whether it's checked or no
				TestObject chkICol = (new com.BB.genralFunctions()).makeTestObject('', '', '', '', '', '',chkInputCol)
				(new com.BB.genralFunctions()).shortDelay()
				if(cAttrstatus=="Enable")
				{
					(new com.BB.genralFunctions()).shortDelay()

					if((WebUI.verifyElementChecked(chkICol,10,FailureHandling.OPTIONAL))== true){
						(new com.BB.genralFunctions()).shortDelay()
						KeywordUtil.logInfo("Column Subtotal is already checked")
					}else
					{
						TestObject chkLableobj = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', chkLabelCol)
						//WebUI.click(chkLableobj)
						(new generalFunc.AllgenralFunc()).clickUsingJS(chkLableobj,10)
						KeywordUtil.logInfo("Column Subtotal Checked")
						(new generalFunc.AllgenralFunc()).shortDelay()
					}
				}//if(cAttrstatus=="Enable")
				else if(cAttrstatus=="Disable")
				{
					if((WebUI.verifyElementChecked(chkICol,10,FailureHandling.OPTIONAL))== true){
						KeywordUtil.logInfo("Column Subtotal is Enabled....Click on checkbox to disable")
						TestObject chkLableobj = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', chkLabelCol)
						//WebUI.click(chkLable)
						(new generalFunc.AllgenralFunc()).clickUsingJS(chkLableobj,10)
						(new generalFunc.AllgenralFunc()).shortDelay()

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
	//gets tabular item count
	def TabularItemCount()
	{
		//Click on open tabular configuration
		WebUI.click(findTestObject('Object Repository/Final Objects/MySelection/Tabular/MainButns/div_Cancel_pivot-config-icon'))
		(new generalFunc.AllgenralFunc()).shortDelay()
		//Click on ID to expand
		WebUI.click(findTestObject('Object Repository/Final Objects/MySelection/Tabular/Attributes/ID-attr'))
		(new generalFunc.AllgenralFunc()).shortDelay()
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
		(new generalFunc.AllgenralFunc()).shortDelay()
		return elemcntsz
	}//TabularItemCount

	@Keyword
	//Compare exported tabular and actual tabular
	def compareTabularR15(String exportFileName, String expectedPath,String actualPath)
	{

		if(expectedPath != '' & actualPath != '')
		{
			(new dashboardFunc.AlldashboardFunc()).exportTabularFromDashboard(exportFileName)
			//to get the workbook of expected tabular sheet
			def workbookExpected = ExcelKeywords.getWorkbook(expectedPath)
			//to get the workbook of actual tabular sheet
			//String Actual = 'C:/Users/DELL/Katalon Studio/Kanvas-R12/KanvasDownloads/T1.xlsx'
			def workbookActual = ExcelKeywords.getWorkbook(actualPath)
			(new generalFunc.AllgenralFunc()).longDelay()
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
	//Compare Tabulars method used in R14(The only difference is export tabular feature changed)
	def compareTabularR14( String expectedPath,String actualPath)
	{

		if(expectedPath != '' & actualPath != '')
		{

			//to get the workbook of expected tabular sheet
			def workbookExpected = ExcelKeywords.getWorkbook(expectedPath)
			//to get the workbook of actual tabular sheet
			//String Actual = 'C:/Users/DELL/Katalon Studio/Kanvas-R12/KanvasDownloads/T1.xlsx'
			def workbookActual = ExcelKeywords.getWorkbook(actualPath)
			(new generalFunc.AllgenralFunc()).longDelay()
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
	//ExportallTabular as per R14 version
	//Requires one argument as a filename
	def exportAllTabularR14(String exportFileName)
	{

		if(exportFileName != '')
		{

			//Export all Tabulars
			WebDriver driver = DriverFactory.getWebDriver()
			TestObject exportAllTabular = (new generalFunc.AllgenralFunc()).makeTestObject('i', '', 'exportPivotIcon', '', '','' ,'')
			WebUI.disableSmartWait()
			WebUI.enableSmartWait()
			WebElement element = WebUiCommonHelper.findWebElement(exportAllTabular,10)
			JavascriptExecutor executor = ((driver) as JavascriptExecutor)
			//WebUI.waitForElementClickable(exportAllTabular,60)
			executor.executeScript('arguments[0].click()', element)
			(new generalFunc.AllgenralFunc()).shortDelay()

			//Set ExportFileName
			TestObject setExportTFileName = (new generalFunc.AllgenralFunc()).makeTestObject('input', '', 'form-control', 'exportfilenm', '','' ,'')
			WebUI.sendKeys(setExportTFileName, Keys.chord(Keys.CONTROL, 'a'))
			WebUI.sendKeys(setExportTFileName, Keys.chord(Keys.BACK_SPACE))
			WebUI.sendKeys(setExportTFileName, exportFileName)

			(new generalFunc.AllgenralFunc()).shortDelay()

			//Click on Export button
			TestObject exportBtn = (new generalFunc.AllgenralFunc()).makeTestObject('button', '', 'blueBtn', 'exportBtn', '','' ,'')
			WebUI.click(exportBtn)

			if(WebUI.verifyTextPresent("Please Wait",false,FailureHandling.OPTIONAL))
			{
				KeywordUtil.logInfo("Continue Enable wait.............IF block")
				(new generalFunc.AllgenralFunc()).shortDelay()

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
	//Export tabular as per R15 version
	//Requires one argument as exportfilename
	def exportAllTabularR15(String exportFileName)
	{
		if(exportFileName != '')
		{

			(new generalFunc.AllgenralFunc()).shortDelay()
			//Export all Tabulars
			WebDriver driver = DriverFactory.getWebDriver()
			TestObject exportAllTabular = (new generalFunc.AllgenralFunc()).makeTestObject('i', '', 'exportPivotIcon', '', '','' ,'')
			if(WebUI.verifyElementClickable(exportAllTabular,FailureHandling.CONTINUE_ON_FAILURE)==true)
			{
				WebUI.waitForElementClickable(exportAllTabular,10,FailureHandling.CONTINUE_ON_FAILURE)
				WebElement element = WebUiCommonHelper.findWebElement(exportAllTabular,10)
				JavascriptExecutor executor = ((driver) as JavascriptExecutor)
				//WebUI.waitForElementClickable(exportAllTabular,60)
				executor.executeScript('arguments[0].click()', element)
				(new generalFunc.AllgenralFunc()).longDelay()
			}else
			{
				KeywordUtil.logInfo("Could not find export Icon")
			}


			//Set ExportFileName
			TestObject setExportTFileName = (new generalFunc.AllgenralFunc()).makeTestObject('input', '', 'form-control', 'myselexportnm', '','' ,'')
			if(WebUI.verifyElementClickable(setExportTFileName, FailureHandling.CONTINUE_ON_FAILURE)==true)
			{
				KeywordUtil.logInfo("Found exportfilename input box")
				WebUI.click(setExportTFileName)
				WebUI.sendKeys(setExportTFileName, Keys.chord(Keys.CONTROL, 'a'))
				WebUI.sendKeys(setExportTFileName, Keys.chord(Keys.BACK_SPACE))
				WebUI.sendKeys(setExportTFileName, exportFileName)

			}
			else
			{
				KeywordUtil.logInfo("Could not find exportfilename input box")
			}
			/*if(WebUI.waitForElementNotClickable(setExportTFileName, 10, FailureHandling.CONTINUE_ON_FAILURE)==true)
			 {
			 (new generalFunc.AllgenralFunc()).longDelay()
			 (new generalFunc.AllgenralFunc()).clickUsingJS(setExportTFileName,10)
			 WebUI.sendKeys(setExportTFileName, Keys.chord(Keys.CONTROL, 'a'))
			 WebUI.sendKeys(setExportTFileName, Keys.chord(Keys.BACK_SPACE))
			 WebUI.sendKeys(setExportTFileName, exportFileName)
			 }
			 else
			 {
			 KeywordUtil.logInfo("Could not find exportfilename input box")
			 KeywordUtil.markFailed("Could not find exportfilename input box")
			 }*/

			(new generalFunc.AllgenralFunc()).shortDelay()

			//Click on Export button
			TestObject exportBtn = (new generalFunc.AllgenralFunc()).makeTestObject('button', '', 'blueBtn', 'exportmyselbtn', '','' ,'')
			WebUI.click(exportBtn)

			if(WebUI.verifyTextPresent("Please Wait",false,FailureHandling.OPTIONAL))
			{
				KeywordUtil.logInfo("Continue Enable wait.............IF block")
				(new generalFunc.AllgenralFunc()).longDelay()
				TestObject exportSuccBtn = (new generalFunc.AllgenralFunc()).makeTestObject('button', '', 'confirm', '', '','' ,'')
				WebUI.click(exportSuccBtn)
				KeywordUtil.logInfo("Exported file added to queue")
			}
			else
			{
				TestObject exportSuccBtn = (new generalFunc.AllgenralFunc()).makeTestObject('button', '', 'confirm', '', '','' ,'')
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
	//Modified on 12-06-20
	//Expands tabular configuration
	def openTabularConfig()
	{

		//TestObject openSlider = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','' ,'id("myselection-content")/div[@class="displayTabMenu col-md-12 col-lg-12"]/div[@class="pivot-config"]/div[@class="pivot-config-box show"]/div[@class="pivot-config-icon"]/i[@class="setting-icon-lg-white"]')
		TestObject tConfigColl = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','' ,'id("myselection-content")/div[@class="displayTabMenu col-md-12 col-lg-12"]/div[@class="pivot-config zerozindex"]/div[@class="pivot-config-box"]/div[@class="pivot-config-icon"]/i[@class="setting-icon-lg-white"]')
		TestObject tConfigExpand = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','' ,'id("myselection-content")/div[@class="displayTabMenu col-md-12 col-lg-12"]/div[@class="pivot-config"]/div[@class="pivot-config-box show"]/div[@class="pivot-config-icon"]/i[@class="setting-icon-lg-white"]')
		if(WebUI.verifyElementPresent(tConfigExpand,10,FailureHandling.OPTIONAL)==true)
		{
			(new generalFunc.AllgenralFunc()).shortDelay()
			KeywordUtil.markPassed("Tabular Slider was Opened...proceed to set configuration")
		}
		else
		{
			(new generalFunc.AllgenralFunc()).shortDelay()
			(new generalFunc.AllgenralFunc()).clickUsingJS(tConfigColl,10)
			KeywordUtil.markPassed("Tabular is closed.....clicked on slider to set Tabular configuration")
		}
		/*
		 if(WebUI.verifyElementPresent(tConfigColl,10,FailureHandling.CONTINUE_ON_FAILURE)==true)
		 {
		 //KeywordUtil.logInfo("Tabular Slider is opened")
		 (new generalFunc.AllgenralFunc()).shortDelay()
		 (new generalFunc.AllgenralFunc()).clickUsingJS(tConfigColl,10)
		 KeywordUtil.markPassed("Tabular is closed.....clicked on slider to set Tabular configuration")
		 }else
		 {
		 //When Tabular config was open  just continue settings
		 //TestObject closeSlider = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','' ,'id("myselection-content")/div[@class="displayTabMenu col-md-12 col-lg-12"]/div[@class="pivot-config zerozindex"]/div[@class="pivot-config-box"]/div[@class="pivot-config-icon"]/i[@class="setting-icon-lg-white"]')
		 (new generalFunc.AllgenralFunc()).shortDelay()
		 KeywordUtil.markPassed("Tabular Slider was Opened...proceed to set configuration")
		 }
		 */
	}//openTabularConfig()

	@Keyword
	//Enables Preview in Tabular
	def previewTabularEnable()
	{

		String chkLabel = '//input[@id="chkpreviewtop"]/following-sibling::label'
		String chkInput = '//input[@id="chkpreviewtop"]'
		//Make Object and verify Input element of Preview whether it's checked or no
		TestObject chkI = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '',chkInput)
		(new generalFunc.AllgenralFunc()).shortDelay()
		if((WebUI.verifyElementChecked(chkI,10,FailureHandling.OPTIONAL))== true){
			KeywordUtil.logInfo("Preview Tabular is checked")
		}else
		{
			TestObject chkLable = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', chkLabel)
			WebUI.click(chkLable)
			(new generalFunc.AllgenralFunc()).shortDelay()
		}


	}//previewTabularEnable()

	@Keyword
	//Disables Preview mode
	def previewTabularDisable()
	{

		String chkLabel = '//input[@id="chkpreviewtop"]/following-sibling::label'
		String chkInput = '//input[@id="chkpreviewtop"]'
		//Make Object and verify Input element of Preview whether it's checked or no
		TestObject chkI = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '',chkInput)
		(new generalFunc.AllgenralFunc()).shortDelay()
		if((WebUI.verifyElementChecked(chkI,10,FailureHandling.OPTIONAL))== true){
			TestObject chkLable = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', chkLabel)
			//WebUI.click(chkLable)
			(new generalFunc.AllgenralFunc()).clickUsingJS(chkLable, 10)
			(new generalFunc.AllgenralFunc()).shortDelay()

		}else
		{
			KeywordUtil.logInfo("Preview Tabular is unchecked")
		}

	}//previewTabularDisable()

	@Keyword
	//Main Function to call in tabular configration screen
	//Requires one argument as preview status
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
	//Enables Image display
	def imageTabularEnable(String ImageTabularEnable)
	{
		if(ImageTabularEnable != '')
		{

			String chkLabel = '//input[@id="chkImgDisplayRow"]/following-sibling::label'
			String chkInput = '//input[@id="chkImgDisplayRow"]'
			//Make Object and verify Input element of Image whether it's checked or no
			TestObject chkI = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '',chkInput)

			if((WebUI.verifyElementChecked(chkI,10,FailureHandling.OPTIONAL))== true){
				KeywordUtil.logInfo("Image Tabular is already checked")
			}else
			{
				TestObject chkLable = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', chkLabel)
				//WebUI.click(chkLable)
				(new generalFunc.AllgenralFunc()).clickUsingJS(chkLable,10)
				(new generalFunc.AllgenralFunc()).shortDelay()
			}
		}else
		{
			KeywordUtil.logInfo("Image Tabular Enable information is blank")
		}


	}//imageTabularEnable()

	@Keyword
	//Disbales image display in tabular
	def imageTabularDisable()
	{

		String chkLabel = '//input[@id="chkImgDisplayRow"]/following-sibling::label'
		String chkInput = '//input[@id="chkImgDisplayRow"]'
		//Make Object and verify Input element of Image whether it's checked or no
		TestObject chkI = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '',chkInput)

		if((WebUI.verifyElementChecked(chkI,10,FailureHandling.OPTIONAL))== true){
			TestObject chkLable = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', chkLabel)
			//WebUI.click(chkLable)
			(new generalFunc.AllgenralFunc()).clickUsingJS(chkLable, 10)
			(new generalFunc.AllgenralFunc()).shortDelay()

		}else
		{
			KeywordUtil.logInfo("Image Tabular is unchecked")
		}

	}//imageTabularDisable()

	@Keyword
	//Main function to call in tabular config function
	//requires one argument to check the image status
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
	//Modified this function with the multiple values reading on 20-Apr
	//Also merged Enable and Disable functionality and created as a single function

	def rowSubTotal15(String rFieldnm,String rowSubTotal,String vSep)
	{
		if(rowSubTotal != '' & rFieldnm != '')
		{
			def mulAttrRowST = rFieldnm.split(vSep)
			def mulAttrRowSTstatus = rowSubTotal.split(vSep)
			int mulAttren = mulAttrRowST.size()
			KeywordLogger log = new KeywordLogger()
			def done = false
			for(def index :(0..mulAttren-1))
			{
				String rAttrnm = mulAttrRowST[index].trim()
				String rAttrstatus = mulAttrRowSTstatus[index].trim()
				KeywordUtil.logInfo('RowName'+rAttrnm+'SubTotalSTtaus'+rAttrstatus)
				//R15 accessor for Subtotal label
				String chkLabel = '//input[@class="checkbox rsubtotal" and @data-rfieldrnm="'+rAttrnm+'" ]/following-sibling::label'
				//R15 accessor for subtotal Input
				String chkInput = '//input[@class="checkbox rsubtotal" and @data-rfieldrnm="'+rAttrnm+'" ]'
				//Make Object and verify Input element of Row fields sub total whether it's checked or no
				TestObject chkI = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '',chkInput)

				if(rAttrstatus=="Enable")
				{

					if((WebUI.verifyElementChecked(chkI,10,FailureHandling.OPTIONAL))== true){
						KeywordUtil.logInfo("Row Subtotal is already checked")
					}else
					{
						TestObject chkLable = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', chkLabel)
						WebUI.click(chkLable)
						KeywordUtil.logInfo("Row Subtotal Checked")
						(new generalFunc.AllgenralFunc()).shortDelay()
					}
				}//if(rAttrstatus=="Enable")
				else if(rAttrstatus=="Disable")
				{
					if((WebUI.verifyElementChecked(chkI,10,FailureHandling.OPTIONAL))== true){
						KeywordUtil.logInfo("Row Subtotal is Enabled....Click on checkbox to disable")
						TestObject chkLable = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', chkLabel)
						//WebUI.click(chkLable)
						(new generalFunc.AllgenralFunc()).clickUsingJS(chkLable,10)
						(new generalFunc.AllgenralFunc()).shortDelay()

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
	//Enables item count in tabular
	def itemcountEnable()
	{
		String chkLabel = '//input[@id="itemcount"]/following-sibling::label'
		String chkInput = '//input[@id="itemcount"]'
		//Make Object and verify Input element of GrandTotal whether it's checked or no
		TestObject chkI = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '',chkInput)

		if((WebUI.verifyElementChecked(chkI,10,FailureHandling.OPTIONAL))== true){
			KeywordUtil.logInfo("Itemcount is checked")
		}else
		{
			TestObject chkLable = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', chkLabel)
			WebUI.click(chkLable)
			(new generalFunc.AllgenralFunc()).shortDelay()
		}
	}//itemcountEnable()

	@Keyword
	//Disables item count in tabular
	def itemcountDisable()
	{
		String chkLabel = '//input[@id="itemcount"]/following-sibling::label'
		String chkInput = '//input[@id="itemcount"]'
		//Make Object and verify Input element of GrandTotal whether it's checked or no
		TestObject chkI = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '',chkInput)

		if((WebUI.verifyElementChecked(chkI,10,FailureHandling.OPTIONAL))== true){

			TestObject chkLable = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', chkLabel)
			//WebUI.click(chkLable)
			(new generalFunc.AllgenralFunc()).clickUsingJS(chkLable,10)
			(new generalFunc.AllgenralFunc()).shortDelay()

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
	//Set Table name for tabular
	def setTableName(String tableName)
	{
		if(tableName != '')
		{
			TestObject setTableName = (new generalFunc.AllgenralFunc()).makeTestObject('input', '', 'form-control', 'pvt-config-tableTitle', '','' ,'')
			WebUI.click(setTableName)
			WebUI.setText(setTableName,tableName)
			WebUI.sendKeys(setTableName,Keys.chord(Keys.ENTER))
		}else
		{
			KeywordUtil.logInfo("tablename  is blank")
		}

	}//setTableName




	@Keyword
	//Selects attributes
	//Requires 3 arguments attribute names with semicolon separator, and returns value from the object
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
			TestObject attrName = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','' ,'//li[@data-attributenm="'+attrTo+'"]//label[@class="lbl_cls chkSelAttr_lbl"]')
			//if(WebUI.verifyElementClickable(attrName)==true)
			//{

			WebElement element = WebUiCommonHelper.findWebElement(attrName,10)
			JavascriptExecutor executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click()', element)
			//WebUI.click(attrName)
			//longDelay()
			KeywordUtil.logInfo("Attribute Clicked")
			(new generalFunc.AllgenralFunc()).shortDelay()

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
	//Selects measures for data field
	//Requires 3 arguments measrure names with semi colon separator and returns to value
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
				KeywordUtil.logInfo("Measure in chkMeasuresT func"+msrTo)
				TestObject msrName = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','' ,'//li[@data-measurenm="'+msrTo+'" ]//label[@class="lbl_cls chkSelAttr_lbl"]')
				if(WebUI.verifyElementClickable(msrName)==true)
				{
					KeywordUtil.logInfo("Able to click Measure Name checkbox")
					(new generalFunc.AllgenralFunc()).clickUsingJS(msrName,10)
					//WebUI.click(msrName)
					KeywordUtil.logInfo("MeasureName Clicked")

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
	//Drag and dropbrow fields
	//Requiress 3 arguments with the rowfield names with semicolon separator and returns to value

	def rowFieldsDD(String rowFields, String nSep , String to)
	{
		//Check for rowfields not equal to null
		//Call returned value from 'chkAttributes' function in make object of listofAttrs
		//Make object for rowfield area.verifyelementpresent ,if yes then drop listofAttrs in to rowfileds area


		String toval = chkAttributesT(rowFields,  nSep, to)
		KeywordUtil.logInfo('Returned attribute name in rowfiledsDD to value'+toval)
		//select list of selected attributes
		TestObject listOfAttrs = (new generalFunc.AllgenralFunc()).makeTestObject('li', '', 'ui-state-default ui-draggable ui-draggable-handle multdrag', '', 'data-attributenm',toval ,'')

		//Rowfields Area
		TestObject rowFieldsArea = (new generalFunc.AllgenralFunc()).makeTestObject('ul', '', 'pvtRows pvtAxisContainer ui-sortable ui-droppable', 'pvt-config-rowfields', '','' ,'')

		if(WebUI.verifyElementPresent(rowFieldsArea,10)==true)
		{
			(new generalFunc.AllgenralFunc()).shortDelay()
			WebUI.dragAndDropToObject(listOfAttrs, rowFieldsArea, FailureHandling.STOP_ON_FAILURE)
			KeywordUtil.logInfo("Attributes dropped in row field area")
		}
		else
		{
			KeywordUtil.logInfo("rowfields is not available")
		}


	}//rowFieldsDD()




	@Keyword
	//Drag and drop attributes into colfields
	//3 arguments colfields with semicolon separator and returns to values
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
			TestObject listOfAttrs = (new generalFunc.AllgenralFunc()).makeTestObject('li', '', 'ui-state-default ui-draggable ui-draggable-handle multdrag', '', 'data-attributenm',toval ,'')

			//Colfields Area
			TestObject colFieldsArea = (new generalFunc.AllgenralFunc()).makeTestObject('ul', '', 'pvtColumns pvtAxisContainer ui-sortable ui-droppable', 'pvt-config-columnfields', '','' ,'')

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
	//Drag and drop measure fields
	//requires 3 arguments data fields with semicolon separator and returns to value
	def dataFieldsDD(String dataFields, String nSep , String to)
	{
		//Check for dataFields not equal to null
		//Call returned value from 'chkMeasureT' function in make object of listofMeasures
		//Make object for dataField area.verifyelementpresent ,if yes then drop listofAttrs in to dataFields area
		if(dataFields != '')
		{
			KeywordUtil.logInfo('entered data fields function')
			(new generalFunc.AllgenralFunc()).shortDelay()
			String toval = chkMeasuresT(dataFields,  nSep, to)
			KeywordUtil.logInfo('Returned attribute name in datafiledsDD to value'+toval)
			//select list of selected measures for datafields
			TestObject listOfMsrs = (new generalFunc.AllgenralFunc()).makeTestObject('li', '', 'ui-state-default ui-draggable ui-draggable-handle multdrag', '', 'data-measurenm',toval ,'')

			//Datafields Area
			TestObject dataFieldsArea = (new generalFunc.AllgenralFunc()).makeTestObject('ul', '', 'pvtAggregations pvtAxisContainer ui-sortable ui-droppable', 'pvt-config-datafields', '','' ,'')

			if(WebUI.verifyElementPresent(dataFieldsArea,10)==true)
			{
				(new generalFunc.AllgenralFunc()).shortDelay()
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
	//Set aggregation for measure
	//requires 3 arguments measure names and aggregation with semicolon separator
	def setAggrForMsr(String msrNameT,String aggrType,String nSep)
	{
		if(msrNameT != '' & aggrType != '' )
		{
			KeywordUtil.logInfo('msrname in data fields' +msrNameT )
			def mulmsrNames = msrNameT.split(nSep)
			def mulaggrs = aggrType.split(nSep)
			int lenMul = mulmsrNames.size()
			for (def index : (0..lenMul-1))
			{
				String clickMsrName = mulmsrNames[index].trim()
				String clickAggrName = mulaggrs[index].trim()
				KeywordUtil.logInfo('msrname in data fields' + clickMsrName+''+clickAggrName)
				//Make object and Click on measure name to set aggregation
				TestObject clickOnMsr = (new generalFunc.AllgenralFunc()).makeTestObject('span', '', 'aggrAttr wide', '', 'data-attr',clickMsrName ,'')
				WebUI.click(clickOnMsr)
				(new generalFunc.AllgenralFunc()).shortDelay()
				//Make object for list of aggregation dropdown and click
				TestObject aggrDropdown = (new generalFunc.AllgenralFunc()).makeTestObject('div', '', 'select2-container form-control', 's2id_aggr-func-selection-2', '','' ,'')

				//TestObject aggrDropdown = makeTestObject('', 'Average', '', '', 'href','javascript:void(0)' ,'')
				if(WebUI.verifyElementVisible(aggrDropdown,FailureHandling.OPTIONAL)==true)
				{
					KeywordUtil.logInfo("If block...........Element found")
					//WebUI.verifyElementClickable(aggrDropdown)
					WebUI.click(aggrDropdown)

					//Make object for list of search input in aggregation dropdown and set search keyword
					TestObject searchAggr = (new generalFunc.AllgenralFunc()).makeTestObject('input', '', 'select2-input', 's2id_autogen3_search', '','' ,'')
					WebUI.click(searchAggr)
					WebUI.setText(searchAggr,clickAggrName)
					WebUI.sendKeys(searchAggr,Keys.chord(Keys.ENTER))

					//Make object for Apply Button and then click
					TestObject applyAggr = (new generalFunc.AllgenralFunc()).makeTestObject('button', '', 'btn-apply-2', '', '','' ,'')
					WebUI.click(applyAggr)
					KeywordUtil.markPassed("Set aggregation for Deepdata measure")
				}else
				{
					KeywordUtil.logInfo("Else block...........Element found")
					TestObject aggrDropdownDD = (new generalFunc.AllgenralFunc()).makeTestObject('div', '', 'select2-container form-control', 's2id_aggr-func-selection', '','' ,'')
					WebUI.click(aggrDropdownDD)

					//Make object for list of search input in aggregation dropdown and set search keyword
					TestObject searchAggr = (new generalFunc.AllgenralFunc()).makeTestObject('input', '', 'select2-input', 's2id_autogen2_search', '','' ,'')
					WebUI.click(searchAggr)

					WebUI.setText(searchAggr,clickAggrName)
					WebUI.sendKeys(searchAggr,Keys.chord(Keys.ENTER))

					//Make object for Apply Button and then click
					TestObject applyAggr = (new generalFunc.AllgenralFunc()).makeTestObject('button', '', 'btn-apply', '', '','' ,'')
					WebUI.click(applyAggr)
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
	//Click on TabularApply button
	def TabularApply()
	{

		openTabularConfig()
		TestObject applyT = (new generalFunc.AllgenralFunc()).makeTestObject('button', '', 'tabularBtn', 'applyTabular', '','' ,'')
		WebUI.click(applyT)
		(new generalFunc.AllgenralFunc()).shortDelay()
	}

	@Keyword
	//Download exported file from notifications menu
	//Requires one argument with the export file name
	def downloadExportFile(String exportFileName)
	{
		if(exportFileName!='')
		{
			//Click on notification icon and export file

			//Make object for notification and click
			TestObject notificationBtn = (new generalFunc.AllgenralFunc()).makeTestObject('', '', 'alertsvgicon', '', '','' ,'')
			WebUI.click(notificationBtn)

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
	}//downloadExportFile()

	@Keyword
	//compares both the tabulars
	//Requires 3 arguments exportfilename,expected and actual path
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

	@Keyword
	//function has all tabular related functions to config tabular
	def configTabular(String rowFields, String colFields, String dataFields, String nSep,String vSep, String to,String msrNameT,String aggrType,String rowSubTotal,String rFieldnm,String cFieldnm,String preview,String ImageTabularEnable, String colSubTotal, String grandTotal,String itemCount, String tableName)
	{
		if(rowFields != '' | colFields != '' & dataFields != '' ){
			(new standardFunc.AllstandardFunc()).switchToTabular()
			openTabularConfig()
		}

		if(rowFields != '')
		{

			rowFieldsDD(rowFields, nSep , to)
			(new generalFunc.AllgenralFunc()).shortDelay()
			//rowSubTotalStatus15(rowSubTotal,rFieldnm)
			rowSubTotal15(rowSubTotal,rFieldnm,vSep)
		}
		if(colFields != '')
		{
			colFieldsDD(colFields,nSep ,  to)
			(new generalFunc.AllgenralFunc()).shortDelay()
			colSubTotal15(colSubTotal,cFieldnm,vSep)
		}
		if(dataFields != '')
		{
			dataFieldsDD(dataFields, nSep ,  to)
			(new generalFunc.AllgenralFunc()).shortDelay()
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

		if(itemCount != '')
		{
			itemCountStatus(itemCount)
		}
		if(tableName != '')
		{
			setTableName(tableName)
		}

		else
		{
			KeywordUtil.logInfo("No tabular Info")
			(new generalFunc.AllgenralFunc()).shortDelay()
		}

	}//def configTabular()

	@Keyword
	//Created on 27-04-2020
	//function has all tabular related functions to config tabular

	def configTabularR15(String rowFields, String colFields, String dataFields, String nSep,String vSep, String to,String msrNameT,String aggrType,String rowSubTotal,String rFieldnm,String colSubTotal,String cFieldnm,String preview,String ImageTabularEnable, String itemCount, String tableName)
	{
		if(rowFields != '' | colFields != '' & dataFields != '' ){
			(new generalFunc.AllgenralFunc()).shortDelay()
			(new standardFunc.AllstandardFunc()).switchToTabular()
			openTabularConfig()
		}

		if(rowFields != '')
		{

			rowFieldsDD(rowFields, nSep , to)
			(new generalFunc.AllgenralFunc()).shortDelay()
			//rowSubTotalStatus15(rowSubTotal,rFieldnm)
			rowSubTotal15(rowSubTotal,rFieldnm,vSep)
		}
		if(colFields != '')
		{
			colFieldsDD(colFields,nSep ,  to)
			(new generalFunc.AllgenralFunc()).shortDelay()
			colSubTotal15(colSubTotal,cFieldnm,vSep)
		}
		if(dataFields != '')
		{
			dataFieldsDD(dataFields, nSep ,  to)
			(new generalFunc.AllgenralFunc()).shortDelay()
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

		if(itemCount != '')
		{
			itemCountStatus(itemCount)
		}
		if(tableName != '')
		{
			setTableName(tableName)
		}

		else
		{
			KeywordUtil.logInfo("No tabular Info")
			(new generalFunc.AllgenralFunc()).shortDelay()
		}

	}//configTabularR15()


	@Keyword
	//copyPivot copies tabular
	//Requires one argument with the status
	def copyPivotTbl(String copyPivot)
	{
		if(copyPivot == 'Yes')
		{
			//Click on Copy Pivot Icon inorder to copy to clipboard
			TestObject copyPivotObj = (new generalFunc.AllgenralFunc()).makeTestObject('li', '', '', 'copyPivot', '','' ,'')
			WebUI.click(copyPivotObj)
			(new generalFunc.AllgenralFunc()).shortDelay()
			//Click on 'OK' button of copy to clipboard success message
			TestObject copyPivotOKBtn = (new generalFunc.AllgenralFunc()).makeTestObject('button', '', 'confirm', '', '','' ,'')
			WebUI.click(copyPivotOKBtn)
			(new generalFunc.AllgenralFunc()).shortDelay()
			KeywordUtil.logInfo("Clicked on copy Pivot")
		}
		else
		{
			KeywordUtil.logInfo("Copypivot table info is blank")
		}

	}// copyPivotTbl()

	@Keyword
	//Remove all tables
	//Requires one argument to perform the action
	def resetAllTbls(String removeAlltbls)
	{
		if(removeAlltbls != '')
		{
			//Click on Remove all tbls icon
			TestObject resetAllTbls = (new generalFunc.AllgenralFunc()).makeTestObject('li', '', '', 'resetPivot', '','' ,'')
			WebUI.click(resetAllTbls)
			(new generalFunc.AllgenralFunc()).shortDelay()
			//Click on 'Ok' to remove all tables
			TestObject Okbtn = (new generalFunc.AllgenralFunc()).makeTestObject('button', '', 'cancel', '', '','' ,'')
			WebUI.click(Okbtn)
			(new generalFunc.AllgenralFunc()).shortDelay()

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
			TestObject duplicateBtn = (new generalFunc.AllgenralFunc()).makeTestObject('button', '', 'createcopy', 'duplicateTabular', '','' ,'')
			WebUI.click(duplicateBtn)
			(new generalFunc.AllgenralFunc()).shortDelay()

		}else
		{
			KeywordUtil.logInfo("Duplicate Table info is blank")
		}

	}// duplicatePivot()

	@Keyword
	//Clicks on new tabular(+) icon
	//Requires one argument with the status 'Enable' inorder to create new table
	def newTabular(String newTbl)
	{
		if(newTbl == 'Enable')
		{
			//Check for open tabular config
			//openTabularConfig()
			//Click on create new tabular Icon
			WebDriver driver = DriverFactory.getWebDriver()
			TestObject newTable = (new generalFunc.AllgenralFunc()).makeTestObject('li', '', '', 'newTabular', '','' ,'')

			if(WebUI.verifyElementPresent(newTable,10,FailureHandling.OPTIONAL))
			{

				WebElement element = WebUiCommonHelper.findWebElement(newTable,10)
				JavascriptExecutor executor = ((driver) as JavascriptExecutor)
				executor.executeScript('arguments[0].click()', element)

				//WebUI.click(newTable)
				(new generalFunc.AllgenralFunc()).shortDelay()

			}else
			{
				KeywordUtil.logInfo("Could not find new table")

				//Click on Kanvas Logo
				WebUI.click(findTestObject('Object Repository/Final Objects/MySelection/MainButns/kanvaslogo'))

				//alerts handling
				(new generalFunc.AllgenralFunc()).alertHandling()
				//Click on Tabular
				(new standardFunc.AllstandardFunc()).switchToTabular()
				//Click on new tabular
				WebElement element = WebUiCommonHelper.findWebElement(newTable,10)
				JavascriptExecutor executor = ((driver) as JavascriptExecutor)
				executor.executeScript('arguments[0].click()', element)

				//WebUI.click(newTable)
				(new generalFunc.AllgenralFunc()).shortDelay()
			}
		}else
		{

			KeywordUtil.logInfo("Create NewTable info is blank")
		}

	}// newTabular()

	@Keyword
	//Clicks on attribute values
	//Requires 3 arguments attribute name with semicolon separator and values coma separator
	static tblclickAttrVals(String tattrNames,String tvals, String vSep)
	{
		def mulVals = tvals.split(vSep)

		int lenMul = mulVals.size()

		for (def index : (0..lenMul-1))
		{
			WebUI.delay(5)
			//Click on search input with in the attribute
			//TestObject SKeyword = makeTestObject('li', '', 'form-control searchInput searchInTabularAttr', '', 'placeholder','Search Values' ,'')
			//TestObject SKeyword = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','' ,'//li[@class="attr-name attrnmtabular open-attr-nm" and @data-attrname="'+tattrNames+'"]//input[@class="form-control searchInput searchInTabularAttr"]')
			TestObject SKeyword = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','' ,'//li[@class="attr-name attrnmtabular open-attr-nm" and @data-attrname="'+tattrNames+'"]//ul[@class="attr-val-lst"]//li[@class="searchLi"]//div/input')
			WebUI.click(SKeyword)
			WebUI.setText(SKeyword,mulVals[index].trim())
			WebUI.sendKeys(SKeyword,Keys.chord(Keys.ENTER))
			//Click on attribute values
			TestObject attrV = (new generalFunc.AllgenralFunc()).makeTestObject('li', '', 'txt-attr-val', '', 'data-attrval', mulVals[index].trim(), '')
			WebUI.click(attrV)
			WebUI.delay(5)
		}


	}//tblclickAttrVals

	@Keyword
	//Clicks in searchbox with in the attribute
	def clickAttrValSearchInput()
	{

		//Click on search input with in the attribute
		TestObject SKeyword = (new generalFunc.AllgenralFunc()).makeTestObject('li', '', 'searchInTabularAttr', '', 'placeholder','Search Values' ,'')
		WebUI.click(SKeyword)
		(new generalFunc.AllgenralFunc()).shortDelay()

	}// clickAttrValSearchInput()

	@Keyword
	//Clicks on attribute names and expands
	//Clicks on select all checkbox

	def tblselAttrNdVals(String tattrNames,String tattrVals, String nSep, String vSep)
	{


		if(tattrNames != ''){

			WebDriver driver = DriverFactory.getWebDriver()


			//TestObject closeSlider = makeTestObject('', '', '', '', '','' ,'id("myselection-content")/div[@class="displayTabMenu col-md-12 col-lg-12"]/div[@class="pivot-config zerozindex"]/div[@class="pivot-config-box"]/div[@class="pivot-config-icon"]/i[@class="setting-icon-lg-white"]')
			//WebUI.click(closeSlider)
			(new generalFunc.AllgenralFunc()).shortDelay()
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
				//String unChkDiv = '//li[@data-attrname="'+attr+'"]//label[@class="lbl_cls chkSelAttr_lbl"]'
				String unChkDiv = '//li[@class="attr-name attrnmtabular" and @data-attrname="'+attr+'"]/div/span[@id="ripple"]'
				TestObject chkbxLbl = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', unChkDiv)
				WebElement element = WebUiCommonHelper.findWebElement(chkbxLbl,10)
				JavascriptExecutor executor = ((driver) as JavascriptExecutor)
				executor.executeScript('arguments[0].click()', element)
				//WebUI.click(attrName)
				//longDelay()
				KeywordUtil.logInfo("Checkbox Clicked")
				(new generalFunc.AllgenralFunc()).shortDelay()

				//Click on attribute name to expand
				String xpathexpand = '//li[@class="attr-name attrnmtabular" and @data-attrname="'+attr+'"]//div[@class = "wrap-attr-name-tabular"]'
				String xpathcollapse = '//li[@class="attr-name attrnmtabular open-attr-nm" and @data-attrname="'+attr+'"]//div[@class = "wrap-attr-name-tabular"]'
				//Make Object to expand attribute name
				TestObject attrNE = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '',xpathexpand)
				////Make Object to collpase attribute name
				TestObject attrNC = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '',xpathcollapse)

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
	//remove attribute or measure
	//Requires 2 arguments attr/msr name with semicolon separator
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
				TestObject rowfieldObj = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','' ,rowfields)
				TestObject colfieldObj = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','' ,colfields)
				TestObject datafieldObj = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '','' ,datafields)

				if(WebUI.verifyElementPresent(rowfieldObj,10,FailureHandling.OPTIONAL))
				{
					WebUI.click(rowfieldObj)
					(new generalFunc.AllgenralFunc()).shortDelay()
				}
				else if (WebUI.verifyElementPresent(colfieldObj,10,FailureHandling.OPTIONAL))
				{
					WebUI.click(colfieldObj)
					(new generalFunc.AllgenralFunc()).shortDelay()
				}
				else
				{
					WebUI.click(datafieldObj)
					(new generalFunc.AllgenralFunc()).shortDelay()
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
	//Sort function R14 version
	//Sort on column fields in tabular
	//Requires attr/msr name with the semi colon separator and status coma separator
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
	//Sort Function for R15 version
	//Sort order on column fields
	//Requires attr/msr name with the semi colon separator and statsus with the coma separator
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
	//R14 version set sort order
	static tmDoSort(String tsortAttrName, String tattrStatus,String vSep)
	{

		def mulmsrStatus = tattrStatus.split(vSep)
		int lenMulmsrStatus = mulmsrStatus.size()
		for (def index : (0..lenMulmsrStatus-1))
		{
			//Make XPath for column 'th'
			String xpathTH = '//th [@class="pvtColLabel controlRow" or @class="pvtColLabel controlRow lastTotalCell"]'

			//Make Test Object based on Xpath and msrname from data sheet
			TestObject thObj = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', 'data-control-col', tsortAttrName ,xpathTH)
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
					(new generalFunc.AllgenralFunc()).shortDelay()

				}
				else if(s.contains("sort-icon-xs-darkgrey"))
				{
					KeywordUtil.logInfo("It's in Disable Status")
					WebUI.click(thObj)
					(new generalFunc.AllgenralFunc()).shortDelay()
				}
				else
				{
					KeywordUtil.logInfo("It's in Descend Status")
					WebUI.click(thObj)
					(new generalFunc.AllgenralFunc()).shortDelay()
					WebUI.click(thObj)
					(new generalFunc.AllgenralFunc()).shortDelay()
				}
			}// if(tattrStatus == "ascend")
			else if(tattrStatus == "descend")
			{
				if(s.contains("descend"))
				{

					KeywordUtil.logInfo("It's in Descend status only")
					(new generalFunc.AllgenralFunc()).shortDelay()
					//WebUI.click(thObj)
					//WebUI.delay(10)
				}
				else if(s.contains("sort-icon-xs-darkgrey"))
				{
					KeywordUtil.logInfo("It's in Disable Status")
					WebUI.click(thObj)
					(new generalFunc.AllgenralFunc()).shortDelay()
					WebUI.click(thObj)
					(new generalFunc.AllgenralFunc()).shortDelay()
				}
				else
				{
					KeywordUtil.logInfo("It's in Ascend Status")
					WebUI.click(thObj)
					(new generalFunc.AllgenralFunc()).shortDelay()

				}
			}//else if(tattrStatus == "descend")
			else
			{
				if(s.contains("sort-icon-xs-darkgrey"))
				{

					KeywordUtil.logInfo("It's in Disable status only")
					(new generalFunc.AllgenralFunc()).shortDelay()
					//WebUI.click(thObj)
					//WebUI.delay(10)
				}
				else if(s.contains("ascend"))
				{
					KeywordUtil.logInfo("It's in Ascend Status")
					WebUI.click(thObj)
					(new generalFunc.AllgenralFunc()).shortDelay()
					WebUI.click(thObj)
					(new generalFunc.AllgenralFunc()).shortDelay()
				}
				else
				{
					KeywordUtil.logInfo("It's in Descend Status")
					WebUI.click(thObj)
					(new generalFunc.AllgenralFunc()).shortDelay()

				}

			}//disable sort


		}//for (def index : (0..lenMulmsrStatus-1))




	}//tmDoSort

	//Created on 14-Apr as per R15 version
	//Selection of sort order is changed in R15
	//Capture the new selection of sort order accessor
	@Keyword
	//Sort on attribute in tabular
	static tmDoSort15(String tsortAttrName, String tattrStatus,String vSep)
	{

		def mulmsrStatus = tattrStatus.split(vSep)
		int lenMulmsrStatus = mulmsrStatus.size()
		for (def index : (0..lenMulmsrStatus-1))
		{
			//Make XPath for column 'th'
			String xpathTH = '//th [@class="pvtColLabel controlRow row-1th" or @class="pvtColLabel controlRow row-1th lastTotalCell"]'

			//Make Test Object based on Xpath and msrname from data sheet
			TestObject thObj = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', 'data-control-col', tsortAttrName ,xpathTH)
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
					(new generalFunc.AllgenralFunc()).shortDelay()
				}else
				{
					//click on column header
					WebUI.click(thObj)
					//Click on Ascend Text
					//Make object for Ascend Order
					TestObject ascOrder = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '' ,'//div[@class="sortmenudiv"]//span[@class="pvtSorterClassSpan ascendSpan"]')
					//Click on Ascend Object
					WebUI.click(ascOrder)
					KeywordUtil.logInfo("Sort Order Set to Ascend")
					(new generalFunc.AllgenralFunc()).shortDelay()
				}

			}//if(tattrStatus == "ascend")

			else if(tattrStatus == "descend")
			{
				if(s.contains("descend"))
				{

					KeywordUtil.logInfo("It's in Descend status only")
					(new generalFunc.AllgenralFunc()).shortDelay()
				}
				else
				{

					//click on column header
					WebUI.click(thObj)
					//Click on Descend Text
					//Make object for Descend Order
					TestObject descOrder = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '' ,'//div[@class="sortmenudiv"]//span[@class="pvtSorterClassSpan descendSpan"]')
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
					(new generalFunc.AllgenralFunc()).shortDelay()
				}
				else
				{
					//click on column header
					WebUI.click(thObj)
					//Click on Disable Text
					//Make object for Disable Order
					TestObject disableOrder = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '' ,'//div[@class="sortmenudiv"]//span[@class="pvtSorterClassSpan nosortSpan"]')
					//Click on Disbale Object
					WebUI.click(disableOrder)
					KeywordUtil.logInfo("Sort Order Set to Disable")
					(new generalFunc.AllgenralFunc()).shortDelay()
				}

			}//disable sort



		}//for (def index : (0..lenMulmsrStatus-1))




	}//tmDoSort15



	//row sort

	@Keyword
	//Row sort in tabular
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
			TestObject rowthObjASC = (new generalFunc.AllgenralFunc()).makeTestObject('i', '', 'ascend', '', 'data-content', tsortRAttrName ,'')
			TestObject rowthObjDESC = (new generalFunc.AllgenralFunc()).makeTestObject('i', '', 'descend', '', 'data-content', tsortRAttrName ,'')
			TestObject rowthObjDIS = (new generalFunc.AllgenralFunc()).makeTestObject('i', '', 'sort-icon-xs-darkgrey', '', 'data-content', tsortRAttrName ,'')

			String attrClass
			//to do ASCEND
			if(tattrRStatus == 'ascend' )
			{

				if((WebUI.verifyElementPresent(rowthObjASC, 10, FailureHandling.OPTIONAL)) == true)
				{
					KeywordUtil.logInfo("It's in Ascend status only")
					(new generalFunc.AllgenralFunc()).shortDelay()
				}
				else if((WebUI.verifyElementPresent(rowthObjDIS, 10, FailureHandling.OPTIONAL)) == true)
				{
					KeywordUtil.logInfo("It's in Disable Status")
					WebUI.click(rowthObjDIS)
					//clickUsingJS(rowthObjDIS,10)
					(new generalFunc.AllgenralFunc()).shortDelay()
				}
				else
				{
					KeywordUtil.logInfo("It's in Descend Status")
					WebUI.click(rowthObjDESC)
					(new generalFunc.AllgenralFunc()).shortDelay()
					WebUI.click(rowthObjDIS)
					(new generalFunc.AllgenralFunc()).shortDelay()
				}
			}//if(tattrRStatus == ' ascend ' )
			else if(tattrRStatus == 'descend' )
			{
				if((WebUI.verifyElementPresent(rowthObjDESC, 10, FailureHandling.OPTIONAL)) == true)
				{
					KeywordUtil.logInfo("It's in Descend status only")
					(new generalFunc.AllgenralFunc()).shortDelay()
				}
				else if((WebUI.verifyElementPresent(rowthObjASC, 10, FailureHandling.OPTIONAL)) == true)
				{
					KeywordUtil.logInfo("It's in Ascend Status")
					WebUI.click(rowthObjASC)
					(new generalFunc.AllgenralFunc()).shortDelay()
				}
				else
				{
					KeywordUtil.logInfo("It's in Disable Status")
					WebUI.click(rowthObjDIS)
					(new generalFunc.AllgenralFunc()).shortDelay()
					WebUI.click(rowthObjASC)
					(new generalFunc.AllgenralFunc()).shortDelay()
				}
			}//else if(tattrRStatus == ' descend ' )
			else if(tattrRStatus == 'disable')
			{
				if((WebUI.verifyElementPresent(rowthObjDIS, 10, FailureHandling.OPTIONAL)) == true)
				{
					KeywordUtil.logInfo("It's in Disable status only")
					(new generalFunc.AllgenralFunc()).shortDelay()
				}
				else if((WebUI.verifyElementPresent(rowthObjDESC, 10, FailureHandling.OPTIONAL)) == true)
				{
					KeywordUtil.logInfo("It's in Descend Status")
					WebUI.click(rowthObjDESC)
					(new generalFunc.AllgenralFunc()).shortDelay()
				}
				else
				{
					KeywordUtil.logInfo("It's in Ascend Status")
					WebUI.click(rowthObjASC)
					(new generalFunc.AllgenralFunc()).shortDelay()
					WebUI.click(rowthObjDESC)
					(new generalFunc.AllgenralFunc()).shortDelay()
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
			TestObject thObj = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '',xpathTH)
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
					(new generalFunc.AllgenralFunc()).shortDelay()
				}else
				{
					//click on column header
					WebUI.click(thObj)
					//Click on Ascend Text
					//Make object for Ascend Order
					TestObject ascOrder = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '' ,'//div[@class="sortmenudiv"]//span[@class="pvtSorterClassSpan ascendSpan"]')
					//Click on Ascend Object
					WebUI.click(ascOrder)
					KeywordUtil.logInfo("Sort Order Set to Ascend")
					(new generalFunc.AllgenralFunc()).shortDelay()
				}

			}//if(tattrStatus == "ascend")

			else if(tattrRStatus == "descend")
			{
				if(s.contains("descend"))
				{

					KeywordUtil.logInfo("It's in Descend status only")
					(new generalFunc.AllgenralFunc()).shortDelay()
				}
				else
				{

					//click on column header
					WebUI.click(thObj)
					//Click on Descend Text
					//Make object for Descend Order
					TestObject descOrder = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '' ,'//div[@class="sortmenudiv"]//span[@class="pvtSorterClassSpan descendSpan"]')
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
					(new generalFunc.AllgenralFunc()).shortDelay()
				}
				else
				{
					//click on column header
					WebUI.click(thObj)
					//Click on Disable Text
					//Make object for Disable Order
					TestObject disableOrder = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '' ,'//div[@class="sortmenudiv"]//span[@class="pvtSorterClassSpan nosortSpan"]')
					//Click on Disbale Object
					WebUI.click(disableOrder)
					KeywordUtil.logInfo("Sort Order Set to Disable")
					(new generalFunc.AllgenralFunc()).shortDelay()
				}

			}//disable sort



		}//for (def index : (0..lenMulmsrStatus-1))





	}//tmrowDoSortR15

	@Keyword
	//Clicks on attribute names and expands
	//Clicks on select all checkbox

	def tblFilters(String tattrNames,String tattrVals, String nSep, String vSep)
	{


		if(tattrNames != ''){

			WebDriver driver = DriverFactory.getWebDriver()


			//TestObject closeSlider = makeTestObject('', '', '', '', '','' ,'id("myselection-content")/div[@class="displayTabMenu col-md-12 col-lg-12"]/div[@class="pivot-config zerozindex"]/div[@class="pivot-config-box"]/div[@class="pivot-config-icon"]/i[@class="setting-icon-lg-white"]')
			//WebUI.click(closeSlider)
			(new generalFunc.AllgenralFunc()).shortDelay()
			openTabularConfig()

			def mulattrN = tattrNames.split(nSep)
			def mulVals =  tattrVals.split(nSep)
			int mulattrLen = mulattrN.size()
			KeywordLogger log = new KeywordLogger()

			for(def index :(0..mulattrLen-1))
			{
				String attr = mulattrN[index].trim()


				//Click on unselect checkbox
				String unChkLbl = '//li[@class="attr-name attrnmtabular" and @data-attrname="'+attr+'"]/div/label[@class="lbl_cls chkSelAttr_lbl"]'
				TestObject chkbxLbl = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '', unChkLbl)
				WebElement element = WebUiCommonHelper.findWebElement(chkbxLbl,10)
				JavascriptExecutor executor = ((driver) as JavascriptExecutor)
				executor.executeScript('arguments[0].click()', element)
				KeywordUtil.logInfo("Checkbox Clicked")
				(new generalFunc.AllgenralFunc()).shortDelay()

				//Click on attribute name to expand
				String xpathexpand = '//li[@class="attr-name attrnmtabular" and @data-attrname="'+attr+'"]//div[@class = "wrap-attr-name-tabular"]'
				String xpathcollapse = '//li[@class="attr-name attrnmtabular open-attr-nm" and @data-attrname="'+attr+'"]//div[@class = "wrap-attr-name-tabular"]'
				//Make Object to expand attribute name
				TestObject attrNE = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '',xpathexpand)
				////Make Object to collpase attribute name
				TestObject attrNC = (new generalFunc.AllgenralFunc()).makeTestObject('', '', '', '', '', '',xpathcollapse)
				WebUI.click(attrNE)
				WebUI.delay(5)

				//Call attribute values function

				tblclickAttrVals(attr,mulVals[index],vSep)
				WebUI.click(attrNC)

			}

			TabularApply()
		}else
		{
			KeywordUtil.logInfo("attribute name is blank")
		}
	}//tblFilters



























}
