# About TvShowManager
implementation of GraphQl Task

Hello there ,This project is an Android Application with clean architecture and MVVM using some of the jetpack libraries with Kotlin Coroutines & Dagger Hilt.
This app is based on the Guide to app architecture article, Kotlin 1.4, and coroutine. I also used some android architecture components like LiveData, ViewModel.

#Project Architecture

- UI calls method from ViewModel.
- ViewModel executes Use case.
- Use case executes one or multiple Repositorie function.
- The Repository returns data from one or multiple Data Sources(Remote-Rick and morty GraphQL , Local-Room DB if (found)). the repository is the single source of truth
- Information flows back to the UI where we display the data fetched from data sources.


#Project Structure

- Data
This is my data layer and consists of the Repository implementation class as well as the remote and local data sources and mappers
- Domain
This is the domain layer and consists of the domain models as well as the usecases.
- UI
This is the presentation layer. I have set up packages by feature here. This consists of the view related code.
- DI
This is where Dagger related code lives , I wanted to try out Hilt and see what it can deliver.
- Utils
This is where most extension functions and constants.

#Libraries Used

- ViewModel - store and manage UI-related data in a lifecycle conscious way
- LiveData - notify the view when data changes .
- ViewBinding - easily write code that interacts with view
- Material - Material Components.
- Coroutine - performs background tasks
- Flows - for asynchronous data streams
- Apollo- GraphQL client for the JVM, Android and Kotlin multiplatform
- Dagger Hilt - dependency injector

