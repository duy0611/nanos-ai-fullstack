import React from 'react';
import ReactDOM from 'react-dom';
import { createStore, applyMiddleware, } from 'redux';
import { Provider, } from 'react-redux';
import { Router, } from 'react-router-dom';
import { MuiThemeProvider, createMuiTheme, } from '@material-ui/core/styles';
import blue from '@material-ui/core/colors/blue';
import 'typeface-roboto';

import './index.scss';
import { history, } from './utils/routes';
import RootReducer from './rootReducer';
import AppRoot from './AppRoot/containers/AppRoot.container';
import * as serviceWorker from './utils/serviceWorker';
import apiMiddleware from './utils/apiMiddleware';

const store = createStore(
  RootReducer,
  window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__(),
  applyMiddleware(apiMiddleware)
);

const theme = createMuiTheme({
  palette: {
    primary: blue,
  },
  typography: {
    useNextVariants: true,
  },
});

ReactDOM.render(
  <Provider store={store}>
    <MuiThemeProvider theme={ theme }>
      <Router history={ history }>
        <AppRoot />
      </Router>
    </MuiThemeProvider>
  </Provider>,
  document.getElementById('root')
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: http://bit.ly/CRA-PWA
serviceWorker.unregister();
