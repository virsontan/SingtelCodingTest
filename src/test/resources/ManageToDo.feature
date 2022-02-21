Feature: Manage todo list

  Background:
    Given I am on the todo site

  Scenario: Verify todo homepage
    Then I should see the homepage todo with "todos" banner
    And I should see a "what needs to be done" text box where I can enter my todo

  Scenario: Verify my entered todos has been added in todos table and to do summary row
    When I enter my todos
      | eat    |
      | sleep  |
      | rave   |
      | repeat |
    Then I should be able to see my entered todos in my todos table with a radio button on its side
      | eat    |
      | sleep  |
      | rave   |
      | repeat |
    And I should be able to see my total number of todos is 4
    And I should be able to see filter buttons
      | All       |
      | Active    |
      | Completed |

  Scenario: Complete todos
    When I tick the radio button of my todos
      | eat    |
      | sleep  |
    Then I should see in todos table that it has been strikethrough
      | eat    |
      | sleep  |
    And I should see in summary row that my total number of todo remaining is 2
    And a "Clear completed" button should appear

  Scenario: Verify filter buttons are working
    When I click "All" button
    Then todos table should display all active and completed todos
      | eat    |
      | sleep  |
      | rave   |
      | repeat |
    When I click "Active" button
    Then todos table should only display all of my active todos
      | rave   |
      | repeat |
    When I click "Completed" button
    Then todos table should only display all of my completed todos
      | eat    |
      | sleep  |

  Scenario: Clear completed todos
    And I have already completed some todos
    When I click the "Clear completed" button
    Then all of my completed todo should be removed from my todos table

  Scenario: Remove a todo
    And I have some todo
    When I click the X button of my "rave" todo
    Then "rave" todo should be removed from my todos table
    And my total number of todos is 1