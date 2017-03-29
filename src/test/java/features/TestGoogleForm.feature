Feature: google form testing

  Scenario: Go thought form

    When you fill first and second question
    And validate that third question is mandatory
    And fill third question and go to another step
    And fill next questions
    And go back to first step
    And reverse text in third question
    And go to second step
    And check that both questions are still filed
    And go to last step
    And fill last question and send form
