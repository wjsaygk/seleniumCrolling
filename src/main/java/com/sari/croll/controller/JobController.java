package com.sari.croll.controller;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sari.croll.model.Jobkorea;
import com.sari.croll.repository.JobkoreaRepository;


@Controller
public class JobController {

   public JobController() {
      //System Property SetUp
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        
        //Driver SetUp
        driver = new ChromeDriver();
        base_url = "http://www.jobkorea.co.kr/recruit/joblist?menucode=duty&duty=1000100";
   }
   
   @Autowired
   private JobkoreaRepository jRepo;
   
   private WebDriver driver;
    //Properties
    public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
    public static final String WEB_DRIVER_PATH = "C:\\Users\\admin\\Desktop\\selenium-java-3.141.59\\chromedriver_win32/chromedriver.exe";
    
    //크롤링 할 URL
    private String base_url;
    private WebElement webElement;
    
   @GetMapping("/job")
   public String parsing(Jobkorea job, Model model) {

      jRepo.delete();
      driver.get(base_url);
      for (int i = 2; i < 3; i++) {
         List<WebElement> elements= driver.findElements(By.cssSelector("#dev-gi-list > div > div.tplList.tplJobList > table > tbody > tr "));
         System.out.println("===================================================");
         for (WebElement webElement : elements) {
        	 
            WebElement name =webElement.findElement(By.cssSelector("td.tplCo > a"));
            // name.getText()는 회사이름, employee.getText()는 공고이름
            System.out.println("name : "+name.getAttribute("href"));
            WebElement employee = webElement.findElement(By.cssSelector("td.tplTit > div > strong > a"));
            System.out.println("employee : "+employee.getAttribute("href"));
            
           
            job.setCompanyName(name.getText()); // 회사이름
            job.setUrl(employee.getAttribute("href")); // 채용주소
            job.setTitle(employee.getText()); // 채용 내용
            job.setType(3);
            jRepo.save(job);
            
         }
         System.out.println("================="+i+"=====================");
            //WebElement element1 = driver.findElement(By.cssSelector("#dvGIPaging > div > ul > li:nth-child("+i+") > a"));
         /*
          * 위와 같은 코드의 경우에는 a에 달려있는 click할때 해당 링크로 넘어간다.
          * 근데 잡코리아 홈페이지에서는 페이지에 달린 링크가 먹통이다. 
          * 그래서 linkText를 이용하면 그냥 마우스로 페이지를 클릭하는 효과를 가질 수 있다.
          * 10>다음>11 
          */
         WebElement element = driver.findElement(By.linkText(Integer.toString(i)));
         element.click();
         
      }
      
      List<Jobkorea> jobkoreas = jRepo.findAll();
      model.addAttribute("jobkoreas", jobkoreas);
      return "croll/jobko";
   }
   
   @GetMapping("/joblist")
   public @ResponseBody List<Jobkorea> list (Jobkorea job, Model model) {

      jRepo.delete();
      driver.get(base_url);
      for (int i = 2; i < 5; i++) {
         List<WebElement> elements= driver.findElements(By.cssSelector("#dev-gi-list > div > div.tplList.tplJobList > table > tbody > tr "));
         System.out.println("===================================================");
         for (WebElement webElement : elements) {
        	 
            WebElement name =webElement.findElement(By.cssSelector("td.tplCo > a"));
            // name.getText()는 회사이름, employee.getText()는 공고이름
            System.out.println("name : "+name.getAttribute("href"));
            WebElement employee = webElement.findElement(By.cssSelector("td.tplTit > div > strong > a"));
            System.out.println("employee : "+employee.getAttribute("href"));
            
           
            job.setCompanyName(name.getText()); // 회사이름
            job.setUrl(employee.getAttribute("href")); // 채용주소
            job.setTitle(employee.getText()); // 채용 내용
            job.setType(3);
            jRepo.save(job);
            
         }
         System.out.println("================="+i+"=====================");
            //WebElement element1 = driver.findElement(By.cssSelector("#dvGIPaging > div > ul > li:nth-child("+i+") > a"));
         /*
          * 위와 같은 코드의 경우에는 a에 달려있는 click할때 해당 링크로 넘어간다.
          * 근데 잡코리아 홈페이지에서는 페이지에 달린 링크가 먹통이다. 
          * 그래서 linkText를 이용하면 그냥 마우스로 페이지를 클릭하는 효과를 가질 수 있다.
          * 10>다음>11 
          */
         WebElement element = driver.findElement(By.linkText(Integer.toString(i)));
         element.click();
         
      }
      
      List<Jobkorea> jobkoreas = jRepo.findAll();
      model.addAttribute("jobkoreas", jobkoreas);
      return jobkoreas;
   }
   
}