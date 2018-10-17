import {connect,} from 'react-redux';

import Dashboard from '../components/Dashboard';
import { fetchAllCampaigns, pureCampaignActions, } from '../../Campaign/actions';

const mapStateToProps = state => ({
  campaigns: state.campaignReducer.campaigns,
});

export default connect(mapStateToProps, {
  fetchAllCampaigns,
  selectCampaign: pureCampaignActions.selectCampaign,
})(Dashboard);
