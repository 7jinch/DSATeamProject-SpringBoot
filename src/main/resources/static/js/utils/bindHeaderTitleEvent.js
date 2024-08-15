import changeURL from './changeURL.js';
import renderPage from './renderPage.js';

const bindHeaderTitleEvent = () => {
  document.querySelector('#header-title').addEventListener('click', () => {
    changeURL('/');
    renderPage();
  });
};

export default bindHeaderTitleEvent;
