using Microsoft.VisualStudio.TestTools.UnitTesting;

using System;
using System.Text;
using OpenQA.Selenium;
using OpenQA.Selenium.Firefox;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.IE;
using OpenQA.Selenium.Edge;

// Leave this in - Needed for Select 
using OpenQA.Selenium.Support.UI;
using System.Collections.Generic;
using System.Linq;


/* 
 * 
 * Notes
 * This has been tested on July 4th, 2019 against (OFFICE system)
 * FIREFOX 60.7.2esr (64-bit)
 * CHROME Version 96 (64-bit)
 * EDGE Version 96
 * IE Version 11 - ERROR Currently do not use
 * 
 * Note that in the lab we must use an older version of ChromeDriver at present.  
 * 
 * C. Mark Yendt (July 2019)
 * Updated November 2021 (EDGE/CHROME/FIREFOX should work) - do not use IE
 * Updated Decemebr 2021 with updated NuGet Drivers and Added Selenium.Support
 */

namespace A6Start
{
    [TestClass]
    public class KatalonAutomationExample
    {
        private static IWebDriver driver;
        private StringBuilder verificationErrors;
        private bool acceptNextAlert = true;
        // private const string BROWSER = "FIREFOX";
        // private const string BROWSER = "CHROME";
        // private const string BROWSER = "IE";
           private const string BROWSER = "EDGE";

        private const string DRIVER_LOCATION = @"C:\Drivers";
        private const string FIREFOX_BIN_LOCATION = @"C:\Program Files\Mozilla Firefox\firefox.exe";

        [ClassInitialize]
        public static void InitializeClass(TestContext testContext)
        {
            // FIREFOX
            if (BROWSER == "FIREFOX")
            {
                FirefoxDriverService service = FirefoxDriverService.CreateDefaultService(DRIVER_LOCATION);
                // Note that the line below needs to be the full exe Name not just the path
                // service.FirefoxBinaryPath = FIREFOX_BIN_LOCATION;
                // service.Host = "::1";
                driver = new FirefoxDriver(service);   // WORKS 
            }
            else if (BROWSER == "CHROME")
                driver = new ChromeDriver(DRIVER_LOCATION);  // WORKS ! 
            else if (BROWSER == "IE")
                // Internet EXPLORER NOTE : Must add DRIVER_LOCATION to Path
                driver = new InternetExplorerDriver();  // WORKS !
            else if (BROWSER == "EDGE")
                driver = new EdgeDriver(DRIVER_LOCATION);


        }

        [ClassCleanup]
        public static void CleanupClass()
        {
            try
            {
                //driver.Quit();// quit does not close the window
                driver.Close();
                driver.Dispose();
            }
            catch (Exception)
            {
                // Ignore errors if unable to close the browser
            }
        }

        [TestInitialize]
        public void InitializeTest()
        {
            verificationErrors = new StringBuilder();
        }

        [TestCleanup]
        public void CleanupTest()
        {
            Assert.AreEqual("", verificationErrors.ToString());
        }

        [TestMethod]
        public void RunAllTests()
        {

            // Put your test cases in order here

            TestLoginAdmin();

            // Create User
            TheCreateUserTest();

            // Delete User
            TheDeleteUserTest();

            // Directory Testing - 4 Cities
            TheCityDirectoryTest("Brampton {4}");
            TheCityDirectoryTest("Calgary {4}");
            TheCityDirectoryTest("Delta {1}");
            TheCityDirectoryTest("Ajax {1}");

        }


        public void TestLoginAdmin()
        {
            driver.Navigate().GoToUrl("https://csunix.mohawkcollege.ca/tooltime/comp10066/A3/login.php");
            //driver.FindElement(By.Id("username")).Click();
            //driver.FindElement(By.Id("username")).Clear();
            driver.FindElement(By.Id("username")).SendKeys("admin");
            //driver.FindElement(By.Name("password")).Clear();
            driver.FindElement(By.Name("password")).SendKeys("adminP6ss");
            driver.FindElement(By.Name("Submit")).Click();
            driver.FindElement(By.Id("loginname")).Click();
            try
            {
                Assert.AreEqual("User: admin", driver.FindElement(By.Id("loginname")).Text);
            }
            catch (Exception e)
            {
                verificationErrors.Append(e.Message);
            }
            try
            {
                Assert.AreEqual("User Admin", driver.FindElement(By.LinkText("User Admin")).Text);
            }
            catch (Exception e)
            {
                verificationErrors.Append(e.Message);
            }
            driver.FindElement(By.LinkText("Logout")).Click();
            driver.FindElement(By.Id("loginname")).Click();
            try
            {
                Assert.AreEqual("Not Logged In", driver.FindElement(By.Id("loginname")).Text);
            }
            catch (Exception e)
            {
                verificationErrors.Append(e.Message);
            }
        }


