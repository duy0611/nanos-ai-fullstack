import createActions from '../utils/createActions';
import {apiRequest,} from '../utils/apiActions';
import {API_URL,} from '../utils/constants';

export const actionLabels = {
  ASYNC_FETCH_ALL_CAMPAIGNS: 'ASYNC_FETCH_ALL_CAMPAIGNS',
  ASYNC_FETCH_ALL_CAMPAIGNS_SUCCESS: 'ASYNC_FETCH_ALL_CAMPAIGNS_SUCCESS',
  ASYNC_FETCH_ALL_CAMPAIGNS_FAILED: 'ASYNC_FETCH_ALL_CAMPAIGNS_FAILED',
  SELECT_CAMPAIGN: 'SELECT_CAMPAIGN',
  ASYNC_FETCH_CAMPAIGN_DETAILS: 'ASYNC_FETCH_CAMPAIGN_DETAILS',
  ASYNC_FETCH_CAMPAIGN_DETAILS_SUCCESS: 'ASYNC_FETCH_CAMPAIGN_DETAILS_SUCCESS',
  ASYNC_FETCH_CAMPAIGN_DETAILS_FAILED: 'ASYNC_FETCH_CAMPAIGN_DETAILS_FAILED',
  REMOVE_ERROR: 'REMOVE_ERROR',
};

export const fetchAllCampaigns = () => apiRequest({
  url: API_URL + '/ad-campaigns',
  actionName: actionLabels.ASYNC_FETCH_ALL_CAMPAIGNS,
  onSuccess: response => ({
    type: actionLabels.ASYNC_FETCH_ALL_CAMPAIGNS_SUCCESS,
    payload: response,
  }),
  onFailure: error => ({
    type: actionLabels.ASYNC_FETCH_ALL_CAMPAIGNS_FAILED,
    payload: error.response,
  }),
});

export const fetchCampaignDetails = (id) => apiRequest({
  url: API_URL + '/ad-campaigns/' + id,
  actionName: actionLabels.ASYNC_FETCH_CAMPAIGN_DETAILS,
  onSuccess: response => ({
    type: actionLabels.ASYNC_FETCH_CAMPAIGN_DETAILS_SUCCESS,
    payload: response,
  }),
  onFailure: error => ({
    type: actionLabels.ASYNC_FETCH_CAMPAIGN_DETAILS_FAILED,
    payload: error.response,
  }),
});

export const pureCampaignActions = {...createActions(actionLabels),};
