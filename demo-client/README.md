# Advertising Campaigns Management Client

## Getting started
---

### Introduction
A client app that visualizes a list of Advertising Campaigns, along with their details and platforms.

### Technologies
* ReactJS v16.5
* Redux v4
* React-router v4
* Material-UI v3.2
* Axios v0.18

### Installation

In the project directory, we can run:

### `npm start`

Runs the app in the development mode.<br>
Open [http://localhost:3000](http://localhost:3000) to view it in the browser.

The page will reload if you make edits.<br>
You will also see any lint errors in the console.

### `npm test`

Launches the test runner in the interactive watch mode.

### `npm run build`

Builds the app for production to the `build` folder.<br>
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.<br>
Now our app is ready to be deployed!

## Folder structure
---

This app's structure was bootstrapped with [Create React App](https://github.com/facebook/create-react-app).

```
app/
├───public
└───src
│   ├───assets
│   │   └───images
│   ├───AppRoot
│   │   ├───components
│   │   │       AppRoot.js
│   │   ├───containers
│   │   │       AppRoot.container.js
│   │   ├───test
│   │   │   styles.scss
│   ├───Campaign
│   │   ├───components
│   │   │       CampaignDetails.js
│   │   │       CampaignsList.js
│   │   ├───containers
│   │   │       CampaignDetails.container.js
│   │   ├───test
│   │   │   actions.js
│   │   │   reducers.js
│   │   │   styles.scss
│   ├───Dashboard
│   │   ├───components
│   │   │       Dashboard.js
│   │   ├───containers
│   │   │       Dashboard.container.js
│   │   ├───test
│   │   │   styles.scss
│   ├───Platform
│   │   ├───components
│   │   │       Platform.js
│   │   ├───containers
│   │   ├───test
│   │   │   styles.scss
│   ├───utils
│   │       apiActions.js
│   │       apiMiddleware.js
│   │       constants.js
│   │       createActions.js
│   │       routes.js
│   │       serviceWorker.js
│   │       utils.js
│   │   App.js
│   │   App.scss
│   │   App.test.js
│   │   index.js
│   │   index.scss
│   │   logo.svg
│   │   rootReducer.js
│   .env
│   .eslintrc
│   .gitignore
│   data.json
│   document.md
│   jsconfig.json
│   package.json
│   README.md
│   yarn.lock 
```

## UI Decisions
---

The app layout consists of 2 main parts:

* **The left sidebar**: While it has only 1 link to the Home url, the sidebar actually serves more purposes. A sidebar is essential in the dashboard of a management app. It gives user the idea of navigating around the app. It is also a *secret reminder* to developer that he must always take web responsiveness into consideration, since the sidebar usually takes a considerable space of the screen.
* **Main content on the right**: In the */home* page, it will be the dashboard. Since all the campaigns share the same few pieces of basic info, it makes sense to present them in a table with selectable rows. When a row/campaign is selected, the *details panel* will slide in from the right.

   *Details panel* provides user with an overall perspective of all campaigns, the currently selected one along with its details. Each campaign consists of different advertising platforms divided into tabs. Users can switch between tabs to view and compare platforms, scroll through the panel and still clearly know what campaign they're viewing, where it is in the list. At the same time, jumping to another campaign is just one click away.

## Conventions
---

### MODULES

Represents a feature, a view or a group of them. Modules are the basic but also biggest building blocks of our app, because they contain a lot of information with different types.

In React's perspective, modules are also components. In our directory structure, they are enlisted as direct sub-folders of ```/src```, then divided into smallers relative components. With this convention, the modules are more scalable for future developments.

There are 2 types of components in each modules, residing in their respectively named folders:

* **Components**, also known as **Presentational** Components. 

* **Containers**.

*What are those and why?*

As we are using Redux to manage states, we follow the pattern above which is recommended by Redux. You can read more about it [here](https://redux.js.org/basics/usagewithreact#designing-component-hierarchy).

*TL;DR:* **Presentational Components** "describe the *look* but don't know *where* the data comes from, or *how* to change it" and "have no dependency on Redux". **Container Components** "connect the presentational components to Redux".


### ACTIONS

There are 2 types of Actions:

#### Pure Redux Actions:

Because they have no *side-effects*.

These actions are created by leveraging the **redux-actions** package. Specifically, I wrote a helper function in ```/src/utils/createActions.js```, which receives action *types* (I call them *labels* to avoid misunderstanding with other data types) to produce respectively functions. These functions are then connected by Container components for later *dispatch*-ing.

By using this helper function **once** (in file ```actions.js``` of each module), we only have to declare Action Labels from now on. No more spending time repeatedly writing an action creator for each one of them.

#### Async Actions:

These actions trigger network requests with **axios** promises, then dispatch consequential actions based on the response. Each of them is created with a(nother) helper function in  ```/src/utils/apiActions.js```, receiving at least:

* API url.
* Action Label.
* A function to resolve successful promise: dispatch an action with response data.
* A function to resolve failed promise: dispatch an action with error data.

You can see an example below.

#### Error handling:

Any errors that occur during an async action calling network requests will be handled by a beforehand provided function. For example, here is an async function to request the list of all campaigns from the API server:

```javascript
  // actions.js
  export const fetchAllCampaigns = () => apiRequest({
    url: API_URL + '/campaigns',
    actionName: actionLabels.ASYNC_FETCH_ALL_CAMPAIGNS,
    onSuccess: response => ({
      type: actionLabels.ASYNC_FETCH_ALL_CAMPAIGNS_SUCCESS,
      payload: response,
    }),
    onFailure: error => ({
      type: actionLabels.ASYNC_FETCH_ALL_CAMPAIGNS_FAILED,
      payload: error.response,
    }),
  });

  // reducers.js
  const campaignReducer = handleActions({
    ...
    ASYNC_FETCH_ALL_CAMPAIGNS_FAILED: (state, action) => ({
      ...state,
      error: action.payload,
    }),
    ...
  });
```

When the network promise is resolved, the ```onSuccess``` and ```onFailure``` functions will be conditionally dispatched to reducers by a custom api middleware (see below). The reducer will update its *error* property upon receiving an error action. The connected ```AppRoot``` component will then receive an *error* prop and show a toast on the screen.

### NETWORK REQUESTS

This app uses a custom middleware to handle API requests. The inspiration is from this [blog post](https://blog.logrocket.com/data-fetching-in-redux-apps-a-100-correct-approach-4d26e21750fc).
Whether this approach is 100% correct or not is still debatable, but here are the reasons it's a suitable choice:

* Based on the scope of the task given, this middleware provides more than enough functionalities. Therefore, no extra packages which means a smaller bundle size.
* Although this approach does not (yet) support features such as request cancellation, throttling, debouncing..., it has a gentle learning curve for developers, with or without experience with redux-saga or redux-observable.
* A custom middleware allows for future implementation of business-specific requirements.

### REDUX STATE
Provided the scope of this task, there is only one reducer ```campaignReducer```.

* Default state:
``` javascript
const defaultState = {
  campaigns: [],
  selectedCampaignId: null,
  selectedCampaign: null, // will be fetched from the above id. This makes the store more adaptive to future api changes
};
```

* When a campaign is selected:
``` javascript
{
  campaignReducer: {
    campaigns: [
      {...},
      {...},
      {...},
    ],
    selectedCampaignId: 100000001,
    selectedCampaign: {
      id: 100000001,
      name: 'Test Ad 1',
      goal: 'Increase Reach',
      ...
    }
  }
}
```
