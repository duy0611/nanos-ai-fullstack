import React from 'react';
import { shallow, } from 'enzyme';
import { expect, } from 'chai';
import sinon from 'sinon';
import IconButton from '@material-ui/core/IconButton';

import CampaignDetails from '../components/CampaignDetails';

function getWrapper(props) {
  const defaults = {
    selectedCampaignId: null,
    selectedCampaign: {},
    location: {},
    getDetails: () => n,
    selectCampaign: () => n,
  };
  return shallow(<CampaignDetails {...defaults} {...props} />);
}

describe('components/CampaignDetails', () => {
  it('renders a div.campaign-details.hidden', () => {
    const div = getWrapper().find('div.campaign-details.hidden');
    expect(div).to.have.lengthOf(1);
  });
  it('renders a div.campaign-details without hidden ', () => {
    const div = getWrapper({selectedCampaignId: 123,}).find('div.campaign-details.hidden');
    expect(div).to.have.lengthOf(0);
  });
  it('can not render campaign-details if selected selectCampaign is null', () => {
    const detailDiv = getWrapper({selectedCampaign: null,}).find('div.details-content');
    expect(detailDiv).to.have.lengthOf(0);
  });

  describe('components/CampaignDetails/closeDetailsView', () => {
    it('call selectCampaign and history push when closeDetailsView get called', () => {
      const selectCampaign = sinon.spy();
      const selectedCampaign = {
        id: 100000001,
        name: 'Test Ad 1',
      };
      const history = {
        push: sinon.spy(),
      };
      const location = {
        pathname: '12345',
      };
      const iconButton = getWrapper({selectCampaign, history, selectedCampaign, location,}).find(IconButton);
      iconButton.simulate('click');
      expect(selectCampaign.calledOnce).to.equal(true);
      expect(selectCampaign.getCall(0).args[0]).to.equal(null);

      expect(history.push.calledOnce).to.equal(true);
      expect(history.push.getCall(0).args[0]).to.equal(location.pathname);
    });
  });

  describe('components/CampaignDetails/selectTab', () => {
    it('call selectTab and expect this.setState get right object', () => {
      const selectCampaign = sinon.spy();
      const selectedCampaign = {
        platforms: {
          facebook: {},
          instagram: {'status': 'Delivering',},
        },
      };
      const wrapper = getWrapper({selectCampaign, selectedCampaign,});
      const instance = wrapper.instance();
      instance.selectTab(null, 1);
      expect(wrapper.state('selectedPlatformIndex')).to.equal(1);
      expect(wrapper.state('selectedPlatform')).to.equal(selectedCampaign.platforms.instagram);
      expect(wrapper.state('selectedPlatformType')).to.equal('instagram');

    });
  });
});
