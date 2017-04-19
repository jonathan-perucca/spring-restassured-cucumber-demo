Feature: the version can be retrieved and created

  Scenario: client makes call to GET /version
    When the client calls /version
    Then the client receives on get status code of 200
    And the client receives server version 1.0

  Scenario: client makes call to POST /version
    When the client post /version 2.0
    Then the client receives on post status code of 201
    And the client receives header location /version/2.0