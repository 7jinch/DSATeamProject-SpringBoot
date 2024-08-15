/**
 * 헤더 컴포넌트의 버튼들에 회원가입, 로그인, 로그아웃 이벤트를 바인딩하는 함수
 */
import changeURL from './changeURL.js';
import renderPage from './renderPage.js';
import changeTitle from './changeTitle.js';

const bindHeaderButtonEvents = () => {
  const app = document.querySelector('#app');

  app.querySelector('.signup-page-button').addEventListener('click', () => {
    changeURL('/member/signup');
    renderPage();
    changeTitle('회원가입');
  });
  app.querySelector('.login-page-button').addEventListener('click', () => {
    changeURL('/member/login');
    renderPage();
    changeTitle('로그인');
  });
  app.querySelector('.logout-page-button').addEventListener('click', () => {});
};

export default bindHeaderButtonEvents;
