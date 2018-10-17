import createHistory from 'history/createBrowserHistory';
import DashboardContainer from '../Dashboard/containers/Dashboard.container';

export const history = createHistory();

export const paths = {
  dashboard: '/home',
};

const routes = [
  {
    path: paths.dashboard,
    name: 'Home',
    exact: true,
    component: DashboardContainer,
  },
];

export default routes;
