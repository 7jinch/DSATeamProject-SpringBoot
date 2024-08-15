/**
 * 페이지 렌더링 함수
 */

// 페이지
import main from '../pages/main.js';
import memberSignup from '../pages/memberSignup.js';
import memberLogin from '../pages/memberLogin.js';

import getURL from './getURL.js';
import concatHeaderFooterPage from './concatHeaderFooterPage.js';
import bindEvnetFunc from './bindEventFunc.js';
import bindSignupButtonEvent from './bindSignupButtonEvent.js';
import { bindModalCloseEvent } from './bindModalEvent.js';
import bindHeaderTitleEvent from './bindHeaderTitleEvent.js';

const renderPage = () => {
  const url = getURL();
  // const app = document.querySelector('#app');

  switch (url) {
    case '/': // 메인 페이지로 이동
      concatHeaderFooterPage(main());
      bindEvnetFunc(bindHeaderTitleEvent);
      break;
    case '/member/signup': // 회원가입 페이지로 이동
      concatHeaderFooterPage(memberSignup());
      bindEvnetFunc(
        bindHeaderTitleEvent,
        bindSignupButtonEvent,
        bindModalCloseEvent
      );
      break;
    case '/member/login':
      concatHeaderFooterPage(memberLogin());
      bindEvnetFunc(
        bindHeaderTitleEvent,
        bindSignupButtonEvent,
        bindModalCloseEvent
      );
      break;
    default:
      console.log(`알 수 없는 URL: ${url}`);
  }
};

export default renderPage;
