# Project Rubric

## Background

*This captures the expectations that we have for your team during the unit.
This is how we will evaluate whether you have demonstrated these expectations.*

## Instructions

*As a team, complete this rubric (everything except for the Appendix) by
answering the questions below. Each question should usually only require one or
two sentences, so please be brief. The remainder of expectations will be
evaluated by instructors, so you don’t need to write anything in the Appendix.
We want you to see the full set of expectations for transparency’s sake.*

## Deliverables

*Provide links to the following project deliverables:*

| Deliverable                                                                |Due Date                  | Date Completed | URL                                                                                                        |
|----------------------------------------------------------------------------|---                       |----------------|------------------------------------------------------------------------------------------------------------|
| Team name                                                                  |Sprint 1 Module 1         | July 5 2023    | name:  SBJOS                                                                                               |
| [Design Document - problem statement](design_document.md)                  |Sprint 1 Module 2         | July 9 2023    | https://github.com/BloomTechBackend/bd-team-project-sbjos/commit/55ca966a81f753b40fd2f1aae87794462e0b73c6  |
| [Team Charter](team_charter.md)                                            |Sprint 1 Module 3         | July 10 2023   | https://github.com/BloomTechBackend/bd-team-project-sbjos/commit/55ca966a81f753b40fd2f1aae87794462e0b73c6  |
| [Design Document](design_document.md)                                      |Sprint 1 REQUIRED TO GO ON| July 11 2023   | https://github.com/BloomTechBackend/bd-team-project-sbjos/commit/55ca966a81f753b40fd2f1aae87794462e0b73c6  |
| Project Completion (Feature Complete)                                      |Sprint 3                  |                |                                                                                                            |
| [Team Reflection](reflection.md)                                           |Sprint 3                  |                |                                                                                                            |
| [Accomplishment Tracking (person 1)](accomplishment_tracking_template.md)  |Sprint 3                  |                |                                                                                                            |
| [Accomplishment Tracking (person 2)](accomplishment_tracking_template.md)  |Sprint 3                  |                |                                                                                                            |
| [Accomplishment Tracking (person 3)](accomplishment_tracking_template.md)  |Sprint 3                  |                |                                                                                                            |
| [Accomplishment Tracking (person 4)](accomplishment_tracking_template.md)  |Sprint 3                  |                |                                                                                                            |
| Self Reflection                                                            |Sprint 3                  |                | n/a (will be submitted via Canvas - "Wrap-up" location)                                                     |

## Technical Learning Objectives

### API Design

**Design an API to meet the needs of your application.** Provide a link to the
definition for your endpoints (can be code/configuration, or can be
documentation). List one thing that your team learned about designing a good
API.

**Endpoint definition location:**
- [InventoryManagement-api.yaml](..%2Fbackend%2Fsrc%2Fmain%2Fresources%2FAPI%2FInventoryManagement-api.yaml)

**What we learned:** 
- [Things I have learned](Learned)

**Develop a service endpoint definition that uses complex inputs and outputs.**
Select one of your endpoints and list the operation’s input and output objects.
Under each, list its attributes.

**Endpoint:**
- GetItemService

**Input object(s):**
- Controller

**attribute:**
- Gets the information provided by the user in the UI. The class runs the complex
operation to fetch the right invormation. 

**Output object(s):** 
- ItemResult

**attribute:** 
- Returns the requested information for an object or in a list for a group of objects

**Given a user story that requires a user to provide values to a service
endpoint, design a service endpoint including inputs, outputs, and errors.**
Select one of your endpoints that accepts input values from a client. List the
error cases you identified and how the service responds in each case. Provide at
most 3 error cases.

| **Endpoint:**                                                                    | GetItemService                   |
|----------------------------------------------------------------------------------|----------------------------------|
| **Error case**                                                                   | **Service response**             |
| If a user provides an invalid name                                               | Throws ItemNotFoundException     |                                    |                                                 |
| If a user is requesting all available items in a category and none are available | Throws ItemListNotFoundException |

**Develop a service endpoint definition that uses query parameters to determine
how results are sorted or filtered.** List at least one endpoint that allows
sorting or filtering of results. Which attribute(s) can be sorted/filtered on?

