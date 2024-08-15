/**
 * 메인 페이지('/')
 */
import changeTitle from '../utils/changeTitle.js';

import eventCarousel from '../components/eventCarousel.js';

const main = () => {
  changeTitle('지역 농산물 직거래 플랫폼');

  const html = `
    ${eventCarousel()}
  `;

  return html;
};

export default main;
