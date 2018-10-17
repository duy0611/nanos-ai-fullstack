import React from 'react';
import PropTypes from 'prop-types';
import IconButton from '@material-ui/core/IconButton';
import CloseIcon from '@material-ui/icons/Close';
import Typography from '@material-ui/core/Typography';
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';

import Platform from '../../Platform/components/Platform';
import { getParamsFromLocation, renderSimpleInfo, } from '../../utils/utils';

const defaultState = {
  selectedPlatformIndex: 0,
  selectedPlatform: null,
};

class CampaignDetails extends React.Component {
  static propTypes = {
    selectedCampaignId: PropTypes.number,
    selectedCampaign: PropTypes.object,
    location: PropTypes.object,
    getDetails: PropTypes.func,
    selectCampaign: PropTypes.func,
  };

  constructor(props) {
    super(props);
    this.urlParams = getParamsFromLocation(props.location);
    this.campaignId = props.selectedCampaignId;
    if (this.urlParams['selectedCampaign']) {
      this.campaignId = parseInt(this.urlParams['selectedCampaign']);
    }
    this.state = defaultState;
  }

  componentDidMount() {
    if (this.campaignId && this.campaignId !== this.props.selectedCampaignId) {
      this.props.selectCampaign(this.campaignId);
      this.props.getDetails(this.campaignId);
    }
  }

  componentDidUpdate(prevProps) {
    const { selectedCampaignId, selectedCampaign, } = prevProps;

    if (selectedCampaignId !== this.props.selectedCampaignId) {
      if (this.props.selectedCampaignId) { // new campaign is selected
        this.props.getDetails(this.props.selectedCampaignId);
      }
      // or current campaign details view is closed
      this.setState({
        ...defaultState,
      });
    }

    if (this.props.selectedCampaign && selectedCampaign !== this.props.selectedCampaign) {
      const platformKeys = Object.keys(this.props.selectedCampaign.platforms);
      this.setState({
        selectedPlatform: this.props.selectedCampaign.platforms[platformKeys[this.state.selectedPlatformIndex]],
      });
    }
  }

  closeDetailsView = () => {
    this.props.selectCampaign(null);
    this.props.history.push(this.props.location.pathname);
  }

  selectTab = (event, value) => {
    this.setState({
      selectedPlatformIndex: value,
      selectedPlatform: this.props.selectedCampaign.platforms[value],
    });
  }

  render() {
    const { selectedCampaign , selectedCampaignId, } = this.props;
    const { selectedPlatformIndex, selectedPlatform, } = this.state;
    return (
      <div className={`campaign-details${!!selectedCampaignId ? '' : ' hidden'}`}>
        {!!selectedCampaign &&
          <div>
            <IconButton onClick={ this.closeDetailsView } className='close'>
              <CloseIcon fontSize='small' />
            </IconButton>
            <div className='details-content'>
              <Typography variant='h4'>{ selectedCampaign.name }</Typography>
              <Typography variant='h5'>{ selectedCampaign.goal }</Typography>
              { renderSimpleInfo('Budget', selectedCampaign.totalBudget) }
              { renderSimpleInfo('Status', selectedCampaign.status) }
              <div className='platforms-section'>
                <Typography variant='button' >Platforms</Typography>
                <Tabs
                  value={ selectedPlatformIndex }
                  onChange={ this.selectTab }
                  indicatorColor='primary'
                  textColor='primary'
                  fullWidth
                >
                  { selectedCampaign.platforms.map((platform, index) => (
                    <Tab key={ index } label={ platform.type } />
                  )) }
                </Tabs>
                <Platform data={ selectedPlatform } />
              </div>
            </div>
          </div>
        }
      </div>
    );
  }
}

export default CampaignDetails;