**Endpoint:**
- GetItemService

**Attribute(s):**
- Filters by availability category or location.

**Determine whether a given error condition should result in a client or server
exception.** List one client exception and one server exception that your
service code throws. List one condition in which this exception is thrown.

|                       | **Exception**              | **One case in which it is thrown**                     |
|---	                |----------------------------|--------------------------------------------------------|
|**Client exception:**  | ItenNotFoundException      | When a user is looking for an item that does not exist |
|**Service exception:** | InvalidAttributeException  | When a user added an invalid name. 	                   |

### DynamoDB Table Design

**Decompose a given set of use cases into a set of DynamoDB tables that provides
efficient data access.** List the DynamoDB tables in your project:

1. IM-Item
2. IM-Category
3. IM-Location


**Design a DynamoDB table key schema that allows items to be uniquely
identified.** Describe the primary key structure for your DynamoDB table with
the most interesting primary key. In a sentence or two, explain your choice of
partition/sort key(s).

- [ItemTable.yaml](..%2Fbackend%2Fsrc%2Fmain%2Fresources%2Fcloudformation%2FItemTable.yaml)
    This table contains a primary key which is the item's name for uniqueness, and a set of GSI for 
filtering purposes.

**Design the attributes of a DynamoDB table given a set of use cases.** List a
DynamoDB table with at least 3 attributes. List one relevant use case that uses
the attribute. In one sentence, describe why the attribute is included.

**Table name:** IM-Item

**Attributes:**

| Attribute name | (One) relevant use case | attribute purpose                                 |
|----------------|-------------------------|---------------------------------------------------|
| itemName       | Find by name            | To find the item                                  |
| category       | Find by category        | To find a list of items by category               |
| available      | Find by availability    | To find a list of item based on thir availability |
| location       | Find by location        | To find a list of items based on their location   ||

### DynamoDB Indexes

**Design a GSI key schema and attribute projection that optimizes queries not
supported by a provided DynamoDB table.** In one or two sentences, explain why
you created one of the GSIs that your project uses, including one use case that
uses that index.

**Table name:** IM-Item

**Table keys:** itemName

**GSI keys:**  
- available
- location
- category

**Use case for GSI:**

**Implement functionality that uses query() to retrieve items from a provided
DynamoDB's GSI.** List one of your use cases that uses `query()` on a GSI.

**Table name:** IM-Item

**Use case for `query()` on GDI:**  
- Find by category
- Find by location
- Find by availability
- Find availability in a category
- Find a category in a location

## Soft(er) Outcomes

**Learn a new technology.** For each team member, list something new that that
team member learned:

| Team Member | Something new the team member learned                                                                                         |
|-------------|-------------------------------------------------------------------------------------------------------------------------------|
| Sebastien   | Learned about SpringBoot - API design - Aquired more experience with Dagger - AWS - Testing classes - HTML - CSS - JavaScript |

**Identify key words to research to accomplish a technical goal | Use sources
like sage and stack overflow to solve issues encountered while programming.**
Give an example of a search term that your team might have used to find an
answer to a technical question/obstacle that your team ran into. List the
resource that you found that was helpful.

**Search terms:**  
- DynamoDB Annotations  
- dynamodb reserved words  
- SpringBoot dependency structure  
- SpringBoot with lambda configuration  
- Dagger dependency

**Helpful resource:**  
- [AWS Documentation](https://docs.aws.amazon.com/)  
- [StackOverflow](https://stackoverflow.co/)  
- [DynamoDB with SpringBoot](https://howtodoinjava.com/spring-boot/access-dynamodb-with-spring/#2-4-disable-repository-scanning-if-spring-data-is-included)
- [Springboot setup](https://start.spring.io/)
- [Youtube](https://www.youtube.com/)

**Find material online to learn new technical topics.** List one resource that
your team found on your own that helped you on your project.


**Topic:**  
- AWS Lambda and DynamoDB  
- SpringBoot  
- Dagger
- CORS
- API

**Resource:**

**Share what was worked on yesterday, the plan for today, and any blockers as a
part of a scrum standup.** In one or two sentences, describe what your team
found to be the most useful outcome from holding daily standups.

1.

