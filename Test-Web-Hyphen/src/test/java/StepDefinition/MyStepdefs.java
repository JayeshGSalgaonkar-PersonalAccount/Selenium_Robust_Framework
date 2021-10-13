package StepDefinition;

import DriverFactory.DriverManager;
import PageObject.MovieChillPages;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.*;

public class MyStepdefs {

    WebDriver driver;
    Properties properties;
    MovieChillPages movieChillPages;

    public MyStepdefs() throws IOException {
        properties = new Properties();
        properties = DriverManager.propertiesFile();
    }

    @Given("the server is running, Movies & Chills homepage is displayed")
    public void theServerIsRunningMoviesChillsHomepageIsDisplayed() throws IOException {

        String browser = properties.getProperty("browser");
        driver = DriverManager.setup(browser);

        String url = properties.getProperty("url");
        driver.get(url);

// Validate webpage components - Header, Search
        movieChillPages = new MovieChillPages(driver);
        movieChillPages.pageHeader();
        movieChillPages.pageSearch();

        System.out.println("Movies & Chills page open -> Success");

    }

    @When("validate total pages & total results")
    public void getMovieList() throws IOException {

// Single API get call to verify response & set expectations
        Response res = (Response) CommonFeatures.firstApiCall();
        int total_pages = res.jsonPath().get("total_pages");
        int total_results = res.jsonPath().get("total_results");

// List of films with original_title on the page
        List<String> original_titles_singlePage = res.jsonPath().getList("results.original_title");
        int count_original_title = original_titles_singlePage.size();

// Assert to test, 20 pictures displayed per page
        Assert.assertEquals(count_original_title, 20);

// Assert to test, total number of pictures received from API for all page

        // Total list of movies in API
        List<Object> original_titles_everyPage = new ArrayList<>();

        for (int i = 1; i <= total_pages; i++) {
            Response response = RestAssured.get("https://api.themoviedb.org/3/movie/popular?api_key=31678fb9d28d7cb3989d049c0061a995&page=" + i);
            List<Integer> counter_i = response.jsonPath().getList("results.original_title");
            original_titles_everyPage.add(counter_i);
        }

        Assert.assertEquals(total_results, original_titles_everyPage.size() * count_original_title);
        System.out.println("Total results & Total per page validation is successful, total result = " + total_results + ", total pages = " + total_pages);

        DriverManager.teardown();
    }

    @When("validate movies displayed on every page")
    public void validateMoviesDisplayedPerPage() throws IOException {

// Get all movie_names on page
        Response res = (Response) CommonFeatures.firstApiCall();
        int total_pages = res.jsonPath().get("total_pages");

        SoftAssert softAssert = new SoftAssert();

        for (int x = 1; x <= total_pages; x++) {
            Response response = RestAssured.get("https://api.themoviedb.org/3/movie/popular?api_key=31678fb9d28d7cb3989d049c0061a995&page=" + x);
            List<String> titles_x = response.jsonPath().getList("results.title");

            List<WebElement> panels = new ArrayList<>();
            for (int y = 1; y <= 20; y++) {
                WebElement panel = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[" + y + "]/div[1]/button[1]/div[1]/h2[1]"));
                panels.add(panel);
            }
            // Assertion per page
            for (int z = 0; z <= 19; z++) {
                softAssert.assertEquals(titles_x.get(z), panels.get(z).getText());
//                System.out.println(titles_x.get(z) + " - (from API) =  (display in panel) - " + panels.get(z).getText());
            }
            System.out.println(x);
            // Change page
            movieChillPages.nextButton();
        }
        softAssert.assertAll();
        DriverManager.teardown();
    }

    @When("check for duplicate movies in total number of pages")
    public void checkForDuplicateMoviesInTotalNumberOfPages() throws IOException {
        Response res = (Response) CommonFeatures.firstApiCall();
        int total_pages = res.jsonPath().get("total_pages");

        // Total list of movies in API
        List<String> original_titles_everyPage = new ArrayList<>();

        for (int i = 1; i <= total_pages; i++) {
            Response response = RestAssured.get("https://api.themoviedb.org/3/movie/popular?api_key=31678fb9d28d7cb3989d049c0061a995&page=" + i);
            List<String> counter_i = response.jsonPath().getList("results.title");
// Adding individual title to List<object>
            for (int x = 0; x < counter_i.size() - 1; x++) {
                original_titles_everyPage.add(counter_i.get(x));
            }
        }
        System.out.println(original_titles_everyPage);

        Set<String> set = new HashSet<>();
        Set<String> duplicate = new HashSet<>();

        for (String s : original_titles_everyPage) {
            if (!(set.add(s))) {
                duplicate.add(s);
            }
        }

        System.out.println(duplicate);

        Set<String> emptySet = new HashSet<>();
        Assert.assertEquals(duplicate, emptySet);

        DriverManager.teardown();
    }

    @When("user is able to click on selective movie for description")
    public void userIsAbleToClickOnSelectiveMovieForDescription() {
        System.out.println("To be implement");
        DriverManager.teardown();
    }


    @And("user is able to navigate back to movie list of same page \\(error observed, user is taken to first page)")
    public void userIsAbleToNavigateBackToMovieListOfSamePageErrorObservedUserIsTakenToFirstPage() {
        System.out.println("To be implement");
        DriverManager.teardown();
    }

    @When("user clicks on cast name")
    public void userClicksOnCastName() {
        System.out.println("To be implement");
        DriverManager.teardown();
    }

    @Then("cast details - Name, Biography should be displayed, with section Known For - listing more movies")
    public void castDetailsNameBiographyShouldBeDisplayedWithSectionKnownForListingMoreMovies() {
        System.out.println("To be implement");
        DriverManager.teardown();
    }

    @And("user is able to navigate back to same movie")
    public void userIsAbleToNavigateBackToSameMovie() {
        System.out.println("To be implement");
        DriverManager.teardown();
    }

    @When("user is able to navigate between pages")
    public void userIsAbleToNavigateBetweenPages() {
        System.out.println("To be implement");
        DriverManager.teardown();
    }

    @Then("user is able to navigate between different page slots")
    public void userIsAbleToNavigateBetweenDifferentPageSlots() {
        System.out.println("To be implement");
        DriverManager.teardown();
    }

    @Then("movie details page is open with Runtime, Overview & Top Billed Cast details \\(only five allowed)")
    public void movieDetailsPageIsOpenWithRuntimeOverviewTopBilledCastDetailsOnlyFiveAllowed() {
        System.out.println("To be implement");
        DriverManager.teardown();
    }
}
