package com.sari.croll.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sari.croll.model.Jobkorea;
import com.sari.croll.model.Saramin;
import com.sari.croll.model.User;
import com.sari.croll.repository.JobkoreaRepository;
import com.sari.croll.repository.SaraminRepository;
import com.sari.croll.repository.UserRepository;
import com.sari.croll.utils.Script;

@Controller
public class  SaramController {
public SaramController() {
		
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		
		driver = new ChromeDriver();
		url = "http://www.saramin.co.kr/zf_user/jobs/list/job-category?page=1&cat_key=40404&search_optional_item=n&search_done=y&panel_count=y&isAjaxRequest=0&page_count=50&sort=RL&type=job-category&is_param=1&isSearchResultEmpty=1&isSectionHome=0&searchParamCount=1#searchTitle";
		base_url = "http://www.jobkorea.co.kr/recruit/joblist?menucode=duty&duty=1000100";
	}
	
	@Autowired
	 private SaraminRepository sRepo;
	@Autowired
	 private JobkoreaRepository jRepo;
	@Autowired
	private UserRepository uRepo;

	JobController jobController = new JobController();
	//webDriver
	private WebDriver driver;
	//Properties
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:\\Users\\admin\\Desktop\\selenium-java-3.141.59\\chromedriver_win32/chromedriver.exe"; 
	
		
	StringBuffer sb= new StringBuffer();	
	private String url;
	private String base_url;

	
	@GetMapping("/sa")
	public String saramin(Saramin saram, Model model)  {		
	
		try {
			
			sRepo.delete();
			//get page (= 브라우저에서 url을 주소창에 넣은 후 request 한 것과 같다)
			driver.get(url);
			/* System.out.println(driver.getPageSource()); */
			/*
			 * WebElement weblist = driver.findElement(By.className("recruit_list_renew"));
			 * WebElement webtitle =
			 * weblist.findElement(By.className("common_recruilt_list"));
			 */
			
			WebElement webid = driver.findElement(By.id("default_list_wrap"));
			WebElement webtest = webid.findElement(By.tagName("tbody"));
			//WebElement webcompany = webtest.findElement(By.tagName("td"));
			
//			System.out.println(webtest.getText());
			List<WebElement> list = webtest.findElements(By.tagName("tr"));
			for (WebElement w : list) {
				
			
				//id
				System.out.println(saram.getId());
				//Companyname
				System.out.println("***회사이름****");
				saram.setCompanyName(w.findElement(By.className("company_nm")).findElement(By.className("str_tit")).getText());
				// System.out.println(w.findElement(By.className("company_nm")).getText()); 
				System.out.println(saram.getCompanyName());
				//Title
				System.out.println("***채용내용*****");
				saram.setTitle(w.findElement(By.className("job_tit")).getText());				
				// System.out.println(w.findElement(By.className("job_tit")).getText()); 
				System.out.println(saram.getTitle());
				//URL
				System.out.println("***URL****"); 
				saram.setUrl(w.findElement(By.className("job_tit")).findElement(By.className("str_tit")).getAttribute("href"));
				//System.out.println(w.findElement(By.className("job_tit")).findElement(By.className("str_tit")).getAttribute("href"));
				System.out.println(saram.getUrl());
				
//				//timestamp
//				System.out.println("***createDate***");
//				saramin.setCreateDate(timestamp);
//				System.out.println(saramin.getCreateDate());
				
				//Type = 1
				System.out.println("****saramin type****");
				saram.setType(1);
				System.out.println(saram.getType());
				sRepo.save(saram);
				
				
				
				
				
//				List<WebElement> td =w.findElements(By.tagName("td"));
//				for (WebElement text : td) {
//					System.out.println("-------------------");
//					System.out.println(text.getText());
//				}
			}
//			WebElement test = webtest.findElement(By.className("job_tit"));
//			System.out.println(test.getText());
			
			
			
			
			/*
			 * String[] str = webcompany.getText().split("\n"); for (String string : str) {
			 * System.out.println(string); // Saramin saramin = new Saramin(); //
			 * saramin.setTitle(t); }
			 */
			
			/* System.out.println(webtitle.getText()); */
			
			/*
			 * for (WebElement element : webtitle) {
			 * 
			 * }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		
		List<Saramin> sarams = sRepo.findAll();
		model.addAttribute("sarams", sarams);
		
		return "croll/saram";
	}
	
	//유저페이지	
	@GetMapping("/saramfind")
	public @ResponseBody List<Saramin> saramfindPage () {
		List<Saramin>  sarams = sRepo.findAll();				
		return sarams;
	}
	//유저페이지
	@GetMapping("/jobfind")
	public @ResponseBody List<Jobkorea> jobfindPage () {
		List<Jobkorea> Jobkoreas = jRepo.findAll();			
		return Jobkoreas;
	}
	//유저페이지
	@GetMapping("/userpage")
	public String userPage(Model model, HttpSession session) {
		List<Saramin> sarams = sRepo.findAll();
		List<Jobkorea> jobkoreas = jRepo.findAll();
		model.addAttribute("sarams", sarams);
		model.addAttribute("jobkoreas", jobkoreas);
		session.invalidate();
		return "croll/userpage";
	}
	
	
	
	//관리자 페이지 ,  크롤링 하기
	@GetMapping("/list")
	public String managerpage (Model model) {
		List<Saramin>  sarams = sRepo.findAll();
		List<Jobkorea>  jobkoreas = jRepo.findAll();		
		
		model.addAttribute("sarams", sarams);
		model.addAttribute("jobkoreas", jobkoreas);

		return "croll/employment";
	}
	@PostMapping("/managerlogin")
	public @ResponseBody String login(User user, HttpSession session, HttpServletResponse response) {
		
		User u = uRepo.findByid(user.getUsername(),user.getPassword());	
		
		 if(u == null ) {
			 
			 return Script.Back("정보가 일치하지 않습니다.");
		 }else {
			 session.setAttribute("user", u);
			 System.out.println(user.getUsername());
			 System.out.println(user.getPassword());
			 return Script.href("/list");
		 }
		
		
	}
	
	@GetMapping("/managerloginForm")
	public String loginForm() {
		return"login/manager";
	}
	
	//회사정보 (ifram)
	@GetMapping("/saramInformation")
	public String infor(@RequestParam String url, Model model) {
		System.out.println("url : "+url);
		model.addAttribute("url", url);
		return "infor/InformationSaram";
	}
	
	//회사정보 (ifram)
		@GetMapping("/jobInformation")
		public String inforjob(@RequestParam String url, Model model) {
			System.out.println("url : "+url);
			model.addAttribute("url", url);
			return "infor/InformationJob";
		}
	
	
	//클로링 하기
	@GetMapping("/saramtest12")
	public @ResponseBody List<Saramin> saramin122(Saramin saram, Model model)  {		
	
		try {
			
			sRepo.delete();
			//get page (= 브라우저에서 url을 주소창에 넣은 후 request 한 것과 같다)
			driver.get(url);
			/* System.out.println(driver.getPageSource()); */
			/*
			 * WebElement weblist = driver.findElement(By.className("recruit_list_renew"));
			 * WebElement webtitle =
			 * weblist.findElement(By.className("common_recruilt_list"));
			 */
			
			WebElement webid = driver.findElement(By.id("default_list_wrap"));
			WebElement webtest = webid.findElement(By.tagName("tbody"));
			//WebElement webcompany = webtest.findElement(By.tagName("td"));
			
//			System.out.println(webtest.getText());
			List<WebElement> list = webtest.findElements(By.tagName("tr"));
			for (WebElement w : list) {
				
			
				//id
				System.out.println(saram.getId());
				//Companyname
				System.out.println("***회사이름****");
				saram.setCompanyName(w.findElement(By.className("company_nm")).findElement(By.className("str_tit")).getText());
				// System.out.println(w.findElement(By.className("company_nm")).getText()); 
				System.out.println(saram.getCompanyName());
				//Title
				System.out.println("***채용내용*****");
				saram.setTitle(w.findElement(By.className("job_tit")).getText());				
				// System.out.println(w.findElement(By.className("job_tit")).getText()); 
				System.out.println(saram.getTitle());
				//URL
				System.out.println("***URL****"); 
				saram.setUrl(w.findElement(By.className("job_tit")).findElement(By.className("str_tit")).getAttribute("href"));
				//System.out.println(w.findElement(By.className("job_tit")).findElement(By.className("str_tit")).getAttribute("href"));
				System.out.println(saram.getUrl());
				
//				//timestamp
//				System.out.println("***createDate***");
//				saramin.setCreateDate(timestamp);
//				System.out.println(saramin.getCreateDate());
				
				//Type = 1
				System.out.println("****saramin type****");
				saram.setType(1);
				System.out.println(saram.getType());
				sRepo.save(saram);
				
				
				
				
				
//				List<WebElement> td =w.findElements(By.tagName("td"));
//				for (WebElement text : td) {
//					System.out.println("-------------------");
//					System.out.println(text.getText());
//				}
			}
//			WebElement test = webtest.findElement(By.className("job_tit"));
//			System.out.println(test.getText());
			
			
			
			
			/*
			 * String[] str = webcompany.getText().split("\n"); for (String string : str) {
			 * System.out.println(string); // Saramin saramin = new Saramin(); //
			 * saramin.setTitle(t); }
			 */
			
			/* System.out.println(webtitle.getText()); */
			
			/*
			 * for (WebElement element : webtitle) {
			 * 
			 * }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		
		List<Saramin> sarams = sRepo.findAll();
		model.addAttribute("sarams", sarams);
		
		return sarams;
	}
	
	
	
	//클로링 하기
	@GetMapping("/jobtest12")
	   public @ResponseBody List<Jobkorea> list (Jobkorea job, Model model) {

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
	      return jobkoreas;
	   }
	
}
