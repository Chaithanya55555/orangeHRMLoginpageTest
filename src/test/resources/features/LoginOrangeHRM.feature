Feature: Login
  As a user login
  I want to access feature in the website
  As result redirected to Homepage
  Scenario: Login successfully
    Given I am on orangeHRM website
    When Enter username , password
    And Click on login button
    Then I redirected to home page
  Scenario: Enter invalid username
    Given I am on orangeHRM website
    When Enter invalid username and Click on login button
    Then Verify the error message for username
  Scenario: Enter invalid password
    Given I am on orangeHRM website
    When Enter invalid password and Click on login button
    Then Verify the error message for password
  Scenario: Enter valid username and invalid password
    Given I am on orangeHRM website
    When Enter valid username and invalid password and Click on login button
    Then Verify the error message for invalid password
  Scenario: Enter invalid username and valid password
    Given I am on orangeHRM website
    When Enter invalid username and valid password and Click on login button
    Then Verify the error message for invalid username
  Scenario: Verify CSS values
    Given I am on orangeHRM website
    When Get font family , font size , back-ground color and font weight
    Then verify the CSS values
  Scenario:Forgot password
    Given I am on orangeHRM website
    When Click on forgot password link , enter username and click on reset password
    Then  Redirect to forgot password webpage and successfully completed
  Scenario: Social media
    Given I am on orangeHRM website
    When Click on every social media and verify it
    Then  Redirect to respected web pages
  Scenario: Company page
    Given I am on orangeHRM website
    When Click on company link and verify
    Then  Redirect to company page