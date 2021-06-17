# Movie App Mobile REST Pagination - Level 3

Your task is to build a mobile app that integrates with the Movie Catalog REST API and presents the retrieved data. Please agree with your hiring team regarding the tech stack choice.

The app should have a movie list view and a movie details view. 

Tapping on a movie on the list should open the details view for that movie. There should also be a way to navigate back to the list view from the details view.

## Mockup

![movie-app](https://user-images.githubusercontent.com/1162212/110803365-6876b680-827f-11eb-8619-01d51bee57cc.png)

## REST API

### Request examples

#### Get all movies

https://playground.devskills.co/api/rest/movie-app/movies

#### Get 5 movies starting from index 3

https://playground.devskills.co/api/rest/movie-app/movies/limit/5/offset/3

#### Get the movie with id 5

https://playground.devskills.co/api/rest/movie-app/movies/5

## What we expect from you

1. Implement pagination for the movie list. It's up to you to define the page limit. We want to see your approach to dealing with large data sets.
2. Implement error handling for cases when the API cannot be reached or returns a server error.
3. Ensure that the app remains responsive while the data is being loaded.
4. Avoid duplication and extract re-usable components where it makes sense. We want to see your approach to creating a codebase that is easy to maintain.
5. Unit test one component of choice. There is no need to test the whole app, as we only want to understand what you take into consideration when writing unit tests.
6. Add instructions describing how to run your app locally.
7. Push your code to the `implementation` branch. We encourage you to commit and push your changes regularly as it's a way for us to understand your thinking process.
8. Create a new Pull Request - **Do NOT merge it** ⚠️ . That's how we know that you're ready.
9. Answer the questions you get on your Pull Request.

## Need help?

Create a new GitHub issue on this repository, and we'll get back to you as soon as we can.

## Time estimate

About 3 hours. But don't worry! There is no countdown. This number is for you to plan your time.

---

Made by [DevSkills](https://devskills.co).

How was your experience? **Give us a shout on [Twitter](https://twitter.com/DevSkillsHQ) / [LinkedIn](https://www.linkedin.com/company/devskills)**.
