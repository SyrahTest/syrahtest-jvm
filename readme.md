# Welcome to SyrahTest

[![Build Status](https://travis-ci.org/SyrahTest/syrahtest-jvm.svg?branch=master)](https://travis-ci.org/SyrahTest/syrahtest-jvm)

SyrahTest is a sweet API that pairs well with other test automation tools that support JVM Languages.

Note: SyrahTest is currently under development.  This page represents the goals and final deliverables of the project at some point in the future.  If you are interested in any of the features please provide feedback or consider contributing.

## Why the name?

Great test automation solutions should be very easy to author and maintain tests.  To be effective they should catch bugs as quickly as possible.  One summer night while enjoying a glass of wine on the porch I couldn't help but notice the eagerness with which bugs were attracted to my wine.  And that is how SyrahTest was born.

## What does it do?

#### Shortcomings with many of the popular open source and vendor solutions for test automation:
1. Data driven testing is limited to data that must be maintained in a single row per test.  There is no ability to normalize the test data so as to remove undesireable repetition.
1. For service based testing, most tools maintain a request and/or response for each test case.  This makes long term maintenance of a large test suite very costly.
1. Version control - Several possibilities:
    * The project file(s) are hard to share and version across a team
    * A vendor solution is integrated into the tool that is not standard or best in class
    * The tool has no version control so changes made inside the tool are not detected by the VCS
1. Lack of ubiquitous development toolset - why should a developer or automation engineer have to leave the idea to execute tests and view results?
1. Little emphasis or ability to monitor and enforce test code quality.  Often testers re-invent ad-hoc scripting which is embedded in test steps.

### SyrahTest solves for these by setting the following goals:

1. Allows for relational data structures that can be mapped to inputs or outputs using a set of transformation instructions.
1. Applies templating to (1) inject data into a payload or (2) validate a response by applying validation instructions.
1. Is based on plain text files and any JVM based language, allowing you to use your current VCS (example: Subversion, Git, etc).
1. Allowing you to use your current build lifecycle (i.e. Maven, Gradle, etc) and testing framework (i.e. JUnit, Cucumber, etc.)
1. Because its JVM based best in class tools like SonarQube can be used to evaluate the test code quality alongside production code.

## How does it work?

SyrahTest is designed to integrate with tools you're already using, or it can be used as a comprehensive test framework by itself.

* Annotations allow data sources to be configured and made available inside of test methods
* An assertion framework allows multiple assertions to be made inside of a test without failing the test immediately 
* A number of Aspects are available for you to easily attach SyrahTest to your existing test suites.



Given a JSON payload or a HTML/XML payload

