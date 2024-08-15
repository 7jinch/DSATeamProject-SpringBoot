/**
 * 회원가입 페이지의 이벤트 활성화 함수
 */
import changeURL from './changeURL.js';
import renderPage from './renderPage.js';
import { bindModalActiveEvent } from './bindModalEvent.js';

const bindSignupButtonEvent = () => {
  const app = document.querySelector('#app');

  app.querySelector('.main-page-button').addEventListener('click', () => {
    changeURL('/');
    renderPage();
  });

  app
    .querySelector('.signup-page-button')
    .addEventListener('click', async () => {
      const userId = app.querySelector('#userId').value;
      const userPassword = app.querySelector('#userPassword').value;
      const userName = app.querySelector('#userName').value;
      const userPhone = app.querySelector('#userPhone').value;
      const userGender = app.querySelector('#userGender').value;
      const userBirth = app.querySelector('#userBirth').value;
      const userData = {
        eamil: userId,
        password: userPassword,
        name: userName,
        birth: userBirth,
        phone_number: userPhone,
        gender: userGender,
      };

      try {
        const response = await fetch('http://localhost:9000/api/member/', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(userData),
        });

        if (response.ok) {
          changeURL('/');
          renderPage();
        } else {
          bindModalActiveEvent();
        }
      } catch (error) {
        bindModalActiveEvent();
      }
    });
};

export default bindSignupButtonEvent;
