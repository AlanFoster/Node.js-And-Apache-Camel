Node.js-Test
============

Node.js-Test is a simple shopping cart application that allows users to log in and add/remove items to a shopping cart
and logout again. It is simply an application designed to see many of the features available through Node.js

##Core Dependencies##

For this project the core dependencies are as follows

- express.js
- ejs

##Deploying##

Deploying this application is extremely easy as currently there are no external dependencies being used
(other than node.js modules)

    cd app
    npm install
    npm start

##Testing##

Currently before starting the application in development/production mode a small set of mocha tests are run.
The tests currently run basic connectivity tests to ensure that there is a valid webservice running, in order to ensure
no nasty surprises come along at runtime.

These mocha connectivity tests are in addition to the [behaviour tests](https://github.com/AlanFoster/Node.js-Test/tree/master/test) which are run

These connectivity tests will run before deployment, but you can also start them manually using the command

    npm test