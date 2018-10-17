import {connect,} from 'react-redux';

import CampaignDetails from '../components/CampaignDetails';
import { fetchCampaignDetails, pureCampaignActions, } from '../../Campaign/actions';

const mapStateToProps = state => ({
  selectedCampaignId: state.campaignReducer.selectedCampaignId,
  selectedCampaign: state.campaignReducer.selectedCampaign,
});

export default connect(mapStateToProps, {
  getDetails: fetchCampaignDetails,
  selectCampaign: pureCampaignActions.selectCampaign,
})(CampaignDetails);
