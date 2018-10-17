import React from 'react';
import Typography from '@material-ui/core/Typography';

export const getParamsFromLocation = location => {
  if (!location || !location.search || !location.search.length) {
    return {};
  }
  const query = location.search.substr(1);
  let result = {};
  query.split('&').forEach((part) => {
    const item = part.split('=');
    result[item[0]] = decodeURIComponent(item[1]);
  });
  return result;
};

export const renderSimpleInfo = (label, value, key) => (
  <div key={ key }>
    <Typography variant='subtitle2' classes={{ root: 'info-label', }}>{ label }:</Typography> { value }
  </div>
);

export const capitalize = string => string.charAt(0).toUpperCase() + string.slice(1);

export const jsonKeyToWord = str => {
  const words = capitalize(str).split('_');
  return words.map((word, index) => word + (index < words.length ? ' ' : ''));
};
