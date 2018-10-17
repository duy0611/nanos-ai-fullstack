import React from 'react';
import PropTypes from 'prop-types';

import CampaignsList from '../../Campaign/components/CampaignsList';
import CampaignDetails from '../../Campaign/containers/CampaignDetails.container';
import '../styles.scss';

class Dashboard extends React.Component {
  static propTypes = {
    campaigns: PropTypes.array,
    location: PropTypes.object,
    fetchAllCampaigns: PropTypes.func,
    selectCampaign: PropTypes.func,
  };

  componentDidMount() {
    this.props.fetchAllCampaigns();
  }

  render() {
    const { selectCampaign, campaigns, location, history, } = this.props;
    return (
      <div className='dashboard-container'>
        <div className='campaigns-wrapper'>
          <CampaignsList history={history} data={ campaigns } selectCampaign={ selectCampaign } location={ location } />
          <CampaignDetails history={history} location={ location } />
        </div>
      </div>
    );
  }
}

export default Dashboard;
