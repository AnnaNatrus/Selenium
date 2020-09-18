package com.test;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import static org.junit.Assert.assertArrayEquals;

public class HomeWorkTest {

    private WebDriver driver;

    @Before
    public void CreateDriver() {
        driver = null;
    }

    @After
    public void CloseDriver() {
        driver.quit();
    }

    @Test
    public void LoginFacebook() {
        driver = new ChromeDriver();
        String url = "https://www.facebook.com/";
        driver.get(url);
        System.out.println("Successfully opened facebook");

        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("Login");
        driver.findElement(By.id("pass")).clear();
        driver.findElement(By.id("pass")).sendKeys("Password");
        driver.findElement(By.id("u_0_b")).click();

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span#ssrb_root_start")));

        driver.findElement(By.cssSelector("span#ssrb_root_start")).isDisplayed();
        System.out.println("Successfully logged in");
    }

    @Test
    public void WikiLinks() {
        String exePath = "E:\\Ganzja\\driver\\msedgedriver.exe";
        System.setProperty("webdriver.edge.driver", exePath);
        driver = new EdgeDriver();
        String url = "https://en.wikipedia.org/wiki/Main_Page";
        driver.get(url);
        java.util.List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println(links.size());
        for (int i = 0; i < links.size(); i++) {
            System.out.println(i + " " + links.get(i).getText());
        }
    }

    @Test
    public void SearchPageChrome() {
        driver = new ChromeDriver();
        String url = "https://www.google.com/";
        driver.get(url);
        driver.manage().window().maximize();
        driver.findElement(By.cssSelector("input.gLFyf.gsfi")).sendKeys("selenium automation testing");
        driver.findElement(By.cssSelector("input.gNO89b")).submit();

        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("html[itemtype='http://schema.org/SearchResultsPage']")));

        driver.findElement(By.cssSelector("html[itemtype='http://schema.org/SearchResultsPage']")).isEnabled();

        int page = 1;
        int maxcycles = 29;
        int isfound = 0;

       do {
            try {
            if (driver.findElement(By.cssSelector("a[href='https://www.lambdatest.com/selenium-automation']")).isDisplayed()) {
                System.out.println("site found at the " + page + " page");
                isfound = 1;

                Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE,500,true).withName("SearchPage").save("E:\\file");

                break; }

            } catch (NoSuchElementException e) {
                try {
                    driver.findElement(By.cssSelector("a#pnnext>span.SJajHc.NVbCr")).isDisplayed();
                    driver.findElement(By.cssSelector("a#pnnext>span.SJajHc.NVbCr")).click();

                new WebDriverWait(driver, 3)
                        .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("html[itemtype='http://schema.org/SearchResultsPage']")));
                    page++;

                } catch (NoSuchElementException ee) {
                    System.out.println("Site not found at all");
                    break; }
            }

        } while (page < maxcycles);

    }

    @Test
    public void SearchPageEdge() {
        String exePath = "E:\\Ganzja\\driver\\msedgedriver.exe";
        System.setProperty("webdriver.edge.driver", exePath);
        driver = new EdgeDriver();
        String url = "https://www.google.com/";
        driver.get(url);
        driver.manage().window().maximize();
        driver.findElement(By.cssSelector("input.gLFyf.gsfi")).sendKeys("selenium automation testing");
        driver.findElement(By.cssSelector("input.gNO89b")).submit();

        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("html[itemtype='http://schema.org/SearchResultsPage']")));

        driver.findElement(By.cssSelector("html[itemtype='http://schema.org/SearchResultsPage']")).isEnabled();

        int page = 1;
        int maxcycles = 29;
        int isfound = 0;

        do {
            try {
                if (driver.findElement(By.cssSelector("a[href='https://www.lambdatest.com/selenium-automation']")).isDisplayed()) {
                    System.out.println("site found at the " + page + " page");
                    isfound = 1;

                    Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE,500,true).withName("SearchPage").save("E:\\file");

                    break; }

            } catch (NoSuchElementException e) {
                try {
                    driver.findElement(By.cssSelector("a#pnnext>span.SJajHc.NVbCr")).isDisplayed();
                    driver.findElement(By.cssSelector("a#pnnext>span.SJajHc.NVbCr")).click();

                    new WebDriverWait(driver, 3)
                            .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("html[itemtype='http://schema.org/SearchResultsPage']")));
                    page++;

                } catch (NoSuchElementException ee) {
                    System.out.println("Site not found at all");
                    break; }
            }

        } while (page < maxcycles);

    }


    @Test
    public void SortedItems(){
        driver = new ChromeDriver();
        String url = "https://demoqa.com/sortable/";
        driver.get(url);
        java.util.List<WebElement> items = driver.findElements
                (By.cssSelector("div.vertical-list-container.mt-4>div.list-group-item.list-group-item-action"));
        java.util.ArrayList<String> arrayitems = new java.util.ArrayList<String>();

        for (WebElement value : items) {
            System.out.println(value.getText());
            arrayitems.add(value.getText());
        }

        String[] mustbe = {"One", "Two", "Three", "Four", "Five", "Six"};
        assertArrayEquals(arrayitems.toArray(), mustbe);
        System.out.println("Sorted OK");
        WebElement From = driver.findElement(By.xpath("//*[@id='demo-tabpane-list']//div[text()='Two']"));
        WebElement To = driver.findElement(By.xpath("//*[@id='demo-tabpane-list']//div[text()='Five']"));

        Actions act = new Actions(driver);
        act.dragAndDrop(From, To).build().perform();
        System.out.println("Two to Five OK");
    }

    @Test
    public void SelectedItems() throws IOException {
        driver = new ChromeDriver();
        String url = "https://demoqa.com/selectable/";
        driver.get(url);
        driver.findElement(By.cssSelector("a#demo-tab-grid")).click();

        for (int i = 0; i < 3; i++) {
            java.util.List<WebElement> elements = driver.findElements(By.xpath("//*[@id='gridContainer']//li"));
            int size = elements.size();
            int randNumber = ThreadLocalRandom.current().nextInt(0, size);
            elements.get(randNumber).click();
        }

        driver.manage().window().maximize();
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
            FileUtils.copyFile(scrFile, new File("E:\\file\\Screen_" + timestamp + ".png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void RecSize() {
        driver = new ChromeDriver();
        String url = "https://demoqa.com/resizable/";
        driver.get(url);
        WebElement element = driver.findElement(By.cssSelector("div#resizableBoxWithRestriction"));
        Dimension size = element.getSize();
        System.out.println("Size h: " + size.height + " w: " + size.width);
        WebElement corner = driver.findElement(By.xpath("//*[@id='resizableBoxWithRestriction']/span"));

        Actions actionsResize = new Actions(driver);
            actionsResize.dragAndDropBy(corner, 100, 50).perform();
        Dimension size2 = element.getSize();
        System.out.println("Size2 h: " + size2.height + " w: " + size2.width);

            actionsResize.dragAndDropBy(corner, -100, -50).perform();
        Dimension size3 = element.getSize();
        System.out.println("Size3 h: " + size3.height + " w: " + size3.width);
    }

}





