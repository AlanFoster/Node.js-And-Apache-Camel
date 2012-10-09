Node.js-Test
============

Node.js-Test is a simple shopping cart application that allows users to log in and add/remove items to a shopping cart
and logout again. It is simply an application designed to see many of the features available through Node.js

##Dependencies##

For this project the core dependencies are as follows

- express.js
- ejs

## Deploying

Deploying this application is extremely easy as currently there are no external dependencies being used
(other than node.js modules)

    cd app
    npm install
    node app.json

##Testing##

For testing I am looking into writing behaviour tests using the gherkin syntax.

The core modules used for testing are as follows

- Cucumber.js
- Zombie.js

You can run the tests for this project with. Ensure the application is running prior to running these tests

    npm test