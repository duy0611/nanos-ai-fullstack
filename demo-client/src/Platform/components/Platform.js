import React from 'react';
import PropTypes from 'prop-types';
import moment from 'moment';
import Typography from '@material-ui/core/Typography';
import Chip from '@material-ui/core/Chip';

import { renderSimpleInfo, jsonKeyToWord, } from '../../utils/utils';
import { IMAGE_URL, } from '../../utils/constants';
import '../styles.scss';

class Platform extends React.Component {
  static propTypes = {
    data: PropTypes.object,
    type: PropTypes.string,
  };

  renderDataArray = (label, array, type) => {
    let renderedValue;
    if (type === 'range') {
      renderedValue = array;
    } else if (type === 'tag') {
      renderedValue = array.map((item, index) => (
        <Chip key={ index } label={ item } variant='outlined' color='primary' classes={{ root: 'tag', }} />
      ));
    } else { // default type: array of strings
      renderedValue = array.reduce((total, current, index) => (
        `${ total } ${ current }${(index === array.length - 1) ? '' : ', '}`
      ), '');
    }
    return renderSimpleInfo(label, renderedValue);
  }

  renderCreativeHeader = creative => {
    return creative.header || (<span>{ creative.header1 }<br/>{ creative.header2 }</span>);
  }

  renderUrl = url => <a href={ url }>{ url }</a>

  renderCreativeImage = imgName => <img className='creative-image' src={ IMAGE_URL + imgName } alt='Creative' />

  renderInsights = (data) => Object.keys(data).map((key, index) => {
    const label = jsonKeyToWord(key);
    const value = data[key];
    return renderSimpleInfo(label, value, index);
  });

  render() {
    const { data, } = this.props;

    if (!data) { return null; }

    const target = data.targetAudiance;

    return (
      <div className='platform-container'>
        <div className='basic-info row'>
          <div>
            { renderSimpleInfo('Status', data.status) }
            { renderSimpleInfo('Total budget', data.totalBudget) }
            { renderSimpleInfo('Remaining budget', data.remainingBudget) }
          </div>
          <div>
            { renderSimpleInfo('Start date', moment(data.startDate).format('DD-MM-YYYY')) }
            { renderSimpleInfo('End date', moment(data.endDate).format('DD-MM-YYYY')) }
          </div>
        </div>
        <div className='platform-target'>
          <Typography variant='h6'>Target: </Typography>
          <div className='target-details'>
            <div className='row'>
              <div>
                { this.renderDataArray('Languages', target.languages) }
                { this.renderDataArray('Genders', target.genders.split(',')) }
              </div>
              <div>
                { this.renderDataArray('Age range', target.ageRange, 'range') }
                { this.renderDataArray('Locations', target.locations) }
              </div>
            </div>
            { this.renderDataArray('Interests', target.KeyWords || target.interests, 'tag') }
          </div>
        </div>

        <div className='platform-creatives'>
          <Typography variant='h6'>Creatives: </Typography>
          { renderSimpleInfo('Header', this.renderCreativeHeader(data.creatives)) }
          { renderSimpleInfo('Description', data.creatives.description) }
          { renderSimpleInfo('Url', this.renderUrl(data.creatives.url)) }
          { renderSimpleInfo('Image', this.renderCreativeImage(data.creatives.image)) }
        </div>

        <div className='platform-insights'>
          <Typography variant='h6'>Insights: </Typography>
          <div className='number'>
            { this.renderInsights(data.insights) }
          </div>
        </div>
      </div>
    );
  }
}

export default Platform;
