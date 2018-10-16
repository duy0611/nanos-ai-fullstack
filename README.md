# Fullstack Assesment

The goal of this test is not to evaluate how fast you deliver results but rather your problem solving and decision making skills.

For this test, we do not provide design mockups in order to let the developer express their Front-end knowledge, but we expect  you to come up with a clean and easy to navigate user interface.

If you get in "the zone" while working on the test and feel that you can do more then the minimum requirements of the task, please feel free to impress us with your skills and knowhow. It will not go unnoticed.

Feel free to use packages, libraries, Frameworks and even starters/code generators or code snippets, but please mention any code that is not yours and justify the use in a sentence or two.

We expect you to use the following technologies for the Front-end:
- ReactJS
- Redux(if you judge needed)
- Redux Sagas (if you judge needed)
- React Router
- Material UI
- Axios

For the Backend please choose one of the following:
- NodeJS
- Spring
- Python(Jango or Flask).

We highly apprecaite candidates who deliver their projects on a containerized environment using Docker (Docker-compose Kubernates are also welcome).


### We Will be evaluating you on the following:

- Documentation
- Code Structure
- Code readability and style(we expect you to use best practices and indicate what guidelines/best practices you followed)
- Logic behind your decisions and choices
- Error handling
- Testing

### We will not be evaluating you on the following:

- User experience(Animation, Transitions ...etc)
- Design decisions(colors, layout... etc)
- Speed

### Recommended deliverables:

- Detailed documentation that explains how to build and run the project,(instructions on how to deploy in production would also be appreciated but not a must)
- Detailed list of your choices and justification of the decisions that you feel need to be explained
- link to a repository where we can find the source code of the project
- List of external libraries, packages, frameworks, starters and any other external code used in the project.

## The Task:

Included in this repository you will find a file named data.json in the /data folder.
The file contains a list of Advertising campaigns created by a single user over time. each campaign is composed of different platforms (Google AdWords, Facebook, Instagram)

We would like to expose this list via a simple API endpoint and allow the client app to consume it and display all the campaigns of the user in a compact view(something in the line of a dashboard, grid or even a table).
Please feel free to use a database of your choice(SQL or NoSQL) and pre-populate it every time the service is started with the content of the json file as you see fit.

In the client app we expect the user to be able to move from the high-level view to a detailed view where they would be able to see all the information and details of a single campaign(this can be a separate view or an expended view in the dashboard, or even a modal ... pick what you feel is better and justify your choice).

In the Repository you will also find the /Images folder where all the creative content of each campaign is stored.

Please provide a solution to expose these images and allow the client app to have access to them and display them for each campaign

Please write tests wherever and whenever you feel adequate

### All images in the /images folder where originally downloaded from UpSplash

`img1.jpg Photo by Jacob Miller on Unsplash`

`img2.jpg Photo by Ash Edmonds on Unsplash`

`img3.jpg Photo by Carl Heyerdahl on Unsplash`

`img4.jpg Photo by Tim Gouw on Unsplash`
