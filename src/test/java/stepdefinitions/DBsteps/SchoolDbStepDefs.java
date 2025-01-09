package stepdefinitions.DBsteps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.DBUtils;

import java.util.List;
import java.util.Map;

import org.junit.Assert;

public class SchoolDbStepDefs {
    private List<Map<String, Object>> queryResult;

    @Given("I connect to the {string} database")
    public void i_connect_to_the_database(String databaseName) {
        // Veritabanına bağlan
        DBUtils.createConnection();
        if (DBUtils.getConnection() == null) {
            throw new RuntimeException("Failed to connect to the database: " + databaseName);
        } else {
            System.out.println("Successfully connected to the database: " + databaseName);
        }
    }

    @When("I query {string}")
    public void i_query(String query) {
        // Sorguyu çalıştır ve sonuçları al
        queryResult = DBUtils.getQueryResultMap(query);
        Assert.assertNotNull("Query returned no results", queryResult);
    }

    @Then("the result should contain a student with first name {string} and last name {string}")
    public void the_result_should_contain_a_student_with_first_name_and_last_name(String firstName, String lastName) {
        // Öğrenci olup olmadığını kontrol et
        boolean studentFound = queryResult.stream().anyMatch(row ->
                row.get("first_name").equals(firstName) && row.get("last_name").equals(lastName)
        );
        Assert.assertTrue("Student not found: " + firstName + " " + lastName, studentFound);
    }

    @Then("the result should show the grade as {string}")
    public void the_result_should_show_the_grade_as(String expectedGrade) {
        // İlk sonucu al ve notu kontrol et
        Assert.assertFalse("Query result is empty", queryResult.isEmpty());
        String actualGrade = queryResult.get(0).get("grade").toString();
        Assert.assertEquals("Grade does not match", expectedGrade, actualGrade);
    }

    @Then("the result should show the total number of students as {string}")
    public void the_result_should_show_the_total_number_of_students_as(String expectedCount) {
        // Toplam öğrenci sayısını kontrol et
        Assert.assertFalse("Query result is empty", queryResult.isEmpty());
        String actualCount = queryResult.get(0).get("total_students").toString();
        Assert.assertEquals("Student count does not match", expectedCount, actualCount);
    }

    @Then("the result should contain a course named {string}")
    public void the_result_should_contain_a_course_named(String courseName) {
        // Ders olup olmadığını kontrol et
        boolean courseFound = queryResult.stream().anyMatch(row ->
                row.get("course_name").equals(courseName)
        );
        Assert.assertTrue("Course not found: " + courseName, courseFound);
    }
}
