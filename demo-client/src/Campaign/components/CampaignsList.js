import React from 'react';
import PropTypes from 'prop-types';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';

import '../styles.scss';
import { getParamsFromLocation, } from '../../utils/utils';

const tableHeaders = [
  { id: 'name', numeric: false, disablePadding: false, label: 'Campaign', },
  { id: 'goal', numeric: false, disablePadding: false, label: 'Goal', },
  { id: 'budget', numeric: true, disablePadding: false, label: 'Total budget', },
  { id: 'status', numeric: false, disablePadding: false, label: 'Status', },
];

class CampaignsList extends React.Component {
  static propTypes = {
    data: PropTypes.array,
    selectCampaign: PropTypes.func,
    location: PropTypes.object,
  };

  constructor(props) {
    super(props);
    const urlParams = getParamsFromLocation(props.location);
    this.state = {
      selectedRow: parseInt(urlParams['selectedCampaign']) || null,
    };
  }

  selectRow = id => () => {
    this.setState({ selectedRow: id, }, () => {
      this.props.selectCampaign(this.state.selectedRow);
      this.props.history.push(this.props.history.location.pathname + ('?selectedCampaign=' + this.state.selectedRow));
    });
  }

  isRowSelected = id => this.state.selectedRow === id;

  render() {
    const { data, } = this.props;
    return (
      <div className='campaigns-list'>
        <div className='campaigns-table-wrapper'>
          <Table>
            <TableHead>
              <TableRow>
                { tableHeaders.map(item => (
                  <TableCell
                    key={ item.id }
                    numeric={ item.numeric }
                    padding={ item.disablePadding ? 'none' : 'default' }
                  >{ item.label }</TableCell>
                )) }
              </TableRow>
            </TableHead>
            <TableBody>
              { data.map(campaign => (
                <TableRow
                  key={ campaign.id }
                  hover
                  onClick={ this.selectRow(campaign.id) }
                  tabIndex={ -1 }
                  selected={ this.isRowSelected(campaign.id) }
                >
                  <TableCell>{ campaign.name }</TableCell>
                  <TableCell>{ campaign.goal }</TableCell>
                  <TableCell numeric>{ campaign.totalBudget }</TableCell>
                  <TableCell>{ campaign.status }</TableCell>
                </TableRow>
              )) }
            </TableBody>
          </Table>
        </div>
      </div>
    );
  }
}

export default CampaignsList;
