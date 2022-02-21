package stepdefinitions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.Driver;
import static utils.Driver.driver;

public class ManageToDoSteps {
	
	@Given("I am on the todo site")
	public void i_am_on_the_todo_site() {
		Driver.getDriver();
		driver.manage().window().maximize();
        driver.get("https://todomvc.com/examples/vue/");
        System.out.println("Homepage is displayed");
	}

	@Then("I should see the homepage todo with {string} banner")
	public void i_should_see_the_homepage_todo_with_banner(String string) {
        if (driver.findElement(By.xpath("//h1[contains(text(), 'todos')]")) != null){
            System.out.println("Banner element is present with text as: " + string);
        } else {
            System.out.println("Element can not be found");
        }
	}

	@Then("I should see a {string} text box where I can enter my todo")
	public void i_should_see_a_text_box_where_i_can_enter_my_todo(String string) {
        if (driver.findElement(By.xpath("//input[@placeholder = 'What needs to be done?']")) != null){
            System.out.println("Text box element is present with message displayed: " + string);
        } else {
            System.out.println("Element can not be found");
        }
	}

	@When("I enter my todos")
	public void i_enter_my_todos(io.cucumber.datatable.DataTable testData) {
        List<String> todos = testData.asList(String.class);
        int x = todos.size();
        for (int j=0; j<x; j++) {
            driver.findElement(By.xpath("//input[@placeholder = 'What needs to be done?']")).sendKeys(todos.get(j));
            driver.findElement(By.xpath("//input[@placeholder = 'What needs to be done?']")).sendKeys(Keys.ENTER);
        }

        System.out.println("I have entered my todos");
	}

	@Then("I should be able to see my entered todos in my todos table with a radio button on its side")
	public void i_should_be_able_to_see_my_entered_todos_in_my_todos_table_with_a_radio_button_on_its_side(io.cucumber.datatable.DataTable tesData) {
        List<String> todos = tesData.asList(String.class);
        int x = todos.size();
        for (int j=0; j<x; j++) {
            if (driver.findElement(By.xpath("//div[input[@type = 'checkbox'] and label[(text() = '" + todos.get(j) + "')]]")) != null) {
                System.out.println("To do entered is: " + todos.get(j) + " - with radio button on its side and has been added into todos table");
            } else {
                System.out.println("No todos were entered");
            }
        }
	}

	@Then("I should be able to see my total number of todos is {int}")
	public void i_should_be_able_to_see_my_total_number_of_todos_is(Integer totalToDos) {
        if (driver.findElement(By.xpath("//span[@class = 'todo-count']/strong[text() = '" + totalToDos + "']")) != null) {
            System.out.println("My total number of todos is: " + totalToDos);
        } else {
            System.out.println("No todos were added");
        }
	}

	@Then("I should be able to see filter buttons")
	public void i_should_be_able_to_see_filter_buttons(io.cucumber.datatable.DataTable testData) {
        List<String> todos = testData.asList(String.class);
        int x = todos.size();
        for (int j=0; j<x; j++) {
            if (driver.findElement(By.linkText(todos.get(j))) != null) {
                System.out.println(todos.get(j) + " button is present");
            } else {
                System.out.println(todos.get(j) + " button is not present");
            }
        }
	}

	@When("I tick the radio button of my todos")
	public void i_tick_the_radio_button_of_my_todos(io.cucumber.datatable.DataTable testData) {
        List<String> todos = testData.asList(String.class);
        int x = todos.size();
        for (int j=0; j<x; j++){
            driver.findElement(By.xpath("//label[text() = '" + todos.get(j) + "']/preceding-sibling::input[@type = 'checkbox']")).click();
            System.out.println(todos.get(j) + " radio button has been ticked");
        }
	}

	@Then("I should see in todos table that it has been strikethrough")
	public void i_should_see_in_todos_table_that_it_has_been_strikethrough(io.cucumber.datatable.DataTable testData) {
        List<String> todos = testData.asList(String.class);
        int x = todos.size();
        for (int j=0; j<x; j++) {
            if (driver.findElement(By.xpath("//label[text() = '" + todos.get(j) + "']/ancestor::li[@class = 'todo completed']")) != null) {
                System.out.println(todos.get(j) + " done...");
            } else {
                System.out.println(todos.get(j) + " not yet done..");
            }
        }
	}

	@Then("I should see in summary row that my total number of todo remaining is {int}")
	public void i_should_see_in_summary_row_that_my_total_number_of_todo_remaining_is(Integer totalToDos) {
        if (driver.findElement(By.xpath("//span[@class = 'todo-count']/strong[text() = '" + totalToDos + "']")) != null) {
            System.out.println("My total number of todos is: " + totalToDos);
        } else {
            System.out.println("No todos has been completed");
        }
	}