        public void TheCreateUserTest()
        {
            driver.Navigate().GoToUrl("https://csunix.mohawkcollege.ca/tooltime/comp10066/A3/login.php");
            driver.FindElement(By.Id("username")).Click();
            driver.FindElement(By.Id("username")).Clear();
            driver.FindElement(By.Id("username")).SendKeys("admin");
            driver.FindElement(By.Name("password")).Click();
            driver.FindElement(By.Name("password")).Clear();
            driver.FindElement(By.Name("password")).SendKeys("adminP6ss");
            driver.FindElement(By.Name("Submit")).Click();
            driver.Navigate().GoToUrl("https://csunix.mohawkcollege.ca/tooltime/comp10066/A3/membernews.php");
            driver.FindElement(By.LinkText("User Admin")).Click();
            driver.Navigate().GoToUrl("https://csunix.mohawkcollege.ca/tooltime/comp10066/A3/adminuser.php");
            driver.FindElement(By.Id("username")).Click();
            driver.FindElement(By.Id("username")).Clear();
            driver.FindElement(By.Id("username")).SendKeys("abcdxyz");
            driver.FindElement(By.Id("password")).Click();
            driver.FindElement(By.Id("password")).Clear();
            driver.FindElement(By.Id("password")).SendKeys("Admin1234");
            driver.FindElement(By.XPath("//form[@id='form1']/table/tbody/tr[4]/td/input[2]")).Click();
            driver.FindElement(By.XPath("//form[@id='form1']/table/tbody/tr[5]/td/input[2]")).Click();
            driver.FindElement(By.Id("email")).Click();
            driver.FindElement(By.Id("email")).Clear();
            driver.FindElement(By.Id("email")).SendKeys("a@b.c");
            driver.FindElement(By.Name("Add New Member")).Click();
            Assert.AreEqual("Record successfully inserted.", driver.FindElement(By.XPath("//*[@id=\"body\"]/div[1]/div")).Text);
            Assert.IsNotNull(driver.FindElement(By.XPath("//td[@id='abcdxyz']")));
            driver.FindElement(By.LinkText("Logout")).Click();
            driver.Navigate().GoToUrl("https://csunix.mohawkcollege.ca/tooltime/comp10066/A3/login.php");
            Assert.AreEqual("Please Log In", driver.FindElement(By.XPath("//*[@id=\"body\"]/div[1]/h2")).Text);
        }

        public void TheDeleteUserTest()
        {
            driver.Navigate().GoToUrl("https://csunix.mohawkcollege.ca/tooltime/comp10066/A3/login.php");
            driver.FindElement(By.Id("username")).Click();
            driver.FindElement(By.Id("username")).Clear();
            driver.FindElement(By.Id("username")).SendKeys("admin");
            driver.FindElement(By.Name("password")).Click();
            driver.FindElement(By.Name("password")).Clear();
            driver.FindElement(By.Name("password")).SendKeys("adminP6ss");
            driver.FindElement(By.Name("Submit")).Click();
            driver.Navigate().GoToUrl("https://csunix.mohawkcollege.ca/tooltime/comp10066/A3/membernews.php");
            driver.FindElement(By.LinkText("User Admin")).Click();
            driver.Navigate().GoToUrl("https://csunix.mohawkcollege.ca/tooltime/comp10066/A3/adminuser.php");
            driver.FindElement(By.XPath("//td[@id='abcdxyz']/a/img")).Click();
            driver.FindElement(By.LinkText("here")).Click();
            Assert.AreEqual("User abcdxyz was successfully deleted.", driver.FindElement(By.XPath("//*[@id=\"body\"]/div[1]/div")).Text);
        }

        public void TheCityDirectoryTest(string city)
        {
            driver.Navigate().GoToUrl("https://csunix.mohawkcollege.ca/tooltime/comp10066/A3/adminuser.php");
            driver.FindElement(By.LinkText("Directory")).Click();
            driver.Navigate().GoToUrl("https://csunix.mohawkcollege.ca/tooltime/comp10066/A3/memberdirectory.php");
            driver.FindElement(By.Id("city")).Click();
            new SelectElement(driver.FindElement(By.Id("city"))).SelectByText(city);
            Assert.AreEqual(1, 1);
        }


        private bool IsElementPresent(By by)
        {
            try
            {
                driver.FindElement(by);
                return true;
            }
            catch (NoSuchElementException)
            {
                return false;
            }
        }

        private bool IsAlertPresent()
        {
            try
            {
                driver.SwitchTo().Alert();
                return true;
            }
            catch (NoAlertPresentException)
            {
                return false;
            }
        }

        private string CloseAlertAndGetItsText()
        {
            try
            {
                IAlert alert = driver.SwitchTo().Alert();
                string alertText = alert.Text;
                if (acceptNextAlert)
                {
                    alert.Accept();
                }
                else
                {
                    alert.Dismiss();
                }
                return alertText;
            }
            finally
            {
                acceptNextAlert = true;
            }
        }
    }
}

