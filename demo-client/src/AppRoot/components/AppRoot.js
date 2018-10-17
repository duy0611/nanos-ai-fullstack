import React, {Component,} from 'react';
import PropTypes from 'prop-types';
import { Route, Switch, Redirect, Link, withRouter, } from 'react-router-dom';
import Drawer from '@material-ui/core/Drawer';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';
import Snackbar from '@material-ui/core/Snackbar';
import IconButton from '@material-ui/core/IconButton';
import CloseIcon from '@material-ui/icons/Close';

import routes, { paths, history, } from '../../utils/routes';
import '../styles.scss';

class AppRoot extends Component {
  static propTypes = {
    error: PropTypes.object,
    removeError: PropTypes.func,
  };

  constructor(props) {
    super(props);
    this.state = {
      errorToastMessage: '',
    };
  }

  componentDidUpdate(prevProps) {
    if (this.props.error && !prevProps.error) {
      const { error, } = this.props;
      this.setState({ errorToastMessage: this.getErrorMessage(error), });
    }
  }

  getErrorMessage = error => {
    let message = 'Sorry! Something went wrong.';
    if (error.status) {
      switch (error.status) {
        case 404:
          message = 'Data not found.';
          break;
        // other cases to be defined later
        default:
          break;
      }
    }
    return message;
  }

  closeErrorToast = (event, reason) => {
    if (reason === 'clickaway') { return; }

    this.props.removeError();
    this.setState({ errorToastMessage: '', });
  }

  render() {
    const { errorToastMessage, } = this.state;

    return (
      <div className='app'>
        <Drawer variant='permanent' open classes={{ paper: 'sidebar-paper', }}>
          <List>
            <ListItem button>
              <ListItemText><Link to={ paths.dashboard }>Home</Link></ListItemText>
            </ListItem>
          </List>
        </Drawer>
        <div className='main-content'>
          <Switch>
            {routes.map((route, index) => (
              <Route
                key={ index }
                path={ route.path }
                render={props => <route.component { ...props } history={history}/>}
                exact={ route.exact }
              />
            ))}
            <Redirect to={ paths.dashboard } />
          </Switch>
        </div>

        {/* show a toast message if there is an error */}
        <Snackbar
          anchorOrigin={{
            vertical: 'bottom',
            horizontal: 'left',
          }}
          open={ !!errorToastMessage.length }
          autoHideDuration={ 4000 }
          onClose={ this.closeErrorToast }
          ContentProps={{
            'aria-describedby': 'error-toast-msg',
          }}
          message={ <span id='error-toast-msg'>{ errorToastMessage }</span> }
          action={[
            <IconButton
              key='close'
              aria-label='Close'
              color='inherit'
              className='close'
              onClick={ this.closeErrorToast }
            >
              <CloseIcon />
            </IconButton>,
          ]}
        />
      </div>
    );
  }
}

export default withRouter(AppRoot);
