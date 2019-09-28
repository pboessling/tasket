# TODO

## Planned Improvements
- Daily Log
    - (/) Search for Collection for today
    - (/) If exists, display that collection
    - (!) Otherwise show:
        - (/) button/link to create new Collection
        - (x) (datepicker to select a specific Daily Log)
    - (/) Button -> Create Daily Log
        - (/) Creates new daily log for the given day in the backend
        - (/) Redirects to /dailylog/<date>
    - (/) Format headline 2019-09-27 -> Fri 27.09.2019
    - (/) Pagination Links:
        - (/) Previous day
        - (/) Today
        - (/) Next day
- URL patterns:
    - /dailylog
    - /dailylog/2019-09-26
    - /weeklylog
    - /weeklylog/2019-W01
    - /monthlylog
    - /monthlylog/2019-09
    - /yearlylog
    - /yearlylog/2019
- Cleanup code.
- For Daily Log -> show link "go to weekly log, monthly log, yearly log"
 
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
- Rank
    - https://stackoverflow.com/questions/9536262/best-representation-of-an-ordered-list-in-a-database
    - https://confluence.atlassian.com/jirakb/lexorank-management-779159218.html
    - https://stackoverflow.com/questions/40718900/jiras-lexorank-algorithm-for-new-stories
    - https://www.youtube.com/watch?v=OjQv9xMoFbg
   -  https://prezi.com/zu442jt_9z2m/lexorank/
- REST
    - https://restfulapi.net/rest-put-vs-post/