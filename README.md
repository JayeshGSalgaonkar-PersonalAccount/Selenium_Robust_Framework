# MoviesChill
A simple app to browse movies. The application has three main pages;
* The `movie listing page` that shows a paginated grid of movie cards. For each movie on the page a movie poster image, movie title, and release date wil be displayed.
* The `movie details page` that can be accessed by clicking on the movie card from the movie listing page. This will show a much richer set of details about the movie including overview, runtime, and a set of cast cards showing the main actors.
* The `cast page` that can be accessed by clicking on cats tiles in the movie details page. It shows details about the cast member, including a section of movie cards showing movies they have appeared in


# Prerequisites 
You'll need `node`, `npm`, and `yarn` installed to run the application. You're solution should be written in Selenium and Java

# Setup
Run the following commands
```sh
yarn && yarn start
```
You should now be able to access the application at http://localhost:7000/


## Task

The codebase only has a few unit tests an no browser-based regression tests. To ensure the correctness of the application end-to-end regression tests should be added. Using Selenium (cucumber) and Java, create a test suite that covers this application. You'll need to come up with some test cases, and write the automation code to run them. Create a pull request in this repo with your solution.
