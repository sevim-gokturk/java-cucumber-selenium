@Db
Feature: Database Testing for School System

  Background: Connect to the school database
    Given I connect to the "school" database

  Scenario: Verify that a student exists in the database
    When I query "SELECT * FROM students WHERE first_name = 'Liam' AND last_name = 'Smith';"
    Then the result should contain a student with first name "Liam" and last name "Smith"

  Scenario: Verify the grade of a specific student
    When I query "SELECT grade FROM students WHERE first_name = 'Sophia' AND last_name = 'Garcia';"
    Then the result should show the grade as "76"

  Scenario: Count the total number of students in the database
    When I query "SELECT COUNT(*) AS total_students FROM students;"
    Then the result should show the total number of students as "14"

  Scenario: Check if a specific course exists in the database
    When I query "SELECT * FROM courses WHERE course_name = 'Mathematics';"
    Then the result should contain a course named "Mathematics"