	@Then("a {string} button should appear")
	public void a_button_should_appear(String clearCompletedButton) {
        if (driver.findElement(By.xpath("//button[@class = 'clear-completed' and contains(text(), '" + clearCompletedButton + "')]")) != null) {
            System.out.println(clearCompletedButton + " button is present");
        } else {
            System.out.println("No todos has been completed");
        }
	}

	@When("I click {string} button")
	public void i_click_button(String string) {
        driver.findElement(By.linkText(string)).click();
        System.out.println(string + " button has been clicked");
	}

	@Then("todos table should display all active and completed todos")
	public void todos_table_should_display_all_active_and_completed_todos(io.cucumber.datatable.DataTable testData) {
        List<String> todos = testData.asList(String.class);
        int x = todos.size();
        int counter = 0;
        int j;
        for (j = 0; j < x; j++) {
            if (driver.findElement(By.xpath("//label[text() = '" + todos.get(j) + "']")) != null) {
                //will count my number of todos that I have entered.
            	counter++;
            } else {
            	//will deduct if it does not see the todos that I entered.
                counter--;
            }
        }
        //will compare if counter is equal to the number of todos I have entered.
        if (counter == j) {
            System.out.println("All button correctly displayed my active and completed todos");
        }
	}

	@Then("todos table should only display all of my active todos")
	public void todos_table_should_only_display_all_of_my_active_todos(io.cucumber.datatable.DataTable testData) {
        List<String> todos = testData.asList(String.class);
        int x = todos.size();
        int counter = 0;
        int j;
        for (j=0; j<x; j++) {
            if (driver.findElement(By.xpath("//label[text() = '" + todos.get(j) + "']/ancestor::li[@class = 'todo']")) != null) {
                counter++;
            } else {
                counter--;
            }
        }
        if (counter == j) {
            try {
                driver.findElement(By.xpath("//li[@class = 'todo completed']")).isDisplayed();
            } catch (Exception e) {
                System.out.println("Active button correctly display my active todos only");
            }
        }
	}

	@Then("todos table should only display all of my completed todos")
	public void todos_table_should_only_display_all_of_my_completed_todos(io.cucumber.datatable.DataTable testData) {
        List<String> todos = testData.asList(String.class);
        int x = todos.size();
        int counter = 0;
        int j;
        for (j=0; j<x; j++) {
            if (driver.findElement(By.xpath("//label[text() = '" + todos.get(j) + "']/ancestor::li[@class = 'todo completed']")) != null) {
                counter++;
            } else {
                counter--;
            }
        }
        if (counter == j) {
            try {
                driver.findElement(By.xpath("//li[@class = 'todo']")).isDisplayed();
            } catch (Exception e) {
                System.out.println("Completed button correctly displayed my completed todos only");
            }
        }
	}

	@Given("I have already completed some todos")
	public void i_have_already_completed_some_todos() {
        if (driver.findElement(By.xpath("//li[@class = 'todo completed']")) != null) {
            System.out.println("I have already completed some todos");
        } else {
            System.out.println("No todos has been completed");
    }
	}

	@When("I click the {string} button")
	public void i_click_the_button(String string) {
        driver.findElement(By.xpath("//button[@class = 'clear-completed']")).click();
        System.out.println(string + " button has been clicked");
	}

	@Then("all of my completed todo should be removed from my todos table")
	public void all_of_my_completed_todo_should_be_removed_from_my_todos_table() {
        try  {
            driver.findElement(By.xpath("//li[@class = 'todo completed']")).isDisplayed();
        } catch (Exception e) {
            System.out.println("Completed todos were removed");
        }
	}

	@Given("I have some todo")
	public void i_have_some_todo() {
        if (driver.findElement(By.xpath("//li[@class = 'todo']")) != null) {
            System.out.println("I have some to do");
        } else {
            System.out.println("No to do has been added");
        }
	}

	@When("I click the X button of my {string} todo")
	public void i_click_the_x_button_of_my_todo(String raveToDo) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//label[text() = '" + raveToDo + "']"))).perform();
        driver.findElement(By.xpath("//label[text() = '" + raveToDo + "']/following-sibling::button[@class = 'destroy']")).click();
        System.out.println("X button of " + raveToDo + " todo has been clicked");
	}

	@Then("{string} todo should be removed from my todos table")
	public void todo_should_be_removed_from_my_todos_table(String raveToDo) {
        try {
            driver.findElement(By.xpath("//label[text() = '" + raveToDo + "']")).isDisplayed();
        } catch (Exception e) {
            System.out.println(raveToDo + " todo has been removed from my todos table");
        }
	}

	@Then("my total number of todos is {int}")
	public void my_total_number_of_todos_is(Integer totalToDos) {
        if (driver.findElement(By.xpath("//span[@class = 'todo-count']/strong[text() = '" + totalToDos + "']")) != null) {
            System.out.println("My total number of todos is: " + totalToDos);
        } else {
            System.out.println("No todos were removed");
        }
        driver.quit();
	}

}
