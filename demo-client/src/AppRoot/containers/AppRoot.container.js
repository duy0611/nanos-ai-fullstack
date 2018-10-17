import {connect,} from 'react-redux';

import AppRoot from '../components/AppRoot';
import { pureCampaignActions, } from '../../Campaign/actions';

const mapStateToProps = state => ({
  error: state.campaignReducer.error,
});

export default connect(mapStateToProps, {
  removeError: pureCampaignActions.removeError,
})(AppRoot);
