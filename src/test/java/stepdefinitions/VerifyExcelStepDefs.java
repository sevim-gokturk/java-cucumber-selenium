package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utilities.ExcelUtil;

import java.util.List;
import java.util.Map;

public class VerifyExcelStepDefs {
    private ExcelUtil excelUtil;
    private List<Map<String, String>> actualData;

    // Load the Excel file and read the data from the specified sheet
    @Given("I load the Excel file {string} and sheet {string}")
    public void iLoadTheExcelFileAndSheet(String fileName, String sheetName) {
        // Initialize ExcelUtil with the file path and sheet name
        String filePath = "src/test/resources/testdata/" + fileName; // Adjust the path as necessary
        excelUtil = new ExcelUtil(filePath, sheetName);
    }

    // Read the data from the Excel sheet into a List of Maps
    @When("I read the data from the Excel file")
    public void iReadTheDataFromTheExcelFile() {
        actualData = excelUtil.getDataList();
    }

    // Verify the data in the Excel sheet against the provided data table
    @Then("I verify the following data exists:")
    public void iVerifyTheFollowingDataExists(DataTable expectedDataTable) {
        List<Map<String, String>> expectedData = expectedDataTable.asMaps(String.class, String.class);

        // Verify each row in the expected data
        for (Map<String, String> expectedRow : expectedData) {
            boolean rowMatches = false;

            // Iterate over the actual data and compare rows
            for (Map<String, String> actualRow : actualData) {
                if (expectedRow.equals(actualRow)) {
                    rowMatches = true;
                    break; // Row matches, no need to check further
                }
            }

            // Assert that the expected row exists in the actual data
            Assert.assertTrue("Expected row " + expectedRow + " not found in actual data.", rowMatches);
        }
    }
}
