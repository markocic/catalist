# Catalist
Android app for listing cat breeds and details about them.
This was done as a part of the Mobile Application Development course at School 
of Computing, Union University.

## Table of contents
1. [Installation](#installation)
2. [Functional requirements](#functional-requirements)
3. [Technical requirements](#technical-requirements)
4. [Visual requirements](#visual-requirements)
5. [Backend API](#backend-api)
6. [Screenshots](#screenshots)

## Installation
Download an apk from releases page and install it on your android device.
Minimum requirements are Android 8.0.

## Functional requirements
Application should contain 3 screens

1. Breeds List Screen
Screen should show list of every known cat breed with the following information
- Name of the breed
- Alternative names if available
- description of the breed (max 250 characters)
- At most 3 different temperament characteristics

Clicking on any list item should open the next screen showing more details.
Search button should be available to lead to the search screen.

2. Breeds Details Screen
Screen should contain the following information about a specific breed
- Minimum of one picture
- Name of the breed
- Full description
- Country of origin
- All temperament characteristics
- Life span
- Average weight
- Minimum of 5 UI widgets about the behaviour and needs of the breed
- Is it a rare breed?
- Button that will open wikipedia page with more information

3. Search Breeds\
Searching is done only by the breed name.
The user enters a search query based on which the results are shown.
List items should be clickable and lead to the details screen.
Author can choose to create a new screen or implement this functionality as part
of the existing screen.
Author can choose to filter data locally or use an api endpoint.

## Technical requirements
1. Application must be written in **Kotlin**.
2. Asynchronous programming should be implement using **Kotlin Coroutines**
3. User interface must be written with **Jetpack Compose**.
4. Application should have only one Activity and use **Jetpack Navigation** to
   navigate screens
5. Every screen has to implement **Model-View-Intent** architecture.
6. API calls should be implement with **Retrofit** and **OkHttp** libraries
7. Application should support loading and errors states.
8. For serialization use **Kotlinx Serialization** library.

## Visual requirements
Only requirement is to use **Material Design 3** Jetpack UX.

## Backend API
All needed data can be fetched from [The Cat API](https://thecatapi.com).

## Screenshots

Breeds List Screen | Breeds Details Screen
:------------------|----------------------:
![](https://i.imgur.com/dARkJr7.jpeg) | ![](https://i.imgur.com/u8JMqKV.jpeg)

