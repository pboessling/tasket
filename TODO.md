# TODO

## Planned Improvements
- Daily Log
    - Move item to other Daily Log (duplicate item and change item status to moved)
    - Refactor itemlist.js
    - Add date field to "Create new event" form
    - Edit event (text, date/time)
    - (?) Status for notes
    - (?) (datepicker to select a specific Daily Log)
    - (?) show link "go to weekly log, monthly log, yearly log of selected day"
- Weekly Log
- Monthly Log
- Yearly Log
- Custom collections    
- Cleanup code.

- SaaS vs. installable app that synchronizes to own cloud/storage 
- Clean up + document timer.js.
- Implement rank attribute for tasks.
- Persist rank after drag & drop.
- Implement columns per status (kanban board). 
    1. backlog, to-do, in-progress, done
    2. todo -> backlog/someday, planned -> today, selected/in progress -> pomodoro cycle, done
- Implement labels.
- Implement creationDate for tasks.
- Implement functionality to reorder rank of tasks.
- Implement functionality to change status for tasks.
- Implement blocker/waiting column (shelf).
- Implement pomodoro time for selected tasks.
- Implement templates for recurring tasks.
- Implement authentication.
- Implement creator attribute for tasks.
- Implement attribute for estimatedTime.
- Implement functionality to show sum of all efforts per column.
- Implement Web API.
- Decouple API from Frontend (webapp, api-gateway, backend-service).
- Implement rate limit for API.
- Implement integrations (e.g. Jira, Outlook)

## Icons
- Events
    - todo: fas fa-calendar
    - done: fas fa-calendar-check    
- Tasks
    - todo: fas fa-square
    - done: fas fa-check-square
- Notes
    - fas fa-file
- Actions
    - mark as done: item-action button fas fa-check-square
    - migrate: item-action button fas fa-caret-square-right
    - cancel: item-action button fas fa-minus-square
    - edit: item-action button fas fa-edit

## Related Products
- JIRA
- Trello
- https://tomato-timer.com/
- https://pomodoro-tracker.com/

## Helpful Resources
- Spring
    - https://spring.io/guides/gs/serving-web-content/
    - https://spring.io/guides/gs/accessing-data-jpa/
    - https://github.com/neocorp/spring-mvc-thymeleaf-crud
    - https://progressive-code.com/post/10/Simple-Spring-Boot-CRUD-application-with-Thymeleaf,-JPA-and-Bootstrap
    - https://medium.com/@joel.barmettler/quick-introduction-to-spring-boot-24a4414a559b
    - https://github.com/joelbarmettlerUZH/Spring_Boot_Demo
    - https://stackoverflow.com/questions/51781348/spring-boot-call-a-rest-controller-method-from-a-service
- Rank
    - https://stackoverflow.com/questions/9536262/best-representation-of-an-ordered-list-in-a-database
    - https://confluence.atlassian.com/jirakb/lexorank-management-779159218.html
    - https://stackoverflow.com/questions/40718900/jiras-lexorank-algorithm-for-new-stories
    - https://www.youtube.com/watch?v=OjQv9xMoFbg
   -  https://prezi.com/zu442jt_9z2m/lexorank/
- REST
    - https://restfulapi.net/rest-put-vs-post/