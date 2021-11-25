Feature: User Login

  @login @wordpress @userpanel
  Scenario: Login
    Given user starts on main page
    When user logs to the user panel
    Then user user can modify user profile